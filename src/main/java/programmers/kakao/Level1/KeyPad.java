package programmers.kakao.Level1;

import java.util.LinkedList;
import java.util.Queue;

public class KeyPad {
    public static void main(String[] args) {
        solution(new int[]{1,3,4,5,8,2,1,4,5,9,5},"right");
    }
    public static String solution(int[] numbers, String hand) {
        Queue<Integer> q = new LinkedList<>();
        StringBuilder answer = new StringBuilder();
        Hand leftHand = new Hand(1,4);
        Hand rightHand = new Hand(3,4);
        for (int number : numbers) {
            q.offer(number);
        }
        while (!q.isEmpty()){
            Integer currNum = q.poll();
            if (currNum == 1 || currNum == 4 || currNum == 7){
                if (currNum == 1){
                    leftHand.y = 1;
                } else if (currNum == 4) {
                    leftHand.y = 2;
                } else {
                    leftHand.y = 3;
                }
                answer.append("L");
            } else if (currNum == 3 || currNum == 6 || currNum == 9) {
                if (currNum == 3){
                    leftHand.y = 1;
                } else if (currNum == 6) {
                    leftHand.y = 2;
                } else {
                    leftHand.y = 3;
                }
                answer.append("R");
            }else {
                if (currNum == 2){

                }
            }
        }
        return answer.toString();
    }
    public static class Hand{
        int x;
        int y;

        public Hand(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
