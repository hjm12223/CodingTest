package programmers.greedy.levelThree;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Camera {
    public static void main(String[] args) {
        int[][] routes = new int[][]{{-7, -0}, {-6, -4}, {-5, -3}, {-3, -1},{-1,4},{1,2},{3,4}}; // -4, -1, 2, 4
//        int[][] routes = new int[][]{{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        int solution = solution(routes);
        System.out.println("solution = " + solution);
    }
    public static int solution(int[][] routes) {
        Arrays.sort(routes, (i, j) -> i[0] - j[0]);
        Queue<Car> cars = new LinkedList<>();
        for (int[] route : routes) {
            Car car = new Car(route[0], route[1]);  // Car 객체를 생성후 해당 start 와 end 를 넣어줌
            cars.offer(car);
        }

        Set<Integer> cameras = new HashSet<>(); // 정답을 저장할 Set을 선언 중복을 배제하기 위해 사용
        while (!cars.isEmpty()) {
            Car car = cars.poll(); // 현재 Car
            int currentEnd = car.end; // 현재 Car 의 End 지점

            while (!cars.isEmpty() && cars.peek().start <= currentEnd) { // Cars 가 비워질때까지 그리고 cars의 제일 앞에 있는 start 가 현재 currentEnd 보다 커질때까지
                Car nextCar = cars.poll(); // nextCar 를 추출
                currentEnd = Math.min(currentEnd, nextCar.end); // 둘중 더 작은걸 currentEnd 로 재지정
            }

            cameras.add(currentEnd); // cuurentEnd 를 저장
        }
        System.out.println("cameras = " + cameras);
        return cameras.size();
    }

        public static class  Car{
            int start;
            int end;

            @Override
            public String toString() {
                return "Car{" +
                        "start=" + start +
                        ", end=" + end +
                        '}';
            }

            public Car(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }
    }

        /**
         * 고속도로를 이동하는 모든 차량이 고속도로를 이용하면서 단속용 카메라를 한 번은 만나도록 카메라를 설치하려고 합니다.
         *
         * 고속도로를 이동하는 차량의 경로 routes가 매개변수로 주어질 때, 모든 차량이 한 번은 단속용 카메라를 만나도록 하려면 최소 몇 대의 카메라를 설치해야 하는지를 return 하도록 solution 함수를 완성하세요.
         *
         * 제한사항
         *
         * 차량의 대수는 1대 이상 10,000대 이하입니다.
         * routes에는 차량의 이동 경로가 포함되어 있으며 routes[i][0]에는 i번째 차량이 고속도로에 진입한 지점, routes[i][1]에는 i번째 차량이 고속도로에서 나간 지점이 적혀 있습니다.
         * 차량의 진입/진출 지점에 카메라가 설치되어 있어도 카메라를 만난것으로 간주합니다.
         * 차량의 진입 지점, 진출 지점은 -30,000 이상 30,000 이하입니다.
         */



        /**
         * 구현실수, 시간복잡도, 구현방법, 알고리즘, 테스트케이스, 예외처리 (0,1, 마지막 번호), 예외케이스.. 하드코딩으로 넣어본다
         * DFS,BFS, DP -> 3차원 DP 가 가장어려웠음
         *
         * 최대한 한번에 풀기..
         */