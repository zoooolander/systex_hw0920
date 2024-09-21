package com.systex.service;

import java.util.Random;
import com.systex.model.GuessNumberResult;

public class GuessGameService {
    private int luckyNumber; // 隨機生成的目標數字
    private int remain; // 剩餘的猜測次數
    private int low;    // 最低猜測範圍
    private int high;   // 最高猜測範圍

    // 構造函數，初始化隨機數和剩餘次數
    public GuessGameService(int high, int remains) {
        this.remain = remains;
        this.low = 1;   // 初始最低範圍為 1
        this.high = high;  // 設定最高範圍
        Random random = new Random();
        luckyNumber = random.nextInt(high) + 1; // 隨機產生 1 到 high 之間的數字
    }

    // 猜測方法，接收玩家的猜測數字
    public GuessNumberResult guess(int guessNumber) {
        GuessNumberResult result = new GuessNumberResult(remain, low, high, "", "");
        
        if (guessNumber == luckyNumber) {
            result.setStatus("success"); // 猜中
        } else {
            remain--;
            result.setRemains(remain); // 設置剩餘次數
            
            // 調整範圍並給予提示
            if (guessNumber < luckyNumber) {
                low = Math.max(low, guessNumber);  // 更新最低猜測範圍
                result.setLow(low);
                result.setMessage("太小了，請輸入 " + low + " 到 " + high + " 的數字");
            } else {
                high = Math.min(high, guessNumber);  // 更新最高猜測範圍
                result.setHigh(high);
                result.setMessage("太大了，請輸入 " + low + " 到 " + high + " 的數字");
            }
        }

        // 如果剩餘次數用完，設置為失敗狀態
        if (remain <= 0) {
            result.setStatus("fail");
        }

        return result; // 返回猜測結果
    }

    // 返回剩餘的猜測次數
    public int getRemain() {
        return remain;
    }
    
    public int getLuckyNumber() {
        return luckyNumber;
    }
}
