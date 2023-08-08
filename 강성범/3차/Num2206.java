package kr.ac.lecture.baekjoon.Num1001_10000;

import java.io.*;
import java.util.*;

public class Num2206 {

    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};

    private static int n, m;
    private static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stz.nextToken());
        m = Integer.parseInt(stz.nextToken());
        map = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = (input.charAt(j) == '1');
            }
        }

        bw.write(String.valueOf(bfs()));

        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs() {
        Queue<Point> qu = new ArrayDeque<>();
        qu.add(new Point(0, 0, 1, false));
        boolean[][][] visited = new boolean[n][m][2];
        visited[0][0][0] = true;

        while (!qu.isEmpty()){
            Point cn = qu.poll();

            if(cn.x == n-1 && cn.y == m-1){
                return cn.depth;
            }

            for(int d = 0; d<DX.length; d++){
                int nx = cn.x + DX[d];
                int ny = cn.y + DY[d];

                // 벽을 부셨으면 1, 부시지 않았으면 0으로 방문 체크
                int whereVisitedCheck = (cn.isBreakWall) ? 1 : 0;

                if(!inRange(nx, ny) || visited[nx][ny][whereVisitedCheck]) continue;

                if(cn.isBreakWall){ // 벽을 부신 상태라면
                    if(!map[nx][ny]) { // 벽은 하나만 부실수 있기 때문에 벽이 아니어야 함
                        visited[nx][ny][1] = true;
                        qu.add(new Point(nx, ny, cn.depth + 1, true));
                    }
                }else{ // 아직까지 벽을 부시지 않았다면
                    visited[nx][ny][0] = true;
                    if(map[nx][ny]){ // 벽이라면 부심
                        qu.add(new Point(nx, ny, cn.depth + 1, true));
                    }else{ // 벽이 아니라면 이동
                        qu.add(new Point(nx, ny, cn.depth + 1, false));
                    }
                }
            }
        }
        return -1;
    }

    private static boolean inRange(int x, int y) {
        return (x>=0 && y>=0 && x<n && y<m);
    }

    private static class Point{
        int x, y, depth;
        boolean isBreakWall;

        public Point(int x, int y, int depth, boolean isBreakWall) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.isBreakWall = isBreakWall;
        }
    }
}
