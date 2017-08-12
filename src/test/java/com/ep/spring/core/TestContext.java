package com.ep.spring.core;
import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ep.spring.core.Client;
import com.ep.spring.core.loggers.CacheFileEventLogger;
import com.ep.spring.core.loggers.CombinedEventLogger;
import com.ep.spring.core.loggers.EventLogger;
import com.ep.spring.core.loggers.FileEventLogger;

public class TestContext {

    @Test
    public void testPropertyPlaceholderSystemOverride() {
        System.setProperty("id", "35");
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("springConfig.xml");
        Client client = ctx.getBean(Client.class);
        ctx.close();

        assertEquals("35", client.getId());
    }

    @Test
    public void testLoggersNames() {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("springConfig.xml");

        EventLogger fileLogger = ctx.getBean("fileEventLogger", FileEventLogger.class);
        EventLogger cacheLogger = ctx.getBean(CacheFileEventLogger.class);
        CombinedEventLogger combinedLogger = ctx.getBean(CombinedEventLogger.class);

        assertEquals(fileLogger.getName() + " with cache", cacheLogger.getName());

        Collection<String> combinedNames = combinedLogger.getLoggers().stream()
                .map(v -> v.getName()).collect(Collectors.toList());

        assertEquals("Combined " + combinedNames, combinedLogger.getName());

        ctx.close();
    }

}