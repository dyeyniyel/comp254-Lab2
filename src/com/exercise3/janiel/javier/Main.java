package com.exercise3.janiel.javier;

import java.util.Arrays;

public class Main {

	//method to find missing number from the array
	public static int findNumber(int[] J) {
		int n = J.length + 1;  //total number of elements in the array is n+1

		//total sum of the numbers using arithmetic progression
		int totalOfArray = n * (n - 1) / 2; 
		System.out.println("Expected sum of numbers due to arithmetic progression: " + totalOfArray);


		// Initialize sum to 0. This will be used to accumulate the total of the numbers in the array
		int sum = 0;
		for (int i = 0; i < J.length; i++) {
			sum += J[i];   //add each element to the sum through the loop
		}
		System.out.println("Actual sum of the array: " + sum);

		//difference between the expected total and the actual sum to find missing number
		int missingNumber = totalOfArray - sum;
		System.out.println("Missing number: " + missingNumber);

		return missingNumber;
	}

	//main method
	//Student Number: 301379377
	//Student Name: Janiel Mark Javier

	public static void main(String[] args) {
		int[] A = {0, 1, 3, 4}; // example array, missing number is 2
		System.out.println("Array: " + Arrays.toString(A)); // print the array
		findNumber(A); // should print 2
	}
}
