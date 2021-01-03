package com.qunar.fin.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * 背包问题
 * 一个背包的总容量为V,现在有N类物品,第i类物品的重量为weight[i],价值为value[i]
 * 那么往该背包里装东西,怎样装才能使得最终包内物品的总价值最大。这里装物品主要由三种装法：
 * 1、0-1背包：每类物品最多只能装一次
 * 2、多重背包：每类物品都有个数限制，第i类物品最多可以装num[i]次
 * 3、完全背包：每类物品可以无限次装进包内
 * @author guotao.gou
 * @version 1.0
 * @date 2020/12/31 13:49
 */
public class knapsackProblem {

    /**
     * 背包容量
     */
    private static final int CAPACITY = 8;

    /**
     * 物品种类
     */
    private static final int TYPE = 5;

    /**
     * 每个物品的重量
     */
    private static final int[] WEIGHT = new int[]{6, 1, 5, 2, 1};

    /**
     * 每个物品的价值
     */
    private static final int[] VALUE = new int[]{48, 7, 40, 12, 8};

    /**
     * 每个物品可以使用的次数
     */
    private static final int[] TIME = new int[]{2, 3, 1, 3, 4};

    public static void main(String[] args) {
        zeroOneKnapsack(CAPACITY, TYPE, WEIGHT, VALUE);
        System.out.println();
        zeroOneKnapsack2(CAPACITY, TYPE, WEIGHT, VALUE);
        System.out.println();
        mayPack(CAPACITY, TYPE, WEIGHT, VALUE, TIME);
        System.out.println();
        completePack(CAPACITY, TYPE, WEIGHT, VALUE);
        System.out.println();
        completePack2(CAPACITY, TYPE, WEIGHT, VALUE);
    }

