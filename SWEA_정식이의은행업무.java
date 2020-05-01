import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_정식이의은행업무 {
	static String two;
	static String third;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			two = br.readLine();
			third = br.readLine();

			for (int i = 0; i < two.length(); i++) {
				String temp = "";
				if (two.charAt(i) == '1') {
					for (int j = 0; j < two.length(); j++) {
						if (j == i) {
							temp += "0";
						} else {
							temp += two.charAt(j);
						}
					}
				} else if (two.charAt(i) == '0') {
					for (int j = 0; j < two.length(); j++) {
						if (j == i) {
							temp += "1";
						} else {
							temp += two.charAt(j);
						}
					}
				}
				int resulttwo = Integer.parseInt(temp, 2);
				for (int j = 0; j < third.length(); j++) {
					for (int k = 0; k < 3; k++) {
						temp = "";
						if (Character.getNumericValue(third.charAt(j)) == k)
							continue;
						for (int m = 0; m < third.length(); m++) {
							if (m == j) {
								temp += k;
							} else {
								temp += third.charAt(m);
							}
						}
						if(resulttwo==Integer.parseInt(temp,3)) {
							System.out.println("#"+tc+" "+resulttwo);
						}
					}
				}

			}

		}
		
	}

}
