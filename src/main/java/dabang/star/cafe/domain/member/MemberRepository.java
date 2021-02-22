package dabang.star.cafe.domain.member;

import dabang.star.cafe.application.data.MemberLogin;

import java.util.Optional;

public interface MemberRepository {

    Long save(Member member);

    boolean isExist(String email);

    Optional<MemberLogin> findMemberByLogin(String email, String password);
}