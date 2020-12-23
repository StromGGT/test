package com.qunar.fin.algorithm.sort;

import java.util.List;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/11/11 20:40
 */
public class QuickSort {

    public static void main(String[] args) {
        List<Integer> list = SortUtil.LIST;
        quickSort(list, 0, list.size() - 1);
        System.out.println(list);
    }

    private static void quickSort(List<Integer> list, int low, int high) {
        if (low < high) {
            int pivot = getPivot(list, low, high);
            quickSort(list, low, pivot - 1);
            quickSort(list, pivot + 1, high);
        }

    }

    private static int getPivot(List<Integer> list, int low, int high) {
        int temp = list.get(low);
        while (low < high) {
            while (list.get(high) >= temp && high > low) {
                high--;
            }
            list.set(low, list.get(high));
            while (list.get(low) <= temp && low < high) {
                low++;
            }
            list.set(high, list.get(low));
        }
        list.set(low, temp);
        return low;
    }
}
