package dabang.star.cafe.application.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {

    public static final String USER_SESSION_KEY = "loginUser";

    public static void loginUser(String email) {
        HttpSession httpSession = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        httpSession.setAttribute(USER_SESSION_KEY, email);
    }

    public static void logoutUser() {
        HttpSession httpSession = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        httpSession.invalidate();
    }
}

