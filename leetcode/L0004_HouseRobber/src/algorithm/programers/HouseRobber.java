package algorithm.programers;

class Solution {
    public int rob(int[] nums) {
        //생각해본 case
        // 2 1 1 6 1
        // 0 1 499 500 499

        // i번째 집을 털 경우: dp[i-2] + nums[i]
        // i번째 집을 털지 않을 경우: dp[i-1]

        // dp[0] = nums[0]
        // dp[1] = nums[0] > nums[1] ? nums[0] : nums[1]
        // if(dp[i-2] + nums[i] > dp[i-1])
        // 	dp[i] = dp[i-2] + nums[i]
        // else
        // 	dp[i] = dp[i-1]
        return 0;
        
    }
}


public class HouseRobber {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
