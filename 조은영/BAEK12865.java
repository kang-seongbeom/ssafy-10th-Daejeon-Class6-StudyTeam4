package com.euny0ung.BOJ;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 1. 배열에 숫자를 넣고 첫번째 요소값을 기준으로 두 수의 합이 K보다 작거나 같은 모든 조합 구하기
 * 2. 두번째 요소값을 더하여 출력하기
 * */

public class BAEK12865 {
	
	static int N, K;
	static int weight, value;
	static int [][]bag;
	static int target, max;
	static boolean isSelected[];
	static int totalWeight, totalValue;
	static int maxValue;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		N=sc.nextInt();
		K=sc.nextInt();
		
		bag=new int[N][2];
		isSelected=new boolean[N];
		
		for(int i=0; i<N; i++) {
			weight=sc.nextInt();
			value=sc.nextInt();
			bag[i][0]=weight;
			bag[i][1]=value;
		}
		
		recursive(0);
		System.out.println(maxValue);
		
	}
	
	static void recursive(int idx) {
		
		if(idx==N) {
			totalValue=0;
			totalWeight=0;
			for(int i=0; i<N; i++) {
				// 만약 선택됐으면 
				if(isSelected[i]) {
					// 무게끼리 더했을때 K 이하인 경우
					totalWeight+=bag[i][0];
					if(totalWeight<=K) {
						// 가치를 모두 더해서 max값 갱신
						totalValue+=bag[i][1];
					}
				
				}
			}
			maxValue=Math.max(maxValue, totalValue);
			return;
		}
		isSelected[idx]=true;
		recursive(idx+1);
		isSelected[idx]=false;
		recursive(idx+1);
		
	}

}
