package com.qunar.fin.algorithm.sort;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 基数排序
 * @author guotao.gou
 * @version 1.0
 * @date 2020/12/28 21:20
 */
public class RadixSort {

    public static void main(String[] args) {
        List<Integer> list = SortUtil.LIST;
        list.add(101);
        list.add(125);
        list.add(23);
        list.add(34);
        List<Integer> result = radixSort(list);
        System.out.println(result);

    }

    private static List<Integer> radixSort(List<Integer> list) {
        //获取最大值
        int max = list.get(0);
        for (Integer i : list) {
            if (i > max) {
                max = i;
            }
        }

        //获取最大值的位数，最大值有几位，就循环几次
        int count = 0;
        while(max > 0) {
            max /= 10;
            count++;
        }

        //初始化桶（0-9 共10个桶）
        ArrayList<List<Integer>> bucketList = Lists.newArrayListWithExpectedSize(10);
        for (int i = 0; i < 10; i++) {
            bucketList.add(Lists.newArrayList());
        }

        int m = 1, n = 10;
        //排序个位、十位、百位....
        for (int i = 0; i < count; i++,  m *= 10) {
            for (int j = 0; j < list.size(); j++) {
                int position = list.get(j) == 0 ? 0 : (list.get(j) / m) % n;
                bucketList.get(position).add(list.get(j));
            }

            //排序
            list.clear();
            bucketList.forEach(list1 -> {
                list.addAll(list1);
                list1.clear();
            });
        }
        return list;
    }
}
