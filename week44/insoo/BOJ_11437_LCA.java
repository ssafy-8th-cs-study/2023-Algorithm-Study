import java.io.*;
import java.util.*;
/*
 * 1. 인접 리스트 연결 ArrayDeque<Integer>[] qList
 * 2. 직속 조상과 10번째 조상 찾기 int[][] parentArr
 * 3. 1부터 각 정점의 깊이 구하기 int[] depthArrs
 * 4. A와 B 정점의 깊이 비교
 * 5. 더 깊은 것은 조상으로 교체
 * 6. 같은 조상을 찾을 때까지 4와 5를 반복
 */
class BOJ_11437_LCA {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		ArrayDeque<Integer>[] qList = new ArrayDeque[N+1]; // 인접 리스트
		for (int i = 0; i < qList.length; qList[i++] = new ArrayDeque<>());
		
		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			qList[A].add(B); // 양방향 연결
			qList[B].add(A);
		}
		
		int[][] parentArr = new int[N+1][10]; // 0 직속 조상, 1 n번째 조상 기록 배열
		int[] depthArr = new int[N+1]; // 깊이 기록 배열
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {1, 1});
		
		boolean[] visit = new boolean[N+1];
		visit[1] = true;
		
		while(!q.isEmpty()) {
			int[] e = q.poll();
			int now = e[0];
			int cnt = e[1];
			
			depthArr[now] = cnt; // 깊이 구하기 BFS
			
			for(int next : qList[now]) {
				if(!visit[next]) {
					visit[next] = true;
					q.add(new int[] {next, cnt+1});
					
					parentArr[next][0] = now; // 직속 조상
					if(10 < cnt) {
						parentArr[next][1] = parentArr[now][0]; // 2번째 조상
						parentArr[next][2] = parentArr[parentArr[next][1]][0]; // 3번째 조상
						parentArr[next][3] = parentArr[parentArr[next][2]][0]; // 4번째 조상
						parentArr[next][4] = parentArr[parentArr[next][3]][0]; // 5번째 조상
						parentArr[next][5] = parentArr[parentArr[next][4]][0]; // 6번째 조상
						parentArr[next][6] = parentArr[parentArr[next][5]][0]; // 7번째 조상
						parentArr[next][7] = parentArr[parentArr[next][6]][0]; // 8번째 조상
						parentArr[next][8] = parentArr[parentArr[next][7]][0]; // 9번째 조상
						parentArr[next][9] = parentArr[parentArr[next][8]][0]; // 10번째 조상
					}
				}
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			int depthA = depthArr[A];
			int depthB = depthArr[B];
			
			while(true) {
				depthA = depthArr[A];
				depthB = depthArr[B];
				
				if(depthA < depthB) { // B가 깊을 때
					if(depthA <= depthB-10 && 1 < depthB-11) {
						B = parentArr[B][9];
					}
					else B = parentArr[B][0];
				}
				else if(depthA == depthB) { // 깊이 같을 때
					if(A == B) {
						sb.append(A).append("\n");
						break; // 같은 조상 찾았다면
					}
					else {
						if(11 < depthA && parentArr[A][9] != parentArr[B][9]) {
							A = parentArr[A][9];
							B = parentArr[B][9];
						}
						else {
							A = parentArr[A][0];
							B = parentArr[B][0];
						}
					}
				}
				else { // A가 깊을 때
					if(depthB <= depthA-10 && 1 < depthA-11) A = parentArr[A][9];
					else A = parentArr[A][0];
				}
			}
			
		}
		
		System.out.print(sb);
	}
}