package com.mytest.pattern.decorator;

/**
 * @Author murongyunge
 * @Describe  装饰父类，即添加功能的父类
 *            当需要在具体的装饰类中执行一些特定的操作时，装饰父类一般使用抽象类，并定义抽象方法
 * @Date 2019-12-09
 */
public class DecoratorReplenish extends DecoratorAbstract{

    private DecoratorAbstract anAbstract;

    DecoratorReplenish(DecoratorAbstract anAbstract) {
        this.anAbstract = anAbstract;
    }

    @Override
    public String getDescription() {
        return this.anAbstract.getDescription();
    }

    @Override
    public int replenish() {
        return this.anAbstract.replenish();
    }
}
