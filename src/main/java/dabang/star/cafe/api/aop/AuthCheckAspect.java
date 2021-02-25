package dabang.star.cafe.api.aop;

import dabang.star.cafe.api.exception.NoAuthorizationException;
import dabang.star.cafe.utils.SessionKey;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthCheckAspect {

    private final HttpSession httpSession;

    /**
     * MemberLoginCheck 어노테이션이 붙은 메서드에 대해 멤버 로그인 여부를 체크한다.
     * @param joinPoint 조인포인트
     */
    @Before("@annotation(dabang.star.cafe.api.aop.MemberLoginCheck)")
    public void memberLoginCheck(JoinPoint joinPoint) {
        var sessionAttribute = httpSession.getAttribute(SessionKey.LOGIN_MEMBER_ID);

        if (sessionAttribute == null) {
            throw new NoAuthorizationException("not authorized");
        }
    }
}
