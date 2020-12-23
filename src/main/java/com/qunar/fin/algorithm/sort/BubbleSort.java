package com.qunar.fin.algorithm.sort;

import java.util.List;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/11/11 18:58
 */
public class BubbleSort {

    public static void main(String[] args) {
        List<Integer> list = SortUtil.LIST;
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j) < list.get(j - 1)) {
                    SortUtil.swap(list, j, j-1);
                }
            }
        }
        System.out.println(list);
    }

}
