package programmers.ex;

public class Pro12924 {
    public static void main(String[] args) {
        solution(15);
    }
    public static int solution(int n) {
        int answer = 0;
        for (int i = 1 ; i < n ; i++){
            int temp = i;
            int isCollectNumber = 0;
            while (true){
                temp += 1;
                isCollectNumber += temp;
                if (isCollectNumber == n){
                    answer++;
                    break;
                }else if (isCollectNumber > n){
                    break;
                }
            }
        }
        System.out.println("answer = " + answer);
        return answer;
    }
}
