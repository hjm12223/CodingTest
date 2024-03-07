package programmers.kakao.Level2;
import java.util.Arrays;

public class ArcherCompetition2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));
        System.out.println(Arrays.toString(solution(1, new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0})));
        System.out.println(Arrays.toString(solution(9, new int[]{0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1})));
        System.out.println(Arrays.toString(solution(10, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3})));
    }
    static int maxScoreDiff = Integer.MIN_VALUE;
    static int[] answer = new int[11];

    static class Score {
        int[] scores;
        int total;

        Score(int[] scores) {
            this.scores = scores.clone();
            for (int i = 0; i < scores.length; i++) {
                total += (10 - i) * scores[i];
            }
        }

        void hit(int idx) {
            scores[idx]++;
            total += 10 - idx;
        }

        void miss(int idx) {
            scores[idx]--;
            total -= 10 - idx;
        }

        int scoreDiff(Score other) {
            return this.total - other.total;
        }
    }

    public static void combination(int[] info, int arrows, int idx, int cnt, Score ryan, Score apeach) {
        if (cnt == arrows) {
            int diff = ryan.scoreDiff(apeach);
            if (diff > maxScoreDiff) {
                maxScoreDiff = diff;
                answer = ryan.scores.clone();
            }
            return;
        }

        for (int i = idx; i <= 10; i++) {
            if (info[i] < 10) {
                Score newRyan = new Score(ryan.scores);
                newRyan.hit(i);
                combination(info, arrows, i, cnt + 1, newRyan, apeach);
            }
        }
    }

    public static int[] solution(int n, int[] info) {
        Score ryan = new Score(new int[11]);
        Score apeach = new Score(info);
        combination(info, n, 0, 0, ryan, apeach);

        if (maxScoreDiff <= 0) {
            return new int[]{-1};
        } else {
            return answer;
        }
    }

}