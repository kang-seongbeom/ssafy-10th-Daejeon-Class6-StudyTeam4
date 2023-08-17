import java.io.*;
import java.util.*;

class Edge {
    public int vex; // 다음 정점
    public int cost; // 다음 정점으로 가는데 필요한 가중치
    Edge(int vex, int cost) {
        this.vex=vex;
        this.cost=cost;
    }
    // 우선순위 큐를 사용하지 않으므로 노드가 가중치 기준으로 정렬 될 필요 X
}
public class Main {
    static ArrayList<Edge> list[];
    static long[] dist;
    static int n, m;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점의 수
        m = Integer.parseInt(st.nextToken()); // 간선의 수

        list = new ArrayList[n + 1]; // 편의상 0번은 사용하지 않을것이므로 +1
        for (int i = 1; i < list.length; i++) {
            list[i] = new ArrayList<Edge>();
        }

        // 시작 정점부터 각 정점으로 가는 최단거리를 저장할 결과배열 dist 선언 및 초기화
        dist = new long[n + 1]; //input 특성상 0번을 사용하지 않으므로 + 1
        Arrays.fill(dist, INF); // 최솟값을 구해야하므로 MAX값으로 초기화

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[a].add(new Edge(b, w));
        }

//        for(int i=1; i<n+1; i++) { // 입력값 확인
//            for(Edge j : list[i]) {
//                System.out.println(i+" "+j.vex+" "+j.cost);
//            }
//        }


        StringBuilder sb = new StringBuilder();

        // 음의 cycle이 없는 경우
        if (bellmanFord()) {
            for (int i = 2; i <= n; i++) {
                sb.append(dist[i] == INF ? "-1\n" : dist[i] + "\n");
            }
        } else { // 음의 cycle이 있는 경우
            sb.append("-1\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean bellmanFord() {
        dist[1] = 0;

        // 정점의 수-1 만큼 수행
        for (int i = 1; i < n; i++) {

            // 우선순위 큐로 최단 거리를 지니는 정점을 선정 시 음수 사이클의 유무를 판별 불가
            // 음의 사이클이 존재할 경우, 그 사이클 내에 있는 노드들의 최단 거리는 음의 무한대에 수렴하기 때문
            // 효율성을 포기하는 대신 모든 간선을 탐색하면서 최단 경로를 갱신
            // 1에서 출발해서 방문가능한 곳 탐색
            for (int j = 1; j <= n; j++) {
                for (Edge edge : list[j]) {
                    // 아직 해당 지점까지는 도착하지 못했으므로 탐색할 필요 없음.
                    if (dist[j] == Integer.MAX_VALUE) break;

                    if (dist[edge.vex] > dist[j] + edge.cost) {
                        dist[edge.vex] = dist[j] + edge.cost;
                    }
                }
            }
        }

        // 만약 N-1번을 초과해서 순회하는 경우에 갱신이 발생하면, 이 경우에 음수 사이클이 있다고 판별
        // 한번 더 탐색해서 또 값이 바뀌면 음의 사이클이 있다는 뜻
        for (int i = 1; i <= n; i++) {
            for (Edge edge : list[i]) {
                if (dist[i] == Integer.MAX_VALUE) break;
                if (dist[edge.vex] > dist[i] + edge.cost) return false;
            }
        }
        return true;
    }
}
