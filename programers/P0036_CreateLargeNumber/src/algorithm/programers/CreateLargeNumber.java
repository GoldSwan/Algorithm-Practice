package algorithm.programers;

class Solution {
	public String solution(String number, int k) {

		String answer = "";
		int index = 0;
		int currentDigitNumber = number.length() - k;//현재 십진수의 자리수
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < number.length() - k; i++) {
			
			char max = '0';
			
			for(int j = index ; j + currentDigitNumber <= number.length();j++){
				//(j+1) : 선택한 index까지의 number 갯수
				//(currentDigitNumber-1) : 선택한 index의 오른쪽에 있는 숫자들이 최소 있어야 할 개수
				//둘을 합하면 j + currentDigitNumber <= number의 숫자 개수가 되어야함.
				if (max < number.charAt(j)) {
					max = number.charAt(j);
					index = j + 1;
				}
			}
			
			sb.append(max);
			
			currentDigitNumber--;
		}
		
		answer = sb.toString();
		
		return answer;
	}
}

public class CreateLargeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution solution = new Solution();
		String number = "4177252841";
		int k = 4;
		String answer = solution.solution(number, k);

		System.out.print(answer);
	}

}
