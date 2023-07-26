package week45.seoyoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* BOJ9465 - 스티커 */
public class BOJ9465 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int ans = 0;
            int n = Integer.parseInt(br.readLine());

            int[][] stickers = new int[2][n];
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (n == 1) {
                ans = Math.max(stickers[0][0], stickers[1][0]);
            }
            else if (n == 2) {
                ans = Math.max(stickers[0][0] + stickers[1][1], stickers[1][0] + stickers[0][1]);
            }
            else {
                int[][] memo = new int[2][n];
                memo[0][0] = stickers[0][0];
                memo[1][0] = stickers[1][0];
                memo[0][1] = stickers[1][0] + stickers[0][1];
                memo[1][1] = stickers[0][0] + stickers[1][1];

                for (int i = 2; i < n; i++) {
                    memo[0][i] = Math.max(memo[1][i - 2], memo[1][i - 1]) + stickers[0][i];
                    memo[1][i] = Math.max(memo[0][i - 2], memo[0][i - 1]) + stickers[1][i];
                }
                ans = Math.max(memo[0][n - 1], memo[1][n - 1]);
            }
            System.out.println(ans);
        }
    }
}