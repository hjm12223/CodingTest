package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Boj4195 {
	static int[] parent;
	static int[] size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while (T-- > 0) {
			int F = Integer.parseInt(br.readLine()); // 친구 관계의 수
			Map<String, Integer> map = new TreeMap<>();
			List<String> list = new ArrayList<>();
			Set<String> set = new HashSet<>();

			for (int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String from = st.nextToken();
				String to = st.nextToken();
				set.add(from);
				set.add(to);
				list.add(from);
				list.add(to);
			}
			parent = new int[set.size() + 1];
			size = new int[set.size() + 1];

			int idx = 1;
			for (String value : set) {
				parent[idx] = idx;
				size[idx] = 1;
				map.put(value, idx++);
			}
			for (int i = 0; i < list.size(); i += 2) {
				String from = list.get(i);
				String to = list.get(i + 1);
				Integer fromIdx = map.get(from);
				Integer toIdx = map.get(to);
				int networkSize = union(fromIdx, toIdx);
				bw.write(networkSize + "\n");
			}
		}
		bw.flush();
		bw.close();
	}

	private static int union(Integer fromIdx, Integer toIdx) {
		int parent_a = find(fromIdx);
		int parent_b = find(toIdx);
		if (parent_a != parent_b) {
			parent[parent_b] = parent_a;
			size[parent_a] += size[parent_b];
		}
		return size[parent_a];
	}

	private static int find(int a) {
		if (a != parent[a]) {
			parent[a] = find(parent[a]);
		}
		return parent[a];
	}
}
