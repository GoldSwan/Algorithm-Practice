import java.util.*;

class Solution {
	static LyricsSearch[] trie = new LyricsSearch[10001];
	static LyricsSearch[] reversedTrie = new LyricsSearch[10001];

	public int[] solution(String[] words, String[] queries) {
		int[] answer = {};

        //
		makeTries(words);
		answer = searchQueries(queries);
		return answer;
	}

	public void makeTries(String[] words) {
		for (String word : words) {
			int size = word.length();
			// 단어길이로 정렬
			if (trie[size] == null) {
				trie[size] = new LyricsSearch();
			}
			trie[size].insert(word);

			StringBuilder sb = new StringBuilder(word);
			String reversedString = sb.reverse().toString();
			if (reversedTrie[size] == null) {
				reversedTrie[size] = new LyricsSearch();
			}
			reversedTrie[size].insert(reversedString);
		}
	}

	public int[] searchQueries(String[] queries) {
		int[] answer = {};
		ArrayList<Integer> answers = new ArrayList<>();
		for (String query : queries) {
			int size = query.length();

			// 단어 맨 뒤가 '?'일때
			if (query.charAt(query.length() - 1) == '?') {
				// 해당 사이즈 단어가 아예 없는 경우
				if (trie[size] == null) {
					answers.add(0);
				} else {
					// 해당 사이즈 단어 모음에서 일치단어 찾는다
					answers.add(trie[size].search(query));
				}
				// 앞에 ?가 있는 경우
			} else {
				StringBuilder sb = new StringBuilder(query);
				String reversed = sb.reverse().toString();
				if (reversedTrie[size] == null) {
					answers.add(0);
				} else {
					answers.add(reversedTrie[size].search(reversed));
				}
			}
		}

		answer = new int[answers.size()];

		for (int c = 0; c < answer.length; c++) {
			answer[c] = answers.get(c);
		}
		return answer;

	}

}

class LyricsSearch {

	LyricsSearchNode root;

	public LyricsSearch() {
		this.root = new LyricsSearchNode(' ');
        // 사용횟수 초기화
		root.usedCnt = 0;
	}

	public void insert(String words) {
		LyricsSearchNode node = root;
		node.usedCnt++;

		for (int a = 0; a < words.length(); ++a) {
			char c = words.charAt(a);
			// 아스키코드 값으로 알파벳순서값 맞춤
			int idx = c - 'a';

			// 현재 idx이 자식노드없으면 생성
			if (node.child[idx] == null) {
				node.child[idx] = new LyricsSearchNode(c);
			} else {
            // 이미 있으면 사용횟수만 증가
				node.child[idx].usedCnt++;
			}
			// child 찾은 시점부터 다시 시작(자식노드의 자식노드 찾음)
			node = node.child[idx];
		}
	}

	public int search(String query) {
		LyricsSearchNode node = root;

		for (int b = 0; b < query.length(); b++) {
			char c = query.charAt(b);
			int idx = c - 'a';

			if (c == '?') {
                // 부모의 글자갯수 출력
				return node.usedCnt;
			}

			if (node.child[idx] != null) {
				node = node.child[idx];
			} else if (node.child[idx] == null) {
				return 0;
			}
		}
		return node.usedCnt;
	}

}

class LyricsSearchNode {
	public char word;
    // 자식노드
	public LyricsSearchNode[] child;
    // 사용횟수
	public int usedCnt;

	public LyricsSearchNode(char word) {
        // 개별 char 값 셋팅
		this.word = word;
		// 알파벳 갯수만큼 부여
		this.child = new LyricsSearchNode[26];
        // 생성하는 시점 = 사용횟수
		this.usedCnt = 1;
	}
}
