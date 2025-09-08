package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj13022 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String wolf = br.readLine();
		int pointer = 0;
		while (pointer < wolf.length()) {
			int[] w = isW(wolf, pointer);
			int cnt = 0;

			if (w[0] == 0) {
				System.out.println(0);
				return;
			} else {
				pointer = w[1];
				cnt = w[0];
			}
			int[] o = isO(wolf, pointer);
			if (o[0] != cnt) {
				System.out.println(0);
				return;
			} else {
				pointer = o[1];
			}
			int[] l = isl(wolf, pointer);
			if (l[0] != cnt) {
				System.out.println(0);
				return;
			} else {
				pointer = l[1];
			}
			int[] f = isf(wolf, pointer);
			if (f[0] != cnt) {
				System.out.println(0);
				return;
			} else {
				pointer = f[1];
			}
		}
		System.out.println(1);
	}

	private static int[] isl(String wolf, int pointer) {
		int lCnt = 0;
		for (int i = pointer; i < wolf.length(); i++) {
			if (wolf.charAt(i) == 'l') {
				lCnt++;
				pointer++;
			} else {
				break;
			}
		}
		return new int[] {lCnt, pointer};
	}

	private static int[] isf(String wolf, int pointer) {
		int fCnt = 0;
		for (int i = pointer; i < wolf.length(); i++) {
			if (wolf.charAt(i) == 'f') {
				fCnt++;
				pointer++;
			} else {
				break;
			}
		}
		return new int[] {fCnt, pointer};
	}

	private static int[] isO(String wolf, int pointer) {
		int oCnt = 0;
		for (int i = pointer; i < wolf.length(); i++) {
			if (wolf.charAt(i) == 'o') {
				oCnt++;
				pointer++;
			} else {
				break;
			}
		}
		return new int[] {oCnt, pointer};
	}

	private static int[] isW(String wolf, int pointer) {
		int wCnt = 0;
		for (int i = pointer; i < wolf.length(); i++) {
			if (wolf.charAt(i) == 'w') {
				wCnt++;
				pointer++;
			} else {
				break;
			}
		}
		return new int[] {wCnt, pointer};
	}
}
