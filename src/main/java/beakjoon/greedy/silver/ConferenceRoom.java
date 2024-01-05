package beakjoon.greedy.silver;

import java.io.InputStream;
import java.util.*;

public class ConferenceRoom {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);

            int N = in.nextInt();

            int[][] rooms = new int[N][2];

            for (int i = 0; i < N; i ++){
                rooms[i][0] = in.nextInt();
                rooms[i][1] = in.nextInt();
            }

            Arrays.sort(rooms,(o1, o2) -> {
                if (o1[1]==o2[1]) return Integer.compare(o1[0],o2[0]);
                else return Integer.compare(o1[1],o2[1]); });

            int count = 0;
            int endTime = 0;

            for (int i = 0 ; i<  N ; i++){

                if (endTime <= rooms[i][0]){
                    endTime = rooms[i][1];
                    count++;
                }
            }
            System.out.println(count);
        }
    }
