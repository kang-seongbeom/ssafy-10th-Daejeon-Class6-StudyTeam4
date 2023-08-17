// 플로이드 와샬 알고리즘은 모든 정점에서 모든 정점으로의 최단거리를 구하는 알고리즘이다.
// 다익스트라 알고리즘이나 벨만 포드 알고리즘은 한 정점에서 다른 모든 정점의 최단거리를 구하는 것과 차이가 있다.
// 플로이드 와샬 알고리즘의 핵심 아이디어는 '거쳐가는 정점'을 기준으로 한다는 것이다.
// 즉, i에서 j까지 가는 것과 i에서 k로 가고, k에서 j로 가는 것은 같다는 의미이다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] graph = new int[n][n];
		
		for(int i=0; i<graph.length; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<graph.length; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// i → k → j
		// k : 거쳐가는 노드 
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) { // i : 출발 노드 
				for(int j=0; j<n; j++) { // j : 도착 노드 
					if(graph[i][k]==1 && graph[k][j]==1) { // 단순히 갈 수 있는 경로가 있는지만 체크
						graph[i][j]=1;
					}
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}
	}
}
