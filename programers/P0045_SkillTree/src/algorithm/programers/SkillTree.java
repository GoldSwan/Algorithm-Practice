package algorithm.programers;

import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int canSkillCnt = 0;
        
        String[] skillSplitArray = skill.split("");
        Map<String,Integer> skillMap = new HashMap<>();
        int skillOffset = 0;
        
        for(String strSkill : skillSplitArray){
            skillMap.put(strSkill,0);
        }
        
        for(String skill_tree : skill_trees){
            String[] skill_tree_split_array = skill_tree.split("");
            boolean isCanSkill = true;
            
            for(String strSkill : skill_tree_split_array){           
                if(skillOffset<skillSplitArray.length && skillMap.containsKey(strSkill)){
                	isCanSkill = strSkill.equals(skillSplitArray[skillOffset++]) ? true : false;
                	
                	if(!isCanSkill)
                		break;
                }
            }
            
            if(isCanSkill)
                ++canSkillCnt;
            
            skillOffset = 0;
        }
        
        return canSkillCnt;
    }
}

public class SkillTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		String skill = "CBD";
		String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

		int answer = sol.solution(skill, skill_trees);
		System.out.println("answer:"+answer);
	}

}
