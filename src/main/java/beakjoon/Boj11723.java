package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj11723 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		List<Integer> list = new ArrayList<>();
		System.out.println(list);
		Set<Integer> set = new HashSet<>(list);
		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			switch (command) {
				case "add":
					int addValue = Integer.parseInt(st.nextToken());
					set.add(addValue);
					break;
				case "check":
					if (set.contains(Integer.parseInt(st.nextToken()))) {
						bw.write(1 + "\n");
					} else {
						bw.write(0 + "\n");
					}
					break;
				case "remove":
					int removeValue = Integer.parseInt(st.nextToken());
					set.remove(removeValue);
					break;
				case "toggle":
					int value = Integer.parseInt(st.nextToken());
					if (set.contains(value)) {
						set.remove(value);
					} else {
						set.add(value);
					}
					break;
				case "all":
					set.addAll(list);
					break;
				case "empty":
					set.clear();
			}
		}
		bw.flush();
		bw.close();
	}
}
