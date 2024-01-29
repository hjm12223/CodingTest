package programmers.kakao.Level1;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Present {
    public static void main(String[] args) {
        int solution = solution(new String[]{"muzi", "ryan", "frodo", "neo"}, new String[]{"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"});
//        int solution = solution(new String[]{"joy", "brad", "alessandro", "conan", "david"}, new String[]{"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"});
        System.out.println("solution = " + solution);
    }

    /*
    구현해야할 것들

    첫번째 선물을 주고 받은 내역
    만약 주고받지 않았을 경우 선물 지수를 확인하여 그에게 선물을 주어야 하기 때문

     */
    public static int solution(String[] friends, String[] gifts) {
        Map<String,Integer> point = new ConcurrentHashMap<>();
        Map<Person,Integer> transaction = new ConcurrentHashMap<>();
        Map<String,Integer> result = new ConcurrentHashMap<>();

        Set<Person> check = new HashSet<>();
        for (String friend : friends) {
            point.put(friend,0);
            result.put(friend,0);
        }
            // 전처리 단계
            // 선물 지수 HashMap point
        for (String gift : gifts) {
            String[] strings = gift.split(" ");
            Person person = new Person(strings[1], strings[0]);

            if (point.containsKey(person.receipt)) {
                point.put(person.receipt,point.getOrDefault(person.receipt,point.get(person.receipt)) -1);
            }

            if (point.containsKey(person.giver)) {
                point.put(person.giver, point.getOrDefault(person.giver,point.get(person.giver)) +1);
            }

            if (transaction.containsKey(person)){
                transaction.put(person,transaction.getOrDefault(person,transaction.get(person)) +1);
            } else {
                transaction.put(person,1);
            }

        }

        /*
        준 횟수를 파악완료
        giver 가 누구에게 주었는지 확인

        그럼 받은 사람이 giver 에게 준적이 있는 지 확인

        준적이 있으면 그 둘의 value 값을 비교해서 더 높은지 확인

        만약 높지 않으면 높은쪽의

        그럼 다시 무지가 프로도에게 주었는지 확인

        받은 적이 있다? 그러면 해당 key 값의 value 들을 비교

        주고 받지 않았을 때를 어떻게 계산하냐

         */
        for (Person person : transaction.keySet()) {
            Person reversePerson= new Person(person.giver,person.receipt);

            if (transaction.containsKey(reversePerson) && transaction.get(reversePerson) != null) { // 주고 받은 기록이 있을 경우

                int personGiftCount = transaction.getOrDefault(person,0);
                int reversePersonGiftCount = transaction.getOrDefault(reversePerson,0);

                if (personGiftCount  == 0 || reversePersonGiftCount == 0) continue;
                if (personGiftCount < reversePersonGiftCount) {
                    result.put(person.receipt, result.getOrDefault(person.receipt, result.get(person.receipt)) + 1);

                } else if (personGiftCount == reversePersonGiftCount && !check.contains(person)) {
                    int personPoint =point.get(person.giver);
                    int reversePersonPoint = point.get(reversePerson.giver);

                    if (personPoint > reversePersonPoint){
                        result.put(person.giver, result.getOrDefault(person.giver, result.get(person.giver)) + 1);
                        transaction.remove(reversePerson);

                    } else if (reversePersonPoint > personPoint) {
                        result.put(person.receipt, result.getOrDefault(person.receipt, result.get(person.receipt)) + 1);
                        transaction.remove(reversePerson);
                    }else continue;
                }
            } else { // 상대방이 주고받은 기록이 없는 경우
                result.put(person.giver, result.getOrDefault(person.giver, result.get(person.giver)) + 1);
            }
                for (String friend : friends) {

                    if (!Objects.equals(person.giver, friend) && !Objects.equals(person.receipt, friend)){
                        Person isCheck = new Person(person.giver,friend);

                        if (!check.contains(isCheck)){
                            if (!transaction.containsKey(isCheck) && !transaction.containsKey(new Person(friend,person.giver))) {
                                int personPoint = point.get(person.giver);
                                int reversePersonPoint = point.get(friend);
                                check.add(isCheck);
                                check.add(new Person(friend,person.giver));
                                if (personPoint > reversePersonPoint) {
                                    result.put(person.giver, result.getOrDefault(person.giver, result.get(person.giver)) + 1);

                                } else if (reversePersonPoint > personPoint) {
                                    result.put(friend, result.getOrDefault(friend, result.get(friend)) + 1);

                                }
                            }
                        }
                    }
                }
            }
        int max = Integer.MIN_VALUE;
        for (int value : result.values()) {
            if (max < value){
                max = value;
            }
        }
        return max;
    }

    public static class Person {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(receipt, person.receipt) && Objects.equals(giver, person.giver);
        }

        @Override
        public int hashCode() {
            return Objects.hash(receipt, giver);
        }
        String receipt;
        String giver;

        @Override
        public String toString() {
            return "Person{" +
                    "receipt='" + receipt + '\'' +
                    ", giver='" + giver + '\'' +
                    '}';
        }

        public Person(String receipt, String giver) {
            this.receipt = receipt;
            this.giver = giver;
        }
    }
}
/*
문제는 결론적으로 다음달에 선물을 누가 더 많이 받는지를 리턴해야한다

하지만 이것에는 제약사항이 있다

첫번째로 이번 달에 두사람 사이에 더많은 선물을 준사람은 다음달에 그사람에게 선물을 하나 받는다
만약 두사람이 준 선물의 주고받은 기록이 없거나, 갯수가 똑같으면

두번째로 선물의 가중치에 계산하여 그 다음달에 가중치가 높은사람이 낮은사람에게 선물을 받는다

예를 들어 A가 친구들에게 준 선물이 3개고 받은 선물이 10개라면 A의 선물 지수는 -7입니다.

 */
