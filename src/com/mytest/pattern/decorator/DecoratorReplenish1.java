package com.mytest.pattern.decorator;

/**
 * @Author murongyunge
 * @Describe 具体装饰类1
 * @Date 2019-12-09
 */
public class DecoratorReplenish1 extends DecoratorReplenish {
    public DecoratorReplenish1(DecoratorAbstract anAbstract) {
        super(anAbstract);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "，见山是你";
    }

    @Override
    public int replenish() {
        return super.replenish() + 1;
    }
}
