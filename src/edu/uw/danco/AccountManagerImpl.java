package edu.uw.danco;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountFactory;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.dao.AccountDao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 4/16/13
 * Time: 8:48 PM
 */
public class AccountManagerImpl implements AccountManager {

    private static final Logger LOGGER = Logger.getLogger(AccountManagerImpl.class.getName());

    /** The data access object */
    private final AccountDao dao;


    /**
     * Instatiates a new AccountManager
     * @param dao
     */
    public AccountManagerImpl(final AccountDao dao) {
        this.dao = dao;
    }

    /**
     * Default constructor to support JavaBean instantiation
     */
    public AccountManagerImpl() {
        this.dao = new AccountDaoImpl();
    }

    /**
     * Used to persist an account
     * @param account - the account to persist
     * @throws AccountException - if operation fails
     */
    @Override
    public void persist(final Account account) throws AccountException {
        dao.setAccount(account);
    }

    /**
     * Lookup the account based on the accountname
     * @param accountName - the name of the desired account
     * @return - the account if located otherwise null
     * @throws AccountException - if operation fails
     */
    @Override
    public Account getAccount(final String accountName) throws AccountException {
        return dao.getAccount(accountName);
    }

    /**
     * Remove the account
     * @param accountName - the name of the account to remove
     * @throws AccountException - if operation fails
     */
    @Override
    public void deleteAccount(final String accountName) throws AccountException {
        dao.deleteAccount(accountName);
    }

    /**
     * Creates an account. The creation process should include persisting the account and setting the account
     * manager reference (through the Account registerAccountManager operation).
     * @param accountName - the name for account to add
     * @param password - the password used to gain access to the account
     * @param balance - the initial balance of the account
     * @return - the newly created account
     * @throws AccountException - if operation fails
     */
    @Override
    public Account createAccount(final String accountName, final String password, int balance) throws AccountException {
        Account account = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(password.getBytes());

            AccountFactory accountFactory = new AccountFactoryImpl();
            account = accountFactory.newAccount(accountName, md.digest(), balance);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, "Unable to create SHA1 hash for password", e);
        }

        return account;
    }

    /**
     *
     * @param accountName - name of account the password is to be validated for
     * @param password - password is to be validated
     * @return - true if password is valid for account identified by username
     * @throws AccountException - if error occurs accessing accounts
     */
    @Override
    public boolean validateLogin(final String accountName, final String password) throws AccountException {
        boolean isPasswordMatch = false;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(password.getBytes());

            Account account = dao.getAccount(accountName);
            isPasswordMatch = Arrays.equals(account.getPasswordHash(), (md.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return isPasswordMatch;
    }

    /**
     * Release any resources used by the AccountManager implementation. Once closed
     * further operations on the AccountManager may fail.
     * @throws AccountException - if error occurs accessing accounts
     */
    @Override
    public void close() throws AccountException {
        dao.close();
    }
}
