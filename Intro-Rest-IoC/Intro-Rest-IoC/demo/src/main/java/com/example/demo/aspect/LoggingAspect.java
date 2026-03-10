package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut para todos os métodos públicos de qualquer classe no pacote de serviço.
    // Vamos focar apenas no VendaService, como solicitado.
    @Pointcut("within(@org.springframework.stereotype.Service *) && execution(* com.example.demo.service.VendaService.*(..))")
    public void vendaServiceMethods() {}

    @Around("vendaServiceMethods()")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        logger.info("===== [AOP LOG] Entrando em {}.{} =====", className, methodName);
        logger.info("Parâmetros recebidos: {}", Arrays.toString(args));

        Object result = null;
        try {
            result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            logger.info("Tempo de execução de {}.{}: {} ms", className, methodName, elapsedTime);
            logger.info("===== [AOP LOG] Saindo de {}.{} =====\n", className, methodName);
            return result;
        } catch (IllegalArgumentException e) {
            logger.error("Exceção em {}.{}: Mensagem: {}", className, methodName, e.getMessage());
            throw e;
        }
    }
}