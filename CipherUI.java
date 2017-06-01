import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class CipherUI extends Application
{
    Cipher cipher = new Cipher();

    String message, skey, answer;
    char key;

    private TextField txtkey;
    private TextArea txtmessageIn, txtmessageOut;
    private Button btnencrypt, btndecrypt, btnclear;
    private Label lblkey;
    private Alert alertkey, alertkeyEmpty, alertmessageEmpty;

    @Override
    public void start(Stage primaryStage)
    {
        //Make the controls
        btnencrypt = new Button("Encrypt");
        btndecrypt = new Button("Decrypt");
        btnclear = new Button("clear fields");
        lblkey = new Label("Enter cryption key: ");
        txtkey = new TextField("");

        txtmessageIn = new TextArea();
        txtmessageOut = new TextArea();

        alertkey = new Alert(Alert.AlertType.WARNING);
        alertkeyEmpty = new Alert(Alert.AlertType.WARNING);
        alertmessageEmpty = new Alert(Alert.AlertType.WARNING);
        alertkeyEmpty.setTitle("Warning");
        alertkeyEmpty.setContentText("You must enter a single letter KEY");
        alertkey.setTitle("Warning!");
        alertkey.setContentText("The key must be a letter!");
        alertmessageEmpty.setTitle("Warning");
        alertmessageEmpty.setContentText("Looks like you forgot to enter a message to Encrypt/Decrypt!");

        //txtmessageOut.setEditable(false);
        txtmessageIn.setWrapText(true);
        txtmessageOut.setWrapText(true);
        txtmessageIn.setPromptText("Enter message here...");
        txtmessageOut.setPromptText("View result here...");
        txtmessageOut.setEditable(false);

        lblkey.setAlignment(Pos.CENTER_RIGHT);

        //make container for app
        GridPane root = new GridPane();
        //pu container in middle of scene
        root.setAlignment(Pos.CENTER);
        //set spacing between controls in grid
        root.setHgap(10);
        root.setVgap(10);
        //add to grid, cell by cell
        //column, row, column span, row span

        root.add(lblkey,0,0,2,1);
        root.add(txtkey,2,0);
        root.add(btnencrypt,4,0,2,1);
        root.add(btndecrypt,6,0,2,1);
        root.add(txtmessageIn,0,1,7,3);
        root.add(txtmessageOut,0,4,7,3);
        root.add(btnclear,0,7,2,1);



        setWidths();
        attachCode();


        //width, height
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("Cipher");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void attachCode()
    {
        //have each button run BTNCODE when clicked
        btnencrypt.setOnAction(e -> btncode(e));
        btndecrypt.setOnAction(e -> btncode(e));
        btnclear.setOnAction(e -> btncode(e));
    }

    public void btncode(ActionEvent e)
    {
        if(e.getSource()==btnclear)
        {
            txtkey.setText("");
            txtmessageIn.setText("");
            txtmessageOut.setText("");
            return;
        }

        if(e.getSource()==btnencrypt)
        {
            if(!getUserInput())
                return;

            answer = cipher.encrypt(message, key);
            txtkey.setText("");
            txtmessageOut.setText(answer);
        }

        else if(e.getSource()==btndecrypt)
        {
            if(!getUserInput())
                return;

            answer = cipher.decrypt(message, key);
            txtkey.setText("");
            txtmessageOut.setText(answer);
        }
    }

    public boolean getUserInput()
    {
        if(txtmessageIn.getText().toLowerCase().trim().equals(""))
        {
            alertmessageEmpty.showAndWait();
            return false;
        }
        message = txtmessageIn.getText().toLowerCase().trim();
        skey = txtkey.getText().toLowerCase().trim();
        if(skey.equals(""))
        {
            alertkeyEmpty.showAndWait();
            return false;
        }
        else{
            key = skey.charAt(0);
        }
        if(!isLetter(key))
        {
            alertkey.showAndWait();
            return false;
        }
        txtkey.setText("");
        return true;
    }

    public boolean isLetter(char key)
    {
        char[] alph = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
                       'n','o','p','q','r','s','t','u','v','w','x','y','z'};

        if(cipher.search(alph, key) == -1)
            return false;
        return true;
    }

    public void setWidths()
    {
        btnencrypt.setPrefWidth(150);
        btndecrypt.setPrefWidth(150);
        lblkey.setPrefWidth(150);
        txtkey.setPrefWidth(40);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
