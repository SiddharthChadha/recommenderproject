package sample;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {

    Button b1,b2;
   static boolean answer;
    public static boolean display(String title, String message){

        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Button yes_btn=new Button("Yes");
        Button no_btn=new Button("No");

        yes_btn.setOnAction(event -> {
            answer=true;
            window.close();
        });
        no_btn.setOnAction(event -> {
            answer=false;
            window.close();
        });

        Label label=new Label();
        label.setText(message);

        VBox layout=new VBox(10);
        layout.getChildren().addAll(label,yes_btn,no_btn);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout,450,200);
        window.setScene(scene);

        window.showAndWait();

        return answer;


    }
}
