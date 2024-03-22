package programmers.kakao.Level2;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import java.util.*;

public class OpenChatting {
    public static void main(String[] args) {
        solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"});
    }
    public static String[] solution(String[] record) {
        Map<String,String> userIdExchangeLog = new HashMap<>();
        List<String> list = new LinkedList<>();


        for (int i = 0 ; i< record.length; i++){
            String[] str = record[i].split(" ");
            String method= str[0]; // enter, leave, change
            String userId = str[1];
            if (method.equals("Leave")){
                String log = userId + " 님이 나갔습니다.";
                list.add(log);
            }else if(method.equals("Enter")){
                String nickname = str[2];
                userIdExchangeLog.put(userId,nickname);
                String log = userId + " 님이 들어왔습니다.";
                list.add(log);
            }
            else{
                String nickname = str[2];
                userIdExchangeLog.put(userId,nickname);
            }
        }
        String[] answer = new String[list.size()];
        for (int i = 0 ; i<list.size();  i++) {
            String[] split = list.get(i).split(" ");
            String changedNick = userIdExchangeLog.get(split[0]);
            answer[i] = changedNick + split[1] + " " + split[2];
        }
        return answer;
    }
}
