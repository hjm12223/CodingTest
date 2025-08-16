package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj1759_2 {
	static String[] chars = new String[] {"a", "e", "i", "o", "u"};
	static boolean[] visited;
	static int L, C;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		String[] strings = br.readLine().split(" ");
		visited = new boolean[C];
		Arrays.sort(strings);
		List<String> output = new ArrayList<>();
		dfs(strings, output, new StringBuilder(), 0);
		bw.flush();
		bw.close();
	}

	private static void dfs(String[] strings, List<String> output, StringBuilder sb, int idx) throws IOException {
		if (sb.length() == L) {
			if (check(sb) && check2(sb)) {
				bw.write(sb + "\n");
			}
			return;
		}
		for (int i = idx; i < C; i++) {
			if (!visited[i]) {
				sb.append(strings[i]);
				visited[i] = true;
				dfs(strings, output, sb, i);
				sb.deleteCharAt(sb.length() - 1);
				visited[i] = false;
			}
		}
	}

	private static boolean check(StringBuilder sb) {
		for (String ch : chars) {
			for (int i = 0; i < sb.length(); i++) {
				if (sb.charAt(i) == ch.charAt(0)) return true;
			}
		}
		return false;
	}

	private static boolean check2(StringBuilder sb) {
		int ja = 0;
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			boolean isJa = true;
			for (String aChar : chars) {
				if (c == aChar.charAt(0)) {
					isJa = false;
					break;
				}
			}
			if (isJa) ja++;
		}
		return ja >= 2;
	}
}
