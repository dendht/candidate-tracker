package com.candidatetracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect
{
  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

  @Pointcut("execution(* com.candidatetracker.controller.*.*(..))")
  private void controllerPointcut()
  {
  }

  @Pointcut("execution(* com.candidatetracker.dataaccess.*.*(..))")
  private void dataAccessPointcut()
  {
  }

  @Pointcut("execution(* com.candidatetracker.service.*.*(..))")
  private void servicePointcut()
  {
  }

  @Pointcut("controllerPointcut() || dataAccessPointcut() || servicePointcut()")
  private void appFlowPointcut()
  {
  }

  // log method calls
  @Before("appFlowPointcut()")
  public void logBeforeMethod(JoinPoint joinPoint)
  {
    LOGGER.info(">>> Method " + joinPoint.getSignature().toShortString()
        + " execution started");
    Object[] args = joinPoint.getArgs();
    if (args.length > 0)
    {
      LOGGER.info(">>>>>> Arguments: " + Arrays.asList(args));
    }
  }

  // log results of successful method calls
  @AfterReturning(
      pointcut = "appFlowPointcut()",
      returning = "result")
  public void logAfterMethod(JoinPoint joinPoint,
      Object result)
  {
    LOGGER.info(">>> Method " + joinPoint.getSignature().toShortString()
        + " execution finished");
    if (result != null)
    {
      LOGGER.info(">>>>>> Result of method execution: " + result);
    }
  }
}
