package dabang.star.cafe.domain.member;

public interface MemberService {

    MemberData join(String email, String password, String nickname, String telephone, String birth);

    void checkDuplicatedEmail(String email);

    void update(Member member);

    MemberData loadById(long id);

    void secession(long id);
}
