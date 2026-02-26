# ALBE5_Team9_JavaCLI

## 1. 개요
> 자바 CLI 기반 게시판 서비스로, 게시글 작성·조회·수정·삭제(CRUD) 기능을 제공합니다.<br>Java 객체지향 설계와 로컬 데이터 관리를 통해 CRUD 처리 과정을 학습하는 것이 목표입니다.
<br>

## 2. 담당 기능 및 담당자
### 사용자 관련 (User)

|                            홍가현                            |                            황보혜                            |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
| ![홍가현](https://avatars.githubusercontent.com/u/71168366?v=4) | ![황보혜](https://avatars.githubusercontent.com/u/252306408?v=4) |
|            [@devken65](https://github.com/devken65)            |          [@hwangbohye03](https://github.com/hwangbohye03)          |

  [작업] : 회원가입, 로그인, User Domain/Service/Repository 담당

### 게시판 관련 (Post)
|                            문창현                            |                            이건희                            |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
| ![문창현](https://avatars.githubusercontent.com/u/67505983?v=4) | ![이건희](https://avatars.githubusercontent.com/u/170064304?v=4) |
|            [@changhyunmoon](https://github.com/changhyunmoon)            |          [@Lee1sd](https://github.com/Lee1sd)          |

  [작업] : 게시글 CRUD, Post Domain/Service/Repository 담당
<br>

## 3. 폴더 구조
```java
.
└── 📂 Team9_JavaCLI/
    └── 📂 src/
        ├── 📦 runner/
        │   ├── BoardApplication.java        # 메인 (앱 실행)
        │   ├── BoardConsoleRunner.java      # 메뉴 입출력 
        │   └── BoardView.java               # 게시글 조회
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

<h3>USER</h3>

| **기능** | **설명** |
| --- | --- |
| **회원가입** | 사용자 아이디와 비밀번호 입력 후 회원 정보 저장 |
| **로그인** | 사용자 아이디와 비밀번호 검증 후 로그인 상태로 변경 |
| **로그아웃** | 로그인 상태 해제 |

<h3>POST</h3>

| **기능**             | **설명**                                                     |
| -------------------- | ------------------------------------------------------------ |
| **게시글 작성**      | 제목과 내용을 입력 받아 로그인 사용자의 게시글 저장          |
| **게시글 조회**      | 모든 게시글 리스트 조회                                      |
| **게시글 상세 조회** | 게시글 ID 입력 후 단일 게시글 조회                           |
| **게시글 수정**      | 게시글 아이디 입력 후 제목과 내용을 수정제목과 내용을 입력 받아 로그인 사용자의 게시글 저장 |
| **게시글 삭제**      | 게시글 아이디 입력 후 해당 게시글 삭제모든 게시글 리스트 조회 |

## 5. 설계

### 5.1 Runner
<div align="center">
  <img src="./github_src/runner.png">
</div>
### 5.2 Domain
<div align="center">
  <img src="./github_src/domain.png">
</div>
### 5.3 Service
<div align="center">
  <img src="./github_src/service.png">
</div>
### 5.4 Repository
<div align="center">
  <img src="./github_src/repository.png">
</div>
<br>

## 6. Git 규칙 
```java
git commit -m "<Type>: <Message>";
```
| 타입 | 의미 | 예시 상황 |
| --- | --- | --- |
| `feat` | 새로운 기능 추가 | 게시글 검색 기능 추가 |
| `fix` | 버그 수정 | 로그인 오류 수정 |
| `docs` | 문서 수정 (README 등) | README 업데이트 |
| `style` | 코드 스타일 변경 (기능 변화 없음) | 들여쓰기 수정, 세미콜론 정리 |
| `refactor` | 기능 변화 없이 코드 개선 | 메소드 분리, 변수명 개선 |
| `test` | 테스트 코드 추가/수정 | 회원가입 테스트 코드 작성 |
| `comment` | 주석만 수정 | 메소드 설명 주석 추가 |
| `rename` | 파일/폴더 이름 변경 | UserService → MemberService |
| `remove` | 파일 삭제 | 사용 안 하는 클래스 삭제 |
## 7. 실행 예시
이곳에 영상 첨부 예정
