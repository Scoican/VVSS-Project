package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceMockitoTesting {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    void testAddPaymentWithValidData() throws Exception {
        Payment payment1 = new Payment(1, PaymentType.CARD,100);

        paymentRepository.add(payment1);
        Mockito.doReturn(Collections.singletonList(payment1)).when(paymentRepository).getAll();

        List<Payment> payments = paymentService.getPayments();

        Mockito.verify(paymentRepository,Mockito.times(1)).getAll();
        assertEquals(1,payments.size());
        assertEquals(payment1,payments.get(0));
        paymentService.removeAllPayments();
    }

    @Test
    void testAddPaymentWithInvalidData() throws Exception {
        try {
            paymentService.addPayment(-4, PaymentType.CARD, 10);
            fail();
        } catch (Exception e) {
        }
        Mockito.doReturn(Collections.emptyList()).when(paymentRepository).getAll();
        List<Payment> payments = paymentService.getPayments();

        Mockito.verify(paymentRepository,Mockito.times(1)).getAll();
        assertEquals(0,payments.size());
        paymentService.removeAllPayments();
    }
}
