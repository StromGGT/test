package com.qunar.fin.algorithm.sort;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/12/10 20:42
 */
public class CountingSort {

    private static int max = 0;

    private static int min = 0;

    public static void main(String[] args) {
        List<Integer> list = countingSort(SortUtil.LIST);
        System.out.println(list);
    }

    private static List<Integer> countingSort(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        //获取最大值和最小值
        getMaxAndMin(list);
        int basic = min;
        //根据最大值和最小值创建数组
        int[] countArr = new int[max - min + 1];

        //计算每个值的出现次数
        list.forEach(i -> countArr[i - basic] += 1);

        int index = 0;
        //排序
        for (int i = 0; i < countArr.length; ) {
            if (countArr[i] > 0) {
                list.set(index++, basic + i);
                countArr[i]--;
            }else {
                i++;
            }
        }
        return list;
    }

    private static void getMaxAndMin(List<Integer> list) {
        max = list.get(0);
        min = list.get(0);
        list.forEach(i -> {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        });
    }
}
