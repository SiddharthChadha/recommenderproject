package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application{

    Scene scene1,scene2,scene3; // SCENE 2 - LOGIN FORM , SCENE 3 - SIGN UP FORM
    Scene scene4,scene5;
    Stage window;


    public void createScene1() //Main Page
    {
        GridPane grid1=new GridPane();
        grid1.setPadding(new Insets(10,10,10,10));
        grid1.setVgap(16);
        grid1.setHgap(10);

        Label lbl1=new Label("Welcome To Our App");
        GridPane.setConstraints(lbl1,0,0);

        Button loginButton = new Button("Login"); // GO TO SCENE 2
        GridPane.setConstraints(loginButton,0,1);


        Button signupButton = new Button("Sign Up"); // GO TO SCENE 3
        GridPane.setConstraints(signupButton,0,2);
        grid1.getChildren().addAll(lbl1,loginButton,signupButton);


        scene1=new Scene(grid1,500,300);

        loginButton.setOnAction(e -> {
            System.out.println("Login");
            window.setScene(scene2);
        });

        signupButton.setOnAction(e ->{
            System.out.println("Signup");
            window.setScene(scene3);
        });
    }
    public void createScene2() //Login Form
    {
        GridPane grid2=new GridPane();
        grid2.setPadding(new Insets(10,10,10,10));
        grid2.setHgap(8);
        grid2.setVgap(10);

        Label name_lbl=new Label("Username");
        GridPane.setConstraints(name_lbl,0,0);
        TextField name_entry=new TextField();
        GridPane.setConstraints(name_entry,1,0);

        Label pass_lbl = new Label("Password");
        GridPane.setConstraints(pass_lbl,0,1);
        PasswordField pass_entry=new PasswordField();
        pass_entry.setPromptText("Your password");
        GridPane.setConstraints(pass_entry,1,1);

        Label message =new Label();
        GridPane.setConstraints(message,1,4);

        Button login_btn1=new Button("LOGIN");
        GridPane.setConstraints(login_btn1,1,3);

        login_btn1.setOnAction(e->{
            if(grid2.getChildren().contains(message)) {
                grid2.getChildren().remove(message);
            }
           boolean temp= JavaMongoConnection.loginUser(name_entry.getText(),pass_entry.getText());

           if(temp==true)
           {
              window.setScene(scene4);
           }
           else
           {
               message.setText("Username or password is incorrect");
           }
            grid2.getChildren().add(message);
        });

        Button back_btn=new Button("BACK");
        GridPane.setConstraints(back_btn,2,3);
        back_btn.setOnAction(e->{
            window.setScene(scene1);
        });

        grid2.getChildren().addAll(name_lbl,pass_lbl,back_btn,name_entry,pass_entry,login_btn1);
        scene2=new Scene(grid2,600,200);

    }
    public void createScene3() //User Profile Submission form
    {
        GridPane grid3=new GridPane();
        grid3.setPadding(new Insets(10,10,10,10));
        grid3.setHgap(8);
        grid3.setVgap(10);

        Label title=new Label("User Information Form ");
        //title.setTextAlignment(Pos.);

        GridPane.setConstraints(title,0,0);
        title.setFont(Font.font(null, FontWeight.EXTRA_BOLD,23));


        Label nameLabel=new Label("Username");
        GridPane.setConstraints(nameLabel,0,1);
        TextField nameEntry=new TextField();
        GridPane.setConstraints(nameEntry,1,1);

        Label passLabel=new Label("Password");
        GridPane.setConstraints(passLabel,0,2);
        PasswordField passEntry=new PasswordField();
        passEntry.setPromptText("Your password");
        GridPane.setConstraints(passEntry,1,2);

        Label contactLabel=new Label("Contact Info");
        GridPane.setConstraints(contactLabel,0,3);
        TextField contactEntry=new TextField();
        GridPane.setConstraints(contactEntry,1,3);


        Label locLabel=new Label("Location");
        GridPane.setConstraints(locLabel,0,4);
        TextField locEntry=new TextField();
        GridPane.setConstraints(locEntry,1,4);


        Label interestLabel=new Label("Interests");
        GridPane.setValignment(interestLabel, VPos.TOP);
        GridPane.setConstraints(interestLabel,0,5);
        ListView<String> listView; 
        listView=new ListView<>();
        listView.getItems().addAll("Java","C++","Python",
                  "Web Development","Game Development", "Node.js", "Artificial Intelligence", "Machine Learning",
                "Data Science", "Security", "Databases", "UX/UI"
        );
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        GridPane.setConstraints(listView,1,5);
        listView.setMaxHeight(300);
        grid3.getChildren().add(listView);

        Label message3=new Label();
        GridPane.setConstraints(message3,0,7);


        grid3.getChildren().addAll(title,nameLabel,nameEntry,contactLabel,contactEntry,passLabel,passEntry,locEntry,locLabel,interestLabel);

        Button backButton = new Button("BACK");
        GridPane.setConstraints(backButton,1,6);
        backButton.setOnAction(e->{
            window.setScene(scene1);
        });

        Button submit = new Button("SUBMIT");
        GridPane.setConstraints(submit,0,6);

        submit.setOnAction(e->{
            boolean result= ConfirmBox.display("Submit Info","Are you sure you want to submit this information?");
            if ( result ==true)
            {

                if(grid3.getChildren().contains(message3))
                {
                    grid3.getChildren().remove(message3);

                }

                ObservableList<String> interests;
                interests=listView.getSelectionModel().getSelectedItems();
                List<String> interests1=new ArrayList<>();
                for(String m:interests)
                {
                    interests1.add(m);

                }

               sample.UserProfile user1 = new sample.UserProfile(nameEntry.getText(),passEntry.getText(),
                       contactEntry.getText(),locEntry.getText(),interests1);
                if(JavaMongoConnection.addUserDB(user1) ==true)
                {
                    message3.setText("User Profile Created");
                    grid3.getChildren().add(message3);
                }

                else
                {
                    AlertBox.display("ERROR!","Username Already Exists");
                }

            }

        });

        grid3.getChildren().addAll(submit,backButton);

        scene3=new Scene(grid3,600,600);
    }

    public void createScene4() //Page that appears after User logs in
    {

        GridPane grid4=new GridPane();
        grid4.setPadding(new Insets(10,10,10,10));
        grid4.setHgap(8);
        grid4.setVgap(10);

        Label text=new Label("You are now logged in!");
        GridPane.setConstraints(text,0,0);

        Button createProjectButton = new Button("Create a new Project");
        createProjectButton.setOnAction(e->{window.setScene(scene5); });
        GridPane.setConstraints(createProjectButton,0,1);

        Button editProjectButton = new Button("Edit an existing project");
        GridPane.setConstraints(editProjectButton,0,2);

        grid4.getChildren().addAll(text,createProjectButton,editProjectButton);
        scene4=new Scene(grid4,600,600);

    }
    public void createScene5() //Project Profile Form to add a project
    {

        GridPane grid5=new GridPane();
        grid5.setPadding(new Insets(10,10,10,10));
        grid5.setHgap(8);
        grid5.setVgap(10);

        Label title=new Label("CREATING A NEW PROJECT");
        GridPane.setConstraints(title,0,0);
        title.setFont(Font.font(null, FontWeight.EXTRA_BOLD,23));


        Label projectName=new Label("Project Name");
        GridPane.setConstraints(projectName,0,1);
        TextField projectNameEntry=new TextField();
        GridPane.setConstraints(projectNameEntry,1,1);



        Label restrictionLabel =new Label("Project Restriction Mode");
        GridPane.setConstraints(restrictionLabel,0,2);
        ChoiceBox<String> restriction =new ChoiceBox<>();
        GridPane.setConstraints(restriction,1,2);

        restriction.getItems().addAll("Public","Private");
        grid5.getChildren().addAll(title,projectName,projectNameEntry,restriction,restrictionLabel);
        scene5=new Scene(grid5,600,600);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        window=primaryStage;
        window.setTitle("PROJECT RECOMMENDER SYSTEM");
        JavaMongoConnection.createConnection();
        createScene1(); // SCENE 1 FOR APP MAIN PAGE
        createScene2(); // SCENE 2 FOR LOGIN FORM
        createScene3(); // SCENE 3 FOR SIGNUP FORM
        createScene4(); // SCENE 4 FOR Page that appears after User logs in
        createScene5(); // SCENE 5 FOR CREATING A NEW PROJECT FORM

        window.setScene(scene1);
        window.show();

    }


    public static void main(String[] args) {

        launch(args);
    }
}
