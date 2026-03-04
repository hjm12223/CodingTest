package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Boj20154 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = new int[] {3, 2, 1, 2, 3, 3, 3, 3, 1, 1, 3, 1, 3, 3, 1, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 1};
		Map<Character, Integer> map = new HashMap<>();
		for (char i = 'A'; i <= 'Z'; i++) {
			map.put(i, arr[i - 'A']);
		}
		char[] alphabets = br.readLine().toCharArray();
		int result = 0;
		for (int i = 0; i < alphabets.length; i++) {
			result += map.get(alphabets[i]);
		}
		System.out.println(result % 10 % 2 == 1 ? "I'm a winner!" : "You're the winner?");
	}
}
