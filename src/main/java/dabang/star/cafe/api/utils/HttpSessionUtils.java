package dabang.star.cafe.api.utils;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {

    public static final String USER_SESSION_KEY = "loginUser";

    public static void setLoginUserEmail(HttpSession httpSession, String email) {
        httpSession.setAttribute(USER_SESSION_KEY, email);
    }

    public static void removeLoginUserEmail(HttpSession httpSession) {
        httpSession.invalidate();
    }

}
