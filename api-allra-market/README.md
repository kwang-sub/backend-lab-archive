# [올라 백엔드 지원자 최광섭]

## 올라 마켓


## 개발 환경

- Spring Boot 3.4.2
- Spring Security(JWT)
- Java 17 
- MariaDB 10.11.10
- Liquibase


## 과제 환경 설정

- 데이터베이스 테이블은 application.yml에 데이터 소스를 설정 해주시면 Liquibase를 통해 자동 생성됩니다. 
- 상품 샘플 데이터는 해당 [csv 파일](https://drive.google.com/file/d/1_qm4TYZU5E4GT-D8iC--eXOg87IOxRBs/view?usp=drive_link)을 통해 product 테이블에 추가 해주시면 됩니다.
- API 규격은 [포스트맨](https://www.postman.com/grey-flare-293196/workspace/api-allra-market/collection/22757500-d9119f87-1b1b-4970-98e7-3257f666fc56?action=share&source=copy-link&creator=22757500&active-environment=67552c52-2e3a-4272-be4d-1c1c159f0487)을 통해 확인 가능하며 사용자 등록 후 로그인을 하면 헤더에 자동으로 토큰 설정이 되어 다른 API 확인 가능합니다.<br>또한 우측 상단 Documentation 탭을 확인하시면 상세 규격 확인 가능합니다.
- 모의 API URL은 application.yml 파일에 app.url.payment를 통해 사용하고 있으며 별도 설정 부탁드립니다


## 기능 요구사항별 API

- 상품 
  - [조회](https://www.postman.com/grey-flare-293196/workspace/api-allra-market/request/22757500-0eecaea8-9ed7-403f-9388-80b3d4a610d1?action=share&source=copy-link&creator=22757500&active-environment=67552c52-2e3a-4272-be4d-1c1c159f0487)

- 장바구니
  - [추가](https://www.postman.com/grey-flare-293196/workspace/api-allra-market/request/22757500-da43cfaa-3e4b-4772-a4e1-281710ff3811?action=share&source=copy-link&creator=22757500&active-environment=67552c52-2e3a-4272-be4d-1c1c159f0487)
  - [수정](https://www.postman.com/grey-flare-293196/workspace/api-allra-market/request/22757500-356e21d6-9d8f-4b50-bd85-a3b24d89c1ba?action=share&source=copy-link&creator=22757500&active-environment=67552c52-2e3a-4272-be4d-1c1c159f0487)
  - [삭제](https://www.postman.com/grey-flare-293196/workspace/api-allra-market/request/22757500-cbeb95fd-09f9-49ae-a615-27e07793777e?action=share&source=copy-link&creator=22757500&active-environment=67552c52-2e3a-4272-be4d-1c1c159f0487)

- 주문
  - [처리](https://www.postman.com/grey-flare-293196/workspace/api-allra-market/request/22757500-ef442eaf-7cd1-4c6e-af3b-af61257b1f94?action=share&source=copy-link&creator=22757500&active-environment=67552c52-2e3a-4272-be4d-1c1c159f0487)
  - [완료 주문 내역 조회](https://www.postman.com/grey-flare-293196/workspace/api-allra-market/request/22757500-8c8fa947-61f5-449e-9f25-12b1ad808155?action=share&source=copy-link&creator=22757500&active-environment=67552c52-2e3a-4272-be4d-1c1c159f0487)


## 과제 진행시 주의한 점

- 요구사항에 맞는 기능을 개발하려 했습니다.
- 테스트 코드를 통한 안정적인 시스템을 개발하려 했습니다.
- 재고 관리 동시성 문제를 주의 했습니다.


추가 요구사항 및 문의사항은  [choikwangsub@gmail.com](mailto:choikwangsub@gmail.com)로 부탁드립니다.