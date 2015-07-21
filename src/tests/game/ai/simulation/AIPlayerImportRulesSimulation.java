package tests.game.ai.simulation;

import java.util.Scanner;

import game.ai.shared.player.AIPlayer;
import game.ai.shared.player.IPlayer;
import game.ai.shared.player.factory.PairsPlayerFactoryChoices;
import game.ai.shared.player.specification.PairsPlayerSpecification;
import game.ai.shared.rules.*;
import game.ai.shared.rules.specification.IChain;

/**
 * This class is a simulation for importing strategy rules file of user for {@link AIPlayer} </br>
 * @author btdiem
 * @version 1.0
 *
 */
public class AIPlayerImportRulesSimulation {
	static Scanner scanner;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(DateHeader.dateString());
		PairsPlayerSpecification pairsPlayer = PairsPlayerFactoryChoices.getRandomPairsPlayer();
		//System.out.println(pairsPlayer.toString());
		IPlayer first = pairsPlayer.getFirstPlayer();
		System.out.println(first);
		if (first instanceof AIPlayer){
			displayUserGuide();
			//import the rule strategy file here
			importStrategyRule(first);
		}
		IPlayer second = pairsPlayer.getSecondPlayer();
		System.out.println(second);
		if (second instanceof AIPlayer){
			displayUserGuide();
			importStrategyRule(second);
		}
	}
	
	static void importStrategyRule(IPlayer player){
		 scanner = new Scanner(System.in);
		 boolean isOK = false;
		 do{
			 System.out.println("Enter your file name: ");
			 System.out.flush();
			 String fileName = scanner.nextLine();
			 try{
				 ((AIPlayer)player).importRules(fileName);
				 isOK = true;
			 }catch(Exception e){
				 isOK = false;
				 System.out.println(e.getMessage());
			 }
			 
		 }while(!isOK);
		System.out.println("Your strategy rules are:");
		System.out.println(((AIPlayer)player).getCommandChain().toString());
	}
	/**
	 * Print the user guide for user to define a correct structure and semantic rule </br>
	 */
	static void displayUserGuide(){
		IChain standardCommandChain = RulesFactory.getInstance().getStandardCommandChain();
		String str = "\n********************************************************************\n";
		str += "Your have to follow 2 rules below: \n";
		str += "Your rule has follow structure : if <condition> then <strategy> \n";
		str += "The condition and strategy have to make sense \n";
		str += "For example: if player_can_win then win \n";
		str += "Your rule collection must be subset of system \n";
		str += "In case your rules has no break condition \n";
		str += "The system will insert a default break command to your collection: \n";
		str += "Here are the rules of  system \n";
		str += standardCommandChain.toString();
		str += "\n********************************************************************";
		System.out.println(str);
		
	
	}
}
