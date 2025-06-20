package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Boj2195 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String start = br.readLine();
		String target = br.readLine();
		int result = 0;
		int left = 0;
		Set<String> comb = makeComb(start);
		while (left < target.length()) {
			StringBuilder sb = new StringBuilder();
			while (left < target.length()) {
				sb.append(target.charAt(left++));
				if (!comb.contains(sb.toString())) {
					left--;
					sb.deleteCharAt(sb.length() - 1);
					break;
				}
			}
			result++;
		}
		System.out.println(result);
	}

	private static Set<String> makeComb(String start) {
		Set<String> result = new HashSet<>();
		for (int i = 0; i < start.length(); i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = i; j < start.length(); j++) {
				sb.append(start.charAt(j));
				result.add(sb.toString());
			}
		}
		return result;
	}
}

