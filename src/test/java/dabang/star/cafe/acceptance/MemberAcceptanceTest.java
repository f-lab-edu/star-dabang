package dabang.star.cafe.acceptance;

import dabang.star.cafe.api.request.MemberLoginRequest;
import dabang.star.cafe.api.request.SignUpRequest;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/clean.sql")
@DisplayName("로그인 관련 시나리오")
class MemberAcceptanceTest {

    public static final String EMAIL = "test@email.com";
    public static final String PASSWORD = "testpassword";
    public static final String NICKNAME = "테스트닉네임";
    public static final String TELEPHONE = "01012345678";
    public static final String BIRTH = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

    @LocalServerPort
    int port;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @DisplayName("로그인에 성공한다")
    @Test
    void testLoginSuccess() {
        // given (회원 등록되어 있음)
        alreadyRegistered(EMAIL, PASSWORD, NICKNAME, TELEPHONE, BIRTH);

        // when (로그인 요청)
        ExtractableResponse<Response> response = loginRequest(EMAIL, PASSWORD);

        // then (로그인 됨)
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("로그인에 실패한다")
    @Test
    void testLoginFail() {
        // when (로그인 요청)
        ExtractableResponse<Response> response = loginRequest(EMAIL, PASSWORD);

        // then (로그인 실패)
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    public static ExtractableResponse<Response> loginRequest(final String email, final String password) {
        return RestAssured
                .given().log().all()
                .contentType(APPLICATION_JSON_VALUE)
                .body(new MemberLoginRequest(email, password), ObjectMapperType.JACKSON_2)
                .when()
                .post("/members/login")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> signupRequest(
            final String email, final String password, final String nickname, final String telephone, final String birth) {

        return RestAssured
                .given().log().all()
                .contentType(APPLICATION_JSON_VALUE)
                .body(new SignUpRequest(email, password, nickname, telephone, birth), ObjectMapperType.JACKSON_2)
                .when()
                .post("/members")
                .then().log().all()
                .extract();
    }

    public static void alreadyRegistered(
            final String email, final String password, final String nickname, final String telephone, final String birth) {

        assertThat(signupRequest(email, password, nickname, telephone, birth).statusCode())
                .isEqualTo(HttpStatus.CREATED.value());
    }


}
