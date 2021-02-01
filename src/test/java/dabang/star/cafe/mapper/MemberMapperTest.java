package dabang.star.cafe.mapper;

import dabang.star.cafe.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @DisplayName("멤버 저장 및 이메일로 멤버 찾기")
    @Test
    public void saveAndFindEmailTest() {

        Member member = Member.builder()
                .email("test@naver.com")
                .passwd("1234")
                .telephone("01012345678")
                .nickname("testNickname")
                .birth("")
                .build();

        memberMapper.save(member);
        Optional<String> findEmail = memberMapper.findByEmail(member.getEmail());

        assertThat(member.getEmail()).isEqualTo(findEmail.get());
    }
}