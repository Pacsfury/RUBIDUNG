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


When the first command is inputted, the map hides, and your only help is your memory.


Actual commands are:


- **W**: Move up
- **A**: Move left
- **S**: Move down
- **D**: Move right


If the correct sequence is inputted, the game halts and you win, but if you do more turns than maximum, you lose!


## Updates Log


09/05/2026:
- Add first level
- Add basic logic
- Add four commands (w, a, s, d)
- Add player and reward


10/05/2026:
- Make win detections better
- Make small optimitzations and code unification
- Now walls kill you!
- Add level 2
- Add modularity to the game
- Start XP implementation
- Optimize code deeply
    - Delete redundant class: World
    - Implement manual boundary checking to prevent ArrayIndexOutOfBounds without try-catch
    - Unify Scanner use to be only closed and opened one time
    - Replaced external clean console with ANSI escape code
    - Refactorize the core to use object references instead of duplicating data structures.


