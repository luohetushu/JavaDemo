package com.mytest.basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-06
 */
public class BasicArray {
    public static void main(String[] args) {

        double[][] class_score = { { 100, 99, 99 }, { 100, 98, 97 }, { 100, 100, 99.5 }, { 99.5, 99, 98.5 } };
        System.out.println(Arrays.deepToString(class_score));
        char[] src = new char[]{'H', 'e', 'l', 'l', 'o'};
        System.out.println(new String(src));

        //不规则数组
        initIrregularArray();

        System.out.println(Arrays.binarySearch(src, 'H'));  //0
        System.out.println(Arrays.binarySearch(src, 'h'));  //-3

        int[] binArray = new int[]{10, 8, 6, 27, 34, 5, 23, 17};
        System.out.println(Arrays.binarySearch(binArray, 17));  //-4  调用该方法时要求数组中元素己经按升序排列，这样才能得到正确结果
        Arrays.sort(binArray);  //升序
        System.out.println(Arrays.toString(binArray));   //[5, 6, 8, 10, 17, 23, 27, 34]
        System.out.println(Arrays.binarySearch(binArray, 17));  //4
        Integer[] teArray = new Integer[]{8, 18, 23, 4, 23, 45, 62};
        //Arrays.sort(teArray, Collections.reverseOrder()); //降序
        Arrays.sort(teArray, new MyComparator());  //降序
        System.out.println(Arrays.toString(teArray));  //[62, 45, 23, 23, 18, 8, 4]



        Arrays.parallelPrefix(binArray, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                // left 代表数组中前一个索引处的元素，计算第一个元素时，left为1
                // right代表数组中当前索引处的元素
                return left + right;
            }
        });
        System.out.println(Arrays.toString(binArray)); //[5, 11, 19, 29, 46, 69, 96, 130]

        //int[] arr3 = Arrays.copyOfRange(binArray, 1, 6);
        int[] arr3 = Arrays.copyOf(binArray, 5);
        //int[] arr3 = binArray.clone();  //[5, 11, 19, 29, 46, 69, 96, 130]
        System.out.println(Arrays.toString(arr3));  //[11, 19, 29, 46, 69]
        Arrays.parallelSetAll(arr3, new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                // operand代表正在计算的元素索引
                return operand;
            }
        });
        System.out.println(Arrays.toString(arr3));  //[0, 1, 2, 3, 4]

    }

    //不规则数组
    private static void initIrregularArray(){
        //如：int intArray[][] = {{1,2}, {11}, {21,22,23}, {31,32,33}};
        int[][] intArray = new int[4][]; // 先初始化高维数组为4
        // 逐一初始化低维数组
        intArray[0] = new int[2];
        intArray[1] = new int[1];
        intArray[2] = new int[3];
        intArray[3] = new int[3];
        // for循环遍历
        for (int i = 0; i < intArray.length; i++) {
            for (int j = 0; j < intArray[i].length; j++) {
                intArray[i][j] = i + j;
            }
        }
        // for-each循环遍历
        for (int[] row : intArray) {
            for (int column : row) {
                System.out.print(column);
                // 在元素之间添加制表符，
                System.out.print('\t');
            }
            // 一行元素打印完成后换行
            System.out.println();
        }
    }

    //使用二分法查询 key 元素值在 a 数组中出现的索引
    private static int binarySearch0(char[] a, int fromIndex, int toIndex,
                                     char key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            char midVal = a[mid];

            if (midVal < key) {
                low = mid + 1;
                System.out.println("low=" + low);
            }
            else if (midVal > key) {
                high = mid - 1;
                System.out.println("high=" + high);
            }
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    // 实现Comparator接口
    static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            /*
             * 如果o1小于o2，我们就返回正值，如果o1大于o2我们就返回负值， 这样颠倒一下，就可以实现降序排序了,反之即可自定义升序排序了
             */
            return o2 - o1;
        }
    }

}
