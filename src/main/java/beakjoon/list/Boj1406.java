package beakjoon.list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1406 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));



		StringBuilder st = new StringBuilder(br.readLine());

		int M = Integer.parseInt(br.readLine());
		int cursor = st.length();
		for (int i = 0 ; i < M; i++){
			String s = br.readLine();

			if (s.equals("L")) {
				if (!(cursor == 0)) {
					cursor -= 1;
				}
			}
			if (s.equals("D")){
				if (!(cursor == st.length())) {
					cursor += 1;
				}
			}
			if (s.equals("B")){
				if (!(cursor == 0)) {
					st.delete(cursor - 1, cursor);
					cursor -= 1;
				}
			}
			if (s.length() > 1){
				String[] split = s.split(" ");
				st.insert(cursor,split[1]);
				cursor+=1;
			}
		}
		System.out.println(st);
	}
}
