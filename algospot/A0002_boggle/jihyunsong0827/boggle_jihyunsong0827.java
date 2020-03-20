package wk2;
import java.util.Scanner;

//boggleGame
public class Main {

	static char [][] board = new char[5][5];
	static String totalWord;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// 테스트케이스 수 및 게임판 글자입력
		int caseCnt = Integer.parseInt(sc.nextLine());

		for(int i = 0; i < caseCnt; i++){

			totalWord = "";

			for(int j = 0; j < 5; j++){
				String inStr = sc.nextLine();
				for(int k = 0; k < 5; k++){
					board[j][k] = inStr.charAt(k);
				}
				totalWord += inStr;
			}

			// 테스트 단어수 및 단어 입력
			int wordCnt = Integer.parseInt(sc.nextLine());

			for(int a = 0; a < wordCnt; a++){
				String word = sc.nextLine();

				boolean isMatchable = true;

				for(int j = 0; j < word.length(); j++){
					// 배열판에 입력한 단어의 글자가 하나라도 없으면 제외
					if(totalWord.indexOf(word.charAt(j)) < 0){
						isMatchable = false;
						break;
					}
				}

				if(isMatchable){
					isMatchable = findWord(word);
				}
				System.out.println(word +" "+(isMatchable? "YES" : "NO"));
			}

		}

	}

	static boolean findWord(String word){

		boolean isMatchable = false;
		char alpa = word.charAt(0);

		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				if(board[i][j] == alpa){

					if(word.length() == 1){
						return true;
					}

					isMatchable = findLetters(word.substring(1), i, j);

					if(isMatchable){
						return true;
					}
				}
			}
		}

		return isMatchable;

	}

	static boolean findLetters(String word, int endX, int endY){
		boolean isMatchable = false;
		char alpa = word.charAt(0);

		int startX = 0;
		int startY = 0;

		if(endX > 0){
			startX = endX - 1;
		}

		if(endY > 0){
			startY = endY - 1;
		}

		System.out.println(" HHHHHHHHHHHHH " + word + ":" + endX + "," + endY);
		for(int x = startX; x <= endX+1 && x < 5; x++){
			for(int y = startY; y <= endY+1 && y < 5; y++){
				
				System.out.println("* " +  x + "," + y);

				if(x == endX && y == endY){
					continue;
				}

				if(board[x][y] == alpa){
					if(word.length() == 1){
						return true;
					}

					isMatchable = findLetters(word.substring(1), x, y);
					if(isMatchable){
						return true;
					}
				}
			}
		}

		return isMatchable;
	}

}
