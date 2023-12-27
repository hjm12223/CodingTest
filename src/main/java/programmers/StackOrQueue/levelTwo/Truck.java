package programmers.StackOrQueue.levelTwo;

import java.util.*;

public class Truck {
    public static void main(String[] args) {
        int bridgeLength = 10;
        int weight = 10;
        int[] truck_weights = new int[]{5,6,7,8,9}; //19초가 return 되어야함
        int solution = solution(bridgeLength, weight, truck_weights);
        System.out.println("solution = " + solution);
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> bridge = new LinkedList<>();
        // 비어있는 다리의 공간을 0으로 가득 채웠다.
        for(int i=0;i<bridge_length;i++){
            bridge.offer(0);
        }

        int index = 0;
        // 다리에 있는 현재 트럭의 무게이다.
        int currentWeight = 0;
        // 트럭이 더이상 남아있지 않을 때 탈출해주기 위해 조건을 만들었다.
        while(index < truck_weights.length){
            // 현재 다리에 있는 트럭무게에서 곧 나갈 트럭의 무게를 빼준다.
            currentWeight -= bridge.poll();
            // 새 트럭이 들어올 것이므로 1초를 추가한다.
            answer++;
            // 현재 다리에 있는 트럭 무게와 곧 들어올 트럭 무게의 합과 다리의 하중을 비교
            if(currentWeight + truck_weights[index] <= weight){
                // 무게를 버틴다면 다리에 트럭을 추가한다.
                bridge.offer(truck_weights[index]);
                // 현재 다리에 있는 트럭 무게에도 새 트럭 무게를 더해준다.
                // 그리고 다음 트럭을 지정하기 위해 후위 연산자를 써주어 index를 증가시켰다.
                currentWeight += truck_weights[index++];
            } else{
                // 버티지 못한다면 0을 추가한다.
                bridge.offer(0);
            }
        }
        //처음 설정한 0으로 채워진 다리가 전부 치환되면 결국 처음 다리 길이와 같으므로
        //트럭이 지나간 시간 + 다리 길이
        return answer + bridge_length;
    }
}