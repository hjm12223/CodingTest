package programmers.BfsDfs.LevelThree;

import javax.naming.CannotProceedException;
import java.util.Arrays;

public class WordConvert {
    static boolean[] visited;
    static int answer;
    static int count;
    public static void main(String[] args) {
        int solution = solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"});
        System.out.println("solution = " + solution);
    }
    public static int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        bfs(begin,target,words,0);
        return answer;
    }

    private static void bfs(String begin, String target, String[] words, int cnt) {
        if (begin.equals(target)){
            answer = cnt;
            return;
    }
        for (int i = 0 ; i < words.length; i++){
            System.out.println("visited = " + Arrays.toString(visited));
            System.out.println("begin = " + begin);
            if (visited[i])
                continue;
            int k = 0;
            for (int j = 0 ; j< begin.length(); j++){
                count++;
                if (begin.charAt(j) != words[i].charAt(j)){
                    k++;
                }
            }
            if (k == 1){
                visited[i] =true;
                bfs(words[i],target,words,cnt+1);
                visited[i] = false;
            }
        }

}
}
