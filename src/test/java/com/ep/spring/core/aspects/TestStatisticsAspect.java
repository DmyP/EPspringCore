package com.ep.spring.core.aspects;

import com.ep.spring.core.loggers.CombinedEventLogger;
import com.ep.spring.core.loggers.ConsoleEventLogger;
import org.aspectj.lang.JoinPoint;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestStatisticsAspect {
    @Test
    public void count() throws Exception {
        JoinPoint jp = mock(JoinPoint.class);
        when(jp.getTarget()).thenReturn(new ConsoleEventLogger())
                .thenReturn(new CombinedEventLogger(Collections.emptyList()))
                .thenReturn(new ConsoleEventLogger());

        StatisticsAspect aspect = new StatisticsAspect();

        aspect.count(jp);
        aspect.count(jp);
        aspect.count(jp);

        verify(jp);

        Map<Class<?>, Integer> counters = aspect.getCounterMap();
        assertEquals(2, counters.size());
        assertEquals(2, counters.get(ConsoleEventLogger.class).intValue());
        assertEquals(1, counters.get(CombinedEventLogger.class).intValue());
    }


}