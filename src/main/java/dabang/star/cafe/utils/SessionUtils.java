package dabang.star.cafe.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionUtils {

    /**
     * 로그인 회원의 ID를 가져오는 세션 키
     */
    public static final String LOGIN_MEMBER_ID = "MEMBER";

    /**
     * 마이 페이지 접속 ID를 가져오는 세션 키
     */
    private static final String CURRENT_MEMBER_ID = "CURRENT";


    public static Optional<Long> getLoginMemberId(HttpSession httpSession) {
        return Optional.ofNullable((Long) httpSession.getAttribute(LOGIN_MEMBER_ID));
    }

    public static void setLoginMemberId(HttpSession httpSession, Long id) {
        httpSession.setAttribute(LOGIN_MEMBER_ID, id);
    }

    public static void clearLoginMemberId(HttpSession httpSession) {
        httpSession.removeAttribute(LOGIN_MEMBER_ID);
    }

    public static Optional<Long> getCurrentMemberId(HttpSession httpSession) {
        return Optional.ofNullable((Long) httpSession.getAttribute(CURRENT_MEMBER_ID));
    }

    public static void setCurrentMemberId(HttpSession httpSession, Long id) {
        httpSession.setAttribute(CURRENT_MEMBER_ID, id);
    }

}
