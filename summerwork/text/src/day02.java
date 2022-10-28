//力扣：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
//深度优先搜索
public class day02 {
    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        day02 d1 = new day02();
        System.out.println(d1.exist(board, word));
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board[0] == null || board.length == 0 || board[0].length == 0 || word == null || word.equals("")) {
            return false;
        }
        char[] words = word.toCharArray();
        boolean[][] vist = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == words[0]) {
                    if (dfs(i, j, vist, board, words, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean dfs(int i, int j, boolean[][] vist, char[][] board, char[] words, int index) {
        if (words.length == index)
            return true;
        if (i < board.length && i >= 0 && j < board[0].length && j >= 0 && !vist[i][j] && board[i][j] == words[index]) {
            vist[i][j] = true;
            boolean res = dfs(i + 1, j, vist, board, words, index + 1)
                    || dfs(i, j + 1, vist, board, words, index + 1)
                    || dfs(i - 1, j, vist, board, words, index + 1)
                    || dfs(i, j - 1, vist, board, words, index + 1);
            vist[i][j] = false;
            return res;
        }
        return false;
    }
}
