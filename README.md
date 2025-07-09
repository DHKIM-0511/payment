# Payment Demo Server
이 프로젝트는 결제 애플리케이션의 백엔드 서버 코드입니다. 클라이언트 코드가 완성되었다는 가정하에 개발되었으며, 결제 승인 및 관련 로직을 처리합니다.

## 🚀 프로젝트 개요
이 서버는 토스페이먼츠(Toss Payments)와 카카오페이(Kakao Pay)를 통한 결제 흐름을 처리하도록 설계되었습니다. 클라이언트에서 시작된 결제 요청을 받아 각 결제 서비스 제공업체와 통신하여 결제를 승인하고, 결과를 관리하는 역할을 수행합니다.

## 🛠️ 기술 스택
Spring Boot: 웹 애플리케이션 개발을 위한 프레임워크

Java 17: 프로젝트의 주요 개발 언어 및 런타임

Gradle: 프로젝트 빌드 자동화 도구

MySQL: 데이터베이스 (Docker Compose를 통해 관리)

JPA, Querydsl: ORM (Object-Relational Mapping)

### 오픈소스 라이브러리
com.googlecode.json-simple:json-simple:1.1.1: JSON 데이터를 파싱하고 생성하는 데 사용됩니다.

## 📦 시작하기
프로젝트를 로컬 환경에서 실행하기 위한 지침입니다.

### 📋 사전 준비 사항
Docker Desktop: MySQL 데이터베이스 및 서버 애플리케이션을 컨테이너로 실행하기 위해 필요합니다.

Java 17 Development Kit (JDK 17): 애플리케이션 빌드를 위해 필요합니다.

### ⚙️ 설치 및 실행
1. 프로젝트 클론
2. 빌드 실행: ```./gradlew build -x test```
3. docker compose 실행
   - ```cd docker```
   - ```docker-compose up```

애플리케이션 확인:
서버는 기본적으로 http://localhost:8080 포트에서 실행됩니다.