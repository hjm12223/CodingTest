package programmers.kakao.Level2;

import java.util.*;

public class FriendsFourBlock {
    static boolean isContinue = true;
    static int[] dx = new int[]{0,1,0,-1};
    static int[] dy = new int[]{1,0,-1,0};

    static int removedBlock = 0;
    static char[][] charBoard;
    static boolean[][] isVisited;

    static Queue<Node> queue = new LinkedList<>();
    public static void main(String[] args) {
        int solution = solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"});
        // int solution = solution(4, 5, new String[] {"CCBDE", "AAADE", "AAABF", "CCBBF"});
        System.out.println("solution = " + solution);
    }
    public static int solution(int m, int n, String[] board) {
        /*
        좌측 상단 부터 2*2의 크기의 계산하여 우측하단까지 계산 만약 해당 범위안에 똑같은 Char 이 존재하면 해당블럭을 삭제 하여 다시 병합
        체크, 삭제, 압축 이렇게 반복
         */
        charBoard = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                charBoard[i][j] = board[i].charAt(j);
            }
        }

        while (isContinue) {
            isContinue = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    queue.offer(new Node(j, i));
                    check(n, m);
                }
            }
            compress();
        }
        return removedBlock;
    }

    private static void check(int n, int m) {
        isVisited = new boolean[m][n];
        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            isVisited[currNode.y][currNode.x] = true;
            for (int i = 0; i < 4; i++) {
                int nx = currNode.x + dx[i];
                int ny = currNode.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || charBoard[ny][nx] == ' ') continue;
                if (charBoard[currNode.y][currNode.x] == charBoard[ny][nx] && !isVisited[ny][nx]) {
                    isVisited[ny][nx] = true;
                    queue.offer(new Node(nx, ny));
                }
            }
        }
        delete();
    }

    private static void delete() {
        for (int i = 0; i < isVisited.length - 1; i++) {
            for (int j = 0; j < isVisited[i].length - 1; j++) {
                if (isValidBlock(i, j)) {
                    charBoard[i + 1][j] = '0';
                    charBoard[i][j] = '0';
                    charBoard[i + 1][j + 1] = '0';
                    charBoard[i][j + 1] = '0';
                }
            }
        }
        for (int i = 0; i < charBoard.length; i++) {
            for (int j = 0; j < charBoard[i].length; j++) {
                if (charBoard[i][j] == '0') {
                    charBoard[i][j] = ' ';
                    removedBlock++;
                }
            }
        }
    }

    private static boolean isValidBlock(int i, int j) {
        if (i + 1 >= isVisited.length || j + 1 >= isVisited[i].length) {
            return false;
        }
        return isVisited[i][j] && isVisited[i + 1][j] && isVisited[i + 1][j + 1] && isVisited[i][j + 1];
    }

    /*
[C, C, B, D, E]
[A, A, A, D, E]
[A, A, A, B, F]
[C, C, B, B, F]

[ ,  , B, D, E]
[ ,  , B, D, E]
[ ,  ,  , B, F]
[ ,  ,  , B, F]
 */
    private static void compress() {

        for (int j = 0; j < charBoard[0].length; j++) {
            for (int i = charBoard.length - 1; i >= 0; i--) { // 아래에서 위로 순회
                if (charBoard[i][j] == ' ') { // 빈 칸을 찾았을 경우
                    for (int k = i - 1; k >= 0; k--) {
                        if (charBoard[k][j] != ' ') {
                            charBoard[i][j] = charBoard[k][j]; // 빈 칸에 값을 옮김
                            charBoard[k][j] = ' '; // 이전 위치는 빈 칸으로 만듦
                            isContinue = true; // 압축이 발생했으니 한번더 수행
                            break; // 값을 찾았으므로 반복문 종료
                        }
                    }
                }
            }
        }
    }
    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}