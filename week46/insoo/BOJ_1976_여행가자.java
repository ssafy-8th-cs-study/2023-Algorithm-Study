package week46.insoo;
import java.io.*;
import java.util.*;
class BOJ_1976_여행가자 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		ArrayDeque<Integer>[] adjList = new ArrayDeque[N]; // 인접 리스트
		for (int i = 0; i < adjList.length; adjList[i++] = new ArrayDeque<>());
		
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				if(st.nextToken().equals("1")) adjList[r].add(c);
			}
		}
		
		String answer = "NO";
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()) - 1; // 출발지
		if(M == 1) answer = "YES";
		
		one:
		for (int i = 1; i < M; i++) {
			int target = Integer.parseInt(st.nextToken()) - 1; // 여행 도시 번호
			
			boolean[] visit = new boolean[N];
			visit[start] = true;
			
			ArrayDeque<Integer> q = new ArrayDeque<Integer>();
			q.add(start);
			
			while(!q.isEmpty()) { // BFS
				int now = q.poll();
				
				if(now == target) {
					if(i == M-1) {
						answer = "YES";
					}
					
					start = target;
					continue one;
				}
				
				for(Integer next : adjList[now]) {
					if(!visit[next]) {
						visit[next] = true;
						q.add(next);
					}
				}
			}
            
            break;
		}
		
		System.out.print(answer);
	}
}