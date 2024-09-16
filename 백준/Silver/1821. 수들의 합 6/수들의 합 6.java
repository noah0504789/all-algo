import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static StringBuilder sb = new StringBuilder();
    private static int[] nums, mul;
    private static int n, f;
    
    public static void main(String... args) {
        n = sc.nextInt();
        f = sc.nextInt();
        
        nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i+1;

        mul = new int[n];
        mul[0] = 1;
        for (int i = 1; i < n; i++) mul[i] = mul[i-1] * (n-i) / i;
        
        do {
            if (sum() == f) {
                for (int i = 0; i < n; i++) sb.append(nums[i]).append(' ');
                
                System.out.print(sb.toString().trim());
                return;
            }
        } while(np());
    }
    
    private static boolean np() {
        int i = nums.length-1;
        
        while (i > 0 && nums[i-1] >= nums[i]) i--;
        
        if (i == 0) return false;
        
        int pivot = i-1;
        
        int j = nums.length-1;
        
        while (nums[pivot] >= nums[j]) j--;
        
        swap(pivot, j);
        
        reverse(pivot+1, nums.length-1);
        
        return true;
    }
    
    private static void swap(int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
    
    private static void reverse(int start, int end) {    
        while (start < end) swap(start++, end--);
    }
    
    private static int sum() {
        int sum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] * mul[i];           
        }
        
        return sum;
    }
}