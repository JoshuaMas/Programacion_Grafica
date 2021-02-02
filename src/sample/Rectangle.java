package sample;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.awt.*;

class Rectangles {
        class PosicioR {
            int posX;
            int posY;
            public PosicioR(int x,int y) {
                this.posX=x;
                this.posY=y;
            }
        }
       Rectangles.PosicioR posicio;
        int velocitat=10;
        Pane panell;
        Node Rectangle;

        public Rectangles(Pane panell,int posX,int posY, Color color) {
            posicio = new Rectangles.PosicioR(posX, posY);
            this.panell = panell;
            this.Rectangle = new Rectangle(posicio.posX  , posicio.posY , color);
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
