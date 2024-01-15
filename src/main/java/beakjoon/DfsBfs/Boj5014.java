package beakjoon.DfsBfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Boj5014 {
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int F = sc.nextInt(); // 건물의 전체 층수
        int S = sc.nextInt() - 1; // 현재 나의 위치
        int G = sc.nextInt() - 1; // 도착해야하는 스타드링크의 층수
        int U = sc.nextInt(); // 위로 올라가는 버튼 만약 해당하는 층이 없을 경우 앨리베이터는 안움직임
        int D = sc.nextInt(); // 아래로 내려가는 버튼 만약 해당하는 층이 없을 경우 앨리베이터는 안움직임

        arr = new int[F];
        Arrays.fill(arr, -1);

        bfs(U, D, G, S);

        if (arr[G] != -1) {
            System.out.println(arr[G]);
        } else {
            System.out.println("use the stairs");
        }
    }

    private static void bfs(int up, int down, int end, int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        arr[start] = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next : new int[]{curr +up, curr - down}){
                if (next >=  0 && next < arr.length && arr[next] == -1){
                    arr[next] = arr[curr]+1;
                    q.offer(next);
                }
                if (next == end){
                    return;
                }
                }
            }
        }
    }