package com.vip.slidingwindow;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

public class stock_buy_sell {
    public int maxProfit(int[] prices) {
        int left = 0, right = 1;
        int maxProfit = 0;

        while (right < prices.length) {
            if (prices[right] < prices[left]) {
                left = right;
            } else {
                if (prices[right] - prices[left] > maxProfit)
                    maxProfit = prices[right] - prices[left];
            }
            right++;
        }

        return maxProfit;
    }

    public void test_01() {
        int[] prices =  {7,1,5,3,6,4};
        System.out.println("Test 1: " + maxProfit(prices));
    }

    public void test_02() {
        int[] prices =  {7,6,4,3,1};
        System.out.println("Test 2: " + maxProfit(prices));
    }

    

    public static void main(String[] args) {
        stock_buy_sell obj = new stock_buy_sell();
        obj.test_01();
        obj.test_02();
    }

}
