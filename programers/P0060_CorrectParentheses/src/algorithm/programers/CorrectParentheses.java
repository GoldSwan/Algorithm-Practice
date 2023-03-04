package algorithm;
import java.util.*;

//스택을 이용한 풀이
class Solution {
    boolean solution(String s) {
        var parenthesesStack = new Stack<Character>();
        int sLength = s.length();

        if(sLength%2 == 1) return false;

        for(int i = 0 ; i < sLength ; i++){
            char c = s.charAt(i);
            if(c == '('){
                parenthesesStack.push(c);
                continue;
            }
            if(parenthesesStack.isEmpty()) return false;
            parenthesesStack.pop();
        }

        return parenthesesStack.isEmpty();
    }
}

//선형 탐색을 이용한 풀이
class Solution2 {
    boolean solution(String s) {
        int sLength = s.length();

        if(sLength%2 == 1) return false;

        int count = 0;
        for(int i = 0 ; i < sLength ; i++){
            char c = s.charAt(i);
            count = (c == '(') ? ++count : --count;
            if(count < 0) return false;
        }

        return count == 0;
    }
}

public class CorrectParentheses {
    public static void main(String[] args) throws Exception {
        var solution = new Solution();
        var solution2 = new Solution2();
        String s = "())(";
        boolean answer = solution.solution(s);
        System.out.println("answer:"+answer);
        answer = solution2.solution(s);
        System.out.println("answer:"+answer);
    }
}
