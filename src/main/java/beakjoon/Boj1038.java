package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Boj1038 {
	static List<Long> comb = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i <= 9; i++) {
			makeComb(i, i);
		}
		Collections.sort(comb);
		if (N < comb.size()) {
			System.out.println(comb.get(N));
		} else {
			System.out.println(-1);
		}
	}

	private static void makeComb(long num, int digit) {
		comb.add(num);
		for (int i = 0; i < digit; i++) {
			makeComb(num * 10 + i, i);
		}
	}
}
