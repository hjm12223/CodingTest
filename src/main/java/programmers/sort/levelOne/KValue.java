package programmers.sort.levelOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KValue {
    public static void main(String[] args) {
        int[] solution = solution(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}});
        System.out.println("solution = " + Arrays.toString(solution));
    }
    public static int[] solution(int[] array, int[][] commands) {
        List<Integer> list = new ArrayList<>();
        // Commands Length 만큼 반복
        for (int i = 0 ; i< commands.length ; i++){
            // 두번째 for 의 배열의 번호 역할을 해줄 Count 선언
            int count = 0;
            // SubString 할 임시 배열 temp 를 선언
            int[] temp = new int[commands[i][1]-commands[i][0]+1];
            // 만약 자르는 범위가 똑같지 않을 경우
            if (commands[i][0]!= commands[i][1]) {
                for (int j = commands[i][0]-1; j < commands[i][1]; j++) {
                    temp[count] = array[j];
                    count++;
                }
                //정렬
                Arrays.sort(temp);
                list.add(temp[commands[i][2] - 1]);
            }else {
                // 만약 같을 경우 해당 해당 command[i][1] +1 한값을 list 에 add
                list.add(array[commands[i][1]-1]);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}
