package programmers.bruteForce.levelTwo;

public class dungeonsDFS {
    static boolean[] visited;
    static int count = 0;
    public static void main(String[] args) {
        int solution = solution(80, new int[][]{{80, 20}, {50, 40}, {30, 10}});
        System.out.println("solution = " + solution);
    }
    private static int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(0,k,dungeons);
        return count;
    }

    private static void dfs(int depth, int k, int[][] dungeons) {
        for (int i = 0 ; i< dungeons.length ;i++){
            if (visited[i] || dungeons[i][0] > k){
                continue;
            }
            visited[i] = true;
            dfs(depth +1,k - dungeons[depth][1] , dungeons);
            visited[i] = false;
        }
        count= Math.max(count,depth);
    }
}
