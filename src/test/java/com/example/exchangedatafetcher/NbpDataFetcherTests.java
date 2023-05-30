package com.example.exchangedatafetcher;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Controller.class)
@ContextConfiguration(classes = Controller.class)public class NbpDataFetcherTests {
    @MockBean
    private Controller basicController;

    @BeforeEach
    public void setUp() {
        basicController = new Controller();
    }

    @Test
    public void testAverageExchangeRatesWithValidInput() {
        // Arrange
        String currency = "usd";
        String date = "2023-04-03";
        Double expected = 4.3168;
        // Act
        Double result = basicController.averageExchangeRates(currency, date);
        // Assert
        assertEquals(result.doubleValue(),expected);
        assertNotNull(result, "Result should not be null");
        assertTrue(result > 0, "Result should be greater than 0");
    }

    @Test
    void diffBuySell() {
        String currency = "usd";
        String no = "5";
        HashSet<Rate> result = basicController.diffBuySell(currency,no);

        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should not be empty");
        assertEquals(5, result.size(), "Result should have 5 rates");

    }

    @Test
    public void testMaxMinAverageWithValidInput() {
        // Arrange
        String currency = "usd";
        String no = "50";
        // Act
        List<Double> result = basicController.maxMinAverage(currency, no);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should not be empty");
    }
}