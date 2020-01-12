package kakao2019_01;

import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        List<String> ansList = new ArrayList<String>();
        Map<String, String> idmap = new HashMap<String, String>();
        
        for (String str : record) {
            StringTokenizer tokenizer = new StringTokenizer(str);
            String cmd = tokenizer.nextToken();
            if (cmd.equals("Enter") || cmd.equals("Change")) {
                String id = tokenizer.nextToken();
                String name = tokenizer.nextToken();
                idmap.put(id, name);
            }
        }
        for(String str : record) {
            StringTokenizer tokenizer = new StringTokenizer(str);
            String cmd = tokenizer.nextToken();
            if(cmd.equals("Enter")) {
                String id = tokenizer.nextToken();
                ansList.add(idmap.get(id)+"´ÔÀÌ µé¾î¿Ô½À´Ï´Ù.");
            } else if(cmd.equals("Leave")) {
                String id = tokenizer.nextToken();
                ansList.add(idmap.get(id)+"´ÔÀÌ ³ª°¬½À´Ï´Ù.");
            } 
        }
        
        String[] answer = new String[ansList.size()];
        ansList.toArray(answer);
        
        return answer;  
    }
}