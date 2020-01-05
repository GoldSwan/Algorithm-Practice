package algorithmStudy;

import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer;

        int[] way1= {1,2,3,4,5};
        int[] way2 = {2,1,2,3,2,4,2,5};
        int[] way3 = {3,3,1,1,2,2,4,4,5,5};

        int[] score = new int[3];

        for(int i = 0; i<answers.length; i++) {
            int way1len = i%way1.length;
            int way2len = i%way2.length;
            int way3len = i%way3.length;

            if(answers[i] == way1[way1len]) 
                score[0]++;
            if(answers[i] == way2[way2len]) 
                score[1]++;
            if(answers[i] == way3[way3len]) 
                score[2]++;

        }

        int win = score[0];
        for(int i = 1; i<score.length; i++) {
            if(win<score[i])
                win = score[i];
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i<score.length; i++) {
            if(win == score[i])
                list.add(i);
        }

        answer = new int[list.size()];
        for(int i = 0; i<list.size(); i++)
            answer[i] = list.get(i)+1;

        return answer;
    }
}