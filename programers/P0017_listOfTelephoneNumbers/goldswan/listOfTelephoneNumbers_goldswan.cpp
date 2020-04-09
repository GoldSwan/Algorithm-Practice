#include <iostream>
#include <string>
#include <vector>
#include <map>

using namespace std;

bool solution(vector<string> phone_book) {

	map<string, int> map_phone_book;

	bool answer = true;
	string s1;
	string s2;

	for(int i=0;i<phone_book.size();i++)
	{
		s1 = phone_book.at(i);

		for(int k=0;k<s1.length();k++)
		{
			s2 = phone_book.at(i).substr(0,k+1);

			if(s1.length()==k+1 && map_phone_book[s2]==1)
			{
				return answer=false;
			}

			else if(map_phone_book[s2]==2)
			{
				return answer=false;
			}

            map_phone_book[s2]=1;
		}
		map_phone_book[s2]=2;
	}

	return answer;
}

int main(void){

	vector<string> phone_book;

	phone_book.reserve(1000000);
	phone_book.push_back("1234");
	phone_book.push_back("789");
	phone_book.push_back("123");

	bool answer = solution(phone_book);
	cout<<answer<<endl;

	return 0;
}
