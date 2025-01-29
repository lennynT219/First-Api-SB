package lt.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import lt.entities.Book;

public class BookPriceCalculatorTest {
  @Test
  void calculatePrice() {
    // Preparación
    Book book = new Book(1L, "Java Programming", "John Doe", 100, 19.99, LocalDate.now(), true);
    BookPriceCalculator calculator = new BookPriceCalculator();

    // Comportamiento esperado
    double price = calculator.calculatePrice(book);
    System.out.println("Price: " + price);

    // Verificación
    assertTrue(price > 0);
  }
}
