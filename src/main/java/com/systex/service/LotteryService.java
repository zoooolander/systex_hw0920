package com.systex.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LotteryService {

	// 產生樂透號碼的方法
    public List<Set<Integer>> generateLotteryNumbers(Set<Integer> excludeNumbers, int totalNumbers) {
        List<Set<Integer>> result = new ArrayList<>();
        Random random = new Random();
        Set<Integer> numbers;

        while (result.size() * 6 < totalNumbers) {
            numbers = new HashSet<>();
            while (numbers.size() < 6) {
                int randomNumber = random.nextInt(49) + 1;
                if (!excludeNumbers.contains(randomNumber)) {
                    numbers.add(randomNumber);
                }
            }
            result.add(numbers);
        }
        return result;
    }

}
