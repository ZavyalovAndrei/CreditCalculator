import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTests {
    Main sut;

    @BeforeEach
    public void init() {
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started.");
    }

    @AfterEach
    public void finished() {
        System.out.println("test completed.");
    }

    @AfterAll
    static void finishedAll() {
        System.out.println("Tests completed.");
    }

    @Test
    public void testGetMonthlyPayment() {
        //arrange
        int creditAmount = 378000;
        int loanTerm = 18;
        double loanInterestRate = 14;
        double expected = 23403.94;
        //act
        double result = sut.getMonthlyPayment(creditAmount, loanTerm, loanInterestRate);
        //assert
        assertEquals(expected, result);
    }

    @Test
    public void testGetTotalPayment() {
        //arrange
        int creditAmount = 5870250;
        int loanTerm = 180;
        double loanInterestRate = 9.5;
        double expected = 11033747.9;
        //act
        double result = sut.getTotalPayment(creditAmount, loanTerm, loanInterestRate);
        //assert
        assertEquals(expected, result);
    }

    @Test
    public void testGetAnnuityRatio() {
        //arrange
        int loanTerm = 72;
        double loanInterestRate = 33.3;
        double expected = 0.03224;
        //act
        double result = Math.round((sut.getAnnuityRatio(loanTerm, loanInterestRate) * 100000.0)) / 100000.0;
        //assert
        assertEquals(expected, result);
    }

    @Test
    public void testGetTotalOverpayment() {
        //arrange
        int creditAmount = 963500;
        int loanTerm = 84;
        double loanInterestRate = 10.2;
        double expected = 388478.67;
        //act
        double result = sut.getTotalOverpayment(creditAmount, loanTerm, loanInterestRate);
        //assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("intSource")
    public void testCheckIntEntry(Scanner entry, int min, int max, String message, int expected) {
        //act
        int result = sut.checkIntEntry(entry, min, max, message);
        //assert
        assertEquals(expected, result);
    }

    public static Stream<Arguments> intSource() {

        return Stream.of(Arguments.of("2", 1, 60, "Message", 2), Arguments.of("250000",
                100000, 10000000, "Message", 250000));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testCheckDoubleEntry(Scanner entry, String message, double expected) {
        //act
        double result = sut.checkDoubleEntry(entry, message);
        //assert
        assertEquals(expected, result);
    }

    public static Stream<Arguments> source() {

        return Stream.of(Arguments.of("12", "Message", 12), Arguments.of("15.3", "Message", 15.3));
    }
}