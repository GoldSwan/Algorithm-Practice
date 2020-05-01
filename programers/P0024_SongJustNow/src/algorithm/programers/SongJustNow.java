package algorithm.programers;

import java.util.ArrayList;

class Solution {
	public String solution(String m, String[] musicinfos) {
		String answer = "";
		String answerTitle = "(None)";
		int answerPlayMinute = -1;
		int count = 0;
		String[] mSplitArray;
		String[] musicSheetSplitArray;
		ArrayList<String> mArrayList;
		ArrayList<String> musicSheetArrayList;
		ArrayList<String> totalPlayMusicSheetArrayList;

		mSplitArray = m.split("");
		mArrayList = getMArrayList(mSplitArray);

		for (String musicinfo : musicinfos) {

			String[] musicinfoSplitArray = musicinfo.split(",");

			int playMinute = getPlayMinute(musicinfoSplitArray[0], musicinfoSplitArray[1]);

			musicSheetSplitArray = musicinfoSplitArray[3].split("");
			musicSheetArrayList = getMusicSheetArrayList(musicSheetSplitArray);
			totalPlayMusicSheetArrayList = getTotalPlayMusicSheetArrayList(musicSheetArrayList, playMinute);
			count = getCount(totalPlayMusicSheetArrayList, mArrayList);

			if ((mArrayList.size() == count) && (answerPlayMinute < playMinute)) {
				answerTitle = musicinfoSplitArray[2];
				answerPlayMinute = playMinute;
			}
		}

		answer = answerTitle;
		return answer;
	}

	public ArrayList<String> getMArrayList(String[] mSplitArray) {
		ArrayList<String> mArrayList = new ArrayList<String>();

		for (int i = 0; i < mSplitArray.length; i++) {

			mArrayList.add(mSplitArray[i]);

			if (i + 1 < mSplitArray.length && mSplitArray[i + 1].equals("#")) {
				mArrayList.set(mArrayList.size() - 1, mSplitArray[i] + mSplitArray[i + 1]);
				i++;
			}
		}

		return mArrayList;
	}

	public int getPlayMinute(String startTime, String endTime) {

		String[] startTimeSplitArray = startTime.split(":");
		String[] endTimeSplitArray = endTime.split(":");

		int playMinute = 0;
		playMinute = (Integer.parseInt(endTimeSplitArray[0]) - Integer.parseInt(startTimeSplitArray[0])) * 60
				+ (Integer.parseInt(endTimeSplitArray[1]) - Integer.parseInt(startTimeSplitArray[1]));

		return playMinute;
	}

	public ArrayList<String> getMusicSheetArrayList(String[] musicSheetSplitArray) {
		ArrayList<String> musicSheetArrayList = new ArrayList<String>();

		for (int i = 0; i < musicSheetSplitArray.length; i++) {

			musicSheetArrayList.add(musicSheetSplitArray[i]);

			if ((i + 1) < musicSheetSplitArray.length && musicSheetSplitArray[i + 1].equals("#")) {
				musicSheetArrayList.set(musicSheetArrayList.size() - 1,
						musicSheetSplitArray[i] + musicSheetSplitArray[i + 1]);
				i++;
			}
		}

		return musicSheetArrayList;
	}

	public ArrayList<String> getTotalPlayMusicSheetArrayList(ArrayList<String> musicSheetArrayList, int playMinute) {
		ArrayList<String> totalPlayMusicSheetArrayList = new ArrayList<String>();
		int offset = 0;

		for (int i = 0; i < playMinute; i++) {

			if (offset == musicSheetArrayList.size())
				offset = 0;

			totalPlayMusicSheetArrayList.add(musicSheetArrayList.get(offset));
			offset++;
		}

		return totalPlayMusicSheetArrayList;
	}

	public int getCount(ArrayList<String> totalPlayMusicSheetArrayList, ArrayList<String> mArrayList) {
		int count = 0;
		int offset = 0;

		loop: for (int i = 0; i < totalPlayMusicSheetArrayList.size(); i++) {

			count = 0;
			offset = 0;

			for (int j = i; j < totalPlayMusicSheetArrayList.size(); j++) {
				if (count == mArrayList.size())
					break loop;

				if (totalPlayMusicSheetArrayList.get(j).equals(mArrayList.get(offset))) {
					offset++;
					count++;
				} else {
					offset = 0;
					count = 0;
				}
			}

			if (count == mArrayList.size())
				break loop;
		}

		if (count != mArrayList.size())
			count = 0;
		return count;
	}
}

public class SongJustNow {

	public static void main(String[] args) {
		// String m = "ABCDEFG";
		// String m = "CC#BCC#BCC#BCC#B";
		// String m = "ABC";
		String m = "CDCDFG";
		// String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB",
		// "13:00,13:05,WORLD,ABCDEF", "12:00,12:14,HELLO,CDEFGAB",
		// "13:00,13:05,WORLD,ABCDEF"};
		// String[] musicinfos = {"03:00,03:30,FOO,CC#B",
		// "04:00,04:08,BAR,CC#BCC#BCC#B", "03:00,03:30,FOO,CC#B",
		// "04:00,04:08,BAR,CC#BCC#BCC#B"};
		// String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB",
		// "13:00,13:05,WORLD,ABCDEF","12:00,12:14,HELLO,C#DEFGAB",
		// "13:00,13:05,WORLD,ABCDEF"};
		String[] musicinfos = { "13:00,13:10,HELLO,CDCDCDFG" };
		Solution sol = new Solution();
		String answer = sol.solution(m, musicinfos);
		System.out.println("answer:" + answer);
	}
}
