import java.util.Scanner;

public class rubidung {

    public static void clearScreen() { // Clear the console screen

        System.out.print("\033[H\033[2J");
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
                return '#'; // Treat out-of-bounds as walls
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
            
                default:
                    break;
            }
        }
    }


    public static void playLevel(Level level, Scanner scanner) {
        boolean win = false;

        Level level1 = level;

        // World world = new World(level1);

        Player player = new Player();


        for (int i = 0; i < level1.map.length; i++) {                 // Print the map using nested loops (rows and columns)
            for (int j = 0; j < level1.map[i].length; j++) {
                System.out.print(level1.map[i][j] + " ");
            }
            System.out.println(); 
        }

        for (int j = 0; j < 10; j++) { // Read and execute user commands


            String command = scanner.nextLine();

            if (j == 0) {        //Clears the screen after first command (which is also cleaned to avoid people writing everything they need)
                clearScreen();
            }

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


            char currentTile = player.getCurrentTile(level1);

            if (currentTile == '$') {
                System.out.println("You win!");
                win = true;
                break;


            } else if (currentTile == '#') {
                System.out.println("You hit a wall! Game over.");
                break;
            }

        }

        if (!win) { //If 10 moves are done, the game ends with a lose message
            System.out.println("Game over. Try again!");
            
        } 

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        Level level_act = new Level(1);
        playLevel(level_act, sc);

        level_act = new Level(2);
        playLevel(level_act, sc);

        sc.close();
        
    }
}
