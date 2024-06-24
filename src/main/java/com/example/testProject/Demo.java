package com.example.testProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.model.Address;
import com.example.model.Employee;

public class Demo {

	 public static void main(String args[]) {
		        
		int[] nums = {0,1,0,3,2,3};
        List a = new ArrayList();
        int temp = 1;
        for(int i=0; i<nums.length - 1; i++){
        	a.add(nums[i]);
        	int k = i;
            for(int j = i +1; j< nums.length; j++){
                if(nums[k] < nums[j]) {
                	a.add(nums[j]);
                	k = j;
                }
            }
            System.out.println(a);
            if(a.size() >  temp) {
            	temp = a.size();
            }
            a = new ArrayList();
       }
        System.out.println(temp);
	 }
}

