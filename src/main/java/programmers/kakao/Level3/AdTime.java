package programmers.kakao.Level3;

import javax.lang.model.SourceVersion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdTime {
    public static void main(String[] args) {
        String solution = solution("02:03:55", "00:14:15", new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"});
        System.out.println("solution = " + solution);
    }
    public static String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        String[] advTimeSplit = adv_time.split(":");

        Time advTime = new Time(Integer.parseInt(advTimeSplit[0]),Integer.parseInt(advTimeSplit[1]),Integer.parseInt(advTimeSplit[2]));

        if (play_time.equals(adv_time)){
            return "00:00:00";
        }

        List<AdLog> list = new ArrayList<>();

        for (int i = 0 ; i< logs.length ; i++){
            String ad = logs[i];
            String[] splitAd = ad.split("-");

            String startLog = splitAd[0];
            String[] startTime = startLog.split(":");
            int startHour = Integer.parseInt(startTime[0]);
            int startMin= Integer.parseInt(startTime[1]);
            int startSec = Integer.parseInt(startTime[2]);
            String endLog = splitAd[1];

            String[] endTime = endLog.split(":");
            int endHour = Integer.parseInt(endTime[0]);
            int endMin= Integer.parseInt(endTime[1]);
            int endSec = Integer.parseInt(endTime[2]);
            list.add(new AdLog(new Time(startHour,startMin,startSec), new Time(endHour,endMin,endSec)));
        }

        sorting(list);

        int idx = getMaxDuplicateIdx(list);

        for (int i = idx ; i< list.size(); i++){
            AdLog adLog = list.get(idx);
            int tempIdx = i+1;
            Double calAdTime = TimeCal(advTime);
            Double nowTime = TimeCal(adLog.startTime);
            double MaxViewingTime = calAdTime;
            double totalTime = calAdTime + nowTime;
            System.out.println("totalTime = " + totalTime);
            while (tempIdx != list.size()){
                AdLog tempLog = list.get(tempIdx);
                System.out.println("tempLog = " + tempLog);
                if (TimeCal(tempLog.endTime) >  totalTime &&  totalTime >= TimeCal(tempLog.startTime)){
                    MaxViewingTime += calAdTime;
                }else if (TimeCal(tempLog.startTime) <= totalTime){
                    MaxViewingTime += totalTime - TimeCal(tempLog.startTime);
                    break;
                }
                tempIdx++;
                System.out.println("MaxViewingTime = " + MaxViewingTime);
            }
        }
        return answer;
    }

    private static void sorting(List<AdLog> list) {
        list.sort((o1, o2) -> {
            if (o1.startTime.hour != o2.startTime.hour){
                return Integer.compare(o1.startTime.hour,o2.startTime.hour);
            } else if (o1.startTime.min != o2.startTime.min) {
                return Integer.compare(o1.startTime.min,o2.startTime.min);
            } else if (o1.startTime.sec != o2.startTime.sec) {
                return Integer.compare(o1.startTime.sec,o2.startTime.sec);
            }
            return o1.endTime.hour - o2.endTime.hour;
        });
    }

    private static int getMaxDuplicateIdx(List<AdLog> list) {
        Map<Integer,Integer> resultCount = new HashMap<>();

        for (int i = 0; i< list.size(); i++){
            AdLog adLog = list.get(i);
            double calculatedStartTime = TimeCal(adLog.endTime);
            int tempIdx = i+1;
            while (tempIdx != list.size()){
                AdLog nextLog = list.get(tempIdx);
                if (TimeCal(nextLog.startTime) <= calculatedStartTime){
                    resultCount.put(i,resultCount.getOrDefault(i,1) +1);
                }else break;
                tempIdx++;
            }
        }

        int maxCount = Integer.MIN_VALUE;
        int idx = 0;
        for (Map.Entry<Integer, Integer> integerIntegerEntry : resultCount.entrySet()) {
            if (maxCount < integerIntegerEntry.getValue()){
                maxCount = integerIntegerEntry.getValue();
                idx = integerIntegerEntry.getKey();;
            }
        }
        return idx;
    }

    private static Double TimeCal(Time time){
        return  (double) time.hour* 60 +time.min + (time.sec * 0.01) ;
    }
    private static class AdLog{

        Time startTime;
        Time endTime;

        public AdLog(Time startTime, Time endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return "AdLog{" +
                    "startTime=" + startTime +
                    ", endTime=" + endTime +
                    '}';
        }
    }
    private static class Time{
        @Override
        public String toString() {
            return "Time{" +
                    "hour=" + hour +
                    ", min=" + min +
                    ", sec=" + sec +
                    '}';
        }

        int hour;
        int min;
        int sec;

        public Time(int hour, int min, int sec) {
            this.hour = hour;
            this.min = min;
            this.sec = sec;
        }
    }
}
