import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ13913_숨바꼭질_4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //수빈이 위치
        int K = Integer.parseInt(st.nextToken()); //동생 위치
        int[] map = new int[100001];
        Arrays.fill(map, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(N);
        map[N] = 0;

        while (!queue.isEmpty()) {
            int X = queue.poll();
            if (X == K) {
                break;
            }

            if (0 <= X - 1 && map[X - 1] == -1) {
                map[X - 1] = X;
                queue.add(X - 1);
            }

            if (X + 1 <= 100000 && map[X + 1] == -1) {
                map[X + 1] = X;
                queue.add(X + 1);
            }

            if (X * 2 <= 100000 && map[X * 2] == -1) {
                map[X * 2] = X;
                queue.add(X * 2);
            }
        }

        int time = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(K);

        while (K != N) {
            time += 1;
            stack.push(map[K]);
            K = map[K];
        }

        StringBuilder sb = new StringBuilder(time + "\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }
}
