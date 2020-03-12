import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWE_숫자이어붙이기 {
	static int result;
	static int dh[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static String[][] arr;
	static Set<String> set;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; tc++) {

			arr = new String[4][4];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					arr[i][j] = st.nextToken();
				}
			}
			set=new HashSet<>();
			for (int i = 0; i < 4; i++) {
				for(int j=0;j<4;j++) {
					dfs(i,j,0," ");
				}
			}
			System.out.println("#" + tc + " " + set.size());
		}
	}

	private static void dfs(int h, int y, int cnt,String s) {
		if(cnt==7) {
			set.add(s);
			return;	
		}
		for(int k=0;k<4;k++){
			int ah=h+dh[k];
			int ay=y+dy[k];
			if(ah<0||ah>=arr.length||ay<0||ay>=arr[0].length)
				continue;
			dfs(ah,ay,cnt+1,s+arr[ah][ay]);
		}
	}

}
