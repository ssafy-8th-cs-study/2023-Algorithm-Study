package week44.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2448 {
    static char[][] map;
    static int N;
    static int width;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        width = 2*N-1;
        int x = width/2;
        map = new char[N][width];
        dfs(0,x,1,'M');
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static void dfs(int y, int x, int cnt, char dir) {
        System.out.println(y);
        map[y][x] = '*';
        map[y+1][x-1] = '*';
        map[y+1][x+1] = '*';
        for (int i = 0; i < 5; i++) {
            map[y+2][x-2+i] = '*';
        }
        for (int i = 0; i <= 10; i++) {
            if (y+3 == 3*Math.pow(2,i)) {
                dfs(y+3,x-3);
            }
        }
        if (y+3 == N) return;

    }
}
