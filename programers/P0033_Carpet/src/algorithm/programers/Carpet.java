package algorithm.programers;
//Problem solving time : 18min
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];        
        int widthPlusHeight = (brown+4)/2;
        
        for(int height=3;height<widthPlusHeight;height++) {
        	int width = widthPlusHeight-height;
        	if((width-2)*(height-2)==yellow) {
        		answer[0] = width;
        		answer[1] = height;
        		break;
        	}
        }
        return answer;
    }
}

public class Carpet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int brown = 24;
		int yellow = 24;
		Solution solution = new Solution();
		int[] answer =solution.solution(brown, yellow);
		
		for(int value : answer)
			System.out.print(value+" ");
	}

}
