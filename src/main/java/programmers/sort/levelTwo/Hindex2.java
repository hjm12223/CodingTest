package programmers.sort.levelTwo;

import java.util.*;

public class Hindex2 {
    public static void main(String[] args) {
        int solution = solution(new int[]{1,1,1,2,3,4});
        System.out.println("solution = " + solution);
    }

    public static int solution(int[] citations) {
        Queue<Integer> queue = new LinkedList<>();

        Arrays.sort(citations);
        int length = citations.length;

        for (int i = 0; i < length / 2; i++) {
            int temp = citations[i];
            citations[i] = citations[length - 1 - i];
            citations[length - 1 - i] = temp;
        }

        for (int i = 0 ; i< citations.length ; i++){
            // n개의 수만큼 for 문을 돌림
            if (citations[i] == i+1 && citations[i] == queue.size()){
                // 만약 인용된 논문의 인덱스 값과 가변적인 자료구조와 같을 경우
                return citations[i];
                // 해당 논문의 인용된 횟수를 반환
            }else if ( queue.size() >= citations[i]){
                // 만약 인용된 논문이 queue 사이즈 보다 작을경우 queue 사이즈를 반환
                return queue.size();
            }else {
                queue.offer(citations[i]);
            }
        }
        // 만약 다 돌았는데도 아닐경우에는 1을 밚환
        return queue.size();
    }
}