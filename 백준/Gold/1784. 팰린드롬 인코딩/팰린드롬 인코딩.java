import java.io.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;

    private static String[][] dp;
    private static String input;
    private static int size, start, end;

    public static void main(String... args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input = br.readLine();
        size = start = end = input.length();

        for (int i = 0; i < size-1; i++) {
            if (input.charAt(i) == input.charAt(i+1)) continue;

            start = i + 1;
            break;
        }

        for (int i = start; i < size-1; i++) {
            if (input.charAt(i) != input.charAt(i+1)) continue;

            end = i + 1;
            break;
        }

        bw.write((end - start + 1) + "\n");
        bw.flush();
    }
}
