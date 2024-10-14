package beakjoon.deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj5430 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int i =0; i < T; i++){
			int[] array;
			Deque<Integer> dq = new ArrayDeque<>();
			Queue<String> q = new LinkedList<>();
			boolean isError = false;
			boolean isOrder = true; // true = 정방향, false = 역방향
				String[] commands = br.readLine().split("");
				for (int k = 0 ; k < commands.length; k++){
					q.offer(commands[k]);
				}

			st = new StringTokenizer(br.readLine());

			int p = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());

			String s = st.nextToken();

			if (p != 0) {
				array = Arrays.stream(s.substring(1, s.length() - 1).split(","))
					.map(String::trim)
					.mapToInt(Integer::parseInt)
					.toArray();
			} else {
				array = new int[0];
			}
			for (int k : array) {
				dq.add(k);
			}

			while (!q.isEmpty()){
				String command = q.poll();
				if (Objects.equals(command, "D")){
					if (dq.isEmpty()){
						System.out.println("error");
						isError = true;
						break;
					}
					if (isOrder) {
						dq.pollFirst();
					}else{
						dq.pollLast();
					}
				} else if (command.equals("R")) {
					isOrder = !isOrder;
				}
			}
			int k = 0;
			int[] answer = new int[dq.size()];
			while (!dq.isEmpty()) {

				if (isOrder){
					answer[k] = dq.poll();
				}else {
					answer[k] = dq.pollLast();
				}
				k++;
			}
			if (!isError) {
				System.out.println(Arrays.toString(answer).replaceAll(" ", ""));
			}
		}
	}
}
