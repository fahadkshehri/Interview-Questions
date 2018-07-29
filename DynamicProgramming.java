/**
 * Created by fahad on 7/29/2018.
 */
public class DynamicProgramming {

    public static void main(String[] args) throws Exception {

    }


    /**
     * https://leetcode.com/problems/maximum-average-subarray-i/description
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int max = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            nums[i] = Math.max(nums[i], nums[i] + nums[i + 1]);
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    /**
     * https://leetcode.com/problems/min-cost-climbing-stairs
     * @param cost
     * @return
     */
    public static int minCostClimbingStairs(int[] cost) {
        for(int i = cost.length - 1; i >= 0; i --){
            if(i + 1 < cost.length && i + 2 < cost.length){
                cost[i] = Math.min(cost[i] + cost[i + 1], cost[i] + cost[i + 2]);
            }
        }
        return Math.min(cost[0], cost[1]);
    }
    /**
     * https://leetcode.com/problems/paint-house-ii/description
     * @param costs
     * @return
     */
    public int minCostII(int[][] costs) {
        if(costs.length == 0) return 0;
        int[][] lMin = new int[costs.length][costs[0].length];
        int[][] rMin = new int[costs.length][costs[0].length];
        for(int i = costs.length - 2; i >= 0; i--){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < costs[0].length; j++){
                lMin[i + 1][j] = min;
                min = Math.min(min, costs[i + 1][j]);
            }
            min = Integer.MAX_VALUE;
            for(int j = costs[0].length - 1; j >= 0; j--){
                rMin[i + 1][j] = min;
                min = Math.min(min, costs[i + 1][j]);
            }

            for(int j = 0; j < costs[0].length; j++){
                if(j == 0){
                    costs[i][j] = costs[i][j] + rMin[i + 1][j];
                } else if(j == costs[0].length - 1){
                    costs[i][j] = costs[i][j] + lMin[i + 1][j];
                } else {
                    costs[i][j] = costs[i][j] + Math.min(lMin[i + 1][j], rMin[i + 1][j]);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < costs[0].length; i ++){
            min = Math.min(min, costs[0][i]);
        }
        return min;
    }

    /**
     * https://leetcode.com/problems/longest-increasing-subsequence/description
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] A = new int[nums.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0, l = nums.length; i < l; i++) {
            int lis = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    lis = Math.max(lis, A[j] + 1);
            }
            A[i] = lis;
            max = Math.max(max, A[i]);
        }
        return max;
    }

    /**
     * https://leetcode.com/problems/climbing-stairs/description
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 0 || n == 1) return 1;
        int[] A = new int[n + 1];
        A[n] = 1;
        A[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--)
            A[i] = A[i + 1] + A[i + 2];
        return A[0];
    }


    /**
     * https://leetcode.com/problems/split-array-with-same-average/description
     * @param nums
     * @param m
     * @return
     */
    public static int splitArray(int[] nums, int m) {
        int[][] dp = new int[m][nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (j + 1 >= nums.length) break;
                for (int k = 0; k < m - 1; k++) {
                    dp[k + 1][i] = (dp[k + 1][i] == 0) ? Integer.MAX_VALUE : dp[k + 1][i];
                    int temp = Math.max(sum, dp[k][j + 1]);
                    dp[k + 1][i] = Math.min(dp[k + 1][i], temp);
                }
            }
            dp[0][i] = sum;
        }
        return dp[m - 1][0];
    }

    /**
     * https://leetcode.com/problems/house-robber/description
     * @param nums
     * @return
     */
    public static  int rob(int[] nums) {
         int[] max;
        if (nums.length == 0) return 0;
        max = new int[nums.length];
        if (nums.length == 1) return nums[0];
        max[nums.length - 1] = nums[nums.length - 1];
        max[nums.length - 2] = Math.max(nums[nums.length - 1], nums[nums.length - 2]);
        for (int i = nums.length - 3; i >= 0; i--) {
            max[i] = Math.max(max[i + 1], nums[i] + max[i + 2]);
        }
        return max[0];
    }


    private int[][] DP;

    /**
     * https://leetcode.com/problems/coin-change/description
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        DP = new int[coins.length][amount + 1];
        int result = dp(amount, 0, coins);
        if (result == Integer.MAX_VALUE - 1) return -1;
        return result;
    }

    private int dp(int amount, int i, int[] coins) {
        if (amount == 0) return 0;
        else if (i >= coins.length || amount < 0) return Integer.MAX_VALUE - 1;
        if (DP[i][amount] != 0) return DP[i][amount];
        DP[i][amount] = Math.min(1 + dp(amount - coins[i], i, coins), dp(amount, i + 1, coins));
        return DP[i][amount];
    }

}