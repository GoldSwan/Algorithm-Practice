import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Stack;

class algospot_PICNIC {
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		//T = testcase
		String input = bf.readLine().trim();
		int T = Integer.parseInt(input);

		
		for(int i=0; i<T; i++) {
			String[] chunks = bf.readLine().split(" ");
			int numOfChildren = Integer.parseInt( chunks[0] );
			int numOfRelationInformation = Integer.parseInt( chunks[1] );
			
			boolean[][] relationMap = new boolean[numOfChildren][numOfChildren];
		
			
			String[] relationInformation = bf.readLine().split(" ");
			
			//initiate relationMap
			for(int j=0; j<numOfRelationInformation; j++) {
				int child1 = Integer.parseInt( relationInformation[2*j] );
				int child2 = Integer.parseInt( relationInformation[2*j+1] );
				
				recordRelation(relationMap, child1, child2);
			}
			

			int result=0;
			
			HashSet<Integer> childrenToBePaired = new HashSet<Integer>();
			for(int m=0; m<numOfChildren; m++)
				childrenToBePaired.add(m);
			
			//CurrentState contains hasPartner and currentLocation
			Stack<State> stateStack = new Stack<State>();
			stateStack.add( new State(childrenToBePaired, new int[]{0, 0}) );
			
			while( !stateStack.isEmpty() ) {
				State currentState = stateStack.pop();
				boolean flag = false;
				
				for(int child1=currentState.currentLocation[0]; child1<numOfChildren; child1++) {
					if ( currentState.hasPartner(child1) ) continue;
					
					if(flag) {currentState.currentLocation[1]=0;}
					flag=true;
					
					for(int child2=currentState.currentLocation[1]; child2<numOfChildren; child2++) {
						if(child1 >= child2) continue;
						if( theyAreFriends(relationMap, child1, child2)&&!(currentState.hasPartner(child2)) ) {
							
							//pair child1 and child2
							HashSet<Integer> clone = (HashSet<Integer>) currentState.childrenToBePaired.clone();
							stateStack.push( new State( clone, new int[] {child1, child2+1} ) );
							currentState.childrenToBePaired.remove(child1);
							currentState.childrenToBePaired.remove(child2);

							if( currentState.childrenToBePaired.isEmpty() ) result++;
							
							break;
						}
					}
					
				}
			}
			
			System.out.println(result);
		}
	}
	public static void recordRelation(boolean[][] relationMap, int child1, int child2) {
		relationMap[child1][child2] = true;
		relationMap[child2][child1] = true;
	}
	public static boolean theyAreFriends(boolean[][] relationMap, int child1, int child2) {
		if(child1 == child2) return false;
		else if(relationMap[child1][child2]) return true;
		else return false;
	}
	
	static class State {
		HashSet<Integer> childrenToBePaired;
		int[] currentLocation;
		
		State(HashSet<Integer> childrenToBePaired, int[] currentLocation) {
			this.childrenToBePaired = childrenToBePaired;
			this.currentLocation = currentLocation;
		}
		
		boolean hasPartner(int child) {
			return !this.childrenToBePaired.contains(child);
		}
	}
	
}