package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1436_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int count = 0;
		int num = 666;

		while (true) {
			if (String.valueOf(num).contains("666")) {
				count++;
			}

			if (count == N) {
				System.out.println(num);
				break;
			}

			num++;
		}
	}
}
/*
1 666
2 1666
3 2666
4 3666
5 4666
6 5666
7 6666
8 7666
9 8666
10 9666
11 10666
 */
