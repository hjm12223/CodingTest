package programmers.greedy.levelTwo;

import javax.swing.*;
import java.util.*;

public class Boat {
    public static void main(String[] args) {
        int[] people = new int[]{60,50};
        int limit = 100;
        int solution = solution(people, limit);
        System.out.println("solution = " + solution);
    }


    public static int solution(int[] people, int limit) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        Arrays.sort(people);
        for (Integer integer : people) {
            stack.push(integer);
        }
        while (!stack.isEmpty()){
            int sum = 0;
            int currentElement = stack.pop();
            for (int i = 0; i < stack.size(); i++){
                sum  = currentElement + stack.get(i);
                if (limit >= sum){
                    stack.remove(i);
                    break;
                }else {
                    break;
                }
            }
            answer++;
        }
     return answer;
    }
    /**
     * 잘못된 방식 오름차순을  내림차순을 기준으로 하면 안됌 진짜 최적의 수를 찾는 알고리즘을 사용해야한다
     * 50000 이기 떄문에 순열 및 조합은 금지
     * 50000 이기 떄문에 n^2도 안됌
     */

    /**
     * 만약
     * 10 20 30 40 50 이면
     *
     * 10 20 30 40 을 동승하고
     *
     * 50만 혼자가는 로직
     *
     *
     * 10 40 50 20 30 70 일 경우
     *
     * 10 20 30 40 을 동승하고
     * 50 70 만 혼자감
     *
     * 오름차순으로 정렬하면?
     * 10 20 30 40 60 70 80 90  을 했을때
     *
     * 10 90, 20 80, 30 70, 40 60 이 최적의 수다
     */
}
