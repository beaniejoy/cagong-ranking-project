# ☕ Cafe Ranking Service for Cagong people 👨‍💻👩‍💻

Cafe Ranking Service for people who study at cafe (spring boot)

> improved version. 🔗[project-cafe-ranking](https://github.com/hanbinleejoy/project-cafe-ranking)

project-cafe-ranking을 기초로 Spring Boot를 이용한 웹 어플리케이션을 개발 중에 있습니다.

## 🔖 About Tools & Library

- Spring Boot 2.2.6 (Java 8)
- Build: Gradle
- DB: MySQL
- Front : thymeleaf
- IDE: IntelliJ IDEA

## 🔖 REST API (작업 중)

- **Admin-api**  
  - **Cafe Entity**
    - GET /cafes : 모든 카페리스트 조회(SELECT)
    - GET /cafes/{cafeId} : 개별 카페 상세정보 조회
    - POST /cafes : 새로운 카페 생성(CREATE)
    - PATCH /cafes : 기존 개별 카페의 정보 수정(UPDATE)
  - **CafeMenu Entity**
    - GET /cafes/{cafeId}/cafemenus : 카페의 메뉴리스트 조회
    - PATCH /cafes/{cafeId}/cafemenus : 카페의 메뉴리스트 생성, 수정, 삭제 처리
  - **Review Entity**
    - GET /cafes/{cafeId}/reviews : 카페의 리뷰리스트 조회 (admin에서는 조회만 가능)
  - **ScoreSet Entity**
    - GET /cafes/{cafeId}/scoreset : 카페의 종합 score 조회
  
- **User-api**
  - **Cafe Entity**
    - GET /cafes/search : search page 내보낼 카페 리스트 조회(pagination 처리)
    - GET /cafes/{cafeId} : 개별 카페 상세정보 조회  
      (user에서 카페 상세정보에 cafeMenu, review, scoreSet에 대한 정보도 포함)
  - **Review Entity**
    - POST /cafe/{cafeId}/reviews : 새로운 리뷰 작성(CREATE), user에서만 생성 가능
  - **ScoreSet Entity**
    - GET /scoresets: 카테코리 별 점수 top 5 카페 목록 조회
  - **Member**
    - POST /users: 회원 가입
    - POST /session: 로그인(세션 생성)
    
## 🔖 Dev Log
- [Cagong Project 개발 일지](https://github.com/hanbinleejoy/daily-dev-log/tree/master/project/cagong-ranking-project)
