package algorithm.programers;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] people, int limit) {  
        return findLifeboatMinCnt(people, limit);
    }
    //HashMap 과 Greedy Algorithm을 활용한 효율적 탐색
    public int findLifeboatMinCnt(int[] people, int limit) {
    	int cnt = 0;
    	Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    	for(int person : people) {
    		if(map.containsKey(person)) {
    			map.put(person, map.get(person)+1);
    			continue;
    		}
    		map.put(person, 1);
    	}
    	for(int person : people) {
    		int optimumWeight = limit-person;
    		if(map.get(person)<=0) {
    			continue;
    		}
    		if(optimumWeight<40) {
    			map.put(person, map.get(person)-1);
    			cnt++;
    			continue;
    		}
    		if(isOptimumWeight(optimumWeight,person,map)) {
    			cnt++;
    			continue;
    		}   		
    		map.put(person, map.get(person)-1);
    		cnt++;
    	} 	
    	
    	return cnt;
    }
    
    public boolean isOptimumWeight(int optimumWeight, int person, Map<Integer,Integer> map) {
    	for(int person2 = optimumWeight;person2>=40;person2--) {
    		if(person==person2 && map.get(person)<2) {
    			continue;
    		}
			if(map.containsKey(person2) && map.get(person2)>0) {
				map.put(person, map.get(person)-1);
				map.put(person2, map.get(person2)-1);	    		
				return true;
			}
		}
    	return false;
    }
    
    //완전 탐색
    /*
    public int findLifeboatMinCnt(int[] people, int limit) {
    	//기저사실 : 무인도에 갇힌 사람이 1명일 경우 구명보트도 1개만 필요하므로 1 리턴
    	if(people.length==1) return 1;
    	int[] use = new int[people.length];
    	int cnt = 0;
    	int sum =0;
    	int OptimalIndex1 = 0;
    	int OptimalIndex2 = 0;
    	for(int i=0;i<people.length;i++) {
    		int OptimalWeight = 0;
    		if(use[i] == 0 ) {
    			if(people[i] == limit) {
    			cnt++;
    			use[i] = 1;
    			continue;
    			}
    			for(int j=i;j<people.length;j++) {
    				if(j!=i && use[j] == 0 ) {
    					int weight = people[i]+people[j];
    					if(weight<=limit && OptimalWeight<weight) {
    						OptimalWeight = weight;
    						OptimalIndex1 = i;
    						OptimalIndex2 = j;
    					}
    					if(OptimalWeight==limit)
        					break;
    				}
    				
            	}
    			cnt++;
    			use[OptimalIndex1] = 1;
    			use[OptimalIndex2] = 1;
    			OptimalIndex1 = 0;
    			OptimalIndex2 = 0;
    		}   		
    	}
    	
    	return cnt;
    }
    */
}

public class Lifeboat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] people = {10, 11, 12, 13, 14, 10};
		int[] people = {70, 50, 80, 50};
		//int[] people = {50, 30, 20, 1};
		int limit = 100;
		Solution solution = new Solution();
		int answer = solution.solution(people, limit);
		System.out.println("answer:"+answer);
	}

}
