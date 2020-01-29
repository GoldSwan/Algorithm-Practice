/********************************************************************
 * 작성일 : 2020-01-29
 * 작성자 : GoldSwan
 * 문제 : 프로그래머스 - 자물쇠와 열쇠 (lockAndKey)
 * 출저 : programers
 * 풀이 : 완전탐색
 * Big-O  : O(N^4) N<=20 이므로 N이 충분히 작으므로 빠른시간내에 해결 가능
 * TEST 결과 : 모두통과
 *********************************************************************/
class Solution {
	boolean answer;

	public boolean solution(int[][] key, int[][] lock) {

		int M = key.length;
		int N = lock.length;
		int L = N + (M - 1) * 2;
		int[][] expansionLock;
		int[][][] keyCase;// 90도씩 회전하여 얻을 수 있는 모든 Key의 경우의 수

		answer = false;
		keyCase = new int[4][M][M];
		keyCase[0] = key;

		for (int i = 1; i < 4; i++) {
			keyCase[i] = rotateLeft90DegreesToKey(keyCase[i - 1], M);
		}

		expansionLock = new int[L][L];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				expansionLock[i + M - 1][j + M - 1] = lock[i][j];
			}
		}

		loop: for (int i = 0; i < 4; i++) {
			for (int x = 0; x < M - 1 + N; x++) {
				for (int y = 0; y < M - 1 + N; y++) {

					answer = searchKey(keyCase[i], expansionLock, N, M, x, y);

					if (answer)
						break loop;
				}
			}
		}
		return answer;
	}

	public boolean searchKey(int[][] key, int[][] expansionLock, int N, int M, int x, int y) {

		int count = 0;

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				expansionLock[x + i][y + j] = expansionLock[x + i][y + j] + key[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (expansionLock[i + M - 1][j + M - 1] == 1)
					count++;
			}
		}

		if (count == N * N) {
			return true;
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				expansionLock[x + i][y + j] = expansionLock[x + i][y + j] - key[i][j];
			}
		}

		return false;
	}

	public int[][] rotateLeft90DegreesToKey(int[][] beforeKey, int M) {

		// 2차원 배열을 왼쪽으로 90도 회전하는 함수
		int[][] afterkey = new int[M][M];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				afterkey[i][j] = beforeKey[j][(M - 1) - i];
			}
		}
		return afterkey;
	}

}

public class lockAndKey_goldswan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// int[][] key = { { 1, 1, 1 }, { 1, 0, 0 }, { 0, 1, 1 } };
		// int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		int[][] key = { { 1, 1, 1, 1 }, { 1, 1, 1, 0 }, { 1, 1, 0, 1 }, { 1, 1, 1, 0 } };
		int[][] lock = { { 0, 1, 0, 1, 1 }, { 0, 0, 1, 0, 1 }, { 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 1 } };
		Solution sol = new Solution();
		System.out.println(sol.solution(key, lock));
	}

}
