package PannableC;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * The canvas which holds all of the nodes of the application.
 */
public class PannableCanvas extends Pane {

    DoubleProperty myScale = new SimpleDoubleProperty(1.0);

    public PannableCanvas() {

        setPrefSize(600, 600);
        setStyle("-fx-background-color: lightgrey; -fx-border-color: blue;");

        // add scale transform
        scaleXProperty().bind(myScale);
        scaleYProperty().bind(myScale);

        // logging
        addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            System.out.println(
                    "canvas event: " + ( ((event.getSceneX() - getBoundsInParent().getMinX()) / getScale()) + ", scale: " + getScale())
            );
            System.out.println( "canvas bounds: " + getBoundsInParent());
        });

    }

    /**
     * Add a grid to the canvas, send it to back
     */
    public void addGrid() {

        double w = getBoundsInLocal().getWidth();
        double h = getBoundsInLocal().getHeight();

        // add grid
        Canvas grid = new Canvas(w, h);

        // don't catch mouse events
        grid.setMouseTransparent(true);

        GraphicsContext gc = grid.getGraphicsContext2D();

        gc.setStroke(Color.GRAY);
        gc.setLineWidth(1);

        // draw grid lines
        double offset = 50;
        for( double i=offset; i < w; i+=offset) {
            // vertical
            gc.strokeLine( i, 0, i, h);
            // horizontal
            gc.strokeLine( 0, i, w, i);
        }

        getChildren().add( grid);

        grid.toBack();
    }

    public double getScale() {
        return myScale.get();
    }

    /**
     * Set x/y scale
     * @param myScale
     */
    public void setScale( double scale) {
        myScale.set(scale);
    }

    /**
     * Set x/y pivot points
     * @param x
     * @param y
     */
    public void setPivot( double x, double y) {
        setTranslateX(getTranslateX()-x);
        setTranslateY(getTranslateY()-y);
    }
}



