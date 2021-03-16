package dabang.star.cafe.api.aop;

import dabang.star.cafe.api.exception.NoAuthenticationException;
import dabang.star.cafe.api.exception.NoAuthorizationException;
import dabang.star.cafe.domain.manager.Role;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static dabang.star.cafe.utils.SessionKey.*;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthCheckAspect {

    private final HttpSession httpSession;

    @Around("@annotation(dabang.star.cafe.api.aop.LoginCheck)")
    public Object LoginCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object loginId = httpSession.getAttribute(LOGIN_ID);

        return proceedWithLoginId(loginId, proceedingJoinPoint);
    }

    @Around("@annotation(dabang.star.cafe.api.aop.CurrentMemberCheck)")
    public Object currentMemberCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object memberId = httpSession.getAttribute(CURRENT_MEMBER_ID);

        return proceedWithLoginId(memberId, proceedingJoinPoint);
    }

    private Object proceedWithLoginId(Object sessionId, ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        if (sessionId == null) {
            throw new NoAuthenticationException("no authenticated");
        }

        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        LoginCheck loginCheck = method.getAnnotation(LoginCheck.class);
        if (loginCheck != null && loginCheck.role() != Role.NONE) {
            managerRoleCheck(loginCheck.role());
        }

        Object[] args = proceedingJoinPoint.getArgs();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int idx = 0; idx < parameterAnnotations.length; idx++) {
            for (Annotation a : parameterAnnotations[idx]) {
                if (a.annotationType().equals(SessionId.class)) {
                    args[idx] = sessionId;
                }
            }
        }

        return proceedingJoinPoint.proceed(args);
    }


    private void managerRoleCheck(Role role) {

        Role managerRole = (Role) httpSession.getAttribute(MANAGER_ROLE);

        if (!isMatchRole(role, managerRole)) {
            throw new NoAuthorizationException("no authorization");
        }
    }

    private boolean isMatchRole(Role role, Role managerRole) {
        return (role == Role.ADMIN && role == managerRole) ||
                (role == Role.MANAGER && role == managerRole);
    }

}
