package algorithm.concept;

class Solution{
	int cnt = 0;
	public void solution(String[] stringArray) {
		int[] visited = new int[stringArray.length];
		StringBuilder sb = new StringBuilder();
		doPermutation(stringArray, sb, visited, 5, 0);
		System.out.println("cnt:"+cnt);
	}
	public void doPermutation(String[] stringArray, StringBuilder sb, int[] visited, int length, int depth) {
		if(length == depth) {
			System.out.println(sb.toString());
			cnt++;
			return;
		}
		for(int i =0 ;i < stringArray.length ; i++) {
			if(visited[i]==0) {
				visited[i] = 1;
				++depth;
				sb.append(stringArray[i]);
				doPermutation(stringArray, sb, visited, length, depth); 
				visited[i] = 0;
				--depth;
				sb.setLength(Math.max(sb.length() - 1, 0));//sb.length() = 0 일 때 Math.max(sb.length() - 1, 0)를 사용하여 에러 방지
			}
		}
	}
}

public class Permutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] stringArray = {"0","1","2","3","4"};
		Solution sol = new Solution();
		sol.solution(stringArray);
	}

}
