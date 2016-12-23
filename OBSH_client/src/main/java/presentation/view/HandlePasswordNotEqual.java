package presentation.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HandlePasswordNotEqual {
	 public Stage passwordnotEqual(){
		 Stage stage = new Stage();
		 Text text = new Text("两次密码输入不一致");
		 Button button  = new Button("确定");
		 HBox hb = new HBox();
		 hb.getChildren().add(text);
		 hb.getChildren().add(button);
		 hb.setSpacing(20);
		 button.setAlignment(Pos.CENTER);
		 hb.setAlignment(Pos.CENTER);
		 Scene scene = new Scene(hb);
		 stage.setScene(scene);
		 stage.setMaxHeight(100);
		 stage.setMaxWidth(200);
		 stage.setMinHeight(100);
		 stage.setMinWidth(200);
		 stage.setResizable(false);
		 stage.show();
		 
		 button.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					stage.close();
				}
		 });
		return stage;
	 }
}
