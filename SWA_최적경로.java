import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * NextPermitation -->현재 순열의 상태에서 사전순으로 나열했을 때 그 다음 단계의 순열생성 최종목표: 가장 큰 순열 생성
 * 1.뒤쪽부터 탐색하며 꼭대기 찾기 (i-1가 i보다 작은 값!!) i=0 이면 순열 생성 x 
 * 2.뒤쪽에서 탐색하며 교환할 큰 값(j)을 찾기 (i-1값과 뒤에서부터 비교) 
 * 3.i-1,j 위치값 교환 
 * 4.i값부터 마지막까지 오름차순으로 정렬 맨처음 순열부터 시작하기 때문에
 * 시작할때 가장작은 순열의 형태를 만들어준후 시작해야됨!! 중복된 값이 있을경우 매우 효과적!! ex) 1 1 3
 *
 */

public class SWA_최적경로 {
	static Data[] arr;
	static boolean[] visit;
	static int person;
	static int SUM;

	static int MIN;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
//			person=Integer.parseInt(br.readLine());
			person = Integer.parseInt(st.nextToken());
			SUM = 0;
			arr = new Data[person + 2];
			visit = new boolean[person + 2];

			st = new StringTokenizer(br.readLine());
			arr[0] =  new Data(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			arr[arr.length - 1] =  new Data(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));

			for (int i = 1; i <= person; i++) {
				arr[i] = new Data(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			MIN = Integer.MAX_VALUE;
			dfs2(0, 0);
			System.out.println("#" + tc + " " + MIN);
		}
	}

	private static void dfs2(int cnt, int depth) {
		int waysum = 0;
		if (cnt == person) {
			int last = SUM + Math.abs(arr[arr.length - 1].h - arr[depth].h)
					+ Math.abs(arr[arr.length - 1].y - arr[depth].y);
			
			if (MIN > last)
				MIN = last;
			return;
		}
		for (int i = 1; i <= person; i++) {
			if (!visit[i]) {
				waysum = Math.abs(arr[depth].h - arr[i].h) + Math.abs(arr[depth].y - arr[i].y);
				if (SUM + waysum > MIN)
					continue;
				SUM += waysum;
				visit[i] = true;
				dfs2(cnt + 1, i);
				SUM -= waysum;
				visit[i] = false;
			}
		}
	}


	static class Data {
		int h;
		int y;

		public Data(int h, int y) {
			this.h = h;
			this.y = y;
		}

	}
}
//================================================================================================
/*import java.util.Scanner;

class Pair {
    int x, y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class SWEA1247최적경로 {
    static int customer;
    static int result;
    static int min;
    static Pair[] arr;
    static int[] check;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        for (int t = 1; t <= testcase; t++) {
            result = 0;
            min = Integer.MAX_VALUE;
            customer = sc.nextInt();
            arr = new Pair[customer + 2];
            check = new int[customer + 2];
            arr[0] = new Pair(sc.nextInt(), sc.nextInt()); // start
            arr[customer + 1] = new Pair(sc.nextInt(), sc.nextInt()); // end
            for (int i = 1; i <= customer; i++) {
                arr[i] = new Pair(sc.nextInt(), sc.nextInt());
            }
            check[0] = 1;
            find(0, 0, 0);

            System.out.println("#" + t + " " + min);
        }

    }

    private static void find(int index, int depth, int result) {
        if(min<=result) {    //여기 시간 줄이기에 제일 중요
            return;
        }
        if (depth == customer) {
            result += Math.abs(arr[index].x - arr[customer + 1].x) + Math.abs(arr[index].y - arr[customer + 1].y);
            if (min > result) {
                min = result;
            }
        }

        for (int i = 1; i <= customer; i++) {
            if (check[i] == 0) {
                // result = Math.abs(arr[index].x- arr[i].x)+ Math.abs(arr[index].y- arr[i].y);
                check[i] = 1;
                find(i, depth + 1, result + Math.abs(arr[index].x - arr[i].x) + Math.abs(arr[index].y - arr[i].y));
                check[i] = 0;

            }
        }
    }

}*/