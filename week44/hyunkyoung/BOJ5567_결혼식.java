import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5567_결혼식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //상근이의 동기의 수
        int m = Integer.parseInt(br.readLine()); //리스트의 길이
        boolean[][] friend = new boolean[n + 1][n + 1]; //친구 관계

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friend[a][b] = true;
            friend[b][a] = true;

            if (a == 1 || b == 1) {
                queue.add(a * b);
                visited[a * b] = true;
            }
        }

        int answer = queue.size();
        while (!queue.isEmpty()) {
            int num = queue.poll();

            for (int i = 2; i <= n; i++) {
                if (friend[num][i] && !visited[i]) {
                    answer++;
                    visited[i] = true;
                }
            }
        }

        System.out.println(answer);
    }
}
