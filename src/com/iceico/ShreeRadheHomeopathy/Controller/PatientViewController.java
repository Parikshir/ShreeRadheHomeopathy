package com.iceico.ShreeRadheHomeopathy.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.iceico.ShreeRadheHomeopathy.Dao.PatientDao;
import com.iceico.ShreeRadheHomeopathy.Modal.Patient;
import com.iceico.ShreeRadheHomeopathy.Modal.PatientDetails;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PatientViewController implements Initializable {

	@FXML
	private TextField txt_fullName;

	@FXML
	private TextField txt_mobileNo;

	@FXML
	private TextField txt_date;

	@FXML
	private TextField txt_firstName;

	@FXML
	private TextField txt_MobileNo;

	@FXML
	private DatePicker admit_date;

	@FXML
	private TextField txt_lastName;

	@FXML
	private Button btn_UpdateDetails;

	@FXML
	private Button btn_Clear;

	@FXML
	private Button btnExit;

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

	@FXML
	void onClickClear(ActionEvent event) {
		txt_firstName.clear();
		txt_lastName.clear();
		txt_MobileNo.clear();
		txt_precscription_1.clear();
		txt_precscription_2.clear();
		txt_precscription_3.clear();
		txt_precscription_4.clear();
		txt_precscription_5.clear();
		admit_date.getEditor().clear();
		admit_date.setValue(null);

	}

	PatientDao patientdao = new PatientDao();

	Long id;

	public long getDetails(Patient patient) {

		id = patient.getId();

		String fullName = patient.getFullName();
		txt_fullName.setText(fullName);

		String mobileNumber = patient.getMobile_no();
		txt_mobileNo.setText(mobileNumber);

		String admitDate = patient.getAdmit_date();
		txt_date.setText(admitDate);

		return id;
	}

	@FXML
	void onClickExit(ActionEvent event) {
		Stage stage = (Stage) btnExit.getScene().getWindow();
		stage.close();
	}

	@FXML
	void onClickUpdateDetails(ActionEvent event) {
		Patient patient = this.patientdao.retrivePatient(id);
		ArrayList<PatientDetails> detailsList = new ArrayList<PatientDetails>();

		PatientDetails patientdetails = new PatientDetails();

		String fullName = txt_fullName.getText();
		String mobileNo = txt_mobileNo.getText();
		String admitdate = txt_date.getText();

		patient.setFullName(fullName);
		patient.setMobile_no(mobileNo);
		patient.setAdmit_date(admitdate);

		patientdetails.setFullName(txt_firstName.getText() + " " + txt_lastName.getText());
		patientdetails.setMobile_no(txt_MobileNo.getText());

		String date = admit_date.getValue().toString();
		patientdetails.setAdmit_date(date);
		patientdetails.setPrescription_1(txt_precscription_1.getText());
		patientdetails.setPrescription_2(txt_precscription_2.getText());
		patientdetails.setPrescription_3(txt_precscription_3.getText());
		patientdetails.setPrescription_4(txt_precscription_4.getText());
		patientdetails.setPrescription_5(txt_precscription_5.getText());
		patientdetails.setFlag(false);
		patientdetails.setPatient(patient);
		detailsList.clear();
		detailsList.add(patientdetails);
		patient.setPatientdetails(detailsList);
		patientdao.updatePatient(patient);

		btn_UpdateDetails.getOnMouseClicked();
		{
			txt_firstName.clear();
			txt_lastName.clear();
			txt_mobileNo.clear();
			txt_precscription_1.clear();
			txt_precscription_2.clear();
			txt_precscription_3.clear();
			txt_precscription_4.clear();
			txt_precscription_5.clear();
			admit_date.getEditor().clear();
			admit_date.setValue(null);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Saved");
			alert.setHeaderText(null);
			alert.setContentText("Referred Patient Saved");
			alert.showAndWait();

			Stage stage = (Stage) btn_UpdateDetails.getScene().getWindow();
			stage.close();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		admit_date.setValue(LocalDate.now());
	}

}
