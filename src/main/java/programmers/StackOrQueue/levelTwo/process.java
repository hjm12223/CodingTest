package programmers.StackOrQueue.levelTwo;

import java.util.*;

public class process {

    public static void main(String[] args) {
        int solution = solution(new int[]{2, 1, 3, 2}, 2); // 2 3 0 1
        System.out.println("solution = " + solution);

    }
    public static int solution(int[] priorities, int location) {
        int answer = 0;
        int l = location;
        Queue<Integer> que = new LinkedList<Integer>();
        for (int i : priorities){
            que.add(i);
        }
        Arrays.sort(priorities);
        int size = priorities.length -1;

        while (!que.isEmpty()){
            Integer i = que.poll();
            if ( i == priorities[size-answer]){
                answer++;
                l--;
                if (l < 0){
                    l = que.size() -1;
                }
            }
        }
        return answer;
    }
}