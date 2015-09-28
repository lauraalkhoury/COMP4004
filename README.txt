Assignment 1

Student:        Laura Alkhoury
Student Number: 100900969
GitHub Account: https://github.com/lauraalkhoury/COMP4004/

Features/Requirements & Test Methods:
  * determine how many players are participating in this round (which defines the valid player ids for this round)
    Suggested minimal test -- 2: Test for Minimum and maximum number of players (2 to 4)
    Class: Round
      setUp()
        - testSetUp 
        - testLowerPlayerLimit
        - testUpperPlayerLimit
        - testInitialNumHands
    Class: Deck
    Deck()
      - testNewDeck

  * input each player's hand, in the form of a player id followed by 5 cards separated by spaces, each card being identified according to the format RankSuit. 
    Suggested minimal test -- 1: Test for Duplicate cards and suites
    Suggested minimal test -- 3: Test to ensure there are 5 cards dealt to each player
    Suggested minimal test -- 6: Test for invalid cards e.g. fourteenClubs, threeShovels, thDiamonds etc.
    Suggested minimal test -- 7: Test for valid player ids
    Suggested minimal test -- 8: Ensure the order of entry is correct - e.g. player id and then the cards
    Class: Round
    addHand()
      - testAddEmptyHand
      - testAddHandWithInvalidPlayerID
      - testAddHandWithTooFewCards
      - testAddHandWithTooManyCards
      - testAddHandWithDuplicateCards
    Class: Card
    getName()
      - testNewCardName
    isInUse()
      - testIsCardInUse
    toSuit()
      - testEmptyToSuit
      - testInvalidToSuit
      - testValidToSuit
    toCardNum()
      - testEmptyToCardNum
      - testInvalidToCardNum
      - testValidToCardNum
    createFromString()
      - testEmptyCreateFromString
      - testInvalidCardNumCreateFromString
      - testInvalidSuitCreateFromString
      - testValidCreateFromString
    Class: Deck
    isValidCard()
      - testInValidCard
      - testValidCard
    isCardInUse
      - testUsedCard
      - testUnusedCard
      
  * output these hands (player id and cards) AND a rank (1 being the winner) in sorted decreasing order.
    Suggested minimal test -- 4: Hands are ranked in order !
    Suggested minimal test -- 5: Test for duplicate hands e.g. Royal Flush and ensure they are listed at the top of the ranking order
    Class: Round
    isAceToTen()
      - testIsAceToTenInOrder
      - testIsAceToTenOutOfOrder
      - testIsNotAceToTen
    countSuits()
      - testCountSuits221
      - testCountSuits5
      - testCountSuits2111
      - testCountSuits32
    countCardNums()
      - testCountCardNums41
      - testCountCardNums32
      - testCountCardNums11111
    checkStraight()
      - testCheckSortedStraight
      - testCheckUnsortedStraight
      - testCheckInvalidStraight
    rankHand()
      - testRankHandRoyalFlush
      - testRankHandStraightFlush
      - testRankHandFourOfAKind
      - testRankHandFullHouse
      - testRankHandFlush
      - testRankHandStraightAceLow
      - testRankHandStraightAceHigh
      - testRankHandThreeOfAKind
      - testRankHandTwoPair
      - testRankHandOnePair
      - testRankHandHighCard
    rank()
      - testRank
      - testTieRoyalFlushRank
      - testTieHighCardRank
    getResultsDescending()
      - testGetResultsDescending
      
Instructions:
  A demo of one Game can be observed by running the main() function in the Game class.
    You may edit the numPlayers variable (line 8 of Game.java) to represent any number of players you would like to test.
    You may also edit the addHand functions (starting from line 13 of Game.java) to add various hands of your choosing to the round.
  
  For a runthrough of all test cases, use JUnit 4 to run the test methods found in TestCard, TestDeck, and TestRound.
