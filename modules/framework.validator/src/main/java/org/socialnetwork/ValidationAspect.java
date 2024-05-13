package org.socialnetwork;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.socialnetwork.annotations.Validate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
public class ValidationAspect {

    @Before("execution(* *(.., @org.socialnetwork.annotations.Validate (*), ..))")
    public void validateAnnotatedParameters(JoinPoint joinPoint) throws IllegalAccessException, IllegalArgumentException {
        Method method = getMethodFromJoinPoint(joinPoint);
        Object[] args = joinPoint.getArgs();
        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(Validate.class)) {
                Object arg = args[i];
                if (arg != null) {
                    Validator.validate(arg);
                }
            }
        }
    }

    private Method getMethodFromJoinPoint(JoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod();
    }
}
