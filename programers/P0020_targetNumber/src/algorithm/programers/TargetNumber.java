package algorithm.programers;

class Solution {
	int answer = 0;
    public int solution(int[] numbers, int target) {
    	//전역변수를 사용하여 경우의 수를 count한 DFS 탐색
    	//dfs_function_void(numbers, target, 0, 0);
        //return global_answer;
    	//함수 내부에서 경우의 수를 count한 DFS 탐색
    	answer = dfs_function_return_int(numbers, target, 0, 0);
    	return answer;
    }
    public void dfs_function_void(int[] numbers, int target, int sum, int height){

    	if(numbers.length==height)
    	{
    		if(sum==target) 
    			answer++;
    		return;
    	}
    	dfs_function_void(numbers, target, sum+numbers[height],  height+1);
    	dfs_function_void(numbers, target, sum-numbers[height],  height+1);
    }
    public int dfs_function_return_int(int[] numbers, int target, int sum, int height){	
    	if(numbers.length==height)
    	{
    		return (sum==target) ? 1 : 0; 
    	}
    	return dfs_function_return_int(numbers, target, sum+numbers[height],  height+1) 
    		   + dfs_function_return_int(numbers, target, sum-numbers[height],  height+1);
    }
}

public class TargetNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numbers = new int[] {1,1,1,1,1};
		int target = 3;
		int answer = 0;
		Solution solution = new Solution();
		answer = solution.solution(numbers, target);
		System.out.println(answer);
	}

}
