import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        M = Integer.parseInt(br.readLine());

        int[][] adjMatrix = new int[N][N];
        boolean[] visited = new boolean[N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++){
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[M];
        for(int m=0; m<M; m++){
            arr[m] = Integer.parseInt(st.nextToken())-1;
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(arr[0]);
        visited[arr[0]] = true;

        while(!q.isEmpty()){
            int now = q.poll();

            for(int i=0; i<N; i++){
                if(visited[i]) continue;
                if(adjMatrix[now][i] == 0) continue;

                visited[i] = true;
                q.add(i);
            }
        }

        String answer = "YES";
        for(int i=0; i<M; i++){
            if(!visited[arr[i]]){
                answer = "NO";
                break;
            }
        }
        System.out.println(answer);
    }
}