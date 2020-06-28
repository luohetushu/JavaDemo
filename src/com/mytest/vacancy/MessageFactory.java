package com.mytest.vacancy;

/**
 * 泛型工厂 工厂模式
 * @param
 */
public class MessageFactory {

    public static IMessage getInstance(String className){
        if (className.equalsIgnoreCase("NorMessage")){
            return new NorMessage();
        }
        return null;
    }

    public <T> IMessage<T> getInstance(String className, T msg){
        if (className.equalsIgnoreCase("NorMessage")){
            return new NorMessage<T>();
        }
        return null;
    }

//    public static <T, B> T getInstance(T classes, String className, B msg){
//        if (className.equalsIgnoreCase("NorMessage")){
//            return ((T) new NorMessage<B>());
//        }
//        return null;
//    }

}
