package dabang.star.cafe.api.aop;

import dabang.star.cafe.api.exception.NoAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

import static dabang.star.cafe.utils.SessionKey.CURRENT_MEMBER_ID;
import static dabang.star.cafe.utils.SessionKey.LOGIN_MEMBER_ID;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthCheckAspect {

    private final HttpSession httpSession;

    @Around("@annotation(dabang.star.cafe.api.aop.MemberLoginCheck) && execution(* *(.., @SessionMemberId(Long)))")
    public Object memberLoginCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object memberId = httpSession.getAttribute(LOGIN_MEMBER_ID);
        return proceedWithMemberId(memberId, proceedingJoinPoint);
    }

    @Around("@annotation(dabang.star.cafe.api.aop.CurrentMemberCheck) && execution(* *(.., @SessionMemberId(Long)))")
    public Object currentMemberCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object memberId = httpSession.getAttribute(CURRENT_MEMBER_ID);
        return proceedWithMemberId(memberId, proceedingJoinPoint);
    }

    private Object proceedWithMemberId(Object memberId, ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (memberId == null) {
            throw new NoAuthenticationException("no authenticated");
        }

        Object[] args = proceedingJoinPoint.getArgs();
        if (args[args.length - 1] == null) {
            args[args.length - 1] = memberId;
        }

        return proceedingJoinPoint.proceed(args);
    }

}
