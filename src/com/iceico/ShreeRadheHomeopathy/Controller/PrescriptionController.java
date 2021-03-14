package com.iceico.ShreeRadheHomeopathy.Controller;

import com.iceico.ShreeRadheHomeopathy.Modal.Patient;
import com.iceico.ShreeRadheHomeopathy.Modal.PatientDetails;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PrescriptionController {

	@FXML
	private TextArea prescription1;

	@FXML
	private TextArea prescription2;

	@FXML
	private TextArea prescription3;

	@FXML
	private TextArea prescription4;

	@FXML
	private TextArea prescription5;

	@FXML
	private Button btn_Exit;

	@FXML
	private Label lbl_fullName;

	@FXML
	private Label lbl_date;

	@FXML
	private Label lbl_contactNo;

	@FXML
	private Button btnClose;

	@FXML
	void onClickClose(ActionEvent event) {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();

	}

	@FXML
	void onClickExit(ActionEvent event) {
		Stage stage = (Stage) btn_Exit.getScene().getWindow();
		stage.close();
	}

	public void retrieveDetails(PatientDetails patientDetails) {
		
		lbl_fullName.setText(patientDetails.getFullName());
		lbl_contactNo.setText(patientDetails.getMobile_no());
		lbl_date.setText(patientDetails.getAdmit_date());
		
		String prescription_1 = patientDetails.getPrescription_1();
		prescription1.setText(prescription_1);

		String prescription_2 = patientDetails.getPrescription_2();
		prescription2.setText(prescription_2);

		String prescription_3 = patientDetails.getPrescription_3();
		prescription3.setText(prescription_3);

		String prescription_4 = patientDetails.getPrescription_4();
		prescription4.setText(prescription_4);

		String prescription_5 = patientDetails.getPrescription_5();
		prescription5.setText(prescription_5);

	}

	public void retrieveDetails(Patient patient) {

		lbl_fullName.setText(patient.getFullName());
		lbl_contactNo.setText(patient.getMobile_no());
		lbl_date.setText(patient.getAdmit_date());
		
		String prescription_1 = patient.getPrescription1();
		prescription1.setText(prescription_1);

		String prescription_2 = patient.getPrescription2();
		prescription2.setText(prescription_2);

		String prescription_3 = patient.getPrescription3();
		prescription3.setText(prescription_3);

		String prescription_4 = patient.getPrescription4();
		prescription4.setText(prescription_4);

		String prescription_5 = patient.getPrescription5();
		prescription5.setText(prescription_5);
	}

}
