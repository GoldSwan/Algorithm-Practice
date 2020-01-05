#include<iostream>
#include<vector>
#include<string>
using namespace std;

int C, H, W;

// 주어진 칸을 덮을 수 있는 네 가지 방법
// 블록을 구성하는 세 칸의 상대적 위치 (dy, dx)의 목록
const int coverType[4][3][2] = {
	{ { 0, 0 }, { 1, 0 }, { 0, 1 } },
	{ { 0, 0 }, { 0, 1 }, { 1, 1 } },
	{ { 0, 0 }, { 1, 0 }, { 1, 1 } },
	{ { 0, 0 }, { 1, 0 }, { 1, -1 } }
};

bool set(vector<vector<int>> & board, int y, int x, int type, int delta);
int cover(vector<vector<int >> & board);

int main() {

	//speedy reading option
	cin.tie(NULL);
	ios_base::sync_with_stdio(false);

	//new part
	cin >> C;
	for (int test_case = 0; test_case < C; test_case++) {
		long long answer = 0;

		cin >> H >> W;
		string line;
		vector<vector<int> > board(H, vector<int>(W, 0));
		for (int i = 0; i < H; i++) {
			cin >> line;
			for (int j = 0; j < W; j++) {
				if (line[j] == '#') {
					board[i][j] = 1;
				} else {
					board[i][j] = 0;
 				}
			}
		}

		answer = cover(board);

		cout << answer << "\n";
	}

	return 0;
}

//board의 (y, x)를 type번 방법으로 덮거나, 덮었던 블록을 없앤다
//delta가 1이면 덮고, -1이면 덮었던 블록을 없앤다
//게임판 밖으로 나가거나, 겹치거나, 검은 칸을 덮을 때 false 반환
bool set(vector<vector<int>>& board, int y, int x, int type, int delta) {
	bool ok = true;

	for (int i = 0; i < 3; i++) {
		const int ny = y + coverType[type][i][0];
		const int nx = x + coverType[type][i][1];
		//범위 초과 리턴
		if (ny < 0 || ny >= (int)board.size() || nx < 0
			|| nx >= (int)board[0].size()) {
			ok = false;
		} else if ((board[ny][nx] += delta) > 1) {//겹쳐질 경우
			ok = false;
		}
	}
	return ok;
}

// board의 모든 빈 칸을 덮을 수 있는 방법의 수를 반환
// board[i][j] = 1 이미 덮인 칸 혹은 검은 칸
// board[i][j] = 0 아직 덮이지 않은 칸
int cover(vector<vector<int>> &board) {
	// 아직 채우지 못한 칸 중 가장 윗줄 왼쪽에 있는 칸을 찾는다
	int y = -1, x = -1;
	for (int i = 0; i < (int)board.size(); i++) {
		for (int j = 0; j < (int)board[i].size(); j++)
			if (board[i][j] == 0) {
				y = i;
				x = j;
				break;
			}
		if (y != -1) break;
	}
	// 기저 사례: 모든 칸을 채웠으면 1을 반환한다.
	if (y == -1) return 1;
	int ret = 0;
	for (int type = 0; type < 4; type++) {
		//만약 board[y][x]를 type 형태로 덮을 수 있으면 재귀 호출한다.
		if (set(board, y, x, type, 1))
			ret += cover(board);
		//덮었던 블록을 치운다
		set(board, y, x, type, -1);
	}
	return ret;
}
