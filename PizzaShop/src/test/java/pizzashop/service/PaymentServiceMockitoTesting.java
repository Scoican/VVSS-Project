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

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Payment payment2 = new Payment(2, PaymentType.CASH,100);
        Mockito.when(paymentRepository.getAll()).thenReturn(Collections.singletonList((payment2)));
        Mockito.doNothing().when(paymentRepository).add(payment1);

        paymentService.addPayment(1, PaymentType.CARD,100);

        Mockito.verify(paymentRepository,Mockito.times(1)).add(payment1);
        Mockito.verify(paymentRepository,Mockito.never()).getAll();

        assertEquals(1,paymentService.getPayments().size());
        Mockito.verify(paymentRepository,Mockito.times(2)).getAll();
    }
}
