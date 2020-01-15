package com.ch.example.test.abstractClass;

import com.ch.example.utils.date.LocalDateUtils;

import javax.swing.tree.TreeNode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author: chenhao
 * @Date: 2019/4/29 11:14
 */
public class Test  {



    public static void main(String[] args) {

        twoSum(new int[]{2,7,11,15},9);
    }

    public  static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        int[] result = new int[2];
        for(int i=0;i<nums.length;i++){
            int key=target-nums[i];
            if(map.get(key)!=null&&map.get(key)!=i){
                result[0]=i;
                result[1]=map.get(key);
                break;
            }
        }

        return result;


    }

}
