package programmers.StackOrQueue.levelTwo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class stockPrice {
    public static void main(String[] args) {
        int[] solution = solution(new int[]{1, 2, 3, 2, 3});
        System.out.println("solution = " + Arrays.toString(solution));
    }
    public static int[] solution(int[] prices) {
        int index = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int price : prices) {
            int find = findPrice(price, index,prices);
            index++;
            queue.add(find);
        }
        return queue.stream().mapToInt(i -> i).toArray();
    }
    public static int findPrice(int price, int index,int[] prices){
        int count = 0;
        while (true){
            if (index == prices.length-1){
                return count;
            }
            if (price <= prices[index]){
                count++;
                index++;
            }else {
                return count;
            }
        }
    }
}
