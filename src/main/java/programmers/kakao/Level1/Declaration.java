package programmers.kakao.Level1;

import java.util.*;

public class Declaration {
    public static void main(String[] args) {
        int[] solution = solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"}, 2);
        System.out.println("solution = " + Arrays.toString(solution));
    }
    /*
    이 문제는 k 번 이상 신고를 받으면 해당 유저는 정지되고, 해당 유저를 신고한 유저한테 통지를 보내야한다

    그 결과값을 int 배열로 만들어주며, 해당 id_list 에 등재된 유저 순으로 신고에 대한 통첩의 결과값을 주면된다

     */
        public static int[] solution(String[] id_list, String[] report, int k) {
            int[] answer = new int[id_list.length];

            Set<DcPeople> set = new HashSet<>();
            Map<String,Integer> map = new HashMap<>();
            Map<String ,Integer> result = new HashMap<>();


            for (String s : id_list) {
                map.put(s,0);
                result.put(s,0);
            }

            for (String s : report) {
                String[] reports = s.split(" ");
                set.add(new DcPeople(reports[0],reports[1],0));
            }
            for (DcPeople dcPeople : set) {
                map.put(dcPeople.to,map.getOrDefault(dcPeople.to,0)+1);
            }
            for (DcPeople dcPeople : set) {
                if (map.get(dcPeople.to) >= k ){
                    result.put(dcPeople.from,result.getOrDefault(dcPeople.from,0)+1);
                }
            }
            for (int i = 0;  i< id_list.length; i++){
                answer[i] = result.get(id_list[i]);
            }
            return answer;
        }
    public static  class DcPeople {
        @Override
        public String toString() {
            return "DcPeople{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", result=" + result +
                    '}';
        }
        String from;
     String to;
     int result = 0;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DcPeople dcPeople = (DcPeople) o;
            return Objects.equals(from, dcPeople.from) && Objects.equals(to, dcPeople.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        public DcPeople(String from, String to, int result) {
            this.from = from;
            this.to = to;
            this.result = result;
        }
    }
}
/**
 * 문제 설명
 * 문제 설명
 * 신입사원 무지는 게시판 불량 이용자를 신고하고 처리 결과를 메일로 발송하는 시스템을 개발하려 합니다.
 * 무지가 개발하려는 시스템은 다음과 같습니다.
 *
 * 각 유저는 한 번에 한 명의 유저를 신고할 수 있습니다.
 * 신고 횟수에 제한은 없습니다. 서로 다른 유저를 계속해서 신고할 수 있습니다.
 * 한 유저를 여러 번 신고할 수도 있지만, 동일한 유저에 대한 신고 횟수는 1회로 처리됩니다.
 * k번 이상 신고된 유저는 게시판 이용이 정지되며, 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송합니다.
 * 유저가 신고한 모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지를 시키면서 정지 메일을 발송합니다.
 * 다음은 전체 유저 목록이 ["muzi", "frodo", "apeach", "neo"]이고, k = 2(즉, 2번 이상 신고당하면 이용 정지)인 경우의 예시입니다
 * 따라서 "muzi"는 처리 결과 메일을 2회, "frodo"와 "apeach"는 각각 처리 결과 메일을 1회 받게 됩니다.
 *
 * 이용자의 ID가 담긴 문자열 배열 id_list, 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열 report,
 * 정지 기준이 되는 신고 횟수 k가 매개변수로 주어질 때,
 * 각 유저별로 처리 결과 메일을 받은 횟수를 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 *
 * 유저 ID	유저가 신고한 ID	설명
 * "muzi"	"frodo"	    "muzi"가 "frodo"를 신고했습니다.
 * "apeach"	"frodo"	    "apeach"가 "frodo"를 신고했습니다.
 * "frodo"	"neo"	    "frodo"가 "neo"를 신고했습니다.
 * "muzi"	"neo"	    "muzi"가 "neo"를 신고했습니다.
 * "apeach"	"muzi"	    "apeach"가 "muzi"를 신고했습니다.
 * 각 유저별로 신고당한 횟수는 다음과 같습니다.
 *
 * 유저 ID	신고당한 횟수
 * "muzi"	1
 * "frodo"	2
 * "apeach"	0
 * "neo"	2
 * 위 예시에서는 2번 이상 신고당한 "frodo"와 "neo"의 게시판 이용이 정지됩니다.
 * 이때, 각 유저별로 신고한 아이디와 정지된 아이디를 정리하면 다음과 같습니다.
 *
 * 유저 ID	유저가 신고한 ID	정지된 ID
 * "muzi"	["frodo", "neo"]	["frodo", "neo"]
 * "frodo"	["neo"]	["neo"]
 * "apeach"	["muzi", "frodo"]	["frodo"]
 * "neo"	없음	없음
 *
 * 각 유저별로 처리 결과 메일을 받은 횟수를 배열에 담아 return 하도록 solution 함수를 완성해주세요.
 */