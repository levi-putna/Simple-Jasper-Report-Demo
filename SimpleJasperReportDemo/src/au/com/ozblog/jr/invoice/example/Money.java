package au.com.ozblog.jr.invoice.example;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Represent an amount of money in any currency.
 * 
 * <P>
 * This class assumes <em>decimal currency</em>, without funky divisions like
 * 1/5 and so on. <tt>Money</tt> objects are immutable. Like {@link BigDecimal},
 * many operations return new <tt>Money</tt> objects. In addition, most
 * operations involving more than one <tt>Money</tt> object will throw a
 * <tt>MismatchedCurrencyException</tt> if the currencies don't match.
 * </p>
 */
public class Money implements Comparable<Money>, Serializable {

	/**
	 * The money amount. Never null.
	 * 
	 * @serial
	 */
	private final BigDecimal amount;

	/**
	 * The currency of the money, such as US Dollars or Euros. Never null.
	 * 
	 * @serial
	 */
	private final Currency currency;

	/**
	 * The default currency to be used if no currency is passed to the
	 * constructor.
	 */
	private static Currency DEFAULT_CURRENCY = Currency
			.getInstance(Locale.ENGLISH);

	private static Locale DEFAULT_LOCALE = Locale.ENGLISH;

	public Money(BigDecimal amount) {
		this(amount, DEFAULT_CURRENCY);
	}

	public Money(BigDecimal amount, Currency currency) {
		super();
		this.amount = amount;
		this.currency = currency;
	}

	@Override
	public int compareTo(Money money) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Return the amount of money this object represents.
	 * 
	 * @return the amount passed to the constructor
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Returns the currency this money in in
	 * 
	 * @return the currency passed to the constructor, or the default currency.
	 */
	public Currency getCurrency() {
		return currency;
	}

	public String getDesplay(Locale locale) {
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		format.setCurrency(currency);
		return format.format(23) + currency.getCurrencyCode();
	}

	public String getDesplay() {
		return getDesplay(DEFAULT_LOCALE);
	}

	/**
	 * Add <tt>aThat</tt> <tt>Money</tt> to this <tt>Money</tt>. Currencies must
	 * match.
	 */
	public Money plus(Money money) {
		return new Money(amount.add(money.getAmount()), this.currency);
	}

	/**
	 * Subtract <tt>aThat</tt> <tt>Money</tt> from this <tt>Money</tt>.
	 * Currencies must match.
	 */
	public Money subtract(Money money) {
		return new Money(amount.subtract(money.getAmount()), this.currency);
	}

	public Money multiply(Money money) {
		return new Money(amount.multiply(money.getAmount()), this.currency);
	}
	
	public Money percent(double percent) {
		BigDecimal decimal = amount.divide(new BigDecimal(100));
		BigDecimal value = decimal.multiply(new BigDecimal(percent));
		return new Money(value, this.currency);
	}

}
