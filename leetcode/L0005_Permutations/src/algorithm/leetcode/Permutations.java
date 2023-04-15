package algorithm.programers;
import java.util.*;

class Solution {
    List<List<Integer>> reult = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        int[] visited = new int[nums.length];
        createPermutation(nums, visited, 0);
        return reult;
    }

    public void createPermutation(int[] nums, int[] visited, int depth){
        if(depth == nums.length){
            //reult.add(list); //그냥 이렇게 넣어버렸더니 빈값으로 나옴. 객체 자체를 넣어서 그런듯. 복사하고 넣어야함.
            
            List<Integer> permutationList =  new ArrayList<>();//list에 담긴 값 복사할 permutationList 생성
            for(int element : list){
                permutationList.add(element);
            }
            reult.add(permutationList);
            return;
        }
        for(int i = 0 ; i < nums.length ; i++){
            if(visited[i] == 0){
                list.add(nums[i]);
                visited[i] = 1;
                createPermutation(nums, visited, depth + 1);
                visited[i] = 0;
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
