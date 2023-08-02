import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9465_스티커 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine()); //2행 n열
            int[][] map = new int[2][n]; //스티커

            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[2][n];
            dp[0][0] = map[0][0];
            dp[1][0] = map[1][0];

            for (int i = 1; i < n; i++) {
                dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + map[0][i]);
                dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 1] + map[1][i]);
            }

            sb.append(Math.max(dp[0][n - 1], dp[1][n - 1])).append("\n");
        }

        System.out.println(sb);
    }
}
