package edu.uw.danco;

import edu.uw.ext.framework.account.*;
import edu.uw.ext.framework.order.Order;

import java.util.prefs.Preferences;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 4/13/13
 * Time: 6:13 PM
 */
public class AccountImpl implements Account {

    private String name;
    private int balance;
    private String fullName;
    private Address address;
    private String phone;
    private String email;
    private CreditCard creditCard;
    private byte[] passwordHash;


    /**
     * Default constructor for bean support
     */
    public AccountImpl() {
    }


    @Override
    /**
     * Gets the name
     */
    public String getName() {
        return name;
    }


    @Override
    /**
     * Sets the name
     */
    public void setName(String name) throws AccountException {
        Preferences prefs = Preferences.userNodeForPackage(Account.class);
        if (name.length() < prefs.getInt("minAccountLength", 8)) {
            throw new AccountException("Account name too short: " + name);
        }
        this.name = name;
    }


    @Override
    /**
     * Gets the password hash
     */
    public byte[] getPasswordHash() {
        return passwordHash;
    }


    @Override
    /**
     * sets the password hash
     */
    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }


    @Override
    /**
     * Gets the balance
     */
    public int getBalance() {
        return balance;
    }


    @Override
    /**
     * Sets the balance
     */
    public void setBalance(int balance) {
        Preferences prefs = Preferences.userNodeForPackage(Account.class);
        if (balance < prefs.getInt("minAccountBalance", 0)) {

        }
        this.balance = balance;
    }


    @Override
    /**
     * Gets the full name
     */
    public String getFullName() {
        return fullName;
    }


    @Override
    /**
     * Sets the full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    @Override
    /**
     * Gets the address
     */
    public Address getAddress() {
        return address;
    }


    @Override
    /**
     * Sets the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    /**
     * Gets the phone
     */
    public String getPhone() {
        return phone;
    }


    @Override
    /**
     * Sets the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    /**
     * Gets the email
     */
    public String getEmail() {
        return email;
    }


    @Override
    /**
     * Sets the email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    /**
     * Gets the creadit card
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }


    @Override
    /**
     * Sets the creadit card
     */
    public void setCreditCard(CreditCard card) {
        this.creditCard = card;
    }


    @Override
    /**
     * Sets the account manager responsible for persisting/managing this account.
     * This may be invoked exactly once on any given account, any subsequent
     * invocations should be ignored. The account manager member should not be
     * serialized with implementing class object.
     */
    public void registerAccountManager(AccountManager m) {
        //no op
    }


    @Override
    /**
     * Incorporates the effect of an order in the balance.
     */
    public void reflectOrder(Order order, int executionPrice) {
        if (order.isBuyOrder()) {
            balance -= order.valueOfOrder(executionPrice);
        }
        else {
            balance += order.valueOfOrder(executionPrice);
        }
    }
}
