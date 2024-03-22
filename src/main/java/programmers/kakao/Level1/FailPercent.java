package programmers.kakao.Level1;

import java.util.*;

public class FailPercent {
    public static void main(String[] args) {
        solution(5,new int[]{2, 1, 2, 6, 2, 4, 3, 3});
    }
    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int userSize = stages.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Node> list = new ArrayList<>();

        for (int stage : stages) {
            map.put(stage, map.getOrDefault(stage, 0) + 1);
        }

        for (int i = 1; i <= N; i++) {
            double value = userSize ;
                    if(value != 0){
                        value = (double) map.getOrDefault(i, 0) / userSize;
                    }else {
                        value = 0;
                    }
            list.add(new Node(i, value));
            userSize -= map.getOrDefault(i, 0);
        }

        list.sort((o1, o2) -> {
            if (o1.value == o2.value) {
                return o1.idx - o2.idx;
            } else {
                return Double.compare(o2.value, o1.value);
            }
        });

        for (int i = 0; i < N; i++) {
            answer[i] = list.get(i).idx;
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }

    private static class Node {
        int idx;
        double value;

        public Node(int idx, double value) {
            this.idx = idx;
            this.value = value;
        }
    }
}
/*
슈퍼 게임 개발자 오렐리는 큰 고민에 빠졌다.
그녀가 만든 프랜즈 오천성이 대성공을 거뒀지만,
요즘 신규 사용자의 수가 급감한 것이다.
원인은 신규 사용자와 기존 사용자 사이에 스테이지 차이가 너무 큰 것이 문제였다.

이 문제를 어떻게 할까 고민 한 그녀는 동적으로 게임 시간을 늘려서 난이도를 조절하기로 했다.
역시 슈퍼 개발자라 대부분의 로직은 쉽게 구현했지만,
실패율을 구하는 부분에서 위기에 빠지고 말았다.
오렐리를 위해 실패율을 구하는 코드를 완성하라.

실패율은 다음과 같이 정의한다.
스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
전체 스테이지의 개수 N,게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열 stages가 매개변수로 주어질 때,
실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 return 하도록 solution 함수를 완성하라

입출력 예 #1
1번 스테이지에는 총 8명의 사용자가 도전했으며, 이 중 1명의 사용자가 아직 클리어하지 못했다. 따라서 1번 스테이지의 실패율은 다음과 같다.

1 번 스테이지 실패율 : 1/8
2번 스테이지에는 총 7명의 사용자가 도전했으며, 이 중 3명의 사용자가 아직 클리어하지 못했다. 따라서 2번 스테이지의 실패율은 다음과 같다.

2 번 스테이지 실패율 : 3/7
마찬가지로 나머지 스테이지의 실패율은 다음과 같다.

3 번 스테이지 실패율 : 2/4
4번 스테이지 실패율 : 1/2
5번 스테이지 실패율 : 0/1
각 스테이지의 번호를 실패율의 내림차순으로 정렬하면 다음과 같다.

[3,4,2,1,5]
입출력 예 #2

모든 사용자가 마지막 스테이지에 있으므로 4번 스테이지의 실패율은 1이며 나머지 스테이지의 실패율은 0이다.

[4,1,2,3]
 */
