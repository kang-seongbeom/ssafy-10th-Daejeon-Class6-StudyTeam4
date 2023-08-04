import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] grid;
    static boolean visited[][];
    static int dx[] = {-1,0,0,1};
    static int dy[] = {0,1,-1,0};
    public void DFS(int x, int y) {
        visited[x][y] = true; // 해당 grid 영역 방문확인
        char tmpChar = grid[x][y]; // 해당 grid 문자 임시저장
        for(int i=0; i<4; i++) { // 4방 탐색
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(0<=nx && nx<N && 0<=ny && ny<N) { // 경계값 체크
                // 4방탐색부분이 방문 안했었고 탐색전 문자와 같은 문자라면
                // 기저부분 : 4방탐색의 모든 부분이 탐색전 문자와 다른 문자인 경우 (DFS 종료)
                if(!visited[nx][ny] && grid[nx][ny]==tmpChar) { 
                    DFS(nx,ny);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); 
        sc.nextLine(); 
        grid = new char[N][N];
        visited = new boolean[N][N];
        for(int i=0; i<N; i++) {
            grid[i]=sc.nextLine().toCharArray();
        }
        Main M = new Main();

        // 정상인 경우
        int cntYg=0; // 초록색 보임
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    M.DFS(i,j);
                    cntYg++;
                }
            }
        }

        // 적록색약인 경우
        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j]=false; // 재초기화
                if (grid[i][j]=='G') {
                    grid[i][j]='R';
                }
            }
        }

        int cntNg=0; // 초록색 안보임
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    M.DFS(i,j);
                    cntNg++;
                }
            }
        }

        System.out.println(cntYg+" "+cntNg);
    }
}
