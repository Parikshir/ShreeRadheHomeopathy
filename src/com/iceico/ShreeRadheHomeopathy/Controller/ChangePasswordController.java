package com.iceico.ShreeRadheHomeopathy.Controller;

import com.iceico.ShreeRadheHomeopathy.Dao.DoctorDao;
import com.iceico.ShreeRadheHomeopathy.Modal.DoctorRegistration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangePasswordController {

	@FXML
	private TextField txtUserName;

	@FXML
	private PasswordField pwdPassword;

	@FXML
	private PasswordField pwdPassword1;

	@FXML
	private Button btnExit;

	@FXML
	private Button btnSubmit;

	@FXML
	void onCliclUpdate(ActionEvent event) {
		String username = txtUserName.getText();
		DoctorDao doctorDao = new DoctorDao();

		DoctorRegistration doctorRegistration = doctorDao.getUsername(username);
		String user = doctorRegistration.getUsername();
		if (user != null) {
			String password = pwdPassword.getText();
			String conPassword = pwdPassword1.getText();
			if (password.equals(conPassword)) {
				doctorRegistration.setPassword(pwdPassword.getText());
				doctorRegistration.setConfirmPassword(pwdPassword1.getText());
				doctorDao.updateDoctor(doctorRegistration);
				showAlert(Alert.AlertType.INFORMATION, "Password Changed", "Password Successfuly Changes");
				Stage primaryStage = new Stage();
				try {
					Parent root = FXMLLoader
							.load(getClass().getResource("/com/iceico/ShreeRadheHomeopathy/Views/AdminLogin.fxml"));
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Stage stage = (Stage) btnExit.getScene().getWindow();
				stage.close();

			} else {
				showAlert(Alert.AlertType.INFORMATION, "Password Error", "Password Not Matched");
			}
		} else {
			showAlert(Alert.AlertType.INFORMATION, "User Error", "Wrong User Name");
		}

	}

	@FXML
	void onClickExit(ActionEvent event) {
		Stage stage = (Stage) btnExit.getScene().getWindow();
		stage.close();
	}

	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}

}
