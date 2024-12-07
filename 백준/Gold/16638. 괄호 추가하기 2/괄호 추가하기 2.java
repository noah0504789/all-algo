import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;

    private static List<String> list;
    private static String input;
    private static boolean[] bracket;
    private static int n, ans;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        input = br.readLine();

        bracket = new boolean[n];
        list = new ArrayList<>();

        ans = Integer.MIN_VALUE;

        if (n == 1) ans = input.charAt(0) - '0';
        else dfs(0);

        bw.write(ans+"");
        bw.flush();
    }

    private static void dfs(int depth) {
        if (depth == n+1) {
            express();
            ans = Math.max(ans, cal2());
            return;
        }

        if (depth == 0 || (depth > 0 && !bracket[depth-2] && depth < n-1)) {
            bracket[depth] = true;
            dfs(depth+2);
            bracket[depth] = false;
        }

        dfs(depth+2);
    }

    private static void express() {
        list.clear();

        int idx = 0;
        while (idx < n) {
            char val = input.charAt(idx);

            if (val == '*') {
                int cur = Integer.parseInt(list.get(list.size()-1)); 
                int next;

                if (bracket[idx+1]) {
                    next = cal1(input.charAt(idx+1) - '0', input.charAt(idx+3) - '0', input.charAt(idx+2));
                    idx+=3;
                } else {
                    next = input.charAt(idx+1) - '0';
                    idx++;
                }

                list.set(list.size()-1, cal1(cur, next, val)+"");
            } else if (bracket[idx]) {
                list.add(cal1(val - '0', input.charAt(idx+2) - '0', input.charAt(idx+1))+"");
                idx+=2;
            } else {
                list.add(val+"");
            }

            idx++;
        }
    }

    private static int cal1(int op1, int op2, char op) {
        if (op == '+') return op1+op2;
        if (op == '-') return op1-op2;

        return op1*op2;
    }

    private static int cal2() {
        int rst = Integer.parseInt(list.get(0));

        for (int i = 1; i < list.size(); i+=2) rst = cal1(rst, Integer.parseInt(list.get(i+1)), list.get(i).charAt(0));

        return rst;
    }
}
