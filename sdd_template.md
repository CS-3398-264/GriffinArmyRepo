## 1. Introduction

### 1.1 Purpose
This document contains the system design description for the Griffin Chess App. Included are the Architectural design and features of the MVC classes, interactions and details of how each class performs. This document will also show how the use cases that were detailed in the SRS are to be implemented using this design pattern.
Primary audiences for this document are the software developers. 

### 1.2 System Overview
Griffin Chess shall be written in Java following the Model-View-Controller pattern. This will enable us to seperate the internal state of the application from the classes that present that information and interact with the user. Griffin Chess shall take advantage of various design paterns including iterators and The Observer/Observable pattern. Interfaces and abstract classes will be utilized to maximize code reuse and streamline the development process with a distributed team.

### 1.3 Definitions, Acronyms and Abbreviations
|Term | Definition|
|:-------|:---------:|
|A.I.|The algorithm that will play a chess game against the human player.|
|A.I. Difficulty|The created sense of increased/decreased difficulty from the A.I.’s algorithm which chooses the best possible move.|                      
|Available moves|The possible moving patterns and destination cells of a specific chess piece.| 
|Draw|If both player 1 and player 2 reach a stalemate without any way for either to achieve a winning condition.|
|Check|The condition when a player’s king is under threat of capture in the opponent’s next turn.|
|Checkmate|When the player cannot move out of check. The player on checkmate loses and the game ends.|
|Chess Board|The 8x8 plane where the chess pieces reside, and where they can be moved.|
|Chess Piece|The items that are used by each player to play chess against the opponent.|
|Player|The user/A.I. that moves the pieces on the board to play the game.|

### 1.4 Supporting Materials
* FIDE Laws of Chess
  * www.fide.com/component/handbook/?id=124&view=article
* Guide to building a simple chess A.I.by Lauri Hartikka, March 30, 2017.
  * https://medium.freecodecamp.org/simple-chess-ai-step-by-step-1d55a9266977
* Software Engineering by Sommerville, 10th Ed. - Chapter 5 System Modeling
  * MVC Diagram from the book goes here... serves as an outline for the structure of our app
  

### 1.5 Document Overview
Section 1 contains an outline of the structure of this document, as well as a glossary of terms and list of references used.

Section 2 contains diagrams displaying the MVC architecture of Griffin Chess, as well as the makeup and relationship between the different classes that make up the application.

Section 3 contains a high-level overview of the different states of Griffin Chess and the flow of data between the view and the managed state of the board.

Section 4 contains several Use Cases that represent the most vital functions of Griffin Chess and shows how the user will progress through them and perform each action.

## 2. Classes and Components

### 2.1 Overview

![High-Level Class Diagram](./images/high-level-classes.png)

While the user will interact with the game through the GUI front end, the core mechanics will be operating in a loosely coupled back-end and will follow a Model-View-Controller pattern. The game state, logic, and user interactions will be kept separate from the classes that display them, which will allow us to offer users different display options in the future and will allow the teams to write more maintainable, encapsulated code.

### 2.2.1 `App` Class 
_<Describe an element (subsystem, component, etc...) from architecture in further detail. When appropriate, include information on how the element is further broken down and the interactions and relationships between these subcomponents.>_

The `App` class acts as the Model in the MVC architecture and maintains the currently selected options.

### 2.2.2 `Board` Class

The `Board` class maintains the state of the board and the location. The Board class extends the Observable class in order to take advantage of the Observer/Observable pattern and keeps its observers constantly updated with the most current version of the board.

### 2.2.3 `aPiece` Class

The abstract `aPiece` class contains the implementation of functions that are relevant to all pieces.

### 2.2.4 `Pawn` Class

The `Pawn` extends the `aPiece` class and initializes individual pawn pieces and provides functions for the characteristics of the pawn.

### 2.2.5 `Piece` Interface

The `Piece` interface provides function declarations that are relevant to all pieces.

### 2.2.6 `Human` Class

The `Human` class implements `Player` and creates a player and initializes the pieces.

### 2.2.7 `Player` Interface

The `Player` interface provides function declarations that are relevant to all players.

### 2.2.8 `Cell` Class

The `Cell` class extends `JButton` and initializes the characteristics of each cell on the chess board and specifies colors for the varying moves of the chess pieces and the natural color of the cells of the board.

### 2.2.9 `GUI` Class

The `GUI` class instantiates the View and graphics.

### 2.2.10 `Main` Class

The `Main` class holds the location of main() and instantiates the application.

### 2.2.11 `aWindow` Class

The `aWindow` class provides characteristics and functions that are common to all unique windows.

### 2.2.12 `BoardWindow` Class

The `BoardWindow` extends the `aWindow` class and provides characteristics and functions unique to the board.

### 2.2.13 `MenuWindow` Class 

The `MenuWindow` extends the `aWindow` class and provides characteristics and functions unique to the menu.

### 2.2.14 `OptionsWindows` Class

The `OptionsWindow` class extends `aWindow` and provides characteristics and functions unique to the options.





![image](./images/class-breakdowns/app.png)

## 3. High-Level Design
* **diagram of model/view interactivity (or something) here**

The user opens the Griffin Chess application and an initial start screen is shown. The user has the choice to start the game or change certain options to the preferences of the user. The user can change display options, whether the opponent should be another person or the AI, varying difficulties for the AI, and can cancel changes or apply those changes to the game. If the user chooses to start the game, the chess board and pieces are generated and the user can play the game. When the user moves a piece, the move is confirmed by the user and the piece moves to an available space until the game ends by checkmate or stalemate. The user can also quit in the middle of the game at any time.
![State Diagram](./images/state-diagram.png)

### 3.1 View / Model Component 1..n
_<Provide a description and diagrams of a system component or set of components that describes a clearly defined view or model of the entire system or a subset of the system.>_

## 4. Use Cases
The Use case diagrams give an representation of the most significant scernarios that the player may use. 
### 4.1 Start Game/Options
![Use Case 1](./images/use-case-1.png)
Description: As Griffin Chess is opened, the player has the choice to start the game immediately. The second choice is the option menu to prompt the user with difficulty level, opponent, and Day/Night mode. 

### 4.2 Move Pieces
![Use Case 2](./images/use-case-2.png)
Description: During game process, the player with the current turn has the option to move the selected piece. 

### 4.3 Choosing of A.I opponent
![Use Case 3](./images/use-case-3.png)
Description: The player chooses the A.I as an opponent, starts the game, and while in the game has the option to move the selected piece or quit the game. 

### 4.4 Player vs Player 
![Use Case 4](./images/use-case-4.png)
Description: The player 1 starts the game. Player 2 or the A.I joins the game and uses the same game functions as player 1. 
