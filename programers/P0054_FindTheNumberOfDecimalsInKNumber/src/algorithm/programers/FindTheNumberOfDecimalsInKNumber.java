package algorithm.programers;

class Solution {
    public int solution(int n, int k) {
        int answer = -1;
        String strDecimalNum = getKDecimalNum(n, k);
        System.out.println(strDecimalNum);
        return answer;
    }
    public String getKDecimalNum(int n, int k) {
    	StringBuilder sb = new StringBuilder();
    	
    	while(n>0) {
    		int remain = n%k;
    		sb.append(remain);
    		n /= k;
    	}
    	
    	return sb.reverse().toString();
    }
}

public class FindTheNumberOfDecimalsInKNumber {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int n = 11;
		int k = 3;
		solution.solution(n, k);

	}

}
