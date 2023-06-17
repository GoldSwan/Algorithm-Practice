package algorithm.leetcode;
import java.util.*;

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
