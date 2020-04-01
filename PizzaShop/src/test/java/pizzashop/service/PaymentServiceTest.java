package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class PaymentServiceTest {

    /*
    La adaugarea unei plati:
     - numarul mesei trebuie sa apartina intervalului [1,8];
     - tipul platii poate lua valori din multimea {CASH, CARD}.
     */


    //Denotes that a method is a parameterized test.
    @ParameterizedTest
    @ValueSource(ints = { 2,3,4,5,6,7 })
    void addPaymentECPValid(int nrTable){
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository());
        try{
            service.addPayment(nrTable, PaymentType.CARD,10);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    //With this annotation, we can give a tag to tests for filtering them.
    @Tag("ECP")
    @Test
    void addPaymentECPInvalid(){
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository());
        try{
            service.addPayment(-4, PaymentType.CARD,10);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    //Declares a custom display name for the test class or test method.
    @DisplayName("Test BVA valid")
    @Test
    void addPaymentBVAValid(){
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository());
        try{
            service.addPayment(1, PaymentType.CARD,10);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    //This annotation is used to disable a test class or test method.
    @Disabled
    @Test
    void addPaymentBVAInvalid(){
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository());
        try{
            service.addPayment(0, PaymentType.CARD,10);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    //Denotes that a method is a test template for a repeated test.
    @RepeatedTest(2)
    void addPayment() {
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository());
        //BVA valid
        try{
            service.addPayment(8, PaymentType.CARD,10);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
        //BVA invalid
        try{
            service.addPayment(9, PaymentType.CARD,10);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
        //ECP invalid
        try{
            service.addPayment(8, null,10);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
        //ECP valid
        try{
            service.addPayment(8, PaymentType.CARD,10);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }
}