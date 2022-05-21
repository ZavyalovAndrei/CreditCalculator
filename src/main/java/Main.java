import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    private static final String ERROR_MESSAGE = "Ошибка ввода!!!";
    private static final int MIN_CREDIT_AMOUNT = 100000;
    private static final int MAX_CREDIT_AMOUNT = 10000000;
    private static final int MIN_LOAN_TERM = 1;
    private static final int MAX_LOAN_TERM = 600;
    private static final MathContext CONTEXT = new MathContext(2, RoundingMode.HALF_UP);

    public static void main(String[] args) {
        System.out.println("Программа \"Кредитный калькулятор\" поможет рассчитать ежемесячный платеж, сумму переплаты" +
                " и общей суммы к возврату в банк.");
        Scanner scanner = new Scanner(System.in);
        int loanTerm;
        int creditAmount;
        double loanInterestRate;

        creditAmount = checkIntEntry(scanner, MIN_CREDIT_AMOUNT, MAX_CREDIT_AMOUNT, "Введите размер кредита в " +
                "рубпях. Минимальный размер кредита 100 000 рублей, максимальный 10 000 000 рублей.");
        loanTerm = checkIntEntry(scanner, MIN_LOAN_TERM, MAX_LOAN_TERM, "Введите срок кредита в месяцах. " +
                "Минимальный срок кредита 1 месяц, максимальный 600 месяцев.");
        loanInterestRate = checkDoubleEntry(scanner, "Введите процентную ставку кредита");

        double monthlyPayment = getMonthlyPayment(creditAmount, loanTerm, loanInterestRate);
        double totalPayment = getTotalPayment(creditAmount, loanTerm, loanInterestRate);
        double overpayment = getTotalOverpayment(creditAmount, loanTerm, loanInterestRate);
        System.out.print("Результаты расчета:\n\tЕжемесячный платеж: ");
        System.out.printf("%.2f", monthlyPayment);
        System.out.print(" руб.\n\tПереплата по кредиту: ");
        System.out.printf("%.2f", overpayment);
        System.out.print("руб.\n\tОбщая выплата: ");
        System.out.printf("%.2f", totalPayment);
        System.out.print(" руб.");
    }

    public static double getMonthlyPayment(int creditAmount, int loanTerm, double loanInterestRate) {
        return Math.round(creditAmount * getAnnuityRatio(loanTerm, loanInterestRate) * 100.0) / 100.0;
    }

    public static double getTotalPayment(int creditAmount, int loanTerm, double loanInterestRate) {
        return Math.round((creditAmount * getAnnuityRatio(loanTerm, loanInterestRate) * loanTerm) * 100.0) / 100.0;
    }

    public static double getTotalOverpayment(int creditAmount, int loanTerm, double loanInterestRate) {
        return Math.round(((creditAmount * getAnnuityRatio(loanTerm, loanInterestRate) * loanTerm) - creditAmount) *
                100.0) / 100.0;
    }

    public static double getAnnuityRatio(int loanTerm, double loanInterestRate) {
        double monthlyInterestRate = loanInterestRate / (12 * 100);
        return (monthlyInterestRate * Math.pow((1 + monthlyInterestRate), loanTerm)) / (Math.pow((1 +
                monthlyInterestRate), loanTerm) - 1);
    }

    public static int checkIntEntry(Scanner scanner, int min, int max, String message) {
        while (true) {
            try {
                System.out.println(message);
                int result = Integer.parseInt(scanner.nextLine());
                if (result >= min && result <= max) {
                    return result;
                } else {
                    System.out.println(ERROR_MESSAGE);
                }
            } catch (NumberFormatException err) {
                System.out.println(ERROR_MESSAGE);
            }
        }
    }

    public static double checkDoubleEntry(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                double result = Double.parseDouble(scanner.nextLine());
                if (result > 0) {
                    return result;
                } else {
                    System.out.println(ERROR_MESSAGE);
                }
            } catch (NumberFormatException err) {
                System.out.println(ERROR_MESSAGE);
            }
        }
    }
}
