package programmers.bruteForce.levelTwo;

import java.util.*;

public class WireSplit {
    public static List<Integer>[] list;
    public static void main(String[] args) {
        int[][] dataList = {{1, 2}, {2, 3}, {3, 4}};
        int solution = solution(4, dataList);
        System.out.println("solution = " + solution);
    }

    public static int solution(int n, int[][] wires) {
        list = new ArrayList[n+1]; // 루트 노드를 포함한 배열을 생성
        int answer = Integer.MAX_VALUE; // 비교하기 위해서 MAX값을 answer 로 선언
        for (int i =1; i < n+1 ; i++){
            list[i] = new ArrayList<>(); // list[i] 에 ArrayList 를 생성함으로써 해당 인덱스값에 인접한 노드들을 저장
        }
        for (int[] wire : wires) {
            list[wire[0]].add(wire[1]); // wire[0] 에 해당하는 인덱스에 인접한 wire[1]의 값을 넣어줌
            list[wire[1]].add(wire[0]); // 반대도 마찬가지
        }
        for (int[] wire : wires) {
            int n1 = bfs(wire[0],wire[1],n); // bfs 를 통해서 전선을 잘랐을 경우 인접한 전선이 몇개 남아있는지 확인
            int n2 = bfs(wire[1],wire[0],n); // 반대편 또한 확인
            answer = Math.min(answer,Math.abs(n1-n2)); // n1 - n2 를 통해 전선간의 차이 값을 구함
        }
        return answer;
    }

    private static int bfs(int v1, int v2, int n) {
        int cnt = 0; // 카운터를 선언
        boolean[] isVisited = new boolean[n+1]; // 해당 배열에 방문했는지를 확인하기위해 isvisited 선언
        Queue<Integer> queue = new LinkedList<>(); // Queue 를 통해서 bfs 를 구현
        queue.add(v1); // 전선의 첫번째 v1을 queue에 넣어줌
        isVisited[v1] =true; // 해당 전선이 들어갔음으로 true 로 선언
        while (!queue.isEmpty()){ // 큐가 없어질때까지 반복문 선언
            int cur = queue.poll(); // 현재 위치는 queue 의 poll 한값
            cnt ++; // cnt 를 ++ 해줌
            for (int next : list[cur]){ // 현재 인덱스에 위치한 인덱스 값들을 다 꺼내줌
                if (next != v2 && !isVisited[next]) { // 만약 인접한값들이 v2 가 아니고 방문하지 않았다면?
                    isVisited[next] = true; // 방문처리
                    queue.add(next); // 큐에 다시 추가
                }
            }
        }
        return cnt;
    }
}