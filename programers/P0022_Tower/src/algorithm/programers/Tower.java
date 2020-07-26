package algorithm.programers;

class Solution {
    public int[] solution(int[] heights) {
        int[] answer = new int[heights.length];
        
        answer[0] = 0;
        
        for(int i=1;i<heights.length;i++) {
        	for(int j=i-1;j>-1;j--) {
        		if(heights[j]>heights[i]) {
        			answer[i] =j+1;
        			break;
        		}
        	}
        }
        
        return answer;
    }
}

public class Tower {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		//int[] heights = {6,9,5,7,4};
		int[] heights = {3,9,9,3,5,7,2};
		
		
		int[] answer = sol.solution(heights);
		
		for(int ans : answer)
			System.out.println(ans);		
	}

}
