package programmers;

public class Miro {
	public static void main(String[] args) {
		String solution = solution(3, 4, 2, 3, 3, 1, 5);
		System.out.println(solution);
	}

	public static String solution(int n, int m, int x, int y, int r, int c, int k) {
		StringBuilder result = new StringBuilder();
		char[][] miro = new char[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				miro[i][j] = '.';
			}
		}
		miro[x][y] = 'S';
		miro[r][c] = 'E';
		
		String answer = "";
		return answer;
	}
}
