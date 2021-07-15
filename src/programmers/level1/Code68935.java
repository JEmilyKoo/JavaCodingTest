package programmers.level1;
public class Code68935 {
	
	  public int solution(int n) {
		  	
	        String string="";
	        while (n>0){
	            string+=n%3;
	            n=(int)(n/3);
	        }
	       // 뒤집을 때 이런 걸 써도 된다 
	       // string=new StringBuilder(string).reverse().toString();
	        
	        return Integer.parseInt(string, 3);
	    }
	  
	  public static void main(String[] args) {
		  int n = 45;
		  Code68935 code = new Code68935();
		  System.out.println(code.solution(n));
		  
	  }
}
