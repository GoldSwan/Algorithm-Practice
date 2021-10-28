package algorithm.programers;

import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;      
        Queue<Integer> workQueue = new LinkedList<Integer>();
  
        for(int i=0;i<priorities.length;i++)
        	workQueue.offer(i);
        
        Integer[] arr = Arrays.stream(priorities).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, Collections.reverseOrder());
        int[] sortPriorities = Arrays.stream(arr).mapToInt(Integer::intValue).toArray();
        int priority_index = 0;
        
        while(!workQueue.isEmpty()) {
        	if(checkPriority(workQueue, priority_index, priorities, sortPriorities)) {
        		answer++;
        		
            	if(workQueue.peek() == location)
            		break;
            	
            	workQueue.poll();
            	priority_index++;
        		continue;
        	}
        	
        	int peekValue = workQueue.peek();
        	workQueue.poll();
        	workQueue.offer(peekValue);
        }
        
        return answer;
    }
    
    public boolean checkPriority(Queue<Integer> workQueue, int priority_index, int[] priorities, int[] sortPriorities) {  	
    	return (priorities[workQueue.peek()]==sortPriorities[priority_index]) ? true : false;	
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
