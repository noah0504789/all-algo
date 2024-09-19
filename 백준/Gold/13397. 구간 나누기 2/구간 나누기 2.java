import java.io.*;

public class Main {
    private static int[] nums;
    private static int n, m, mid, ans, min = Integer.MAX_VALUE, max = 0;

    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        nums = new int[n];        
        for (int i = 0; i < n; i++) {
            nums[i] = readInt();
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
                
        

        int l = 0, r = max-min;
        ans = r;
                
        while (l <= r) {
            mid = (l+r)/2;
            
            if (available()) {
                ans = mid;
                r = mid-1;
            } else {
                l = mid+1;
            }           
        }

        System.out.print(ans);
    }

    private static boolean available() {
        int min = nums[0], max = nums[0], cnt = 1;
        
        for (int i = 1; i < n; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
            
            if (max-min > mid) {
                cnt++;
                min = nums[i];
                max = nums[i];
            }
            
            if (cnt > m) return false;
        }
        
        return true;
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
