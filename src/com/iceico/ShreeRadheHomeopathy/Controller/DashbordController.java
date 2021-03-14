package com.iceico.ShreeRadheHomeopathy.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DashbordController implements Initializable {

	@FXML
	private ImageView btn_patientReg;

	@FXML
	private ImageView btn_viewList;

	@FXML
	private ImageView btn_AssList;

	@FXML
	private ComboBox<String> choicebox;

	@FXML
	private Button btnExit;

	@FXML
	private Label lbl_name;

	@FXML
	void onClickAssList(MouseEvent event) {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/com/iceico/ShreeRadheHomeopathy/Views/AssociatePatientView.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onClickRegistration(MouseEvent event) {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/com/iceico/ShreeRadheHomeopathy/Views/PatientDetails.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onClickViewList(MouseEvent event) {
		Stage primaryStage = new Stage();
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/com/iceico/ShreeRadheHomeopathy/Views/PatientTableView.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		choicebox.getItems().addAll("Register User", "Change Password", "Logout");
		choicebox.setOnAction(ActionEvent -> {
			switch (choicebox.getValue())// Switch on choiceBox value
			{
			case "Register User":
				Stage primaryStage = new Stage();
				try {
					Parent root = FXMLLoader.load(
							getClass().getResource("/com/iceico/ShreeRadheHomeopathy/Views/AdminRegistration.fxml"));
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.initStyle(StageStyle.TRANSPARENT);
					primaryStage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Stage stage = (Stage) btnExit.getScene().getWindow();
				stage.close();
				break;
			case "Change Password":

				try {
					Parent root = FXMLLoader
							.load(getClass().getResource("/com/iceico/ShreeRadheHomeopathy/Views/ChangePassword.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage2 = new Stage();
					primaryStage2.setScene(scene);
					primaryStage2.initStyle(StageStyle.TRANSPARENT);
					primaryStage2.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Stage stage2 = (Stage) btnExit.getScene().getWindow();
				stage2.close();

				break;
			case "Logout":
				Stage primaryStage1 = new Stage();
				try {
					Parent root = FXMLLoader
							.load(getClass().getResource("/com/iceico/ShreeRadheHomeopathy/Views/AdminLogin.fxml"));
					Scene scene = new Scene(root);
					primaryStage1.setScene(scene);
					primaryStage1.initStyle(StageStyle.TRANSPARENT);
					primaryStage1.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Stage stage1 = (Stage) btnExit.getScene().getWindow();
				stage1.close();
				break;
			}
		});

	}
}
