package com.mytest.basic;

import java.util.*;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-15
 */
public class BasicGather<T extends List> {
    public static void main(String[] args){
        List<String> lists = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        HashSet<String> hashSet = new HashSet<>();

        Queue<String> queue = new LinkedList<>();

        

        //实例化使用ArrayList的泛型类BasicGather，正确
        BasicGather<ArrayList> lc1 = new BasicGather<ArrayList>();
        //实例化使用LinkedList的泛型类BasicGather，正确
        BasicGather<LinkedList> lc2 = new BasicGather<LinkedList>();
        //实例化使用HashMap的泛型类BasicGather，错误，因为HasMap没有实现List接口
        //BasicGather<HashMap> lc3 = new BasicGather<HashMap>();

        //类型通配符
        BasicGather <? extends List> a = null;
        a = new BasicGather<ArrayList> ();    //正确
        a = new BasicGather<LinkedList> ();    //正确
        //a = new BasicGather<HashMap> ();    //错误

        //values() 方法可以将枚举的所有成员以数组形式返回
        for(int i = 0; i < Signal.values().length; i++) {
            //ordinal()	获取枚举成员的索引位置
            System.out.println("枚举成员：索引：" + Signal.values()[i].ordinal() +  ", 值：" + Signal.values()[i]);
            //valueOf()	    将普通字符串转换为枚举实例
            //compareTo()	比较两个枚举成员在定义时的顺序
            System.out.println(Signal.valueOf("RED") + "与" + Signal.values()[i] + "的比较结果是："
                    + Signal.valueOf("RED").compareTo(Signal.values()[i]));
        }

        for(WeekDay day: EnumSet.range(WeekDay.Tue, WeekDay.Sat)) {
            System.out.println(day + "====>" + day.getDay());
        }
        WeekDay.printDay(5);

        BitSet bits1 = new BitSet();
        BitSet bits2 = new BitSet(16);
        // set some bits
        for(int i = 0; i < 16; i++) {
            if((i % 2) == 0) bits1.set(i);
            if((i % 5) != 0) bits2.set(i);
        }
        System.out.println("Initial pattern in bits1: ");
        System.out.println(bits1);
        System.out.println("Initial pattern in bits2: ");
        System.out.println(bits2);
        // OR bits
        bits2.andNot(bits1);
        System.out.println("bits2 andNot bits1: ");
        System.out.println(bits2);
        System.out.println(bits2.length());
        System.out.println(bits2.cardinality());


        Vector vector1 = new Vector();
        System.out.println(vector1.capacity());
        Vector vector2 = new Vector(2, 2);
        System.out.println(vector2.capacity());  //2
        for (int i = 0; i < 5; i++) {
            vector2.addElement(i);
        }
        System.out.println(vector2.capacity());   //4
        vector2.setSize(3);
        System.out.println(vector2);
        Vector<String> dayNames = new Vector<>();
        dayNames.add("Sunday");
        dayNames.add("Monday");
        dayNames.add("Tuesday");
        dayNames.add("Wednesday");
        dayNames.add("Thursday");
        dayNames.add("Friday");
        dayNames.add("Saturday");
        Enumeration<String> days = dayNames.elements();
        while (days.hasMoreElements()){
            System.out.println(days.nextElement());
        }

        Properties properties1 = new Properties();
        properties1.put("Illinois", "Springfield");
        properties1.put("Missouri", "Jefferson City");
        properties1.put("Washington", "Olympia");
        properties1.put("California", "Sacramento");
        properties1.setProperty("Indiana", "Indianapolis");
        Properties properties2 = new Properties(properties1);
        System.out.println(properties2.size());  //0
//        Set set = properties1.keySet();
//        Iterator iterator = set.iterator();  //迭代器
//        while (iterator.hasNext()){
//            String str = (String) iterator.next();
//            System.out.println("The capital of " + str + " is " + properties1.getProperty(str) + ".");
//        }
        //或
        for (Object o : properties1.keySet()) {
            String str = (String) o;
            System.out.println("The capital of " + str + " is " + properties1.getProperty(str) + ".");
        }
        //或
//        Enumeration names = properties1.propertyNames();
//        while (names.hasMoreElements()){
//            String str = (String) names.nextElement();
//            System.out.println("The capital of " + str + " is " + properties1.getProperty(str) + ".");
//        }

        Stack<String> stack = new Stack<>();
        stack.push("W");
        stack.push("Zio");
        System.out.println(stack.pop());
        //Enumeration<String> knights = stack.elements();

        BasicGather basic = new BasicGather();

        //打印成绩
        basic.printScores();

        //打印信息
        basic.printRider();

    }

