import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int r,c,cnt;
    public Point(int x, int y, int cnt) {
        this.r=x;
        this.c=y;
        this.cnt=cnt;
    }
    @Override
    public String toString() {
        return "Point [r=" + r + ", c=" + c + ", cnt=" + cnt + "]";
    }
}
public class Main {
    static int n,m;
    static boolean[][] visited;
    static int[][] maze;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    private static void BFS(Point p) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(p);

        while(!queue.isEmpty()) {
            Point now = queue.poll();

            //  System.out.println(now);
            // 마지막 정점에 도달했을 때 반복문 종료
            if(now.r==n-1 && now.c==m-1) {
                System.out.println(now.cnt);
                return;
                // System.exit(0);
            }

            // 정점 하나에 대한 탐색
            for(int k=0; k<4; k++) {
                int nr = now.r + dr[k];
                int nc = now.c + dc[k];

                // if(inRange(nr,nc) && maze[nr][nc]==1) <<< 4방탐색이 시작이 상우하좌인데
                // 시작점에서 상을 탐색 시 padding도 안 주었기 때문에 값이 없어서 시작 자체가 불가능했다
                if(inRange(nr,nc)) {
                    //cnt++; // cnt를 갱신해주어야지 단순 증가시키는 것은 코드 오류
                    // 모든 지점의 cnt를 관리필요 -> 큐에 같이 넣기(BFS)
                    if(visited[nr][nc] || maze[nr][nc]==0) continue;
                    visited[nr][nc]=true;
                    queue.offer(new Point(nr,nc,now.cnt+1));
                }
            }
        }
    }
    private static boolean inRange(int nx, int ny) {
        return 0<=nx && nx<n && 0<=ny && ny<m;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maze = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i<n; i++) {
            String line = br.readLine();
            for(int j=0; j<m; j++) {
                maze[i][j] = line.charAt(j);
            }
        }

        visited[0][0]=true;
        BFS(new Point(0,0,1));
    }
}
