package com.mytest.basic;

import java.util.Objects;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-05
 */
public class BasicPackage {

    public static void main(String[] args) {
        //通过 Class 对象可以获取该类的各种信息
        printClassInfo("");

        int num = 40;
        System.out.println(num + "的二进制数是：" + Integer.toBinaryString(num));
        System.out.println(num + "的八进制数是：" + Integer.toOctalString(num));
        System.out.println(num + "的十六进制数是：" + Integer.toHexString(num));
        System.out.println(Integer.compare(num, 50));  //-1

        Character charA = 'A';  //@code 65
        System.out.println(charA.hashCode());  //65
        char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (char ch : chars) {
            System.out.print(Character.hashCode(ch) + "\t");  //48	49	50	51	52	53	54	55	56	57
        }
        System.out.println();
        System.out.println(charA.compareTo('S'));  //@code 'A' - 'S'   -18
        System.out.println('A' - 'S');  //-18
        System.out.println(charA.compareTo('7'));  //@code 'A' - '7'   10
        System.out.println(Character.getType('B'));  //Character#UPPERCASE_LETTER 1
        System.out.println(Character.getType('8'));  //Character#DECIMAL_DIGIT_NUMBER 9

        System.out.println(Boolean.logicalXor(true, false));   //true

        //字节型	    byte	1 字节	-128~127
        byte m_byte = (byte) 380;
        System.out.println(m_byte);   //(byte) 129 == -127   (byte) 380 == 124 (-128 + 380 - 128 = 124)
        // num < 128 (byte) num = num
        // num >= 128 * 1 && num < 128 * 3  (byte) num = num - 128 * 2
        // num >= 128 * 3 && num < 128 * 5  (byte) num = num - 128 * 4
        System.out.println(Byte.compare(m_byte, (byte) 2));  //122

        char[] src = new char[]{'H', 'e', 'l', 'l', 'o'};
        char[] dest = new char[]{'W', 'o', 'r', 'i', 'd', '!'};
        System.arraycopy(src, 3, dest, 3, 1);
        System.out.println(new String(src) + "\t" + new String(dest));  //Hello	World!

        System.out.println("Java 运行时环境版本：" + System.getProperty("java.version"));
        System.out.println("当前操作系统是：" + System.getProperty("os.name"));
        System.out.println("当前用户是：" + System.getProperty("user.name"));

        //对象的克隆
        Ship shipA = new Ship("YiLi", 5);
        System.out.println(shipA);
        try {
            Ship shipB = (Ship) shipA.clone();
            System.out.println(shipB);
            System.out.println(shipA == shipB); //false
            System.out.println(shipA.equals(shipB)); //false
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }

    //通过 Class 对象可以获取该类的各种信息
    public static void printClassInfo(Object obj) {
        //获取类名
        System.out.println("类名：" + obj.getClass().getName());
        //获取父类名
        System.out.println("父类：" + obj.getClass().getSuperclass().getName());
        System.out.println("实现的接口有：");
        //获取实现的接口并输出
        for(int i = 0; i < obj.getClass().getInterfaces().length ; i++) {
            System.out.println(obj.getClass().getInterfaces()[i]);
        }
    }

    //类的克隆
    static class Ship implements Cloneable{

        String name;
        int age;

        public Ship(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Ship ship = (Ship) o;
            return age == ship.age &&
                    Objects.equals(name, ship.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "Ship" + super.toString() + "{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone(); //要想在主方法中调用 clone 方法，此处需要覆写
        }
    }

}
