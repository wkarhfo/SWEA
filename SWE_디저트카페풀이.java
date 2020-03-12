import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWE_디저트카페풀이 {
	static int N;
	static int max;
	static int sr;
	static int sc;
	static int[][] map;
	static boolean[][] visit;
	static HashSet<Integer> list = new HashSet<Integer>();
	static int[][] direction = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			max = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visit = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sr = i;
					sc = j;
					list.clear();
					for (int k = 0; k < N; k++) {
						Arrays.fill(visit[k], false);
					}
					dfs(i,j,0);
				}
			}

		}
		System.out.println("#"+tc+" "+(max==0)?-1:max);
	}

	private static void dfs(int r, int c, int dir) {
		visit[r][c]=true;
		list.add(map[r][c]);
		for(int d=dir;d<4;d++) {
			int nr=r+direction[d][0];
			int nc=c+direction[d][1];
			int cnt=list.size();
			
			if(nr==sr&&nc==sc&&cnt>=4) {
				if(cnt>max) {
					max=cnt;
					return;
				}
				
			}
			if(nr>-1&&nr<N&&nc>-1&&nc<N&&!visit[nr][nc]&&!list.contains(map[nr][nc])) {
				dfs(nr, nc, d);
			}
		}
		visit[r][c]=false;
		list.remove(map[r][c]);
		
		
	}

}
