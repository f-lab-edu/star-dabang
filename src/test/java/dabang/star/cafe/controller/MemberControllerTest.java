package dabang.star.cafe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dabang.star.cafe.domain.Member;
import dabang.star.cafe.dto.request.SignUpRequestDto;
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

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberControllerTest {

    private SignUpRequestDto signUpRequestDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void before() {
        RestAssuredMockMvc.mockMvc(mockMvc);

        signUpRequestDto = new SignUpRequestDto(
                "test@naver.com",
                "1234",
                "testNickName",
                "01055555555",
                "19960909");
    }

    @DisplayName("회원가입시 정상적으로 가입이 완료되면 상태코드 200을 반환한다")
    @Test
    @Order(1)
    public void signUpMemberTest() throws Exception {

        Member member = new Member(signUpRequestDto);
        String value = objectMapper.writeValueAsString(member);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("회원가입시 중복된 이메일 입력하면 상태코드 409를 반환한다")
    @Test
    @Order(2)
    public void duplicatedEmailCheckTest() throws Exception {

        Member member = new Member(signUpRequestDto);
        String value = objectMapper.writeValueAsString(member);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .body(equalTo("duplicated Email"));
    }

    @DisplayName("회원가입시 이메일을 입력하지 않으면 상태코드 400을 반환한다")
    @Test
    public void validatedEmptyEmail() throws Exception {

        signUpRequestDto.setEmail("");
        Member member = new Member(signUpRequestDto);
        String value = objectMapper.writeValueAsString(member);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo("blank email"));
    }

    @DisplayName("회원가입시 잘못된 이메일 형식을 입력하면 상태코드 400을 반환한다")
    @Test
    public void validatedNotEmail() throws Exception {

        signUpRequestDto.setEmail("test@");
        Member member = new Member(signUpRequestDto);
        String value = objectMapper.writeValueAsString(member);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo("not valid email format"));
    }

    @DisplayName("회원가입시 패스워드에 공백을 포함하면 상태코드 400을 반환한다")
    @Test
    public void validatedEmptyPasswd() throws Exception {

        signUpRequestDto.setPassword("1 2 34");
        Member member = new Member(signUpRequestDto);
        String value = objectMapper.writeValueAsString(member);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo("not valid password"));
    }

    @DisplayName("회원가입시 닉네임에 공백을 포함하면 상태코드 400을 반환한다")
    @Test
    public void validatedEmptyNickname() throws Exception {

        signUpRequestDto.setNickname("nick name");
        Member member = new Member(signUpRequestDto);
        String value = objectMapper.writeValueAsString(member);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo("not valid nickname"));
    }

    @DisplayName("회원가입시 핸드폰번호에 공백을 포함하면 상태코드 400을 반환한다")
    @Test
    public void validatedEmptyPhone() throws Exception {

        signUpRequestDto.setTelephone("010 1234 5678");
        Member member = new Member(signUpRequestDto);
        String value = objectMapper.writeValueAsString(member);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo("not valid telephone"));
    }

    @DisplayName("회원가입시 생일에 공백을 포함하면 상태코드 400을 반환한다")
    @Test
    public void validatedEmptyBirth() throws Exception {

        signUpRequestDto.setBirth("2020 09 03");
        Member member = new Member(signUpRequestDto);
        String value = objectMapper.writeValueAsString(member);

        RestAssuredMockMvc
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(value)
                .when()
                .post("/members")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo("not valid birth day"));
    }
}