package view;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
   private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

   public GameEngineCallbackImpl()
   {
      logger.setLevel(Level.FINE);
   }

   @Override
   public void playerDieUpdate(Player player, Die die, GameEngine gameEngine)
   {
      logger.log(Level.FINE, player.getPlayerName() + " die " + die.getNumber() + " rolled to " + die.toString());
   }

   @Override
   public void playerResult(Player player, DicePair result, GameEngine gameEngine)
   {
      logger.log(Level.INFO, "*RESULT*: " + result.toString());
   }

   public void houseDieUpdate(Die die, GameEngine gameEngine) {
	   
	   logger.log(Level.FINE, "House die " + die.getNumber() + " rolled to " + die.toString());
	   
   }
   
   public void houseResult(DicePair result, GameEngine gameEngine) {
	   
	   //stores player collection from game engine then converts into an array
	   Collection<Player> players = gameEngine.getAllPlayers();
	   String playerResults = "";
	   
	   //stores all the player results for every player into a string
	   for (Player player : players) {
		   playerResults += player.toString() + "\n";
	   }
	   
	   //logs the result of the house then logs the results of the players
	   logger.log(Level.INFO, "House *RESULT*: "+ result.toString());
	   logger.log(Level.INFO, "FINAL PLAYER RESULTS: " + playerResults);
	   
   }
}
