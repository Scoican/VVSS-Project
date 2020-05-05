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

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationERSTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testAddPaymentWithValidData() throws Exception {
        Payment payment1 = new Payment(1, PaymentType.CARD, 100);

        Mockito.doNothing().when(paymentRepository).add(payment1);
        this.paymentRepository.add(payment1);

        Mockito.verify(paymentRepository,Mockito.times(1)).add(payment1);

        Mockito.when(paymentRepository.getAll()).thenReturn(Collections.singletonList(payment1));
        boolean status = paymentRepository.getAll().size() == 1;

        Mockito.verify(paymentRepository, Mockito.times(1)).getAll();
        assertTrue(status);
    }

    @Test
    void testAddPaymentWithInvalidData() throws Exception {
        Mockito.doNothing().when(paymentRepository).add(null);
        this.paymentRepository.add(null);

        Mockito.verify(paymentRepository,Mockito.times(1)).add(null);

        Mockito.when(paymentRepository.getAll()).thenReturn(Collections.emptyList());
        boolean status = paymentRepository.getAll().size() == 0;

        Mockito.verify(paymentRepository, Mockito.times(1)).getAll();
        assertTrue(status);
    }
}
