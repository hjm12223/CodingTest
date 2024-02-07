package beakjoon.prirorityqueue;

import java.util.*;

public class Boj1715 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int reuslt = 0;
        Queue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        });
        for (int i= 0 ; i< n ; i++){
            q.offer(sc.nextInt());
        }
        while (!q.isEmpty()){
            Integer curr1 = q.poll();
            Integer curr2 = q.poll();
            if (curr1 == null || curr2 == null) break;
            int curr3 = curr1 + curr2;
            reuslt += curr3;
            q.offer(curr3);
        }
        System.out.println(reuslt);
    }
}
