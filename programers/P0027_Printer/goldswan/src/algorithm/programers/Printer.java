package algorithm.programers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Integer> prioritiyQueue = new LinkedList<Integer>();
        Queue<Integer> prioritiySortQueue = new LinkedList<Integer>();
       
        for(int i=0;i<priorities.length;i++)
        	prioritiyQueue.offer(i);
        
        int[] prioritiesSort = priorities.clone();
        
        Arrays.sort(prioritiesSort);
        
        for(int i = prioritiesSort.length-1;i>=0;i--) 
        	prioritiySortQueue.offer(prioritiesSort[i]);      

        while(!prioritiyQueue.isEmpty()) {
        	if(checkPriority(prioritiyQueue, prioritiySortQueue, priorities)) {
        		answer++;
        		
            	if(prioritiyQueue.peek() == location)
            		break;
            	
        		prioritiyQueue.poll();
        		prioritiySortQueue.poll();
        		continue;
        	}
        	
        	int peekValue = prioritiyQueue.peek();
        	prioritiyQueue.poll();
        	prioritiyQueue.offer(peekValue);
        }
        
        return answer;
    }
    
    public boolean checkPriority(Queue<Integer> prioritiyQueue, Queue<Integer> prioritiySortQueue, int[] priorities) {
    	
    	if(prioritiySortQueue.peek()==priorities[prioritiyQueue.peek()])    		
    		return true;   	
    	
    	return false;
    }
    
}

public class Printer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		int[] priorities = {1, 1, 9, 1, 1, 1};
		int location = 0;
		//int[] priorities = {2,1,3,2};
		//int location = 2;
		
		int answer = sol.solution(priorities, location);
		
		System.out.println("answer:"+answer);
	}

}
