package algorithm.programers;
import java.util.*;
//당시 풀이 상황
//순서대로 문자열2개씩 집합으로 묶어서 알파벳으로 이루어져 있는지 판단 후 집합으로 넣어야 하는데
//알파벳을 제외한 문자를 먼저 제거 후 문자열2개씩 묶어 집합을 만들어서 혼란을 겪음...
//교집합, 합집합 둘 다 0일 경우 유사도를 1로 하는 예외처리가 필요한가봄.
class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        double numAnd = 0;//교집합
        double numSum = 0;//합집합
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        String str1Upper = str1.toUpperCase();
        String str2Upper  = str2.toUpperCase();
        int str1Length = str1Upper.length();
        int str2Length = str2Upper.length();
        String[] str1Split = str1Upper.split("");
        String[] str2Split = str2Upper.split("");

        for(int i = 0 ; i < str1Length-1; i++) {
        	String strChar1 = str1Split[i] + str1Split[i+1];
        	if(isNonAlpabet(strChar1))
        		continue;
        	if(map1.containsKey(strChar1)) {
        		map1.put(strChar1, map1.get(strChar1)+1);
        	}
        	else {
        		map1.put(strChar1, 1);
        	}
        }

        for(int i = 0 ; i < str2Length-1; i++) {
        	String strChar2 = str2Split[i] + str2Split[i+1];
        	if(isNonAlpabet(strChar2))
        		continue;
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

        if(numAnd==0 && numSum==0)
        	return 65536;

        answer = (int)Math.floor((double)65536 * numAnd / numSum);

        return answer;
    }

    public boolean isNonAlpabet(String str) {
    	int strLength =  str.length();
    	for(int i = 0 ; i < strLength ; i++) {
    		if(str.charAt(i) < 65 || str.charAt(i) > 90) {
    			return true;
    		}
    	}
    	return false;
    }
}

public class NewsClustering {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "aa1+aa2";
		String str2 = "AAAA12";
		//String str1 = "handshake";
		//tring str2 = "shake hands";

		Solution solution = new Solution();
		int answer = solution.solution(str1, str2);
		System.out.println("answer:" + answer);
	}

}
