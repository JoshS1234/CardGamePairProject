# Pair programming - Card game project 🃏♠️♣️♥️♦️🃏

## Overview 👁️

The repository holds the source code for our pair-programming project, which was a set of card games to be played within
a terminal environment.

The project contains:

- An initial game selection tool.
- 2 game modes
    - Snap.
    - Texas Hold'em Poker

## Structure 👷

The project is largely broken into 3 sections: Poker functionality, Snap functionality and some shared features used for
both games.

The general structure of the project is:
- General utility functions (Largely contained within the "utils" package)
- Poker related classes/functions (In the GameManagement.Poker package)
- Snap related classes/functions (In the GameManagement.Snap package)
- Some general classes which will be useful to both games (Eg: Deck, Card)


## Setup 🛠️
- Clone the project -> https://github.com/JoshS1234/CardGamePairProject
- Run the "main" method in the Main class, this will open the Game Selector

## Extra info 🧠
- Poker game does not small/big blinds implemented#
- Timer for pressing enter on snap is 2s

## Accreditations 🏅
Following classes were co-developed:
- ASCIIArt.DrawCards
- CardSetup (All classes/enums)
- GameManagement.ChooseGame
- GameManagement.Game
- GameManagement.GameSelector
- utils.SortMethods

Following classes were made by Beth:
- ASCIIArt.DrawDealer
- GameManagement.Snap (All classes)
- utils.CompareCards
- utils.UserInteraction

Following classes were made by Josh:
- GameManagement.Poker (All classes)
- utils.UserMessages

## Authors 📖
Josh Spence: https://github.com/JoshS1234
Beth Burrows: https://github.com/BethScarlett