/*
무지는 프로도에게 선물을 하나 받는다 왜냐하면 선물을 더 많이 주었기 때문이다

라이언은 무지에게 하나받고 네오에게 하나 받는다 왜냐하면 무지에게 선물을 더 많이 주었고 네오보다 선물지수가 크기 때문이다

프로도는 라이언에게 선물을 받는다 왜냐하면 라이언에게 선물을 더 많이 주어서 이다

네오는 무지와 프로도에게 하나 받는다 무지에게 받는 이유는 무지에게 선물을 더 많이 주었으며, 프로도 보다 선물지수가 높기 때문이다
 */
/**
 * 문제 설명
 * 선물을 직접 전하기 힘들 때 카카오톡 선물하기 기능을 이용해 축하 선물을 보낼 수 있습니다.
 * 당신의 친구들이 이번 달까지 선물을 주고받은 기록을 바탕으로 다음 달에 누가 선물을 많이 받을지 예측하려고 합니다.
 *
 * 두 사람이 선물을 주고받은 기록이 있다면, 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받습니다.
 *
 * 예를 들어 A가 B에게 선물을 5번 줬고,
 * B가 A에게 선물을 3번 줬다면
 * 다음 달엔 A가 B에게 선물을 하나 받습니다.
 *
 * 두 사람이 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같다면,
 * 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받습니다.
 *
 * 선물 지수는 이번 달까지 자신이 친구들에게 준 선물의 수에서 받은 선물의 수를 뺀 값입니다.
 * 예를 들어 A가 친구들에게 준 선물이 3개고 받은 선물이 10개라면 A의 선물 지수는 -7입니다.
 * B가 친구들에게 준 선물이 3개고 받은 선물이 2개라면 B의 선물 지수는 1입니다.
 * 만약 A와 B가 선물을 주고받은 적이 없거나 정확히 같은 수로 선물을 주고받았다면,
 * 다음 달엔 B가 A에게 선물을 하나 받습니다.
 *
 * 만약 두 사람의 선물 지수도 같다면 다음 달에 선물을 주고받지 않습니다.
 * 위에서 설명한 규칙대로 다음 달에 선물을 주고받을 때,
 * 당신은 선물을 가장 많이 받을 친구가 받을 선물의 수를 알고 싶습니다.
 *
 * 친구들의 이름을 담은 1차원 문자열 배열 friends 이번 달까지 친구들이 주고받은 선물 기록을 담은 1차원 문자열 배열 gifts가 매개변수로 주어집니다.
 * 이때, 다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수를 return 하도록 solution 함수를 완성해 주세요.
 */