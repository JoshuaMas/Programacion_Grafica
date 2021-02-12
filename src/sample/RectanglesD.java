package sample;


import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.awt.*;

class RectanglesD {
    class PosicioR {
        int posX;
        int posY;
        public PosicioR(int x,int y) {
            this.posX=x;
            this.posY=y;
        }
    }
    RectanglesD.PosicioR posicio;
    int velocitat=40;
    Pane panell;
    Node Rectangle;

    public RectanglesD(Pane panell,int posX,int posY, Color color) {
        posicio = new RectanglesD.PosicioR(posX, posY);
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