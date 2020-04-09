#include <string>
#include <vector>
#include <iostream>

using namespace std;

int answer=0;

void dfs_function(vector<int> numbers, int target, int sum, int height){

	if(numbers.size()==height)
	{
		if(sum==target)
			answer++;
		return;
	}
	dfs_function(numbers, target, sum+numbers.at(height),  height+1);
	dfs_function(numbers, target, sum-numbers.at(height),  height+1);
}

int solution(vector<int> numbers, int target) {

	dfs_function(numbers, target, 0, 0);

    return answer;
}

int main(void){

	vector<int> numbers;

	numbers.push_back(1);
	numbers.push_back(1);
	numbers.push_back(1);
	numbers.push_back(1);
	numbers.push_back(1);

	int answer = solution(numbers, 3);

	cout<<answer<<endl;

	return 0;
}
