package algorithm.programers;
import java.util.*;

//참고 https://pangtrue.tistory.com/350
class Solution {
    public int solution(int[] arrows) {
        int roomCount = 0;//방의 개수
        int[] dx = { 0,  1, 1, 1, 0, -1, -1, -1};//x축 이동
        int[] dy = { 1, 1, 0, -1, -1,  -1,  0, 1};//y축 이동
        Node curNode = new Node(0, 0);

        // 그래프 인접리스트
        // key = 그래프 정점(Node)의 hashcode, value = 연결된 정점(Node)의 리스트 hashcode
        Map<Node, List<Node>> graph = new HashMap<>();

        for (int arrow : arrows) {
            for (int i = 0; i <= 1; i++) { //교차점 처리를 위해 1칸의 이동을 2칸의 이동으로 한다.
                Node nextNode = new Node(curNode.x + dx[arrow], curNode.y + dy[arrow]);

                // 처음 방문하는 경우 = map에 키값이 없는 경우
                if (!graph.containsKey(nextNode)) {
                    // 리스트에 연결 정점 추가
                    graph.put(nextNode, makeEdgeList(curNode));

                    if (graph.get(curNode) == null) {
                        graph.put(curNode, makeEdgeList(nextNode));
                    } else {
                        graph.get(curNode).add(nextNode);
                    }

                // 해당 정점을 재방문했고, 간선을 처음 통과하는 경우
                } else if (!graph.get(nextNode).contains(curNode)) {
                    graph.get(nextNode).add(curNode);
                    graph.get(curNode).add(nextNode);
                    roomCount++;
                }

                curNode = nextNode;
            }
        }

        return roomCount;
    }

    private List<Node> makeEdgeList(Node node) {
        List<Node> edge = new ArrayList<>();
        edge.add(node);
        return edge;
    }

    private class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}

public class TheNumberOfRooms {
    public static void main(String[] args) throws Exception {
        //int[] arrows = {6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0};
        //int[] arrows = {2,5,2,7};
        int[] arrows = {2,4,6,0};
        var solution = new Solution();
        int answer = solution.solution(arrows);
        System.out.println(answer);
    }
}
