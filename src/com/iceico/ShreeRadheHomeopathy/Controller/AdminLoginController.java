package com.iceico.ShreeRadheHomeopathy.Controller;

import com.iceico.ShreeRadheHomeopathy.Dao.DoctorDao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminLoginController {

	@FXML
	private TextField txtUserName;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Button btn_login;

	@FXML
	private Hyperlink forgotPassword;

	@FXML
	private Button btnExit;

	DoctorDao doctorDao = new DoctorDao();

	@FXML
	void onClickLogin(ActionEvent event) {
		String userName = txtUserName.getText();
		String passWord = txtPassword.getText();
		DoctorDao doctorDao = new DoctorDao();
		String pass = doctorDao.getPassword(userName);
		if (passWord.equals(pass)) {
			try {
				Parent root = FXMLLoader
						.load(getClass().getResource("/com/iceico/ShreeRadheHomeopathy/Views/Dashboard.fxml"));
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initStyle(StageStyle.TRANSPARENT);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}

			Stage stage = (Stage) btn_login.getScene().getWindow();
			stage.close();

		} else {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Wrong Username or Password");
			txtPassword.clear();
			txtUserName.clear();
			return;
		}
	}

	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}

	@FXML
	void onClickRedirect(ActionEvent event) {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/com/iceico/ShreeRadheHomeopathy/Views/ForgotPassword.fxml"));
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
	void onClickExit(ActionEvent event) {
		Stage stage = (Stage) btnExit.getScene().getWindow();
		stage.close();
	}

}
