import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
    static ArrayList<Node>[] list;
    private static int V;
    private static int E;
    private static int start;
    private static int[] distance;
    private static int INF = Integer.MAX_VALUE;
    
    private static class Node implements Comparable<Node> {
    	// 사용자 정의 class는 comparable 인터페이스를 구현해야 정렬됨
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken()); //정점개수
        E =Integer.parseInt(st.nextToken()); //간선개수
        
        st=new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()); //시작정점
        
        list = new ArrayList[V + 1]; //정점 인접리스트
        distance = new int[V + 1]; //시작점과 다른 정점간의 최단경로
        
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }
        //초기화
        Arrays.fill(distance, INF);
        distance[start] = 0;
        for (int i = 0; i < E; i++) {
        	st=new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); 
            int v = Integer.parseInt(st.nextToken()); 
            int w = Integer.parseInt(st.nextToken()); 
            // u에 연결된 노드와 가중치 넣기
            list[u].add(new Node(v, w));
        }
        dijkstra();
        for (int i = 1; i <= V; i++) {
            if (distance[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }

    private static void dijkstra() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int vertex = node.vertex;
            int weight = node.weight;
            if (distance[vertex] < weight) { //가중치를 비교해서 더 작을때만 갱신
                continue;
            }
            for (int i = 0; i < list[vertex].size(); i++) {//해당 정점과 연결된 것들 탐색
                int vertex2 = list[vertex].get(i).vertex;
                int weight2 = list[vertex].get(i).weight + weight;
                if (distance[vertex2] > weight2) { //지금께 더 최단경로라면 갱신해준다.
                    distance[vertex2] = weight2;
                    queue.add(new Node(vertex2, weight2));
                }
            }
        }
    }

 
}