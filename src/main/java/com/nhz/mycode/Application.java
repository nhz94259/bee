package com.nhz.mycode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main1(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public int binarySearch(int[] nums,int target){
        int l = 0 ;
        int r = nums.length-1;
        while(l< r){
            int m = l + (r-l)/2;
            if(target>nums[m]){
                l = m + 1;
            }else{
                r = m - 1;
            }
        }
        return l;
    }

    public int lengthOfLIS(int[] nums) {
        int[] res = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            int k = binarySearch(nums,nums[i]);
            res[k] = nums[i];
        }
        return res.length;
    }

    public static void main(String[] args) {
        Application app = new Application();
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(app.lengthOfLIS(nums));
    }

}
