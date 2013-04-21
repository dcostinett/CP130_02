package edu.uw.danco;

import edu.uw.ext.framework.account.Address;

/**
 * Created with IntelliJ IDEA.
 * User: dcostinett
 * Date: 4/13/13
 * Time: 6:16 PM
 */
public class AddressImpl implements Address {
    /** serialVersionUID */
    private static final long serialVersionUID = 7549265121331107862L;

    /** Factor used in calculating hashCode. */
    private static final int HASH_FACTOR = 37;

    /** Street number. */
    private String streetAddress;

    /** City name. */
    private String city;

    /** State or province. */
    private String state;

    /** Postal or zip code. */
    private String zipCode;

    /** Hash code value. */
    private Integer hashCode;

    @Override
    public String getStreetAddress() {
        return streetAddress;
    }

    @Override
    public void setStreetAddress(final String streetAddress) {
        this.streetAddress = streetAddress == null ? "" : streetAddress;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(final String city) {
        this.city = city == null ? "" : city;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(final String state) {
        this.state = state == null ? "" : state;
    }

    @Override
    public String getZipCode() {
        return zipCode;
    }

    @Override
    public void setZipCode(final String zip) {
        this.zipCode = zip == null ? "" : zip;
    }
}
