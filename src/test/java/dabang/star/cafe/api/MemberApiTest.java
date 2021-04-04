package dabang.star.cafe.api;

import dabang.star.cafe.application.exception.DuplicatedException;
import dabang.star.cafe.application.MemberService;
import dabang.star.cafe.application.command.SignUpCommand;
import dabang.star.cafe.application.data.MemberData;
import dabang.star.cafe.domain.authentication.MemberLoginService;
import dabang.star.cafe.domain.member.Member;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@WebMvcTest(MemberApi.class)
class MemberApiTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @MockBean
    MemberLoginService memberLoginService;

    @BeforeEach
    void before() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @DisplayName("회원가입이 성공하면 상태코드 201과 멤버 정보를 반환한다")
    @Test
    void testSuccessSignUpRequest() throws Exception {

        SignUpCommand signUpCommand = prepareSignUpCommand(Collections.emptyMap());

        Member member = signUpCommand.toMember("encryptPassword");
        MemberData memberData = MemberData.from(member);

        when(memberService.join(any(SignUpCommand.class))).thenReturn(memberData);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(signUpCommand, ObjectMapperType.JACKSON_2)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("email", equalTo(signUpCommand.getEmail()))
                .body("nickname", equalTo(signUpCommand.getNickname()))
        ;

    }

    @DisplayName("중복된 이메일로 가입 요청시 상태코드 422와 'duplicated email' 을 반환합니다.")
    @Test
    void duplicatedEmailCheckTest() throws Exception {

        SignUpCommand signUpCommand = prepareSignUpCommand(Collections.emptyMap());

        when(memberService.join(any(SignUpCommand.class))).thenThrow(new DuplicatedException("duplicated email"));

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(signUpCommand, ObjectMapperType.JACKSON_2)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .body("message", equalTo("duplicated email"))
                .body("status", equalTo(409))
        ;
    }

    @DisplayName("회원가입시 잘못된 이메일 형식을 입력하면 상태코드 422와 'not valid email format' 을 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"test.com"})
    @NullAndEmptySource
    void testEmailIsValid(String email) throws Exception {

        SignUpCommand signUpCommand = prepareSignUpCommand(new HashMap<>() {{
            put("email", email);
        }});

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(signUpCommand, ObjectMapperType.JACKSON_2)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("not valid email"))
                .body("status", equalTo(422))
        ;
    }

    @DisplayName("회원가입시 잘못된 패스워드 형식을 입력하면 상태코드 422와 'not valid password' 을 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"t", "test password"})
    @NullAndEmptySource
    void testPasswordIsValid(String password) throws Exception {

        SignUpCommand signUpCommand = prepareSignUpCommand(new HashMap<>() {{
            put("password", password);
        }});

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(signUpCommand, ObjectMapperType.JACKSON_2)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("not valid password"))
                .body("status", equalTo(422))
        ;
    }

    @DisplayName("회원가입시 잘못된 닉네임 형식을 입력하면 상태코드 422와 'not valid nickname' 을 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"테 스트", "테스트테스트테스트테스트테스트", "test"})
    @NullAndEmptySource
    void testNicknameIsValid(String nickname) throws Exception {

        SignUpCommand signUpCommand = prepareSignUpCommand(new HashMap<>() {{
            put("nickname", nickname);
        }});

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(signUpCommand, ObjectMapperType.JACKSON_2)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("not valid nickname"))
                .body("status", equalTo(422))
        ;
    }

    @DisplayName("회원가입시 잘못된 핸드폰 번호 형식을 입력하면 상태코드 422와 'not valid telephone' 을 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"010 1234 5678", "010123456789"})
    @NullAndEmptySource
    void testTelephoneIsValid(String telephone) throws Exception {

        SignUpCommand signUpCommand = prepareSignUpCommand(new HashMap<>() {{
            put("telephone", telephone);
        }});

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(signUpCommand, ObjectMapperType.JACKSON_2)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("not valid telephone"))
                .body("status", equalTo(422))
        ;
    }

    @DisplayName("회원가입시 잘못된 생일 형식을 입력하면 상태코드 422와 'not valid birthday' 을 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"1999 12 12", "1999-12-12"})
    @NullAndEmptySource
    void testBirthIsValid(String birthday) throws Exception {

        SignUpCommand signUpCommand = prepareSignUpCommand(new HashMap<>() {{
            put("birth", birthday);
        }});

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(signUpCommand, ObjectMapperType.JACKSON_2)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("not valid birthday"))
                .body("status", equalTo(422))
        ;
    }

    private SignUpCommand prepareSignUpCommand(final Map<String, String> info) {
        String email = info.getOrDefault("email", "test@test.com");
        String password = info.getOrDefault("password", "testpassword");
        String nickname = info.getOrDefault("nickname", "테스트닉");
        String telephone = info.getOrDefault("telephone", "01012345678");
        String birth = info.getOrDefault("birth", LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));

        return new SignUpCommand(email, password, nickname, telephone, birth);
    }

}