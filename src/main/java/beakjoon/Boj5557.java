package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj5557 {
	static long result = 0;
	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		System.out.println(result);
	}
}
