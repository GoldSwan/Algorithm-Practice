package algorithm.concept;

class Solution{
	int cnt = 0;
	public void solution(String[] stringArray) {
		int[] visited = new int[stringArray.length];
		StringBuilder sb = new StringBuilder();
		for(int start = 0 ; start<stringArray.length;start++) {
			sb.append(stringArray[start]);
			doCombination(stringArray, sb, visited, 3, start+1);
			sb.setLength(Math.max(sb.length()-1,0));
		}
		System.out.println("cnt:"+cnt);
	}
	public void doCombination(String[] stringArray, StringBuilder sb, int[] visited, int length, int start) {
		if(length == 1) {
			System.out.println(sb.toString());
			cnt++;
			return;
		}
				
		for(int i = start ; i<stringArray.length;i++) {
			--length;
			sb.append(stringArray[i]);
			doCombination(stringArray, sb, visited, length, start+1);
			++length;
			sb.setLength(Math.max(sb.length()-1,0));
		}		
	}
}
public class Combination {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] stringArray = {"0","1","2","3","4"};
		Solution sol = new Solution();
		sol.solution(stringArray);
	}

}
