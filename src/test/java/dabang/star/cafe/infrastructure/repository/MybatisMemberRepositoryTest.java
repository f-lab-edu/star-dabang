package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.member.MemberData;
import dabang.star.cafe.domain.member.Member;
import dabang.star.cafe.domain.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@MybatisTest
@Import(MybatisMemberRepository.class)
class MybatisMemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void before() {
        member = Member.builder()
                .email("test@naver.com")
                .password("asdf1234567")
                .nickname("테스트")
                .telephone("01012345678")
                .birth("20201010")
                .build();
    }

    @DisplayName("멤버를 저장할 수 있어야 한다")
    @Test
    void saveMember() {
        memberRepository.save(member);
        MemberData saveMember = new MemberData(member);

        Optional<MemberData> findMember = memberRepository.findMemberByEmailAndPassword(this.member.getEmail(), this.member.getPassword());

        assertThat(findMember.get().getEmail()).isEqualTo(saveMember.getEmail());
    }

    @DisplayName("이미 존재하는 이메일이면 true를 반환한다")
    @Test
    void isNotExistEmail() {
        memberRepository.save(member);
        boolean result = memberRepository.isExist("test@naver.com");

        assertThat(result).isTrue();
    }

    @DisplayName("존재하지 않는 이메일이면 true를 반환한다")
    @Test
    void isExistEmail() {
        memberRepository.save(member);
        boolean result = memberRepository.isExist("abcd@naver.com");

        assertThat(result).isFalse();
    }

    @DisplayName("회원의 id가 주어졌을 때 회원을 삭제한다")
    @Test
    void deleteMemberTest() {
        memberRepository.save(member);
        memberRepository.deleteById(member.getId());

        Optional<MemberData> findMember = memberRepository.findMemberById(member.getId());

        assertThat(findMember).isEmpty();
    }

}