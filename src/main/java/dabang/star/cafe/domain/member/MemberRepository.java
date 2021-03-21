package dabang.star.cafe.domain.member;

import java.util.Optional;

public interface MemberRepository {

    void save(Member member);

    boolean existsByEmail(String email);

    Optional<MemberData> findByEmailAndPassword(String email, String password);

    Optional<MemberData> findByIdAndPassword(long id, String password);

    Optional<MemberData> findById(long id);

    void deleteById(long id);
}