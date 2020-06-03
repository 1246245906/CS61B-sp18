public class drawTriangle {
   public static void main(String[] args) {
      drawTriangle(10);
   }
   public static void drawTriangle(int n){
      int i = 0;
		while(i < n){
			int j = 0;
			i += 1;

			while(j<i){
				System.out.print("*");
				j += 1;
			}
			if(i<n){
				System.out.println();
			}
		}
   }
}