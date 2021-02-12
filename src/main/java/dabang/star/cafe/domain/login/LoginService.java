package dabang.star.cafe.domain.login;


import dabang.star.cafe.application.data.MemberNickname;

public interface LoginService {

    MemberNickname login(String email, String password);

    void logout();
}
