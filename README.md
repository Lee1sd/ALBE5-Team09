<h1 align="center">Team 구속성장💫 - JAVA CLI</h1>

<h2 align="center">1. 소개</h2>
<p align="center">자바 CLI기반 게시판 서비스로, 사용자의 회원가입 및 로그인을 제공합니다. <br />
로그인 수행 후, 제공되는 게시판에서 게시글을 작성·조회·수정·삭제(CRUD)의 작업을 수행할 수 있습니다. <br />
사용자 정보 및 게시글 데이터는 각각의 Repository에 저장됩니다. <br />
<br />
본 프로젝트는 Java 객체지향 설계와 로컬 데이터 관리를 통한 CRUD 처리 과정을 학습하는 것이 목표입니다.
</p>

<br />

<h2 align="center">2. 담당 기능 및 담당자</h2>
<h3 align="center">👤 사용자 관련 (User)</h3>
<table align="center" style="text-align:center";>
  <tr>
    <th>홍가현</th>
    <th>황보혜</th>
  </tr>
  <tr>
    <td>
      <img src="https://avatars.githubusercontent.com/u/71168366?v=4" alt="홍가현" width="300">
    </td>
    <td>
      <img src="https://avatars.githubusercontent.com/u/252306408?v=4" alt="황보혜" width="300">
    </td>
  </tr>
  <tr>
    <td>
      <a href="https://github.com/devken65">@devken65</a>
    </td>
    <td>
      <a href="https://github.com/hwangbohye03">@hwangbohye03</a>
    </td>
  </tr>
  <tr>
    <td>
      사용자 데이터 테이블 구축 <br />
      사용자 데이터 관련 메소드 및 로직 정의 <br />
      회원가입 기능
    </td>
    <td>
      러너 로직 구축 <br />
      로그인 & 로그아웃 기능
    </td>
  </tr>
</table>

<h3 align="center">📝 게시판 관련 (Post)</h3>
<table align="center" style="text-align:center";>
  <tr>
    <th>문창현</th>
    <th>이건희</th>
  </tr>
  <tr>
    <td>
      <img src="https://avatars.githubusercontent.com/u/67505983?v=4" alt="문창현" width="300">
    </td>
    <td>
      <img src="https://avatars.githubusercontent.com/u/170064304?v=4" alt="이건희" width="300">
    </td>
  </tr>
  <tr>
    <td>
      <a href="https://github.com/changhyunmoon">@changhyunmoon</a>
    </td>
    <td>
      <a href="https://github.com/Lee1sd">@Lee1sd</a>
    </td>
  </tr>
  <tr>
    <td>
      게시글 수정, 삭제 기능
    </td>
    <td>
      로그인 여부 확인 기능 <br />
      게시글 생성, 전체 조회, 상세 조회 기능
    </td>
  </tr>
</table>

<br />

<h2 align="center">3. 기능 정의</h2>
<h3 align="center">👤 USER</h3>
<table align="center" style="text-align: center;">
  <tr>
    <th>기능</th>
    <th>설명</th>
  </tr>
  <tr>
    <td>
      <strong>회원가입</strong>
    </td>
    <td>사용자 아이디와 비밀번호 입력 후 회원 정보 저장</td>
  </tr>
  <tr>
    <td>
      <strong>로그인</strong>
    </td>
    <td>사용자 아이디와 비밀번호 검증 후 로그인 상태로 변경</td>
  </tr>
  <tr>
    <td>
      <strong>로그아웃</strong>
    </td>
    <td>로그인 상태 해제</td>
  </tr>
</table>

<h3 align="center">📝 POST</h3>
<table align="center" style="text-align: center;">
  <tr>
    <th>기능</th>
    <th>설명</th>
  </tr>
  <tr>
    <td>
      <strong>게시글 작성</strong>
    </td>
    <td>제목과 내용을 입력 받아 로그인 사용자의 게시글 저장</td>
  </tr>
  <tr>
    <td>
      <strong>게시글 조회</strong>
    </td>
    <td>모든 게시글 리스트 조회</td>
  </tr>
  <tr>
    <td>
      <strong>게시글 상세 조회</strong>
    </td>
    <td>게시글 ID 입력 후 단일 게시글 조회</td>
  </tr>
  <tr>
    <td>
      <strong>게시글 수정</strong>
    </td>
    <td>게시글 ID 입력 후 제목과 내용을 수정</td>
  </tr>
  <tr>
    <td>
      <strong>게시글 삭제</strong>
    </td>
    <td>게시글 ID 입력 후 해당 게시글 삭제</td>
  </tr>
</table>

<br />

<h2 align="center">4. 폴더 구조</h2>

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
            ├── UserRepository.java          # User 테이블
            └── PostRepository.java          # Post 테이블
```
<br />
<h2 align="center">5. 설계</h2>
<h3 align="center">5-1. Runner</h3>
<div align="center">
  <img src="./github_src/runner.png">
</div>

<h3 align="center">5-2. Domain</h3>
<div align="center">
  <img src="./github_src/domain.png">
</div>

<h3 align="center">5-3. Service</h3>
<div align="center">
  <img src="./github_src/service.png">
</div>

<h3 align="center">5-4. Repository</h3>
<div align="center">
  <img src="./github_src/repository.png">
</div>
<br />
<h2 align="center">6. Git 규칙</h2>

```java
git commit -m "<Type>: <Message>";
```
<table align="center" style="text-align: center;">
  <tr>
    <th>타입</th>
    <th>의미</th>
    <th>예시 상황</th>
  </tr>
  <tr>
    <td>feat</td>
    <td>새로운 기능 추가</td>
    <td>게시글 검색 기능 추가</td>
  </tr>
  <tr>
    <td>fix</td>
    <td>버그 수정</td>
    <td>로그인 오류 수정</td>
  </tr>
  <tr>
    <td>docs</td>
    <td>문서 수정 (README 등)</td>
    <td>README 업데이트</td>
  </tr>
  <tr>
    <td>style</td>
    <td>코드 스타일 변경 (기능 변화 없음)</td>
    <td>들여쓰기 수정, 세미콜론 정리</td>
  </tr>
  <tr>
    <td>refactor</td>
    <td>기능 변화 없이 코드 개선</td>
    <td>메소드 분리, 변수명 개선</td>
  </tr>
  <tr>
    <td>test</td>
    <td>테스트 코드 추가/수정</td>
    <td>회원가입 테스트 코드 작성</td>
  </tr>
  <tr>
    <td>comment</td>
    <td>주석만 수정</td>
    <td>메소드 설명 주석 추가</td>
  </tr>
  <tr>
    <td>rename</td>
    <td>파일/폴더 이름 변경</td>
    <td>UserService → MemberService</td>
  </tr>
  <tr>
    <td>remove</td>
    <td>파일 삭제</td>
    <td>사용 안 하는 클래스 삭제</td>
  </tr>
</table>
<br />
<h2 align="center">7. 실행 예시</h2>
이곳에 영상 첨부 예정
