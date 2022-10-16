# designosouls Instruction

## During the project:
1. Read and understand UML design documentation for an existing Java system

2. Proposed a design for additional functionality for this system

3. Created UML class diagrams and interaction diagrams to document my design, using a UML drawing tool such as UMLet or Visual Paradigm – you are free to choose which one

4. Wrote a design rationale evaluating my proposed design and outlining some alternatives

5. Used git to manage my team’s files and documents **(Could find all necessary documents in docs folder)**

# General background
You will be working on a text-based “rogue-like” game. Rogue-like games are named after the first such program: a fantasy game named Rogue. They were very popular in the days before home computers were capable of elaborate graphics and still have a small but dedicated following. If you would like more information about rogue-like games, a good site is http://www.roguebasin.com/. The initial codebase will be available in the repository mentioned above. It includes a folder containing design documents for the system.

### Lore of the Game
We are inspired by the Dark Souls III game. We may use several similar names (characters, items, locations) and concepts. The purpose of using an actual game’s concepts is to help students understand the features that may require imagination. Lastly, we believe it can bring fun while working on the assignments. All of the linked game contents, videos, and images belong to the respective owners and are subject to copyright. We mainly use the concepts for educational purposes and provide credit to the original creators accordingly. 

In this game, you are a Player that has risen from the dead to slay enemies, get strong, and kill bosses (Lords of Cinders).

## Requirement 1: Player and Estus Flask.
You need to show the player's health/hit points in the console, such as “Unkindled (30/100)”. Initially, the Player holds a Broadsword (@see Weapons). The Player also has a unique health potion inside the inventory that can be refilled if you rest. This health potion is called Estus Flask (Health Potion). It has three charges, and each charge will heal the Player with 40% of the maximum hit points. The Player cannot drop the Estus Flask. You need to display the number of charges in the console (in the list of actions) whenever the Player wants to drink it. For instance, “a: Unkindled drinks Estus Flask (2/3)” where `a` is the hotkey to execute the corresponding action (i.e. drinking Estus Flask).

