    package programmers.kakao.level2;
    import java.util.*;

    public class ParkingFee {
        public static void main(String[] args) {
//        int[] solution = solution(new int[]{180, 5000, 10, 600}, new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});
//        System.out.println("solution = " + Arrays.toString(solution));
//        solution(new int[]{120, 0, 60, 591}, new String[]{"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"});
//        solution(new int[]{180, 5000, 10, 1000} ,new String[]{"05:59 0000 IN", "05:59 1111 IN"}); // [95000, 95000]
            solution(new int[]{180, 5000, 10, 600}, new String[]{"05:34 5961 IN", "06:34 5961 OUT", "07:34 5961 IN", "08:34 5961 OUT", "09:34 5961 IN", "10:34 5961 OUT", "11:34 5961 IN", "12:34 5961 OUT"});
        }

        public static int[] solution(int[] fees, String[] records) {
            Map<String, Integer> leftTime = new HashMap<>();
            HashMap<String, Value> map = new HashMap<>();
            List<String> list = new ArrayList<>();

            for (String record : records) { // 문제형식으로 만들기
                String[] cars = record.split(" "); // split
                String[] time = cars[0].split(":"); // ':'  기준으로 split
                int hour = Integer.parseInt(time[0]);
                int minute = Integer.parseInt(time[1]);

                if (cars[2].equals("IN")) { // IN 일 경우
                    if (!list.contains(cars[1])) {
                        list.add(cars[1]);
                    }
                    if (map.containsKey(cars[1])) { // 만약 이미 들어왔던적이 있던 차면
                        map.get(cars[1]).isOut = false;// 해당 다시 false
                    } else {
                        map.put(cars[1], map.getOrDefault(map.get(cars[1]), new Value(0, false)));
                        // 처음 들어온 차면 기본값을 설정해준다
                    }
                    // 해당 차의 들어온 시간을 기록
                    leftTime.put(cars[1], hour * 60 + minute);
                } else {
                    int nowTime = hour * 60 + minute - leftTime.get(cars[1]); // 계산된 시간
                    int preTime = map.get(cars[1]).time; // 이전 시간

                    leftTime.put(cars[1], 0); // 다시 초기화
                    if (preTime == 0) { // 만약 처음 나가는 차면
                        map.put(cars[1], new Value(nowTime + preTime, map.get(cars[1]).isOut = true));
                    } else { // 아니면
                        map.get(cars[1]).isOut = true;
                        map.get(cars[1]).time += nowTime;
                    }
                }
            }

            int[] answer = new int[list.size()];

            for (String s : map.keySet()) {
                if (!map.get(s).isOut) { // 나간 이력이 없다면
                    map.get(s).time += 1439 - leftTime.get(s);
                }
            }

            Collections.sort(list);
            for (int i = 0; i < list.size(); i++) {
                String key = list.get(i); // 오름차순으로 정렬된 키값

                int time = map.get(key).time; // 시간

                int minimumTime = fees[0];
                int minimumFee = fees[1];
                int perMinute = fees[2];
                int perMinuteFee = fees[3];

                if (isStandardFee(time - minimumTime)) { // 기본요금을 초과하는가?
                    answer[i] = minimumFee;
                } else {
                    if (isOverStandardFee(time - minimumTime - perMinute)) { // 기본요금을 초과하면서 추가요금보다 작은가?
                        answer[i] = minimumFee + perMinuteFee;
                    } else {
                        if (!isRoundUp((time - minimumTime) % perMinute)) {
                            int value = ((((time - minimumTime) + 9) / 10 * 10) / perMinute) * perMinuteFee + minimumFee;
                            answer[i] = value;
                        } else {
                            answer[i] = (time - minimumTime) / perMinute * perMinuteFee + minimumFee;
                        }
                    }
                }
            }
            return answer;
        }

        private static boolean isRoundUp(int i) {
            return i == 0;
        }

        private static boolean isOverStandardFee(int i) {
            return i <= 0;
        }

        private static boolean isStandardFee(int i) {
            return i <= 0;
        }

        public static class Value {
            int time;
            boolean isOut;

            public Value(int time, boolean isOut) {
                this.time = time;
                this.isOut = isOut;
            }

            @Override
            public String toString() {
                return "Value{" +
                        "time=" + time +
                        ", isOut=" + isOut +
                        '}';
            }
        }
    }