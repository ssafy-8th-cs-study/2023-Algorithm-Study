package week45.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9465_스티커 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] cost = new int[2][N];
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cost[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[][] dp = new int[2][N];
            dp[0][0] = cost[0][0];
            dp[1][0] = cost[1][0];
            if (N >= 2) {
                dp[0][1] = cost[0][1] + cost[1][0];
                dp[1][1] = cost[0][0] + cost[1][1];
            }

            for (int i = 2; i < N; i++) {
                dp[0][i] = cost[0][i] + Math.max(dp[1][i-1], dp[1][i-2]);
                dp[1][i] = cost[1][i] + Math.max(dp[0][i-1], dp[0][i-2]);
            }
            sb.append(Math.max(dp[0][N-1], dp[1][N-1])).append("\n");
        }
        System.out.println(sb);
    }

}
