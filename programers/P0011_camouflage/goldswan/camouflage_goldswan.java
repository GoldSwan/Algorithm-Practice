package algorithm.programers;

import java.util.HashMap;
import java.util.Vector;

class Solution {
    
	public int solution(String[][] clothes) {
	    int answer = 1;
	    
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Vector<String> key = new Vector<String>();
        
        for(int i=0;i<clothes.length;i++) {
        	if(map.containsKey(clothes[i][1])) {
        		map.put(clothes[i][1],map.get(clothes[i][1])+1);
        		continue;
        	}
        	map.put(clothes[i][1],1);
        	key.add(clothes[i][1]);
        }
        
        for(int i=0;i<key.size();i++) {
        	answer  *= (map.get(key.get(i))+1);//아무것도 안입은 경우 + 1
        }
        
        return answer-1;//모두 안입었을 경우는 제외 -1
    }

}

public class camouflage_goldswan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

        int answer = 0;
        Solution sol = new Solution();
        answer = sol.solution(clothes);
        System.out.println(answer);
	}

}
