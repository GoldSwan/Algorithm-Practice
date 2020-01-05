#include <iostream>
#include <stdio.h>
#include <string.h>
#include <vector>
#include <algorithm>
#include <stack>
#include <queue>
#include <string>
#include <math.h>

/**
	[문제]


*/

//namespaces
using namespace std;
#define SIZE 10000000

//variables
int * A;
bool isPrime[SIZE];
//pointer variables

//comparator

//method
int solution(string numbers);

//main method
int main() {
	//speedy reading option
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	//new part
	string input;
	cin >> input;

	int answer = solution(input);

	cout << answer;

	return 0;
}

int solution(string numbers) {
	int answer = 0;

	A = new int[10];
	for (int i = 0; i < 10; i++) {
		A[i] = 0;
	}

	for (int i = 0; i < numbers.length(); i++) {
		A[(int)(numbers[i] - '0')]++;
	}

	//making prime number
	for (int i = 2; i < SIZE; i++) {
		isPrime[i] = true;
	}
	//에라토스테네스의 해
	for (int i = 2; i * i < SIZE; i++) {
		if (isPrime[i]) {
			for (int j = i * i; j < SIZE; j += i) {
				isPrime[j] = false;
			}
		}
	}

	int rule[10];
	for (int i = 0; i < SIZE; i++) {
		if (isPrime[i] == false) {
			continue;
		}
		//rule 초기화
		for (int i = 0; i < 10; i++) {
			rule[i] = 0;
		}
		int j = i;
		while (j > 0) {
			rule[j % 10]++;
			j /= 10;
		}

		bool flag = true;
		for (int i = 0; i < 10; i++) {
			if (A[i] < rule[i]) {
				flag = false;
				break;
			}
		}
		if (flag == false) {
			continue;
		}


		answer++;
	}

	return answer;
}