## Requirement 2: Bonfire
The Player starts at the Firelink Shrine (it will be the name of Bonfire). It is an area in the middle of the map that is surrounded by the walls (displayed as “#”, the player or any other character cannot cross a wall), and its ground is made of Floors (one Floor is displayed as an underscore “_”). You can see a Bonfire (displayed as “B”) sitting exactly in the middle. Only a Player can interact with Bonfire. At the moment, the Bonfire only has one action, and we call it “Rest at Firelink Shrine bonfire”.

When the Player chooses to rest, all of the following `RESET` features are executed (in one turn):

Refill Player’s health/hit points to the maximum.

Refill Estus Flask to maximum charges.

Reset the enemies’ position, health, and skills (@see Enemies): in this case, reposition them at the initial locations, and refill their hit points back to maximum. However, all Undead (Hollow Soldier) do not need this re-positioning. They will be removed/wiped out from the map immediately. 

## Requirement 3: Souls (a.k.a. Money)
Souls are the currency of this game, and the Player has 0 souls at the start of the game. When the Player slays/kills enemies (@see Enemies), the Player gains a certain number of souls from them.

## Requirement 4: Enemies
In this game, you will have several enemies. When an enemy notices that the Player is in its surrounding (adjacent squares), it will follow the Player and starts to attack Player whenever possible. For the sake of simplicity, once the enemies are killed, they are completely removed from the game (i.e., no corpses around). You need to show hitpoints, maximum hit points, and the weapon that it equips in the console history log.

Enemies cannot enter the Firelink Shrine because they cannot enter the Floor.

Enemies cannot attack each other.

Enemies can randomly use active skills from a weapon.

**1. Undead (Hollow soldier)**: 
The Undead will spawn from the Cemetery (@see Terrains). An Undead starts with and has 50 maximum hit points, and it cannot be equipped with any weapons. It walks around aimlessly and will attack the Player if it stands next to it (adjacent squares). When it detects a Player, it will follow the Player until the Player dies/rest at Bonfire. Its base attack points are 20 hit points and print either ‘thwacks’ or ‘punches’. When the Player kills it, the Player gains 50 souls.

When it is not under attack, or not following a Player, the undead has a 10% chance to die instantly every turn, and they are immediately removed from the map. Dying this way WILL NOT increase the Player’s souls level. (NOTE: We merely use this feature to clean up the map).

**2. Skeleton**: 
You need to place several Skeletons (4 to 12 skeletons) manually and anywhere in the game map. Please calibrate the number of skeletons as much as you need. A skeleton needs to know its initial location to reset its position when the world gets reset (@see Bonfire). Skeletons walk around and follow the Player (plus attack) if the Player is within its radius (i.e., adjacent squares).

This enemy starts with and has 100 maximum hit points. The Skeleton carries one random weapon. The attack hit-rate point & damage will follow its weapon’s description. When it truly dies, it gives 250 souls to the Player. When it dies for the first time, it can resurrect itself with a 50% success rate that heals to maximum hit points. It cannot revive more than once (in other words, remove it from the game once it dies again).

**3. Yhorm the Giant (Lord of Cinder)**:
It is the first advanced enemy/boss in the game. It starts and has a maximum of 500 hit points. It holds a Yhorm’s Great Machete (@see Weapons). It will stand still in the room/hall that is provided in the base code. This boss is weak to Storm Ruler (@see Weapons). It has the following unique behaviour: 

  Ember Form (Second Phase): When Yhorm the Giant’s health reaches below half of its maximum hit points (< 50% HP), it enrages and its weapon becomes much more effective - its hit rate increases (@see Weapons for the detail). You must print a suitable message when Yhorm is entering this phase. 
  
  Other enemies cannot enter this room/hall due to floors that are placed at the entrance. When Yhorm the Giant is killed:

    Print an appropriate message in the console, such as “LORD OF CINDER FALLEN”.

    Give 5000 souls to the Player

    (OPTIONAL) Drop “Cinders of a Lord” item: the Player can drop it in the Firelink Shrine to continue the story (for Assignment 3, now, it does nothing).
  
## Requirement 5: Terrains (Valley and Cemetery)

There are many valleys and cemeteries.

Valley: We provided several valleys in the map (with the display character “+”). When the player steps on it, it instantly kills the Player (you can imagine that the Player falls from the cliff and the Player receives a lot of damage) (@see Soft Reset/Dying). Other actors (allies or enemies) cannot step on it.

Cemetery: Each cemetery has a 25% success rate to spawn/create Undead (Hollow soldier)(@see Enemies) at every turn. Please place several cemeteries (at least five) in the game map to spawn a lot of undead in the game.

## Requirement 6: Dying in this game/Soft reset
When the Player dies/gets killed, you need to print the classic Souls game phrase or any appropriate message in the console. You may optionally customise it with ASCII art to add a bit of fun. When it happens, all of the RESET features are executed (@see Bonfire, those dot points when taking a rest). Then, the game will move/respawn the Player back to the latest Bonfire that the Player has rested. At the moment, the game only has one Bonfire at the Firelink Shrine. Hence, the Player will be moved to that Firelink Shrine’s Bonfire (standing on top of it).

NOTE: This is NOT the end game yet, and we don't want to see the game STOPS if the Player died (that’s why it is called a soft reset).

Additionally, when the Player dies, the Player's soul level will become 0. Then, a “token of souls” will appear at the dying spot/location. The Player can get back these lost souls only by interacting with that token of souls. This token of souls is shown as a `$` in the game. It stays there forever even though the World gets reset. When the Player stands next to (or on top) and interacts with it, the Player can regain its recent lost souls. Recovering souls this way will increment current souls that the Player has.

We have a specific scenario for dying from falling (when the Player steps on the Valley ground). If the Player falls into the valley (Player steps on the ”+” in the game), that token of souls must not be placed on the valley location (even though it is the Player’s dying spot). But, it will be placed one step behind. For example, Player’s north direction is a Valley. The player steps on it and so the Player falls into the abyss. The token of souls will show up at the ground before the Player steps on that valley (i.e, at the south of the corresponding valley object).

## Requirement 7: Weapons**:

The Player can only bring one weapon at a time. The Player and enemies cannot drop their weapons when they die or intentionally through an action. A weapon has active and/or passive skills.

![1665964092(1)](https://user-images.githubusercontent.com/64874008/196064669-6043fc23-6ab4-4cb5-8008-c3b1471dc347.png)

## Requirement 8: Vendor

The souls can be traded to buy a new weapon and to upgrade the Player’s attributes (stats) through a vendor. We may give this vendor a name: “Fire Keeper”. You need to place this vendor somewhere in the Firelink Shrine and set it to stay at its location permanently. When the Player buys a new weapon, the weapon in the current inventory will be automatically replaced with it. Replacing the weapon will cause the old weapon to be removed from the game. You need to print an appropriate message when the transaction is successful or fails.

