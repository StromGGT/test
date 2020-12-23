package com.qunar.fin.algorithm.sort;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/11/12 10:53
 */
public class MergeSort {

    /**
     * 3,7,5,5,1,6,1,9,5,7,8,9,3,2,0
     */
    public static void main(String[] args) {
        List<Integer> list = SortUtil.LIST;
        System.out.println(mergeSort(list));
    }

    private static List<Integer> mergeSort(List<Integer> list) {
        if (list.size() < 2) {
            return list;
        }
        int mid = list.size() / 2;
        return merge(mergeSort(list.subList(0, mid)), mergeSort(list.subList(mid, list.size())));
    }

    private static List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> list = Lists.newArrayListWithCapacity(list1.size() + list2.size());
        for (int index = 0, i = 0, j = 0; index < list1.size() + list2.size(); index++) {
            if (i >= list1.size()) {
                list.add(index, list2.get(j++));
            } else if (j >= list2.size()) {
                list.add(index, list1.get(i++));
            } else if (list1.get(i) < list2.get(j)) {
                list.add(index, list1.get(i++));
            } else {
                list.add(index, list2.get(j++));
            }
        }
        System.out.println(list);
        return list;
    }
}
