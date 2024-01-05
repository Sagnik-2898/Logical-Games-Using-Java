import java.util.Random;
import java.util.Scanner;

public class Game2048 {
    private static final int SIZE = 4;
    private int[][] board;
    private Random random;
    private boolean isGameOver;

    public Game2048() {
        board = new int[SIZE][SIZE];
        random = new Random();
        isGameOver = false;
        addTile();
    }

    public void playGame() {
        while (!isGameOver) {
            printBoard();
            handleMove();
            addTile();
            checkGameOver();
        }
        System.out.println("Game Over! Your final score: " + calculateScore());
    }

    private void printBoard() {
        System.out.println("2048 Game");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private void handleMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter move (W/A/S/D): ");
        char move = scanner.next().charAt(0);
        switch (move) {
            case 'W':
            case 'w':
                moveUp();
                break;
            case 'A':
            case 'a':
                moveLeft();
                break;
            case 'S':
            case 's':
                moveDown();
                break;
            case 'D':
            case 'd':
                moveRight();
                break;
            default:
                System.out.println("Invalid move! Use W/A/S/D");
        }
    }

    private void moveUp() {
        for (int col = 0; col < SIZE; col++) {
            for (int row = 1; row < SIZE; row++) {
                if (board[row][col] != 0) {
                    for (int i = row; i > 0; i--) {
                        if (board[i - 1][col] == 0) {
                            board[i - 1][col] = board[i][col];
                            board[i][col] = 0;
                        } else if (board[i - 1][col] == board[i][col]) {
                            board[i - 1][col] *= 2;
                            board[i][col] = 0;
                        }
                    }
                }
            }
        }
    }

    private void moveLeft() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 1; col < SIZE; col++) {
                if (board[row][col] != 0) {
                    for (int j = col; j > 0; j--) {
                        if (board[row][j - 1] == 0) {
                            board[row][j - 1] = board[row][j];
                            board[row][j] = 0;
                        } else if (board[row][j - 1] == board[row][j]) {
                            board[row][j - 1] *= 2;
                            board[row][j] = 0;
                        }
                    }
                }
            }
        }
    }

    private void moveDown() {
        for (int col = 0; col < SIZE; col++) {
            for (int row = SIZE - 2; row >= 0; row--) {
                if (board[row][col] != 0) {
                    for (int i = row; i < SIZE - 1; i++) {
                        if (board[i + 1][col] == 0) {
                            board[i + 1][col] = board[i][col];
                            board[i][col] = 0;
                        } else if (board[i + 1][col] == board[i][col]) {
                            board[i + 1][col] *= 2;
                            board[i][col] = 0;
                        }
                    }
                }
            }
        }
    }

    private void moveRight() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = SIZE - 2; col >= 0; col--) {
                if (board[row][col] != 0) {
                    for (int j = col; j < SIZE - 1; j++) {
                        if (board[row][j + 1] == 0) {
                            board[row][j + 1] = board[row][j];
                            board[row][j] = 0;
                        } else if (board[row][j + 1] == board[row][j]) {
                            board[row][j + 1] *= 2;
                            board[row][j] = 0;
                        }
                    }
                }
            }
        }
    }

    private void addTile() {
        int emptyCells = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    emptyCells++;
                }
            }
        }

        if (emptyCells > 0) {
            int index = random.nextInt(emptyCells);
            int value = (random.nextInt(2) + 1) * 2; // Either 2 or 4
            emptyCells = 0;

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == 0) {
                        if (emptyCells == index) {
                            board[i][j] = value;
                            return;
                        }
                        emptyCells++;
                    }
                }
            }
        }
    }

    private void checkGameOver() {
        // Check if there are any adjacent equal tiles or if there are still empty cells
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    return; // Game is not over as there is an empty cell
                }

                if (j < SIZE - 1 && board[i][j] == board[i][j + 1]) {
                    return; // Game is not over as there are adjacent equal tiles horizontally
                }

                if (i < SIZE - 1 && board[i][j] == board[i + 1][j]) {
                    return; // Game is not over as there are adjacent equal tiles vertically
                }
            }
        }

        isGameOver = true;
    }

    private int calculateScore() {
        int score = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                score += board[i][j];
            }
        }
        return score;
    }

    public static void main(String[] args) {
        Game2048 game = new Game2048();
        game.playGame();
    }
}
