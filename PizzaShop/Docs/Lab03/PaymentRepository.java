package pizzashop.repository;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PaymentRepository {
    private List<Payment> paymentList;
    private File file;

    public PaymentRepository(){
        this.paymentList = new ArrayList<>();
        readPayments();
    }

    public PaymentRepository(String filePath){
        this.paymentList = new ArrayList<>();
        file = new File(filePath);
        readPayments();
    }

    private void readPayments(){
        if(file==null){
            file = new File("C:\\Users\\Scoican\\Desktop\\Work\\Validation and verification of software systems\\VVSS-Project\\PizzaShop\\src\\main\\resources\\data\\payments.txt");
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                Payment payment=getPayment(line);
                paymentList.add(payment);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Payment getPayment(String line){
        Payment item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        int tableNumber= Integer.parseInt(st.nextToken());
        String type= st.nextToken();
        double amount = Double.parseDouble(st.nextToken());
        item = new Payment(tableNumber, PaymentType.valueOf(type), amount);
        return item;
    }

    public void add(Payment payment){
        paymentList.add(payment);
        writeAll();
    }

    public List<Payment> getAll(){
        return paymentList;
    }

    public void writeAll(){
        if(file==null){
            file = new File("C:\\Users\\Scoican\\Desktop\\Work\\Validation and verification of software systems\\VVSS-Project\\PizzaShop\\src\\main\\resources\\data\\payments.txt");
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Payment p:paymentList) {
                //System.out.println(p.toString());
                bw.write(p.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void EmptyFile(){
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
