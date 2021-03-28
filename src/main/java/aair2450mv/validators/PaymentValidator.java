package aair2450mv.validators;

import aair2450mv.model.Payment;

public class PaymentValidator {
    public static int validate(Payment payment){
        if (payment.getAmount()<=0 || payment.getTableNumber()<1 || payment.getTableNumber()>8)
            return -1;
        return 0;
    }
}
