package week42.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18808 {
    static int N;
    static int M;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        boolean[][] visited = new boolean[N][M];
        for (int tc = 0; tc < K; tc++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            boolean[][] map = new boolean[r][c];
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < c; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
                }
            }
            Sticker sticker = new Sticker(r, c, map);
            boolean isFit = false;

            while (!isFit) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (sticker.isFit(i, j, visited)) {
                            isFit = true;
                            sticker.setSticker(i, j, visited);
                            break;
                        }
                    }
                    if (isFit) break;
                }

                if (!isFit) {
                    // 스티커 90도 회전
                    sticker.rotate();
                    sticker.cnt++;
                    // 4바퀴 돌리기 끝
                    if (sticker.cnt == 4) {
                        break;
                    }
                }

            }
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    static class Sticker {
        int n;
        int m;
        boolean[][] map;
        int cnt;

        public void rotate() {
            boolean[][] temp = new boolean[m][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    try {

                    temp[j][n - i - 1] = map[i][j];
                    } catch (Exception e) {
                        System.out.println(i+" "+j);
                    }
                }
            }
            int tn = n;
            n = m;
            m = tn;
            map = temp;
        }


        public Sticker(int n, int m, boolean[][] map) {
            this.n = n;
            this.m = m;
            this.map = map;
            this.cnt = 0;
        }

        public boolean isFit(int r, int c, boolean[][] visited) {
            if (n + r > N || m + c > M) return false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] && visited[i + r][j + c]) return false;
                }
            }
            return true;
        }

        public void setSticker(int r, int c, boolean[][] visited) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j]) {
                        visited[i + r][j + c] = true;
                    }
                }
            }
        }

    }
}
