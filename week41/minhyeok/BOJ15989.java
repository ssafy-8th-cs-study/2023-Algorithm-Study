package week41.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[10001][4];
            // a_i,j: i를 j까지 사용해 만들 수 있는 모든 경우, 단 중복 X
            arr[1][1] = 1; // 1
            arr[1][2] = 0;
            arr[1][3] = 0;

            arr[2][1] = 1; // 1 + 1
            arr[2][2] = 1; // 2
            arr[2][3] = 0;

            arr[3][1] = 1; // 1+1+1
            arr[3][2] = 1; // 1+2
            arr[3][3] = 1; // 3

            for (int i = 4; i < N+1; i++) {
                arr[i][1] = 1;
                arr[i][2] = arr[i-2][1] + arr[i-2][2];
                arr[i][3] = arr[i-3][1] + arr[i-3][2] + arr[i-3][3];
            }
            System.out.println(arr[N][1] + arr[N][2] + arr[N][3]);
        }
    }
}
