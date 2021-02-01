package dabang.star.cafe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dabang.star.cafe.domain.Member;
import dabang.star.cafe.dto.MemberRequestDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberControllerTest {

    private MemberRequestDto memberRequestDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void before() {

        memberRequestDto = new MemberRequestDto(
                "test@naver.com",
                "1234",
                "testNickName",
                "01055555555",
                "990909");
    }

    @DisplayName("정상적으로 회원가입을 한다")
    @Test
    @Order(1)
    public void signUpMemberTest() throws Exception {

        Member member = new Member(memberRequestDto);
        String value = objectMapper.writeValueAsString(member);

        mockMvc.perform(post("/members")
                .content(value)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @DisplayName("회원가입시 이메일 중복을 체크한다")
    @Test
    @Order(2)
    public void duplicatedEmailCheckTest() throws Exception {

        Member member = new Member(memberRequestDto);
        String value = objectMapper.writeValueAsString(member);

        mockMvc.perform(post("/members")
                .content(value)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isConflict());
    }

    @DisplayName("회원가입시 이메일 공백을 체크한다")
    @Test
    public void validatedEmptyEmail() throws Exception {

        memberRequestDto.setEmail("");
        Member member = new Member(memberRequestDto);
        String json = objectMapper.writeValueAsString(member);

        mockMvc.perform(post("/members")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("회원가입시 이메일에 형식을 체크한다")
    @Test
    public void validatedNotEmail() throws Exception {

        memberRequestDto.setEmail("test@");
        Member member = new Member(memberRequestDto);
        String json = objectMapper.writeValueAsString(member);

        mockMvc.perform(post("/members")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("회원가입시 패스워드 공백을 체크한다")
    @Test
    public void validatedEmptyPasswd() throws Exception {

        memberRequestDto.setPasswd("");
        Member member = new Member(memberRequestDto);
        String json = objectMapper.writeValueAsString(member);

        mockMvc.perform(post("/members")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("회원가입시 닉네임 공백을 체크한다")
    @Test
    public void validatedEmptyNickname() throws Exception {

        memberRequestDto.setNickname("");
        Member member = new Member(memberRequestDto);
        String json = objectMapper.writeValueAsString(member);

        mockMvc.perform(post("/members")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("회원가입시 핸드폰번호 공백을 체크한다")
    @Test
    public void validatedEmptyPhone() throws Exception {

        memberRequestDto.setTelephone("");
        Member member = new Member(memberRequestDto);
        String json = objectMapper.writeValueAsString(member);

        mockMvc.perform(post("/members")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("회원가입시 생일 공백을 체크한다")
    @Test
    public void validatedEmptyBirth() throws Exception {

        memberRequestDto.setBirth("");
        Member member = new Member(memberRequestDto);
        String json = objectMapper.writeValueAsString(member);

        mockMvc.perform(post("/members")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
}