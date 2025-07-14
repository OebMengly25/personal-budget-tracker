public class BudgetData {
    private static double totalBudget = 0;
    private static double totalExpenses = 0;

    public static void setTotalBudget(double b) { totalBudget = b; }
    public static double getTotalBudget() { return totalBudget; }

    public static void setTotalExpenses(double e) { totalExpenses = e; }
    public static double getTotalExpenses() { return totalExpenses; }

    public static double getRemainingBudget() { return totalBudget - totalExpenses; }
}
