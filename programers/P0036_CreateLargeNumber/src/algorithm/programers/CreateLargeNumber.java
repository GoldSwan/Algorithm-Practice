package algorithm.programers;

class Solution {
	public String solution(String number, int k) {
		String answer = "";
		int index = 0;
		int currentDigitNumber = number.length() - k;//현재 십진수의 자리수
		int LargeNumLength = currentDigitNumber;//가장 큰 수의 최대 자리수
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < LargeNumLength; i++) {
			char max = '0';
			int maxIndex = number.length() - currentDigitNumber;
			for(int j = index ; j <= maxIndex ; j++){
				//(j+1) : 선택한 index까지의 number 갯수
				//(currentDigitNumber-1) : 선택한 index의 오른쪽에 있는 숫자들이 최소 있어야 할 개수
				//둘을 합하면 j + currentDigitNumber <= number의 숫자 개수가 되어야함.
				//j <= currentDigitNumber - number.length() 이므로
				//반복문이 돌때마다 1)length()를 호출하여 문자열 길이를 계산하는 연산과 2)상수 연산을 빼기 위해 우측 연산을 maxIndex 변수에 담음.
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
