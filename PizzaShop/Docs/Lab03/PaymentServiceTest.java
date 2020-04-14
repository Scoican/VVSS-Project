package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    private String filePath= "C:\\Users\\Scoican\\Desktop\\Work\\Validation and verification of software systems\\VVSS-Project\\PizzaShop\\src\\main\\resources\\data\\paymentsTest.txt";


    //Denotes that a method is a parameterized test.
    @ParameterizedTest
    @ValueSource(ints = { 2,3,4,5,6,7 })
    void addPaymentECPValid(int nrTable){
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository(filePath));
        try{
            service.addPayment(nrTable, PaymentType.CARD,10);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
        service.removeAllPayments();
    }

    //With this annotation, we can give a tag to tests for filtering them.
    @Tag("ECP")
    @Test
    void addPaymentECPInvalid(){
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository(filePath));
        try{
            service.addPayment(-4, PaymentType.CARD,10);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
        service.removeAllPayments();
    }

    //Declares a custom display name for the test class or test method.
    @DisplayName("Test BVA valid")
    @Test
    void addPaymentBVAValid(){
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository(filePath));
        try{
            service.addPayment(1, PaymentType.CASH,10);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
        service.removeAllPayments();
    }

    //This annotation is used to disable a test class or test method.
    @Disabled
    @Test
    void addPaymentBVAInvalid(){
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository(filePath));
        try{
            service.addPayment(0, PaymentType.CASH,10);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
        service.removeAllPayments();
    }

    //Denotes that a method is a test template for a repeated test.
    @RepeatedTest(2)
    void addPayment() {
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository(filePath));
        //BVA valid
        try{
            service.addPayment(8, PaymentType.CASH,10);
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
        service.removeAllPayments();
    }

    @Test
    void testTotalAmountNullParameter() {
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository(filePath));
        try{
            service.getTotalAmount(null);
            fail();
        }catch (IllegalArgumentException error){
            assertTrue(true);
        }
        service.removeAllPayments();
    }

    @Test
    void testTotalAmountNoPayments() {
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository(filePath));
        assertEquals(0, service.getTotalAmount(PaymentType.CARD));
    }

    @Test
    void testTotalAmountWithCard() {
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository(filePath));
        try {
            service.addPayment(1,PaymentType.CARD,100);
            service.addPayment(1,PaymentType.CASH,200);
            service.addPayment(1,PaymentType.CASH,200);
            service.addPayment(1,PaymentType.CARD,100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(200, service.getTotalAmount(PaymentType.CARD));
        service.removeAllPayments();
    }

    @Test
    void testTotalAmountWithCash() {
        PaymentService service = new PaymentService(new MenuRepository(),new PaymentRepository(filePath));
        try {
            service.addPayment(1,PaymentType.CARD,100);
            service.addPayment(1,PaymentType.CASH,200);
            service.addPayment(1,PaymentType.CASH,200);
            service.addPayment(1,PaymentType.CARD,100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(400,service.getPayments().stream().mapToDouble(Payment::getAmount).sum(), service.getTotalAmount(PaymentType.CASH));
        service.removeAllPayments();
    }
}