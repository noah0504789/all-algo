import java.io.*;
import java.util.*;

public class Main {
    private static List<String> list;
    static int N, answer = Integer.MIN_VALUE;
    static String input;
    static boolean[] bracket;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        bracket = new boolean[N];
        input = br.readLine();
        list = new ArrayList<>();

        if (input.length() == 1)
            answer = Character.getNumericValue(input.charAt(0));
        else
            dfs(0);

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int depth) {
        if (depth == N+1) {
            express();
            answer = Math.max(answer, cal2());
            return;
        }

        if (depth == 0) {
            bracket[depth] = true;
            dfs(depth + 2);
            bracket[depth] = false;
        } else if (!bracket[depth - 2] && depth < N - 1) {
            bracket[depth] = true;
            dfs(depth + 2);
            bracket[depth] = false;
        }

        dfs(depth + 2);
    }

    static void express() {
        list.clear();
        int idx = 0;
        while (idx < N) {
            char val = input.charAt(idx);

            if (val == '+' || val == '-') {
                list.add(val + "");
            } else if (val == '*') {
                int cur = Integer.parseInt(list.get(list.size() - 1)), next;

                if (bracket[idx + 1]) {
                    next = cal(input.charAt(idx + 1) - '0', input.charAt(idx + 3) - '0', input.charAt(idx + 2));
                    idx += 3;
                } else {
                    next = input.charAt(idx + 1) - '0';
                    idx++;
                }

                list.set(list.size() - 1, cal(cur, next, val) + "");
            } else if (bracket[idx]) {
                list.add(cal(val - '0', input.charAt(idx + 2) - '0', input.charAt(idx + 1)) + "");
                idx += 2;
            } else {
                list.add(val + "");
            }

            idx++;
        }
    }

    static int cal2() {
        int rst = Integer.parseInt(list.get(0));

        for (int i = 1; i < list.size(); i += 2)
            rst = cal(rst, Integer.parseInt(list.get(i + 1)), list.get(i).charAt(0));

        return rst;
    }

    static int cal(int n1, int n2, char operation) {
        if (operation == '+')
            return n1 + n2;
        else if (operation == '-')
            return n1 - n2;
        else
            return n1 * n2;
    }
}
