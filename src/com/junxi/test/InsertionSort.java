package com.junxi.test;


/**
 *
 * 插入排序的细节讲解与复杂度分析
 * 时间复杂度O(N^2)，额外空间复杂度O(1)
 * 此排序算法仅用于教学
 *
 */
public class InsertionSort {

    public static void insertionSort(int []arr){
        if(arr == null || arr.length <2){
            return;
        }
        for (int i=1;i<arr.length;i++){
            for (int j = i;j>0;j--){
                if(arr[j]>arr[j-1]){
                    break;
                }else {
                    swap(arr,j,j-1);
                }
            }
        }
    }

    public static void swap (int []arr,int i ,int j){
        arr[i] = arr[i]^arr[j];
        arr[j] = arr[i]^arr[j];
        arr[i] = arr[i]^arr[j];
    }

    public static void main(String[] args) {
        int[] a = {44,33,10,29,50,31};
        insertionSort(a);
        for (int i=0;i<a.length;i++) {
            System.out.print(a[i]+" ");
        }
    }

}
