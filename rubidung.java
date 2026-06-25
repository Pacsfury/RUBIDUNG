import java.util.Scanner;

public class Rubidung {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J"); //ANSI escape code to clear the screen and move the cursor to the top-left corner
        System.out.flush();
        
    }

    public static class Player {
        public int x;
        public int y;

        public Player() {
            this.x = 1;
            this.y = 4;
        }

        public char getCurrentTile(Level level) {
            if (y < 0 || y >= level.map.length || x < 0 || x >= level.map[0].length) {
                return '#'; // Treat out-of-bounds as spiky walls
            }
            
            return level.map[y][x];
        }
    }

    public static class Level {

        public int levelNumber;
        public char[][] map;

        public Level(int levelNumber) {

            this.levelNumber = levelNumber;
 
            switch (levelNumber) {
                case 1:
                    this.map = new char[][] {
                        {'#', '#', '#', '#', '#'},
                        {'#', '.', '.', '$', '#'},
                        {'#', '#', '#', '.', '#'},
                        {'#', '.', '.', '.', '#'},
                        {'#', '@', '#', '#', '#'}
                    };
                    break;

                case 2:
                    this.map = new char[][] {
                        {'#', '#', '#', '#', '#', '#'},
                        {'#', '.', '.', '.', '$', '#'},
                        {'#', '#', '#', '.', '#', '#'},
                        {'#', '.', '.', '.', '.', '#'},
                        {'#', '@', '#', '#', '#', '#'},
                        {'#', '#', '#', '#', '#', '#'}
                    };
                    break;

                case 3:
                    this.map = new char[][] {
                        {'#', '#', '#', '#', '#', '#', '#'},
                        {'#', '.', '.', '.', '#', '$', '#'},
                        {'#', '#', '.', '#', '#', '.', '#'},
                        {'#', '.', '.', '.', '.', '.', '#'},
                        {'#', '@', '#', '#', '#', '#', '#'},
                        {'#', '#', '#', '#', '#', '#', '#'}
                    };
                    break;
            
                case 4:
                    this.map = new char[][] {
                        {'#', '#', '#', '#', '#', '#', '#', '#'},
                        {'#', '.', '.', '.', '#', '.', '$', '#'},
                        {'#', '#', 'O', '#', '#', '.', 'O', '#'},
                        {'#', '.', '.', '.', '.', '.', '.', '#'},
                        {'#', '@', '.', '#', '#', '#', '#', '#'},
                        {'#', '#', '#', '#', '#', '#', '#', '#'}
                    };
                    break;
                default:
                    break;
            }
        }
    }


    public static boolean playLevel(Level level, Scanner scanner) {

        boolean win = false;
        Player player = new Player();

        for (int i = 0; i < level.map.length; i++) { // Print the map using nested loops (rows and columns)
            for (int j = 0; j < level.map[i].length; j++) {
                System.out.print(level.map[i][j] + " ");
            }
            System.out.println(); 
        }

        for (int turn = 0; turn < 10; turn++) { // Read and execute user commands
            String command = scanner.nextLine();

            if (turn == 0) {  //Clears the screen after first command (which is also cleaned to avoid people writing everything they need)
                clearScreen();
            }

            int oldX = player.x;
            int oldY = player.y;

            
            if (command.equals("w")) {

                System.out.println("Move up");
                player.y -= 1;
            } else if (command.equals("a")) {

                System.out.println("Move left");
                player.x -= 1;
            } else if (command.equals("s")) {

                System.out.println("Move down");
                player.y += 1;
            } else if (command.equals("d")) {

                System.out.println("Move right");
                player.x += 1;
            } else {

                System.out.println("Invalid command");
            }

            char currentTile = player.getCurrentTile(level);

            if (currentTile == '$') {
                System.out.println("You win!");
                win = true;
                break;
            } else if (currentTile == '#') {
                System.out.println("You hit a wall! Game over.");
                break;
            } else if (currentTile == 'O') {
                player.x = oldX;
                player.y = oldY;
            }
        }

        if (!win) { //If 10 moves are done, the game ends with a lose message
            System.out.println("Game over. Try again!");
        } 

        return win;
    }

    public static void main(String[] args) {
        int TOTAL_LEVELS = 4; 
        Scanner sc = new Scanner(System.in);
        boolean win = false;

        Level actualLevel = new Level(1);
        win = playLevel(actualLevel, sc);

        for (int level = 2; level < TOTAL_LEVELS + 1; level++) {

            if (win) {
                actualLevel = new Level(level);
                win = playLevel(actualLevel, sc);
            } else {
                break;
            }
        }

        clearScreen();
        System.out.println("Congratulations! You completed all levels!");
        sc.close();
    }
}
