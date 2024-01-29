package beakjoon.TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj13114 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int left = 1;
        int right = 1;
        int cnt = 0;
        int result = 0;

        Set<Integer> set = new HashSet<>();

        while (left <= n && right <= n) {
            int value = arr[right];
            if (set.contains(value)) {
                set.remove(arr[left]);
                cnt--;
                left++;
            } else {
                set.add(value);
                cnt++;
                right++;
                result += cnt;
            }
        }

        System.out.println(result);
    br.close();
    }

}
/*
길이가 N인 수열이 주어질 때, 수열에서 연속한 1개 이상의 수를 뽑았을 때
같은 수가 여러 번 등장하지 않는 경우의 수를 구하는 프로그램을 작성하여라.

1 1 1 1 1 -> 5
1 2 3 1 2 -> 12
1 2 3 4 5
1
1 2
1 2 3
1 2 3 4
1 2 3 4 5

2
2 3
2 3 4
2 3 4 5

3
3 4
3 4 5

4
4 5

5
----
1
1 2         3
1 2 3
---
2
2 3        3
2 3 1
----
3
3 1
3 1 2      3
----
1
1 2         2
----
2           1
 */