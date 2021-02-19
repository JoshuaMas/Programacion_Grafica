package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Text;
public class Main extends Application {

    public static Circle cercle;
    public static Pane canvas;
    public static Pane gameOverScene;
    public static RectanglesI rectangleI;
    public static RectanglesD rectangleD;
    private Linea Linea;
    public final static int ampleRectangle=20;
    public final static int alturaRectangle=100;
    public final static int ampleCanvas=800;
    public final static int alturaCanvas=600;
    private Text scoreI;
    private Text scoreD;
    private Text player1Title;
    private Text player2Title;
    private Text start;
    private int Player1=0;
    private int Player2=0;
    public static Timeline loop;
    public static int radi=15;



    public void start( Stage primaryStage) {


        canvas = new Pane();
        final Scene escena = new Scene(canvas, ampleCanvas, alturaCanvas, Color.BLACK);
        primaryStage.setTitle("MayerPong");
        primaryStage.setScene(escena);
        primaryStage.show();

        cercle = new Circle(radi, Color.WHITE);
        cercle.setLayoutY(alturaCanvas/2);
        cercle.setLayoutX(ampleCanvas/2);
        Linea = new Linea(canvas, 2, alturaCanvas, Color.WHITE);
        rectangleI=new RectanglesI(canvas,ampleRectangle, alturaRectangle, Color.WHITE );
        rectangleD=new RectanglesD(canvas,ampleRectangle, alturaRectangle, Color.WHITE);



        //Texto de la puntuación
        texto();


        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            // Formula en graus
            double angle_en_radians = Math.toRadians(30);
            double velocitat = 3.00;
            double deltaX = velocitat * Math.cos(angle_en_radians);
            double deltaY = velocitat * Math.sin(angle_en_radians);

            // Simulació gravitatòria
            final Bounds limits = canvas.getBoundsInLocal();

            @Override
            public void handle(final ActionEvent t) {
                if (Player1 == 2){
                    loop.stop();

                    Pane gameOverScene = new Pane();
                    final Scene escena2 = new Scene(gameOverScene, ampleCanvas, alturaCanvas, Color.BLACK);
                    primaryStage.setTitle("gameOverMayerPong");
                    primaryStage.setScene(escena2);
                    primaryStage.show();
                    Text gameOver = new Text("Game Over");
                    gameOver.setFont(new Font("ComicSans",60));
                    gameOver.setFill(Color.WHITE);
                    gameOver.relocate(ampleCanvas/2-150, alturaCanvas/2-50);
                    gameOverScene.getChildren().addAll(gameOver);
                    Text win1 = new Text("Ha ganado el " + player1Title.getText());
                    win1.setFont(new Font("ComicSans",40));
                    win1.setFill(Color.WHITE);
                    win1.relocate(ampleCanvas*50/100-210,  alturaCanvas*22/100);
                    gameOverScene.getChildren().addAll(win1);

                    ResetPos();
                }else if (Player2 == 2){
                    loop.stop();
                    gameOverScene = new Pane();
                    Scene escena2 = new Scene(gameOverScene, ampleCanvas, alturaCanvas, Color.BLACK);
                    primaryStage.setTitle("gameOverMayerPong");
                    primaryStage.setScene(escena2);
                    primaryStage.show();
                    Text gameOver = new Text("Game Over");
                    gameOver.setFont(new Font("ComicSans",60));
                    gameOver.setFill(Color.WHITE);
                    gameOver.relocate(ampleCanvas/2-150, alturaCanvas/2-50);
                    gameOverScene.getChildren().addAll(gameOver);

                    Text win2 = new Text("Ha ganado el Jugador 2");
                    win2.setFont(new Font("ComicSans",40));
                    win2.setFill(Color.WHITE);
                    win2.relocate(ampleCanvas*50/100-210, alturaCanvas*22/100);
                    gameOverScene.getChildren().addAll(win2);
                    ResetPos();
                }
                else {

                cercle.setLayoutX(cercle.getLayoutX() + deltaX);
                cercle.setLayoutY(cercle.getLayoutY() + deltaY);



                final boolean alLimitDret = cercle.getLayoutX() >= (limits.getMaxX() - cercle.getRadius());
                final boolean alLimitEsquerra = cercle.getLayoutX() <= (limits.getMinX() + cercle.getRadius());
                final boolean alLimitInferior = cercle.getLayoutY() >= (limits.getMaxY() - cercle.getRadius());
                final boolean alLimitSuperior = cercle.getLayoutY() <= (limits.getMinY() + cercle.getRadius());

                final boolean alLimitInferiorRectangleI = rectangleI.Rectangle.getLayoutY() > (limits.getMaxY() - alturaRectangle + 20);
                final boolean alLimitSuperiorRectangleI = rectangleI.Rectangle.getLayoutY() <= (limits.getMinY()) - 20;
                final boolean alLimitInferiorRectangleD = rectangleD.Rectangle.getLayoutY() > (limits.getMaxY() - alturaRectangle + 20);
                final boolean alLimitSuperiorRectangleD = rectangleD.Rectangle.getLayoutY() <= (limits.getMinY() - 20);

                if (alLimitSuperiorRectangleI) {
                    rectangleI.mouAbaix();
                }
                if (alLimitInferiorRectangleI) {
                    rectangleI.mouAmunt();
                }
                if (alLimitSuperiorRectangleD) {
                    rectangleD.mouAbaix();
                }
                if (alLimitInferiorRectangleD) {
                    rectangleD.mouAmunt();
                }
                if (alLimitEsquerra) {
                    // Delta aleatori
                    // Multiplicam pel signe de deltaX per mantenir la trajectoria
                    //deltaX = Math.signum(deltaX)*(Math.random()*10+1);
                    int a = (int) (Math.random()*2+1);
                    if (a == 1){
                        deltaX *= +1;
                        deltaY *= -1;
                    }else {
                        deltaX *= +1;
                        deltaY *= +1;
                        ResetPos();
                        Player2++;
                        scoreD.setText("" + Player2);
                    }
                }
                if (alLimitDret) {
                    // Delta aleatori
                    // Multiplicam pel signe de deltaX per mantenir la trajectoria
                    //deltaX = Math.signum(deltaX)*(Math.random()*10+1);
                    int a = (int) (Math.random()*2+1);
                    if (a == 1){
                        deltaX *= +1;
                        deltaY *= -1;
                    }else{
                        deltaX *= +1;
                        deltaY *= +1;
                    Player1++;
                    scoreI.setText(Player1 + "");
                    ResetPos();
                }
                }

                    if (alLimitInferior || alLimitSuperior) {
                    // Delta aleatori
                    // Multiplicam pel signe de deltaX per mantenir la trajectoria
                    //deltaY = Math.signum(deltaY)*(Math.random()*10+1);
                    deltaY *= -1;
                     }
                    if (cercle.getBoundsInParent().intersects(rectangleI.Rectangle.getBoundsInParent())) {
                    deltaX *= -1;
                    }
                    if (cercle.getBoundsInParent().intersects(rectangleD.Rectangle.getBoundsInParent())) {
                    deltaX *= -1;
                    }
                }
            }
        };
        loop = new Timeline(new KeyFrame(Duration.millis(10), event));
        loop.setCycleCount(Timeline.INDEFINITE);
        canvas.requestFocus();
        canvas.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.SPACE)){
                loop.play();
                canvas.getChildren().remove(start);
            }
            switch (e.getCode()) {
                case UP:
                    rectangleI.mouAmunt();
                    break;
                case DOWN:
                    rectangleI.mouAbaix();
                    break;
                case W:
                    rectangleD.mouAmunt();
                    break;
                case S:
                    rectangleD.mouAbaix();
                    break;
            }
        });
    }
    public void texto(){
        scoreI = new Text(Player1+"");
        scoreI.setFont(new Font("ComicSans",40));
        scoreI.setFill(Color.WHITE);
        scoreI.relocate(ampleCanvas*40/100, 25);

        scoreD = new Text(""+Player2);
        scoreD.setFont(new Font("ComicSans",40));
        scoreD.setFill(Color.WHITE);
        scoreD.relocate(ampleCanvas*60/100-20, 25);

        //Texto de los players
        player1Title = new Text("Jugador1");
        player1Title.setFont(new Font("ComicSans",40));
        player1Title.setFill(Color.WHITE);
        player1Title.relocate(ampleCanvas*2/100, 25);
        player1Title.setStyle("-fx-font-weight:bold;");

        player2Title = new Text("Jugador2");
        player2Title.setFont(new Font("ComicSans",40));
        player2Title.setFill(Color.WHITE);
        player2Title.relocate(ampleCanvas*75/100, 25);
        player2Title.setStyle("-fx-font-weight:bold;");

        start = new Text("Pitja la tecla espai per començar");
        start.setFont(new Font("ComicSans",20));
        start.setFill(Color.WHITE);
        start.relocate(ampleCanvas/2-130, alturaCanvas*20/100);

        canvas.getChildren().addAll(cercle);
        canvas.getChildren().addAll(scoreI);
        canvas.getChildren().addAll(scoreD);
        canvas.getChildren().addAll(player1Title);
        canvas.getChildren().addAll(player2Title);
        canvas.getChildren().addAll(start);
    }
    public void ResetPos(){

        cercle.setLayoutY((Math.random()*(alturaCanvas-cercle.getRadius()))+cercle.getRadius());
        cercle.setLayoutX(ampleCanvas/2);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
    class RectanglesD {
    class PosicioR {
        int posX;
        int posY;
        public PosicioR(int x,int y) {
            this.posX=x;
            this.posY=y;
        }
    }
    PosicioR posicio;
    int velocitat=40;
    Pane panell;
    Node Rectangle;

    public RectanglesD(Pane panell,int posX,int posY, Color color) {
        posicio = new PosicioR(posX, posY);
        this.panell = panell;
        this.Rectangle = new Rectangle(posicio.posX  , posicio.posY , color);
        final Bounds limits = panell.getBoundsInLocal();
        posicio.posX = (int) limits.getMaxX() -posX*2;
        posicio.posY = (int) limits.getMaxY() /2 - (posY/2);
        this.Rectangle.setLayoutX(posicio.posX);
        this.Rectangle.setLayoutY(posicio.posY);
        this.panell.getChildren().add(this.Rectangle);

    }

    public void mouAmunt() {
        posicio.posY=posicio.posY-this.velocitat;
        this.repinta();
    }

    /**
     * Mou bolla cap abaix
     */
    public void mouAbaix() {
        posicio.posY=posicio.posY+this.velocitat;
        this.repinta();
    }
    private void repinta() {
        this.Rectangle.setLayoutX(posicio.posX);
        this.Rectangle.setLayoutY(posicio.posY);
    }
}
    class RectanglesI {
    class PosicioR {
        int posX;
        int posY;
        public PosicioR(int x,int y) {
            this.posX=x;
            this.posY=y;
        }
    }
    PosicioR posicio;
    int velocitat=40;
    Pane panell;
    Node Rectangle;

    public RectanglesI(Pane panell,int posX,int posY, Color color) {
        posicio = new PosicioR(posX, posY);
        this.panell = panell;
        this.Rectangle = new Rectangle(posicio.posX  , posicio.posY , color);
        final Bounds limits = panell.getBoundsInLocal();
        posicio.posX = (int) limits.getMinX() + posX ;
        posicio.posY = (int) limits.getMaxY() /2 - posY/2;
        this.Rectangle.setLayoutX(posicio.posX);
        this.Rectangle.setLayoutY(posicio.posY);
        this.panell.getChildren().add(this.Rectangle);

    }

    public void mouAmunt() {
        posicio.posY=posicio.posY-this.velocitat;
        this.repinta();
    }

    /**
     * Mou bolla cap abaix
     */
    public void mouAbaix() {
        posicio.posY=posicio.posY+this.velocitat;
        this.repinta();
    }
    private void repinta() {

        this.Rectangle.setLayoutY(posicio.posY);
    }
    public void LimitY(){
        posicio.posY=posicio.posY-this.velocitat;
        repinta();
    }
}
    class Linea {
    class PosicioR {
        int posX;
        int posY;
        public PosicioR(int x,int y) {
            this.posX=x;
            this.posY=y;
        }
    }
    sample.Linea.PosicioR posicio;

    Pane panell;
    Node Line;

    public Linea(Pane panell,int posX,int posY, Color color) {
        posicio = new sample.Linea.PosicioR(posX, posY);
        this.panell = panell;
        this.Line = new Rectangle(posicio.posX  , posicio.posY , color);
        posicio.posX = Main.ampleCanvas/2;
        posicio.posY = 0;
        this.Line.setLayoutX(posicio.posX);
        this.Line.setLayoutY(posicio.posY);
        this.panell.getChildren().add(this.Line);
    }
}