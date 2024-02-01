package programmers.kakao.Level1;

import java.time.Month;
import java.util.*;

public class Personal {
    public static void main(String[] args) {
//        int[] solution = solution("2022.05.19", new String[]{"A 6", "B 12", "C 3"}, new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"});
//        int[] solution = solution("2020.01.01", new String[]{"Z 3", "D 5"}, new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"});
        solution("2009.12.31", new String[]{"A 13"}, new String[]{"2008.11.03 A"});
//        int[] solution = solution("2023.01.01", new String[]{"A 6"}, new String[]{"2022.07.01 A", "2022.07.01 A", "2022.07.01 A"});


    }

    public static int[] solution(String today, String[] terms, String[] privacies) {

        Map<String, Integer> expire = new HashMap<>();
        String[] todayArr = today.split("\\.");

        List<Integer> result = new ArrayList<>();
        int cnt = 0;
        for (String term : terms) {
            String[] strings = term.split(" ");
            expire.put(strings[0], Integer.parseInt(strings[1]));
        }
        for (String privacy : privacies) {
            cnt++;
            String[] pre = privacy.split(" ");

            String[] day = pre[0].split("\\.");// 해당 계약을 체결한 일자
            if (Integer.parseInt(day[1]) + expire.get(pre[1]) >= 13) {
                day[0] = String.valueOf(Integer.parseInt(day[0]) + (Integer.parseInt(day[1]) + expire.get(pre[1])) / 12);
                day[1] = String.valueOf((Integer.parseInt(day[1]) + expire.get(pre[1])) % 12);
            }else {
                day[1] = String.valueOf(Integer.parseInt(day[1]) + expire.get(pre[1]));
            }

            if (day[1].equals("0")){
                day[0] = String.valueOf(Integer.parseInt(day[0]) -1);
                day[1] = "12";
            }
            if (Integer.parseInt(day[0]) < Integer.parseInt(todayArr[0])) {
                result.add(cnt);
            } else if (Integer.parseInt(day[0]) == Integer.parseInt(todayArr[0]) && Integer.parseInt(day[1]) < Integer.parseInt(todayArr[1])) {
                result.add(cnt);
            } else if (Integer.parseInt(day[0]) == Integer.parseInt(todayArr[0]) && Integer.parseInt(day[1])  == Integer.parseInt(todayArr[1]) &&  Integer.parseInt(day[2]) <= Integer.parseInt(todayArr[2])) {
                result.add(cnt);
            }
        }
        System.out.println("result = " + result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    // pre[1] 현재의 계약종류 expire의 저장된 값
}
/**
 * 고객의 약관 동의를 얻어서 수집된 1~n번으로 분류되는 개인정보 n개가 있습니다.
 * 약관 종류는 여러 가지 있으며 각 약관마다 개인정보 보관 유효기간이 정해져 있습니다.
 * 당신은 각 개인정보가 어떤 약관으로 수집됐는지 알고 있습니다.
 * 수집된 개인정보는 유효기간 전까지만 보관 가능하며,
 * 유효기간이 지났다면 반드시 파기해야 합니다.
 *
 * 예를 들어, A라는 약관의 유효기간이 12 달이고,
 * 2021년 1월 5일에 수집된 개인정보가 A약관으로 수집되었다면 해당 개인정보는 2022년 1월 4일까지 보관 가능하며
 * 2022년 1월 5일부터 파기해야 할 개인정보입니다.
 * 당신은 오늘 날짜로 파기해야 할 개인정보 번호들을 구하려 합니다.
 *
 * 모든 달은 28일까지 있다고 가정합니다.
 *
 * 다음은 오늘 날짜가 2022.05.19일 때의 예시입니다.
 */