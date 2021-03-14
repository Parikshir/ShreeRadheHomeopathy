package com.iceico.ShreeRadheHomeopathy.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.iceico.ShreeRadheHomeopathy.Dao.PatientDao;
import com.iceico.ShreeRadheHomeopathy.Modal.Patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PatientRegistrationController implements Initializable {

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtLastName;

	@FXML
	private TextField txtMobileno;

	@FXML
	private DatePicker datepickerDate;

	@FXML
	private Button btnSubmit;

	@FXML
	private Button btn_Exit;

	@FXML
	private Button btnClear;

	@FXML
	private TextArea txt_precscription_1;

	@FXML
	private TextArea txt_precscription_2;

	@FXML
	private TextArea txt_precscription_3;

	@FXML
	private TextArea txt_precscription_4;

	@FXML
	private TextArea txt_precscription_5;

	private PatientDao patientDao = new PatientDao();

	@FXML
	void onClickExit(ActionEvent event) {
		Stage stage = (Stage) btn_Exit.getScene().getWindow();
		stage.close();

	}

	@FXML
	void btnClear(ActionEvent event) {
		txtFirstName.clear();
		txtLastName.clear();
		txtMobileno.clear();
		datepickerDate.getEditor().clear();
		datepickerDate.setValue(null);
		txt_precscription_1.clear();
		txt_precscription_2.clear();
		txt_precscription_3.clear();
		txt_precscription_4.clear();
		txt_precscription_5.clear();
	}

	@FXML
	void btnSubmitSaveDetails(ActionEvent event) {
		Patient patient = new Patient();

		String date = datepickerDate.getValue().toString();
		patient.setFullName(txtFirstName.getText() + " " + txtLastName.getText());
		patient.setMobile_no(txtMobileno.getText());
		patient.setAdmit_date(date);
		patient.setPrescription1(txt_precscription_1.getText());
		patient.setPrescription2(txt_precscription_2.getText());
		patient.setPrescription3(txt_precscription_3.getText());
		patient.setPrescription4(txt_precscription_4.getText());
		patient.setPrescription5(txt_precscription_5.getText());
		patient.setFlag(false);
		patientDao.savePatient(patient);

		btnSubmit.getOnMouseClicked();
		{
			txtFirstName.clear();
			txtLastName.clear();
			txtMobileno.clear();
			datepickerDate.getEditor().clear();
			datepickerDate.setValue(null);
			txt_precscription_1.clear();
			txt_precscription_2.clear();
			txt_precscription_3.clear();
			txt_precscription_4.clear();
			txt_precscription_5.clear();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Saved");
			alert.setHeaderText(null);
			alert.setContentText("Patient Saved");
			alert.showAndWait();

			Stage stage = (Stage) btnSubmit.getScene().getWindow();
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

			stage.close();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		datepickerDate.setValue(LocalDate.now());
	}

}
