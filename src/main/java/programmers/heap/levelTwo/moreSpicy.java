package programmers.heap.levelTwo;

import java.util.*;

public class moreSpicy {

    public static void main(String[] args) {
        int solutioned = solution(new int[]{1, 2, 3, 9, 10, 12}, 11);
        System.out.println("solutioned = " + solutioned);
    }
    public static int solution(int[] scoville, int k) {
        int answer = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i : scoville) {
            heap.offer(i);
        }
        while (heap.peek() < k){
            if (heap.size() == 1) return -1;

        int a = heap.poll();
        int b = heap.poll();
        int result = a + (b *2);
        answer++;
        heap.offer(result);
        }
        return answer;
    }
}

//
//    public int solution(int[] scoville, int k) {
//        int answer = 0;
//        Stack<Integer> stack = new Stack<>();
//        Queue<Integer> queue = new LinkedList<>();
//        Arrays.sort(scoville);
//        for (int i : scoville) {
//            queue.offer(i);
//        }
//        while (true){
//            int element = queue.poll();
//            if (element>= k){
//                return answer;
//            }
//            if (queue.isEmpty()){
//                return -1;
//            }
//            if (stack.isEmpty()) {
//                element += queue.poll() * 2;
//                stack.add(element);
//            } else {
//                if (stack.peek() <= queue.peek()) {
//                    element += stack.pop() * 2; // 섞은걸 해놓은곳
//                    if (element < k) {
//                        stack.add(element);
//                    } else {
//                        queue.add(element);
//                    }
//                } else {
//                    element += queue.poll() * 2;
//                    queue.add(element);
//                }
//            }
//            answer++;
//        }
//    }
//}
