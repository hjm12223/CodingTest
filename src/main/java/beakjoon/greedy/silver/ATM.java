package beakjoon.greedy.silver;

import java.util.Arrays;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        in.nextLine();

        String[] strings = in.nextLine().split(" ");


        int answer = 0;
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(strings[i]);
        }
        Arrays.sort(array);

        for (int i = 0; i < array.length; i++) {
            answer += array[i]  *  (N - i);
        }

        System.out.println(answer);
    }
}