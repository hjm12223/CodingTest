package programmers.greedy.levelTwo;

import programmers.sort.levelTwo.MaxValue;

import java.util.*;

public class MakeBigValue {
    static int maxValue = Integer.MIN_VALUE;
    public static void main(String[] args) {
        String number = "1924";
        String solution = solution(number,2);
        System.out.println("solution = " + solution);
    }

    public static String solution(String number, int k) {
        boolean[] isVisited = new boolean[number.length()];
        int r = number.length()-k;
        String[] numbers = number.split("");
        Arrays.sort(numbers);
        Set<Integer> set = new HashSet<>();

        for (int i = 0 ; i <= numbers.length-k; i++) {
            System.out.println("ex : " + number.substring(i,i+k));
            permu(number.substring(i,i+k), r, 0, isVisited, set);
        }
        System.out.println("set = " + set);
        for (int integer : set) {
            if (maxValue < integer) {
                maxValue = integer;
            }
        }
        return String.valueOf(maxValue);
    }

    private static void permu(String number, int r,int depth, boolean[] isVisited,Set<Integer> set) {
        String[] numbers = number.split("");
        if (depth == r){
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < numbers.length ; i++){
                if (isVisited[i]){
                    str.append(numbers[i]);
                    System.out.println("str = " + str);
                }
            }
            set.add(Integer.parseInt(String.valueOf(str)));
        }
        for (int i = 0 ; i < numbers.length ; i++){
            if (!isVisited[i]){
                isVisited[i] = true;
                permu(number,r,depth+1,isVisited,set);
                isVisited[i] = false;
            }
        }
    }
}
