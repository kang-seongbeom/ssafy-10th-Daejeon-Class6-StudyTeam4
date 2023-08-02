package kr.ac.lecture.baekjoon.Num1001_10000;

import java.io.*;
import java.util.*;
public class Num7562 {

    private static final int[] DX = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int[] DY = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        int t = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase <= t; testCase++) {
            int n = Integer.parseInt(br.readLine());

            stz = new StringTokenizer(br.readLine());
            Point start = new Point(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()));
            stz = new StringTokenizer(br.readLine());
            Point end = new Point(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()));

            System.out.println(bfs(n, start, end));
        }

        br.close();
    }

    private static int bfs(int n, Point start, Point end) {
        Queue<Point> qu = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        qu.add(start);
        visited[start.x][start.y] = true;

        while (!qu.isEmpty()){
            Point cn = qu.poll();

            if(cn.equals(end)){
                return cn.depth;
            }

            for(int d=0; d<DX.length; d++){
                int nx = cn.x + DX[d];
                int ny = cn.y + DY[d];

                if(!inRange(n, nx, ny) || visited[nx][ny]) continue;;

                visited[nx][ny] = true;
                qu.add(new Point(nx, ny, cn.depth+1));
            }
        }
        return 0;
    }

    private static boolean inRange(int n, int x, int y) {
        return (x >= 0 && y>=0 && x<n && y<n);
    }

    private static class Point{
        int x, y, depth;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.depth = 0;
        }

        public Point(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof Point){
                // o 객체는 Point라도, Object 참조 변수로 받고 있기 때문에 캐스팅 필요
                return (this.x == ((Point) o).x && this.y == ((Point) o).y);
            }
            return false;
        }
    }
}
