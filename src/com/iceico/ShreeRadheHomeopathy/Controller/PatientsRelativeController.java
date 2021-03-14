package com.iceico.ShreeRadheHomeopathy.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.iceico.ShreeRadheHomeopathy.Dao.PatientDao;
import com.iceico.ShreeRadheHomeopathy.Modal.Patient;
import com.iceico.ShreeRadheHomeopathy.Modal.PatientDetails;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class PatientsRelativeController implements Initializable {

	@FXML
	private TableView<PatientDetails> tab_relative_data;

	@FXML
	private TableColumn<PatientDetails, Long> col_relative_id;

	@FXML
	private TableColumn<PatientDetails, String> col_relative_fullName;

	@FXML
	private TableColumn<PatientDetails, String> col_mobileNo;

	@FXML
	private TableColumn<PatientDetails, String> col_date;

	@FXML
	private Label lbl_fullName;

	@FXML
	private Label lbl_date;

	@FXML
	private Label lbl_contactNo;

	@FXML
	private Button btnExit;

	@FXML
	void onClickExit(ActionEvent event) {
		Stage stage = (Stage) btnExit.getScene().getWindow();
		stage.close();
	}

	@SuppressWarnings("unchecked")
	void addPrescriptionButtonTable() {
		TableColumn<PatientDetails, Void> columnView = new TableColumn<PatientDetails, Void>("Prescription");
		Callback<TableColumn<PatientDetails, Void>, TableCell<PatientDetails, Void>> cellFactory = new Callback<TableColumn<PatientDetails, Void>, TableCell<PatientDetails, Void>>() {

			@Override
			public TableCell<PatientDetails, Void> call(TableColumn<PatientDetails, Void> param) {

				final TableCell<PatientDetails, Void> cell = new TableCell<PatientDetails, Void>() {
					private final Button btnView = new Button("Prescription");

					{

						btnView.setOnAction((ActionEvent event) -> {
							try {
								FXMLLoader loader = new FXMLLoader(getClass()
										.getResource("/com/iceico/ShreeRadheHomeopathy/Views/Prescription.fxml"));
								Parent root = loader.load();
								Scene scene = new Scene(root);
								Stage stage = new Stage();
								stage.setScene(scene);
								stage.initStyle(StageStyle.TRANSPARENT);
								stage.show();
								getOnMousePressed();
								{
									PatientDetails patient = tab_relative_data.getSelectionModel().getSelectedItem();
									PrescriptionController prescriptionController = loader.getController();
									prescriptionController.retrieveDetails(patient);
								}
								stage = (Stage) btnView.getScene().getWindow();
								stage.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						});

					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btnView);
						}
					}
				};
				return cell;
			}
		};

		columnView.setCellFactory(cellFactory);
		tab_relative_data.getColumns().addAll(columnView);
	}

	PatientDao patientDao = new PatientDao();

	public void retrieveDetails(Patient patient) {
		lbl_fullName.setText(patient.getFullName());

		String contactNo = patient.getMobile_no();
		lbl_contactNo.setText(contactNo);

		String date = patient.getAdmit_date();
		lbl_date.setText(date);

		List<PatientDetails> details = FXCollections.observableArrayList();
		details = patient.getPatientdetails();
		tab_relative_data.getItems().addAll(details);

	}

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		col_relative_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("admit_date"));
		col_relative_fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		col_mobileNo.setCellValueFactory(new PropertyValueFactory<>("mobile_no"));
		addPrescriptionButtonTable();
	}

}
