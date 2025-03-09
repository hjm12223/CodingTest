package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj11728 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>(1_000_001);
		int[] arr1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] arr2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int i : arr1) {
			list.add(i);
		}
		for (int i : arr2) {
			list.add(i);
		}
		list.sort((o1, o2) -> o1 - o2);
		StringBuilder sb = new StringBuilder();
		for (Integer i : list) {
			sb.append(i + " ");
		}
		System.out.println(sb.toString());
	}
}
