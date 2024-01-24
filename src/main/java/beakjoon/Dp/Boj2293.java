package beakjoon.Dp;

import java.util.Scanner;

public class Boj2293 {

    static int[] arr;
    static int n;
    static int k;
    static int count = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         n = sc.nextInt();
         k = sc.nextInt();
        arr = new int[n+1];
        int[] dp = new int[n+1];
        for (int i = 1 ; i<= n ; i++){
            arr[i] = sc.nextInt();
        }
        permu(0,0);
        System.out.println(count);

    }

    private static void permu(int sum,int depth) {
        if (sum == k) {
            count++;
            return;
        }
        for (int i = 1 ; i < arr.length; i++){
            if (sum + arr[i] <= k){
                permu(sum+arr[i] ,depth+1);
            }
        }
    }
}
/**
 * n가지 종류의 동전이 있다. 각각의 동전이 나타내는 가치는 다르다.
 * 이 동전을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 그 경우의 수를 구하시오.
 * 각각의 동전은 몇 개라도 사용할 수 있다.
 *
 * 사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.
 */