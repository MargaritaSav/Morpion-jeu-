import java.util.Random;

public class Logic {
    static int maxSize = 10;

    static int SIZE = 3;
    static int DOTS_TO_WIN = 3;


    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '*';
    static char playerOne = DOT_X;
    static char playerTwo = DOT_O;

    static boolean playerOneFinished;
    static boolean playerTwoFinished;


    static char[][] map;

    static Random random = new Random();

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void aiTurn() {
        int x = -1, y = -1;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(j, i)) {
                    map[i][j] = playerOne;
                    if (checkWin(playerOne)) {
                        x = j;
                        y = i;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }
        if (x == -1 && y == -1) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(x, y));
        }

        map[y][x] = playerTwo;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static boolean checkWin(char symbol) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkGorizont(i, j, symbol) || checkVertical(i, j, symbol)
                        || checkDiogonal1(i, j, symbol) || checkDiogonal2(i, j, symbol)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkGorizont(int x, int y, char symbol) {
        if (x < 0 || y < 0 || x + DOTS_TO_WIN > SIZE) {
            return false;
        }
        int k = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (map[i + x][y] == symbol) {
                k++;
            }
        }
        return k == DOTS_TO_WIN;
    }

    public static boolean checkVertical(int x, int y, char symbol) {
        if (x < 0 || y < 0 || y + DOTS_TO_WIN > SIZE) {
            return false;
        }
        int k = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (map[x][y + i] == symbol) {
                k++;
            }
        }
        return k == DOTS_TO_WIN;
    }

    public static boolean checkDiogonal1(int x, int y, char symbol) {
        if (x < 0 || y < 0 || x + DOTS_TO_WIN > SIZE || y + DOTS_TO_WIN > SIZE) {
            return false;
        }
        int k = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (map[x + i][y + i] == symbol) {
                k++;
            }
        }
        return k == DOTS_TO_WIN;
    }

    public static boolean checkDiogonal2(int x, int y, char symbol) {
        if (x < 0 || x + DOTS_TO_WIN > SIZE || y + 1 - DOTS_TO_WIN < 0) {
            return false;
        }
        int k = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (map[x + i][y - i] == symbol) {
                k++;
            }
        }
        return k == DOTS_TO_WIN;
    }

    public static boolean isFull() {
        int k = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    k++;
                }
            }
        }
        return k == 0;
    }

}
