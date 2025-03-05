package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Boj1213 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();// 팰린드룸이란 앞에서 시작하고 뒤에서도 시작했을때 똑같은 문자열을 팰린드룸이라고 한다
		Map<Character, Integer> map = new TreeMap<>();
		char[] chars = line.toCharArray();
		int oddCnt = 0;
		char oddChar = 0;
		for (char c : chars) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				oddCnt++;
				oddChar = entry.getKey();
			}
		}
		if (oddCnt > 1) {
			System.out.println("I'm Sorry Hansoo");
			return;
		}
		StringBuilder half = new StringBuilder();
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			half.append(String.valueOf(entry.getKey()).repeat(entry.getValue() / 2));
		}
		StringBuilder result = new StringBuilder(half);
		if (oddCnt == 1) {
			result.append(oddChar);
		}
		result.append(half.reverse());
		System.out.println(result);
	}
}
