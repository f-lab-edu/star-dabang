package dabang.star.cafe.domain.login;


import dabang.star.cafe.domain.member.MemberData;

public interface MemberLoginService {

    MemberData login(String email, String password);

    void logout();

    MemberData accessMyPage(long id, String password);
}
