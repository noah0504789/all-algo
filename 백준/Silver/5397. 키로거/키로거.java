import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static StringBuilder sb, cur;

    private static char[] input;
    private static int tc, idx;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        cur = new StringBuilder();

        tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            cur.setLength(0);
            idx = 0;

            for (char c : br.readLine().toCharArray()) {
                if (c == '<') {
                    if (idx == 0) continue;

                    idx--;
                } else if (c == '>') {
                    if (idx >= cur.length()) continue;

                    idx++;
                } else if (c == '-') {
                    if (idx == 0) continue;
                    
                    cur.deleteCharAt(--idx);
                } else {
                    cur.insert(idx++, c);
                }
            }

            sb.append(cur.toString());
            sb.append("\n");
        }

        System.out.print(sb);
    }
}