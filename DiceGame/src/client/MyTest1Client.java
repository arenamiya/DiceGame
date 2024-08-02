package client;


import model.DicePairImpl;
import model.DieImpl;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

public class MyTest1Client {

	public static void main(String[] args) {
		
		MyTest1Client test = new MyTest1Client();
		
		//TESTS FOR EVERY CLASS, comment out any to look through the tests
		
		//test.diceTests(); //tests for both Die and DicePair classes, compare/equals/hash methods
		//test.betTests(); //tests to see if betting is valid
		//test.playerTests(); //tests for adding and removing player 
		//test.gameCallbackTests(); //tests for callback methods, and multiple callbacks
		test.gameEngineTests(); //tests to see if game functions entirely

	}
	
	public void diceTests() {
		
		//test for D1 = D2 true
		Die d1 = new DieImpl(1, 2, 12);
		Die d2 = new DieImpl(2, 2, 12);
		
		System.out.println(d1.toString() + ", " + d2.toString());
		System.out.println("is d1 = d2? " + d1.equals(d2));
		
		//test for hashcode
		System.out.println("HASHCODE D1: " + d1.hashCode());
		System.out.println("HASHCODE D2: " + d2.hashCode() + "\n");

		
		//test for D3 = D4 false
		Die d3 = new DieImpl(1, 4, 6);
		Die d4 = new DieImpl(2, 5, 7);
		
		System.out.println(d3.toString() + ", " + d4.toString());
		System.out.println("is d3 = d4? " + d3.equals(d4) + "\n");
		
		//test for dicepairs, comparing/equal/hashcodes
		DicePair dp1 = new DicePairImpl();
		DicePair dp2 = new DicePairImpl();
		
		System.out.println(dp1.toString() + "\n" + dp2.toString());
		System.out.println("Is DP1 = DP2? " + dp1.equals(dp2));
		System.out.println("is D1 > D2? (compare DP1 to DP2): " + dp1.compareTo(dp2));
		
		//test for dicepair hashcode
		System.out.println("HASHCODE DP1: " + dp1.hashCode());
		System.out.println("HASHCODE DP2: " + dp2.hashCode());
		
	}
	
	public void betTests() {
		
		GameEngine g = new GameEngineImpl();
		
		Player[] players = new Player[] {
				new SimplePlayer("1", "Joker", 5000),
				new SimplePlayer("2", "Skull", 3000),
				new SimplePlayer("3", "Panther", 1000),
				new SimplePlayer("4", "Fox", 500)
				};
		
		for(Player player : players) {
			g.addPlayer(player);
			g.placeBet(player, 500);
			System.out.println(g.getPlayer(player.getPlayerId()).getBet());
		}
		
		for(Player p : players) {
			p.setPoints(-(p.getBet()));
			System.out.println(p.getPoints());
		}
		
		//joker, skull and panther should have valid bets
		//fox's bet should be invalid, should not have bet anything
		
	}
	
	public void playerTests() {
		
		GameEngine gameEngine = new GameEngineImpl();
		
		//creating new players
		Player[] players = new Player[] {
		new SimplePlayer("1", "Joker", 5000),
		new SimplePlayer("2", "Skull", 3000),
		new SimplePlayer("3", "Panther", 1000),
		new SimplePlayer("4", "Fox", 500)
		};
		
		//adding players into game engine and sets all bets to 100
		for(Player player : players) {
			gameEngine.addPlayer(player);
			player.setBet(100); 
		}
		
		//prints information of player 4
		System.out.println(gameEngine.getPlayer("4").toString());
		
		//player 3 "panther" will bet higher than the other players
		gameEngine.getPlayer("3").setBet(500);
		
		//removes player 4 "fox" from the game
		gameEngine.removePlayer(gameEngine.getPlayer("4"));
		
		//house rolls then prints all players
		gameEngine.rollHouse(100, 1000, 200, 50, 500, 25);
		
		//output should be house rolling & all players except fox
		//panther should either win/lose 500 whereas other players will win/lose 100
	}
	
	public void gameCallbackTests() {
		
		GameEngine gameEngine = new GameEngineImpl();
		GameEngineCallback gameCallback = new GameEngineCallbackImpl();
		
		//creating new players
		Player[] players = new Player[] {
				new SimplePlayer("1", "Joker", 5000),
				new SimplePlayer("2", "Skull", 3000),
				new SimplePlayer("3", "Panther", 1000),
				new SimplePlayer("4", "Fox", 500)};
		
		//add players on game engine
		for(int i = 0; i < players.length; i++) { 
		     gameEngine.addPlayer(players[i]);
		}
		
		//get result of player 1, "skull"
		players[1].getResult();
		
		//create new dicepair, "dp1"
		DicePair dp1 = new DicePairImpl();
		
		//set result for skull as dp1
		players[1].setResult(dp1);
		
		//log onto console with skull's new dicepair results with die 1 and die 2
		gameCallback.playerDieUpdate(players[1], dp1.getDie1(), gameEngine);
		gameCallback.playerDieUpdate(players[1], dp1.getDie2(), gameEngine);
		
		//log dicepair results as result
		gameCallback.playerResult(players[1], dp1, gameEngine);
		
		//skull rolling the same numbers for die update and results 
		
		GameEngineCallback callback2 = new GameEngineCallbackImpl();
		
		DicePair dp2 = new DicePairImpl();
		
		players[2].setResult(dp2);
		
		callback2.playerDieUpdate(players[2], dp2.getDie1(), gameEngine);
		callback2.playerDieUpdate(players[2], dp2.getDie2(), gameEngine);
		callback2.playerResult(players[2], dp2, gameEngine);
		
		//similar output for panther but for callback2 and a different dice pair
		
	}
	
	public void gameEngineTests() {
		
		GameEngine gameEngine = new GameEngineImpl();
		
		//create new players
		Player[] players = new Player[] {
				new SimplePlayer("1", "Joker", 5000),
				//new SimplePlayer("2", "Skull", 3000),
				//new SimplePlayer("3", "Panther", 1000),
				//new SimplePlayer("4", "Fox", 500)
				};
		
		//add players onto game engine
		for(Player player : players) {
			gameEngine.addPlayer(player);
		}
		
		//place bets for all players, 100 points and roll each player
		for (Player player : gameEngine.getAllPlayers()) {
			 gameEngine.placeBet(player, 500);
	         gameEngine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
		}
		
		//roll house
		gameEngine.rollHouse(100, 1000, 200, 50, 500, 25);
			
		//output should be all players rolling with results as well as house rolling
		//complete with all player results
		
		
	}

}
