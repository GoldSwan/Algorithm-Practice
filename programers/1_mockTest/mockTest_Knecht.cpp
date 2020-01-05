#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> answers) {

	int a[] = { 1, 2, 3, 4, 5 };
	int b[] = { 2, 1, 2, 3, 2, 4, 2, 5 };
	int c[] = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };

	int a_cnt = 0;
	int b_cnt = 0;
	int c_cnt = 0;

	for (int i = 0; i < answers.size(); i++) {
		if (answers[i] == a[i % 5]) {
			//cout << "answers[" << i << "] is : " << answers[i] << ", and a[" << i % 5 << "] is " << a[i%5] << "\n";
			a_cnt++;
		}
		if (answers[i] == b[i % 8]) {
			//cout << "answers[" << i << "] is : " << answers[i] << ", and b[" << i % 8 << "] is " << b[i%8] << "\n";
			b_cnt++;
		}
		if (answers[i] == c[i % 10]) {
			//cout << "answers[" << i << "] is : " << answers[i] << ", and c[" << i % 10 << "] is " << c[i%10] << "\n";
			c_cnt++;
		}
	}

	int max = -1;
	if (a_cnt > max) {
		max = a_cnt;
	}
	if (b_cnt > max) {
		max = b_cnt;
	}
	if (c_cnt > max) {
		max = c_cnt;
	}

	vector<int> answer;
	if (a_cnt == max) {
		answer.push_back(1);
	}
	if (b_cnt == max) {
		answer.push_back(2);
	}
	if (c_cnt == max) {
		answer.push_back(3);
	}

	return answer;
}

/*int main() {

	vector<int>input = { 1,3,2,4,2 };

	vector<int> answer = solution(input);

	for (int i = 0; i < answer.size(); i++) {
		cout << answer[i] << "\n";
	}
	return 0;
}*/