    //打印成绩
    private void printScores(){
        //创建 TreeSet 集合
        TreeSet<Double> scores=new TreeSet<Double>();

        //将成绩添加到TreeSet集合中，并自动进行从低到高的排列
        scores.add(98.5);
        scores.add(67.5);
        scores.add(88.0);
        scores.add(48.0);
        scores.add(56.5);
        scores.add(78.5);

        Iterator<Double> it = scores.iterator();    //创建 Iterator 对象
        System.out.println("成绩从低到高的排序为：");
        while(it.hasNext()) {
            System.out.print(it.next() + "\t");
        }

        //查询不及格的成绩
        SortedSet<Double> score1 = scores.headSet(60.0);
        Iterator<Double> it1 = score1.iterator();
        System.out.println("\n不及格的成绩有：");
        while (it1.hasNext()){
            System.out.print(it1.next() + "\t");
        }
        //查询90分以上的成绩
        SortedSet<Double> score2 = scores.tailSet(90.0);
        System.out.println("\n90 分以上的成绩有：");
        for(int i = 0; i < score2.toArray().length; i++) {
            System.out.print(score2.toArray()[i] + "\t");
        }

    }

    //打印骑士
    private void printRider(){
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(20172018, "Build");
        hashMap.put(20092009, "Decade");
        hashMap.put(20142015, "Drive");
        hashMap.put(20162017, "Ex-Aid");
        hashMap.put(20112012, "Fourze");
        hashMap.put(20152016, "Ghost");
        hashMap.put(20082009, "Kiva");
        hashMap.put(20102011, "OOO");
        hashMap.put(20122013, "Wizard");

        System.out.println("\n******** 骑士列表 ********");
        //第一种：普遍使用，二次取值
        System.out.println("通过Map.keySet遍历key和value：");
        for (Integer key : hashMap.keySet()) {
            System.out.println("年份：" + key + "，骑士：" + hashMap.get(key));
        }

        //第二种
//        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
//        Iterator<Map.Entry<Integer, String>> it = hashMap.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<Integer, String> entry = it.next();
//            System.out.println("年份：" + entry.getKey() + "，骑士：" + entry.getValue());
//        }
        //或
//        Iterator itHash = hashMap.keySet().iterator();
//        while (itHash.hasNext()){
//            int key = (int) itHash.next();
//            System.out.println("年份：" + key + "，骑士：" + hashMap.get(key));
//        }

        //第三种：推荐，尤其是容量大时
//        System.out.println("通过Map.entrySet遍历key和value");
//        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
//            System.out.println("年份：" + entry.getKey() + "，骑士：" + entry.getValue());
//        }


        TreeMap<Integer, String> treeMap = new TreeMap<>(hashMap);  //对键对象进行排序
        System.out.println("******** 骑士列表 ********");
        for (Integer integer : treeMap.keySet()) {
            System.out.println("年份：" + integer + "，骑士：" + hashMap.get(integer));
        }

    }

    //定义泛型方法
    public static <T> void List(T book) {
        if(book != null) {
            System.out.println(book);
        }
    }

    // 泛型方法 printArray
    public static <E> void printArray(E[] inputArray) {
        // 输出数组元素
        for (E element : inputArray){
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    // 比较三个值并返回最大值
    //声明一个有界的类型参数，首先列出类型参数的名称，后跟extends关键字，最后紧跟它的上界
    public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x; // 假设x是初始最大值
        if ( y.compareTo( max ) > 0 ){
            max = y; //y 更大
        }
        if ( z.compareTo( max ) > 0 ){
            max = z; // 现在 z 更大
        }
        return max; // 返回最大对象
    }

    //声明枚举
    enum Signal{
        //定义一个枚举类型
        GREEN, YELLOW, RED
    }

    //枚举添加内置方法
    enum WeekDay{
        Mon("Monday"),Tue("Tuesday"),Wed("Wednesday"),Thu("Thursday"),Fri("Friday"),Sat("Saturday"),Sun("Sunday");
        //以上是枚举的成员，必须先定义，而且使用分号结束
        private final String day;

        WeekDay(String day) {
            this.day = day;
        }


        public String getDay() {
            return day;
        }

        public static void printDay(int i) {
            switch(i) {
                case 1:
                    System.out.println(WeekDay.Mon);
                    break;
                case 2:
                    System.out.println(WeekDay.Tue);
                    break;
                case 3:
                    System.out.println(WeekDay.Wed);
                    break;
                case 4:
                    System.out.println(WeekDay.Thu);
                    break;
                case 5:
                    System.out.println(WeekDay.Fri);
                    break;
                case 6:
                    System.out.println(WeekDay.Sat);
                    break;
                case 7:
                    System.out.println(WeekDay.Sun);
                    break;
                default:
                    System.out.println("wrong number!");
            }
        }

    }


    //定义数据库类型枚举
    enum DataBaseType {
        MYSQL, ORACLE, DB2, SQLSERVER
    }

    //某类中定义的获取数据库URL的方法以及EnumMap的声明
    private EnumMap<DataBaseType, String> urls = new EnumMap<>(DataBaseType.class);
    private BasicGather(){
        urls.put(DataBaseType.DB2, "jdbc:db2://localhost:5000/sample");
        urls.put(DataBaseType.MYSQL, "jdbc:mysql://localhost/mydb");
        urls.put(DataBaseType.ORACLE, "jdbc:oracle:thin:@localhost:1521:sample");
        urls.put(DataBaseType.SQLSERVER, "jdbc:microsoft:sqlserver://sql:1433;Database=mydb");
    }

    /**
     * 根据不同的数据库类型，返回对应的URL
     * @param type DataBaseType 枚举类新实例
     * @return
     */
    public String getURL(DataBaseType type)
    {
        return this.urls.get(type);
    }

}


