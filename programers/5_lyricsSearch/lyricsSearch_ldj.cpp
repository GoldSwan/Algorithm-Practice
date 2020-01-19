#include <iostream>
#include <stdio.h>
#include <string.h>
#include <vector>
#include <algorithm>
#include <stack>
#include <queue>
#include <string>
#include <math.h>

//namespaces
using namespace std;

//variables
int T, N, K;
vector<int> A;

//pointer variables

//comparator

//method
class Trie {
public:
	Trie* next[26];
	int count;
	bool term;
	Trie() :term(false), count(1) {
		memset(next, 0, sizeof(next));
	}
	~Trie() {
		for (int i = 0; i < 26; i++) {
			if (next[i]) {
				delete next[i];
			}
		}
	}
	void insert(const char* key) {
		if (*key == '\0') {
			term = true;
		} else {
			int curr = *key - 'a';
			if (next[curr] == NULL) {
				next[curr] = new Trie();
			} else {
				next[curr]->count++;
			}
			next[curr]->insert(key + 1);//다음 문자를 삽입
		}
	}
	int find(const char* key) {
		int curr = *key - 'a';
		if (*key == '?') {
			int temp = 0;
			for (int k = 0; k < 26; k++) {
				if (next[k] != NULL) {
					temp += next[k]->count;
				}
			}
			return temp;
		}
		if (next[curr] == NULL) { return 0; }
		if ((*key + 1) == '?') { return next[curr]->count; }
		return next[curr]->find(key + 1);
	}
};

Trie* root[10001];
Trie* reroot[10001];

vector<int> solution(vector<string> words, vector<string> queries) {
	int wsize = words.size();
	int qsize = queries.size();
	vector<int> answer(qsize, 0);

	for (auto & a : words) {
		int size = a.size();

		const char * c = a.c_str();// 첫 글자를 가져온다.
		if (root[size] == NULL) { root[size] = new Trie(); }
		root[size]->insert(c);

		string reversed_string = a;
		reverse(reversed_string.begin(), reversed_string.end());

		const char *k = reversed_string.c_str();
		if (reroot[size] == NULL) { reroot[size] = new Trie(); }
		reroot[size]->insert(k);
	}

	int idx = 0;

	for (auto &a : queries) {

		int size = a.size();

		if (a[size - 1] == '?') {//끝에 있는 거
			const char *c = a.c_str();

			if (root[size] == NULL) { idx++; continue; } else { answer[idx] = root[size]->find(c); }

		} else {//앞에 있는 거
			string re = a;
			reverse(re.begin(), re.end());
			const char * k = re.c_str();

			if (reroot[size] == NULL) { idx++; continue; } else { answer[idx] = reroot[size]->find(k); }
		}
		idx++;
	}

	return answer;
}