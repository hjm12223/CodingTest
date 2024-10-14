package beakjoon.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1629 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		long a = Integer.parseInt(st.nextToken());
		long b = Integer.parseInt(st.nextToken());
		long m = Integer.parseInt(st.nextToken());

		long pow = pow(a,b,m);
		System.out.println(pow);
	}

	public static long pow(long a, long b, long m){
		if (b < 1){
			return a % m;
		}
		long value = pow(a, b / 2, m);
		value = value * value % m;
		if (b % 2 == 0) return value;
		return value * a % m;
	}
}
