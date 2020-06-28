package com.mytest.demo;

import com.mytest.pattern.adapter.*;
import com.mytest.pattern.decorator.*;
import com.mytest.pattern.factory.*;
import com.mytest.pattern.observer.RealObserver;
import com.mytest.pattern.observer.RealObserver2;
import com.mytest.pattern.observer.RealSubject;
import com.mytest.pattern.observer.RealSubject2;
import com.mytest.pattern.proxy.ConcreteSubject;
import com.mytest.pattern.proxy.ProxyHandler;
import com.mytest.pattern.proxy.ProxyStatic;
import com.mytest.pattern.proxy.Subject;
import com.mytest.pattern.singleton.SingletonPattern5;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-12-07
 */
public class DesignPatternDemo {

    public static void main(String[] args) {

        //单例模式 反射攻击
        try {
            SingletonPattern5 s = SingletonPattern5.getInstance();
            SingletonPattern5 sUsual = SingletonPattern5.getInstance();
            Constructor<SingletonPattern5> constructor = SingletonPattern5.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            SingletonPattern5 sReflection = constructor.newInstance();
            System.out.println(s + "\n" + sUsual + "\n" + sReflection);
            System.out.println("正常情况下，实例化两个实例是否相同：" + (s == sUsual));  //true
            System.out.println("通过反射攻击单例模式情况下，实例化两个实例是否相同：" + (s == sReflection));  //false
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //观察者模式
        RealSubject subject = new RealSubject();
        RealObserver observer = new RealObserver();
        //注册
        subject.attach(observer);
        // 发送通知
        subject.notifyChanged();

        //使用Java自带的观察者模式类实现
        RealSubject2 subject2 = new RealSubject2();
        RealObserver2 observer2 = new RealObserver2();
        //
        subject2.addObserver(observer2);
        //
        subject2.makeChanged();

        //装饰者模式
        DecoratorAbstract anAbstract;
        anAbstract = new DecoratorMain();
        System.out.println("历经" + anAbstract.replenish() + "辈子，" + anAbstract.getDescription() + "，皆是你");
        anAbstract = new DecoratorReplenish1(anAbstract);
        //anAbstract = new DecoratorReplenish1(anAbstract);
        anAbstract = new DecoratorReplenish2(anAbstract);
        anAbstract = new DecoratorReplenish3(anAbstract);
        System.out.println("历经" + anAbstract.replenish() + "辈子，" + anAbstract.getDescription() + "，皆是你");

        //适配器模式
        TargetIn target1 = new Adapter1();
        target1.request();
        TargetIn target2 = new Adapter2();
        target2.request();
        TargetCl target3 = new TargetCl();
        target3.setAdapter(new Adapter3());
        target3.request();

        //简单工厂设计模式
        FactorySimple factory1 = new FactorySimple();
        Sender mail = factory1.getSenderInstance("mail");
        mail.Send();
        //多个工厂方法模式
        FactorySeveral factory2 = new FactorySeveral();
        Sender sms = factory2.getSmsInstance();
        sms.Send();
        //静态工厂方法模式
        Sender sms2 = FactoryStatic.getSmsInstance();
        sms2.Send();
        //抽象工厂模式
        //该模式下，如果想增加一个功能：发及时信息，则只需做一个实现类，实现Sender接口，同时做一个工厂类，实现Provider接口，就好，无需去改动现成的代码
        Factory factory4 = new SenderMailFactory();
        Sender mail2 = factory4.getInstance();
        mail2.Send();

        //静态代理模式
        Subject subject1 = new ConcreteSubject();
        ProxyStatic proxy = new ProxyStatic(subject1);
        proxy.marry();
        //动态代理模式
        ProxyHandler handler = new ProxyHandler();
        Subject subject3 = (Subject) handler.newProxyInstance(new ConcreteSubject());
        subject3.marry();

    }

}
