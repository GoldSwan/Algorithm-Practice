package algorithm.programers;

import java.util.*;

class Solution {
	Map<String, Integer> map = new HashMap<String, Integer>();
	List<String> list = new ArrayList<>();
	int max = -1;
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        for(String order : orders) {  
        	String[] split = order.split("");
        	boolean[] visited = new boolean[split.length];
        	
        	for(int i = 0 ; i <= split.length ; i++) {        		
        		combination(split, visited, 0, split.length, i);       		
        	}
        }
        /*
        for(String keySet : map.keySet()){
        	System.out.println("keySet:"+keySet);
        }
        */
        for(int cnt : course) {
        	max = -1;
        	for(String key : map.keySet()) {
        		if(key.length()==cnt) {
        			max = Math.max(map.get(key), max);
        		}
        	}

        	for(String key : map.keySet()) {
        		if((key.length() == cnt) && max > 1 && (map.get(key) == max)) {
        			list.add(key);
        		}
        	}
        	
        	//System.out.println("cnt:"+cnt+" "+"max:"+max+"list:"+list.toString());
        }
        
        //for(String str : list) {
        //	System.out.println("answer:"+str);
        //}
        
        Collections.sort(list);

        answer = list.toArray(new String[list.size()]);
        
        return answer;
    }
	
	public void combination(String[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            print(arr, visited, n);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }
    
	public void print(String[] arr, boolean[] visited, int n) {
        StringBuilder sb = new StringBuilder();
        
		for (int i = 0; i < n; i++) {
            if (visited[i]) {
            	sb.append(arr[i]);
                //System.out.print(arr[i] + " ");
            }
        }
		String str = sb.toString();
		String[] split = str.split("");
		Arrays.sort(split);
		sb.setLength(0);
		for(String s : split) {
			sb.append(s);
		}
		str = sb.toString();
		
        if(sb.length()>=2) {
        	if(map.containsKey(str)) {
        		map.put(str, map.get(str)+1);
        	}
        	else {
        		map.put(str, 1);
        	}
        }
        //System.out.println();
    }
}

public class MenuRenewal {

	public static void main(String[] args) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		//String[] orders = {"ABCFG"};
		int[] course = {2,3,4};
		
		Solution solution = new Solution();
		String[] answer = solution.solution(orders, course);
		
		for(String str : answer) {
			System.out.print(str+" ");
		}
		System.out.println();
	}

}
