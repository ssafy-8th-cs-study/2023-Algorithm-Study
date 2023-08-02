package week46.seoyoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* BOJ1309 - 동물원 */
public class BOJ1309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N + 1][3];
        dp[1][0] = dp[1][1] = dp[1][2] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901; // 사자 선택 x
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;	// 왼쪽 사자 선택
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901; // 오른쪽 사자 선택
        }
        System.out.println((dp[N][0] + dp[N][1] + dp[N][2]) % 9901);
    }
}