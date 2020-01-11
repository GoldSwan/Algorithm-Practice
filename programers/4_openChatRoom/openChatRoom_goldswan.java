/********************************************************************
작성일 : 2020-01-11
작성자 : GoldSwan
문제 : 프로그래머스 - 오픈채팅방(openChatRoom)
출저 : programers
풀이 : 해시이용
TEST 결과 : 모두통과
*********************************************************************/

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

class Solution {
	private String[] answer = new String[100000];
	private String[] answerTemp;// null 제거용 answerTemp
	private HashMap<String, Vector<Integer>> idIndexMap;// 각 id의 answer index 저장하는 Map
	private HashMap<String, String> saveIdNickNameMap;// 각 id의 nickName 저장하는 Map

	public String[] solution(String[] record) {

		idIndexMap = new HashMap<String, Vector<Integer>>();
		saveIdNickNameMap = new HashMap<String, String>();
		String command;
		StringTokenizer st;
		int index = 0;

		for (int i = 0; i < record.length; i++) {

		    st = new StringTokenizer(record[i], " ");

			// Enter, Leave, Change
			command = st.nextToken();

			if (command.equals("Enter")) {
				Enter(st, index);
				index++;
			} else if (command.equals("Leave")) {
				Leave(st, index);
				index++;
			} else if (command.equals("Change")) {
				Change(st, index);
			}

		}

		// null 제거
		answer = removeNullArray(answer);


		return answer;
	}

	public String[] removeNullArray(String[] answer) {

		int count = 0;

		for (int i = 0; i < answer.length; i++) {

			if (answer[i] == null)
				break;

			count++;
		}

		answerTemp = new String[count];

		for (int i = 0; i < answerTemp.length; i++) {
			answerTemp[i] = answer[i];
		}

		// null 제거한 정답 복사
		answer = answerTemp;

		return answer;
	}

	public void Enter(StringTokenizer st, int index) {

		String id = st.nextToken();// 유저아이디
		String nickName = st.nextToken(); // 닉네임
		String beforeNickName; // 이전 닉네임
		Vector<Integer> idIndexV;//id answer Index Vector

		// 처음 들어오는 유저인 경우
		if (idIndexMap.get(id) == null) {

			idIndexMap.put(id, new Vector<Integer>());

		}
		// 이미 들어온 유저인 경우
		else {
			beforeNickName = saveIdNickNameMap.get(id);
			// 닉네임이 바뀌었을 경우
			if (ChangeNickName(beforeNickName, nickName)) {
				idIndexV = idIndexMap.get(id);

				for (int i = 0; i < idIndexV.size(); i++) {

					if (answer[idIndexV.get(i)].contains("들어왔습니다."))
						answer[idIndexV.get(i)] = nickName + "님이 들어왔습니다.";
					else
						answer[idIndexV.get(i)] = nickName + "님이 나갔습니다.";

				}
			}
		}
		saveIdNickNameMap.put(id, nickName);
		this.answer[index] = nickName + "님이 들어왔습니다.";
		idIndexMap.get(id).add(index);// id에 해당하는 answer index 저장
	}

	public void Leave(StringTokenizer st, int index) {
		String id = st.nextToken();// 유저아이디

		answer[index] = saveIdNickNameMap.get(id) + "님이 나갔습니다.";
		idIndexMap.get(id).add(index);// id에 해당하는 answer index 저장
	}

	public void Change(StringTokenizer st, int index) {

		String id = st.nextToken();// 유저아이디
		String nickName = st.nextToken(); //닉네임
		String beforeNickName = saveIdNickNameMap.get(id);//이전 닉네임
		Vector<Integer> idIndexV = idIndexMap.get(id);//id answer Index Vector
		String beforeStr = "";
		String afterStr = "";

		saveIdNickNameMap.put(id, nickName);

		if (ChangeNickName(beforeNickName, nickName))// 닉네임이 바뀐경우
			for (int i = 0; i < idIndexV.size(); i++) {
				// replace 사용시 주의 : JAVA 문자열은 불변이므로 replace 된 값을 반환받을 문자열을 새로 생성하여 교체해야함.
				beforeStr = answer[idIndexV.get(i)];
				afterStr = beforeStr.replace(beforeNickName, nickName);

				answer[idIndexV.get(i)] = afterStr;
			}
	}

	public boolean ChangeNickName(String beforeNickName, String nickName) {
		if (beforeNickName.equals(nickName))
			return false;

			return true;
	}
}

public class openChatRoom_goldswan {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		String[] record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" };
		String[] answer = sol.solution(record);
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}
}
