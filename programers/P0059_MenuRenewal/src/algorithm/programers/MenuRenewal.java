package algorithm.programers;

import java.util.*;

class Solution {
	Map<String, Integer> map = new HashMap<>();
	int[] arrMax = {};
	Queue<String> pq = new PriorityQueue<>();

	public String[] solution(String[] orders, int[] course) {
		String[] answer = {};

		for (String order : orders) {
			String[] split = order.split("");
			boolean[] visited = new boolean[split.length];

			for (int i = 0; i <= split.length; i++) {
				combination(split, visited, 0, split.length, i);
			}
		}

		arrMax = new int[11];//10자리이하 문자열들의 각 max를 담는 배열
    	for(String key : map.keySet()) {
    		arrMax[key.length()] = Math.max(map.get(key), arrMax[key.length()]);
    	}

        for(int cnt : course) {
        	for(String key : map.keySet()) {
        		if((key.length() == cnt) && arrMax[key.length()] > 1 && (map.get(key) == arrMax[key.length()])) {
        			pq.add(key);
        		}
        	}
        }

        answer = new String[pq.size()];

        int i = 0;
        while(!pq.isEmpty()) {
        	answer[i++] = pq.poll();
        }

		return answer;
	}

	public void combination(String[] arr, boolean[] visited, int start, int n, int r) {
		if (r == 0) {
			print(arr, visited, n);
			return;
		}

		for (int i = start; i < n; i++) {
			visited[i] = true;
			combination(arr, visited, i + 1, n, r - 1);
			visited[i] = false;
		}
	}

	public void print(String[] arr, boolean[] visited, int n) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				sb.append(arr[i]);
			}
		}
		String key = sb.toString();
		String[] split = key.split("");
		Arrays.sort(split);
		sb.setLength(0);
		for (String s : split) {
			sb.append(s);
		}
		key = sb.toString();

		if (sb.length() >= 2) {
			map.put(key, map.getOrDefault(key, 0) + 1);
		}
	}
}

public class MenuRenewal {

	public static void main(String[] args) {
		String[] orders = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
		// String[] orders = {"ABCFG"};
		int[] course = { 2, 3, 4 };

		Solution solution = new Solution();
		String[] answer = solution.solution(orders, course);

		for (String str : answer) {
			System.out.print(str + " ");
		}
		System.out.println();
	}

}
