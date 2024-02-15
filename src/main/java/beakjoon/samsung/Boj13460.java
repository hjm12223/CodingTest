package beakjoon.samsung;

import java.io.*;
import java.util.*;

public class Boj13460 {
    static Queue<Node> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int column = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());

        char[][] arr = new char[column][row];
        Queue<Node> q = new LinkedList<>();
        /*
        BFS 를 통해서 먼저 해당 목적지를 찾은 후 BFS 를 통해 움직인 방향을 저장
        저장한 뒤 다시 while 문을 돌려 Blue 도 똑같은 움직임을 줌
         */

        for (int i = 0 ; i< column ; i++){
            String line = br.readLine();
            for (int j = 0 ; j < row ; j++){
                arr[i][j]  = line.charAt(j);
                if (arr[i][j] == 'R'){
                    q.offer(new Node(i,j));
                } else if (arr[i][j] == 'B') {
                    q.offer(new Node(i,j));
                }
            }
//            bfs();
        }

        System.out.println("arr = " + Arrays.deepToString(arr));

    }
    private static class Node{
        public Node(int c, int r) {
            this.c = c;
            this.r = r;
        }

        int c;
        int r;

    }
}
