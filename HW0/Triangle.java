public class Triangle{
	public static void main(String[] args){

		int i = 0;
		while(i < 5){
			int j = 0;
			i += 1;

			while(j<i){
				System.out.print("*");
				j += 1;
			}
			if(i<5){
				System.out.println();
			}
		}
	}
}