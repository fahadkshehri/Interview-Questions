/**
 * @author  Fahad Alshehri
 * @Date  6/25/2018.
 * Independent Study
 * Array Interview Questions
 */
import java.util.*;

public class Solution {


    public static void main(String[] args) {

    }

    /**
     * https://leetcode.com/problems/k-empty-slots/description/
     * @param flowers
     * @param k
     * @return
     */
    public static int kEmptySlots(int[] flowers, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < flowers.length; i ++){
            Integer lowerBound = set.floor(flowers[i]);
            Integer upperBound = set.ceiling(flowers[i]);
            if(lowerBound != null){
                if((Math.abs(flowers[i] - lowerBound) + 1) == k){
                    return i + 1;
                }
            } if(upperBound != null){
                if((Math.abs(flowers[i] - upperBound) + 1) == k){
                    return i + 1;
                }
            }
            set.add(flowers[i]);
        }
        return -1;
    }

    /**
     * https://leetcode.com/problems/missing-number/description/
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        int arrSum = (((n + 1)) * n) / 2;
        if (arrSum == sum) return 0;
        else return arrSum - sum;
    }
    public int[][] imageSmoother(int[][] M) {

        int[] R = {1, -1, 0, 0, 1, -1, 1, -1};
        int[] C = {0, 0, -1, 1, 1, 1, -1, -1};

        int[][] result = new int[M.length][M[0].length];
        for(int i = 0; i < M.length; i ++){
            for(int j = 0; j < M[0].length; j ++){
                int numCount = 0;
                int totalCount = 1;
                for(int k = 0; k < 8; k++){
                    int newR = i + R[k];
                    int newC = j + C[k];
                    if(newR >= 0 && newC >= 0 && newR < M.length && newC < M[0].length){
                        if(M[newR][newC] > 0){
                            numCount += M[newR][newC];
                        }
                        totalCount++;
                    }
                }
                if(M[i][j] == 1) numCount++;
                result[i][j] = numCount / totalCount;
            }
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/third-maximum-number/description
     * @param nums
     * @return
     */
    public static int thirdMax(int[] nums) {
        long[] max = {Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE};
        int count = 0;
        for (int num : nums) {
            for (int j = 0; j < 3; j++) {
                if (max[j] > num) continue;
                else if (max[j] == num) break;
                int k = j;
                long temp1, temp2;
                temp1 = num;
                count++;
                while (k < 3) {
                    temp2 = max[k];
                    max[k] = temp1;
                    temp1 = temp2;
                    k++;
                }
                break;
            }
        }
        System.out.println(Integer.MIN_VALUE);
        return (count >= 3) ? (int) max[2] : (int) max[0];
    }

    public static int read(char[] buf, int n) {
        int i = 0;
        int toRead = Math.min(n, buf.length);
        while(i < toRead){
            char[] temp = new char[4];
            int r = read4(temp);
            for(int j = 0; j < r && i < toRead; j ++){
                buf[i] = temp[j];
                i++;
            }
            if(r < 4) break;
        }
        return Math.min(i, toRead);
    }


    private static int read4(char[] buf){
        return 1; // error
    }

    /**
     *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {

        int min = prices[0];
        int minIndex = 0;
        for(int i = 0; i< prices.length; i++){
            if(prices[i] < min){
                min = prices[i]; // found best price to buy
                minIndex = i;  // index of best price to buy
            }
        }

        System.out.println(min);

        int price = 0;
        int bestPrice = 0;
        for(int j = minIndex; j < prices.length; j++){

            price = prices[j] - prices[minIndex];


            if(prices[j] - prices[minIndex] > price){
                bestPrice = prices[j] - prices[minIndex];
            }

        }
        return bestPrice;
    }

    /**
     * https://leetcode.com/problems/max-consecutive-ones/description
     * @param nums
     * @return
     */
    public static int findMaxConsecutiveOnes(int[] nums) {

        int counter = 0;
        int pointer = 0;
        int[] temp = new int[nums.length];

        for(int i = 0; i < nums.length; i++){

            if(nums[i] != 1){
                temp[pointer++] = counter;
                counter = 0;
            }else{
                counter++;
            }

        }

        for(int i = 0; i < temp.length; i++){
            if(temp[i] > counter){
                counter = temp[i];
            }
        }

        return counter;
    }

    /**
     * https://leetcode.com/problems/move-zeroes/description
     * @param nums
     */
    public static void moveZeros(int[] nums){

        int pointer = 0;
        for(int i = 0; i < nums.length; i++){

            if(nums[i] != 0) {
                nums[pointer] = nums[i];
                pointer++;
            }
        }

        while(pointer <nums.length){
            nums[pointer++] = 0;
        }

        for(int i = 0; i < nums.length; i++){

            System.out.print(nums[i] + " ");
        }
    }

}
