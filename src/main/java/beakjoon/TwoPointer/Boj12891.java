package beakjoon.TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj12891 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int S = Integer.parseInt(st.nextToken()); // DNA 문자열의 길이 S
		int P = Integer.parseInt(st.nextToken()); // DNA 를 만들어야하는 문자길이 P

		int[] arr = new int[4];

		char[] dna = br.readLine().toCharArray();

		Map<Character, Integer> map = new HashMap(); // A,C,G,T 를 넣을 맵

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 4; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println("arr = " + Arrays.toString(arr));
	}
}
