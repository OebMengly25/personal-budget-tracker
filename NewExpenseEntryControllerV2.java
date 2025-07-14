import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class NewExpenseEntryControllerV2 {

    @FXML private Button Button_Back;
    @FXML private Button Button_Save_Expense;
    @FXML private Label Label_status;
    @FXML private TextField amountTextField;
    @FXML private DatePicker datePicker;
    @FXML private TextField nameItemsTextField;
    @FXML private TextField typeOfItemsTextField;

    @FXML
    void Button_Back_Action(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ViewDisbord.fxml"));
            Stage stage = (Stage) Button_Back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("View Dashboard");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Button_Save_Expense_Action(ActionEvent event) {
        String item = nameItemsTextField.getText();
        String category = typeOfItemsTextField.getText();
        String amountText = amountTextField.getText();
        String date = datePicker.getValue() != null ? datePicker.getValue().toString() : "";

        if (!item.isEmpty() && !category.isEmpty() && !amountText.isEmpty() && !date.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountText);
                saveToCSV("Expense", item, category, amount, date);
                Label_status.setText("Expense saved!");

                // ✅ Update BudgetData total expenses
                BudgetData.setTotalExpenses(BudgetData.getTotalExpenses() + amount);

                nameItemsTextField.clear();
                typeOfItemsTextField.clear();
                amountTextField.clear();
                datePicker.setValue(null);
            } catch (NumberFormatException e) {
                Label_status.setText("Invalid amount.");
            }
        } else {
            Label_status.setText("Please fill in all fields.");
        }
    }

    private void saveToCSV(String type, String item, String category, double amount, String date) {
        try {
            String user = Session.currentUser;
            File dir = new File("users");
            dir.mkdirs();
            File file = new File("users/user_" + user + ".csv");
            boolean isNew = !file.exists() || file.length() == 0;

            try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                if (isNew) {
                    writer.println("Type,Item Name,Category,Amount,Date");
                }
                writer.printf("%s,%s,%s,%.2f,%s%n", type, item, category, amount, date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
