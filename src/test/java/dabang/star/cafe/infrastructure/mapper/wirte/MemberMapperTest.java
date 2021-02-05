package dabang.star.cafe.infrastructure.mapper.wirte;

import dabang.star.cafe.domain.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@MybatisTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    Member member;

    @BeforeEach
    public void before() {
        member = Member.builder()
                .email("testtest@naver.com")
                .password("1023012030")
                .nickname("테스트")
                .telephone("0104324234")
                .birth("19990909")
                .build();
    }

    @DisplayName("Member 객체를 저장하면 id를 반환한다")
    @Test
    public void insertTest() {
        memberMapper.insert(member);

        assertThat(member.getId()).isEqualTo(6);
    }
}