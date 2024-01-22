package programmers.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevelThreeN {
    /*
    N 1 개 일경우 = N N
    N 2 개 일 경우 N = 1
    N + N = 2
    N - N = 0
    N / N = 1
    N * N = 1
    NN    = 11


    즉 N^N 만큼의 경우의 수가 생김

    N 3 개일 경우
    1개일 떄의 경우의 수와 2개일 때의 경우의 수를 다시 사용하면 된다
    총 3^3 만큼의 경우의 수가 나옴 다시한번 이 경우를 박스에 저장
    ex) N + ( N + N )
     N + ( N * N )
     N + ( N - N )
     N + ( N / N )
     N  ( NN )
     1 , 2 박스를 결합해서 3번 박스
     */
    public static void main(String[] args) {
        int solution = solution(5, 12);
        System.out.println("solution = " + solution);
    }

    public static int solution(int N, int number) {
            List<Set<Integer>> countList = new ArrayList<>(); // 박스를 만들기 위한 배열 생성
            for (int i = 0 ; i < 9 ; i++)
                countList.add(new HashSet<>());
            countList.get(1).add(N);
            for (int i = 2 ; i < 9 ; i++){ // 다음 박스를 이야기함
                Set<Integer> set = countList.get(i);
                for (int j = 1 ; j <= i ; j++){ // 이게 이전의 박스
                    Set<Integer> pre = countList.get(j);
                    Set<Integer> post = countList.get(i - j);
                    for (Integer preValue : pre) {
                        for (Integer postValue : post) {
                            set.add(preValue + postValue);
                            set.add(preValue - postValue);
                            set.add(preValue * postValue);
                            if (preValue != 0 && postValue != 0){
                                set.add(preValue/postValue);
                            }
                        }
                    }
                }
                set.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            }
        System.out.println("countList = " + countList);
        for (Set<Integer> integers : countList) {
            if (integers.contains(number)){
                return countList.indexOf(integers);
            }
        }
        return -1;
        }
}
/**
 * 아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.
 *
 * 12 = 5 + 5 + (5 / 5) + (5 / 5)
 * 12 = 55 / 5 + 5 / 5
 * 12 = (55 + 5) / 5
 *
 * 5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
 * 이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.
 *
 * 제한사항
 * N은 1 이상 9 이하입니다.
 * number는 1 이상 32,000 이하입니다.
 * 수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
 * 최솟값이 8보다 크면 -1을 return 합니다.
 * 입출력 예
 * N	number	return
 * 5	12	4
 * 2	11	3
 * 입출력 예 설명
 * 예제 #1
 * 문제에 나온 예와 같습니다.
 *
 * 예제 #2
 * 11 = 22 / 2와 같이 2를 3번만 사용하여 표현할 수 있습니다.
 *
 * ※ 공지 - 2020년 9월 3일 테스트케이스가 추가되었습니
 */