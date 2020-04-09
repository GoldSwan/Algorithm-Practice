#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int solution(vector<int> scoville, int K) {
    int answer = 0;
	  priority_queue< int, vector<int>, greater<int> > pq;

	for(int i=0; i<scoville.size();i++)
	{
		pq.push(scoville.at(i));
	}

	while(!pq.empty())
	{
	int first_min  =  pq.top();

	if(first_min>=K)
	break;

	if(pq.size()==1 && first_min<K)
	{
		pq.pop();
		answer++;
	}

	else if(pq.size()>=2)
	{
	     pq.pop();
	     int second_min =  pq.top();
	     pq.pop();

	     pq.push(first_min+(second_min*2));
	     answer++;
	}
	}

	if(answer==scoville.size())
	answer = -1;

    return answer;
}


int main(void){

	vector<int> scoville;

	scoville.push_back(1);
	scoville.push_back(2);
    scoville.push_back(3);
    scoville.push_back(9);
    scoville.push_back(10);
    scoville.push_back(12);

	int answer = solution(scoville, 7);
	cout<<answer<<endl;

	return 0;
}
