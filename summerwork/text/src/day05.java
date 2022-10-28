public class day05 {
    public static void main(String[] args) {
        day05 dy5=new day05();
        String str=dy5.convert("PAYPALISHIRING",4);
        System.out.println(str);
    }
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        int n = s.length();
        if (n == 1)
            return s;
        int x = 2 * numRows - 2;
        int p = n / numRows + 1;
        char[][] chars = new char[p + 1][x + 1];
        int t = 0;
        int y = 0;
        for (int i = 0; i < n; i++) {
            if (y >= x) {
                y %= x;
                t++;
            }
            chars[t][y] = s.charAt(i);
            y++;
        }
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < x; j++) {
                if (chars[i][j] == '\u0000') {
                    chars[i][j] = '1';
                }
            }
        }
        char[] ans = new char[n + 1];
        t = 0;
        for (int i = 0; i < p; i++) {
            if (chars[i][0] != '1')
                ans[t++] = chars[i][0];
        }
        for (int i = 1; i <= x - 1; i++) {
            y = x - i;
            if (i < y) {
                for (int j = 0; j < p; j++) {
                    if (chars[j][i] != '1')
                        ans[t++] = chars[j][i];
                    if (chars[j][y] != '1')
                        ans[t++] = chars[j][y];
                }
            } else if (i == y) {
                for (int j = 0; j < p; j++) {
                    if (chars[j][i] != '1')
                        ans[t++] = chars[j][i];
                }
            } else {
                break;
            }
        }
        String str11 = new String(ans);
        String str12 = str11.substring(0, t);
        return str12;
    }
}
