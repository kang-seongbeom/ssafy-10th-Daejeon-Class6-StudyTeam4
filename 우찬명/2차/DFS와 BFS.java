import java.io.*;
import java.util.*;

public class Main {
    static int n, m, v;
    static int[][] graph;
    static boolean[] check; // 거의 필수
    public static void DFS(int node) {
        check[node] = true;
        System.out.print(node+" ");
        for(int i=1; i<=n; i++) {
            if(graph[node][i]==1 && !check[i]) DFS(i);
        }
    }

    public static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        check[node]=true;
        queue.offer(node);
        while(!queue.isEmpty()) {
            int p = queue.poll();
            System.out.print(p+" ");
            for(int i=1; i<=n; i++) {
                if(graph[p][i]==1 && !check[i]) {
                    check[i]=true;
                    queue.offer(i);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        v = sc.nextInt();
        graph = new int[n+1][n+1];
        check = new boolean[n+1];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a][b] = 1;
            graph[b][a] = 1;
        }
        DFS(v);
        for(int i=1; i<=n; i++)
            check[i]=false;
        System.out.println();
        BFS(v);
    }
}
