package dabang.star.cafe.domain.member;

import java.util.Optional;

public interface MemberRepository {

    Long save(Member member);

    boolean isExist(String email);

    Optional<MemberData> findMemberByEmailAndPassword(String email, String password);

    Optional<MemberData> findMemberByIdAndPassword(long id, String password);

    Optional<MemberData> findMemberById(long id);

    void deleteById(long id);
}