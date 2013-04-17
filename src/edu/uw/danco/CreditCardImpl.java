package edu.uw.danco;

import edu.uw.ext.framework.account.CreditCard;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 4/13/13
 * Time: 6:17 PM
 */
public class CreditCardImpl implements CreditCard {

    private String issuer;
    private String type;
    private String holder;
    private String accountNumber;
    private String expirationDate;

    @Override
    /**
     * Gets the issuer
     */
    public String getIssuer() {
        return issuer;
    }

    @Override
    /**
     * Sets the issuer
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    /**
     * Gets the cc type
     */
    public String getType() {
        return type;
    }

    @Override
    /**
     * Sets the cc type
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    /**
     * Gets the card holder name
     */
    public String getHolder() {
        return holder;
    }

    @Override
    /**
     * Sets the card holder name
     */
    public void setHolder(String name) {
        this.holder = name;
    }

    @Override
    /**
     * Gets the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    /**
     * Sets the account number
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    /**
     * Gets the expiration date
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    @Override
    /**
     * Sets the exp date
     */
    public void setExpirationDate(String expDate) {
        this.expirationDate = expDate;
    }
}
