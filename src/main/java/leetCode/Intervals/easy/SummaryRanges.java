package leetCode.Intervals.easy;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {

    public static void main(String[] args) {
        int[] nums = new int[]{0,2,3,4,6,8,9};
        List<String> stringList = summaryRanges(nums);
        System.out.println("stringList = " + stringList);
    }
    public static List<String> summaryRanges(int[] nums) {
        int i = 0;
        List<String> str = new ArrayList<>();
            while(i < nums.length){
                int maxValue = findMaxValue(nums, i);
                if (i == maxValue){
                    /* 현재 위치가 MaxValue 와 같으면 더이상 뒤에 숫자가 없기 떄문에 add 해준다*/
                    str.add(String.valueOf(nums[maxValue]));
                }else {
                    /*아닐 경우 nums[i] 부터 nums[maxValue] 사이의 값이 존재하기 떄문에 더해준다*/
                    str.add(nums[i] + "->" + nums[maxValue]);
                }
                /* i = maxValue + 1 을 해줌으로써 i를 갱신해준다*/
                i = maxValue +1;
            }
            return str;
        }

    public static int findMaxValue(int[] num, int i){
        /*만약 I 가 num.length -1 와 같다면 더이상 뒤에 숫자가 없기때문에 MaxIndex 값으로 반환해준다*/
        if (i == num.length-1) {
            return i;
        }
        int maxindex=0;  // MAX 인덱스를 선언
        int startindex = Integer.MAX_VALUE;
        for (int j = i; j< num.length; j++) {
            if (j < startindex) {
                startindex = j; // 시작인덱스를 판별
            }
            if (j == num.length-1){
                return j; // 만약 마지막 인덱스와 같으면 끝값이기 떄문에 MaxIndex 로 j를 반환
            }
            if (num[j] + 1 != num[j + 1]) {
                maxindex = j;
                break;
            }
        }
        return maxindex;
        }
    }
    /**
     * RunTime
     * 5ms
     * Beats 86.26%of users with Java
     * */