package com.ep.spring.core.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class AwareBean implements ApplicationContextAware, BeanNameAware, ApplicationEventPublisherAware {
    private ApplicationEventPublisher eventPublisher;
    private String name;
    private ApplicationContext ctx;

    public void init(){
        System.out.println(this.getClass().getSimpleName() + " > My name is '" + name + "'");

        if (ctx != null) {
            System.out.println(this.getClass().getSimpleName() + " > My context is " + ctx.getClass().toString());
        } else {
            System.out.println(this.getClass().getSimpleName() + " Context is nor set");
        }
        if (eventPublisher  != null) {
            System.out.println(this.getClass().getSimpleName() + " > My context is " + eventPublisher.getClass().toString());
        } else {
            System.out.println(this.getClass().getSimpleName() + " EventPublisher  is nor set");
        }

    }

    @Override
    public void setBeanName(String s) {
        this.name = s;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
