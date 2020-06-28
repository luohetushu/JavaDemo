package com.mytest.pattern.decorator;

/**
 * @Author murongyunge
 * @Describe  具体装饰类3
 * @Date 2019-12-09
 */
public class DecoratorReplenish3 extends DecoratorReplenish {
    public DecoratorReplenish3(DecoratorAbstract anAbstract) {
        super(anAbstract);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "，见星空是你";
    }

    @Override
    public int replenish() {
        return super.replenish() + 1;
    }
}
