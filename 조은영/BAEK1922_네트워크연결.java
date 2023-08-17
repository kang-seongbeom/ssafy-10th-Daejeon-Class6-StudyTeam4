package com.euny0ung.BAEK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BAEK1922 {

	static int N, M;
	static int result;
	static int[][] graph;
	static int[] parent;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		
		graph=new int[M][3];
		
		// 부모의 값을 1부터 넣어주기 위해 0번 인덱스는 사용하지 않음
		parent=new int[N+1]; 
		
		for(int i=0; i<M; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			// a b c값을 입력받음
			for(int j=0; j<3; j++) {
				graph[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		// 컴퓨터(노드)의 수만큼 부모를 만들어줌(?)
		for(int i=1; i<=N; i++) {
				parent[i]=i;
			}
					
		// 1. 가중치를 기준으로 정렬
		Arrays.sort(graph, (o1, o2)->{
				return o1[2]-o2[2];
		});
					
		// 2. 가중치가 낮은 순으로 조회하여 연결된 두 노드의 부모가 같은지 체크
		for(int i=0; i<M; i++) {
			
			// 부모가 다른 경우
			if(find(parent,graph[i][0])!=find(parent, graph[i][1])) {
				// 더 작은 부모 아래로 들어감(?). 그래프 연결에 성공한 것이므로 가중치를 더해준다
				union(parent, graph[i][0], graph[i][1]);
				result+=graph[i][2];
				
			}
		}
		
		System.out.println(result);
	}
	
	static int find(int []parent, int node) {
		if(parent[node]==node) return node;
		return find(parent, parent[node]);
	}
	
	static void union(int []parent, int a, int b) {
		
		int parentA=find(parent, a);
		int parentB=find(parent, b);
		
		// a의 부모가 b의 부모보다 작으면 b의 부모값은 a의 부모값이 됨
		if(parentA<parentB) {
			parent[parentB]=parentA;
		}
		else parent[parentA]=parentB;
	}
}
