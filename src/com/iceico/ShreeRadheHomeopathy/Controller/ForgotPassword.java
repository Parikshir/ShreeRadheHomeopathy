package com.iceico.ShreeRadheHomeopathy.Controller;

import com.iceico.ShreeRadheHomeopathy.Dao.DoctorDao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgotPassword {

	@FXML
	private Label lbl_password;

	@FXML
	private Button btn_submit;

	@FXML
	private TextField txt_user;

	@FXML
	private Button btnExit;

	@FXML
	void onClickSubmit(ActionEvent event) {
		String userName = txt_user.getText();

		DoctorDao doctorDao = new DoctorDao();
		String user = doctorDao.getPassword(userName);

		lbl_password.setText(user);

	}

	@FXML
	void onClickExit(ActionEvent event) {
		Stage stage = (Stage) btnExit.getScene().getWindow();
		stage.close();
	}
}
