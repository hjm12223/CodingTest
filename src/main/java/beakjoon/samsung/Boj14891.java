package beakjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj14891 {
	static int[][] gears;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gears = new int[5][8];
		for (int i = 1; i < 5; i++) {
			gears[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}

		int n = Integer.parseInt(br.readLine()); // 회전 횟수
		for (int k = 0; k < n; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Map<Integer, Evidence> possible = new HashMap<>();
			int index = Integer.parseInt(st.nextToken()); // 돌릴 톱니바퀴 번호
			int forward = Integer.parseInt(st.nextToken()); // 회전 방향 (1 = 시계, -1 = 반시계)

			possible.put(index, new Evidence(true, forward)); // 현재 톱니바퀴 회전 설정
			checkPossible(index, possible); // 회전 가능한 톱니 확인

			for (int i = 1; i <= 4; i++) {
				Evidence evidence = possible.get(i);
				if (evidence != null && evidence.isRotate) {
					if (evidence.forward == 1) {
						gears[i] = Rrotate(gears[i]);
					} else if (evidence.forward == -1) {
						gears[i] = Lrotate(gears[i]);
					}
				}
			}
		}
		cal(gears);
	}

	private static void cal(int[][] gears) {
		int x = 1;
		int result = 0;
		for (int i = 1; i <= 4; i++) {
			result += gears[i][0] * x;
			x *= 2;
		}
		System.out.println(result);
	}

	private static void checkPossible(int index, Map<Integer, Evidence> possible) {
		// 인덱스 기준 오른쪽
		for (int i = index; i < 4; i++) {
			if (gears[i][2] != gears[i + 1][6] && possible.get(i).isRotate) {
				possible.put(i + 1, new Evidence(true, -possible.get(i).forward));
			} else {
				break;
			}
		}

		// 인덱스 기준 왼쪽
		for (int i = index; i > 1; i--) {
			if (gears[i][6] != gears[i - 1][2] && possible.get(i).isRotate) {
				possible.put(i - 1, new Evidence(true, -possible.get(i).forward));
			} else {
				break;
			}
		}
	}

	// 시계
	private static int[] Rrotate(int[] gear) {
		int value = gear[7];
		int[] tempArr = new int[gear.length];
		System.arraycopy(gear, 0, tempArr, 1, gear.length - 1);
		tempArr[0] = value;
		return tempArr;
	}

	// 반시계
	private static int[] Lrotate(int[] gear) {
		int firstValue = gear[0];
		int[] tempArr = new int[gear.length];
		System.arraycopy(gear, 1, tempArr, 0, gear.length - 1);
		tempArr[gear.length - 1] = firstValue;
		return tempArr;
	}

	public static class Evidence {
		boolean isRotate;
		int forward;

		public Evidence(boolean isRotate, int forward) {
			this.isRotate = isRotate;
			this.forward = forward;
		}

		@Override
		public String toString() {
			return "Evidence{" +
				"isRotate=" + isRotate +
				", forward=" + forward +
				'}';
		}
	}
}
