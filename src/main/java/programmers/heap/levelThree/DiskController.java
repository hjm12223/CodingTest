package programmers.heap.levelThree;

import java.util.*;

public class DiskController {
    public static void main(String[] args) {
        int solution = solution(new int[][]{{0, 3}, {1, 9}, {2, 6}});
        System.out.println("solution = " + solution);
    }
    public static int solution(int[][] jobs) {
        Arrays.sort(jobs,((o1, o2) -> o1[0] -o2[0]));
        PriorityQueue<int[]> queue = new PriorityQueue<>(((o1, o2) -> o1[1] - o2[1]));
        int index = 0;
        int time = jobs[0][0];
        int answer =0;
        while (!queue.isEmpty() || index <jobs.length){
            while ( index < jobs.length && jobs[index][0] <= time){
                queue.add(jobs[index++]);
            }
            if (queue.isEmpty()){
                time = jobs[index][0];
                queue.add(jobs[index++]);
            }
            int[] work = queue.poll();
            time += work[1];
            answer += time - work[0];
        }
        return answer/jobs.length;
    }
}
/*
 * 못풀었음 2시간
 */
//import java.util.*;
//// 종료시간이 빠른 작업 중 가장 먼저 시작하는 작업을 우선순위로 둠
//class Solution {
//    public static class Job implements Comparable<Job> {
//        int start;
//        int end;
//
//        public Job(int start, int end) {
//            this.start = start;
//            this.end = end;
//        }
//
//        @Override
//        public int compareTo(Job o) {
//            if (this.end == o.end) {
//                return Integer.compare(this.start, o.start);
//            }
//            return Integer.compare(this.end, o.end);
//        }
//    }
//
//    public int solution(int[][] jobs) {
//        int answer = 0;
//        int time = 0;
//        PriorityQueue<Job> pq1 = new PriorityQueue<>();
//        PriorityQueue<Job> pq2 = new PriorityQueue<>(new Comparator<Job>() {
//            @Override
//            public int compare(Job o1, Job o2) {
//                if (o1.start == o2.start) {
//                    return Integer.compare(o1.end, o2.end);
//                } else {
//                    return Integer.compare(o1.start, o2.start);
//                }
//            }
//        });
//        for (int i = 0; i < jobs.length; i++) {
//            pq2.add(new Job(jobs[i][0], jobs[i][1]));
//        }
//        System.out.println("pq2 = " + pq2);
//
//        while (!pq2.isEmpty() && time > pq2.peek().start) {
//            pq1.add(pq2.poll());
//        }
//
//        while (!pq1.isEmpty() || !pq2.isEmpty()) {
//            if (pq1.isEmpty()) {
//                pq1.add(pq2.poll());
//            }
//            Job cur = pq1.poll();
//            if (cur.start > time) {
//                time = cur.start;
//            }
//            time += cur.end;
//            answer += time - cur.start;
//
////            System.out.println("start : " + cur.start);
//            while (!pq2.isEmpty() && time > pq2.peek().start) {
//                pq1.add(pq2.poll());
//            }
//        }
//        return answer/ jobs.length;
//    }
//}