package com.ep.spring.core.aspects;

import com.ep.spring.core.beans.Event;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import com.ep.spring.core.loggers.ConsoleEventLogger.*;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class StatisticsAspect {
    private Map<Class<?>, Integer> counterMap = new HashMap<>();

    @Pointcut("execution(* *.logEvent(*))")
    private void allLogEventMethods() {}


    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint joinPoint){
        Class<?> clazz = joinPoint.getTarget().getClass();
        System.out.println("I'm in static aspect in - " + clazz);
        if (!counterMap.containsKey(clazz)) {
            counterMap.put(clazz, 1);
        } else {
            counterMap.put(clazz, counterMap.get(clazz) + 1);
        }
    }

    public Map<Class<?>, Integer> getCounterMap() {
        return counterMap;
    }

    public void setCounterMap(Map<Class<?>, Integer> counterMap) {
        this.counterMap = counterMap;
    }
}
