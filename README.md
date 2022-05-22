# mario-s1-2022
# Requirement 4

**Title**: Blink action + Patrol behaviour

**Description**: When the player steps on BlinkingTower, the player is able to blink to a location that is several steps (predefined) away within one turn. There is also another Actor called Luigi who exhibits a patrol behaviour that moves at the set path, back and forth.

**Classes created**:
- BlinkAction: an Action used by the player to blink to a location that is several steps (predefined) away within one turn.

- BlinkingTower: a HighGround where the player can jump onto to blink to a location that is several steps (predefined) away.

- Luigi: an Actor who will patrol at the set path, back and forth.

- PatrolBehaviour: a Behaviour that enables an Actor to patrol at the set path, back and forth.

**Explanation why it adheres to SOLID principles**:
- BlinkAction is an extension from Action abstract class, which allows the player to blink to a location that is several steps (predefined) away within one turn. BlinkAction satisfies the **Single Responsibility Principle**, because it only focuses on the blinking process of the player from one location to another. This is to separate the action of blinking from other actions, which have their own implementations.

- Luigi is an actor who will patrol at the set path, back and forth. Since the characteristics of Luigi is so much different with other actors (e.g. Toad, PrincessPeach, etc.), a new Luigi class is created and extends from Actor class as the general characteristics of actor is defined in the Actor abstract class. Luigi class satisfies the **Open-Closed Principle** as Actor is an abstract class closed for modification and the specific characteristics for Luigi only appear in Luigi class. This means that we can extend the implementations and characteristics of Luigi without modifying the Actor abstract class.

- PatrolBehaviour provides an actor a patrolling behavioural property, which allows it to move at the set path, back and forth. PatrolBehaviour also satisfies the **Single Responsibility Principle**, because it only provides an actor the specific patrolling behaviour and not other behaviours, which have their own implementations.

- BlinkingTower is a HighGround where the player can jump onto to blink to a location that is several steps away. Since BlinkingTower has this extra functionality of blink, apart from the original functionality of jump in the HighGround abstract class where it extends from. Therefore, BlinkingTower satisfies the **Open-Closed Principle** as the functionalities of BlinkingTower have been extended without modifying the High Ground abstract class.


| Requirements                                                                                                            | Features (HOW) / Your Approach / Answer                                                                                                                               |
| ----------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Must use at least two (2) classes from the engine package                                                               | We use three classes from the engine package: Action, Ground and Actor. BlinkAction class extends Action, BlinkingTower class extends HighGround which extends Ground and Luigi class extends Actor. |
| Must use/re-use at least one(1) existing feature (either from assignment 2 and/or fixed requirements from assignment 3) | Existing features used: <ul><li> Jump feature (assignment 2): The player needs to jump onto the BlinkingTower before he sees the action to blink to a location that is several steps away.<br> <br> <li> Reset game (assignment 2): When the game is reset, Luigi will move back to its initial position in the next round.</ul>                                                                                                                                                                |
| Must use existing or create new abstractions (e.g., abstract or interface, apart from the engine code)                  | Existing abstract class used: <ul><li>HighGround: extended by BlinkingTower class so that the player will need to jump onto it before seeing the action of blinking to a location that is several steps away.</ul> Existing interfaces used: <ul><li> Resettable: Implemented by Luigi class and override resetInstance method to provide RESET status to Luigi, so that Luigi will move back to its initial position in the next round when the game is reset.<br><br> <li> Behaviour: Implemented by PatrolBehaviour class and override getAction method to return MoveActorAction, which is used by Luigi to patrol at the set path, back and forth. </ul>                                                                                                                                                                   |
| Must use existing or create new capabilities                                                                            | Existing capability used: <ul><li>RESET: When the game is reset, this status will be given to Luigi, so that it will move back to its initial position in the next round. </ul>                                                                                                                                                                      |

---

# Requirement 5

**Title**: Yoshi as adventure partner

**Description**: When the game starts, Yoshi who is beside the player will follow the player wherever the player goes. When the player takes damage from the enemies, Yoshi will also heal the player with a constant hitpoints of 5.

**Classes created**:
- Yoshi: an Actor who acts as an adventure partner for the player and will follow the player wherever he goes.

- HealBehaviour: a Behaviour used by Yoshi to get HealAction when the player takes damage from the enemies

- HealAction: an Action used by Yoshi to heal the player when the player takes damage from the enemies.

**Explanation why it adheres to SOLID principles**:
- HealAction is an extension from Action abstract class, which allows an actor to heal a target with a constant amount of hitpoints. HealAction satisfies the **Single Responsibility Principle**, because it only focuses on the healing process between an actor and a target. This is to separate the action of healing from other actions, which have their own implementations.

- HealBehaviour provides an actor a healing behavioural property, which allows it to heal a target with a constant amount of hitpoints. HealBehaviour also satisfies the **Single Responsibility Principle**, because it only provides an actor the specific healing behaviour and not other behaviours, which have their own implementations.

- Yoshi is an actor who will follow the player wherever he goes and heal the player when the player takes damage from the enemies. Since the characteristics of Yoshi is so much different with other actors (e.g. Toad, Luigi, etc.), a new Yoshi class is created and extends from Actor class as the general characteristics of actor is defined in the Actor abstract class. Yoshi class satisfies the **Open-Closed Principle** as Actor is an abstract class closed for modification and the specific characteristics for Yoshi only appear in Yoshi class. This means that we can extend the implementations and characteristics of Yoshi without modifying the Actor abstract class.


| Requirements                                                                                                            | Features (HOW) / Your Approach / Answer                                                                                                                               |
| ----------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Must use at least two (2) classes from the engine package                                                               | We use two classes from the engine package: Actor and Action. Yoshi class extends Actor and HealAction class extends Action. |
| Must use/re-use at least one(1) existing feature (either from assignment 2 and/or fixed requirements from assignment 3) | Existing feature used: <ul><li> Reset game feature from assignment 2 is used. When the game is reset, Yoshi will move back to its initial position in the next round.</ul>                                                                                                                                                                |
| Must use existing or create new abstractions (e.g., abstract or interface, apart from the engine code)                  | Existing interfaces used: <ul><li> Resettable: Implemented by Yoshi class and override resetInstance method to provide RESET status to Yoshi, so that Yoshi will move back to its initial position in the next round when the game is reset.<br><br> <li> Behaviour: Implemented by HealBehaviour class and override getAction method to return HealAction, which is used by Yoshi to heal the player when the player takes damage from the enemies. </ul>                                                                                                                                                                   |
| Must use existing or create new capabilities                                                                            | Existing capabilities used: <ul><li> RESET: When the game is reset, this status will be given to Yoshi, so that it will move back to its initial position in the next round.<br><br> <li> HOSTILE_TO_ENEMY: This status is used to retrieve the player in the map, which will then be used to calculate the Manhattan distance between the player and Yoshi. Note: Yoshi will only follow the player when the player is nearby to Yoshi.<br><br><li> CAN_ENTER_HIGH_GROUND: This status is given to Yoshi, so that Yoshi is able to follow the player even if the player is currently on a high ground.</li></ul> New capability used: <ul><li>INJURED: When the player takes damage from the enemies, the player will be given INJURED status. This status will be checked by Yoshi before healing the player.</ul>                                                                                                                                                                     |
