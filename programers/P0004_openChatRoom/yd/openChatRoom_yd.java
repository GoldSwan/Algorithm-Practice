import java.util.HashMap;

public class programmers42888 {
	public static void main(String[] args) {
		String[] record = new String[] {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		solution(record);
	}
	
	public static String[] solution(String[] record) {
		int recordLength = record.length;
		int howManyChangeCommandCalled = 0;
		
		//key : uid, value : final nickname 		
		HashMap<String, String> map = new HashMap<String, String>();
		
		for(int i=0; i<recordLength; i++) {
			//first chunk : command, second chunk: uid, third chunk(optional): nickname 
			String[] chunks = record[i].split(" ");
			
			String command = chunks[0];
			
			if( command.equals("Leave") ) {
				continue;
			}else if( command.equals("Change") ) howManyChangeCommandCalled++;
			
			String uid = chunks[1];
			String nickname = chunks[2];
			
			setNicknameForUser(map, uid, nickname);
		}
		
		int answerLength = recordLength - howManyChangeCommandCalled;
		String[] answer = new String[answerLength];
		
		for(int i=0; i<recordLength; i++) {
			String[] chunks = record[i].split(" ");

			String command = chunks[0];
			if(command.equals("Change")) continue;
			
			String uid = chunks[1];
			answer[i] = getConvertedMessage(map, command, uid);
		}
		
        return answer;
	}
	
	public static void setNicknameForUser(HashMap<String, String> map, String uid, String nickname) {
		map.put(uid, nickname);
	}
	
	public static String getConvertedMessage(HashMap<String, String> map, String command, String uid) {
		switch(command) {
			case "Enter": return map.get(uid)+"님이 들어왔습니다.";
			case "Leave": return map.get(uid)+"님이 나갔습니다.";
			default : return "";
		}
	}
}
