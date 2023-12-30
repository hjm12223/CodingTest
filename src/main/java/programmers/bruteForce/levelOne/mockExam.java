package programmers.bruteForce.levelOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class mockExam {
    public static void main(String[] args) {
        int[] solution = solution(new int[]{1, 3, 2, 4, 2});
        System.out.println("solution = " + Arrays.toString(solution));
    }
    public  static int[] solution(int[] answers) {

        // 수포자 1,2,3 을 생성자를 통해 생성
        Supo supo1 = new Supo(new int[]{1,2,3,4,5},0,1);
        Supo supo2 = new Supo(new int[]{2,1,2,3,2,4,2,5},0,2);
        Supo supo3 = new Supo(new int[]{3,3,1,1,2,2,4,4,5,5},0,3);

        // 수포 리스트를 만들어 넣어줌
        List<Supo> list = Arrays.asList(supo1,supo2,supo3);


        // 이중포문을 통해 해당 answer[i] 정답값과 supo list 에 있는 정답과 같은지 확인하고 같으면 +1
        for (int i = 0; i < answers.length; i++) {
            for (Supo supo : list) {
                int length = supo.method.length;
                // 최대 10000개의 배열이 있기 떄문에 supo 의 찍기 방식의 길이만큼 나머지를 구해서 그 나머지 값으로 값을 구함
                if (answers[i] == supo.method[i % length]) {
                    supo.correct++;
                }
            }
        }

        // correct 를 기준으로 오름차순 정렬
        list.sort((o1, o2) -> {
                 return o2.correct - o1.correct;
             });


        List<Integer> result = new ArrayList<>();

        //최대값인 max value 를 통해 같은 값이 있는지 확인
        int maxValue = list.get(0).correct;
        for (Supo supo : list) {
            if (supo.correct == maxValue){
                //있으면 result 에 더해줌
                result.add(supo.number);
            }else {
                break;
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    public static class Supo{
        int[] method;

        int correct;

        int number;


        public Supo (int[] method, int correct, int number){
            this.method = method;
            this.correct = correct;
            this.number = number;
        }
    }
}

/**
 * 1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
 * 2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
 * 3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5,
 */