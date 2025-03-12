package org.example.technique;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class PrefixSumTest {
    class NumArray {
        public int[] arr;
        public NumArray(int[] nums) {
            arr=nums;
            for(int i=1;i<arr.length;i++){
                arr[i]+=arr[i-1];
            }
        }

        public int sumRange(int left, int right) {
            if(left==0){
                return arr[right];
            }
            return arr[right]-arr[left-1];
        }
    }

    @Test
    void q303_RangeSumQueryImmutable() {
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i<nums.length; i++) {
            sum += nums[i];
            int rem = sum%k;
            if (map.containsKey(rem))
                if (i - map.get(rem)>=2)
                    return true;
            if (!map.containsKey(rem))
                map.put(rem, i);
        }
        return false;
    }

    @Test
    void q523_ContinuousSubarraySum() {
    }

    public int findMaxLength(int[] nums) {
        int zero = 0;
        int one = 0;
        int res = 0;

        HashMap<Integer, Integer> diffIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (n == 0)
                zero++;
            else
                one++;
            if (diffIndex.get(one - zero) == null)
                diffIndex.put(one - zero, i);

            if (one == zero) {
                res = one + zero;
            } else {
                int idx = diffIndex.getOrDefault(one - zero, 0);
                res = Math.max(res, i - idx);
            }
        }

        return res;
    }

    @Test
    void q525_ContiguousArray() {
    }

    public int subarraySum(int[] nums, int k) {
        int res = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int diff = sum - k;
            if (map.containsKey(diff)) {
                res += map.get(diff);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    @Test
    void q560_SubarraySumEqualsK() {
    }
}
