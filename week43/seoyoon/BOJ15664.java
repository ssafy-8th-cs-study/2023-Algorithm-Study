package week43.seoyoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* BOJ 15664 - N과 M(10) */
public class BOJ15664 {

    static int N, M;
    static int[] arr, result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        result = new int[M];
        visited = new boolean[N];
        perm(0);
    }

    public static void perm(int cnt) {
        if (cnt == M) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                sb.append(result[i] +" ");
            }
            System.out.println(sb.toString());
            return;
        }

        int temp = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i] || temp == arr[i]) continue;
            if (cnt > 0) {
                if (arr[i] < result[cnt - 1]) continue;
            }
            visited[i] = true;
            result[cnt] = arr[i];
            temp = arr[i];
            perm(cnt + 1);
            visited[i] = false;
        }
    }
}
