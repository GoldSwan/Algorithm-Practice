package algorithm.leetcode;
import java.util.*;

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        
        dfs(root, targetSum, 0, result, path);
        
        return result;
    }
    
    private void dfs(TreeNode node, int targetSum, int sum, List<List<Integer>> result, List<Integer> path){
        if(node == null) return;
        
        path.add(node.val);
        sum += node.val;
        
        if(node.left == null && node.right == null && sum == targetSum){
            result.add(new ArrayList<>(path));
        } else {
            dfs(node.left, targetSum, sum, result, path);
            dfs(node.right, targetSum, sum, result, path);
        }

        path.remove(path.size()-1);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
   }
}

public class PathSumII {
    public static void main(String[] args) throws Exception {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        var solution = new Solution();
        var answer = solution.pathSum(root, 22).toString();
        System.out.println(answer);
    }
}
