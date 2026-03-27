package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Boj2204 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) return;
			List<String> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				list.add(br.readLine());
			}
			Collections.sort(list, (o1, o2) -> o1.toLowerCase(Locale.ROOT).compareTo(o2.toLowerCase(Locale.ROOT)));
			System.out.println(list.get(0));
		}
	}
}
