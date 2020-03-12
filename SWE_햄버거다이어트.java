import java.util.Scanner;

public class SWE_햄버거다이어트 {
	static int MAX;
	static Food[] foodlist;
	static boolean[] visit;
	static int N;
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int input=sc.nextInt();
		for(int tc=1;tc<=input;tc++) {
			N=sc.nextInt();
			MAX=sc.nextInt();
			foodlist=new Food[N];
			visit=new boolean[N];
			for(int i=0;i<N;i++) {
				foodlist[i]=new Food(sc.nextInt(),sc.nextInt());
			}
			result=Integer.MIN_VALUE;
			dfs(0);
			System.out.printf("#%d %d\n",tc,result);
			
		}

	}
	static int result;
	static void dfs(int depth) {
		if(depth==N) {
			int scsum=0;
			int calsum=0;
			for(int i=0;i<N;i++) {
				if(visit[i]==true) {
					scsum+=foodlist[i].score;
					calsum+=foodlist[i].cal;
				}
			}
			//System.out.println("칼로리:"+calsum+", 점수: "+scsum);
			if(calsum<=MAX&&result<=scsum) {
				result=scsum;
			}
			return;
		}
		visit[depth]=true;
		dfs(depth+1);
		visit[depth]=false;
		dfs(depth+1);
	}
	static class Food{
		int score;
		int cal;
		public Food(int score, int cal) {
			this.score = score;
			this.cal = cal;
		}
	}
}
