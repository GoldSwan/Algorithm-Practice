package algorithm.leetcode;
import java.util.*;

//참고 : https://leetcode.com/problems/3sum/solutions/3109452/c-easiest-beginner-friendly-sol-set-two-pointer-approach-o-n-2-logn-time-and-o-n-space/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int target = 0;
        Arrays.sort(nums);//탐색 효율성을 높이기 위해 오름차순 정렬
        Set<List<Integer>> set = new HashSet<>();
        List<List<Integer>> output = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            int j = i + 1;
            int k = nums.length - 1;
            //j와 k가 만나지 않을 때까지 순회
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                //합이 0일 경우 답안에 넣고 제외해야 하므로 j를 증가, k를 감소한다.
                if (sum == target) {
                    //set 자료구조를 사용하여 중복 리스트 제거
                    set.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                }
                //합이 음수일 경우 합이 증가해야 하므로 j를 증가한다.
                else if (sum < target) {
                    j++;
                }
                //합이 양수일 경우 합이 감소해야 하므로 k를 감소한다.
                else {
                    k--;
                }
            }
        }
        output.addAll(set);
        return output;
    }
}

/*
class Solution {
    //완전탐색풀이 : 시도 했더니 시간초과 에러 남
    public List<List<Integer>> threeSum(int[] nums) {
        var lists = new ArrayList<List<Integer>>();
        var set = new HashSet<String>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < nums.length ; i++){
            int num1 = nums[i];
            for(int j = i + 1 ; j < nums.length ; j++){
                int num2 = nums[j];
                for(int k = j + 1 ; k < nums.length ; k++){
                    int num3 = nums[k];
                    if(num1 + num2 + num3 == 0){
                        var list = new ArrayList<Integer>();
                        list.add(num1);
                        list.add(num2);
                        list.add(num3);
                        Collections.sort(list);
                        sb.setLength(0);
                        for(int num : list){
                            sb.append(num);
                        }
                        String str3Num = sb.toString();
                        if(!set.contains(str3Num)){
                            set.add(str3Num);
                            lists.add(list);
                        }
                    }
                }
            }
        }
        return lists;
    }
}
 */

public class Sum3 {
    public static void main(String[] args) throws Exception {
        int[] nums = {-1,0,1,2,-1,-4};
        var s = new Solution();
        var lists = s.threeSum(nums);
        for(var list : lists){
            for(var num : list){
                System.out.print(num+" ");
            }
            System.out.println();
        }
    }
}
