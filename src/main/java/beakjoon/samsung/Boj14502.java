package beakjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14502 {
    /**
     * 1. 벽의 갯수는 3개로 한정되어있다
     * 2. 만약 벽이 없을 경우 가장자리가 바이러스로 꽉 안채워져있을 경우에는 최대 만들 수 있는 안전지역은 3개
     * 그럼 어떻게 벽을 세우는 기준을 할까
     *
     * 처음 한 생각은 만약 바이러스가 꼭지점에 있을 경우 벽을 두개 사용해서 막을 수 있음
     *
     */
    static int[] dx = new int[]{1,0,-1,0};
    static int[] dy = new int[]{0,1,0,-1};

    static int column;
    static int row;

    static int[][] arr;

    static int MaxSafeZone =0;
    static Queue<Virus> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        column = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        arr = new int[column][row];


        for (int i = 0;  i< column; i ++){
            String[] line = br.readLine().split(" ");
            for (int j = 0 ; j < row; j++){
                arr[i][j] = Integer.parseInt(line[j]);
                }
            }


        dfs(0);
        System.out.println(MaxSafeZone);
    }

    private static void dfs(int wallCount) {

        if (wallCount == 3){
            bfs();
            return;
        }
        for (int i = 0 ; i < column ; i ++){
            for (int j = 0; j < row ; j++){
                if (arr[i][j] == 0){
                    arr[i][j] = 1;
                    dfs(wallCount+1);
                    System.out.println("wallCount = " + wallCount);
                    arr[i][j] = 0;
                }
            }
        }
    }

    private static void bfs() {
        int[][] copyArr = new int[column][row];
        for (int i = 0; i < column ; i++){
            copyArr[i] = arr[i].clone();
        }
        q = new LinkedList<>();
        for (int i = 0;  i< column; i ++){
            for (int j = 0 ; j < row; j++){
                if (arr[i][j] == 2){
                    q.offer(new Virus(i,j));
                }
            }
        }

        while (!q.isEmpty()){
            Virus curr = q.poll();
            for (int i = 0 ; i < 4 ; i++){
                int ny = dy[i] + curr.c;
                int nx = dx[i] +  curr.r;
                if (nx < 0 || ny < 0 || nx >= row || ny >= column) continue;
                if (copyArr[ny][nx] == 0){
                    copyArr[ny][nx] = 2;
                    q.offer(new Virus(ny,nx));
                }
            }
        }
        SafeZone(copyArr);
    }

    private static void SafeZone(int[][] copyArr) {
        int safeZone = 0;
        for (int[] ints : copyArr) {
            for (int anInt : ints) {
                if (anInt == 0){
                    safeZone ++;
                }
            }
        }
        MaxSafeZone = Math.max(safeZone,MaxSafeZone);
    }


    private static class Virus {
        int c;
        int r;

        public Virus(int c, int r) {
            this.c = c;
            this.r = r;
        }
    }
}
