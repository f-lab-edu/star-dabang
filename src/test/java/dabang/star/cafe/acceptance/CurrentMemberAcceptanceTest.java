package dabang.star.cafe.acceptance;

import dabang.star.cafe.api.request.CurrentMemberRequest;
import dabang.star.cafe.application.command.MemberUpdateCommand;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/clean.sql")
@DisplayName("마이 페이지 관련 시나리오")
public class CurrentMemberAcceptanceTest {

    public static final String NEW_PASSWORD = "newpassword";
    public static final String NEW_NICKNAME = "새로운닉네임";
    public static final String NEW_TELEPHONE = "01087654321";

    @LocalServerPort
    int port;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @DisplayName("마이 페이지 접속에 성공한다.")
    @Test
    void testAccessMyPage() {
        // given (회원 등록되어 있고 로그인 되어 있음)
        ExtractableResponse<Response> loginResponse = alreadyLogin();

        // when (패스워드 검증 요청)
        ExtractableResponse<Response> response = accessMyPage(loginResponse, MemberAcceptanceTest.PASSWORD);

        // then (마이페이지 접속됨)
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("마이 페이지에 접속해 나의 정보를 수정한다.")
    @Test
    void testMyPageUpdate() {

        // given (회원 등록 되어 있고 로그인 되어 있음)
        ExtractableResponse<Response> loginResponse = alreadyLogin();

        // when (패스워드 검증 요청)
        ExtractableResponse<Response> response = accessMyPage(loginResponse, MemberAcceptanceTest.PASSWORD);

        // then (마이페이지 접속됨)
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        // when (내 정보 수정 요청)
        ExtractableResponse<Response> updateResponse
                = memberUpdateRequest(loginResponse, NEW_PASSWORD, NEW_NICKNAME, NEW_TELEPHONE);

        // then (내 정보 수정됨)
        assertThat(updateResponse.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @DisplayName("마이 페이지에 접속해 탈퇴한다.")
    @Test
    void testMyPageDelete() {

        // given (회원 등록 되어 있고 로그인 되어 있음)
        ExtractableResponse<Response> loginResponse = alreadyLogin();

        // when (패스워드 검증 요청)
        ExtractableResponse<Response> response = accessMyPage(loginResponse, MemberAcceptanceTest.PASSWORD);

        // then (마이페이지 접속됨)
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        // when (탈퇴 요청)
        ExtractableResponse<Response> deleteResponse = memberDeleteRequest(loginResponse);

        // then (탈퇴됨)
        assertThat(deleteResponse.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    public static ExtractableResponse<Response> memberDeleteRequest(ExtractableResponse<Response> loginResponse) {

        return RestAssured
                .given().log().all()
                .contentType(APPLICATION_JSON_VALUE)
                .cookie("SESSION", loginResponse.cookie("SESSION"))
                .when()
                .delete("/members/my-info")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> memberUpdateRequest(
            ExtractableResponse<Response> loginResponse, String newPassword, String newNickname, String newTelephone) {

        return RestAssured
                .given().log().all()
                .contentType(APPLICATION_JSON_VALUE)
                .cookie("SESSION", loginResponse.cookie("SESSION"))
                .body(new MemberUpdateCommand(NEW_PASSWORD, NEW_NICKNAME, NEW_TELEPHONE), ObjectMapperType.JACKSON_2)
                .when()
                .patch("/members/my-info")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> accessMyPage(
            ExtractableResponse<Response> loginResponse, String password) {

        return RestAssured
                .given().log().all()
                .contentType(APPLICATION_JSON_VALUE)
                .cookie("SESSION", loginResponse.cookie("SESSION"))
                .body(new CurrentMemberRequest(password), ObjectMapperType.JACKSON_2)
                .when()
                .post("/members/my-info")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> alreadyLogin() {

        MemberAcceptanceTest.alreadyRegistered(MemberAcceptanceTest.EMAIL,
                MemberAcceptanceTest.PASSWORD,
                MemberAcceptanceTest.NICKNAME,
                MemberAcceptanceTest.TELEPHONE,
                MemberAcceptanceTest.BIRTH
        );
        return MemberAcceptanceTest.loginRequest(MemberAcceptanceTest.EMAIL, MemberAcceptanceTest.PASSWORD);
    }

}
