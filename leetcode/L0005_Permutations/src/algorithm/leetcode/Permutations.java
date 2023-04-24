package algorithm.leetcode;
import java.util.*;

class Solution {
    private final List<List<Integer>> result = new ArrayList<>();
    private final List<Integer> list = new ArrayList<>();
    private final int VISITED = -11;
    public List<List<Integer>> permute(int[] nums) {
        //final int[] visited = new int[nums.length];
        createPermutation(nums, 0);
        return result;
    }

    public void createPermutation(int[] nums, int depth){
        if(depth == nums.length){
            result.add(new ArrayList<>(list));//ArrayList 생성자를 이용하여 list 깊은 복사 후 result에 넣음
            return;
        }
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] != VISITED){
                list.add(nums[i]);
                int prev = nums[i];
                nums[i] = VISITED;
                createPermutation(nums, depth + 1);
                nums[i] = prev;
                list.remove(list.size()-1);
            }
        }
    }
}

public class Permutations {
    public static void main(String[] args) throws Exception {
        var solution = new Solution();
        int[] nums = {0, -1 , 1};
        var result = solution.permute(nums);
        System.out.println(result.toString());
    }
}
