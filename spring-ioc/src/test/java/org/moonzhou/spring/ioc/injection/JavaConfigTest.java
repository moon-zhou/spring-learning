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

    @Test
    public void testListBean() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);

        Demo005AutowiredListBean autowiredListBean = ctx.getBean(Demo005AutowiredListBean.class);
        autowiredListBean.test();
        System.out.println();

        Demo005InjectListBean injectListBean = ctx.getBean(Demo005InjectListBean.class);
        injectListBean.test();
        System.out.println();

        Demo005ResourceListBean resourceListBean = ctx.getBean(Demo005ResourceListBean.class);
        resourceListBean.test();
    }

    /**
     * org.springframework.beans.factory.UnsatisfiedDependencyException:
     * Error creating bean with name 'demo006AutowiredFiledNameConflictQualifier':
     * Unsatisfied dependency expressed through field 'twoWheeler';
     * nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException:
     * No qualifying bean of type 'org.moonzhou.spring.ioc.injection.service.Vehicle' available:
     * expected at least 1 bean which qualifies as autowire candidate. Dependency annotations:
     * {@org.springframework.beans.factory.annotation.Autowired(required=true), @org.springframework.beans.factory.annotation.Qualifier(value=noSuchBean)}
     */
    @Test
    public void testAutowiredFiledNameConflictQualifier() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        Demo006AutowiredFiledNameConflictQualifier autowiredFiledNameConflictQualifier = ctx.getBean(Demo006AutowiredFiledNameConflictQualifier.class);
        autowiredFiledNameConflictQualifier.test();
    }

    /**
     * org.springframework.beans.factory.UnsatisfiedDependencyException:
     * Error creating bean with name 'demo006InjectFiledNameConflictQualifier':
     * Unsatisfied dependency expressed through field 'twoWheeler';
     * nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException:
     * No qualifying bean of type 'org.moonzhou.spring.ioc.injection.service.Vehicle' available:
     * expected at least 1 bean which qualifies as autowire candidate. Dependency annotations:
     * {@javax.inject.Inject(), @org.springframework.beans.factory.annotation.Qualifier(value=noSuchBean)}
     */
    @Test
    public void testInjectFiledNameConflictQualifier() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        Demo006InjectFiledNameConflictQualifier injectFiledNameConflictQualifier = ctx.getBean(Demo006InjectFiledNameConflictQualifier.class);
        injectFiledNameConflictQualifier.test();
    }

    @Test
    public void testResourceFiledNameConflictQualifier() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        Demo006ResourceFiledNameConflictQualifier resourceFiledNameConflictQualifier = ctx.getBean(Demo006ResourceFiledNameConflictQualifier.class);
        resourceFiledNameConflictQualifier.test();
    }

    @Test
    public void testResourceBeanName() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        Demo007ResourceBeanName resourceBeanName = ctx.getBean(Demo007ResourceBeanName.class);
        resourceBeanName.test();
    }

    @Test
    public void testSetter() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);

        Demo008AutowiredSetter autowiredSetter = ctx.getBean(Demo008AutowiredSetter.class);
        autowiredSetter.test();

        Demo008InjectSetter injectSetter = ctx.getBean(Demo008InjectSetter.class);
        injectSetter.test();

        Demo008ResourceSetter resourceSetter = ctx.getBean(Demo008ResourceSetter.class);
        resourceSetter.test();
    }

    @Test
    public void testNamed() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);

        Demo009AutowiredNamed autowiredNamed = ctx.getBean(Demo009AutowiredNamed.class);
        autowiredNamed.test();

        Demo009InjectNamed injectNamed = ctx.getBean(Demo009InjectNamed.class);
        injectNamed.test();

        Demo009ResourceNamed resourceNamed = ctx.getBean(Demo009ResourceNamed.class);
        resourceNamed.test();
    }
}