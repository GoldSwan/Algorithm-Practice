import java.util.HashMap;

public class programmers60060 {
	static HashMap mapByWordLength = new HashMap();
	static HashMap reverseMapByWordLength = new HashMap();
	static class Node{
		HashMap map;
		int count;
		
		Node(){
			this.map = new HashMap();
			this.count = 0;
		}
	}
	
	public static void main(String[] args) {
		solution(new String[]{"frodo", "frodn", "frod", "f", "i", "k", "o"}, new String[]{"?rodo", "frodo", "??odo", "???o", "?????", "??od"});

	}
	public static int[] solution(String[] words, String[] queries) {
		
		//save words to maps
		for(String word : words) {
			int wordLength = word.length();
			
			saveWord( word, getMapByWordLength(wordLength), 0);
			saveWordReverse( word, getReverseMapByWordLength(wordLength), wordLength-1 );
		}
		

		int[] answer = new int[queries.length];
		
		//get answers
		for(int i=0; i<queries.length; i++){
			String query = queries[i];
			int queryLength = query.length();
			
			if(query.charAt(0) == '?') {
				String reversedQuery = reverseQuery(query);
				if( reverseMapByWordLength.containsKey(queryLength) )
					answer[i] = getCount( reversedQuery, (Node) reverseMapByWordLength.get(queryLength), 0 );
				else
					answer[i] = 0;
			}else {
				if( mapByWordLength.containsKey(queryLength) )
					answer[i] = getCount( query, (Node) mapByWordLength.get(queryLength), 0 );
				else
					answer[i] = 0;
			}
		
		}
		for(int el:answer)
			System.out.println(el);
        return answer;
    }
	
	public static Node getMapByWordLength(int wordLength) {
		if(!mapByWordLength.containsKey(wordLength))
			mapByWordLength.put(wordLength, new Node());
		
		Node node = (Node) mapByWordLength.get(wordLength);
		node.count++;
	
		return node; 
	}
	public static Node getReverseMapByWordLength(int wordLength) {
		if(!reverseMapByWordLength.containsKey(wordLength))
			reverseMapByWordLength.put(wordLength, new Node());
		
		Node node = (Node) reverseMapByWordLength.get(wordLength);
		node.count++;
	
		return node; 
	}
	public static void saveWord(String word, Node node, int index) {
		if( word.length()-1<index ) return;
		
		char charToSave = word.charAt(index);
		
		if( node.map.containsKey(charToSave) ) {
			Node updatedNode = (Node) node.map.get(charToSave);
			updatedNode.count++;
			saveWord( word, updatedNode, ++index);
		}else {
			node.map.put( charToSave, new Node() );
			Node updatedNode = (Node) node.map.get(charToSave);
			updatedNode.count++;
			saveWord( word, updatedNode, ++index);
		}
	}
	public static void saveWordReverse(String word, Node node, int index) {
		if( index<0 ) return;

		char charToSave = word.charAt(index);
		
		if( node.map.containsKey(charToSave) ) {
			Node updatedNode = (Node) node.map.get(charToSave);
			updatedNode.count++;
			saveWordReverse( word, updatedNode, --index);
		}else {
			node.map.put( charToSave, new Node() );
			Node updatedNode = (Node) node.map.get(charToSave);
			updatedNode.count++;
			saveWordReverse( word, updatedNode, --index);
		}
	}
	public static int getCount(String query, Node node, int index) {
		//index = 0 ~ query.length-1
		if( index == query.length() || query.charAt(index)=='?')
			return node.count;
		
		if( node.map.containsKey(query.charAt(index)) )
			return getCount( query, (Node)node.map.get(query.charAt(index)), ++index);
		else
			return 0;
	}
	public static String reverseQuery(String query) {
	    char[] in = query.toCharArray();
	    int begin=0;
	    int end=in.length-1;
	    char temp;
	    while(end>begin){
	        temp = in[begin];
	        in[begin]=in[end];
	        in[end] = temp;
	        end--;
	        begin++;
	    }
	    return new String(in);
	}
}
