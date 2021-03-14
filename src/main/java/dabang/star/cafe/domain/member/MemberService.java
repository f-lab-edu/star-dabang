package dabang.star.cafe.domain.member;

public interface MemberService {

    MemberData join(Member member);

    void checkDuplicatedMember(Member member);

    void update(Member member);

    MemberData loadById(long id);

    void secession(long id);
}
