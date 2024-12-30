
/**
 * Programming Challenge 19-4. Top Ten Gamers.
 */
import java.util.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application
{
    private TextArea textArea = new TextArea();
    private TextField cmdTextField = new TextField();
    private SortedLinkedList sll = new SortedLinkedList();

    static public void main(String[] args)
    {
       launch(args) ;     
    } 

    @Override
    public void start(Stage stage) throws Exception
    {
        // Command Entry Controls
        Label cmdLabel = new Label("Insert name score:");        
        HBox cmdHBox = new HBox(10);
        cmdHBox.setPadding(new Insets(10, 0, 0, 0));
        cmdHBox.getChildren().addAll(cmdLabel, cmdTextField);

        // Top Level Pane
        BorderPane pane = new BorderPane();   
        pane.setPadding(new Insets(10));
        pane.setCenter(textArea);
        pane.setBottom(cmdHBox);
        textArea.setEditable(false);
        cmdTextField.setOnAction(new CmdTextListener());

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Top Ten Gamers");
        stage.show();
    }

     class CmdTextListener implements EventHandler<ActionEvent>
    {       
        public void handle(ActionEvent evt)
        {

            String cmd = cmdTextField.getText();
            Scanner sc = new Scanner(cmd);
            String cmdOp = sc.next();

            if (cmdOp.equalsIgnoreCase("insert"))
            {
                if (!sc.hasNext())
                {
                    throw new RuntimeException("Missing name in insert command");
                }
                String name = sc.next();
                if (!sc.hasNextInt())
                {
                    throw new RuntimeException("Missing score in insert command");
                }
                int score = sc.nextInt();                

                sll.insert(name, score);                   

                // Display the new list
                Iterator<GameStat> listIter = sll.iterator();
                textArea.setText("");
                int i = 1;
                while (listIter.hasNext())
                {                   
                    GameStat stat = listIter.next();
                    textArea.appendText(i + " " + stat.toString() + "\n");
                    i++;                   
                }                
                return;
            }
        }
    }
}

/**
 * GameStat represents a pair consisting of a name and a score, to be stored in
 * the list.
 */
class GameStat implements Comparable<GameStat>
{
    String name;
    int score;

    public GameStat(String s, int i)
    {
        name = s;
        score = i;
    }

    @Override
    public String toString()
    {
        return name + " " + score;
    }

    @Override
    public int compareTo(GameStat other)
    {
        return score - other.score;
    }
}


class SortedLinkedList extends GenericLinkedList<GameStat>
{
    /**
     * getPosition
     *
     * @param stat A GameStat to be stored.
     * @return The index the GameStat will have when stored in the list.
     * When new element are added at the returned position, the larger values
     * will be at the beginning of the list
     */
    private int getPosition(GameStat stat)
    {

    }

    /**
     * void insert(String name, int score). Adds the score according to the
     * rules that govern the hall of fame list.
     *
     * @param name The name of the player.
     * @param score The player's score.
     */
    public void insert(String name, int score)
    {
      
    }
}



