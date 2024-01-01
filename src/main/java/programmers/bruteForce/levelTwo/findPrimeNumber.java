package programmers.bruteForce.levelTwo;

import java.util.*;

public class findPrimeNumber {
    public static void main(String[] args) {
        int solution = solution("17");
        System.out.println("solution = " + solution);
    }


    public static int solution(String numbers) {
        Set<Integer> set = new HashSet<>();
        boolean[] visited = new boolean[numbers.length()];

        for(int r = 1 ; r <= numbers.length(); r++){
            combination(numbers,0,r,set,visited,"");
        }
        return set.size();
    }
    /* 순열 알고리즘 */
    public static void combination(String numbers, int depth, int r, Set<Integer> set,boolean[] visited, String result) {

        if(depth == r){
            int number = Integer.parseInt(result);
            if (isPrime(number)){
                set.add(number);
            }
            return;
        }
            for (int i = 0 ; i < numbers.length(); i++){
                if (!visited[i]) {
                    visited[i] = true;
                    combination(numbers, depth + 1, r, set, visited, result + numbers.charAt(i));
                    visited[i] = false;
                }
            }
    }


    /**
     * 테라스토스체 를 사용하여 제곱근 까지만 계산하여 소수인지 확인
     */
    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}