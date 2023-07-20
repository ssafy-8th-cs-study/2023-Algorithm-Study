package week41.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ14620 {
    static int N;
    static int[][] map;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,-1,0,1};
    static boolean[][] visited = new boolean[N][N];
    static int cost = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0,3);
        System.out.println(cost);
    }

    private static void dfs(int total, int cnt) {
        if (cnt == 0) {
            cost = Math.min(cost,total);
            return;
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (!visited[y][x]) {
                    ArrayList<int[]> arr = new ArrayList<>();
                    arr.add(new int[]{y,x});
                    int res = map[y][x];
                    for (int d = 0; d < 4; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        // 꽃을 심을 수 없다.
                        if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx]) {
                            unVisit(arr);
                            res = -1;
                            break;
                        }
                        res += map[ny][nx];
                        visited[ny][nx] = true;
                        arr.add(new int[]{ny,nx});
                    }

                    if (res >= 0) {
                        if (res+total < cost) dfs(res+total, cnt-1);
                        unVisit(arr);
                    }

                }
            }
        }
    }

    private static void unVisit(ArrayList<int[]> arr) {
        for (int[] yx : arr) {
            int yy = yx[0];
            int xx = yx[1];
            visited[yy][xx] = false;
        }
    }
}
