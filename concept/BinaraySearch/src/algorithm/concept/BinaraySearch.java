package algorithm.concept;

import java.util.*;

class Solution {
	public void solution(List<Integer> listScore, int target) {
		binaraySearch(listScore, 0, listScore.size(), target);
	}
	public void binaraySearch(List<Integer> listScore, int start, int end, int target) {
		
		if(start >= end) 
		{
			System.out.println("start:"+start + " " + "end:"+end);
			return;
		}
		int mid = (start + end) / 2;
		System.out.println("start:"+start + " " + " end:"+end + " mid:"+ mid + " mid value:"+listScore.get(mid));

		if(listScore.get(mid) > target) {
			end = mid;
		}
		else if(listScore.get(mid)==target){
			System.out.println("target index:"+mid + " value:" + listScore.get(mid));
			return;
		}
		else {			
			start = mid+1;
		}
		binaraySearch(listScore,start, end, target);
	}
}

public class BinaraySearch {

	public static void main(String[] args) {
		Random random = new Random();
		random.setSeed(System.currentTimeMillis()); 
		List<Integer> listScore = new ArrayList<>();
		
		for(int i = 1 ; i <= 333 ; i++) {
			listScore.add(i);
		}
		
		Solution solution = new Solution();
		solution.solution(listScore, 172);
	}

}
