package algorithm.leetcode;
import java.util.*;
import java.util.stream.*;

class Solution {
    private final List<List<Integer>> result = new ArrayList<>();
    private final int VISITED = -11;
    public List<List<Integer>> subsets(int[] nums) {
		int[] subset = new int[nums.length];
        createSubsets(nums, subset, 0);
        return result;
    }

    public void createSubsets(int[] nums, int[] subset, int index){
        if(index == nums.length){
            List<Integer> list = Arrays.stream(subset)
                                       .filter(num -> num != VISITED)
                                       .boxed()
                                       .collect(Collectors.toList());
            result.add(list);
        } else {
            subset[index] = VISITED;//방문한 경우 - 미포함
            createSubsets(nums, subset, index + 1);
            subset[index] = nums[index];//방문하지 않은 경우 - 포함
            createSubsets(nums, subset, index + 1);
        }
    }
}

public class Subsets {
    public static void main(String[] args) throws Exception {
        int[] nums = {1,2,3};
        var solution = new Solution();

        var answer = solution.subsets(nums);

        for(List<Integer> list : answer){
            System.out.println(list.toString());
        }
    }
}
