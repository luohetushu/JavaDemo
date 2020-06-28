package com.mytest.pattern.proxy;

/**
 * @Author murongyunge
 * @Describe  代理类
 * @Date 2019-12-10
 */
public class ProxyStatic implements Subject {

    /**
     * 要代理的实现类
     */
    private Subject subject = null;

    /**
     * 默认代理自己
     */
    public ProxyStatic() {
        this.subject = new ProxyStatic();
    }

    public ProxyStatic(Subject subject) {
        this.subject = subject;
    }

    /**
     * 构造函数，传递委托者
     *
     * @param objects 委托者
     */
    public ProxyStatic(Object... objects) {
    }

    /**
     * 实现接口方法
     */
    @Override
    public void request() {
        this.before();
        this.subject.request();
        this.after();
    }

    @Override
    public void marry() {
        this.before();
        this.subject.marry();
        this.after();
    }

    /**
     * 预处理
     */
    private void before() {
        System.out.println("我们做结婚前的准备工作，可以开始结婚了");
    }

    /**
     * 后处理
     */
    private void after() {
        System.out.println("结婚完毕，我们需要做后续处理，你们可以回家了，其余的事情我们公司来做");
    }

}
