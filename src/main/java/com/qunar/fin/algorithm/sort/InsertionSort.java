package com.qunar.fin.algorithm.sort;

import java.util.List;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/11/11 19:37
 */
public class InsertionSort {

    public static void main(String[] args) {
        List<Integer> list = SortUtil.LIST;
        for (int i = 1; i < list.size(); i++) {
            int cur = list.get(i);
            int j = i;
            for (; j > 0; j--) {
                if (list.get(j - 1) <= cur) {
                    break;
                }
                list.set(j, list.get(j - 1));
            }
            if (j != i) {
                list.set(j, cur);
            }
        }
        System.out.println(list);
    }

}
