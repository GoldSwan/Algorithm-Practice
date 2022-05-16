package algorithm.programers;
import java.util.*;

class Solution {
    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        Map<Integer,ArrayList<Integer>> mapTeam = new HashMap<>();
        int salesLength = sales.length;
        int[] visited = new int[salesLength];
        int[] twoRole = new int[salesLength];
        int[] realSales = new int[salesLength];
        
        for(int[] link : links) {
        	mapTeam.computeIfAbsent(link[0]-1, key -> new ArrayList<>()).add(link[1]-1);
        }
        
        Set<Integer> setKeys = mapTeam.keySet();
        
        for(int key : setKeys) {
        	ArrayList<Integer> list = mapTeam.get(key);
        	for(int person : list) {
        		if(mapTeam.containsKey(person)) {
        			twoRole[person] = 1;
        		}
        	}
        }
        
        for(int role : twoRole) {
        	System.out.print(role + " ");
        }
        
        return answer;
    }
}

public class MinimizeTheDeclineInSales {

	public static void main(String[] args) {
		int[] sales = {14, 17, 15, 18, 19, 14, 13, 16, 28, 17};
		int[][] links = {{10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3}, {10, 2}};
		int answer = 0;	
		Solution solution = new Solution();
		
		answer = solution.solution(sales, links);
		System.out.println("answer:"+ answer);
	}
	
}
