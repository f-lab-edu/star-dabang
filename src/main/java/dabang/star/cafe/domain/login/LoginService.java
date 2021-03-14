package dabang.star.cafe.domain.login;


import dabang.star.cafe.domain.manager.ManagerData;
import dabang.star.cafe.domain.member.MemberData;

public interface LoginService {

    MemberData loginMember(String email, String password);

    void logoutMember();

    MemberData accessMyPage(long id, String password);

    ManagerData loginManager(String name, String password);

    void logoutManager();
}
