package algorithm.programers;
import java.util.*;

class Solution {
    private int[] result = new int[2];//이모티콘 플러스 서비스 가입수 + 이모티콘 매출액
    private int[] discount_percent_arr = {10, 20 , 30, 40};//가능한 이모티콘 할인율
    private List<Integer> combination_list = new ArrayList<>();

    public int[] solution(int[][] users, int[] emoticons) {
        getResult(users, emoticons, 0);
        return result;
    }

    public int roundUpOnesPlace(int number) {
    double roundedNumber = Math.ceil(number / 10.0) * 10;  // 일의 자리수 올림 처리
    return (int) roundedNumber;
    }

    public void getResult(int[][] users, int[] emoticons, int depth){
        if(depth == emoticons.length){
            int emoticon_plus_cnt = 0;
            int purchase_price = 0;//구매 가격
            int total_purchase_price = 0;
            for(int[] user : users){
                purchase_price = 0;
                int user_max_discount_percent = roundUpOnesPlace(user[0]);//비율
                int user_max_price = user[1];//가격
                for(int i = 0 ; i < emoticons.length ; i++){
                    int discount_percent = discount_percent_arr[combination_list.get(i)];
                    if(user_max_discount_percent <= discount_percent){
                        purchase_price += emoticons[i] * 0.01 * (100 - discount_percent);
                    }
                }
                //이모티콘 플러스 구매
                if(purchase_price >= user_max_price){
                    emoticon_plus_cnt++;
                }
                //이모티콘 구매
                else{
                    total_purchase_price += purchase_price;
                }
            }
            if(emoticon_plus_cnt > result[0]){
                result[0] = emoticon_plus_cnt;
                result[1] = total_purchase_price;
            }
            else if(emoticon_plus_cnt == result[0] && total_purchase_price >= result[1]){
                result[1] = total_purchase_price;
            }
            return;
        }
        for(int i = 0 ; i < discount_percent_arr.length ; i++){
            combination_list.add(i);
            getResult(users, emoticons, depth+1);
            combination_list.remove(combination_list.size()-1);
        }
    }

}

public class EmojiDiscountEvent {
    public static void main(String[] args) throws Exception {
        var solution = new Solution();
        int[][] users = {{40,10000},{25,10000}};
        int[] emoticons = {7000,9000};
        var answer = solution.solution(users, emoticons);
        for(int num : answer){
            System.out.println(num+" ");
        }
    }
}
