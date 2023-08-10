package kr.ac.lecture.baekjoon.Num1001_10000;

import java.io.*;
import java.util.*;

public class Num1753 {

    private static final List<List<Node>> graph = new ArrayList<>();
    private static final int MAX = 987_654_321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(stz.nextToken());
        int e = Integer.parseInt(stz.nextToken());

        int k = Integer.parseInt(br.readLine())-1;

        for(int i=0; i<v; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<e; i++){
            stz = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(stz.nextToken())-1;
            int b = Integer.parseInt(stz.nextToken())-1;
            int w = Integer.parseInt(stz.nextToken());

            graph.get(a).add(new Node(b, w));
        }

        int[] dis = dijkstra(v, k);
        for(int d : dis){
            String value = (d == MAX) ? "INF" : String.valueOf(d);
            bw.write(value + System.lineSeparator());
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] dijkstra(int v, int k) {
        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.add(new Node(k ,0));
        int[] dis = new int[v];
        Arrays.fill(dis, MAX);
        dis[k] = 0;

        while (!qu.isEmpty()){
            Node cn = qu.poll();

            for(int i=0; i < graph.get(cn.v).size(); i++){
                Node nn = graph.get(cn.v).get(i);

                if(dis[nn.v] > dis[cn.v] + nn.w){
                    dis[nn.v] = dis[cn.v] + nn.w;
                    qu.add(new Node(nn.v, dis[nn.v]));
                }
            }
        }
        return dis;
    }

    private static class Node implements Comparable<Node>{
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
