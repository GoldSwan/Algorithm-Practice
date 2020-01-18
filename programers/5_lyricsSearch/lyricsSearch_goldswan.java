/********************************************************************
작성일 : 2020-01-18
작성자 : GoldSwan
문제 : 프로그래머스 - 가사검색(lyricsSearch)
출저 : programers
풀이 : 트라이 자료구조 이용
예상 시간복잡도 : 전체 길이합 : N 일 때 O(N)
TEST 결과 : 모두통과
*********************************************************************/

import java.util.HashMap;

class Solution {

	int[] answer;
	Trie frontTrieRoot = new Trie('*');
	Trie BackTrieRoot = new Trie('*');

	public int[] solution(String[] words, String[] queries) {

		answer = new int[queries.length];

		MakeTrie(words);// 트라이 자료구조 생성

		wordSearch(queries);// 단어 검색

		return answer;
	}

	public void MakeTrie(String[] words) {

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			Trie prevFront = frontTrieRoot;
			Trie prevBack = BackTrieRoot;
			Trie current;
			// 단어 그대로 생성
			for (int j = 0; j < word.length(); j++) {
				char c = word.charAt(j);
				current = new Trie(c);
				prevFront = prevFront.putChild(current, word.length());
			}
			// 접미사가 ?로 된 키워드 탐색을 위해 reverse 하여 생성
			for (int j = word.length() - 1; j >= 0; j--) {
				char c = word.charAt(j);
				current = new Trie(c);
				prevBack = prevBack.putChild(current, word.length());
			}
		}

	}

	public void wordSearch(String[] queries) {

		for (int i = 0; i < queries.length; i++) {
			String query = queries[i];
			Trie TrieRoot;

			// 접두사가 물음표인 경우 검색 fro??
			if (query.charAt(0) != '?') {
				TrieRoot = frontTrieRoot;
				for (int j = 0; j < query.length(); j++) {
					char c = query.charAt(j);
					if (c == '?') {
						answer[i] = TrieRoot.getNumChildLen(query.length());
						break;
					}
					TrieRoot = TrieRoot.getChild(c);
					if (TrieRoot == null) {
						answer[i] = 0;
						break;
					}
				}
			} else {// 접미사가 물음표인 경우 검색 ????o
				TrieRoot = BackTrieRoot;
				for (int j = query.length() - 1; j >= 0; j--) {
					char c = query.charAt(j);
					if (c == '?') {
						answer[i] = TrieRoot.getNumChildLen(query.length());
						break;
					}
					TrieRoot = TrieRoot.getChild(c);
					if (TrieRoot == null) {
						answer[i] = 0;
						break;
					}
				}
			}
		}
	}
}

class Trie {
	char c;
	HashMap<Character, Trie> childMap;//자식노드 Map
	HashMap<Integer, Integer> numChildLenMap;//길이별 마지막 자식 갯수 저장

	Trie(char c) {
		this.c = c;
		childMap = new HashMap<Character, Trie>();
		numChildLenMap = new HashMap<Integer, Integer>();
	}

	Trie putChild(Trie trie, int len) {
		if (!childMap.containsKey(trie.c)) {
			childMap.put(trie.c, trie);//존재하지 않은 글자인 경우 자식노드 put
		}
		if (numChildLenMap.containsKey(len)) {
			numChildLenMap.put(len, numChildLenMap.get(len) + 1);
		} else {
			numChildLenMap.put(len, 1);
		}
		return childMap.get(trie.c);
	}

	Trie getChild(char c) {
		return childMap.get(c);
	}

	int getNumChildLen(int len) {
		if (numChildLenMap.containsKey(len))
			return numChildLenMap.get(len);
		return 0;
	}
}

public class lyricsSearch_goldswan {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();

		String[] words = { "frodo", "front", "frost", "frame", "kakao", "frozen", "prozen", "zzzzz" };
		String[] queries = { "fro??", "????o", "fr???", "fro???", "pro?", "?????", "??????" };

		int answer[] = sol.solution(words, queries);

		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}
}
