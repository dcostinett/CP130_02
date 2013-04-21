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

    /**
     * Gets the issuer
     * @return - the issuer
     */
    @Override
    public String getIssuer() {
        return issuer;
    }


    /**
     * Sets the issuer
     * @param issuer - the issuer
     */
    @Override
    public void setIssuer(final String issuer) {
        this.issuer = issuer == null ? "" : issuer;
    }


    /**
     * Gets the credit card type
     * @return - the type
     */
    @Override
    public String getType() {
        return type;
    }


    /**
     * Sets the credit card type
     * @param type - the type
     */
    @Override
    public void setType(final String type) {
        this.type = type == null ? "" : type;
    }


    /**
     * Gets the name of the credit card holder
     * @return - the holder's name
     */
    @Override
    public String getHolder() {
        return holder;
    }


    /**
     * Sets the credit card holder
     * @param name - the name of the holder
     */
    @Override
    public void setHolder(final String name) {
        this.holder = name == null ? "" : name;
    }


    /**
     * Gets the account number
     * @return - the credit card number
     */
    @Override
    public String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the account number
     * @param accountNumber - the number
     */
    @Override
    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber == null ? "" : accountNumber;
    }


    /**
     * Gets the expiration date
     * @return - the expiration date
     */
    @Override
    public String getExpirationDate() {
        return expirationDate;
    }


    /**
     * Sets the expiration date
     * @param expDate - the expiration date
     */
    @Override
    public void setExpirationDate(final String expDate) {
        this.expirationDate = expDate == null ? "" : expDate;
    }
}
