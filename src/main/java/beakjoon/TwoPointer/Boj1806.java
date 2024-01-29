package beakjoon.TwoPointer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Boj1806 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0 ; i< n ; i++){
            arr[i] = sc.nextInt();
        }
        List<Integer> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;

        if (arr[left] >= s) {
            System.out.println(1);
            return;
        }
        if (n == 2 && arr[left] + arr[right] >= s) {
            System.out.println(2);
            return;
        }
        int sum =0;
        sum += arr[left];
        while (right < n && left <= right) {
            if (sum >= s) {
                min = Math.min(min, right - left + 1);
                list.add(right - left + 1);
                sum -= arr[left];
                left++;
            } else {
                right++;
                if (right < n) {
                    sum += arr[right];
                }
            }
        }

        if (list.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(min);
        }
    }
}
