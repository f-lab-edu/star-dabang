package dabang.star.cafe.domain.member;

import dabang.star.cafe.infrastructure.repository.MybatisMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@MybatisTest
@Import(MybatisMemberRepository.class)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    public void before() {
        member = Member.builder()
                .email("test@naver.com")
                .password("asdf1234567")
                .nickname("테스트")
                .telephone("01012345678")
                .birth("20201010")
                .build();
    }

    @DisplayName("멤버를 저장한다면 id를 반환한다")
    @Test
    public void saveMember() {
        Long saveId = memberRepository.save(member);

        assertThat(saveId).isEqualTo(6);
    }
}