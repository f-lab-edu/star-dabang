package dabang.star.cafe.api.aop;

import dabang.star.cafe.api.exception.NoAuthorizationException;
import dabang.star.cafe.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthCheckAspect {

    private final HttpSession httpSession;

    @Around("@annotation(dabang.star.cafe.api.aop.MemberLoginCheck)")
    public Object memberLoginCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Optional<Long> memberIdOptional = SessionUtils.getLoginMemberId(httpSession);
        return proceedWithMemberId(memberIdOptional, proceedingJoinPoint);
    }

    @Around("@annotation(dabang.star.cafe.api.aop.CurrentMemberCheck)")
    public Object currentMemberCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Optional<Long> memberIdOptional = SessionUtils.getCurrentMemberId(httpSession);
        return proceedWithMemberId(memberIdOptional, proceedingJoinPoint);
    }

    private Object proceedWithMemberId(Optional<Long> memberIdOptional, ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return proceedingJoinPoint.proceed(
                new Object[]{
                        proceedingJoinPoint.getArgs()[0],
                        memberIdOptional.orElseThrow(() -> new NoAuthorizationException("not authorized"))
                }
        );
    }
}
