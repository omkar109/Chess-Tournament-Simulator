import java.util.ArrayList;

public class Game {

  private String[] moves; //An array holding the moves of each game. Moves are in sequential order 
                          //and represented as strings
  private double result; //1 if white won, 0 if white lost, 0.5 if tie
  private Player whitePlayer; //The player who played white
  private Player blackPlayer; //The player who played black
  
  /**
   * Constructor
   * @param moves a String of the moves, each move separated by a space. Eg: "e4 e5 Bc4 Nc6 Qh5 d6 Qxf7#"
   */
  public Game(Player whitePlayer, Player blackPlayer) {
    this.whitePlayer = whitePlayer;
    this.blackPlayer = blackPlayer;
  }
  
  /**
   * Used when player had a bye
   */
  public Game() {
    result = 1;
  }
  
  public Player getWhite() {
    return whitePlayer;
  }
  
  public Player getBlack() {
    return blackPlayer;
  }
  
  public void setResult(double result) {
    this.result = result;
    if(result == 1) {
      this.whitePlayer.addWins(1);
    }
    else if(result == 0.5) {
      this.whitePlayer.addWins(0.5);
      this.blackPlayer.addWins(0.5);
    }
    else {
      this.blackPlayer.addWins(1);
    }
  }
  
  public String toString() {
    String result = "";
    if(this.result == 1) {
      return whitePlayer.getName() + "(" + whitePlayer.getWins() + ") beat " 
          + blackPlayer.getName() + "(" + blackPlayer.getWins() + ")\n"; 
    }
    else if(this.result == 0.5) {
      return whitePlayer.getName() + "(" + whitePlayer.getWins() + ") tied " 
          + blackPlayer.getName() + "(" + blackPlayer.getWins() + ")\n"; 
    }
    else {
      return blackPlayer.getName() + "(" + blackPlayer.getWins() + ") beat " 
    + whitePlayer.getName() + "(" + whitePlayer.getWins() + ")\n"; 
    }

  }
}
