package week42.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17128 {
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int[] qs = new int[N]; // qs_i: i번째 소부터 4마리의 품질을 곱한 값
        for (int i = 0; i < N; i++) {
            int value = 1;
            for (int j = 0; j < 4; j++) {
                int idx = (i+j>=N) ? (i+j-N) : i+j;
                value *= arr[idx];
            }
            qs[i] = value;
            sum += qs[i];
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int idx = Integer.parseInt(st.nextToken())-1;
            for (int j = 0; j < 4; j++) {
                int ii = idx - j < 0 ? idx-j+N : idx-j;
                sum -= qs[ii];
                qs[ii] *= -1;
                sum += qs[ii];
            }
            sb.append(sum + "\n");
        }
        System.out.println(sb);
    }
}
