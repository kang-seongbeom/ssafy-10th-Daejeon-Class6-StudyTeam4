import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 *
 * 가중치 개념이 없는 단순 최소경로문제   ->   BFS
 * 가중치 개념이 있는 한 노드기준의 최소경로값   ->   Dijkstra (다익스트라)
 * 가중치 개념이 있는 모든 노드기준의 최소경로값   -> Floyd Warshall(플로이드 와샬)
 *
 * 문제 접근 :
 * 1과 5가 이어진 걸로 보지 않고 INF값이 나온 것을 통해 단방향성 연결
 * 모든 노드별로 최단거리를 구하는 것이 아닌 시작노드를 기준으로 다른 모든 노드들 간의 최단 경로 구하기
 * 따라서 시작점을 기준으로 가중치값을 더해가면서 최단 경로 구하기
 * 우선순위 큐에서 노드가 가중치 기준으로 정렬되기 위해서는 Comparable 상속 후 compareTo 메소드 구현 필요
 *
 */
class Edge implements Comparable<Edge> {
    public int vex; // 정점
    public int cost;  // 비용=가중치값
    Edge(int vex, int cost) {
        this.vex=vex;
        this.cost=cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost-o.cost;
    }
}
public class Main {
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Edge>[] list;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
//        int[] in = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        int dot = in[0], line = in[1];
//        int start = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken()); // 정점의 개수
        int e = Integer.parseInt(st.nextToken()); // 간선의 개수
        int s = Integer.parseInt(br.readLine()); // 시작 정점

        list = new ArrayList[v+1]; // 편의상 0번은 사용하지 않을것이므로 +1
        for(int i=1; i<list.length; i++) {
            list[i] = new ArrayList<Edge>();
        }
        for(int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 노드1
            int b = Integer.parseInt(st.nextToken()); // 노드2
            int w = Integer.parseInt(st.nextToken()); // 가중치
            list[a].add(new Edge(b,w));
        }

        // 시작 정점부터 각 정점으로 가는 최단거리를 저장할 결과배열 dist 선언 및 초기화
        dist = new int[v+1]; //input 특성상 0번을 사용하지 않으므로 + 1
        Arrays.fill(dist, Integer.MAX_VALUE); // 최솟값을 구해야하므로 MAX값으로 초기화
        dist[s] = 0; // 시작점 거리값

        // 다익스트라로 start에서 연결된 모른 경로값을 탐색
        dijkstra(s);

        // 결과 출력문 (0번 제외)
        for(int i=1;i<dist.length;i++) {
            if(dist[i]== Integer.MAX_VALUE) sb.append("INF").append("\n");
            else sb.append(dist[i]).append("\n");
        }
        System.out.println(sb);
    }
    private static void dijkstra(int s) {
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(); // //pq를 사용하면 최소값 기준으로 들어가기 때문에 연산이 줄어든다
        pq.add(new Edge(s,0)); // 탐색 시작점
        while(!pq.isEmpty()) {
            Edge tmp = pq.poll(); // 탐색할 정점 now
            int now = tmp.vex;
            int nowCost = tmp.cost;
            if(nowCost>dist[now]) continue; // 시간 단축

            // 탐색할 점에 연결된 정보기반으로 dist 갱신
            for(Edge next : list[tmp.vex]) {

                // 다음 dist배열 내의 가중치 최소값 vs 탐색할 정점으로의 가중치 + 탐색할 정점에서 다음 정점으로의 가중치
                if(dist[next.vex]>tmp.cost+next.cost) { // dist[next] > dist[tmp] + (tmp에서 next까지의 가중치)
                    dist[next.vex]=tmp.cost+next.cost; // 더 짧은거리가 있다면 dist를 최단거리로 갱신
                    pq.add(new Edge(next.vex, dist[next.vex]));
                }
            }
        }
    }
}

