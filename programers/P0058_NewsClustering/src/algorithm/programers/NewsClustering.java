package algorithm.programers;
import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        double numAnd = 0;//교집합
        double numSum = 0;//합집합     
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        String str1Filter = alpabetFilter(str1);
        String str2Filter = alpabetFilter(str2);
        int str1Length = str1Filter.length();
        int str2Length = str2Filter.length();
        String[] str1Split = str1Filter.split("");
        String[] str2Split = str2Filter.split("");
        
        System.out.println("str1Filter:"+str1Filter);
        System.out.println("str2Filter:"+str2Filter);
        
        for(int i = 0 ; i < str1Length-1; i++) {
        	String strChar1 = str1Split[i] + str1Split[i+1];
        	if(map1.containsKey(strChar1)) {
        		map1.put(strChar1, map1.get(strChar1)+1);
        	}
        	else {
        		map1.put(strChar1, 1);
        	}
        }
        
        for(int i = 0 ; i < str2Length-1; i++) {
        	String strChar2 = str2Split[i] + str2Split[i+1];
        	if(map2.containsKey(strChar2)) {
        		map2.put(strChar2, map2.get(strChar2)+1);
        	}
        	else {
        		map2.put(strChar2, 1);
        	}
        }
        	
        for(String key2 : map2.keySet()) {
        	if(map1.containsKey(key2)){
        		numAnd += Math.min(map1.get(key2), map2.get(key2));
        		numSum += Math.max(map1.get(key2), map2.get(key2));
        		continue;
        	}
        	numSum += map2.get(key2);
        }
        
        for(String key1 : map1.keySet()) {
        	if(!map2.containsKey(key1)) {
        		numSum += map1.get(key1);
        	}
        }
        
        System.out.println(numAnd);
        System.out.println(numSum);
        
        answer = (int)Math.ceil((numAnd / numSum) * (double)65536);
        
        return answer;
    }
    
    public String alpabetFilter(String str) {
    	int strLength =  str.length();
    	String strUpper = str.toUpperCase();
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0 ; i < strLength ; i++) {
    		if(strUpper.charAt(i) >= 65 && strUpper.charAt(i) <= 90) {
    			sb.append(strUpper.charAt(i));
    		}
    	}  	
    	return sb.toString();
    }
}

public class NewsClustering {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String str1 = "aa1+aa2";
		//String str2 = "AAAA12";
		String str1 = "handshake";
		String str2 = "shake hands";
		
		Solution solution = new Solution();
		int answer = solution.solution(str1, str2);
		System.out.println("answer:" + answer);
	}

}
