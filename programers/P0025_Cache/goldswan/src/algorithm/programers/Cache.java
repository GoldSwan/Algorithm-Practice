package algorithm.programers;

import java.util.ArrayList;

class Solution {
	public int solution(int cacheSize, String[] cities) {
		int answer = 0;
		ArrayList<String> casheArrayList = new ArrayList<String>();

		for (String city : cities) {
			if (isCashHit(city, casheArrayList)) {
				answer++;
				continue;
			}

			addCashe(cacheSize, city, casheArrayList);
			answer += 5;
		}

		return answer;
	}

	public boolean isCashHit(String city, ArrayList<String> casheArrayList) {

		if (casheArrayList.size() == 0)
			return false;

		for (int i = 0; i < casheArrayList.size(); i++) {
			if (city.toUpperCase().equals(casheArrayList.get(i))) {
				updateCashe(city, casheArrayList, i);
				return true;
			}
		}

		return false;
	}

	public void updateCashe(String city, ArrayList<String> casheArrayList, int index) {

		casheArrayList.remove(index);
		casheArrayList.add(city.toUpperCase());

	}

	public void addCashe(int cacheSize, String city, ArrayList<String> casheArrayList) {

		if (cacheSize == 0)
			return;

		if (casheArrayList.size() == cacheSize) {
			casheArrayList.remove(0);
		}

		casheArrayList.add(city.toUpperCase());
	}
}

public class Cache {

	public static void main(String[] args) {
		int cacheSize = 0;
		String[] cities = { "a", "b", "c", "d", "A", "a", "c", "e" };
		Solution sol = new Solution();
		int answer = sol.solution(cacheSize,  cities);
		System.out.println("answer:" + answer);
	}

}
