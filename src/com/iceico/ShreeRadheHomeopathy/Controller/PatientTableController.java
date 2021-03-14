package com.iceico.ShreeRadheHomeopathy.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.iceico.ShreeRadheHomeopathy.Dao.PatientDao;
import com.iceico.ShreeRadheHomeopathy.Modal.Patient;

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

public class PatientTableController implements Initializable {

	@FXML
	private TableView<Patient> tab_patient;

	@FXML
	private TableColumn<Patient, Long> col_patientId;

	@FXML
	private TableColumn<Patient, String> col_patientFullName;

	@FXML
	private TableColumn<Patient, String> col_mobileNo;

	@FXML
	private TableColumn<Patient, String> col_date;

	@FXML
	private Button btn_exit;

	@FXML
	private TextField filterField;

	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn<Patient, List> col_list;

	PatientDao patientDao = new PatientDao();

	ObservableList<Patient> lists = FXCollections.observableArrayList();

	public void getDataTable() {
		lists = patientDao.getPatientList();
		tab_patient.setItems(lists);
	}

	@SuppressWarnings("unchecked")
	void addPrescriptionButtonTable() {
		TableColumn<Patient, Void> columnViewPres = new TableColumn<Patient, Void>("Prescription");
		Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>>() {

			@Override
			public TableCell<Patient, Void> call(TableColumn<Patient, Void> param) {

				final TableCell<Patient, Void> cell = new TableCell<Patient, Void>() {
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
								getOnMouseClicked();
								{
									Patient patient = tab_patient.getSelectionModel().getSelectedItem();
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
	void addViewButtonTable() {
		TableColumn<Patient, Void> columnView = new TableColumn<Patient, Void>("View Details");
		Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>>() {

			@Override
			public TableCell<Patient, Void> call(TableColumn<Patient, Void> param) {

				final TableCell<Patient, Void> cell = new TableCell<Patient, Void>() {
					private final Button btnView = new Button("View");

					{

						btnView.setOnAction((ActionEvent event) -> {
							try {
								FXMLLoader loader = new FXMLLoader(getClass().getResource(
										"/com/iceico/ShreeRadheHomeopathy/Views/PatientRelativeList.fxml"));
								Parent root = loader.load();
								Scene scene = new Scene(root);
								Stage stage = new Stage();
								stage.setScene(scene);
								stage.initStyle(StageStyle.TRANSPARENT);
								stage.show();
								getOnMousePressed();
								{
									Patient patient = tab_patient.getSelectionModel().getSelectedItem();
									PatientsRelativeController patientRelativeController = loader.getController();
									
									patientRelativeController.retrieveDetails(patient);
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

		columnView.setCellFactory(cellFactory);
		tab_patient.getColumns().addAll(columnView);
	}

	@SuppressWarnings("unchecked")
	void addDeleteButtonTable() {
		TableColumn<Patient, Void> columnDel = new TableColumn<Patient, Void>("Delete Details");
		Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>>() {

			@Override
			public TableCell<Patient, Void> call(TableColumn<Patient, Void> param) {

				final TableCell<Patient, Void> cell = new TableCell<Patient, Void>() {
					private final Button btnDel = new Button("Delete");

					{
						btnDel.setOnAction((ActionEvent event) -> {
							@SuppressWarnings("unused")
							PatientDao patientDao1 = new PatientDao();
							Patient patient = tab_patient.getSelectionModel().getSelectedItem();
							patient.setFlag(true);
							patientDao1.updatePatient(patient);
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

	@SuppressWarnings("unchecked")
	void addEditButtonTable() {
		TableColumn<Patient, Void> columnEdit = new TableColumn<Patient, Void>("Edit Details");
		Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>>() {

			@Override
			public TableCell<Patient, Void> call(TableColumn<Patient, Void> param) {

				final TableCell<Patient, Void> cell = new TableCell<Patient, Void>() {
					private final Button btnEdit = new Button("Edit");

					{
						btnEdit.setOnAction((ActionEvent event) -> {
							try {
								FXMLLoader loader = new FXMLLoader(getClass()
										.getResource("/com/iceico/ShreeRadheHomeopathy/Views/PatientView.fxml"));
								Parent root = loader.load();
								Scene scene = new Scene(root);
								Stage stage = new Stage();
								stage.setScene(scene);
								stage.initStyle(StageStyle.TRANSPARENT);
								stage.show();
								getOnMousePressed();
								{
									Patient patient = tab_patient.getSelectionModel().getSelectedItem();
									PatientViewController patientViewController = loader.getController();
									patientViewController.getDetails(patient);
								}

							} catch (IOException e) {
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
							setGraphic(btnEdit);
						}
					}
				};
				return cell;
			}

		};
		columnEdit.setCellFactory(cellFactory);
		tab_patient.getColumns().addAll(columnEdit);
	}

	void createDynamicButtons() {
		addPrescriptionButtonTable();
		addViewButtonTable();
		addEditButtonTable();
		addDeleteButtonTable();
	}

	@FXML
	void onClickExit(ActionEvent event) {
		Stage stage = (Stage) btn_exit.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL url, ResourceBundle resources) {

		col_patientId.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("admit_date"));
		col_patientFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
		col_mobileNo.setCellValueFactory(new PropertyValueFactory<>("mobile_no"));
		getDataTable();
		createDynamicButtons();

		FilteredList<Patient> filteredData = new FilteredList<>(lists, p -> true);
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Patient -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				tab_patient.itemsProperty().set(filteredData);
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
