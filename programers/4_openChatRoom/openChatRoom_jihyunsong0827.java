import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<String, String>();
		List<String> list = new ArrayList();
		for(int i = 0 ; i < record.length ; i++) {
			String[] split = record[i].split(" ");
            // 나가지 않은 유저들
			if(!"Leave".equals(split[0])){
                //uid, name 적재(Enter, Change시 갱신)
               map.put(split[1], split[2]);
            }
		}
		
		for(int i = 0 ; i < record.length ; i++) {
			String[] split = record[i].split(" ");
			if(split[0].equals("Enter")) {
				list.add(map.get(split[1])+"님이 들어왔습니다.");
			}
			else if(split[0].equals("Leave")) {
				list.add(map.get(split[1])+"님이 나갔습니다.");
			}
		}
		String[] answer = new String[list.size()];
		for(int i = 0 ; i < answer.length; i++) {
			answer[i] = list.get(i);
		}
		return answer;
    }
}