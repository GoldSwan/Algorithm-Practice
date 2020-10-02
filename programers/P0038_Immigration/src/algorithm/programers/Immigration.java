package algorithm.programers;

import java.util.Arrays;

class Solution {
	public long solution(int n, int[] times) {	
		Arrays.sort(times);
		return searchMinTimeByBinarySearch(n, times);
	}
	
	public long searchMinTimeByBinarySearch(int n, int[] times) {
		long minTime = Long.MAX_VALUE;						
		long min = 1;
		long max= (long)n*times[times.length-1];//long 으로 형변환해줘야 값이 정상적으로 나옴.
		
		while(min<=max) {
			long sum = 0;
			long mid = (min+max)/2;
			
			for(int time : times) 		
				sum += mid/time;
			
		    if(n > sum) {	    	
		    	min = mid+1;
		    	continue;
			}
		    
		    max = mid-1;
		    minTime = (mid < minTime) ? mid : minTime;			
		}
		return minTime;
	}
}

public class Immigration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 6;
		int[] times = {7, 10};
		
		Solution solution = new Solution();
		long answer = solution.solution(n, times);
		System.out.println("answer:" + answer);
	}

}
