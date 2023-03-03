package algorithm;
import java.util.*;

//스택을 이용한 풀이
class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        int sLength = s.length();

        for(int i = 0 ; i < sLength ; i++){
            char c = s.charAt(i);
            if(c == '('){
                stack.push(c);
                continue;
            }
            if(stack.isEmpty()) return false;
            stack.pop();
        }

        return stack.isEmpty();
    }
}

//처음 시도했던 풀이 - 완전 탐색을 시도해보았으나 실패
/*
class Solution {
    boolean solution(String s) {
        List<String> sList = new ArrayList<>(Arrays.asList(s.split("")));
        if(s.length()<=1)
            return false;
        if(s.length()%2 == 1) 
            return false;

        while(sList.size() > 0){
            int i = 0;
            while(i<sList.size()){
                if(sList.get(i).equals(")")){
                    if(i-1 < 0) return false;
                    if(sList.get(i-1).equals("(")){
                        sList.remove(i);
                        sList.remove(i-1);
                     break;
                     }
                }
                i++;
            }
            if(sList.size() > 0 && i == sList.size()) 
                return false;
        }

        return true;
    }
}
*/

public class CorrectParentheses {
    public static void main(String[] args) throws Exception {
        var solution = new Solution();
        String s = "())(";
        boolean answer = solution.solution(s);
        System.out.println("answer:"+answer);
    }
}
