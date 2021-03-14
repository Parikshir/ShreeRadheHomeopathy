package com.iceico.ShreeRadheHomeopathy.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.iceico.ShreeRadheHomeopathy.Dao.PatientDao;
import com.iceico.ShreeRadheHomeopathy.Modal.PatientDetails;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class AssociateTableController implements Initializable {

	@FXML
	private TableView<PatientDetails> tab_patient;

	@FXML
	private TableColumn<PatientDetails, Long> col_patientId;

	@FXML
	private TableColumn<PatientDetails, String> col_patientFullName;

	@FXML
	private TableColumn<PatientDetails, String> col_mobileNo;

	@FXML
	private TableColumn<PatientDetails, String> col_date;

	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn<PatientDetails, List> col_list;

	@FXML
	private Button btn_exit;

	@FXML
	private TextField search;

	PatientDao patientDao = new PatientDao();

	@FXML
	void onClickExit(ActionEvent event) {
		Stage stage = (Stage) btn_exit.getScene().getWindow();
		stage.close();

	}

	ObservableList<PatientDetails> list = FXCollections.observableArrayList();

	public void getDataTable() {
		list = patientDao.getAssociateList();
		tab_patient.getItems().addAll(list);
	}

	@SuppressWarnings("unchecked")
	void addPrescriptionButtonTable() {
		TableColumn<PatientDetails, Void> columnViewPres = new TableColumn<PatientDetails, Void>("Prescription");
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
									PatientDetails patient = tab_patient.getSelectionModel().getSelectedItem();
									PrescriptionController prescriptionController = loader.getController();
									prescriptionController.retrieveDetails(patient);
								}
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

		columnViewPres.setCellFactory(cellFactory);
		tab_patient.getColumns().addAll(columnViewPres);
	}

	@SuppressWarnings("unchecked")
	void addDeleteButtonTable() {
		TableColumn<PatientDetails, Void> columnDel = new TableColumn<PatientDetails, Void>("Delete Details");
		Callback<TableColumn<PatientDetails, Void>, TableCell<PatientDetails, Void>> cellFactory = new Callback<TableColumn<PatientDetails, Void>, TableCell<PatientDetails, Void>>() {

			@Override
			public TableCell<PatientDetails, Void> call(TableColumn<PatientDetails, Void> param) {

				final TableCell<PatientDetails, Void> cell = new TableCell<PatientDetails, Void>() {
					private final Button btnDel = new Button("Delete");

					{
						btnDel.setOnAction((ActionEvent event) -> {

							@SuppressWarnings("unused")
							PatientDao patientDao = new PatientDao();
							PatientDetails patient = tab_patient.getSelectionModel().getSelectedItem();
							patient.setFlag(true);
							tab_patient.getItems().clear();
							patientDao.updatePatient(patient);
							getDataTable();
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btnDel);
						}
					}
				};
				return cell;
			}

		};
		columnDel.setCellFactory(cellFactory);
		tab_patient.getColumns().addAll(columnDel);
	}

	void createDynamicButtons() {
		addPrescriptionButtonTable();
		addDeleteButtonTable();
	}

	@Override
	public void initialize(URL url, ResourceBundle resources) {

		col_patientId.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("admit_date"));
		col_patientFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		col_mobileNo.setCellValueFactory(new PropertyValueFactory<>("mobile_no"));
		col_list.setCellValueFactory(new PropertyValueFactory<>("patientdetails"));
		getDataTable();
		createDynamicButtons();

		FilteredList<PatientDetails> filteredData = new FilteredList<>(list, p -> true);
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Patient -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				tab_patient.getItems().addAll(filteredData);
				if (Patient.getFullName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (Patient.getMobile_no().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches mobile no..
				} else if (Patient.getAdmit_date().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches Date.
				}
				return false; // Does not match.
			});
		});

	}

}
