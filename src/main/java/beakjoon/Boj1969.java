package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1969 {
	static String[] dnas = new String[] {"a", "c", "g", "t"};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String[] dnaStrings = new String[N];
		for (int i = 0; i < N; i++) {
			dnaStrings[i] = br.readLine();
		}
		
		StringBuilder result = new StringBuilder();
		int totalHammingDistance = 0;

		for (int i = 0; i < M; i++) {
			int[] count = new int[4];
			for (int j = 0; j < N; j++) {
				char c = dnaStrings[j].charAt(i);
				if (c == 'a') count[0]++;
				else if (c == 'c') count[1]++;
				else if (c == 'g') count[2]++;
				else count[3]++;
			}
			int maxIndex = 0;
			for (int k = 1; k < 4; k++) {
				if (count[k] > count[maxIndex]) maxIndex = k;
			}
			char[] dnaBases = {'A', 'C', 'G', 'T'};
			char selected = dnaBases[maxIndex];
			result.append(selected);

			for (int j = 0; j < N; j++) {
				if (Character.toUpperCase(dnaStrings[j].charAt(i)) != selected) totalHammingDistance++;
			}
		}

		System.out.println(result);
		System.out.println(totalHammingDistance);

	}
}
