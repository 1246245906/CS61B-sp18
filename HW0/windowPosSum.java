public class windowPosSum{
	public static void main(String[] args){
		int[] a = {1, -1, -1, 10, 5, -1};
		int n = 2;
		windowPosSum(a, n);
		System.out.println(java.util.Arrays.toString(a));
	}
	public static void windowPosSum(int[] a, int n){
		for(int i = 0; i < a.length - 1; ++i){
			if(a[i] < 0){
				continue;
			}
			for(int j = 1; j <= n; ++j){
				if(i + j > a.length - 1){
					break;
				}
				a[i] = a[i] + a[i+j];
			}
		}
	}
}