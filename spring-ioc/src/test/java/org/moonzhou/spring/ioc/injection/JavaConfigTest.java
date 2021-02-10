package org.moonzhou.spring.ioc.injection;

import org.junit.Test;
import org.moonzhou.spring.ioc.injection.biz.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class JavaConfigTest {

    /**
     * Exception encountered during context initialization - cancelling refresh attempt:
     * <p>
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

    @Test
    public void testConcreteClass() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);

        Demo002AutowiredConcreteClass autowiredConcreteClass = ctx.getBean(Demo002AutowiredConcreteClass.class);
        autowiredConcreteClass.test();

        Demo002InjectConcreteClass injectConcreteClass = ctx.getBean(Demo002InjectConcreteClass.class);
        injectConcreteClass.test();

        Demo002ResourceConcreteClass resourceConcreteClass = ctx.getBean(Demo002ResourceConcreteClass.class);
        resourceConcreteClass.test();
    }

    @Test
    public void testFiledName() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);

        Demo003AutowiredFiledName autowiredFiledName = ctx.getBean(Demo003AutowiredFiledName.class);
        autowiredFiledName.test();

        Demo003InjectFiledName injectFiledName = ctx.getBean(Demo003InjectFiledName.class);
        injectFiledName.test();

        Demo003ResourceFiledName resourceFiledName = ctx.getBean(Demo003ResourceFiledName.class);
        resourceFiledName.test();
    }

    @Test
    public void testQualifierBeanName() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);

        Demo004AutowiredQualifierBeanName autowiredQualifierBeanName = ctx.getBean(Demo004AutowiredQualifierBeanName.class);
        autowiredQualifierBeanName.test();

        Demo004InjectQualifierBeanName injectQualifierBeanName = ctx.getBean(Demo004InjectQualifierBeanName.class);
        injectQualifierBeanName.test();

        Demo004ResourceQualifierBeanName resourceQualifierBeanName = ctx.getBean(Demo004ResourceQualifierBeanName.class);
        resourceQualifierBeanName.test();
    }
}