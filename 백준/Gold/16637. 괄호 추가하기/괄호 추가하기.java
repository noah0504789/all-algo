import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;

    private static char[] operators, chArr;
    private static int[] operands;
    private static int n, size, max;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        size = n/2;
       
        operands = new int[size + 1];
        operators = new char[size];
        
        max = Integer.MIN_VALUE;

        chArr = br.readLine().toCharArray();
        for (int i = 0, j = 0, k = 0; i< chArr.length; i++) {
            char ch = chArr[i];

            if (Character.isDigit(ch)) operands[j++] = ch - '0';
            else operators[k++] = ch;
        }

        dfs(0, operands[0]);
        
        System.out.print(max);
    }
    
    private static void dfs(int idx, int result) {
        if (idx >= size) {
            max = Math.max(max, result);
            return;
        }
        
        dfs(idx+1, calc(result, operands[idx+1], operators[idx]));
        
        if (idx + 1 < size) {
            int next = calc(operands[idx+1], operands[idx+2], operators[idx+1]);
            dfs(idx+2, calc(result, next, operators[idx]));
        }
    }

    private static int calc(int left, int right, char op) {
        int result = left;

        if (op == '+') result += right;
        else if (op == '-') result -= right;
        else if (op == '*') result *= right;

        return result;
    }
}