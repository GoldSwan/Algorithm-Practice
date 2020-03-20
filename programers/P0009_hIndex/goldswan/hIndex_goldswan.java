import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        //citations : 인용횟수의 집합
        for(int h=0;h<=citations.length;h++)     
        	for(int i=0;i<citations.length;i++) 
        	{
        		if(h<=citations[i])
        		{
        			if(h<=citations.length-i)
        				answer = Math.max(answer, h);
        		} 
        	}
        	
        return answer;
    }
}

public class hIndex_goldswan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        //int[] citations = { 3, 0, 6, 1, 5};
		//int[] citations = {6,0,1,3,3,6};
		int[] citations = new int[1000];
		
		for(int i=0;i<1000;i++)
			citations[i] = 1000;
        int answer = 0;
        
        Solution sol = new Solution();
        answer = sol.solution(citations);
        
        System.out.println(answer);
	}

}
