package programmers.heap.levelThree;

import java.util.*;

public class DoublePriorityQueue {
    public static void main(String[] args) {
        String[] operations = {"I 1", "I 2", "D 1", "D -1", "I 3", "I 4", "D 1"};
        int[] solution = solution(operations);
        System.out.println("solution = " + Arrays.toString(solution));
    }

    public static int[] solution(String[] operations) {
        Queue<Integer> reverseQueue = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> queue = new PriorityQueue<>();

        for (String operation : operations) {
            if (operation.charAt(0) == 'I') {
                int value = Integer.parseInt(operation.substring(2));
                reverseQueue.add(value);
                queue.add(value);
            } else if (operation.equals("D 1")) {
                if (!reverseQueue.isEmpty()) {
                    int maxValue = reverseQueue.poll();
                    queue.remove(maxValue);
                }
            } else if (operation.equals("D -1")) {
                if (!queue.isEmpty()) {
                    int minValue = queue.poll();
                    reverseQueue.remove(minValue);
                }
            }
        }
        if (reverseQueue.isEmpty() || queue.isEmpty()) {
            return new int[]{0, 0};
        } else {
            return new int[]{reverseQueue.poll(), queue.poll()};
        }
    }
}