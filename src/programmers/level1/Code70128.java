package programmers.level1;

import java.util.stream.IntStream;

public class Code70128 {
	
	  public int solution(int[] a, int[] b) {
	        //int sum = 0;
	        //for(int i=0 ; i<a.length;i++)
	        //     sum+= a[i]*b[i];
	        //return sum;
		//  return IntStream.range(0, a.length).map(index -> a[index] * b[index]).sum();
	        
		 // 	return IntStream.range(0, a.length).sum(); // 0+1+2+3=6
	     //	return a.length; // 4
		  return IntStream.range(0, a.length).map(index -> 4).sum();
		  	
	    }
	  
	  public static void main(String[] args) {
		  int[] a = {1,2,3,4};
		  int[] b = {1,2,3,4};
		  
		 // IntStream.range(0, a.length).map(index -> a[index] * b[index]).sum();
		  
		  
		  Code70128 code = new Code70128();
		//  IntStream.range(0, a.length).map(index -> 4).max().ifPresent(System.out::println);
		//  System.out.println( IntStream.range(0, a.length).map(index->4).max());
		  System.out.println(code.solution(a,b));
		  
	  }
}
