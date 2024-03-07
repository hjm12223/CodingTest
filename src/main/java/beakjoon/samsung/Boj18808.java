package beakjoon.samsung;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj18808 {
    static int[][] arr;
    static int row,column;
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String[] split = br.readLine().split(" ");

         row = Integer.parseInt(split[0]);
         column = Integer.parseInt(split[1]);
        int testCase = Integer.parseInt(split[2]);

        arr = new int[row][column];

        for (int k = 0 ; k < testCase ; k++){
            String[] sp = br.readLine().split(" ");
            int r = Integer.parseInt(sp[0]);
            int c = Integer.parseInt(sp[1]);
            int[][] tempArr = new int[r][c];
            makeTempArr(tempArr,r,c);
        }
    }
    /*
    1. 스티커를 회전시키지 않고 모눈종이에서 떼어낸다.
    2. 다른 스티커와 겹치거나 노트북을 벗어나지 않으면서 스티커를 붙일 수 있는 위치를 찾는다.
    혜윤이는 노트북의 위쪽부터 스티커를 채워 나가려고 해서,
    스티커를 붙일 수 있는 위치가 여러 곳 있다면 가장 위쪽의 위치를 선택한다.
    가장 위쪽에 해당하는 위치도 여러 곳이 있다면 그중에서 가장 왼쪽의 위치를 선택한다.
    3. 선택한 위치에 스티커를 붙인다. 만약 스티커를 붙일 수 있는 위치가 전혀 없어서 스티커를 붙이지 못했다면,
     스티커를 시계 방향으로 90도 회전한 뒤 2번 과정을 반복한다.
    4. 위의 과정을 네 번 반복해서 스티커를 0도, 90도, 180도, 270도 회전시켜 봤음에도 스티커를 붙이지 못했다면 해당 스티커를 붙이지 않고 버린다.
     */

    private static void makeTempArr(int[][] tempArr, int r, int c) throws IOException {
        for (int i = 0 ; i < r ; i++){
            String[] split = br.readLine().split(" ");
            for (int j = 0 ;j < c ; j++){
                tempArr[i][j] = Integer.parseInt(split[j]);
            }
        }
        rotate(tempArr);
    }

    private static int[][] rotate(int[][] tempArr) {
        int[][] temp =  new int[tempArr[0].length][tempArr.length];
        for (int i = 0 ; i< tempArr.length ; i++){
            for (int j = 0 ; j< tempArr[0].length ; j++){
                temp[j][tempArr.length-i-1] = tempArr[i][j];
            }
        }
        return temp;
    }
}

