package beakjoon.prirorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Boj11286 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Math.abs(o1) == Math.abs(o2) ){
                    return Integer.compare(o1,o2);
                }
                    else return Math.abs(o1)-Math.abs(o2)  ;
            }
        });

        for (int i = 0; i < n; i++) {
            int value = sc.nextInt();
            if (value == 0 && q.isEmpty()) {
                System.out.println(0);
            } else if (value == 0) System.out.println(q.poll());
            else {
                q.offer(value);
            }
        }
    }
}