    /**
     * 0-1背包问题（二维数组解法）
     * @param capacity 背包容量
     * @param type 物品种类
     * @param weight 每个物品重量
     * @param value 每个物品价值
     * @return
     */
    private static int zeroOneKnapsack(int capacity, int type, int[] weight, int[] value) {
        //初始化动态数组
        int[][] dp = new int[type+1][capacity+1];

        for (int i = 1; i < type+1; i++) {
            for (int j = 1; j < capacity+1; j++) {
                //物品的总量大于当前背包容量，不放入物品
                if (weight[i-1] > j) {
                    //不放入物品，当前最大价值为前一个物品放入当前容量背包的最大价值
                    dp[i][j] = dp[i-1][j];
                } else {
                    /**
                     * dp[i-1][j]  不放入当前物品时的最大总价值
                     * dp[i-1][j-weight[i-1]] 如果放入当前物品，放入当前物品后的剩余空间在没放入当前物品之前的最大总价值
                     * value[i-1] 当前物品总价值
                     */
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i-1]] + value[i-1]);
                }
            }

        }

        printResult(dp);
        printZeroOnePack(dp);
        return dp[type][capacity];
    }

    /**
     * 0-1背包问题(一维数组解法)
     * @param capacity 背包容量
     * @param type 物品种类
     * @param weight 每个物品重量
     * @param value 每个物品价值
     * @return
     */
    private static int zeroOneKnapsack2(int capacity, int type, int[] weight, int[] value) {
        int[] dp = new int[capacity+1];

        for (int i = 1; i < type + 1; i++) {
            //背包容量大于等于当前物品的重量
            //倒序遍历的话dp[j-weight[i-1]]得出的就是前一种物品的最大价值
            //正序遍历的话dp[j-weight[i-1]]得出的有可能是已经放了当前物品的最大价值，就是完全背包问题
            for (int j = capacity; j >= weight[i-1]; j--) {
                /**
                 * dp[j]：当前物品不放入背包的最大价值
                 */
                dp[j] = Math.max(dp[j], dp[j-weight[i-1]] + value[i-1]);
            }
        }

        System.out.println(Arrays.toString(dp));
        return dp[capacity];
    }


    /**
     * 多重背包
     * @param capacity 背包容量
     * @param type 物品种类
     * @param weight 每个物品重量
     * @param value 每个物品价值
     * @param time 每个物品可以使用的次数
     * @return
     */
    private static int mayPack(int capacity, int type, int[] weight, int[] value, int[] time) {
        int[][] dp = new int[type+1][capacity+1];

        for (int i = 1; i < type + 1; i++) {
            for (int j = 1; j < capacity +1; j++) {
                //物品容量小于等于当前背包容量，则物品可以放入背包
                if (j >= weight[i-1]) {
                    //放几次
                    int count = Math.min(time[i-1], j/weight[i-1]);

                    for (int k = 0; k < count+1; k++) {
                        dp[i][j] = dp[i][j] > Math.max(dp[i-1][j], k*value[i-1] + dp[i-1][j - k*weight[i-1]])
                                ? dp[i][j] : Math.max(dp[i-1][j], k*value[i-1] + dp[i-1][j - k*weight[i-1]]);
                    }
                } else {
                    //当前物品不放入背包
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        printResult(dp);
        printManyPack(dp);
        return dp[type][capacity];
    }


    /**
     * 完全背包问题（二维数组解法）
     * @param capacity 背包容量
     * @param type 物品种类
     * @param weight 每个物品重量
     * @param value 每个物品价值
     * @return
     */
    private static int completePack(int capacity, int type, int[] weight, int[] value) {
        int[][] dp = new int[type+1][capacity+1];

        for (int i = 1; i < type+1; i++) {
            for (int j = 1; j < capacity+1; j++) {
                if (j < weight[i-1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-weight[i-1]] + value[i-1]);
                }
            }
        }

        printResult(dp);
        printCompletePack(dp);
        return dp[type][capacity];
    }

    /**
     * 完全背包问题（一维数组解法）
     * @param capacity 背包容量
     * @param type 物品种类
     * @param weight 每个物品重量
     * @param value 每个物品价值
     * @return
     */
    private static int completePack2(int capacity, int type, int[] weight, int[] value) {
        int[] dp = new int[capacity+1];

        for (int i = 1; i < type+1; i++) {
            //从当前物品的重量开始计算
            //倒序遍历的话dp[j-weight[i-1]]得出的就是前一种物品的最大价值
            //正序遍历的话dp[j-weight[i-1]]得出的有可能是已经放了当前物品的最大价值，就是完全背包问题
            for (int j = weight[i-1]; j < capacity+1; j++) {
                /**
                 * dp[j] 上一种物品在当前背包容量的最大价值
                 * dp[j-weight[i-1]] 当前背包容量减去当前物品的重量后的剩余重量的最大价值
                 */
                dp[j] = Math.max(dp[j], dp[j-weight[i-1]] + value[i-1]);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[capacity];
    }


    private static void printResult(int[][] result) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private static void printZeroOnePack(int[][] result) {
        System.out.println();
        int j = CAPACITY;
        for (int i = TYPE; i > 0 && j > 0; i--) {
            if (result[i][j] > result[i-1][j]) {
                System.out.print(i + " ");
                j -= WEIGHT[i-1];
            }
        }
        System.out.println();
    }

    private static void printManyPack(int[][] result) {
        System.out.println();
        int j = CAPACITY;
        for (int i = TYPE; i > 0 && j > 0; i--) {
            int time = TIME[i-1];
            while (result[i][j] > result[i-1][j] && time > 0) {
                System.out.print(i + " ");
                j -= WEIGHT[i-1];
                time--;
            }
        }
        System.out.println();
    }

    private static void printCompletePack(int[][] result) {
        System.out.println();
        int j = CAPACITY;
        for (int i = TYPE; i > 0 && j > 0; i--) {
            while (result[i][j] > result[i-1][j]) {
                System.out.print(i + " ");
                j -= WEIGHT[i-1];
            }
        }
        System.out.println();
    }
}















