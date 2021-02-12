package sample;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Text;
public class Main extends Application {

    public static Circle cercle;
    public static Pane canvas;
    public static RectanglesI rectangleI;
    public static RectanglesD rectangleD;
    private Linea Linea;
    public static int ampleRectangle=20;
    public static int alturaRectangle=100;
    public static int ampleCanvas=1600;
    public static int alturaCanvas=800;
    private Text scoreI;
    private Text scoreD;
    private int Player1=0;
    private int Player2=0;



    public void start( Stage primaryStage) {
        boolean start= false;

        canvas = new Pane();
        final Scene escena = new Scene(canvas, ampleCanvas, alturaCanvas, Color.BLACK);

        primaryStage.setTitle("Bolla Rebotant");
        primaryStage.setScene(escena);
        primaryStage.show();

        int radi=15;
        cercle = new Circle(radi, Color.WHITE);
        cercle.setLayoutY(400);
        cercle.setLayoutX(800);

        Linea = new Linea(canvas, 5, alturaCanvas, Color.WHITE);



        rectangleI=new RectanglesI(canvas,ampleRectangle, alturaRectangle, Color.WHITE );
        //rectangleI.Rectangle.setLayoutX(20);
        //rectangleI.Rectangle.setLayoutY(350);


        rectangleD=new RectanglesD(canvas,ampleRectangle, alturaRectangle, Color.WHITE);
        //rectangleD.Rectangle.setLayoutX(960);
        //rectangleD.Rectangle.setLayoutY(350);


        //Texto de la puntuación
        scoreI = new Text(Player1+"");
        scoreI.setFont(new Font("ComicSans",60));
        scoreI.setFill(Color.WHITE);
        scoreI.relocate(750, 25);
        scoreI.setStyle("-fx-font-weight:bold;");

        scoreD = new Text(""+Player2);
        scoreD.setFont(new Font("ComicSans",60));
        scoreD.setFill(Color.WHITE);
        scoreD.relocate(820, 25);
        scoreD.setStyle("-fx-font-weight:bold;");

        //Texto de los players
        Text player1Title = new Text("Player1");
        player1Title.setFont(new Font("ComicSans",60));
        player1Title.setFill(Color.WHITE);
        player1Title.relocate(50, 25);
        player1Title.setStyle("-fx-font-weight:bold;");

        Text player2Title = new Text("Player2");
        player2Title.setFont(new Font("ComicSans",60));
        player2Title.setFill(Color.WHITE);
        player2Title.relocate(ampleCanvas-270, 25);
        player2Title.setStyle("-fx-font-weight:bold;");

        Text gameOver = new Text("Game Over!");
        gameOver.setFont(new Font("ComicSans",60));
        gameOver.setFill(Color.WHITE);
        gameOver.relocate(ampleCanvas-230, 25);
        gameOver.setStyle("-fx-font-weight:bold;");

        canvas.getChildren().addAll(cercle);
        canvas.getChildren().addAll(scoreI);
        canvas.getChildren().addAll(scoreD);
        canvas.getChildren().addAll(player1Title);
        canvas.getChildren().addAll(player2Title);
        //canvas.getChildren().addAll(gameOver);
        //canvas.getChildren().addAll(rectangleI);
        //canvas.getChildren().addAll(rectangleD);

        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {


            // Formula en radians
            //double deltaX = 3*Math.cos(Math.PI/3);
            //double deltaY = 3*Math.sin(Math.PI/3);

            // Formula en graus
            double angle_en_radians =Math.toRadians(30);
            double velocitat=5.00;
            double deltaX = velocitat*Math.cos(angle_en_radians);
            double deltaY = velocitat*Math.sin(angle_en_radians);

            // Simulació gravitatòria
            final Bounds limits = canvas.getBoundsInLocal();

            @Override
            public void handle(final ActionEvent t) {
                /*if (Player1<2&&Player2<2){*/
                //cercle.setLayoutX(cercle.getLayoutX() + deltaX/2);

                cercle.setLayoutX(cercle.getLayoutX() + deltaX);
                //cercle.setLayoutY(cercle.getLayoutY() + deltaY/3);
                cercle.setLayoutY(cercle.getLayoutY() + deltaY);
                //System.out.println(cercle.getLayoutX()+":"+cercle.getLayoutY());



                final boolean alLimitDret = cercle.getLayoutX() >= (limits.getMaxX() - cercle.getRadius());
                final boolean alLimitEsquerra = cercle.getLayoutX() <= (limits.getMinX() + cercle.getRadius());
                final boolean alLimitInferior = cercle.getLayoutY() >= (limits.getMaxY() - cercle.getRadius());
                final boolean alLimitSuperior = cercle.getLayoutY() <= (limits.getMinY() + cercle.getRadius());

                final boolean alLimitInferiorRectangleI = rectangleI.Rectangle.getLayoutY() > (limits.getMaxY()-alturaRectangle+20 );
                final boolean alLimitSuperiorRectangleI = rectangleI.Rectangle.getLayoutY() <= (limits.getMinY())-20;
                final boolean alLimitInferiorRectangleD = rectangleD.Rectangle.getLayoutY() > (limits.getMaxY()-alturaRectangle+20 );
                final boolean alLimitSuperiorRectangleD = rectangleD.Rectangle.getLayoutY() <= (limits.getMinY()-20);

                if (alLimitSuperiorRectangleI) {
                    rectangleI.mouAbaix();
            }
                if (alLimitInferiorRectangleI) {
                    rectangleI.mouAmunt();
                }
                if (alLimitSuperiorRectangleD){
                    rectangleD.mouAbaix();
                }
                if (alLimitInferiorRectangleD){
                    rectangleD.mouAmunt();
                }
                if (alLimitEsquerra) {
                    // Delta aleatori
                    // Multiplicam pel signe de deltaX per mantenir la trajectoria
                    //deltaX = Math.signum(deltaX)*(Math.random()*10+1);

                    deltaX *= -1;
                    ResetPos();
                    Player2++;
                    scoreD.setText(""+Player2);
                }
                if (alLimitDret) {
                    // Delta aleatori
                    // Multiplicam pel signe de deltaX per mantenir la trajectoria
                    //deltaX = Math.signum(deltaX)*(Math.random()*10+1);

                    deltaX *= -1;
                    Player1++;
                    scoreI.setText(Player1+"");
                    ResetPos();

                }

                if (alLimitInferior || alLimitSuperior) {
                    // Delta aleatori
                    // Multiplicam pel signe de deltaX per mantenir la trajectoria
                    //deltaY = Math.signum(deltaY)*(Math.random()*10+1);
                    deltaY *= -1;
                }
                if(cercle.getBoundsInParent().intersects(rectangleI.Rectangle.getBoundsInParent())) {
                    deltaX *= -1;
                }
                if(cercle.getBoundsInParent().intersects(rectangleD.Rectangle.getBoundsInParent())) {
                    deltaX *= -1;
                }
                System.out.println(velocitat);
            /*}else{
                    canvas.getChildren().addAll(gameOver);
                    try{
                        Thread.sleep(2000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    } {
                        System.out.println("Game Over");
                    }
                }*/

            }
        }
 ));



        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
        canvas.requestFocus();
        canvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    rectangleI.mouAmunt();
                    break;
                case S:
                    rectangleI.mouAbaix();
                    break;
                case UP:
                    rectangleD.mouAmunt();
                    break;
                case DOWN:
                    rectangleD.mouAbaix();
                    break;
                }
            });
        }

    public void gameOver(){
    }
    public void ResetPos(){
        cercle.setLayoutY(400);
        cercle.setLayoutX(800);
    }
    public static void main(String[] args) {
        launch(args);
    }
}