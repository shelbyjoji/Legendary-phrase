/*
 Course: CS1302
 Section: 09
 Instructor: Carlos A. Cepeda Mora
 Name: Shelby joji
 Project #:01

 This program creates a memory matching game. This game contains three views on main screen: Home, Game and Author.
 Home view: This window welcomes the uses and gives instruction on the games rules
 Game view: This window creates the game. A total of 16 tiles are created. If matching tile is found, user get points otherwise
 user loses points as penalty for wrong selection
 Author view: This window shows the author details.
 */

package sample;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.geometry.Side;
import java.util.*;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.Parent;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.text.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;

// Main method for the program
public class Main extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

       // Create a scene and place a button in the scene
        Pane pane = new Pane();
        Image image = new Image(getClass().getResource("memory2.jpg").toExternalForm()); // Image for background image

        //creating background image in the pane
        pane.setBackground(new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(200,200,false,false,false,true))));

        //creating title of the program using Label
        Label label = new Label("Memory Matching Game");
        label.setFont(Font.font("Arial",FontWeight.BOLD, FontPosture.REGULAR, 60));
        label.setTextFill(Color.WHITESMOKE);
        label.setLayoutX(150);
        label.setLayoutY(30);

        //placing label on pane
        pane.getChildren().add(label);

        //Creating button for home-view
        Button Home = new Button("Home");
        Home.setLayoutX(150);
        Home.setLayoutY(250);
        Home.setStyle("-fx-background-color: #32cd32; -fx-font-size: 16px; -fx-font-size: 1.3em; -fx-font-weight: bold; -fx-padding: 10px 24px; -fx-width: 50%; -fx-border-width: 5px; -fx-border-radius: 2px;");

        //Creating button for Game-view
        Button Game = new Button("Game");
        Game.setStyle("-fx-background-color: #00bfff;-fx-font-size: 16px; -fx-padding: 10px 24px;-fx-font-weight: bold; -fx-border-width: 5px;-fx-border-radius: 2px;");
        Game.setLayoutX(450);
        Game.setLayoutY(250);

        //Creating button for About-view
        Button About = new Button("Author");
        About.setStyle("-fx-background-color: #e6e6fa;-fx-font-size: 16px;-fx-font-weight: bold; -fx-padding: 10px 24px; -fx-border-width: 5px; -fx-border-radius: 2px;");
        About.setLayoutX(750);
        About.setLayoutY(250);
        pane.getChildren().addAll(Home,Game,About);

        //creating objects for Home view, Game view, author view
        experiment exp = new experiment();
        Authorview auth = new Authorview();
        Homeview homes =new Homeview();

        // Create an array of scene in order to go back and forth with the view
        Scene[] scene = {new Scene(pane, 1000, 600), new Scene(exp.createcontent(), 1000, 600), new Scene(auth.authorcontent(), 1000, 600), new Scene(homes.Homeviewcontent(),1000, 600) };
        stages(primaryStage, scene[0]);

        //Going from Main screen to home view on mouse click
        Home.setOnAction( e-> {
                stages(primaryStage,scene[3]);
        });

        //Going back to Main screen from home view on mouse click
        homes.Backhome.setOnAction(e -> {
                stages(primaryStage,scene[0]);
        });

        //Going from Main screen to Game view on mouse click
        Game.setOnAction( e-> {
                stages(primaryStage,new Scene(exp.createcontent(), 1000, 600));
        });

        //Going from Main screen to author view on mouse click
        About.setOnAction(e-> {
                stages(primaryStage,scene[2]);
        });
        //Going back to Main screen from game view on mouse click
        exp.Back.setOnAction(e -> {
                stages(primaryStage,scene[0]);
        });

        //Going back to Main screen from author view on mouse click
        auth.Back1.setOnAction(e-> {
                stages(primaryStage,scene[0]);
        });
    }

    //Inner class for Home view which extends parent class
    private class Homeview extends Parent {

        //creating a back button to return to Main screen and this should be called from Main class
        private  Button Backhome = new Button("Back");

        // Parent type class which returns the pane containing Home View
        private Parent Homeviewcontent(){
            Pane border = new Pane();// creating pane
            Image image = new Image(getClass().getResource("memory1.png").toExternalForm());// background image
            //setting background image
            border.setBackground(new Background(new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, new BackgroundSize(200,200,false,false,false,true))));

            //creating label for home view welcome message
            Label[] label ={new Label("About the Game"),
                            new Label("Welcome to memory matching game ! This is a simple game that requires observation and memory."),
                            new Label("You will be given a set of 8 pictures. You will have six seconds to memorize the pictures and its positions."),
                            new Label("Once the pictures are gone, you will have the option to click the tiles. If matching pictures are selected,"),
                            new Label("you will receive 10 points otherwise you lose 2 points for each click. Challenge your memory!")};
            //specifying position of all  labels created
            label[0].setTranslateX(250);
            label[0].setTranslateY(150);
            label[0].setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.REGULAR, 25));
            label[0].setTextFill(Color.BLACK);
            for (int i=1;i<5;++i)
            {
                label[i].setTextFill(Color.BLACK);
                label[i].setFont(Font.font("Verdana",FontWeight.BOLD, FontPosture.REGULAR, 12));
                label[i].setTranslateX(250);
                label[i].setTranslateY(200+(i*20));
            }
            //creating position of back button
            Backhome.setLayoutX(800);
            Backhome.setLayoutY(550);
            Backhome.setStyle("-fx-background-color: #32cd32; -fx-font-size: 16px; -fx-font-size: 1.3em; -fx-font-weight: bold; -fx-padding: 10px 24px; -fx-width: 50%; -fx-border-width: 5px; -fx-border-radius: 2px;");

            //adding labels and back button
            border.getChildren().addAll(label);
            border.getChildren().add(Backhome);

           return border; // returning pane
        }
    }

    //Inner class for author view which extends parent class
    private class Authorview extends Parent {

        //Back button should be available in the main class to go back to main view
        private  Button Back1 = new Button("Back");

        //method containing author pane which return a parent type
        private Parent authorcontent() {
            Pane border = new Pane();// creating pane
            Image image = new Image(getClass().getResource("memory1.png").toExternalForm());// background image for the pane
            //setting background image
            border.setBackground(new Background(new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, new BackgroundSize(200,200,false,false,false,true))));

            //Text feild to display about me and setting associated properties
            TextField tfMessage = new TextField("About me");
            tfMessage.setEditable(false);
            tfMessage.setStyle("-fx-text-fill: Black");
            tfMessage.setFont(Font.font("Freestyle script", 30));
            tfMessage.setLayoutX(150);
            tfMessage.setLayoutY(50);

            String s1 = "My name is Shelby Joji. I am a Senior majoring in Computational and Applied Mathematics with double minor in Data Analytics and Computer Science. This is my third year at Kennesaw State University. Besides school I am a full time critical care Respiratory Therapist working at Kennestone hospital. Also, I am a big movie fan and enjoy watching movies when I am free. I love coding and always wanting to learn more in the feild of computer science. I hope you will like my simple JavaFX program. ";
            // Text area to display about me contents and setting its properties
            TextArea text = new TextArea(s1);
            text.setFont(new Font("Bahnschrift Light", 15));
            text.setWrapText(true);
            text.setMaxWidth(450);
            text.setBackground(new Background(new BackgroundFill(Color.rgb(70, 90, 70), CornerRadii.EMPTY, Insets.EMPTY)));
            text.setStyle("-fx-focus-color: transparent; -fx-text-box-border: transparent;");
            text.setLayoutX(400);
            text.setLayoutY(200);
            text.setStyle(" -fx-background-color: #4CAF50; ");
            //using label tp include author's picture and setting its properties
            ImageView mypic =new ImageView(getClass().getResource("shelby.jpg").toExternalForm());
            Label lb1 = new Label(" ",mypic);
            lb1.setStyle("-fx-border-color: none; -fx-border-width: 2");
            lb1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            lb1.setLayoutX(130);
            lb1.setLayoutY(156);
            lb1.setScaleX(0.7);
            lb1.setScaleY(0.7);
            Back1.setLayoutX(800);
            Back1.setLayoutY(550);
            Back1.setStyle("-fx-background-color: #32cd32; -fx-font-size: 16px; -fx-font-size: 1.3em; -fx-font-weight: bold; -fx-padding: 10px 24px; -fx-width: 50%; -fx-border-width: 5px; -fx-border-radius: 2px;");

            //adding all items to the pane
            border.getChildren().addAll(lb1,tfMessage,text,Back1);

           return border;// returning the pane to create scene
        }


    }

    // create inner class experiment which extends parent class
    //This is the major class of this program containing Game code
    private class experiment extends Parent {

        //We are creating a 4X4 matrix for game content. In order to do so, we create 2 copies of each picture making total pairs 8
        private static final int num_of_pairs = 8;
        private Tile checker = null ;// to reference the location of previous tile
        private int point = 0;// point set the score of user
        private Label labels= new Label(); // points are placed within the labels for display
        private Button Back = new Button("Back");// Back button should be available in the main class

        //An array is created to hold all 10 images.
        private Image[] matchimage = {new Image(getClass().getResource("bob.jpg").toExternalForm()),
                new Image(getClass().getResource("janet.jpg").toExternalForm()),
                new Image(getClass().getResource("dexter.jpg").toExternalForm()),
                new Image(getClass().getResource("merryboy.jpg").toExternalForm()),
                new Image(getClass().getResource("marri.jpg").toExternalForm()),
                new Image(getClass().getResource("mathew.jpg").toExternalForm()),
                new Image(getClass().getResource("tommy.jpg").toExternalForm()),
                new Image(getClass().getResource("madmaddy.jpg").toExternalForm()),
                new Image(getClass().getResource("page.jpg").toExternalForm()),
                new Image(getClass().getResource("merrygirl.jpg").toExternalForm())};

        //create content for the GAME VIEW. This method returns the pane to the scene.
        private Parent createcontent(){

            Pane root = new Pane();//creating pane
            root.setPrefSize(1000, 600);

            //setting background image and its position
            BackgroundPosition bkg =new BackgroundPosition(Side.RIGHT,0,false,Side.TOP,0,true);
            Image image = new Image(getClass().getResource("game.jpg").toExternalForm());
            root.setBackground(new Background(new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, bkg, new BackgroundSize(200,200,false,false,false,true))));


            //creating back button properties
            Back.setLayoutX(150);
            Back.setLayoutY(450);
            Back.setStyle("-fx-background-color: #32cd32; -fx-font-size: 16px; -fx-font-size: 1.3em; -fx-font-weight: bold; -fx-padding: 10px 24px; -fx-width: 50%; -fx-border-width: 5px; -fx-border-radius: 2px;");


            // Create a label and set its properties
            Label label = new Label("Score");
            label.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.ITALIC, 47));
            labels.setFont(Font.font("Times New Roman",FontWeight.BOLD, FontPosture.ITALIC, 47));
            label.setTextFill(Color.WHITESMOKE);
            labels.setTextFill(Color.WHITESMOKE);
            labels.setLayoutX(100);
            labels.setLayoutY(300);
            label.setLayoutX(90);
            label.setLayoutY(250);
            root.getChildren().add(label);

            //Here an array list of tiles (which is another inner class of experiment) are created
            List<Tile> tiles = new ArrayList<>();
            //array list is being used to create all 16 tiles. Two tiles are creates for each image as it appears in pair
            for(int i = 0; i < num_of_pairs; i++){
                tiles.add(new Tile(matchimage[i]));
                tiles.add(new Tile(matchimage[i]));
            }

            //tiles are shuffled for randomness of picture
            Collections.shuffle(tiles);

            //Here array list is called to set position and to add it to pane
            for(int i = 0; i < tiles.size(); i++){
                Tile tile = tiles.get(i);
                tile.setTranslateX(150 * (i%4)+380);
                tile.setTranslateY(150* (i/4));
                root.getChildren().add(tile);
            }

            //back button and labels are placed on pane using codes displayed below
            root.getChildren().addAll(Back, labels);
            return root;
        }

        //  Tile class is created which extends Stack Pane because we are creating images are placed on top of rectangles
        private class Tile extends StackPane {
            private ImageView snap = new ImageView(); //snap is my Image View

            //Here we start the tile constructor
            private Tile(Image image){
                Rectangle rect = new Rectangle(150,150); // to create rectangle and set it properties
                rect.setFill(null);
                rect.setStroke(Color.BLACK);
                rect.setStrokeWidth(5.0);

                // add points to label withs score
                labels.setText(Integer.toString(point));

                //Tiles take an image and this image is added to snap. We set snap properties below
                snap.setImage(image);
                snap.setFitHeight(250);
                snap.setFitWidth(147);
                snap.setPreserveRatio(true);
                setAlignment(Pos.CENTER);

                //Finally snap and rectangles are placed on top of each other
                getChildren().addAll(rect, snap);

                //events set to happen when mouse pressed
                setOnMousePressed(e -> {

                    if (isOpen())//if tile is open ----> do nothing
                    {return ;}

                    // if previous tile (checker) is null, then checker get the reference of current tile and open tile
                    if (checker==null) {
                        checker = this;
                        open();}

                    // If checker contains something
                    else {
                        //If checker does not match with current tile, you lose points tile is flashed and closed
                        if(!compare(checker))
                        {
                            this.closelonger();
                            point=point-2;
                            this.displaypoint();
                            checker.closelonger();} //  need to close current and previous

                        //If checked matches tile stays open
                        else
                        {
                            checker.open();
                            this.open();
                            point=point+10;
                            this.displaypoint();
                        }

                        checker=null; //checker is finally set to null value
                    }

                });

                close();// by default all tiles are closed
            }
            // end of constructor

            //method to update user score
            private void displaypoint(){
                labels.setText(Integer.toString(point));
            }

            //return opacity of snap: 0 when closed and 1 when open
            private boolean isOpen(){
                return snap.getOpacity()==1;
            }


            //checking if image you clicked is same as before
            //compare current snap image is same as image referenced in the checker
            private boolean compare(Tile check){
                return snap.getImage().equals(check.snapper().getImage());
            }

            //Here we set the tile open by changing opacity of image
            private void open(){
                FadeTransition ft = new FadeTransition(Duration.millis(50), snap);
                ft.setToValue(1);// this is what keeps the image open --> set opacity to max
                ft.play();
            }

            //Here we close the tile by setting opacity to none. This method is used to flash the wrong snap only
            private void closelonger(){
                FadeTransition ft = new FadeTransition(Duration.millis(500), snap);
                ft.setDelay(Duration.millis(150));
                ft.setFromValue(1);
                ft.setToValue(0);// this is what keeps the image open --> set opacity to max
                ft.play();
            }

            //we close the tile by setting opacity to none. This happens in the beginning
            private void close(){
                FadeTransition ft = new FadeTransition(Duration.millis(5000), snap);
                ft.setDelay(Duration.millis(4999));
                ft.setToValue(0); // this is what keeps the image open --> set opacity to none
                ft.play();
                labels.setText(Integer.toString(0));
            }

            //This method return current snap. This is used to compare current image with previous
            private ImageView snapper(){
                return snap;
            }

        } //closing class Tile

    }//end of primary class

    // This method is used to display the stage with the changing scene.
    private void stages(Stage primaryStage, Scene s){
        primaryStage.setTitle("MyJavaFX"); // Set the stage title
        primaryStage.setScene(s); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
//End of program