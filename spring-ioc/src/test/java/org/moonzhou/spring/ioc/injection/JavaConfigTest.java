package org.moonzhou.spring.ioc.injection;

import org.junit.Test;
import org.moonzhou.spring.ioc.injection.biz.Demo001AutowiredInterfaceType;
import org.moonzhou.spring.ioc.injection.biz.Demo001InjectInterfaceType;
import org.moonzhou.spring.ioc.injection.biz.Demo001ResourceInterfaceType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class JavaConfigTest {

    /**
     * Exception encountered during context initialization - cancelling refresh attempt:
     *
     * org.springframework.beans.factory.UnsatisfiedDependencyException:
     * Error creating bean with name 'demo001AutowiredInterfaceType':
     * Unsatisfied dependency expressed through field 'vehicle';
     * nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException:
     * No qualifying bean of type 'org.moonzhou.spring.ioc.injection.service.Vehicle' available:
     * expected single matching bean but found 2: fourWheeler,twoWheeler
     */
    @Test
    public void testDemo001AutowiredInterfaceType() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        Demo001AutowiredInterfaceType bean = ctx.getBean(Demo001AutowiredInterfaceType.class);
        bean.test();
    }

    /**
     * org.springframework.beans.factory.UnsatisfiedDependencyException:
     * Error creating bean with name 'demo001AutowiredInterfaceType':
     * Unsatisfied dependency expressed through field 'vehicle';
     * nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException:
     * No qualifying bean of type 'org.moonzhou.spring.ioc.injection.service.Vehicle' available:
     * expected single matching bean but found 2: fourWheeler,twoWheeler
     */
    @Test
    public void testDemo001InjectInterfaceType() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        Demo001InjectInterfaceType bean = ctx.getBean(Demo001InjectInterfaceType.class);
        bean.test();
    }

    /**
     * org.springframework.beans.factory.UnsatisfiedDependencyException:
     * Error creating bean with name 'demo001AutowiredInterfaceType':
     * Unsatisfied dependency expressed through field 'vehicle';
     * nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException:
     * No qualifying bean of type 'org.moonzhou.spring.ioc.injection.service.Vehicle' available:
     * expected single matching bean but found 2: fourWheeler,twoWheeler
     */
    @Test
    public void testDemo001ResourceInterfaceType() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        Demo001ResourceInterfaceType bean = ctx.getBean(Demo001ResourceInterfaceType.class);
        bean.test();
    }
}