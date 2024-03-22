package programmers.kakao.Level2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class Bracket {
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) {
        System.out.println(solution("()))((()"));
    }

    public static String solution(String p) {
        if (p.isEmpty()) return "";

        Queue<Character> q = new LinkedList<>();
        for (char c : p.toCharArray()) {
            q.add(c);
        }
        extracted(q);
        return answer.toString();
    }

    private static void extracted(Queue<Character> q) {
        StringBuilder u = new StringBuilder();
        System.out.println("q = " + q);
        int cnt = 0;
        while (!q.isEmpty()) {
            char curr = q.poll();
            if (curr == '(') {
                u.append(curr);
                cnt++;
            } else {
                cnt--;
                u.append(curr);
            }
            if (cnt == 0) {
                String uStr = u.toString();
                if (isProperBracket(uStr)) {
                    answer.append(uStr);
                } else {
                    answer.append('(');
                    if (uStr.length() > 2) {
                        String substring = uStr.substring(1, uStr.length() - 1);
                        Queue<Character> recursiveQ = new LinkedList<>();
                        char[] charArray = substring.toCharArray();
                        for (char c : charArray) {
                            recursiveQ.add(c);
                        }
                    }
                    answer.append(')');
                    for (int i = 1; i < u.length() - 1; i++) {
                        answer.append(u.charAt(i) == '(' ? ')' : '(');
                    }
                }

                System.out.println("answer = " + answer);
                u = new StringBuilder();
            }
        }
    }

    private static boolean isProperBracket(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            if (curr == '(') {
                cnt++;
            } else {
                cnt--;
                if (cnt < 0) return false;
            }
        }
        return cnt == 0;
    }
}

    /*
1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며,
 v는 빈 문자열이 될 수 있습니다.
3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
  3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
  4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
  4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
  4-3. ')'를 다시 붙입니다.
  4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
  4-5. 생성된 문자열을 반환합니다.
 */
