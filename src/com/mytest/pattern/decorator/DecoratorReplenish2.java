package com.mytest.pattern.decorator;

/**
 * @Author murongyunge
 * @Describe  具体装饰类2
 * @Date 2019-12-09
 */
public class DecoratorReplenish2 extends DecoratorReplenish {
    public DecoratorReplenish2(DecoratorAbstract anAbstract) {
        super(anAbstract);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "，见水是你";
    }

    @Override
    public int replenish() {
        return super.replenish() + 1;
    }
}
