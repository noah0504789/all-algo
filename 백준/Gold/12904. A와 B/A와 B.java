import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;

    private static StringBuilder t;
    private static String s;
    private static int sSize, lastIdx;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        s = br.readLine();
        t = new StringBuilder(br.readLine());

        sSize = s.length();

        while (t.length() > sSize) {
            lastIdx = t.length()-1;

            if (t.charAt(lastIdx) == 'A') {
                t.setLength(t.length()-1);
            } else {
                t.setLength(t.length()-1);
                t.reverse();
            }
        }

        bw.write(t.toString().equals(s) ? "1" : "0");
        bw.flush();
    }
}
