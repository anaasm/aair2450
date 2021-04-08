package aair2450mv.repository;

import aair2450mv.validators.PaymentValidator;
import javafx.collections.ObservableList;
import aair2450mv.model.MenuDataModel;
import aair2450mv.model.Payment;
import aair2450mv.model.PaymentType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PaymentRepository {
    private String filename;
    private List<Payment> paymentList;

    public PaymentRepository(String filename){
        this.paymentList = new ArrayList<>();
        this.filename=filename;
        readPayments();
    }

    public void deleteAllPayments(){
        this.paymentList=new ArrayList<>();
        writeAll();
    }

    private void readPayments(){
        ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                Payment payment=getPayment(line);
                paymentList.add(payment);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        if (PaymentValidator.validate(payment)==0) {
            paymentList.add(payment);
            writeAll();
        }
    }

    public List<Payment> getAll(){
        return paymentList;
    }

    public void writeAll(){
        ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Payment p:paymentList) {
                System.out.println(p.toString());
                bw.write(p.toString());
                bw.newLine();
            }
            bw.close();
            System.out.println("____________");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
