package algorithm.programers;

import java.util.ArrayList;

class Solution {
    public String solution(int n) {
        //처음 작성한 풀이법                
        ArrayList<Integer> strArray = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        while(n>0){
            int num = (n % 3 == 0) ? 4 : n % 3;
            strArray.add(num);
            n = (n % 3 == 0) ? n/3 - 1 : n/3; 
        }
        
        for(int i = strArray.size()-1;i>=0;i--){
            sb.append(Integer.toString(strArray.get(i)));
        }     
        return sb.toString();    
        
        /*
        //다른 풀이 생각용으로 기재 - 위의 풀이가 속도적 측면에서는 더 빠르다.
        String answer = "";
        
        String[] num = {"4","1","2"};
        
        while(n>0){
            answer =  num[n%3] + answer;
            n = (n % 3 == 0) ? n/3 - 1 : n/3;
        }
        
        return answer;
        */
    }
}

public class Number124OfNation {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		int n = 10;
		String answer = sol.solution(n);
		System.out.println("answer:"+answer);
	}
}
