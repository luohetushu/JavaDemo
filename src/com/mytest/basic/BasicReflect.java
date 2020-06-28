package com.mytest.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-16
 */
public class BasicReflect {

    public static void main(String[] args){

        //通过反射访问构造函数（方法）
        //constructorFromReflect();

        //通过反射执行方法（获取方法）
        //methodFromReflect();

        //通过反射访问成员变量
        filedFromReflect();

    }

    //通过反射访问构造函数（方法）
    private static void constructorFromReflect(){
        //获取动态类 BasicReflect
        Class reflect = BasicReflect.class;
        //用 Constructor 类的方法获取构造方法
        //获取 BasicReflect 类的所有构造方法
        Constructor[] constructors = reflect.getDeclaredConstructors();
        //遍历所有构造方法
        for (Constructor constructor : constructors) {
            System.out.println("---------------construct start------------------");
            //判断构造方法的参数是否可变
            System.out.println("查看该构造方法是否允许带可变数量的参数：" + constructor.isVarArgs());

            //获取所有参数类型
            Class[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length > 0) {
                System.out.println("该构造方法的入口参数类型依次为：");
                for (Class parameterType : parameterTypes) {
                    System.out.print(parameterType + "\t");
                }
                System.out.println();
            } else {
                System.out.println("该构造方法无入口参数，为空构造方法");
            }

            //获取所有可能拋出的异常类型
            Class[] exceptionTypes = constructor.getExceptionTypes();
            if (exceptionTypes.length > 0) {
                System.out.println("该构造方法拋出的异常类型为：");
                for (Class exceptionType : exceptionTypes) {
                    System.out.print(exceptionType + "\t");
                }
                System.out.println();
            } else {
                System.out.println("该构造方法无拋出的异常类型");
            }

            //创建一个未实例化的 BasicReflect 类实例
            BasicReflect reflect1 = null;
            try {
                if (parameterTypes.length == 0 && exceptionTypes.length == 0) {
                    System.out.println("构造方法1");
                    //通过执行默认构造方法实例化 reflect1
                    reflect1 = (BasicReflect) constructor.newInstance();
                } else if (parameterTypes.length > 0 && exceptionTypes.length > 0) {
                    System.out.println("构造方法3");
                    //通过执行可变数量参数的构造方法实例化 reflect1
                    Object[] parameters = new Object[]{new String[]{"3", "201903"}};
                    reflect1 = (BasicReflect) constructor.newInstance(parameters);
                } else {
                    System.out.println("构造方法2");
                    //通过执行带两个参数的构造方法实例化 reflect1
                    reflect1 = (BasicReflect) constructor.newInstance(2, "BasicReflect反射机制");
                }
            } catch (Exception e) {
                System.out.println("在创建对象时拋出异常，下面执行 setAccessible() 方法");
                //如果该构造方法的权限为 private，默认为不允许通过反射利用 newInstance()方法创建对象
                //先执行该方法，并将入口参数设置为 true，则允许创建对象
                constructor.setAccessible(true);
            }
            if (reflect1 != null) {
                System.out.println(reflect1);
                System.out.println("---------------construct end------------------");
            }


        }
    }

