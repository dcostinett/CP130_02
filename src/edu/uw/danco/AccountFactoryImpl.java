package edu.uw.danco;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountFactory;

import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 4/13/13
 * Time: 6:17 PM
 */
public class AccountFactoryImpl implements AccountFactory {
    private static final Logger LOGGER = Logger.getLogger(AccountFactoryImpl.class.getName());

    @Override
    /**
     * instantiates a new account instance
     */
    public Account newAccount(String accountName, byte[] hashedPassword, int initialBalance) {
        Preferences prefs = Preferences.userNodeForPackage(edu.uw.ext.framework.account.Account.class);
        AccountImpl account = null;
        try {
            if (accountName.length() < prefs.getInt("minAccountLength", 8)) {
                LOGGER.warning(String.format("Account creation failed for '%s', name too short", accountName));
                return account;
            }

            if (initialBalance < prefs.getInt("minAccountBalance", 0)) {
                LOGGER.warning(String.format("Account creation failed for account '%s', balance = %d",
                        accountName, initialBalance));
                return account;
            }

            account = new AccountImpl();
            account.setName(accountName);
            account.setPasswordHash(hashedPassword);
            account.setBalance(initialBalance);

            LOGGER.info(String.format("Created account: %s, balance = %d", accountName, initialBalance));
        } catch (AccountException e) {
            e.printStackTrace();
        }

        return account;
    }
}
