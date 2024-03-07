package beakjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj14891 {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[4][8];
        for (int i = 0  ; i< 4 ; i++){
            String line = br.readLine();
            for (int j = 0 ; j < 8 ; j++){
                arr[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        int k = Integer.parseInt(br.readLine());
        for (int i = 0 ; i< k ; i++){
            String[] command = br.readLine().split(" ");
            int number = Integer.parseInt(String.valueOf(command[0]))-1;
            int direction= Integer.parseInt(String.valueOf(command[1]));
            nextSpin(number, direction);
            preSpin(number,direction);
        }
       int result = (arr[0][0]) + (arr[1][0] * 2 ) +(arr[2][0] * 4 ) +(arr[3][0] * 8 );
        System.out.println(result);
    }

    private static void preSpin(int number, int direction) {
        int preGear = number - 1;
        if (preGear >= 0 && arr[preGear][2] != arr[number][6]) {
            preSpin(preGear, direction * -1);
            rotate(preGear, direction * -1);
        }
    }

    /*
   먼저 해당 톱니바퀴 양쪽에 있는 (3,7) 부분이 맞닿은 톱니바퀴가 돌아가는지의 여부를 확인
   만약 돌아가지 극이 같아 돌아가지 않는다면 해당 톱니바퀴는 continue;
   만약 돌아간다면 해당 number 의 저장된 톱니바퀴 배열을 direction 방향으로 회전시킨 후
    */
    private static void nextSpin(int number, int direction){
        int nextGear = number + 1;
        if (nextGear < 4 && arr[nextGear][6] != arr[number][2]) {
            nextSpin(nextGear, direction * -1);
            rotate(nextGear, direction * -1);
        }
    }


    private static void rotate(int gearIndex, int direction) {
        if (direction == 1) {
            int temp = arr[gearIndex][7];
            System.arraycopy(arr[gearIndex], 0, arr[gearIndex], 1, 7);
            arr[gearIndex][0] = temp;
        } else {
            int temp = arr[gearIndex][0];
            System.arraycopy(arr[gearIndex], 1, arr[gearIndex], 0, 7);
            arr[gearIndex][7] = temp;
        }
    }
}
/*
N극은 0 S극은 1

3번쨰와 7번쨰 숫자를 기준으로 시계 반시계방향으로 돌리면 된다.

첫번째 정수는 회전시킨 톱니바퀴의 번호, 두번째는 방향 1 은 시계 -1은 반시계
10101111
01111101
11001110
00000010
 */