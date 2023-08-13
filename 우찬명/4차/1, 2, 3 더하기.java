// dp[n] = dp[n - 1] + dp[n - 2] + dp[n - 3]
// dp[n]를 1,2,3의 합으로 나타내는 방법은 = 원래 dp[n - 3]에 +3을 한 경우 + 원래 dp[n - 2]에 +2를 한 경우 + 원래 dp[n - 1]에 +1을 한 경우와 일치한다.       

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[] dp = new int[11];

			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;

			for (int i = 4; i <= n; i++) {
				dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
			}

			bw.write(dp[n] + "\n");
		}
		bw.flush();
	}
}
