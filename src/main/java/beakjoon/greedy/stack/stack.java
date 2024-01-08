package beakjoon.greedy.stack;

import java.util.*;

public class stack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int N = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0 ; i < N ; i++){
            arr[i] = scanner.nextInt();
        }
        int[] result = isArray(arr);
        if (result != null){
            for (int i : result) {
                System.out.println(i >0 ? '+' : '-');
            }
        }else {
            System.out.println("No");
        }

    }
    public static int[] isArray(int[] arr){
        int[] result = new int[arr.length *2];
        int resultIdx = 0;
        int current = 1;
        int seqIdx = 0;
        Stack<Integer> stack =new Stack<>();
        while (resultIdx < result.length){
            if (!stack.isEmpty() && stack.peek() == arr[seqIdx]){
                stack.pop();
                result[resultIdx++] = -1;
                seqIdx++;
            }else if (current <= arr[seqIdx]){
                stack.push(current);
                result[resultIdx++] = 1;
                current++;
            }
            else {
                return null;
            }
        }
        return result;
    }
}