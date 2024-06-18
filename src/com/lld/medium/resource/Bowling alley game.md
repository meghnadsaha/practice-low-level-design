
---

## Low-Level Design: Bowling Alley Game


### Problem Statement

**Description:**  
Design a system to simulate a bowling alley game where players' scores are maintained and shown by the system, and a winner is declared at the end of the game. Multiple games can be played in parallel on multiple free lanes.

**Rules:**
- A game consists of ten sets.
- In each set, the player has two opportunities to knock down ten pins.
- The score for a set is the total number of pins knocked down, plus bonuses for strikes and spares.
- A spare is when the player knocks down all ten pins in two tries. If there is a spare, the player gets 5 bonus points.
- A strike is when the player knocks down all ten pins on their first try. If there is a strike, the player gets 10 bonus points.
- In the final set, a player who rolls a spare or a strike is allowed to roll extra balls to complete the set. However, only a maximum of three balls can be rolled in the final set.

**Admin Configurations:**
- Number of parallel games
- Number of rounds in a single game
- Number of players in a game
- Player turn strategy (who will get the next chance to play)
- Scoring Strategy
- Number of retries per user in a Round (once fixed at the beginning of the game, should be fixed)


---

Here is a simplified example of a class diagram and a sequence diagram for the bowling alley game:

### Class Diagram

<img src="https://github.com/meghnadsaha/practice-low-level-design/blob/master/src/com/lld/medium/resource/Bowling%20Alley%20Game-class%20diagram.png?raw=true"/>

```plantuml
@startuml

class Game {
    - rounds: Round[]
    - users: User[]
    - strategy: Strategy
    + startGame(): void
    + endGame(): void
}

class Round {
    - retries: Retry[]
    + rollBall(): void
    + calculateScore(): int
}

class User {
    - name: string
    - score: int
}

interface Strategy {
    + calculateScore(): int
}

class SpareStrategy {
    + calculateScore(): int
}

class StrikeStrategy {
    + calculateScore(): int
}

class Retry {
    + retry(): void
}

Game "1" -- "n" Round
Round "1" -- "n" Retry
Game "1" -- "n" User
Game "1" -- "1" Strategy
Strategy <|-- SpareStrategy
Strategy <|-- StrikeStrategy

@enduml
```

### Sequence Diagram
<img src="https://github.com/meghnadsaha/practice-low-level-design/blob/master/src/com/lld/medium/resource/Bowling%20Alley%20Game-Sequence%20Diagram.png?raw=true" width="100%"/>

