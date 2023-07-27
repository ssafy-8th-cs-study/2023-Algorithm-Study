import java.io.*;
import java.util.*;
class BOJ_9465_스티커 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int nT = 0; nT < T; nT++) {
			int n = Integer.parseInt(br.readLine());
			
			int[][] input = new int[n+1][3];
			for (int r = 1; r <= 2; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 1; c <= n; c++) {
					input[c][r] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] board = new int[n+1][3]; // 0 둘다 안 뗌 / 1 1행 뗌 / 2 2행 뗌
			
			for (int c = 1; c <= n; c++) { // 열 차례대로 보면서
				board[c][0] = Math.max(board[c-1][1], board[c-1][2]);
				board[c][1] = Math.max(board[c-1][0] + input[c][1], board[c-1][2] + input[c][1]);
				board[c][2] = Math.max(board[c-1][0] + input[c][2], board[c-1][1] + input[c][2]);
			}
			
			sb.append(Math.max(board[n][1], board[n][2])).append("\n");
		}
		
		System.out.print(sb);
	}
}