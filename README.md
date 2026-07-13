# RUBIDUNG
## A memory dungeon CLI game
---


# Features
**Rubidung** is a dungeon memory game, memory-based.


When the game starts, the map is shown. Diferent tiles mean different things:


| **Symbol** | **Meaning**                |
|------------|----------------------------|
|**#**       |A wall, recovered of spikes!|
|**.**       |Free space to walk          |
|**$**       |The treasure you want!      |
|**@**       |You!                        |
|**O**       |A wall, without spikes!     |


When the first command is inputted, the map hides, and your only help is your memory.


Actual commands are:


- **W**: Move up
- **A**: Move left
- **S**: Move down
- **D**: Move right


If the correct sequence is inputted, the game halts and you win, but if you do more turns than maximum, you lose!

## How To Play The Multiplayer Guess The Number? (and future online gamemode)
When executing the file, it will ask you if you want to play online or offline. To mark online, write an N and press enter.

Then, if you want to be the host (the number guesser), write a 0 when asking for IP.

If you want to be the client, because you or somebody else has already created the room, enter `localhost` or another IP (Server listents to :8080 port). Don't put yourself http:// nor :8080. The code does it iself.

After that, the client puts a random number, and the host keeps trying to guess it.

If you want the backend to be better, I would love to see your contribution [here](https://github.com/Pacsfury/NetworkLib-Backend).

## Updates Log

09/05/2026: 
- **Add**: first level
- **Add**: basic logic
- **Add**: four commands (w, a, s, d)
- **Add**: player and rewards symbols


10/05/2026:
- **Change**: make win detections better
- **Change**: make small optimitzations and code unification
- **Change**: now walls kill you!
- **Add**: level 2
- **Add**: modularity to the game
- **Start**: XP implementation
- **Change**: optimize code deeply
    - Delete redundant class `World`
    - Implement manual boundary checking to prevent `ArrayIndexOutOfBounds` without try-catch
    - Unify Scanner use to be only closed and opened one time
    - Replace external clean console with ANSI escape code
    - Refactorize the core to use object references instead of duplicating data structures.


11/05/2026:
- **Add**: level 3
- **Change**: make better level running system
- **Fix**: various bug fixes:
    - Skipping level 1
    - Continuing after dying
    - Congratulation message now shown correctly
    - Level 3 now possible
- **Change**: small optimitzation
- **Change**: updated code for legibility and conventions
- **Add**: new tile: "O"
- **OUT**: stable version 0.1.0:0


12/05/2026:
- **Fix**: level 4

16/5/2026:
- **OUT**: C++ edition for version stable 0.1.0

13/7/2026:
- **ADD**: multiplayer guess the number (future RUBIDUNG online game). The multiplayer is just for texts. You can find the backend source code at [Github](https://github.com/Pacsfury/NetworkLib-Backend). I recommend waiting or contributing to the backend until it's enough mature to be able to work with RUBIDUNG.

