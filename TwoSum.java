/**
* Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
* You may assume that each input would have exactly one solution, and you may not use the same element twice.
* You can return the answer in any order.
 */


class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> sumIndexMap = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            int diff = target - nums[i];
            if(sumIndexMap.get(diff) != null){
                int[] result = {sumIndexMap.get(diff), i};
                return result;
            }
            sumIndexMap.put(nums[i], i);
        }
        
        int[] result = new int[0];
        return result;
    }
}