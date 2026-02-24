# ALBE5_Team9_JavaCLI

## 1. 개요
> 자바 CLI 기반 게시판 서비스로, 게시글 작성·조회·수정·삭제(CRUD) 기능을 제공합니다.<br>Java 객체지향 설계와 로컬 데이터 관리를 통해 CRUD 처리 과정을 학습하는 것이 목표입니다.
<br>

## 2. 역할 분담
- **사용자 관련 기능** <br>
[담당] : 홍가현, 황보혜 <br>
[작업] : 회원가입, 로그인, User Domain/Service/Repository 담당

- **게시판 관련 기능** <br>
[담당] : 문창현, 이건희 <br>
[작업] : 게시글 CRUD, Post Domain/Service/Repository 담당
<br>

## 3. 폴더 구조
```java
.
└── 📂 Team9_JavaCLI/
    └── 📂 src/
        ├── 📦 runner/
        │   ├── BoardConsoleRunner.java      # 메뉴 입출력 
        │   └── BoardApplication.java        # 메인
        ├── 📦 domain/
        │   ├── User.java                    # 회원 엔티티
        │   └── Post.java                    # 게시글 엔티티
        ├── 📦 service/
        │   ├── UserService.java             # 회원가입, 로그인
        │   └── PostService.java             # 게시글 CRUD
        └── 📦 repository/
            ├── UserRepository.java          # User 테이블 접근
            └── PostRepository.java          # Post 테이블 접근
```
<br>

## 4. 기능 정의
| **기능** | **설명** |
| --- | --- |
| **회원가입** | 사용자 아이디와 비밀번호 입력 후 회원 정보 저장 |
| **로그인** | 사용자 아이디와 비밀번호 검증 후 로그인 상태로 변경 |
| **로그아웃** | 로그인 상태 해제 |
| **게시글 작성** | 제목과 내용을 입력 받아 로그인 사용자의 게시글 저장 |
| **게시글 조회** | 모든 게시글 리스트 조회 |
| **게시글 상세 조회** | 게시글 ID 입력 후 단일 게시글 조회 |
| **게시글 수정** | 게시글 아이디 입력 후 제목과 내용을 수정 |
| **게시글 삭제** | 게시글 아이디 입력 후 해당 게시글 삭제 |
<br>

## 5. 설계
### 5.1 클래스 설계
### 5.2 메소드 설계
### 5.3 데이터 설계
<br>

## 6. Git 규칙 

## 7. 실행 예시


