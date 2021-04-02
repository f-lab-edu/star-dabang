package dabang.star.cafe.domain.authentication;


import dabang.star.cafe.application.data.MemberData;

public interface MemberLoginService {

    MemberData login(String email, String password);

    void logout();

    MemberData accessMyPage(long id, String password);
}
