package com.mytest.demo;

import java.util.Arrays;

/**
 * @Author murongyunge
 * @Describe
 * @Date 2019-11-07
 */
public class SortDemo {

    public static void main(String[] args) {

        SortDemo sort = new SortDemo();

        //冒泡排序（Bubble Sort）
        sort.sortBubble();

        //快速排序（Quick sort）
        sort.sortQuick();

        //选择排序
        sort.selectSort();

        //直接插入排序
        sort.insertSort();

        //直接插入排序
        insert2Sort();

        //反转数组
        reverseArray();

    }

    /**
     * 冒泡排序（Bubble Sort）
     * 对比相邻的元素值，如果满足条件就交换元素值，把较小的元素值移动到数组前面，把大的元素值移动到数组后面
     * 冒泡排序在双层循环中实现，其中外层循环控制排序轮数，总循环次数为要排序数组的长度减 1。
     * 而内层循环主要用于对比相邻元素的大小，以确定是否交换位置，对比和交换次数依排序轮数而减少
     */
    private void sortBubble(){
        int[] sort = new int[]{32, 24, 56, 72, 98, 75, 48, 15, 65, 88};

        for (int i = 0; i < sort.length - 1; i++) {
            for (int j = 0; j < sort.length - 1 - i; j++) { //每一次将最大值放置末尾
                if (sort[j] > sort[j + 1]){
                    int temp = sort[j + 1];
                    sort[j + 1] = sort[j];
                    sort[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(sort)); //[15, 24, 32, 48, 56, 65, 72, 75, 88, 98]
    }

    /**
     * 快速排序（Quick sort）
     * 通过一趟排序，将要排序的数据分隔成独立的两部分，其中一部分的所有数据比另外一部分的所有数据都要小，
     * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此使整个数据变成有序序列
     */
    private void sortQuick(){
        int[] sort = new int[]{32, 24, 56, 72, 98, 75, 48, 15, 65, 88};

        //sort
        recurseSort(sort, 0, sort.length - 1);

        System.out.println(Arrays.toString(sort)); //[15, 24, 32, 48, 56, 65, 72, 75, 88, 98]
    }
    private void recurseSort(int[] list, int low, int high) {
        if(low < high) {
            int middle = getMiddle(list, low, high);    // 将list数组一分为二
            recurseSort(list, low, middle - 1);    // 对低字表进行递归排序
            recurseSort(list,middle + 1, high);    // 对高字表进行递归排序
        }
    }
    private int getMiddle(int[] list, int low, int high) {
        int tmp = list[low]; // 数组的第一个值作为中轴（分界点或关键数据）
        while (low < high) {
            while (low < high && list[high] > tmp) {
                high--;
            }
            list[low] = list[high]; // 比中轴小的记录移到低端
            while (low < high && list[low] < tmp) {
                low++;
            }
            list[high] = list[low]; // 比中轴大的记录移到高端
        }
        list[low] = tmp; // 中轴记录到尾
        return low; // 返回中轴的位置
    }

    /**
     * 选择排序
     * 指每一趟从待排序的数据元素中选出最大（或最小）的一个元素，顺序放在已排好序的数列的最后，直到全部待排序的数据元素排完
     */
    private void selectSort(){
        int[] sort = new int[]{32, 24, 56, 72, 98, 75, 48, 15, 65, 88};

        for(int i = 0; i < sort.length; i++) {
            int index = i;
            for(int j = i; j < sort.length; j++) {
                if(sort[j] < sort[index]) {
                    index = j;    //查找最小值
                }
            }
            int temp = sort[i];
            sort[i] = sort[index];
            sort[index] = temp;
        }

        System.out.println(Arrays.toString(sort)); //[15, 24, 32, 48, 56, 65, 72, 75, 88, 98]
    }

    /**
     * 直接插入排序
     * 将 n 个有序数存放在数组 a 中，要插入的数为 x，首先确定 x 插在数组中的位置 p，然后将 p 之后的元素都向后移一个位置，
     * 空出 a(p)，将 x 放入 a(p)，这样可实现插入 x 后仍然有序
     */
    private void insertSort(){
        int[] sort = new int[]{32, 24, 56, 72, 98, 75, 48, 15, 65, 88};

        int temp, j;
        for (int i = 1; i < sort.length; i++) {
            temp = sort[i];
            for (j = i - 1; j >= 0 && sort[j] > temp; j--) {
                sort[j + 1] = sort[j];
            }
            sort[j + 1] = temp;
        }

        System.out.println(Arrays.toString(sort)); //[15, 24, 32, 48, 56, 65, 72, 75, 88, 98]

    }

    /**
     * 直接插入排序
     * 将 n 个有序数存放在数组 a 中，要插入的数为 x，首先确定 x 插在数组中的位置 p，然后将 p 之后的元素都向后移一个位置，
     * 空出 a(p)，将 x 放入 a(p)，这样可实现插入 x 后仍然有序
     */
    private static void insert2Sort(){
        int[] sort = new int[]{32, 24, 56, 72, 98, 75, 48, 15, 65, 88};

        for (int i = 0, j = i; i < sort.length - 1; j = ++i) {
            int temp = sort[i + 1];
            while (temp < sort[j]) {
                sort[j + 1] = sort[j];
                if (j-- == 0) {
                    break;
                }
            }
            sort[j + 1] = temp;
        }

        System.out.println(Arrays.toString(sort)); //[15, 24, 32, 48, 56, 65, 72, 75, 88, 98]
    }

    private static void reverseArray(){
        int[] sort = new int[]{32, 24, 56, 72, 98, 75, 48, 15, 65, 88};

        int center = sort.length / 2;
        int head = 0;
        int foot = sort.length - 1;
        for (int i = 0; i < center; i++) {
            int temp = sort[head];
            sort[head] = sort[foot];
            sort[foot] = temp;
            head++;
            foot--;
        }

        System.out.println(Arrays.toString(sort));
    }

}
