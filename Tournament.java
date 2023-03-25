import java.util.ArrayList;

public class Tournament {

  public static int rounds; //Number of rounds in the tournament
  private ArrayList<Player> players; //All players in the tournament, is sorted by wins
  private Game[][] games; //A 2d array of games where each array is an array of games for that round
  private int numPlayers;
  
  public Tournament(int rounds, int numPlayers) {
    this.rounds = rounds;
    players = new ArrayList<Player>();
    games = new Game[rounds][numPlayers/2];
  }
  
  /**
   * Add a player to the tournament. Is originally sorted by rating
   * 
   * @param player the player to be added
   */
  public void addPlayer(Player player) {
    if(players.size() == 0) {
      players.add(player);
      return;
    }
    int i = 0;
    while(player.getRating() < players.get(i).getRating()) {
      if(i == players.size() - 1) {
        players.add(player);
        return;
      }
      i++;
    }
    players.add(i, player);
  }
  
  /**
   * @return number of rounds in this tournament
   */
  public static int getRounds() {
    return rounds;
  }
  
  /**
   * Simulates the playing of a tournament
   */
  public void simulateTournament() {
    for(int i = 0; i < rounds; i++) {
      Player bye = assignPairings(i + 1);
      playGames(i);
      reSortPlayers();
      System.out.println(roundToString(i, bye));
    }
  }
  
  /**
   * Sorts players by highest wins to lowest
   */
  private void reSortPlayers() {
    Player[] tempPlayers = new Player[players.size()];
    int currIndex = 0;
    while(players.size() != 0) {
      Player temp = players.get(0);
      int tempIndex = 0;
      for(int i = 0; i < players.size(); i++) {
        if(temp.getWins() < players.get(i).getWins() || 
            (temp.getWins() == players.get(i).getWins() && temp.getRating() < players.get(i).getRating())) {
          temp = players.get(i);
          tempIndex = i;
        }
      }
      tempPlayers[currIndex] = temp;
      currIndex++;
      players.remove(tempIndex);
    }
    
    for(int i = 0; i < tempPlayers.length; i++) {
      players.add(tempPlayers[i]);
    }
  }
  
  /**
   * Assigns matchups for any round of the tournament. Matches player with another player 
   * with the same number of wins. If no player are available with same amount of wins, matches
   * with player with different amount of wins. If odd number of players, last player to be assigned gets bye.
   * @return the player with a bye if there was one
   */
  private Player assignPairings(int roundNum) {
    for(int i = 0; i < players.size() - 1; i = i+2) {
      //Add game to tournament array
      Game currGame = new Game(players.get(i), players.get(i+1));
      games[roundNum - 1][i/2] = currGame;
      
      //Add game to players game arrays
      players.get(i).addGame(currGame, roundNum);
      players.get(i + 1).addGame(currGame, roundNum);
    }
    //Add a bye for an unmatched player
    if(players.size() % 2 != 0) {
      players.get(players.size() - 1).addGame(new Game(), roundNum);
      players.get(players.size() - 1).addWins(0.5);
      return players.get(players.size()-1);
    }
    return null;
    
  }
  
  /**
   * Simulates playing of games between players
   */
  private void playGames(int round) {
    //Player with highest rating wins (Change this to probabilities later)
    for(int i = 0; i < games[round].length; i++) {
      Game currGame = games[round][i];
      Player white = currGame.getWhite();
      Player black = currGame.getBlack();
      if(white.getRating() > black.getRating()) {
        currGame.setResult(1);
      }
      else if(white.getRating() == black.getRating()) {
        currGame.setResult(0.5);
      }
      else {
        currGame.setResult(0);
      }
    }
  }
  
  /**
   * Outputs summary of tournament once complete
   */
  public String toString() {
    String result = "";
    for(int i = 0; i < rounds; i++) {
      result += "\nRound " + i + ": \n";
      for(int j = 0; j < players.size()/2; j++) {
        if(games[i][j] == null) {
          break;
        }
        result += games[i][j];
      }
    }
    result += "\nStandings: \n";
    for(Player player : players) {
      result += player + "\n";
    }
    return result;
  }
  
  /**
   * Output summary for a single round
   * @return
   */
  public String roundToString(int round, Player bye) {
    String result = "";
    result += "Round " + (round + 1) + ": \n";
    for(int j = 0; j < players.size()/2; j++) {
      if(games[round][j] == null) {
        break;
      }
      result += games[round][j];
    }
    if(bye != null) {
      result += bye.getName() + " had a bye\n";
    }
    result += "\nStandings: \n";
    for(Player player : players) {
      result += player + "\n";
    }
    
    return result;
  }
  
  public static void main(String[] args) {
//    Had to change players field to public static
    Tournament tourney = new Tournament(5, 10); 
    tourney.addPlayer(new Player(1200, "Jim"));
    tourney.addPlayer(new Player(1440, "Jackson"));
    tourney.addPlayer(new Player(700, "al"));
    tourney.addPlayer(new Player(1900, "Bob"));
    tourney.addPlayer(new Player(1600, "Andrew"));
    tourney.addPlayer(new Player(500, "Mark"));
    tourney.addPlayer(new Player(1300, "Darko"));
    tourney.addPlayer(new Player(900, "Goran"));
    tourney.addPlayer(new Player(1526, "Luka"));
    tourney.addPlayer(new Player(1400, "LeBron"));
    tourney.simulateTournament();
    //System.out.println(tourney.toString());
    
  }
}