    //通过反射执行方法（获取方法）
    private static void methodFromReflect(){
        //获取动态类
        BasicReflect reflect = new BasicReflect();
        Class reflectClass = reflect.getClass();

        //获取 reflectClass 类的所有方法
        Method[] declaredMethods= reflectClass.getDeclaredMethods();

        System.out.println(declaredMethods.length);
        for (Method method: declaredMethods) {
            System.out.println("---------------method start------------------");

            System.out.println("方法名称为：" + method.getName());
            System.out.println("方法是否带有可变数量的参数：" + method.isVarArgs());
            //获取所有参数类型
            Class[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length > 0) {
                System.out.println("该方法的入口参数类型依次为：");
                for (Class parameterType : parameterTypes) {
                    System.out.print(parameterType + "\t");
                }
                System.out.println();
            } else {
                System.out.println("该方法无入口参数，为空方法");
            }
            //获取返回值类型
            System.out.println("方法的返回值类型为：" + method.getReturnType());

            //获取所有可能拋出的异常类型
            Class[] exceptionTypes = method.getExceptionTypes();
            if (exceptionTypes.length > 0) {
                System.out.println("该方法拋出的异常类型为：");
                for (Class exceptionType : exceptionTypes) {
                    System.out.print(exceptionType + "\t");
                }
                System.out.println();
            } else {
                System.out.println("该方法无拋出的异常类型");
            }

            try {
                switch (method.getName()) {
                    case "staticMethod":
                        //调用没有参数的方法
                        method.invoke(reflect);
                        break;
                    case "publicMethod":     //调用一个参数的方法
                        System.out.println("publicMethod(10)的返回值为：" + method.invoke(reflect, 10));
                        break;
                    case "protectedMethod":     //调用两个参数的方法
                        System.out.println("protectedMethod(\"10\",15)的返回值为：" + method.invoke(reflect, "10", 15));
                        break;
                    case "privateMethod":     //调用可变数量参数的方法
                        Object[] parameters = new Object[]{new String[]{"J", "A", "V", "A"}};
                        System.out.println("privateMethod()的返回值为：" + method.invoke(reflect, parameters));
                        break;
                }
            } catch (Exception e){
                System.out.println("在设置成员变量值时抛出异常，下面执行setAccessible()方法");
                //如果该成员变量的访问权限为private，则抛出异常
                method.setAccessible(true);    //设置为允许访问private方法
            }

            System.out.println("---------------method end------------------");
        }

    }

    //通过反射访问成员变量
    private static void filedFromReflect(){
        //获取动态类
        BasicReflect reflect = new BasicReflect();
        Class reflectClass = reflect.getClass();

        //获取 reflectClass 类的所有成员
        Field[] declaredFields = reflectClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("---------------field start------------------");

            //获取类中的成员变量
            System.out.println("成员名称为：" + field.getName());
            Class fieldType = field.getType();
            System.out.println("成员类型为：" + field.getType());

            try {

                System.out.println("修改前成员的值为：" + field.get(reflect));
                //判断成员类型是否为int
                if(fieldType.equals(int.class)) {
                    System.out.println("利用setInt()方法修改成员的值");
                    field.setInt(reflect, 100);
                } else if(fieldType.equals(float.class)) {    //判断成员变量类型是否为float
                    System.out.println("利用setFloat()方法修改成员的值");
                    field.setFloat(reflect, 29.815f);
                } else if(fieldType.equals(boolean.class)) {    //判断成员变量是否为boolean
                    System.out.println("利用setBoolean()方法修改成员的值");
                    field.setBoolean(reflect, true);
                } else {
                    System.out.println("利用set()方法修改成员的值");
                    field.set(reflect, "Java编程");
                }
                System.out.println("修改后成员的值为：" + field.get(reflect));

            } catch (Exception e){
                System.out.println("在设置成员变量值时抛出异常，下面执行setAccessible()方法");
                //如果该成员变量的访问权限为private，则抛出异常
                field.setAccessible(true);    //设置为允许访问private方法
            }

            System.out.println("---------------field end------------------");

        }
    }

    private int id;
    String name;
    int number;
    public float value;
    protected boolean isNeed;

    //空的构造方法
    private BasicReflect(){
        //this.id = 1;
        //this.name = "反射机制";
        //this.number = 201901;
    }

    //带两个参数的构造方法
    protected BasicReflect(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //带可变参数的构造方法
    public BasicReflect(String...strings)throws NumberFormatException {
        if(0 < strings.length)
            id = Integer.parseInt(strings[0]);
        if(1 < strings.length)
            number = Integer.parseInt(strings[1]);
    }

    //static 作用域方法
    static void staticMethod() {
        System.out.println("执行staticMethod()方法");
    }

    //public 作用域方法
    public int publicMethod(int i) {
        System.out.println("执行publicMethod()方法");
        return 100+i;
    }

    //protected 作用域方法
    protected int protectedMethod(String s, int i)throws NumberFormatException {
        System.out.println("执行protectedMethod()方法");
        return Integer.parseInt(s) + i;
    }

    //private 作用域方法
    private String privateMethod(String ...strings) {
        System.out.println("执行privateMethod()方法");

        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "id: " + id + " ,name: " + name + " ,number: " + number;
    }
}
