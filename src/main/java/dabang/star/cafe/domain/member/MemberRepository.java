package dabang.star.cafe.domain.member;

import dabang.star.cafe.api.response.member.MemberData;

import java.util.Optional;

public interface MemberRepository {

    Long save(Member member);

    boolean isExist(String email);

    Optional<MemberData> findMemberByEmailAndPassword(String email, String password);

    Optional<MemberData> findMemberById(long id);
}