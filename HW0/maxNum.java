public class maxNum {
   // public static int max(int[] m) {
   //    int max = m[0];
   //    int i = 1;
      
   //    while(i < m.length){
   //       if(max < m[i]){
   //          max = m[i];
   //       }
   //       i += 1;
   //    }
   //    return max;
   // }

   /**
    * for loop version of max func.
    */
   public static int max(int[] m) {
      int max = m[0];
      for(int i = 1; i < m.length; ++i){
         if(max < m[i]){
            max = m[i];
         }
      }
      return max;
   }
   public static void main(String[] args) {
      int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
      System.out.println(max(numbers));
   }
}