import java.util.ArrayList;

class Solution {

	  int k = 1;
	  static ArrayList<Integer> answerList = new ArrayList<Integer>();

	  public int[] solution(int n) {

	      LinkedTree tree = new LinkedTree();
	      TreeNode heder = new TreeNode(0);

		  function(heder, n, k);
		  tree.inorder(heder);

		  int [] answer = new int[answerList.size()];

		  for(int i=0;i<answerList.size();i++)
		  {
			  answer[i] = answerList.get(i);
		  }
	      return answer;
	  }

	  public void function(TreeNode node, int n, int k) {
		  if(k==n)
			  return;

		  TreeNode leftNode = new TreeNode(0);
		  TreeNode rightNode =new TreeNode(1);
		  node.left = leftNode;
		  node.right = rightNode;
		  k++;
		  function(leftNode,n,k);
		  function(rightNode, n,k);
	  }
}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class LinkedTree {

	int i = 0;

    public void preorder(TreeNode root){
        if(root!=null){
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    public void inorder(TreeNode root){
        if(root!=null){
            inorder(root.left);
            //System.out.print(root.data + " ");
            Solution.answerList.add(root.data);
            inorder(root.right);
        }
    }

    public void postorder(TreeNode root){
        if(root!=null){
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }
}

public class origami_goldswan {

	public static void main(String[] args) {

			Solution sol = new Solution();
			int n = 3;

			int [] answer = sol.solution(n);

			System.out.println(answer[0]);
	}

}
