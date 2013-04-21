package edu.uw.danco;

import edu.uw.ext.framework.account.*;
import edu.uw.ext.framework.order.Order;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 4/13/13
 * Time: 6:13 PM
 */
public class AccountImpl implements Account {
    /** The logger */
    private static final Logger LOGGER = Logger.getLogger(AccountImpl.class.getName());

    /** Name of account holder */
    private String name;

    /** Account balance */
    private int balance;

    /** Full name of account holder */
    private String fullName;

    /** Address of account holder */
    private Address address;

    /** Phone number for account holder */
    private String phone;

    /** Email address for account holder */
    private String email;

    /** Credit card number of account holder */
    private CreditCard creditCard;

    /** Hash of account holder's password */
    private byte[] passwordHash;

    /** The account manager */
    private AccountManager accountManager = null;

    /**
     * Default constructor for bean support
     */
    public AccountImpl() {
    }


    /**
     * Gets the account name
     * @return - the name of the account
     */
    @Override
    public String getName() {
        return name;
    }


    /**
     * Sets the name. This operation is not generally used but is provided for JavaBean conformance.
     * @param name - the value to be set for the account name
     * @throws AccountException - if the account name is unacceptable
     */
    @Override
    public void setName(final String name) throws AccountException {
        Preferences prefs = Preferences.userNodeForPackage(Account.class);
        if (name.length() < prefs.getInt("minAccountLength", 8)) {
            throw new AccountException("Account name too short: " + name);
        }
        this.name = name;
    }


    /**
     * Gets the hashed password.
     * @return - the hashed password
     */
    @Override
    public byte[] getPasswordHash() {
        return passwordHash;
    }


    /**
     * Sets the hashed password
     * @param passwordHash - the value to be stored for the password hash
     */
    @Override
    public void setPasswordHash(final byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }


    /**
     * Gets the account balance
     * @return - the current balance of the account
     */
    @Override
    public int getBalance() {
        return balance;
    }


    /**
     * Sets the account balance.
     * @param balance - the account balance.
     */
    @Override
    public void setBalance(final int balance) {
        Preferences prefs = Preferences.userNodeForPackage(Account.class);
        if (balance < prefs.getInt("minAccountBalance", 0)) {

        }
        this.balance = balance;
    }


    /**
     * Gets the full name of the account holder
     * @return - the account holder's full name
     */
    @Override
    public String getFullName() {
        return fullName;
    }


    /**
     * Sets the full name of the account holder
     * @param fullName - the full name of the account holder
     */
    @Override
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }


    /**
     * Gets the account address
     * @return - the address for the account holder
     */
    @Override
    public Address getAddress() {
        return address;
    }


    /**
     * Sets the address of the account holder
     * @param address - the address value to use
     */
    @Override
    public void setAddress(final Address address) {
        if (isEmptyAddress(address)) {
            this.address = null;
        } else {
        this.address = address;
        }
    }

    private boolean isEmptyAddress(Address address) {
        return (address.getStreetAddress() == null || address.getStreetAddress().length() == 0) &&
               (address.getCity() == null || address.getCity().length() == 0) &&
               (address.getState() == null || address.getState().length() == 0) &&
               (address.getZipCode() == null || address.getZipCode().length() == 0);
    }


    /**
     * Gets the phone number
     * @return - the phone number of the account holder
     */
    @Override
    public String getPhone() {
        return phone;
    }


    /**
     * Sets the phone number of the account holder
     * @param phone - the value to use for the account holder
     */
    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }


    /**
     * Gets the email address
     * @return - the email address of the account holder
     */
    @Override
    public String getEmail() {
        return email;
    }


    /**
     * Sets the email address
     * @param email - the value to use for the account holder's email
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Gets the account holder's credit card number
     * @return - the credit card number
     */
    @Override
    public CreditCard getCreditCard() {
        return creditCard;
    }


    /**
     * Sets the credit card number
     * @param card - the value to use for the credit card
     */
    @Override
    public void setCreditCard(CreditCard card) {
        if (isEmptyCreditCard(card)) {
            this.creditCard = null;
        } else {
            this.creditCard = card;
        }
    }

    private boolean isEmptyCreditCard(CreditCard card) {
        return (card.getAccountNumber() == null || card.getAccountNumber().length() == 0) &&
               (card.getHolder() == null || card.getHolder().length() == 0);
    }


    /**
     * Sets the account manager responsible for persisting/managing this account.
     * This may be invoked exactly once on any given account, any subsequent
     * invocations should be ignored. The account manager member should not be
     * serialized with implementing class object.
     * @param m - the account manager to set
     */
    @Override
    public void registerAccountManager(AccountManager m) {
        if (accountManager == null) {
            accountManager = m;
        } else {
            LOGGER.log(Level.SEVERE, "Account manager already set");
        }
    }


    /**
     * Incorporates the effect of an order in the balance.
     * @param order - the order to be reflected in the account
     * @param executionPrice - the price at which to execute the order
     */
    @Override
    public void reflectOrder(Order order, int executionPrice) {
        if (order.isBuyOrder()) {
            balance -= order.valueOfOrder(executionPrice);
        }
        else {
            balance += order.valueOfOrder(executionPrice);
        }
    }
}
