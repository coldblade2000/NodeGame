package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Controller {

    static PannableCanvas canvas;
    public AnchorPane canvasAnchor;
    public SplitPane splitPane;


    @FXML
    public void initialize(){
        makeCanvas();
    }
    private void makeCanvas(){
        canvas = new PannableCanvas();

        // we don't want the canvas on the top/left in this example => just
        // translate it a bit
        canvas.setTranslateX(100);
        canvas.setTranslateY(100);

        // create sample nodes which can be dragged
        NodeGestures nodeGestures = new NodeGestures( canvas);
        Label label1 = new Label("Draggable node 1");
        label1.setTranslateX(10);
        label1.setTranslateY(10);
        label1.addEventFilter( MouseEvent.MOUSE_PRESSED, nodeGestures.getOnMousePressedEventHandler());
        label1.addEventFilter( MouseEvent.MOUSE_DRAGGED, nodeGestures.getOnMouseDraggedEventHandler());

        Label label2 = new Label("Draggable node 2");
        label2.setTranslateX(100);
        label2.setTranslateY(100);
        label2.addEventFilter( MouseEvent.MOUSE_PRESSED, nodeGestures.getOnMousePressedEventHandler());
        label2.addEventFilter( MouseEvent.MOUSE_DRAGGED, nodeGestures.getOnMouseDraggedEventHandler());

        Label label3 = new Label("Draggable node 3");
        label3.setTranslateX(200);
        label3.setTranslateY(200);
        label3.addEventFilter( MouseEvent.MOUSE_PRESSED, nodeGestures.getOnMousePressedEventHandler());
        label3.addEventFilter( MouseEvent.MOUSE_DRAGGED, nodeGestures.getOnMouseDraggedEventHandler());

        Circle circle1 = new Circle( 300, 300, 50);
        circle1.setStroke(Color.ORANGE);
        circle1.setFill(Color.ORANGE.deriveColor(1, 1, 1, 0.5));
        circle1.addEventFilter( MouseEvent.MOUSE_PRESSED, nodeGestures.getOnMousePressedEventHandler());
        circle1.addEventFilter( MouseEvent.MOUSE_DRAGGED, nodeGestures.getOnMouseDraggedEventHandler());

        Rectangle rect1 = new Rectangle(100,100);
        rect1.setTranslateX(450);
        rect1.setTranslateY(450);
        rect1.setStroke(Color.BLUE);
        rect1.setFill(Color.BLUE.deriveColor(1, 1, 1, 0.5));
        rect1.addEventFilter( MouseEvent.MOUSE_PRESSED, nodeGestures.getOnMousePressedEventHandler());
        rect1.addEventFilter( MouseEvent.MOUSE_DRAGGED, nodeGestures.getOnMouseDraggedEventHandler());

        canvas.getChildren().addAll(label1, label2, label3, circle1, rect1);

        canvasAnchor.getChildren().add(canvas);
        canvas.prefWidthProperty().bind(canvasAnchor.widthProperty());
        canvas.prefHeightProperty().bind(canvasAnchor.heightProperty());


        SceneGestures sceneGestures = new SceneGestures(Controller.getCanvas());
        canvas.addEventFilter( MouseEvent.MOUSE_PRESSED, sceneGestures.getOnMousePressedEventHandler());
        canvas.addEventFilter( MouseEvent.MOUSE_DRAGGED, sceneGestures.getOnMouseDraggedEventHandler());
        canvas.addEventFilter( ScrollEvent.ANY, sceneGestures.getOnScrollEventHandler());


        canvas.addGrid();
        /*AnchorPane.setTopAnchor(canvas,0.0);
        AnchorPane.setBottomAnchor(canvas,0.0);
        AnchorPane.setLeftAnchor(canvas,0.0);
        AnchorPane.setRightAnchor(canvas,0.0);*/
    }
    public static PannableCanvas getCanvas(){
        return canvas;
    }
}
