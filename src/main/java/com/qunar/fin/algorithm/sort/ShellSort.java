package com.qunar.fin.algorithm.sort;

import java.util.List;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/11/11 21:14
 */
public class ShellSort {

    /**
     * 3,7,5,5,1,6,1,9,5,7,8,9,3,2,0
     */
    public static void main(String[] args) {
        List<Integer> list = SortUtil.LIST;
        int step = list.size() / 2;
        while (step > 0 ) {
            System.out.println(step);
            for (int i = step; i < list.size(); i++) {
                int cur = list.get(i);
                int j = i;
                while (j - step >= 0 && list.get(j - step) > cur) {
                    list.set(j, list.get(j - step));
                    j -= step;
                }
                if (j != i) {
                    list.set(j, cur);
                }
                System.out.println(list);
            }
            step /= 2;
        }
        System.out.println(list);
    }
}
