import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        String[] strNumbers = new String[numbers.length];
        
        for (int i = 0; i < numbers.length; i++)
        	strNumbers[i] = String.format("%d",numbers[i]);
        
        Arrays.sort(strNumbers, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s2+s1).compareTo(s1+s2);
            }
        });
        
        if (strNumbers[0].equals("0")) 
        	return "0";
             
        for (String num : strNumbers)
        	sb.append(num);
        
        answer = sb.toString();
        
        return answer;
    }
}
public class biggestNumber_goldswan {
    public static void main(final String[] args) {
        Solution sol = new Solution();
        // 0~1000 정수
        // int[] numbers = {6, 10, 2};//6210
        int[] numbers = { 3, 3, 30, 34, 5, 9, 99, 900, 98, 981, 999, 97 };// 9534330
        String answer = "";
        answer = sol.solution(numbers);
        System.out.println(answer);
        
        System.out.println("999998".compareTo("998999"));
        System.out.println("998999".compareTo("999998"));
    }
}

