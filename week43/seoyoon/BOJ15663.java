package week43.seoyoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* BOJ 15663 - N과 M(9) */
/* [ 계획 ]
 1. 순열을 만든다.
 1-1. 중복을 제거하기 위해 같은 자리의 이전에 나온 수와 같은 수를 기억시킴
*/
public class BOJ15663 {
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
            for (int i = 0; i < M; i++) {
                System.out.print(result[i] +" ");
            }
            System.out.println();
            return;
        }

        int temp = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i] || temp == arr[i]) continue;
            visited[i] = true;
            result[cnt] = arr[i];
            temp = arr[i];
            perm(cnt + 1);
            visited[i] = false;
        }
    }
}
