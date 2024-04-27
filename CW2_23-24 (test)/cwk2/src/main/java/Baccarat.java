import java.util.Scanner;

public class Baccarat {
  // TODO: Implement your Baccarat simulation program here
  private BaccaratHand banker;
  private BaccaratHand player;
  private Shoe shoe;
  private String result;
  private Boolean playerDrew;
  private String win;
  private Integer ties;
  private Integer bankerWins;
  private Integer playerWins;

  private Baccarat() {
    banker = new BaccaratHand();
    player = new BaccaratHand();
    shoe = new Shoe(6);
    ties = 0;
    bankerWins = 0;
    playerWins = 0;
  }

  private String round() {
    initialDeal();
    result = checkResult();
    if (result != "None") {
      return result;
    }
    playerDrew = checkPlayerDraw();
    checkBankerDraw();
    System.out.println("Player: " + player.toString() + " = " + player.value());
    System.out.println("Banker: " + banker.toString() + " = " + banker.value());
    win = checkWin();
    endRound();
    return win;
  }

  private void initialDeal() {
    banker.add(shoe.deal());
    player.add(shoe.deal());
    banker.add(shoe.deal());
    player.add(shoe.deal());
    System.out.println("Player: " + player.toString() + " = " + player.value());
    System.out.println("Banker: " + banker.toString() + " = " + banker.value());
  }

  private void endRound() {
    player.discard();
    banker.discard();
  }

  private String checkResult() {
    if (player.isNatural() && banker.isNatural()) {
      if (player.value() == banker.value()) {
        return "Tie";}
      else if (player.value() > banker.value()) {
        return "Player";}
      else {return "Banker";}
    } 
    else if (player.isNatural()) {
      return "Player";}
    else if (banker.isNatural()) {
      return "Banker";}
    return "None";
  }

  private Boolean checkPlayerDraw() {
    if (player.value() < 6) {
      player.add(shoe.deal());
      System.out.println("Dealing third card to player...");
      return true;}
    return false;
  }

  private void checkBankerDraw() {
    if (playerDrew == false){
      if (banker.value() < 6) {
        banker.add(shoe.deal());
        System.out.println("Dealing third card to banker...");
      }
    } else {
      switch (banker.value()) {
        case 3:
          if (player.cards.get(2).value() != 8) {
            banker.add(shoe.deal());
            System.out.println("Dealing third card to banker...");
          }
          break;
        case 4:
        if (player.cards.get(2).value() > 1 && player.cards.get(2).value() < 8) {
          banker.add(shoe.deal());
          System.out.println("Dealing third card to banker...");
        }
          break;
        case 5:
        if (player.cards.get(2).value() > 3 && player.cards.get(2).value() < 8) {
          banker.add(shoe.deal());
          System.out.println("Dealing third card to banker...");
        }
          break;
        case 6:
        if (player.cards.get(2).value() == 6 || player.cards.get(2).value() == 7) {
          banker.add(shoe.deal());
          System.out.println("Dealing third card to banker...");
        }
        case 7:
          break;
        default:
        banker.add(shoe.deal());
        System.out.println("Dealing third card to banker...");
      }
    }
  }

  private String checkWin() {
    if (player.value() == banker.value())  {
      ties++;
      return "Tie";
    } else if (player.value() > banker.value()) {
      playerWins++;
      return "Player";}
    bankerWins++;
    return "Banker";
  }
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Baccarat baccarat = new Baccarat();
    baccarat.shoe.shuffle();
    Boolean run = true;
    String display;
    Integer numRounds = 0;
    while(run == true) {
      numRounds++;
      System.out.println("Round " + numRounds);
      display = baccarat.round();
      if (display == "Tie") {
        System.out.println(display);
      }
      else {
        System.out.println(display + " win!");
      }
        System.out.print("Another round? (y/n): ");
        if (scanner.nextLine().toLowerCase() == "n") {
          run = false;
        }
      System.out.println();
    }
    System.out.println(numRounds + "rounds played");
    System.out.println(baccarat.playerWins + "player wins");
    System.out.println(baccarat.bankerWins + "banker wins");
    System.out.println(baccarat.ties + "ties");
  }
}

//exception messages
//proper y/n
//mabye manual game stops with 6 cards left
