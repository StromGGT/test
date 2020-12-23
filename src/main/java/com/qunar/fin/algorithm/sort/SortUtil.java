package com.qunar.fin.algorithm.sort;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/11/11 19:25
 */
class SortUtil {

    static List<Integer> LIST = Lists.newArrayList(3,7,5,5,1,6,1,9,5,7,8,9,3,2,0);

    static void swap(List<Integer> list, int index1, int index2) {
        int temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
