package week41.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class BOJ11559 {
    static int R = 12;
    static int C = 6;
    static char[][] map = new char[R][C];
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};

    static class Puyo {
        int y;
        int x;

        public Puyo(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int cnt = 0;
        while (true) {
            ArrayList<Puyo> group = foundGroup();
            if (group.isEmpty()) {
                break;
            }
            cnt++;
            gravity(group);
        }
        System.out.println(cnt);
    }

    private static void gravity(ArrayList<Puyo> group) {
        // 뿌요 제거
        for (Puyo puyo : group) {
            map[puyo.y][puyo.x] = '.';
        }

        // 중력 작용
        for (int i = R-1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != '.' ) {
                    char c = map[i][j];
                    int h = 1;
                    while (true) {
                        if (i+h >= R || map[i+h][j] != '.') {
                            h--;
                            break;
                        }
                        h++;
                    }
                    map[i][j] = '.';
                    map[i+h][j] = c;
                }
            }
        }
    }

    private static ArrayList<Puyo> foundGroup() {
        boolean[][] visited = new boolean[R][C];
        ArrayList<Puyo> totalPuyo = new ArrayList<>();
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if (map[y][x] == '.' || visited[y][x]) continue;
                ArrayList<Puyo> puyos = new ArrayList<>();
                visited[y][x] = true;
                puyos.addAll(dfs(y,x,visited,map[y][x]));
                if (puyos.size() >= 4) {
                    totalPuyo.addAll(puyos);
                }
            }
        }
        return totalPuyo;
    }

    private static ArrayList<Puyo> dfs(int y, int x, boolean[][] visited, char c) {
        ArrayList<Puyo> res = new ArrayList<>();
        res.add(new Puyo(y,x));
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny < 0 || nx < 0 || ny >= R || nx >= C || visited[ny][nx]) continue;
            if (!visited[ny][nx] && map[ny][nx] == c) {
                visited[ny][nx] = true;
                res.addAll(dfs(ny,nx,visited,c));
            }
        }
        return res;
    }
}
