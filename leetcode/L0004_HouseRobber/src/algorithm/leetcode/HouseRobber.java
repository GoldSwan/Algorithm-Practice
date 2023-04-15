package algorithm.leetcode;

// 풀기 전 생각
// max인 값을 차례로 찾아서 그리드 알고리즘으로는 못푸나?
// -> 아래 생각해본 case에서 안될 것 같다는 것으로 판단
// 생각해본 case
// 2 1 3 100 1
// 0 1 499 500 499
// 점화식을 세우려 했으나 영감이 떠오르지 않아 chat gpt 선생님한테 힌트좀 달라고 해서 풀었음.
// chat gpt가 준 힌트
// i번째 집을 털 경우 최대값 : dp[i-2] + nums[i]
// i번째 집을 털지 않을 경우 최대값 : dp[i-1]
//의사 코드 작성
// dp[0] = nums[0]
// dp[1] = nums[0] > nums[1] ? nums[0] : nums[1]
// if(dp[i-2] + nums[i] > dp[i-1])
// 	dp[i] = dp[i-2] + nums[i]
// else
// 	dp[i] = dp[i-1]

class Solution {
    public int rob(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[0] > nums[1] ? nums[0] : nums[1];

        if(nums.length <= 2)
            return dp[nums.length-1];

        for(int i = 2 ; i < dp.length ; i ++){
            if(dp[i-2] + nums[i] > dp[i-1]){
                dp[i] = dp[i-2] + nums[i];
            }
            else{
                dp[i] = dp[i-1];
            }
        }
        return dp[nums.length-1];

    }
}


public class HouseRobber {
    public static void main(String[] args) throws Exception {
        var solution = new Solution();
        //int[] nums = {1,2,3,1};
        //int[] nums = {2,7,9,3,1};
        int[] nums = {0,1,499,500,499};
        var result = solution.rob(nums);
        System.out.println(result);
    }
}
