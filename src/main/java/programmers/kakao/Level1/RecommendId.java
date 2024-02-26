package programmers.kakao.Level1;

public class RecommendId {
    public static void main(String[] args) {
        String solution = solution("=.=");
        System.out.println("solution = " + solution);
    }
    public static String solution(String new_id) {
        String answer = new_id.toLowerCase();

        StringBuilder sb = new StringBuilder();
        for (char c : answer.toCharArray()) {
            if (Character.isLowerCase(c) || Character.isDigit(c) || c == '-' || c == '_' || c == '.') {
                sb.append(c);
            }
        }
        answer = sb.toString();

        answer = continueDot(answer);

        answer = firstLastDot(answer);

        if (answer.isEmpty()) {
            answer = "a";
        }

        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
            answer = lastDot(answer);
        }

        while (answer.length() <= 2) {
            answer += answer.charAt(answer.length() - 1);
        }

        return answer;
    }

    private static String continueDot(String str) {
        StringBuilder sb = new StringBuilder();
        char prev = ' ';
        for (char c : str.toCharArray()) {
            if (c != '.' || c != prev) {
                sb.append(c);
                prev = c;
            }
        }
        return sb.toString();
    }

    private static String firstLastDot(String str) {
        StringBuilder sb = new StringBuilder(str);
        if (sb.length() > 0 && sb.charAt(0) == '.') {
            sb.deleteCharAt(0);
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private static String lastDot(String str) {
        if (str.charAt(str.length() - 1) == '.') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}
/*
1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
 */