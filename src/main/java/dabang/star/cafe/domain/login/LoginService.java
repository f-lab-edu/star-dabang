package dabang.star.cafe.domain.login;


import dabang.star.cafe.api.response.member.MemberData;

public interface LoginService {

    MemberData login(String email, String password);

    void logout();

    MemberData accessToMyPage(long id, String password);
}
