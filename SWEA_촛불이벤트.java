import java.util.Scanner;

public class SWEA_촛불이벤트 {
	static long N;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int TC=sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			N=sc.nextLong();
			N*=2;
			double ans=((Math.sqrt(1+(4*1*N))-1)/2);
			if(ans%1!=0.0) {
				System.out.println(-1);
			}else {
				System.out.println((int)ans);
			}
		}
	}
}
