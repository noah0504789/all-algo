import java.io.*;
import java.util.*;

public class Main {        
    private static BufferedReader br;
    private static StringBuilder sb;

    private static final String NO = "FRULA";
    //private static Stack<Character> stack;
    private static char[] str, boom, result;
    private static int idx;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        str = br.readLine().toCharArray();
        boom = br.readLine().toCharArray();
        
        idx = 0;
        result = new char[str.length];        
        //stack = new Stack<>();
        
        for (char c : str) {
            //stack.push(c);        
            result[idx++] = c;
            
            if (idx < boom.length) continue;
            if (boom[boom.length-1] != c) continue;
            
            boolean canBoom = true;
            for (int i = 0; i < boom.length; i++) {
                if (boom[i] != result[idx - boom.length + i]) {
                    canBoom = false;
                    break;
                }
            }
            
            if (canBoom) idx -= boom.length;
        }
        
        System.out.print(idx == 0 ? NO : String.valueOf(result, 0, idx));
    }
}