package beakjoon.TwoPointer;

import java.util.*;

public class Boj1644 {
    static int cnt = 0;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        visited = new boolean[n+1];
        visited[0] =  visited[1] = true;
        isPrime(n);
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (visited[i]) {
                list.add(i);
            }
        }

        int[] arr = list.stream().mapToInt(i -> i).toArray();

        int right = 0;
        int left = 0;
        int sum = 0;

        while (true) {
            if (sum >= n) sum -= arr[left++];
            else if (right == arr.length) break;
            else sum += arr[right++];
            if (n == sum) cnt++;
            }
        System.out.println(cnt);
    }
    public static boolean[] isPrime(int value){

        if (value< 2) return visited;

        Arrays.fill(visited,true);
            for (int i = 2 ; i*i <= value ; i++){
                if (visited[i]) {
                    for (int j = i * i; j <= value; j += i) {
                        visited[j] = false;
                    }
                }
            }
            return visited;
    }
}
