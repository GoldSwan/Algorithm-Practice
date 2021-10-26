package algorithm.programers;

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
    	Set<String> hashSet = new HashSet<>();
    	StringBuilder sb = new StringBuilder();

    	for(String phone_number : phone_book) {
    		hashSet.add(phone_number);
    	}

    	for(String phone_number : phone_book) {
    		for(int i = 0; i<phone_number.length()-1 ; i++) {
    			sb.append(phone_number.charAt(i));
    			if(hashSet.contains(sb.toString()))
    				return false;
    		}
    		sb.delete(0, sb.length());
    	}

        return true;
    }
}

public class ListOfTelephoneNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] phone_book = new String[] {"119", "97674223", "1195524421"};
		boolean answer = false;
		Solution solution = new Solution();
		answer = solution.solution(phone_book);
		System.out.println(answer);
	}

}
