package algorithm.programers;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    private final List<List<Integer>> nestedList = new ArrayList<>();
    private final int SUBSET_EMPTY = -11;

    public List<List<Integer>> subsets(int[] nums) {
		int[] subset = new int[nums.length];
        createSubsets(nums, subset, 0);
        return nestedList;
    }

    public void createSubsets(int[] nums, int[] subset, int index){
        if(index == nums.length){
            List<Integer> list = Arrays.stream(subset)
                                       .filter(num -> num != SUBSET_EMPTY)
                                       .boxed()
                                       .collect(Collectors.toList());
            nestedList.add(list);
        } else {
            subset[index] = SUBSET_EMPTY;
            createSubsets(nums, subset, index + 1);
            subset[index] = nums[index];
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
