package dabang.star.cafe.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dabang.star.cafe.api.response.ErrorResponse;
import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.api.request.SignUpRequest;
import dabang.star.cafe.domain.member.MemberRepository;
import dabang.star.cafe.infrastructure.mapper.read.MemberReadService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberApiTest {

    private SignUpRequest signUpRequest;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void before() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @DisplayName("회원가입시 정상적으로 가입이 완료되면 상태코드 200을 반환한다")
    @Test
    @Order(1)
    public void signUpMemberTest() throws Exception {

        Member member = new Member(new SignUpRequest(
                "test11@naver.com",
                "1234",
                "테스트",
                "01055555555",
                "19960909"));
        String value = objectMapper.writeValueAsString(member);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @DisplayName("회원가입시 중복된 이메일 입력하면 상태코드 409를 반환한다")
    @Test
    @Order(2)
    public void duplicatedEmailCheckTest() throws Exception {

        Member member = new Member(new SignUpRequest(
                "test11@naver.com",
                "1234",
                "테스트",
                "01055555555",
                "19960909"));
        String value = objectMapper.writeValueAsString(member);

        ErrorResponse errorResponse = ErrorResponse.builder().message("duplicated Email").status(HttpStatus.CONFLICT.value()).build();
        String error = objectMapper.writeValueAsString(errorResponse);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .body(equalTo(error));
    }

    @DisplayName("회원가입시 이메일을 입력하지 않으면 상태코드 400을 반환한다")
    @Test
    public void validatedEmptyEmailTest() throws Exception {

        Member member = new Member(new SignUpRequest(
                "",
                "1234",
                "테스트",
                "01055555555",
                "19960909"));
        String value = objectMapper.writeValueAsString(member);

        ErrorResponse errorResponse = ErrorResponse.builder().message("blank email").status(HttpStatus.BAD_REQUEST.value()).build();
        String error = objectMapper.writeValueAsString(errorResponse);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo(error));
    }

    @DisplayName("회원가입시 잘못된 이메일 형식을 입력하면 상태코드 400을 반환한다")
    @Test
    public void validatedNotEmailTest() throws Exception {

        Member member = new Member(new SignUpRequest(
                "test@",
                "1234",
                "테스트",
                "01055555555",
                "19960909"));
        String value = objectMapper.writeValueAsString(member);

        ErrorResponse errorResponse = ErrorResponse.builder().message("not valid email format").status(HttpStatus.BAD_REQUEST.value()).build();
        String error = objectMapper.writeValueAsString(errorResponse);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo(error));
    }

    @DisplayName("회원가입시 패스워드에 공백을 포함하면 상태코드 400을 반환한다")
    @Test
    public void validatedEmptyPasswdTest() throws Exception {

        Member member = new Member(new SignUpRequest(
                "test11@naver.com",
                "12 3 4",
                "테스트",
                "01055555555",
                "19960909"));
        String value = objectMapper.writeValueAsString(member);

        ErrorResponse errorResponse = ErrorResponse.builder().message("not valid password").status(HttpStatus.BAD_REQUEST.value()).build();
        String error = objectMapper.writeValueAsString(errorResponse);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo(error));
    }

    @DisplayName("회원가입시 닉네임에 공백을 포함하면 상태코드 400을 반환한다")
    @Test
    public void validatedEmptyNicknameTest() throws Exception {

        Member member = new Member(new SignUpRequest(
                "test11@naver.com",
                "1234",
                "테 스 트",
                "01055555555",
                "19960909"));
        String value = objectMapper.writeValueAsString(member);

        ErrorResponse errorResponse = ErrorResponse.builder().message("not valid nickname").status(HttpStatus.BAD_REQUEST.value()).build();
        String error = objectMapper.writeValueAsString(errorResponse);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo(error));
    }

    @DisplayName("회원가입시 핸드폰번호에 공백을 포함하면 상태코드 400을 반환한다")
    @Test
    public void validatedEmptyPhoneTest() throws Exception {

        Member member = new Member(new SignUpRequest(
                "test11@naver.com",
                "1234",
                "테스트",
                "010 5555 5555",
                "19960909"));
        String value = objectMapper.writeValueAsString(member);

        ErrorResponse errorResponse = ErrorResponse.builder().message("not valid telephone").status(HttpStatus.BAD_REQUEST.value()).build();
        String error = objectMapper.writeValueAsString(errorResponse);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo(error));
    }

    @DisplayName("회원가입시 생일에 공백을 포함하면 상태코드 400을 반환한다")
    @Test
    public void validatedEmptyBirthTest() throws Exception {

        Member member = new Member(new SignUpRequest(
                "test11@naver.com",
                "1234",
                "테스트",
                "01055555555",
                "1996 09 09"));
        String value = objectMapper.writeValueAsString(member);

        ErrorResponse errorResponse = ErrorResponse.builder().message("not valid birth day").status(HttpStatus.BAD_REQUEST.value()).build();
        String error = objectMapper.writeValueAsString(errorResponse);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo(error));
    }
}