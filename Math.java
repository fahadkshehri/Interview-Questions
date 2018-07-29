/**
 * @author  Fahad Alshehri
 * @Date  6/25/2018.
 * Independent Study
 * Array Interview Questions
 */
import java.util.*;
import java.math.*;

public class Math {

    public static void main(String[] args) throws Exception {

    }

    public boolean canMeasureWater(int x, int y, int z) {
        if (x == y && y == z) return true;
        if (z > (x + y)) return false;
        BigInteger b1 = new BigInteger(String.valueOf(x));
        BigInteger b2 = new BigInteger(String.valueOf(y));
        BigInteger b3 = b1.gcd(b2);
        return b3.intValue() != 0 && (z % b3.intValue()) == 0;
    }

    public int countPrimes(int n) {
        if (n == 0 || n == 1 || n == 2) return 0;
        else if (n == 3) return 1;
        BitSet set = new BitSet();
        n = n - 1;
        int sqRt = (int) Math.sqrt(n);
        int count = n;
        for (int i = 2; i <= sqRt; i++) {
            if (!set.get(i)) {
                for (int j = 2; (i * j) <= n; j++) {
                    if (!set.get(i * j)) {
                        count--;
                        set.set(i * j);
                    }
                }
            }
        }
        return count - 1;
    }

    public int maxRotateFunction(int[] A) {
        if (A.length == 0 || A.length == 1) return 0;
        int max = Integer.MIN_VALUE;
        int l = A.length;
        int sum = 0, prodSum = 0;
        for (int i = 0; i < l; i++) {
            prodSum += (A[i] * i);
            sum += A[i];
        }
        max = Math.max(max, prodSum);
        for (int i = 0; i < l - 1; i++) {
            prodSum = (prodSum - sum + A[i] + ((l - 1) * A[i]));
            max = Math.max(max, prodSum);
        }
        return max;
    }
    
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        Pair target = new Pair(tx, ty);
        Pair start = new Pair(sx, sy);
        while(true){
            if(start.x == target.x && start.y == target.y){
                return true;
            } else if(start.x > target.x || start.y > target.y || target.x == target.y){
                return false;
            } else if(start.x == target.x){
                int t = target.y - start.y;
                return (t % target.x) == 0;
            } else if(start.y == target.y){
                int t = target.x - start.x;
                return (t % target.y) == 0;
            } else{
                if(target.x > target.y){
                    int[] R = reduce(target.x, target.y);
                    target.x = R[0];
                    target.y = R[1];
                } else {
                    int[] R = reduce(target.y, target.x);
                    target.x = R[1];
                    target.y = R[0];
                }
            }
        }
    }

    private int[] reduce(int x, int y){
        int t = x - y;
        int q = t / y;
        x -= (y * q);
        if((t % y) != 0){
            x -= y;
        }
        return new int[]{x, y};
    }
}
