package week42.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18430 {

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    static int ans = 0;
    static Dir[] dirs = new Dir[]{
            new Dir(new int[]{0,1},new int[]{-1,0}),
            new Dir(new int[]{0,-1},new int[]{-1,0}),
            new Dir(new int[]{-1,0},new int[]{0,1}),
            new Dir(new int[]{1,0},new int[]{0,1}),
    };

    static class Dir {
        int[] dy;
        int[] dx;

        public Dir(int[] dy, int[] dx) {
            this.dy = dy;
            this.dx = dx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // backtracking
        visited = new boolean[N][M];
        dfs(0,0,0);
        System.out.println(ans);
    }

    private static void dfs(int y, int x, int max) {
            // 백트래킹
            if (x == M) {
                y++;
                x = 0;
            }
            if (y == N) {
                ans = Math.max(max,ans);
                return;
            }

            if (!visited[y][x]) {
                // 4개 부메랑 확인
                for (Dir dir : dirs) {
                    int[] dy = dir.dy;
                    int[] dx = dir.dx;
                    // ㄱ 형태로 자를 수 있는지 체크
                    boolean isBoomerang = true;
                    for (int d = 0; d < 2; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx]) {
                            isBoomerang = false;
                            break;
                        }
                    }
                    if (!isBoomerang) continue;

                    int power = map[y][x] * 2;

                    // 4가지 ㄱ 형태로 자르기
                    visited[y][x] = true;
                    for (int d = 0; d < 2; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        visited[ny][nx] = true;
                        power += map[ny][nx];
                    }
                    dfs(y,x+1,power+max);
                    visited[y][x] = false;
                    for (int d = 0; d < 2; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        visited[ny][nx] = false;
                    }
                }
            }
            dfs(y,x+1,max);
    }
}
