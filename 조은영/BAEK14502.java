package com.euny0ung.BAEK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEK14502 {

	static int N,M;
	static int[][] arr;
	static ArrayList<int[]> Empty;
	static ArrayList<int[]> Virus;
	static ArrayList<int[][]> numberOfCase=new ArrayList<>();
	static int []combi;
	static int []picked;
	static int cnt, start; // 조합에 사용될 변수
	static int max;
	
	static int []dx= {-1,1,0,0};
	static int []dy= {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		arr=new int[N][M];
		
		Empty=new ArrayList<>();
		Virus=new ArrayList<>();
		
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
				// 바이러스, 0의 위치 따로 저장해두기
				if(arr[i][j]==0) {
					Empty.add(new int[] {i,j});
					
				}
				if(arr[i][j]==2) {
					Virus.add(new int[] {i,j});
				}
			}
		}
		
		// 조합에 사용될 배열
		combi=new int[Empty.size()];
		picked=new int[3];
		
		for(int i=0; i<Empty.size(); i++) {
			combi[i]=i;
		}
		
		// 조합 구하기
		comb(cnt, start);
		
		for(int [][]walls : numberOfCase) {
			int tmp=bfs(walls);
			max=Math.max(max, tmp);
		
		}
		
		System.out.println(max);
		
	}
	
	static void comb(int cnt, int start) {
		if(cnt==3) {
			// 배열에 저장하고 0,1,2로 접근해서 꺼내 쓰기
			int []empty0=Empty.get(picked[0]);
			int []empty1=Empty.get(picked[1]);
			int []empty2=Empty.get(picked[2]);
			
			// 여기서 값이 제대로 안들어옴
			//System.out.println(empty0[0]+" "+empty0[1]+" "+empty1[0]+" "+empty1[1]+" "+empty2[0]+" "+empty2[1]);
			numberOfCase.add(new int[][] {{empty0[0], empty0[1]}, {empty1[0], empty1[1]}, {empty2[0], empty2[1]}});
			return;
		}
		for(int i=start; i<combi.length; i++) {
			picked[cnt]=combi[i];
			comb(cnt+1, i+1);
		}
	}
	
	static int bfs(int [][]walls) {
		
		Queue<int[]> que=new LinkedList<>();
		
		for(int []xy : Virus) {
			que.add(xy);
		}
		
		// 값이 변화되므로 원본 배열을 복사한 임시 배열을 만들어줌
		int [][]map=arr;
		
		// 세개의 wall 설치
		for(int []xy : walls) {
			int x=xy[0];
			int y=xy[1];
			map[x][y]=1;
		}
		
		// 탐색
		while(que.size()>0) {
			
			int[]xy=que.poll();
			int y=xy[1];
			int x=xy[0];
			
			//4방 탐색
			for(int i=0; i<4; i++) {
				int nx=x+dx[i];
				int ny=y+dy[i];
			
				if(nx>=0 && nx<N && ny>=0 && ny<M) {
					if(map[nx][ny]==0) {
						map[nx][ny]=2;
						que.add(new int[] {nx, ny});
					}
					
				}
			
			}
		}
		
		int safeZone=0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) safeZone++;
			}
		}
		
		return safeZone;
	}
	
	

}

// 시간복잡도 : 64칸 중에 바이러스가 2개, 0이 3개 -> 59칸중에 세개의 벽을 세울 칸을 구하는 경우의 수 (32509)* bfs
// 일반화 : 0 개수중 3개 뽑기. 조합으로 모든 경우의 수 구해서 bfs 돌리자