package dabang.star.cafe.api;

import dabang.star.cafe.application.UserApplicationService;
import dabang.star.cafe.application.data.UserData;
import dabang.star.cafe.domain.user.User;
import dabang.star.cafe.domain.user.UserRepository;
import dabang.star.cafe.infrastructure.mybatis.readservice.UserReadService;
import dabang.star.cafe.infrastructure.service.SHA256EncryptService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@WebMvcTest(UsersApi.class)
@Import({UserApplicationService.class, SHA256EncryptService.class})
class UsersApiTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserReadService userReadService;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    @DisplayName("회원가입 요청에 성공합니다.")
    void createUserSuccess() throws Exception {
        String email = "test@test.com";

        User user = new User(email, "test", "test", "01000000000", LocalDate.now());
        UserData userData = UserData.builder()
                .id(user.getId())
                .email(email)
                .nickname(user.getNickname())
                .tel(user.getTel())
                .birthday(user.getBirthday())
                .build();
        when(userReadService.findById(any())).thenReturn(userData);
        when(userRepository.findByEmail(eq(email))).thenReturn(Optional.empty());

        Map<String, Object> parameter = prepareRegisterParameter(email);
        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(parameter)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("user.email", equalTo(email))
                .body("user.nickname", equalTo("test"))
                .body("user.tel", equalTo("01000000000"))
                .body("user.birthday", equalTo(LocalDate.now().toString()));
        verify(userRepository).save(any());
    }

    @Test
    @DisplayName("중복된 이메일로 가입 요청시 'duplicated email' 에러를 보여줍니다.")
    void showErrorForDuplicateEmail() throws Exception {
        String email = "test@test.com";

        when(userRepository.findByEmail(eq(email))).thenReturn(Optional.of(new User(
                email, "test", "test", "01000000000", LocalDate.now()
        )));
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        Map<String, Object> parameter = prepareRegisterParameter(email);
        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(parameter)
                .when()
                .post("/users")
                .then()
                .statusCode(422)
                .body("errors.email[0]", equalTo("duplicated email"));
    }

    @Test
    @DisplayName("이메일 형식이 아닌 회원가입 요청이면 'should be an email' 에러를 보여줍니다.")
    void showErrorForInvalidEmail() throws Exception {
        String email = "test.com";

        Map<String, Object> parameter = prepareRegisterParameter(email);
        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(parameter)
                .when()
                .post("/users")
                .then()
                .statusCode(422)
                .body("errors.email[0]", equalTo("should be an email"));
    }

    private HashMap<String, Object> prepareRegisterParameter(final String email) {
        return new HashMap<String, Object>() {{
            put("user", new HashMap<String, Object>() {{
                put("email", email);
                put("password", "test");
                put("nickname", "test");
                put("tel", "01000000000");
                put("birthday", LocalDate.now().toString());
            }});
        }};
    }
}