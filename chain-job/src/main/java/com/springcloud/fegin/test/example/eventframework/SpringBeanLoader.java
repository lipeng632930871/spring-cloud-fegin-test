package com.springcloud.fegin.test.example.eventframework;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Created by 003186 on 2010/7/27.
 */
@Component
public class SpringBeanLoader implements ApplicationContextAware {
    private static ApplicationContext appCtx;

    /**
     * 此方法可以把ApplicationContext对象inject到当前类中作为一个静态成员变量。
     *
     * @param applicationContext ApplicationContext 对象.
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setAppCtx(applicationContext);
    }

    /**
     * 这是一个便利的方法，帮助我们快速得到一个BEAN
     *
     * @param beanName bean的名字
     * @return 返回一个bean对象
     */
    public static Object getBean(String beanName) {
        return appCtx.getBean(beanName);
    }

    /**
     * 获取 bean 对象
     *
     * @param beanName
     * @param clazz
     * @return
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        Assert.notNull(appCtx, "Application Context not initialize");
        return appCtx.getBean(beanName, clazz);
    }


    @SuppressWarnings("hiding")
    public static <T> T getBeanOfType(Class<T> class_) {
        return appCtx.getBean(class_);
    }

    /**
     * 根据type获取Bean集合
     *
     * @param type
     * @return <name, object>集合
     */
    public static Map<String, ? extends Object> getRealBeanOfType(Class<? extends Object> type) {
        return appCtx.getBeansOfType(type);
    }

    public static void setAppCtx(ApplicationContext appCtx) {
        SpringBeanLoader.appCtx = appCtx;
    }
}
