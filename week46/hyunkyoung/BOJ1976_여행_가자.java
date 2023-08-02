import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1976_여행_가자 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //도시의 수
        int M = Integer.parseInt(br.readLine()); //여행 계획에 속한 도시들의 수

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                int flag = Integer.parseInt(st.nextToken());
                if (flag == 1) union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());
        int root = find(Integer.parseInt(st.nextToken()));
        for (int i = 0; i < M - 1; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (root != find(num)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        return find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[x] = y;
        }
    }
}
