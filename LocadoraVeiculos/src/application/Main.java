package application;
	


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	private double x, y;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("TelaMain.fxml"));

			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);
			
			
			
			root.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					x = event.getSceneX();
					y = event.getSceneY();
				}
			});
			
			root.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					primaryStage.setX(event.getScreenX() - x);
					primaryStage.setY(event.getSceneY() - y);
				}
			});
			
			
			
			//TODO: Crie suas próprias teclas de atalho
			scene.setOnKeyPressed(e -> System.out.println(e.getCode()));
			
<<<<<<< HEAD
			primaryStage.setTitle("Clientes");
			primaryStage.setResizable(false);
=======
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setTitle("FTT Form");
>>>>>>> refs/remotes/origin/Rafael
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
