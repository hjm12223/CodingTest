package beakjoon.bruteforce;

import java.util.Arrays;
import java.util.Scanner;

public class Boj1182 {
    static int result = 0;
    static boolean[] isVisited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        isVisited = new boolean[n];

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            permu(arr, i, target, 0);
        }
        System.out.println(result);
    }

    private static void permu(int[] arr, int index, int target, int sum) {
        sum += arr[index];
        System.out.println("sum = " + sum);
        if (sum == target) {
            result++;
        }

        for (int i = index + 1; i < arr.length; i++) {
            permu(arr, i, target, sum);
        }
    }
}
