package week46.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ2146_다리_만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // input
        int N = Integer.parseInt(br.readLine());
        boolean[][] bmap = new boolean[N][N]; // boolean 섬
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (st.nextToken().equals("1")) {
                    bmap[i][j] = true;
                }
            }
        }

        // 섬 번호 붙이기
        int[] dy = {-1,1,0,0};
        int[] dx = {0,0,-1,1};
        int[][] map = new int[N][N]; // 번호가 붙은 섬
        boolean[][] visited = new boolean[N][N];
        int idx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (bmap[i][j] && !visited[i][j]) {
                    visited[i][j] = true;
                    map[i][j] = idx;
                    ArrayDeque<int[]> aq = new ArrayDeque<>();
                    aq.add(new int[]{i,j});
                    while (!aq.isEmpty()) {
                        int[] yx = aq.poll();
                        int y = yx[0];
                        int x = yx[1];
                        for (int d = 0; d < 4; d++) {
                            int ny = y + dy[d];
                            int nx = x + dx[d];
                            if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] || !bmap[ny][nx]) continue;
                            visited[ny][nx] = true;
                            aq.add(new int[]{ny,nx});
                            map[ny][nx] = idx;
                        }
                    }
                idx++;
                }
            }
        }

        // bfs
        int ans  = Integer.MAX_VALUE;
        visited = new boolean[N][N]; // 섬 출발 체크
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    int start = map[i][j]; // 출발한 섬
                    visited[i][j] = true;
                    boolean[][] tVisit = new boolean[N][N]; // 경로 방문 체크
                    tVisit[i][j] = true;
                    ArrayDeque<int[]> aq = new ArrayDeque<>();
                    aq.add(new int[]{i,j});
                    int dist = 0;
                    boolean isReach = false;
                    while (!aq.isEmpty()) {
                        int size = aq.size();
                        for (int s = 0; s < size; s++) {
                            int[] yx = aq.poll();
                            int y = yx[0];
                            int x = yx[1];
                            for (int d = 0; d < 4; d++) {
                                int ny = y + dy[d];
                                int nx = x + dx[d];
                                if (ny < 0 || nx < 0 || ny >= N || nx >= N || tVisit[ny][nx] || map[ny][nx] < 0) continue;
                                if (map[ny][nx] > 0) {
                                    if (map[ny][nx] != start) {
                                        isReach = true;
                                        ans = Math.min(ans,dist);
                                        break;
                                    } else {
                                        continue;
                                    }
                                }
                                tVisit[ny][nx] = true;
                                aq.add(new int[]{ny,nx});
                            }
                            if (isReach) break;
                        }
                        if (isReach) break;
                        dist++;
                        if (dist > ans) break; // 가지치기
                    }
                }

            }
        }
        System.out.println(ans);
    }
}
