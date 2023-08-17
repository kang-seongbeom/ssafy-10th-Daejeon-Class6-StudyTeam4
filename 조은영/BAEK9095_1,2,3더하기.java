package com.euny0ung.BAEK;

import java.util.Scanner;

public class BAEK9095 {
	
	static int []dp;
	static int N, input;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		
		dp=new int[11];
		
		dp[1]=1;
		dp[2]=2;
		dp[3]=4;
		
		for(int j=4; j<11; j++) {
				dp[j]=dp[j-1]+dp[j-2]+dp[j-3];
				
		}
		
		for(int i=0; i<N; i++) {
			input=sc.nextInt();
			System.out.println(dp[input]);
			
		}
	}
	
	
}
