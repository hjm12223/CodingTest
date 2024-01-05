package beakjoon.greedy.silver;

import java.util.Scanner;

public class Coin0 {

    public static void main(String[] args) throws Exception {
            Scanner scanner = new Scanner(System.in);

            // 첫째 줄에서 N과 K를 받아옴
            int n = scanner.nextInt();
            int k = scanner.nextInt();

            // 둘째 줄부터 N개의 줄에 동전의 가치를 받아옴
            int[] coins = new int[n];
            for (int i = 0; i < n; i++) {
                coins[i] = scanner.nextInt();
            }

            // 최소 동전 개수 계산
            int count = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (k >= coins[i]) {
                    count += k / coins[i];
                    k %= coins[i];
                }
            }

            // 결과 출력
            System.out.println(count);

            scanner.close();
        }
    }
