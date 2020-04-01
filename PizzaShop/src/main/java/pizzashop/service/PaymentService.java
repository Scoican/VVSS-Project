package pizzashop.service;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.List;

public class PaymentService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;

    public PaymentService(MenuRepository menuRepo, PaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
    }

    public List<MenuDataModel> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){return payRepo.getAll(); }

    public void addPayment(int table, PaymentType type, double amount) throws Exception {
        if(table < 1 || table > 8){
            throw new Exception("Table value is invalid!");
        }
        if(amount<=0){
            throw new Exception("Amount value is invalid!");
        }
        if(type==null){
            throw new Exception("Payment type is invalid!");
        }
        Payment payment= new Payment(table, type, amount);
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type){
        double total=0.0f;
        List<Payment> l=getPayments();
        if ((l==null) ||(l.isEmpty())) return total;
        for (Payment p:l){
            if (p.getType().equals(type))
                total+=p.getAmount();
        }
        return total;
    }

}
