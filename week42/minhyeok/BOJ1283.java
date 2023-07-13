package week42.minhyeok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1283 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[26]; // visited alphabets
        StringBuilder sb = new StringBuilder(); // answer
        // input
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String word = "";
            boolean flag = false; // check if shortcut key exist
            while (st.hasMoreTokens()) {
                String str = st.nextToken();
                if (!flag) {
                    char c = str.charAt(0);
                    // upper -> lowercase
                    int idx = 0;
                    if (c - 'a' >= 0) {
                        idx = c-'a';
                    } else {
                        idx = c-'A';
                    }

                    // make shortcut key
                    if (!visited[idx]) {
                        visited[idx] = true;
                        flag = true;
                        String temp = "[" + c + "]";
                        int size = str.length();
                        for (int j = 1; j < size; j++) {
                            temp += str.charAt(j);
                        }
                        str = temp;
                    }
                }
                word += str;
                word += ' ';
            }
           // still no shortcut key -> check all letters
            if (!flag) {
                int size = word.length();
                int sIdx = 0;
                for (int j = 1; j < size; j++) {
                    char c = word.charAt(j);
                    if (c == ' ') continue;
                    int idx = 0;
                    if (c - 'a' >= 0) {
                        idx = c-'a';
                    } else {
                        idx = c-'A';
                    }

                    if (!visited[idx]) {
                        visited[idx] = true;
                        flag = true;
                        sIdx = j;
                        break;
                    }
                }
                // find shortcut key
                if (sIdx > 0) {
                    String temp = "";
                    for (int j = 0; j < size; j++) {
                        if (sIdx == j) {
                            temp += "[" + word.charAt(j) + "]";
                            continue;
                        }
                        temp += word.charAt(j);
                    }
                    word = temp;
                }
            }
            sb.append(word+"\n");
        }
        System.out.println(sb);
    }
}
