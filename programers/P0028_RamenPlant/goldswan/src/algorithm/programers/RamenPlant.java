package algorithm.programers;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int solution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;
        
        Queue<Integer> priorityQueue = new PriorityQueue<Integer>(Comparator.reverseOrder());//최대값을 우선순위로 두기위해 Comparator.reverseOrder()
        int supplyIndex = 0;
        int supplyCount = 0;
        //0 : 오늘, k : k일 이후 - k일 이후는 밀가루를 공급받으므로 고려하지 않아도 됨.
        for(int i=0;i<k;i++) {
        	if(supplyIndex < dates.length && dates[supplyIndex]==i) {
        		priorityQueue.offer(supplies[supplyIndex]);
        		supplyIndex++;
        	}
        	if(stock==0) {//재고가 0인 날에 밀가루 공급횟수를 최소화하기 위해 우선순위 큐에 저장해놓은 밀가루 중 가장 큰 것을 공급받는다.
        		stock += priorityQueue.poll();
        		supplyCount++;//공급 횟수 카운트
        	}
        	stock--;
        	
        }
        
        answer = supplyCount;
        
        return answer;
    }
}

public class RamenPlant {

	public static void main(String[] args) {

		Solution sol = new Solution();
		int stock = 4;
		int[] dates = {4,10,15};
		int[] supplies = {20,5,10};
		int k = 30;
		
		int answer = sol.solution(stock, dates, supplies, k);
		
		System.out.println("answer:"+answer);
	}

}
