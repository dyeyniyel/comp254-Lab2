package com.exercise1.janiel.javier;

/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


/**
 * Code for end-of-chapter exercises on asymptotics.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
class Exercises {

	//running time is O(n)
//Answer: O(n) because loop runs n times which is linear. There is a single loop in the method that iterates over each element of the array exactly once
 
	/** Returns the sum of the integers in given array. */
  public static int example1(int[] arr) {
    int n = arr.length, total = 0;
    for (int j=0; j < n; j++)       // loop from 0 to n-1
      total += arr[j];
    return total;
  }

//Answer: O(n) because loop runs n/2 times which is still linear
//  
  /** Returns the sum of the integers with even index in given array. */
  public static int example2(int[] arr) {
    int n = arr.length, total = 0;
    for (int j=0; j < n; j += 2)    // note the increment of 2
      total += arr[j];
    return total;
  }

  
  //Answer: O(n^2) because the first loop runs n times, then the inner loop runs j times (where j runs n-1 times), multiplying them will make it a quadratic function of n
  /** Returns the sum of the prefix sums of given array. */
  public static int example3(int[] arr) {
    int n = arr.length, total = 0;
    for (int j=0; j < n; j++)       // loop from 0 to n-1
      for (int k=0; k <= j; k++)    // loop from 0 to j
        total += arr[j];
    return total;
  }

  //Answer: O(n) as the loop still iterates n times, and within each iteration, there are constant operations like addition/assignment.
  /** Returns the sum of the prefix sums of given array. */
  public static int example4(int[] arr) {
    int n = arr.length, prefix = 0, total = 0;
    for (int j=0; j < n; j++) {     // loop from 0 to n-1
      prefix += arr[j];
      total += prefix;
    }
    return total;
  }
  
//Answer: O(n^3) as there is an outermost loop, a middle loop and an innermost loop which is atleast n*n*n = n^3 number of runs.
  /** Returns the number of times second array stores sum of prefix sums from first. */
  public static int example5(int[] first, int[] second) { // assume equal-length arrays
    int n = first.length, count = 0;
    for (int i=0; i < n; i++) {     // loop from 0 to n-1
      int total = 0;
      for (int j=0; j < n; j++)     // loop from 0 to n-1
        for (int k=0; k <= j; k++)  // loop from 0 to j
        	
        	//NOTE: if int k=0; k <= 10; k++ >> running time will be O(n^2) as this will be n x n x 10
          total += first[k];
      if (second[i] == total) count++;
    }
    return count;
  }
  
  
  
  //main method
  //Student Number: 301379377
  //Student Name: Janiel Mark Javier
  public static void main(String[] args)
  {
	  System.out.println("Answer list:");
	  System.out.println("1. O(n) because there is a single loop in the method that iterates over each element of the array exactly once");
	  System.out.println("2. O(n) because loop runs n/2 times which is still linear");
	  System.out.println("3. O(n^2) because the first loop runs n times, then the inner loop runs j times (where j runs n-1 times), multiplying them will make it a quadratic function of n");
	  System.out.println("4. O(n)as the loop still iterates n times, and within each iteration, there are constant operations like addition/assignment");
	  System.out.println("5. O(n^3) as there is an outermost loop, a middle loop and an innermost loop which is atleast n*n*n = n^3 number of runs.");
  }

}



