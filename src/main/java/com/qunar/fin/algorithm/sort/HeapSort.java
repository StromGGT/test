package com.qunar.fin.algorithm.sort;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/12/6 22:43
 */
public class HeapSort {

    private static int length;

    /**
     * 3,7,5,5,1,6,1,9,5,7,8,9,3,2,0
     */
    public static void main(String[] args) {
        List<Integer> list = heapSort(Lists.newArrayList(SortUtil.LIST));
        System.out.println(list);
    }

    private static List<Integer> heapSort(List<Integer> list) {
        length = list.size();
        if (length < 1) {
            return list;
        }
        //创建堆
        buildHeap(list);
        while (length > 0) {
            SortUtil.swap(list, 0, length-1);
            length--;
            //调整堆
            adjustHeap(list, 0);
        }
        return list;
    }

    private static void buildHeap(List<Integer> list) {
        for (int i = (list.size() / 2 - 1); i >= 0; i--) {
            //调整堆
            adjustHeap(list, i);
        }
    }

    private static void adjustHeap(List<Integer> list, int index) {
        if (index < 0) {
            return;
        }
        //当前节点为最大节点
        int maxIndex = index;
        //如果有左子树，并且最大节点的值小于左子树，最大节点指向左子树
        if ((2* index + 1) < length && list.get(2* index + 1) > list.get(maxIndex)) {
            maxIndex = 2 * index + 1;
        }
        //如果有右子树，并且最大节点的值小于右子树，最大节点指向右子树
        if ((2 * index + 2) < length && list.get(2 * index + 2) > list.get(maxIndex)) {
            maxIndex = 2 * index + 2;
        }
        //最大节点不是当前节点，交换，然后递归前面的节点
        if (maxIndex != index) {
            SortUtil.swap(list, index, maxIndex);
            //调整子树成为大顶堆
            adjustHeap(list, maxIndex);
        }
    }
}
