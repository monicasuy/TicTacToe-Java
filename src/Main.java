import java.util.*;

public class Main {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, { '-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}, { '-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}};
        System.out.println("Want to play? (yes/no)");

        Scanner scanner = new Scanner(System.in);
        String play = scanner.next().toLowerCase();


        while (true) {
            printGameBoard(gameBoard);

            System.out.println("Enter a placement (1-9)");

            Scanner scan = new Scanner(System.in);
            int playerPosition = scan.nextInt();
            while (playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)) {
                System.out.println("Position taken, please choose another position");
                playerPosition = scan.nextInt();
            }

            Random rand = new Random();
            int cpuPosition = rand.nextInt(9) + 1;
            while (playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
                System.out.println("Position taken, please choose another position");
                cpuPosition = rand.nextInt(9) + 1;
            }

            getPosition(gameBoard, playerPosition, "player");
            getPosition(gameBoard, cpuPosition, "cpu");

            String result = getWinner();

            if (result.length() > 0) {
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }

        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            System.out.println(row);
        }
    }

    public static void getPosition(char[][] gameBoard, int position, String user) {
        char symbol = 'X';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);

        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;

        }
//        printGameBoard(gameBoard);

    }

    public static String getWinner () {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winningPositions = new ArrayList<>();
        winningPositions.add(topRow);
        winningPositions.add(midRow);
        winningPositions.add(botRow);
        winningPositions.add(leftCol);
        winningPositions.add(midCol);
        winningPositions.add(rightCol);
        winningPositions.add(cross1);
        winningPositions.add(cross2);

        for ( List list : winningPositions) {
            if (playerPositions.containsAll(list)) {
                return "Congrats, you're the winner";
            } else if (cpuPositions.containsAll(list)) {
                return "Sorry, the computer won!";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "it's a tie!";
            }
        }
        return "";
    }
}