package com.qunar.fin.algorithm.sort;

import java.util.List;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/11/11 19:24
 */
public class SelectionSort {

    public static void main(String[] args) {
        List<Integer> list = SortUtil.LIST;
        for (int i = 0; i < list.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j) < list.get(minIndex)) {
                    minIndex = j;
                }
            }
            SortUtil.swap(list, i, minIndex);
        }
        System.out.println(list);
    }
}
