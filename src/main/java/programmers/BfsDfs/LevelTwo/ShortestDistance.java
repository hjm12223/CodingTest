package programmers.BfsDfs.LevelTwo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistance {

    static int[] dx = new int[]{1,0,-1,0};
    static int[] dy = new int[]{0,1,0,-1};

    public static void main(String[] args) {
        int result = solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}});
        System.out.println("result = " + result);
    }

    /**
     * 최단 거리는 대부분 BFS
     */
    public static int solution(int[][] maps) {
        int answer = 0;
        int[][] isVisited = new int[maps.length][maps[0].length];
            bfs(maps,isVisited);
        answer = isVisited[maps.length-1][maps[0].length-1];
        return answer;
    }

    /**
     * PQ 사용 고려하기
     * @param maps
     * @param isVisited
     */
    private static void bfs(int[][] maps, int[][] isVisited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        isVisited[0][0] =1;

        while (!q.isEmpty()){
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];

            for (int i = 0 ; i < 4 ; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx > maps.length-1 || ny < 0|| ny > maps[0].length-1 )
                    continue;
                System.out.println("isVisited = " + Arrays.deepToString(isVisited));
                if (isVisited[nx][ny] == 0 && maps[nx][ny] == 1){
                    isVisited[nx][ny] = isVisited[x][y] +1;
                    q.add(new int[]{nx,ny});
                }
            }
        }
    }

}
