## Spring MVC Security Project
### 2020-04-08


### 개요
* Spring MVC 기반 Security Coding
* Security 기능을 포함한 WebSocket Coding
* React 기반 Front-End 기능 Coding


* JDBC, MyBatis 연동
* MySQL DB 연동
* Transaction 설정
* 회원가입 E-mail 인증
* 비밀번호 분실 E-mail 인증 후 재설정



### Spring Security Dependency

* spring security 설정

* spring-security-core
* spring-security-web
* spring-security-config
* spring-security-taglibs

* jasypt
* jasypt-spring31

### DB Dependency(MySQL, mybatis 연동)

* mysql-connector-java
- MySQL과 java를 연결해줄 때 사용하는 DB Driver
* spring-jdbc
- Java(Spring)와 DB Driver 사이에서 명령어나 데이터를 변환 시켜주는 보조 클래스
* mybatis
- spring-jdbc와 DB Driver 사이에서 Mybatis Mapper 등으로 작성된 SQL을 변환하고 데이터를 VO에 쉽게 매핑시키는 용도의 Class
* mybatis spring
- spring-jdbc와 mybatis 엔진 사이에서 서로 맞지 않는 부분을 잘 조정하여 버전에 관계없이 연동이 쉽도록 만들어주는 보조 class
* commons-dbcp2
- jdbc와 driver 사이에서 DB Connection pool을 만들고 Connection, DisConnection을 수행해주는 보조 클래스


### Security와 관련 용어

#### 접근 주체(principal)
* 보호된 대상에 접근하는 유저(User)

#### 인증(Authenticate)
* 접근하는 유저가 누구인가 확인(로그인 절차)

#### 인가(Authorize)
* 접근한 유저가 어떤 서비스, 어떤 페이지에 접근할 수 있는 권한이 있는가 검사

#### 권한(Role)
* 인증(Authenticate)된 주체가 어떤 페이지, 기능, 서비스에 접근할 수 있는 권한이 있다는 것을 보증, 증명

#### 무결성, 보안
* 무결성 파괴: 인가된 사용자에 의해 손상될 수 있는 것들
* 보안 파괴: 인가되지 않은 사용자에 의해 손상될 수 있는 것들

## Spring Security
* Filter를 사용하여 접근하는 사용자의 인증절차와 인가를 통해 권한이 있는가를 파악하고 적절한 조치(되돌리기, Redirect, 사용 가능)를 비교적 적은 코드양으로 처리할 수 있도록 만든 framework

* spring security는 세션과 쿠키방식을 병행하여 사용

### 유저가 로그인을 시도하면
1. Authentication Filter에서부터 users table까지 접근하여 사용자 정보를 인증하는 절차를 거침
2. 인증이 되면 user table, user detail table에서 사용자 정보를 fetch(select)하여 session에 저장
3. 일반적인 HttpSession은 서버에 활동 영역 메모리에 session을 저장하는데 비해 Spring Security는 SecurityContextHolder라는 메모리에 저장
4. view로 유저 정보가 담긴 session을 session ID와 함께 응답으로 전달
* JSESSIONID라는 쿠키에 sessionId를 담아서 보내고
5. 이후 유저가 접근을 하면 JSESSIONID에서 쿠키를 추출하여 사용자 인증을 시도(?JSESSIONID=asdsg 이러한 값이 URL뒤에 따라 붙기도 함)
6. JSESSIONID에서 추출한 Session ID가 유효하면 접근 request에게 Authentication을 부착


### Spring-Security와 form 데이터
* Web browser에서 서버로 요청하는 것을 request라고 하며, 요청할 때 사용하는 주소를 URL, URI라고 함
* Web browser에서 서버에 request하는 방식에는 GET, POST, PUT, DELETE가 있고 이중 SpringMVC에서는 GET, POST를 주로 사용
* GET method는 주소창에 URL을 입력하고 Enter를 누르거나, anchor tag를 마우스로 클릭하거나 또는 form tag의 method가 없는 경우  서버로 요청하는 방식
* GET method는 단순히 리스트를 요구하거나 입력 form 화면을 요구하는 용도로 사용됨
* POST method는 입력화면에 값들을 입력한 후 서버로 전송할 때 주로 사용하며 입력화면의
form, input 등의 tag에 값을 저장한 후 서버로 submit을 수행하는 경우
* POST method는 데이터의 양에 관계없이 서버로 전송할 수 있으며 file upload등도 수행할 수 있음
* Spring-Security를 적용한 프로젝트에서는 GET method 방식은 아무런 제약이 없으나
POST method방식은 서버로부터 전달받은 csrf토큰을 데이터들과 함께 보내야만 정상적으로 서버로 보낼 수 있음 
* 그렇기 때문에 POST 방시의 form 코드에는 다음과 같은 코드르 추가해 주어야 함
`<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">`
* 매번 form화면 구현을 하면서 코드를 추가하다보면, 빠뜨리는 경우가 발생할 수 있고 해당 페이지의 데이터를 전송하면 서버는 수신을 거부하고 403 오류를 보냄
* 이러한 불편을 방지하기 위해 Spring form taglibs를 사용하여 form을 작성
<form:form> ... </form:form> 형식의 form화면을 작성하면 Spring form taglib는 자동으로 토큰을 form화면코드에 추가하여 별도의 조치를 취하지 않아도 문제가 발생하지 않도록 만들어줌