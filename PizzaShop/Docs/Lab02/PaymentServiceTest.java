package pizzashop.service;

import org.junit.jupiter.api.Test;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    @Test
    void addPayment() {
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository());
        //ECP valid
        try{
            service.addPayment(6, PaymentType.CARD,10);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        //ECP invalid
        try{
            service.addPayment(-4, PaymentType.CARD,10);
            fail();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(true);
        }
        //BVA valid
        try{
            service.addPayment(1, PaymentType.CARD,10);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        //BVA invalid
        try{
            service.addPayment(0, PaymentType.CARD,10);
            fail();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(true);
        }
        //BVA valid
        try{
            service.addPayment(8, PaymentType.CARD,10);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        //BVA invalid
        try{
            service.addPayment(9, PaymentType.CARD,10);
            fail();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(true);
        }
        //ECP invalid
        try{
            service.addPayment(0, null,10);
            fail();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(true);
        }
        //ECP valid
        try{
            service.addPayment(8, PaymentType.CARD,10);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }
}