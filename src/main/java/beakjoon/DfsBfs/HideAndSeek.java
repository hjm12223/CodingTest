package beakjoon.DfsBfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HideAndSeek {
    static int[] dist;
    static boolean[] isVisited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        dist = new int[100001];
        isVisited = new boolean[100001];
        if (n == k){
            System.out.println(0);
        }
        bfs(n, k);
        System.out.println(dist[k]);
    }

    private static void bfs(int n,int k) {
        dist[n] = 0;
        isVisited[n] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int temp : new int[]{curr - 1, curr + 1, curr * 2}) {
                System.out.println("temp = " + temp);
                if (!isVisited[temp] && temp <= 100000) {
                    isVisited[temp] = true;
                    dist[temp] = dist[curr] + 1;
                    q.offer(temp);
                }
            }
            if (dist[k] > 0) break;
        }
    }
}

    /**
     * isMultifly 라는 함수를 사용
     * 이 함수를 통해 곱셈을 사용해도 되는지 확인
     *
     * 만약 20이라는 값이 나오면 --  -- -- 17
     * 하지만 기존의 10의 값에서 17까지 갈려면
     * @param curr
     * @param k
     * @return
     */
