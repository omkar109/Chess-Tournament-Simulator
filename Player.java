
public class Player{

  private String name;
  private int rating; //The rating of this player
  private Game[] gamesPlayed; //An array of all the games this player has played
  private double wins; //Number of wins in this tournament
  
  public Player(int rating, String name) {
    this.rating = rating;
    gamesPlayed = new Game[Tournament.getRounds()];
    wins = 0;
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  public void addGame(Game game, int round) {
    gamesPlayed[round - 1] = game;
  }
  
  public int getRating() {
    return rating;
  }
  
  public String toString() {
    return name + ": " + rating + " " + wins + " wins";
  }
  
  public double getWins() {
    return wins;
  }
  
  public void addWins(double result) {
    wins += result;
  }
}
