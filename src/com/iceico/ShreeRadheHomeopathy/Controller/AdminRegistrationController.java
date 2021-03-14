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
import javafx.stage.StageStyle;

public class AdminRegistrationController {

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtMobileNo;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private TextField txtAddress;

	@FXML
	private Button btnClear;

	@FXML
	private TextField txtEmail;

	@FXML
	private PasswordField txtConfirmPassword;

	@FXML
	private Button btn_register;

	@FXML
	private TextField txtLastName;

	@FXML
	private Button btnExit;

	@FXML
	void onClickExit(ActionEvent event) {
		Stage stage = (Stage) btnExit.getScene().getWindow();
		stage.close();
	}

	static String getAlphaNumericString() {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		int n = 4;
		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	@FXML
	void onClickClear(ActionEvent event) {
		txtFirstName.clear();
		txtLastName.clear();
		txtMobileNo.clear();
		txtAddress.clear();
		txtConfirmPassword.clear();
		txtPassword.clear();
		txtEmail.clear();
	}

	@FXML
	void onClickRegister(ActionEvent event) {
		if (txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| txtAddress.getText().isEmpty() || txtMobileNo.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please, enter all the required fields");
			return;

		}
		DoctorDao doctorDao = new DoctorDao();
		DoctorRegistration doctorRegistration = new DoctorRegistration();

		String fullName = txtFirstName.getText() + " " + txtLastName.getText();
		String userName = txtFirstName.getText().toLowerCase() + txtLastName.getText().toLowerCase()
				+ getAlphaNumericString();
		doctorRegistration.setFullName(fullName);
		doctorRegistration.setUsername(userName);
		doctorRegistration.setEmailid(txtEmail.getText());

		doctorRegistration.setMobileNo(txtMobileNo.getText());
		String password = txtPassword.getText();
		String confirmPassword = txtConfirmPassword.getText();
		doctorRegistration.setPassword(txtPassword.getText());
		doctorRegistration.setConfirmPassword(txtConfirmPassword.getText());
		doctorRegistration.setAddress(txtAddress.getText());

		if (!confirmPassword.equals(password)) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Confirm password is not the same as password");
			return;
		}

		doctorDao.saveDoctorRegistration(doctorRegistration);
		showAlert(Alert.AlertType.INFORMATION, "Form Registered",
				"Registration Successful \n Your Username : " + userName);
		{

			Stage primaryStage = new Stage();
			try {
				Parent root = FXMLLoader
						.load(getClass().getResource("/com/iceico/ShreeRadheHomeopathy/Views/AdminLogin.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.initStyle(StageStyle.TRANSPARENT);
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		txtFirstName.clear();
		txtLastName.clear();
		txtMobileNo.clear();
		txtAddress.clear();
		txtConfirmPassword.clear();
		txtPassword.clear();
		txtEmail.clear();

		Stage stage = (Stage) btn_register.getScene().getWindow();
		stage.close();
		return;

	}

	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}

}
