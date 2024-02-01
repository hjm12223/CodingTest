package beakjoon.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Boj2457 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = 1;
        int temp = 0;
        int n = sc.nextInt();
        List<Flower> list = new ArrayList<>();
        for (int i = 0 ; i< n ; i++){
            Flower flower = new Flower(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt());
            list.add(flower);
        }


        Collections.sort(list,(i,j) -> {
            if (i.startMonth == j.startMonth) return Integer.compare(i.endMonth,j.endMonth);
            else return Integer.compare(i.startMonth, j.startMonth);        });

        System.out.println("list = " + list);

        for (int i = 1; i < list.size(); i++) {
            Flower currFlower = list.get(i);
            Flower preFlower = list.get(i - 1);
            temp = i + 1;
            System.out.println("curr = " + currFlower);
            if (currFlower.startMonth == preFlower.startMonth && currFlower.startDay <= preFlower.endDay) {
                preFlower.endDay = Math.max(preFlower.endDay, currFlower.endDay);
                continue;
            }

            while (true) {
                if (temp == n) {
                    cnt++;
                    break;
                }

                Flower nextFlower = list.get(temp);

                if (currFlower.endMonth > nextFlower.startMonth ||
                        (currFlower.endMonth == nextFlower.startMonth && currFlower.endDay >= nextFlower.startDay)) {
                    temp++;
                } else {
                    cnt++;
                    break;
                }
            }

            i = temp - 1;
            if (temp == n) break;
        }
        System.out.println(cnt);
    }

    /*
                while 문 안에서의 조건들
                첫번째 현재 curr 의 endMonth 가  nextMonth의 startMonth 보다 크거나 같은지 확인
                만약 클 경우 그 다음달을 가져옴
                같을 경우에도 그 다음달을 가져와 nextMonth 와 비교 해서 " 그 다음 달 " 이 현재 curr 의 EndMonth 와 endDay 이내에 존재하는지 확인
                존재 할 경우 그달 다시 임시 변수로 두고
                그다음달을 확인 만약 그 다음달이 범위안에 존재하지 않을 경우
                그 달을 선정해서 i = temp 로 변경해주고 break문을 걸어줌

                그러면 다시 즉 그 달로부터 시작해서 다시 로직을 수행

                만약 temp가 n-1이 되었을 경우 모든 로직을 다 수행하였기 떄문에 break를 걸어줌

                그러면
                아닐 경우
                 */
/*
즉 3월 1일부터 11월 30일까지 매일 꽃이 한 가지 이상 피어 있도록 꽃들을 선택할 때, 선택한 꽃들의 최소 개수를 출력하는 프로그램을 작성하시오.
 */
    private static class Flower {
        int startMonth;
        int startDay;
        int endMonth;
        int endDay;

        @Override
        public String toString() {
            return "Flower{" +
                    "startMonth=" + startMonth +
                    ", startDay=" + startDay +
                    ", endMonth=" + endMonth +
                    ", endDay=" + endDay +
                    '}';
        }

        public Flower(int startMonth, int startDay, int endMonth, int endDay) {
            this.startMonth = startMonth;
            this.startDay = startDay;
            this.endMonth = endMonth;
            this.endDay = endDay;
        }
    }
}
/**
 * 오늘은 공주님이 태어난 경사스러운 날이다. 왕은 이 날을 기념하기 위해 늘 꽃이 피어있는 작은 정원을 만들기로 결정했다.
 *
 * 총 N개의 꽃이 있는 데, 꽃은 모두 같은 해에 피어서 같은 해에 진다.
 * 하나의 꽃은 피는 날과 지는 날이 정해져 있다.
 * 예를 들어,
 * 5월 8일 피어서 6월 13일 지는 꽃은 5월 8일부터 6월 12일까지는 꽃이 피어 있고,
 * 6월 13일을 포함하여 이후로는 꽃을 볼 수 없다는 의미이다. (올해는 4, 6, 9, 11월은 30일까지 있고, 1, 3, 5, 7, 8, 10, 12월은 31일까지 있으며, 2월은 28일까지만 있다.)
 *
 * 이러한 N개의 꽃들 중에서 다음의 두 조건을 만족하는 꽃들을 선택하고 싶다.
 *
 * 공주가 가장 좋아하는 계절인 3월 1일부터 11월 30일까지 매일 꽃이 한 가지 이상 피어 있도록 한다.
 * 정원이 넓지 않으므로 정원에 심는 꽃들의 수를 가능한 적게 한다.
 * N개의 꽃들 중에서 위의 두 조건을 만족하는,
 * 즉 3월 1일부터 11월 30일까지 매일 꽃이 한 가지 이상 피어 있도록 꽃들을 선택할 때,
 * 선택한 꽃들의 최소 개수를 출력하는 프로그램을 작성하시오.
 */