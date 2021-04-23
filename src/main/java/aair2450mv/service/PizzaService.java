package aair2450mv.service;

import aair2450mv.model.MenuDataModel;
import aair2450mv.model.Payment;
import aair2450mv.model.PaymentType;
import aair2450mv.repository.MenuRepository;
import aair2450mv.repository.PaymentRepository;

import java.util.List;

public class PizzaService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;

    public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
    }

    public List<MenuDataModel> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){return payRepo.getAll(); }

    public void addPayment(int table,PaymentType type, Double amount){
        Payment payment= new Payment(table, type, amount);
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type){
        double total=0.0f;
        int count=0;
        List<Payment> l=getPayments();
        if (l.size()==0)
            return total;
        for (int i=0;i<l.size();i++){
            if (l.get(i).getType().equals(type))
                total+=l.get(i).getAmount();
            if(l.size()%2 == 0){
                count++;
            }
        }
        return total;
    }

}
