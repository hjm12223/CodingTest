package beakjoon.list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj5397 {
	static int cursor;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int k = 0; k < T; k++) {
			StringBuilder sb = new StringBuilder();
			String arr = br.readLine();
			cursor = 0;
			List<Character> list = new LinkedList<>();

			for (int i = 0; i < arr.length(); i++) {
				if (!hasMove(arr.charAt(i), list)) {
					list.add(cursor, arr.charAt(i));
					cursor++;
				}
			}
			for (Character s : list) {
				sb.append(s);
			}
			System.out.println(sb);
		}
	}

	private static boolean hasMove(Character str, List<Character> list) {
		switch (str) {
			case '>':
				if (cursor != list.size())
					cursor++;
				return true;
			case '<':
				if (cursor != 0) {
					cursor--;
				}
				return true;
			case '-':
				if (cursor == 0) {
					if (!list.isEmpty()) {
						list.remove(cursor);
					}
					return true;
				} else {
					list.remove(--cursor);
				}
				return true;
		}
		return false;
	}
}
