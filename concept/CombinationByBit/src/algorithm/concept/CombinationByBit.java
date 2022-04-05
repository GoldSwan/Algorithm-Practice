package algorithm.concept;

import java.util.*;

//2021 KAKAO BLIND RECRUITMENT - 순위 검색 문제 일부 참고
class Solution {
	public void solution(String[] info, String[] query) {
		Map<String, List<Integer>> infos = new HashMap<>();
		for (String in : info) {
			String[] split = in.split(" ");
			int score = Integer.parseInt(split[4]);
		
			//비트연산을 이욯한 조합 구현
			//info안에는 언어, 직군, 경력, 소울푸트 총 4개의 카테고리가 존재한다.
			//따라서 원소4개가 존재하는 집합의 부분 집합을 구하는 방식으로 조합을 구한다.
			//System.out.println("(1 << 4):" + (1 << 4));
			System.out.println("(1 << 0):" + (1 << 0));
			for (int i = 0; i < (1 << 4); i++) {
				StringBuilder key = new StringBuilder();
				for (int j = 0; j < 4; j++) {
					//System.out.println("(1 << j):"+(1 << j));
					if ((i & (1 << j)) > 0)
						key.append(split[j]);//비트연산을 이용하여 4카테고리에 대한 key 조합을 구한다.
				}
				infos.computeIfAbsent(key.toString(), s -> new ArrayList<>()).add(score);
			}
		}
		//조합 결과 출력
		for(Map.Entry<String, List<Integer>> infoEntry : infos.entrySet()) {
			System.out.println(String.join("", " key: ", infoEntry.getKey()," value: ",infoEntry.getValue().toString()));
		}
	}
}

public class CombinationByBit {

	public static void main(String[] args) {
		String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String[] query = { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };

		Solution solution = new Solution();
		solution.solution(info, query);
	}

}
