import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceCalculatorTest {

    @Test
    public void testTotalSimpleWithDiscount() {
        Product product = new Product("Sorvete", 50.0, 10);
        double result = InvoiceCalculator.calculateInvoice(Collections.singletonList(product), 10);
        assertEquals(450.00, result);
    }

    @Test
    public void testTotalAbove1000WithAdditionalDiscount() {
        Product product = new Product("Cafeteira", 500.0, 3);
        double result = InvoiceCalculator.calculateInvoice(Collections.singletonList(product), 5);
        assertEquals(1325.00, result);
    }

    @Test
    public void testMultipleProductsWithoutDiscount() {
        Product product1 = new Product("Coca", 20.0, 5);
        Product product2 = new Product("Guarana", 30.0, 2);
        double result = InvoiceCalculator.calculateInvoice(Arrays.asList(product1, product2), 0);
        assertEquals(160.00, result);
    }

    @Test
    public void testNegativeQuantityThrowsException() {
        Product product = new Product("Feijao", 50.0, -3);
        assertThrows(InvalidProductException.class, () -> {
            InvoiceCalculator.calculateInvoice(Collections.singletonList(product), 10);
        });
    }

    @Test
    public void testNegativePriceThrowsException() {
        Product product = new Product("Arroz", -20.0, 3);
        assertThrows(InvalidProductException.class, () -> {
            InvoiceCalculator.calculateInvoice(Collections.singletonList(product), 5);
        });
    }

    @Test
    public void testEmptyProductList() {
        double result = InvoiceCalculator.calculateInvoice(Collections.emptyList(), 10);
        assertEquals(0.00, result);
    }
}
