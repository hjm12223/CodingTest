package programmers.sort.levelTwo;

import java.util.*;

public class Hindex {
    public static void main(String[] args) {
        int solution = solution(new int[]{1,1,1,2,3,4});
        System.out.println("solution = " + solution);
    }
    public static int solution(int[] citations) {
        Arrays.sort(citations);
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        // 만약 오름차순으로 하였을 경우 끝자리가 0일경우 인용된 횟수가 0이기 떄문에 0을 리턴
        if (citations[citations.length-1] == 0 ){
            return 0;
        }
        // 동적인 자료구조를 위해 queue 를 사용
        for (int citation : citations) {
            queue.offer(citation);
        }

        // 길이가 2와 1일 경우 예외처리 로직
        if (citations.length == 2){
            if (citations[1] == 0){
                return 0;
                //2일 경우 마지막 숫자가 0이면 0 을 리턴
            }else if (citations[0] >= 2){
                // 첫번째 논문이 2회 이상 인용되었으면 2를반환
                return 2;
            }else {
                // 아닐 경우 1을 반환
                return 1;
            }
            // 길이가 1일 경우 또한 예외처리
        } else if (citations.length ==1) {
            if (citations[0] >=1){
                return 1;
            }else {
                return 0;
            }
        }



        for (int i = 0 ; i< citations.length ; i++){
            // n개의 수만큼 for 문을 돌림
            if (citations[i] == i+1 &&   citations[i] == queue.size()){
                // 만약 인용된 논문의 인덱스 값과 가변적인 자료구조와 같을 경우
                return citations[i];
                // 해당 논문의 인용된 횟수를 반환
            }else if ( queue.size() <= citations[i]){
                // 만약 인용된 논문이 queue 사이즈 보다 작을경우 queue 사이즈를 반환
                return queue.size();
            }else {
                queue.poll();
            }
        }
        // 만약 다 돌았는데도 아닐경우에는 1을 밚환
        return 1;
    }
}
/**
 * 어떤 과학자가 발표한 논문 `n` 편 중 `h` 번 이상 인용된 논문이 `h`편 이상이고 나머지 논문이 `h`번 이하 인용되었다면 `h`의 최댓값이 이 과학자의 h-index 입니다.
 *
 * 그럼 어떻게 해야하나?
 *
 * 일단 내림차순으로 정렬
 *
 * 0 1 3 5 6 최소 3회 이상 논문이 3개
 *
 * 0 1 2 -> 최소 1회 이상 논문이 2개
 *
 *
 */