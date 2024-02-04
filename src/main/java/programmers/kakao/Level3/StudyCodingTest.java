package programmers.kakao.Level3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudyCodingTest {
    public static void main(String[] args) {
        int solution = solution(10, 10, new int[][]{{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}});
        System.out.println(solution(0,0, new int[][]{{0,0,2,1,2},{4,5,3,1,2},{4,11,4,0,2},{10,4,0,4,2}}));
        System.out.println("solution = " + solution);
    }
    /*
    problems[필요한알고력][필요한코딩력][증가하는코딩력][증가하는알고력][푸는시간]

    혹은

    알고력을 높이기 위해 알고리즘 공부를 합니다. 알고력 1을 높이기 위해서 1의 시간이 필요합니다.
    코딩력을 높이기 위해 코딩 공부를 합니다. 코딩력 1을 높이기 위해서 1의 시간이 필요합니다.

    정렬을 잘 사용해야한다

    최적의 알고문제를 풀어 코딩력과, 알고리즘력을 올리는게 관건
    만약 현재 문제가 다음문제의 필요수치가 똑같을 경우? 그럼 해당 문제가 올려주는 알고력, 코딩력, 시간을 비교하여 더효율적인지 판단

     이걸 어떻게 판단?


     */
    public static int solution(int alp, int cop, int[][] problems) {
        Person person = new Person(alp,cop);
        List<Test> testList = new ArrayList<>();
        int usedTime = 0;
        for (int i = 0 ; i < problems.length ; i++){
            Test test = new Test(problems[i][0],problems[i][1],problems[i][2],problems[i][3],problems[i][4]);
            testList.add(test);
        }
        Collections.sort(testList,(o1, o2) -> {
            if (o1.reqAlgo == o2.reqAlgo) return Integer.compare(o1.reqCoding, o2.reqCoding);
            else return Integer.compare(Integer.compare(o1.reqAlgo,o2.reqAlgo),Integer.compare(o1.reqCoding,o2.reqCoding));
        });

        System.out.println(testList);

        for (int i = 0 ; i< testList.size()-1; i++){
        if (person.algo < testList.get(i).reqAlgo ||  person.coding < testList.get(i).reqCoding){

            if (person.algo >= testList.get(i).reqAlgo && person.coding >= testList.get(i).reqCoding  ){ // 현재 필요치를 충족할 경우
                if (testList.get(i).reqAlgo >= testList.get(i+1).reqAlgo && testList.get(i).reqCoding >= testList.get(i+1).reqCoding){
                    //
                }

            }

            while (person.coding<testList.get(i).reqCoding) {
                person.coding++;
                usedTime++;
            }
            while (person.algo < testList.get(i).reqAlgo){
                person.algo++;
                usedTime++;
                }
            if (person.algo < testList.get(i+1).reqAlgo ||  person.coding < testList.get(i+1).reqCoding){
                while (person.coding<testList.get(i+1).reqCoding || person.algo < testList.get(i+1).reqAlgo) {
                    person.coding += testList.get(i).rewardCoding;
                    person.algo += testList.get(i).rewardAlgo;

                    usedTime += testList.get(i).time;
                }
            }
            }
        }

        return usedTime;
    }
    public static class Person{
        int algo;
        int coding;

        public Person(int algo, int coding) {
            this.algo = algo;
            this.coding = coding;
        }
    }
    public static class Test{
        @Override
        public String toString() {
            return "Test{" +
                    "reqAlgo=" + reqAlgo +
                    ", reqCoding=" + reqCoding +
                    ", rewardAlgo=" + rewardAlgo +
                    ", rewardCoding=" + rewardCoding +
                    ", time=" + time +
                    '}';
        }

        int reqAlgo;
        int reqCoding;
        int rewardAlgo;
        int rewardCoding;

        int time;

        public Test(int reqAlgo, int reqCoding, int rewardAlgo, int rewardCoding, int time) {
            this.reqAlgo = reqAlgo;
            this.reqCoding = reqCoding;
            this.rewardAlgo = rewardAlgo;
            this.rewardCoding = rewardCoding;
            this.time = time;
        }
    }
    // 문제집이 더 나은지 확인하는 함수
    private static boolean isBetter(Test nowTest, Test afterTest){
        return false;
    }
}
/**
 * [본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]
 *
 * 당신은 코딩 테스트를 준비하기 위해 공부하려고 합니다.
 * 코딩 테스트 문제를 풀기 위해서는 알고리즘에 대한 지식과 코드를 구현하는 능력이 필요합니다.
 *
 * 알고리즘에 대한 지식은 알고력, 코드를 구현하는 능력은 코딩력이라고 표현합니다.
 * 알고력과 코딩력은 0 이상의 정수로 표현됩니다.
 *
 * 문제를 풀기 위해서는 문제가 요구하는 일정 이상의 알고력과 코딩력이 필요합니다.
 *
 * 예를 들어, 당신의 현재 알고력이 15, 코딩력이 10이라고 가정해보겠습니다.
 *
 * A라는 문제가 알고력 10, 코딩력 10을 요구한다면 A 문제를 풀 수 있습니다.
 * B라는 문제가 알고력 10, 코딩력 20을 요구한다면 코딩력이 부족하기 때문에 B 문제를 풀 수 없습니다.
 *
 * 풀 수 없는 문제를 해결하기 위해서는 알고력과 코딩력을 높여야 합니다.
 * 알고력과 코딩력을 높이기 위한 다음과 같은 방법들이 있습니다.
 *
 * 알고력을 높이기 위해 알고리즘 공부를 합니다. 알고력 1을 높이기 위해서 1의 시간이 필요합니다.
 * 코딩력을 높이기 위해 코딩 공부를 합니다. 코딩력 1을 높이기 위해서 1의 시간이 필요합니다.
 *
 * 현재 풀 수 있는 문제 중 하나를 풀어 알고력과 코딩력을 높입니다.
 * 각 문제마다 문제를 풀면 올라가는 알고력과 코딩력이 정해져 있습니다.
 * 문제를 하나 푸는 데는 문제가 요구하는 시간이 필요하며 같은 문제를 여러 번 푸는 것이 가능합니다.
 * 당신은 주어진 모든 문제들을 풀 수 있는 알고력과 코딩력을 얻는 최단시간을 구하려 합니다.
 *
 * 초기의 알고력과 코딩력을 담은 정수 alp와 cop,
 * 문제의 정보를 담은 2차원 정수 배열 problems가 매개변수로 주어졌을 때,
 * 모든 문제들을 풀 수 있는 알고력과 코딩력을 얻는 최단시간을 return 하도록 solution 함수를 작성해주세요.
 *
 * 모든 문제들을 1번 이상씩 풀 필요는 없습니다. 입출력 예 설명을 참고해주세요.
 */