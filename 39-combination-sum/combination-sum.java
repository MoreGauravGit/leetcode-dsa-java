class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    void solve(int index,
               int[] nums,
               int target,
               List<Integer> current){

        if(target==0){

            ans.add(new ArrayList<>(current));
            return;
        }

        if(index==nums.length)
            return;

        if(nums[index]<=target){

            current.add(nums[index]);

            solve(index,nums,target-nums[index],current);

            current.remove(current.size()-1);
        }

        solve(index+1,nums,target,current);
    }

    public List<List<Integer>> combinationSum(int[] candidates,
                                              int target) {

        solve(0,candidates,target,new ArrayList<>());

        return ans;
    }
}