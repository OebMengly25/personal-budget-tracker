import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class BudgetDashboardController {
    // Removed duplicate expenseBarChart, xAxis, yAxis declarations

    @FXML
    private TableView<Expense> expenseTableView;

    @FXML
    private TableColumn<Expense, String> ColumExpense;

    @FXML
    private TableColumn<Expense, Double> ColumAmount;

    @FXML
    private BarChart<String, Number> expenseBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button addBudgetButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField budgetAmountTextField;

    @FXML
    private Label budgetLeftLabel;

    @FXML
    private Label totalBudgetLabel;

    @FXML
    private Label totalExpensesLabel;

    private double totalBudget = 0;

   @FXML
    public void initialize() {
    totalBudget = loadBudgetFromCSV();
    BudgetData.setTotalBudget(totalBudget);

    double expenses = loadTotalExpenses();
    BudgetData.setTotalExpenses(expenses);

    updateLabels();
    loadBarChartFromSession(); // 👈 Add this

}

    

    private void updateLabels() {
        totalBudgetLabel.setText(String.format("%.2f", BudgetData.getTotalBudget()));
        totalExpensesLabel.setText(String.format("%.2f", BudgetData.getTotalExpenses()));
        budgetLeftLabel.setText(String.format("%.2f", BudgetData.getRemainingBudget()));
    }

    @FXML
    void handleAddBudget_action(ActionEvent event) {
        String amountText = budgetAmountTextField.getText();

        try {
            double newAmount = Double.parseDouble(amountText);
            double updatedBudget = BudgetData.getTotalBudget() + newAmount;
            BudgetData.setTotalBudget(updatedBudget);
            saveBudgetToCSV(newAmount);
            updateLabels();
            budgetAmountTextField.clear();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void saveBudgetToCSV(double amount) {
        try {
            String user = Session.currentUser;
            new File("users").mkdirs();
            File file = new File("users/user_" + user + "_budget.csv");

            try (FileWriter writer = new FileWriter(file, true)) {
                if (file.length() == 0) {
                    writer.write("Add_budget\n");
                }
                writer.write(amount + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double loadBudgetFromCSV() {
        double sum = 0;
        try {
            String user = Session.currentUser;
            File file = new File("users/user_" + user + "_budget.csv");
            if (!file.exists()) return 0;

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.trim().isEmpty() || line.startsWith("Add_budget")) continue;
                    sum += Double.parseDouble(line);
                }
            }
        } catch (Exception e) {
            System.out.println("Budget CSV not found or error reading: " + e.getMessage());
        }
        return sum;
    }

    private double loadTotalExpenses() {
        double sum = 0;
        try {
            String user = Session.currentUser;
            File file = new File("users/user_" + user + ".csv");
            if (!file.exists()) return 0;

            try (Scanner scanner = new Scanner(file)) {
                if (scanner.hasNextLine()) scanner.nextLine(); // skip header
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.trim().isEmpty()) continue;
                    String[] parts = line.split(",");
                    if (parts.length >= 4) {
                        sum += Double.parseDouble(parts[3]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Could not load expenses: " + e.getMessage());
        }
        return sum;
    }
    private void loadBarChartFromSession() {
    expenseBarChart.getData().clear();

    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("User Expenses");

    try {
        String user = Session.currentUser;
        File file = new File("users/user_" + user + ".csv");
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) scanner.nextLine(); // Skip header

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String expenseName = parts[1];           // Assume column 2 = name
                    double amount = Double.parseDouble(parts[3]); // Column 4 = amount

                    series.getData().add(new XYChart.Data<>(expenseName, amount));
                }
            }
        }

        expenseBarChart.getData().add(series);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    
    @FXML
    void handleBack_action(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ViewDisbord.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void handleResetAll_action(ActionEvent event) {
        BudgetData.setTotalBudget(0);
        BudgetData.setTotalExpenses(0);
        updateLabels();

        try {
            String user = Session.currentUser;
            new FileWriter("users/user_" + user + "_budget.csv", false).close();
            new FileWriter("users/user_" + user + ".csv", false).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
