package beakjoon.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());  // 1부터 N까지의 자연수
		int M = Integer.parseInt(st.nextToken());  // 길이가 M인 수열

		int[] arr = new int[M];  // 결과 수열을 저장할 배열
		boolean[] isVisited = new boolean[N + 1];  // 방문 여부를 체크하는 배열

		permute(N, M, arr, isVisited, 0);
	}

	// 순열을 생성하는 백트래킹 함수
	public static void permute(int N, int M, int[] arr, boolean[] isVisited, int depth) {
		if (depth == M) {  // 수열이 M개의 숫자로 완성된 경우 출력
			for (int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (!isVisited[i]) {  // 해당 숫자가 사용되지 않은 경우
				isVisited[i] = true;  // 숫자를 선택하고 방문 표시
				arr[depth] = i;  // 수열에 숫자를 넣음
				permute(N, M, arr, isVisited, depth + 1);  // 다음 위치로 이동
				isVisited[i] = false;  // 백트래킹을 위해 선택한 숫자를 다시 풀어줌
			}
		}
	}
}