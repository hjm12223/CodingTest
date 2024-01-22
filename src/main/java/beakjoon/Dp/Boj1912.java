package beakjoon.Dp;

import java.util.Arrays;
import java.util.Scanner;

public class Boj1912 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] dp = new int[n+1];
        int max =Integer.MIN_VALUE;
        int parseMax = Integer.MIN_VALUE;
        sc.nextLine();

        String[] l = sc.nextLine().split(" ");
        for (int i = 0 ; i < l.length ; i++){
            int value = Integer.parseInt(l[i]);
            if (value  > parseMax ){
                parseMax= value;
            }
            arr[i]= value;

        }
        dp[0] = arr[0];
        for (int i = 1 ; i < arr.length; i++){
            int value = dp[i - 1] + arr[i];
            if (value < 0){
                dp[i] = 0;
            }else {
                dp[i] = value;
            }
            if (value  > max ){
                max = value;
            }
        }
        System.out.println(Math.max(max,parseMax));
    }
}
