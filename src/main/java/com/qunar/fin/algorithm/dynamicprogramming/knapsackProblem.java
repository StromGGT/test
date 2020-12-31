package com.qunar.fin.algorithm.dynamicprogramming;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/12/31 13:49
 */
public class knapsackProblem {

    private static final int CAPACITY = 8;

    private static final int TYPE = 5;

    private static final int[] WEIGHT = new int[]{6, 1, 5, 2, 1};

    private static final int[] VALUE = new int[]{48, 7, 40, 12, 8};

    public static void main(String[] args) {
        zeroOneKnapsack(CAPACITY, TYPE, WEIGHT, VALUE);
    }

    /**
     * 0-1背包问题
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

        for (int i = 0; i < type+1; i++) {
            for (int j = 0; j < capacity+1; j++) {
                System.out.print(dp[i][j] + "  ");
            }
            System.out.println();
        }
        return dp[type][capacity];
    }

}















