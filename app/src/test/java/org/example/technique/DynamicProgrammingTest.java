package org.example.technique;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DynamicProgrammingTest {
    int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    int fibMemo(int n, int[] memo) {
        // check in memo, if found, retrieve and return right away
        if (memo[n] != 0) return memo[n];

        if (n == 0 || n == 1) return n;

        int res = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);

        // save result to memo before returning
        memo[n] = res;
        return res;
    }

    // This method calculates the number of distinct ways to climb to the top.
    public int climbStairs(int n) {
        int first = 0, second = 1;

        // Loop through number of steps n
        for (int i = 0; i < n; i++) {
            // Calculate next number in the series
            int next = first + second;

            // Update the previous two numbers for next iteration
            first = second;
            second = next;
        }

        // The 'second' variable holds the total ways to reach the top
        return second;
    }

    @Test
    void q70_ClimbingStars() {
    }

    public int rob(int[] nums) {
        // f represents the max profit we can get from the previous house
        int prevNoRob = 0;
        // g represents the max profit we can get if we rob the current house
        int prevRob = 0;

        // Iterate over all the houses in the array
        for (int currentHouseValue : nums) {
            // Store max profit of robbing/not robbing the previous house
            int tempPrevNoRob = Math.max(prevNoRob, prevRob);

            // If we rob the current house, we cannot rob the previous one
            // hence our current profit is previous house's no-rob profit + current house value
            prevRob = prevNoRob + currentHouseValue;

            // Update the previous no-rob profit to be the best of robbing or not robbing the last house
            prevNoRob = tempPrevNoRob;
        }

        // Return the max profit we can get from the last house,
        // regardless of whether we rob it or not
        return Math.max(prevNoRob, prevRob);
    }

    @Test
    void q198_HouseRobber() {
    }

    public int coinChange(int[] coins, int amount) {
        // Define a large value which would act as our "infinity" substitute.
        final int INF = 1 << 30;

        // 'dp' will hold our optimal solutions to sub-problems, dp[i] will store the minimum number of coins needed to make amount 'i'.
        int[] dp = new int[amount + 1];

        // Initialize the dp array with INF to signify that those amounts are currently not achievable with the given coins.
        Arrays.fill(dp, INF);

        // Base case initialization: No coins are needed to make an amount of 0.
        dp[0] = 0;

        // Iterate over each type of coin available.
        for (int coin : coins) {
            // For each coin, try to build up to the target amount, starting from the coin's value itself up to 'amount'.
            for (int currentAmount = coin; currentAmount <= amount; ++currentAmount) {
                // Check if the current coin can contribute to a solution for 'currentAmount'.
                // If so, update dp[currentAmount] to the minimum value between its current and the new possible number of coins used.
                dp[currentAmount] = Math.min(dp[currentAmount], dp[currentAmount - coin] + 1);
            }
        }

        // Return the answer for the target 'amount'. If dp[amount] is still INF, then it was not possible to make the amount using the given coins.
        return dp[amount] >= INF ? -1 : dp[amount];
    }

    void q322_CoinChange() {
    }

    public int minDistance(String word1, String word2) {
        // Lengths of the input strings
        int lenWord1 = word1.length();
        int lenWord2 = word2.length();

        // Create a 2D array to store the subproblem results
        int[][] dpTable = new int[lenWord1 + 1][lenWord2 + 1];

        // Initialize the first column, representing insertions needed to transform an empty string into word2
        for (int indexWord2 = 1; indexWord2 <= lenWord2; ++indexWord2) {
            dpTable[0][indexWord2] = indexWord2;
        }

        // Fill out the dpTable for all subproblems
        for (int indexWord1 = 1; indexWord1 <= lenWord1; ++indexWord1) {
            // First row represents deletions needed to transform word1 into an empty string
            dpTable[indexWord1][0] = indexWord1;

            for (int indexWord2 = 1; indexWord2 <= lenWord2; ++indexWord2) {
                // If the characters are the same, take the value from the diagonal (no operation needed)
                if (word1.charAt(indexWord1 - 1) == word2.charAt(indexWord2 - 1)) {
                    dpTable[indexWord1][indexWord2] = dpTable[indexWord1 - 1][indexWord2 - 1];
                } else {
                    // If the characters are different, take the minimum operations from left (insert), top (delete), or diagonal (replace) and add 1
                    int insertOps = dpTable[indexWord1][indexWord2 - 1];
                    int deleteOps = dpTable[indexWord1 - 1][indexWord2];
                    int replaceOps = dpTable[indexWord1 - 1][indexWord2 - 1];

                    dpTable[indexWord1][indexWord2] = Math.min(insertOps, Math.min(deleteOps, replaceOps)) + 1;
                }
            }
        }

        // The bottom-right cell gives the final result
        return dpTable[lenWord1][lenWord2];
    }

    @Test
    void q72_EditDistance() {
    }

    public int longestCommonSubsequence(String text1, String text2) {
        // Lengths of the input strings
        int length1 = text1.length();
        int length2 = text2.length();

        // Create a 2D array to store the lengths of longest common subsequences
        // for all subproblems, initialized with zero
        int[][] dp = new int[length1 + 1][length2 + 1];

        // Build the dp array from the bottom up
        for (int i = 1; i <= length1; ++i) {
            for (int j = 1; j <= length2; ++j) {
                // If characters match, take diagonal value and add 1
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                // If characters do not match, take the maximum value from
                // the left (dp[i][j-1]) or above (dp[i-1][j])
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // The bottom-right cell contains the length of the longest
        // common subsequence of text1 and text2
        return dp[length1][length2];
    }

    @Test
    void q1143_LongestCommonSequence() {
    }

    void q300_() {
    }

    void q416_() {
    }
}
