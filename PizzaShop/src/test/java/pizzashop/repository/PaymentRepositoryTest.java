package pizzashop.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PaymentRepositoryTest {

    private String filePath = "C:\\Users\\Scoican\\Desktop\\Work\\Validation and verification of software systems\\VVSS-Project\\PizzaShop\\src\\main\\resources\\data\\paymentsTest.txt";
    private Payment payment;

    @BeforeAll
    public void setUp(){
        payment=mock(Payment.class);
    }

    @Test
    void testAddValidPayment() {
        Payment payment = new Payment(1, PaymentType.CARD, 10);
        PaymentRepository paymentRepository = new PaymentRepository(filePath);
        try {
            paymentRepository.add(payment);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
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