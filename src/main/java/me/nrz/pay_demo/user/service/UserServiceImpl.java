package me.nrz.pay_demo.user.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.nrz.pay_demo.user.dto.KakaoJSONObject;
import me.nrz.pay_demo.user.dto.TossJSONObject;
import me.nrz.pay_demo.user.dto.request.KaKaoPaymentRequest;
import me.nrz.pay_demo.user.dto.request.TossPaymentRequest;
import me.nrz.pay_demo.user.dto.request.RegisterUserRequest;
import me.nrz.pay_demo.user.dto.response.UserListResponse;
import me.nrz.pay_demo.user.dto.response.UserResponse;
import me.nrz.pay_demo.user.entity.User;
import me.nrz.pay_demo.user.repository.UserQueryDslRepository;
import me.nrz.pay_demo.user.repository.UserRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserQueryDslRepository userQueryDslRepository;
    private final UserRepository userRepository;

    @Value("${url.toss}")
    private String tossUrl;
    @Value("${url.kakao}")
    private String kakaoUrl;
    @Value("${url.kakao-ready}")
    private String kakaoReadyUrl;

    @Value("${secret.toss}")
    private String tossSecret;
    @Value("${secret.kakao}")
    private String kakaoSecret;

    @Override
    @Transactional
    public UserResponse getUserInformation(int userId) {
        User foundedUser = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        foundedUser.increaseViewCount();
        return new UserResponse(foundedUser);
    }

    @Override
    public UserListResponse getUserList(String sortBy, String direction, int page, int size) {
        List<String> allowedSortFields = List.of("name", "viewCount", "createdAt");
        if (!allowedSortFields.contains(sortBy)) throw new IllegalArgumentException("Invalid sort field");

        return new UserListResponse(userQueryDslRepository.findUsersOrderByCondition(sortBy, direction, page, size));
    }

    @Override
    @Transactional
    public void registerUser(RegisterUserRequest registerUserRequest) {
        userRepository.save(new User(registerUserRequest.getName()));
    }

    @Override
    @Transactional
    public JSONObject confirmTossPayment(TossPaymentRequest tossPaymentRequest)
        throws IOException, ParseException {
        User user = userRepository.findById(tossPaymentRequest.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("User not found."));

        String authorizations = getAuthorization("toss");
        URL url = new URL(tossUrl);
        InputStream responseStream = requestPaymentApproval(authorizations, url, new TossJSONObject(tossPaymentRequest));

        JSONParser parser = new JSONParser();
        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONObject result = (JSONObject) parser.parse(reader);
        responseStream.close();

        if(result.get("totalAmount") != null){
            user.addPoint(Integer.parseInt(result.get("totalAmount").toString()));
        }else{
            throw new RuntimeException("Failed to receive a valid response from the external server.");
        }

        return result;
    }

    @Override
    @Transactional
    public JSONObject confirmKakaoPayment(KaKaoPaymentRequest kaKaoPaymentRequest) throws IOException, ParseException {
        User user = userRepository.findById(kaKaoPaymentRequest.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        String authorizations = getAuthorization("kakao");
        URL url = new URL(kakaoUrl);
        InputStream responseStream = requestPaymentApproval(authorizations, url, new KakaoJSONObject(kaKaoPaymentRequest));

        JSONParser parser = new JSONParser();
        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONObject result = (JSONObject) parser.parse(reader);
        responseStream.close();

        if(result.get("amount") != null){
            JSONObject amount = (JSONObject) result.get("amount");
            user.addPoint(Integer.parseInt(amount.get("total").toString()));
        }else{
            throw new RuntimeException("Failed to receive a valid response from the external server.");
        }

        return result;
    }

    private InputStream requestPaymentApproval(String authorizations, URL url, JSONObject jsonObject) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorizations);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            try(OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(jsonObject.toString().getBytes("UTF-8"));
                outputStream.flush();
            }
            int code = connection.getResponseCode();
            return code == 200 ? connection.getInputStream() : connection.getErrorStream();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private String getAuthorization(String provider) {
        if(provider.equals("toss")){

            Base64.Encoder encoder = Base64.getEncoder();
            byte[] encodedBytes = encoder.encode((tossSecret + ":").getBytes(StandardCharsets.UTF_8));
            return "Basic " + new String(encodedBytes);
        } else if (provider.equals("kakao")) {
            return "SECRET_KEY " + kakaoSecret;
        }
        throw new AssertionError("Reached an unreachable code path.");
    }
}
