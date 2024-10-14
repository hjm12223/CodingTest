package beakjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

import com.sun.source.tree.Tree;

public class Boj7662 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken()); // 테스트 케이스

		for (int i = 0; i < T; i++) {

			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken()); // 연산을 나타내는 문자와 정수 n 이 주어지는 갯수
			TreeMap<Long, Integer> treeMap = new TreeMap<>();

			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				String command = st.nextToken();
				long value = Long.parseLong(st.nextToken());

				if (command.equals("I")) {
					treeMap.put(value,treeMap.getOrDefault(value,0)+1);
				} else if(!treeMap.isEmpty()){
					long key = value == 1 ? treeMap.lastKey() : treeMap.firstKey();
					int count = treeMap.get(key);
					if (count == 1){
						treeMap.remove(key);
					}else{
						treeMap.put(key, count-1);
					}
				}
			}
			if (treeMap.isEmpty()) {
				System.out.println("EMPTY");
			} else {
				System.out.println(treeMap.lastKey() + " " + treeMap.firstKey());
			}
		}
	}
}
