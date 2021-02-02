package sample;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Rectangle {
        class PosicioR {
            int posX;
            int posY;
            public PosicioR(int x,int y) {
                this.posX=x;
                this.posY=y;
            }
        }
       Rectangle.PosicioR posicio;
        int velocitat=10;
        Pane panell;
        Node Rectangle;

        public Rectangle(Pane panell,int posX,int posY) {
            posicio = new Rectangle.PosicioR(posX, posY);
            this.panell = panell;
            this.Rectangle = new javafx.scene.shape.Rectangle(posicio.posX - 20 , posicio.posY - 20);
            posicio.posX = 0;
            posicio.posY = 0;
            this.Rectangle.setLayoutX(posicio.posX);
            this.Rectangle.setLayoutY(posicio.posY);
            this.panell.getChildren().add(this.Rectangle);

        }

    public void mouAmunt() {
        posicio.posY=posicio.posY-this.velocitat;
        this.repinta();
        System.out.println("Amunt pitjat");
    }

    /**
     * Mou bolla cap abaix
     */
    public void mouAbaix() {
        posicio.posY=posicio.posY+this.velocitat;
        this.repinta();
        System.out.println("Abaix pitjat");
    }
    private void repinta() {
        this.Rectangle.setLayoutX(posicio.posX);
        this.Rectangle.setLayoutY(posicio.posY);
    }
}
