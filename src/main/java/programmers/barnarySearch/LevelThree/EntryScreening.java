package programmers.barnarySearch.LevelThree;

import java.util.Arrays;

public class EntryScreening {
    public static void main(String[] args) {
        long solution = solution(6, new int[]{7, 10});
        System.out.println("solution = " + solution);
    }
    public static long solution(int n, int[] times) {
        Arrays.sort(times);
        long start = 0; // 시작의 최솟값
        long end = (long) times[times.length - 1] * n ; // 최대값을 구함 60
        while(start <= end) { // End 지점이 Start 지점을 지나갈때 까지
            long mid = (start + end) / 2; // 중간지점 , 30
            long humans = 0; // 사람의 수
            System.out.println("mid = " + mid);
            for(int i = 0; i < times.length; i++) { // 타임의 수 만큼 human 을 더해줌
                humans += mid / times[i];
                System.out.println("humans = " + humans);
            }

            if(humans >= n){
                end = mid - 1;
                System.out.println("end = " + end);
            }
            else{
                start = mid + 1;
                System.out.println("start = " + start);
            }
        }

        return start;
    }
}
