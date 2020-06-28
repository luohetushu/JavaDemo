package com.mytest.basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class BasicCompare {
    public static void main(String[] args) {
        //对象排序
        Integer[] integers = new Integer[]{10, 2, 35, 48, 56};
        Arrays.sort(integers);
        System.out.println(Arrays.toString(integers));  //[2, 10, 35, 48, 56]

        //对象排序
        String[] strings = new String[]{"Decade", "OOO", "Drive", "Ghost", "W", "Fourze", "Wizard", "Build"};
        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings)); //[Build, Decade, Drive, Fourze, Ghost, OOO, W, Wizard]

        //自定义类对象的排序
        //注：任何一个自定义类默认情况下是无法使用系统内部的类实现数组排序或比较的
        //java.lang.ClassCastException: class com.mytest.basic.BasicCompare$Member
        //                              cannot be cast to class java.lang.Comparable
        Member[] members = new Member[]{
                new Member("Drive", 27),
                new Member("Ghost", 25),
                new Member("Wizard", 28),
                new Member("Build", 26)
        };
        Arrays.sort(members);
        System.out.println(Arrays.toString(members));

        //java.util.Comparator<T>, 用于没有实现 java.lang.Comparable<T> 接口的类的对象的排序
        Person[] people = new Person[]{
                new Person("Drive", 27),
                new Person("Ghost", 25),
                new Person("Wizard", 28),
                new Person("Build", 26)
        };
        Arrays.sort(people, new PersonComparator());
        System.out.println(Arrays.toString(people));

        //二叉树 存储数据 所有的数据都有序排列
        BinaryTree<Member> bTree = new BinaryTree<>();
        bTree.add(new Member("Drive", 30));
        bTree.add(new Member("Ghost", 20));
        bTree.add(new Member("Wizard", 25));
        bTree.add(new Member("Build", 10));
        bTree.add(new Member("Decade", 22));
        bTree.add(new Member("Kiva", 32));
        System.out.println(Arrays.toString(bTree.toArray()));
        //判读是否存在
        System.out.println(bTree.contains(new Member("Wizard", 28))); //false
        //删除数据
        bTree.remove(new Member("Drive", 30));
        System.out.println(Arrays.toString(bTree.toArray()));

    }

    enum Color{
        RED, BLACK;
    }

    //实现红黑树
    static class RedBlackTree<T extends Comparable<T>>{
        //内部 Node 类
        class Node{
            private T data; //当前节点保存的对象或数据
            private Node parent;  //当前节点父节点
            private Node left;    //当前节点左节点
            private Node right;   //当前节点右节点
            private Color color;  //当前节点的颜色
        }
    }

    //实现二叉树
    static class BinaryTree<T extends Comparable<T>>{

        //实现二叉树功能
        private Node root;  //根节点
        private int count;  //保存数据个数

        private Object[] returnData;  //以数组形式返回数据
        private int foot = 0; //脚标

        /**
         * 添加数据
         * @param data 要添加的数据
         * @Exception  NullPointerException   数据为空抛出的异常
         */
        public void add(Comparable<T> data){
            if (data == null){
                throw new NullPointerException("data can not be null!");
            }
            //将数据包装在 Node 类中，使其富有即节点的关系
            Node newNode = new Node(data);
            //
            if (root == null){
                this.root = newNode; //第一个节点作为根节点
            } else {
                //由内部类 Node 类实现数据的添加
                this.root.addNode(newNode);
            }
            this.count++;
        }

        /**
         * 删除指定的数据
         * @param data
         */
        public void remove(Comparable<T> data){
            if (this.root == null){
                return;
            }
            Node removeNode = this.root.getRemoveNode(data);
            if (removeNode != null){
                System.out.println("parent=" + removeNode.parent + "  left=" + removeNode.left + "   right=" + removeNode.right);
                if (this.root.data.compareTo((T)data) == 0){ //如果是根节点
                    // TODO: 2020/4/27 根节点也需要考虑有无左右子树的情况
                    Node moveNode = this.root.right;
                    boolean isHasLeft = false; //判断该删除的点的右子树是否有向左的节点
                    while (moveNode.left != null) {
                        moveNode = moveNode.left; //一直向左找，找到最左边的节点
                        isHasLeft = true;
                    }
                    if (isHasLeft) {
                        moveNode.parent.left = null; //断开原本链接
                    } else {
                        moveNode.parent.right = null;
                    }
                    moveNode.left = this.root.left;
                    moveNode.right = this.root.right;
                    this.root = moveNode;
                } else {
                    //情况一：删除的节点没有左右子树，直接删除，设置父节点为 null
                    if (removeNode.left == null && removeNode.right == null) {
                        System.out.println("情况一：删除的节点没有左右子树，直接删除，设置父节点为 null");
                        if (removeNode.parent != null) {
                            if (removeNode.data.compareTo((T) removeNode.parent.data) <= 0) {
                                removeNode.parent.left = null;
                            } else {
                                removeNode.parent.right = null;
                            }
                        }
                    }
                    //情况二：删除的节点只有左子树或右子树，则使用子树节点代替当前节点
                    else if (removeNode.left != null && removeNode.right == null) {
                        System.out.println("情况二：删除的节点只有左子树，则使用子树节点代替当前节点");
                        removeNode.left.parent = removeNode.parent;
                        if (removeNode.data.compareTo((T) removeNode.parent.data) <= 0) {
                            removeNode.parent.left = removeNode.left;
                        } else {
                            removeNode.parent.right = removeNode.left;
                        }
                    } else if (removeNode.left == null && removeNode.right != null) {
                        System.out.println("情况二：删除的节点只有右子树，则使用子树节点代替当前节点");
                        removeNode.right.parent = removeNode.parent;
                        if (removeNode.data.compareTo((T) removeNode.parent.data) <= 0) {
                            removeNode.parent.left = removeNode.right;
                        } else {
                            removeNode.parent.right = removeNode.right;
                        }
                    } else {
                        //情况三：删除的节点有左右子树，则将右子树的最左边的节点代替当前节点；
                        System.out.println("情况三：删除的节点有左右子树，则将右子树的最左边的节点代替当前节点；");
                        Node moveNode = removeNode.right;
                        boolean isHasLeft = false; //判断该删除的点的右子树是否有向左的节点
                        while (moveNode.left != null) {
                            moveNode = moveNode.left; //一直向左找，找到最左边的节点
                            isHasLeft = true;
                        }
                        if (removeNode.data.compareTo((T) removeNode.parent.data) <= 0) {
                            removeNode.parent.left = moveNode;
                        } else {
                            removeNode.parent.right = moveNode;
                        }
                        if (isHasLeft) {
                            moveNode.parent.left = null; //断开原本链接
                        } else {
                            moveNode.parent.right = null;
                        }
                        moveNode.parent = removeNode.parent;
                        moveNode.left = removeNode.left;
                        moveNode.right = removeNode.right;
                    }
                }
                this.count--;
            }
        }

        public boolean contains(Comparable<T> data){
            if (data == null || this.root == null) {
                return false;
            }
            return this.root.containsNode(data); //在 Node 内部类中递归判读数值是否存在
        }

        /**
         * 以对象数组的形式返回所有的数据
         * @return
         */
        public Object[] toArray(){
            if (this.count == 0){
                return null;
            }
            this.returnData = new Object[this.count];
            this.foot = 0;
            //在内部类 Node 类中实现数据的返回
            this.root.toArrayNode();
            return this.returnData;
        }


        //内部类 二叉树节点
        private class Node{
            private Comparable<T> data;  //存放 Comparable，进行大小比较 当前节点保存的数据
            private Node parent; //保存父节点
            private Node left;   //左子树
            private Node right;  //右子树

            public Node(Comparable<T> data) {
                this.data = data;  //当前节点
            }

            /**
             * 实现节点数据的添加
             * @param newNode 要保存的数据
             */
            public void addNode(Node newNode){
                if (newNode.data.compareTo((T) this.data) <= 0){ //比当前节点数据小
                    if (this.left == null) {
                        this.left = newNode;
                        newNode.parent = this; //保存父节点
                    } else {
                        //与左子树节点的数据进行比较
                        this.left.addNode(newNode);
                    }
                } else {
                    if (this.right == null) {
                        this.right = newNode;
                        newNode.parent = this; //保存父节点
                    } else {
                        //与右子树节点的数据进行比较
                        this.right.addNode(newNode);
                    }
                }
            }

            /**
             * 获取要删除的数据的节点对象
             * @param data
             * @return
             */
            public Node getRemoveNode(Comparable<T> data){
                if (this.data.equals(data)){
                    return this;
                }
                if (data.compareTo((T) this.data) <= 0) { //往左子树判断
                    if (this.left != null) {
                        return this.left.getRemoveNode(data);
                    }
                } else { //往右子树判断
                    if (this.right != null) {
                        return this.right.getRemoveNode(data);
                    }
                }
                return null;
            }

            /**
             * 在 Node 内部类中递归判读数值是否存在
             * @param data
             * @return
             */
            public boolean containsNode(Comparable<T> data){
                if (this.data.equals(data)){
                    return true;
                }
                if (data.compareTo((T) this.data) <= 0) { //往左子树判断
                    if (this.left != null) {
                        return this.left.containsNode(data);
                    }
                } else { //往右子树判断
                    if (this.right != null) {
                        return this.right.containsNode(data);
                    }
                }
                return false;

            }

            /**
             * 采用中序遍历(左-根-右)形式，实现所有数据获取，获取的数据已自动升序
             */
            public void toArrayNode(){
                if (this.left != null){ //左子树
                    this.left.toArrayNode(); //递归
                }
                //当前节点 根
                BinaryTree.this.returnData[BinaryTree.this.foot ++] = this.data;
                if (this.right != null){ //右子树
                    this.right.toArrayNode(); //递归
                }
            }

            @Override
            public int hashCode() {
                return Objects.hash(data);
            }
        }
    }

    //实现 java.lang.Comparable<T> 接口
    static class Member implements Comparable<Member>{
        String name;
        int age;

        public Member(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Member member) {
            return this.age - member.age;
            //return this.name.compareTo(member.name);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Member member = (Member) o;
            return age == member.age &&
                    Objects.equals(name, member.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "Member{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}' + "\n";
        }

    }

    //定义排序规则类，该类实现 java.util.Comparator<T> 接口
    static class PersonComparator implements Comparator<Person>{

        @Override
        public int compare(Person person1, Person person2) {
            return person1.getAge() - person2.age;
            //return person1.getName().compareTo(person2.getName());
        }
    }

    //没有实现 java.lang.Comparable<T> 接口，若要想实现比较，需要定义排序规则类，该类实现 java.util.Comparator<T> 接口
    static class Person{
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}' + "\n";
        }

    }


}
