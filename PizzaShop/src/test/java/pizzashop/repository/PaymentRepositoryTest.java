package pizzashop.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PaymentRepositoryTest {

    private String filePath = "C:\\Users\\Scoican\\Desktop\\Work\\Validation and verification of software systems\\VVSS-Project\\PizzaShop\\src\\main\\resources\\data\\paymentsTest.txt";

    @Test
    void testAddValidPayment() {
        Payment payment = mock(Payment.class);
        PaymentRepository paymentRepository = new PaymentRepository(filePath);
        try {
            paymentRepository.add(payment);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
        paymentRepository.EmptyFile();
    }

    @Test
    void testAddNullPayment() {
        PaymentRepository paymentRepository = new PaymentRepository(filePath);
        try {
            paymentRepository.add(null);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}