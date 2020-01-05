#include <iostream>

using namespace std;

int C;
int N;
int L;

int main() {

	cin >> C;
	for (int test = 0; test < C; test++) {
		cin >> N >> L;
		int A[1001];
		for (int i = 0; i < N; i++) {
			cin >> A[i];
		}
		double answer = 1000000.0;
		while (L <= N) {
			for (int i = 0; i < N; i++) {
				double temp = 0.0;
				if (i + L > N) {
					continue;
				}
				for (int j = i; j < i + L; j++) {
					temp += A[j];
				}
				if (answer > (temp / (double)L)) {
					answer = (temp / (double)L);
				}
			}
			L++;
		}

		//cout << answer << "\n";
		printf("%.12f\n", answer);
	}
	return 0;
}