package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Boj26122 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();
		List<Integer> list = new ArrayList<>();
		int cnt = 1;
		for (int i = 1; i < N; i++) {
			if (arr[i] == arr[i - 1]) {
				cnt++;
			} else {
				list.add(cnt);
				cnt = 1;
			}
		}
		list.add(cnt);
		int result = 0;
		for (int i = 1; i < list.size(); i++) {
			result = Math.max(Math.min(list.get(i), list.get(i - 1)) * 2, result);
		}
		System.out.println(result);
	}
}
