#include <iostream>
#include <queue>
#include <string>
#include <vector>

using namespace std;

bool check[200] = {false};
int a[200] = {0};

void dfs(int n, vector<vector<int>> computers, int v){

	//cout<<v;

	check[v] = true;

	for(int i=0;i<computers.size();i++){
		if(computers.at(v).at(i) == 1 && !check[i])
		{
			//cout<<" ";
			dfs(n,computers,i);
		}
	}
}

int solution(int n, vector<vector<int>> computers) {

    int answer = 0;

	for(int i=0;i<computers.size();i++)
	{
		if(!check[i])
		{
				dfs(n,computers,i);
				answer++;
		}
	}

    return answer;
}

int main(void){

	vector<vector<int>> computers;
	int answer;

	vector<int> v1;
	vector<int> v2;
	vector<int> v3;
	vector<int> v4;
	vector<int> v5;
	vector<int> v6;

	v1.push_back(1);
	v1.push_back(1);
	v1.push_back(0);
	v1.push_back(0);
	v1.push_back(0);
	v1.push_back(0);

	v2.push_back(1);
	v2.push_back(1);
	v2.push_back(1);
	v2.push_back(1);
	v2.push_back(0);
	v2.push_back(0);

	v3.push_back(0);
	v3.push_back(1);
	v3.push_back(1);
	v3.push_back(1);
	v3.push_back(0);
	v3.push_back(0);

	v4.push_back(0);
	v4.push_back(1);
	v4.push_back(1);
	v4.push_back(1);
	v4.push_back(0);
	v4.push_back(0);

	v5.push_back(0);
	v5.push_back(0);
	v5.push_back(0);
	v5.push_back(0);
	v5.push_back(1);
	v5.push_back(1);

	v6.push_back(0);
	v6.push_back(0);
	v6.push_back(0);
	v6.push_back(0);
	v6.push_back(1);
	v6.push_back(1);

	computers.push_back(v1);
	computers.push_back(v2);
	computers.push_back(v3);
	computers.push_back(v4);
	computers.push_back(v5);
	computers.push_back(v6);

	answer = solution(computers.size(),computers);

	cout<<answer<<endl;

	return 0;
}
