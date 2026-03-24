package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 남학생은 스위치 번호가 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꾼다. 즉, 스위치가 켜져 있으면 끄고, 꺼져 있으면 켠다.
// 여학생은 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서,
// 그 구간에 속한 스위치의 상태를 모두 바꾼다. 이때 구간에 속한 스위치 개수는 항상 홀수가 된다.
public class Boj1244 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken()); // 1 남자 2 여
			int idx = Integer.parseInt(st.nextToken());
			if (gender == 1) {
				for (int j = idx; j <= N; j += idx) {
					System.out.println(j);
					arr[j] = Math.abs(arr[j] - 1);
				}
			} else {
				int left = idx - 1;
				int right = idx + 1;
				arr[idx] = Math.abs(arr[idx] - 1);
				while (left > 0 && right <= N) {
					if (arr[left] == arr[right]) {
						arr[left] = Math.abs(arr[left--] - 1);
						arr[right] = Math.abs(arr[right++] - 1);
					} else break;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (i % 20 == 0) {
				sb.append(arr[i]).append(" ");
				sb.append("\n");
			} else {
				sb.append(arr[i]).append(" ");
			}
		}
		System.out.println(sb);
	}
}
