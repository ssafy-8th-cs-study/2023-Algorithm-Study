package week46.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1309_동물원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N+1][2];
        long[] arr = new long[N+1];
        dp[1][0] = 1; // y번째 왼쪽 칸에 사자를 배치할 수 있는 경우의 수
        dp[1][1] = 1; // x번째 오른쪽 칸에 사자를 배치할 수 있는 경우의 수
        arr[1] = 2; // i까지 모든 배치 경우의 수 (사자 배치 안하는 경우 제외)
        int R = 9901;
        for (int i = 2; i < N+1; i++) {
            dp[i][0] = (arr[i-2] + dp[i-1][1] + 1) % R; // i-2번째 까지 모든 경우의 수 + 대각선 방향의 경우의 수 + 1(현재 자리에 놓는 경우의 수)
            dp[i][1] = (arr[i-2] + dp[i-1][0] + 1) % R;
            arr[i] =(dp[i][0] + dp[i][1] + arr[i-1]) % R;
        }
        System.out.println(arr[N]+1);
    }
}
