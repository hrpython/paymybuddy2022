package com.paymybuddynow.application.exception;

public class BankTransactionException extends Exception {
	 //exception en cas de défaut dans la validation de la transaction
    private static final long serialVersionUID = -3128681006635769411L;
    
    public BankTransactionException(String message) {
        super(message);
    }

}