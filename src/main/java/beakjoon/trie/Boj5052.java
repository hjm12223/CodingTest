package beakjoon.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
18:38
 */
public class Boj5052 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int k = 0; k < T; k++) {
			boolean isContain = false;

			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			Set<String> set = new HashSet<>();
			String[] arr = new String[n];
			for (int i = 0; i < n; i++) {

				st = new StringTokenizer(br.readLine());
				String string = st.nextToken();
				set.add(string);
				arr[i] = string;
			}

			for (int i = 0 ; i < set.size(); i++){
				if (isContain) break;
				String string = arr[i];
				for (int j = 1 ; j <= string.length() ; j++){
					String word = string.substring(0, j);
					if (set.contains(word) && !string.equals(word)){
						isContain = true;
						break;
					}
				}
			}
			if (!isContain) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
	}
}
