package com.euny0ung.BAEK;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;



public class BAEK7576 {
	
	static Queue<Point>queue=new LinkedList<>();
	
	
	static int N;
	static int M;
	static int zeroCnt;
	static int [][]arr;
	static int nx, ny;
	static int []dx= {0,0,1,-1}; // 상하좌우
	static int []dy= {1,-1,0,0};
	static int cnt;
	static boolean isZero;
	static Point p;
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		M=sc.nextInt();
		N=sc.nextInt();
		arr=new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				arr[i][j]=sc.nextInt();
				if(arr[i][j]==1) {
					queue.add(new Point(i,j));
					
				}
			}
		}
		
		
		
		func();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				cnt=Math.max(cnt, arr[i][j]);
				if(arr[i][j]==0) {
					isZero=true; 
					break;
				}
			}
		}
		
		if(isZero) { // 0이 남아있는 경우
		
			System.out.println(-1);
		}
		else if(zeroCnt==0){ // 0이 처음부터 없는 경우
		
			System.out.println(0);
		}
		else {
		
			System.out.println(cnt-1);
		}
		
	}
	
	static void func() {
		
		while(!queue.isEmpty()) {
			p=queue.poll();
			for(int i=0; i<4; i++) {
				nx=p.x+dx[i];
				ny=p.y+dy[i];
				
				
				if((nx>=0 && nx<N) && (ny>=0 && ny<M)) {
					if(arr[nx][ny]==0) {
						arr[nx][ny]=arr[p.x][p.y]+1;
//						System.out.println(arr[nx][ny]);
						queue.add(new Point(nx,ny));
						zeroCnt++;
						}
					}
					
				}	
		
		}
	}

}
