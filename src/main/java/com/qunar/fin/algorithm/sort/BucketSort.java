package com.qunar.fin.algorithm.sort;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/12/16 21:08
 */
public class BucketSort {

    /**
     * 3,7,5,5,1,6,1,9,5,7,8,9,3,2,0
     */
    public static void main(String[] args) {
//        List<Integer> result = insertSort(SortUtil.LIST);
        List<Integer> result = bucketSort(SortUtil.LIST, 5);
        System.out.println(result);
    }

    /**
     * @param list 待排序的数组
     * @param bucketSize 每个桶的容量
     * @return 排好序的数组
     */
    private static List<Integer> bucketSort(List<Integer> list, int bucketSize) {
        int max = list.get(0);
        int min = list.get(0);

        //获取最大，最小值
        for (Integer i : list) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }

        //计算桶的数量
        int bucketCount = (max - min) / bucketSize + 1;

        //初始化桶
        List<List<Integer>> bucket = Lists.newArrayListWithCapacity(bucketCount);
        List<Integer> result = Lists.newArrayList();

        for (int i = 0; i < bucketCount; i++) {
            bucket.add(Lists.newArrayList());
        }

        //将元素放入桶中
        for (Integer i : list) {
            int index = (i - min) / bucketSize;
            bucket.get(index).add(i);
        }

        //遍历桶并对每个桶中元素进行排序
        for (List<Integer> integerList : bucket) {
            if (integerList.size() < 1) {
                continue;
            }
         insertSort(integerList);
        }

        bucket.forEach(result::addAll);
        return result;
    }


    private static List<Integer> insertSort(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int cur = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j) > cur) {
                list.set(j + 1, list.get(j));
                j--;
            }
            if (i != j+1) {
                list.set(j+1, cur);
            }
        }
        return list;
    }

}
