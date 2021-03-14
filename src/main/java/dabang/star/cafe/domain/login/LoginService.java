package dabang.star.cafe.domain.login;


import dabang.star.cafe.api.response.manager.ManagerData;
import dabang.star.cafe.api.response.member.MemberData;

public interface LoginService {

    MemberData loginMember(String email, String password);

    void logoutMember();

    MemberData accessMyPage(long id, String password);

    ManagerData loginManager(String name, String password);

    void logoutManager();
}
