/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: HiMom.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/2/26 17:43
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.spring.ioc.scope.dependencies;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 功能描述: singleton bean 依赖 prototype bean <br>
 *
 *     通过getBean，在使用时重新获取prototype bean，在使用时则可以避免这个问题
 *     一定要注意，getBean之后立即使用，否则下一次的getBean会覆盖掉本次的getBean
 *     即：
 *     HiMomGetBean hiMomGetBean1 = ctx.getBean(HiMomGetBean.class);
 *     HiMomGetBean hiMomGetBean2 = ctx.getBean(HiMomGetBean.class);
 *     hiMomGetBean1.createPerformer();
 *     hiMomGetBean2.createPerformer(); //覆盖了上面的createPerformer()产生的getBean
 *
 *     则：
 *     hiMomGetBean1.getPerformer();
 *     hiMomGetBean2.getPerformer();
 *     所获取的值是一样的，因为getBean和使用是分离的，多次getBean连续，已最后一次为准，覆盖前面的getBean的实例对象
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HiMomGetBean implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Performer performer;

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }

    public Performer getPerformer() {
        return performer;
    }

    /**
     * 手动重新获取，设置bean
     */
    protected void createPerformer() {
        this.performer = applicationContext.getBean("performer", Performer.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public String toString() {
        return "HiMom{" +
                "performer=" + performer +
                '}';
    }
}
