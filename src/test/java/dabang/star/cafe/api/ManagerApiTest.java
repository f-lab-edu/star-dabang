package dabang.star.cafe.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dabang.star.cafe.application.exception.NoAuthenticationException;
import dabang.star.cafe.api.request.ManagerLoginRequest;
import dabang.star.cafe.domain.authentication.ManagerLoginService;
import dabang.star.cafe.application.data.ManagerData;
import dabang.star.cafe.domain.manager.Role;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@WebMvcTest(ManagerApi.class)
class ManagerApiTest {

    private final String MANAGER_NAME = "name";
    private final String MANAGER_PASSWORD = "password";
    private final Long MANAGER_ID = 1L;
    private final Integer OFFICE_ID = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManagerLoginService managerLoginService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @DisplayName("관리자 로그인시 정상적으로 로그인을 완료한다면 상태코드 200과 MemberData를 반환한다.")
    @Test
    void successfulManagerLoginTest() throws Exception {
        ManagerLoginRequest managerLoginRequest = new ManagerLoginRequest(MANAGER_NAME, MANAGER_PASSWORD);
        ManagerData managerData = new ManagerData(MANAGER_ID, OFFICE_ID, "name", Role.MANAGER);

        when(managerLoginService.login(managerLoginRequest.getName(), managerLoginRequest.getPassword()))
                .thenReturn(managerData);

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(objectMapper.writeValueAsString(managerLoginRequest))
                .when()
                .post("/managers/login")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("officeId", equalTo(1))
                .body("name", equalTo("name"))
                .body("role", equalTo("MANAGER"));

        verify(managerLoginService).login(any(String.class), any(String.class));
    }

    @DisplayName("관리자 로그인시 존재하지 않는 이름으로 조회시에 상태코드 401과 ErrorResponse를 반환한다")
    @Test
    void failedManagerLoginTest() throws Exception {
        ManagerLoginRequest managerLoginRequest = new ManagerLoginRequest("duplicateName", MANAGER_PASSWORD);

        when(managerLoginService.login(managerLoginRequest.getName(), managerLoginRequest.getPassword()))
                .thenThrow(new NoAuthenticationException("no authenticated"));

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(objectMapper.writeValueAsString(managerLoginRequest))
                .when()
                .post("/managers/login")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .body("message", equalTo("no authenticated"))
                .body("status", equalTo(HttpStatus.UNAUTHORIZED.value()));

        verify(managerLoginService).login(any(String.class), any(String.class));
    }

    @DisplayName("관리자 로그아웃 정상적으로 완료시 상태코드 200을 반환한다")
    @Test
    void successfulLogoutTest() {

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/managers/logout")
                .then()
                .statusCode(HttpStatus.OK.value());

        verify(managerLoginService).logout();
    }

    @DisplayName("관리자 로그인시 패스워드를 입력하지 않으면 상태코드 422와 ErrorResponse를 반환한다")
    @Test
    void notValidPasswordLoginTest() throws Exception {
        ManagerLoginRequest managerLoginRequest = new ManagerLoginRequest(MANAGER_NAME, "");

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(objectMapper.writeValueAsString(managerLoginRequest))
                .when()
                .post("/managers/login")
                .then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("blank password"))
                .body("status", equalTo(HttpStatus.UNPROCESSABLE_ENTITY.value()));
    }

    @DisplayName("관리자 로그인시 이름을 입력하지 않으면 상태코드 422와 ErrorResponse를 반환한다")
    @Test
    void notValidNameLoginTest() throws Exception {
        ManagerLoginRequest managerLoginRequest = new ManagerLoginRequest("", MANAGER_PASSWORD);

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(objectMapper.writeValueAsString(managerLoginRequest))
                .when()
                .post("/managers/login")
                .then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("blank name"))
                .body("status", equalTo(HttpStatus.UNPROCESSABLE_ENTITY.value()));
    }

}