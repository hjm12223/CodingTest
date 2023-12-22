package leetCode.HashMap.easy;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] num = new int[]{1,2,3,1,2,3};
        int k = 2;
        System.out.println("result = " + containsNearbyDuplicate(num,k));
    }
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0 ; i < nums.length ; i++){
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k)  {/*만약 중복이 있는걸 확인하면*/
                return true;
            }
            map.put(nums[i],i);
        }
        return false;
    }
}
