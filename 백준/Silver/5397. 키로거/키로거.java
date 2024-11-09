import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static StringBuilder sb;

    private static Stack<Character> left, right;
    private static char[] input;
    private static int tc;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        tc = Integer.parseInt(br.readLine());

        left = new Stack<>();
        right = new Stack<>();

        for (int i = 0; i < tc; i++) {
            left.clear();
            right.clear();

            for (char c : br.readLine().toCharArray()) {
                if (c == '<') {                    
                    if (left.isEmpty()) continue;
                    
                    right.push(left.pop());
                } else if (c == '>') {
                    if (right.isEmpty()) continue;
                    
                    left.push(right.pop());
                } else if (c == '-') {
                    if (left.isEmpty()) continue;
                    
                    left.pop();
                } else {
                    left.push(c);
                }
            }

            while (!right.isEmpty()) left.push(right.pop());

            int size = left.size();

            for (int j = 0; j < size; j++) sb.append(left.get(j));

            sb.append("\n");
        }

        System.out.print(sb);
    }
}