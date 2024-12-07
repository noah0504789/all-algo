import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static BufferedWriter bw;

    private static String input;
    private static int n, ans;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        input = br.readLine();

        ans = Integer.MIN_VALUE;

        dfs(0, input.charAt(0)-'0');

        bw.write(ans+"");
        bw.flush();
    }

    private static void dfs(int idx, int rst) {
        if (idx+2 >= n) {
            ans = Math.max(ans, rst);
            return;
        }

        dfs(idx+2, cal(rst, input.charAt(idx+2)-'0', input.charAt(idx+1)));

        if (idx+4 < n) {
            int next = cal(input.charAt(idx+2)-'0', input.charAt(idx+4)-'0', input.charAt(idx+3));
            dfs(idx + 4, cal(rst, next, input.charAt(idx + 1)));
        }
    }

    private static int cal(int op1, int op2, char op) {
        if (op == '+') return op1+op2;
        if (op == '-') return op1-op2;

        return op1*op2;
    }    
}