package beakjoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj11501 {
	/*
	자료형 진짜 무조건 확인하기 자료형 떄문에 40분을 날림
	1시간
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		/*
		홍준이의 행동은 총 3가지이다
		1. 주식을 하나 산다
		2. 원하는 만큼 판매한다
		3. 아무것도 안한다

		 */
		int T = Integer.parseInt(st.nextToken());
		for (int k = 0; k < T; k++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] array = new int[N];
			String[] split = br.readLine().split(" ");

			for (int i = 0; i < N; i++) {
				array[i] = Integer.parseInt(split[i]);
			}

			long result = 0;
			int count = 0;
			long buyStock = 0;
			long maxStock = 0;
			for (int i = array.length - 1; i > 0; i--) {
				maxStock = Math.max(maxStock, array[i]);
				if (maxStock >= array[i - 1]) { // 다음날 주식이 현재 가격 보다 높으면
					buyStock += array[i - 1]; // 주식을 산다
					count++; // 산 주식을 카운트한다( 판매일에 한꺼번에 판매를 해야하기 때문이다 )
				} else {
					result += maxStock * count - buyStock;
					maxStock = 0;
					count = 0; // 이전의 카운트했던 수들은 다시 초기화한다
					buyStock = 0;
				}
			}
			result += maxStock * count - buyStock;
			bw.write(result + "\n");
		}
		bw.flush();
		bw.close();
	}
}
