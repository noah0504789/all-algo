import java.io.*;

public class Main {
    private static int n;

    public static void main(String... args) throws IOException {
        n = readInt();
        
        int cnt = 0;
        
        while (true) {
            if (n % 5 == 0) {
                cnt += n / 5;                
                break;
            } else {
                n -= 2;
                cnt++;
            }
            
            if (n < 0) {
                cnt = -1;
                break;
            }
        }

        System.out.print(cnt);
    }

    private static int readInt() throws IOException {
        int result = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();

        while (c >= '0' && c <= '9') {
            result *= 10;
            result += c - '0';
            c = System.in.read();
        }

        return result;
    }
}
