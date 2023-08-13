import java.io.*;
import java.util.*;
class Point {
    public int x, y;
    public Point(int x, int y) {
        this.x=x;
        this.y=y;
    }
}
public class Main {
    public static int[] dx = {0,1,0,-1};
    public static int[] dy = {1,0,-1,0};
    static int n,m;
    static int[][] board, dis;
    static Queue<Point> Q = new LinkedList<>();
    public void BFS() {
        while(!Q.isEmpty()) {
            Point tmp = Q.poll();
            for(int i=0; i<4; i++) {
                int nx = tmp.x+dx[i];
                int ny = tmp.y+dy[i];
                if(nx>=0 && nx<n && ny>=0 && ny<m && board[nx][ny]==0) {
                    board[nx][ny]=1;
                    Q.offer(new Point(nx,ny));
                    dis[nx][ny]=dis[tmp.x][tmp.y]+1; // dis 2차원
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        board = new int[n][m];
        dis = new int[n][m];
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++) {
                board[i][j] = sc.nextInt();
                if(board[i][j]==1) Q.offer(new Point(i, j)); // 출발점이 여러 개이므로 전역 큐에 미리 출발점들을 넣어둔다.
            }
        T.BFS();
        boolean flag=true;
        int answer = Integer.MIN_VALUE;
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                if (board[i][j] == 0) flag = false;
        if(flag) {
            for(int i=0; i<n; i++)
                for(int j=0; j<m; j++) {
                    answer = Math.max(answer, dis[i][j]); // 이미 모든 토마토가 익어있는 상태라면 dis의 최댓값은 0
                }
            System.out.println(answer);
        }
        else System.out.println(-1);
    }
}

