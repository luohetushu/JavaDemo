package com.mytest.pattern.decorator;

/**
 * @Author murongyunge
 * @Describe  原有基类
 * @Date 2019-12-09
 */
public class DecoratorMain extends DecoratorAbstract {
    @Override
    public String getDescription() {
        return "一眼所见";
    }

    @Override
    public int replenish() {
        return 1;
    }
}
