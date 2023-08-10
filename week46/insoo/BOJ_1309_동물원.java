package week46.insoo;
import java.io.*;
class BOJ_1309_동물원 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		dp[1] = 2;
		
		for (int i = 2; i <= N; i++) {
			dp[i] = ((dp[i-1] * 2) + dp[i-2] + 2) % 9901;
		}
		
		System.out.print(dp[N] + 1);
	}
}