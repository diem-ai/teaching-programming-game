package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.game.ai.shared.controller.TestGameController;
import tests.game.ai.shared.model.TestConnect4Board;
import tests.game.ai.shared.model.TestTictactoeBoard;
import tests.game.ai.shared.player.TestAIPlayer;
import tests.game.ai.shared.player.TestHumanPlayer;
import tests.game.ai.shared.player.TestPairsPlayer;
import tests.game.ai.shared.player.factory.TestHumanAIPairsPlayerFactory;
import tests.game.ai.shared.player.factory.TestPairsPlayerFactoryChoices;
import tests.game.ai.shared.player.factory.TestTwoAIPairsPlayerFactory;
import tests.game.ai.shared.player.factory.TestTwoHumanPairsPlayer;
import tests.game.ai.shared.rules.TestCommand;
import tests.game.ai.shared.rules.TestCommandChain;
import tests.game.ai.shared.rules.TestCommandParser;
import tests.game.ai.shared.rules.TestStandardRuleCollection;
import tests.game.ai.shared.view.TestConnect4GameView;
import tests.game.ai.shared.view.TestTictactoeGameView;

@RunWith(Suite.class)
@SuiteClasses({TestGameController.class,
				TestConnect4Board.class,
				TestTictactoeBoard.class,
				TestAIPlayer.class,
				TestHumanPlayer.class,
				TestPairsPlayer.class,
				TestHumanAIPairsPlayerFactory.class,
				TestPairsPlayerFactoryChoices.class,
				TestTwoAIPairsPlayerFactory.class,
				TestTwoHumanPairsPlayer.class,
				TestCommand.class,
				TestCommandChain.class,
				TestCommandParser.class,
				TestStandardRuleCollection.class,
				TestConnect4GameView.class,
				TestTictactoeGameView.class})

public class TestAll {
}
