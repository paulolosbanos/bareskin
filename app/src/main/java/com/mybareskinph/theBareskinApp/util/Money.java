package com.mybareskinph.theBareskinApp.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;

public class Money implements Serializable {

    public static final String PHILIPPINE_PESO = "PHP";

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String NON_BREAKING_SPACE = "\u00a0";

    private static final String CURRENCY_SIGN_PESO = "\u20b1 ";
    private static final String CURRENCY_SIGN_PESO_SIMPLE = "PhP ";
    private static final String EMPTY = "";

    /**
     * Money in its lowest unit.
     * E.g.
     * 10000 in PHP means 100 peso.
     * 10000 in INDO means 10000 rp
     */

    private long normalizedAmount;

    private Currency currency;

    public Money(long normalizedAmount, Currency currency) {
        this.normalizedAmount = normalizedAmount;
        this.currency = currency;
    }

    public static NumberFormat getCurrencyFormat(@Nullable final String currency,
                                                 final boolean isSimpleFormatting) {
        return getCurrencyFormat(currency, isSimpleFormatting, false);
    }

    public static NumberFormat getCurrencyFormat(@Nullable final String currency,
                                                 final boolean isSimpleFormatting,
                                                 final boolean isSigned) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

        try {
            numberFormat.setCurrency(Currency.getInstance(currency));
        } catch (NullPointerException | IllegalArgumentException e) {
            DecimalFormat f = (DecimalFormat) numberFormat;
            final String currencySymbol = getCurrencySymbolByCode(currency);
            f.setPositivePrefix(isSigned ? PLUS + currencySymbol : currencySymbol);
            f.setNegativePrefix(MINUS + currencySymbol);
            f.setPositiveSuffix(EMPTY);
            f.setNegativeSuffix(EMPTY);
            f.setMaximumFractionDigits(2);

            return numberFormat;
        }

        if (PHILIPPINE_PESO.equals(currency)) {
            DecimalFormat f = (DecimalFormat) numberFormat;

            final String currencySymbol;
            if (isSimpleFormatting) {
                currencySymbol = CURRENCY_SIGN_PESO_SIMPLE;
            } else {
                currencySymbol = getCurrencySymbolByCode(currency);
            }

            f.setPositivePrefix(isSigned ? PLUS + currencySymbol : currencySymbol);
            f.setNegativePrefix(MINUS + currencySymbol);
            f.setPositiveSuffix(EMPTY);
            f.setNegativeSuffix(EMPTY);
            f.setMaximumFractionDigits(2);
        }

        return numberFormat;
    }

    public static String formatPrice(@Nullable final String currency, double normalizedAmount) {
        if (PHILIPPINE_PESO.equals(currency)) {
            normalizedAmount = normalizedAmount / 100d;
        }
        return getCurrencyFormat(currency, false).format(normalizedAmount);
    }

    @NonNull
    public static String getCurrencySymbolByCode(@Nullable final String currencyCode) {
        if (currencyCode.equals(PHILIPPINE_PESO)) {
            return CURRENCY_SIGN_PESO;
        } else if (currencyCode.length() <= 3) {
            return currencyCode;
        } else {
            return EMPTY;
        }
    }

    public enum CurrencySymbolPosition {LEFT, RIGHT}

    public static CurrencySymbolPosition getCurrencySymbolPosition(@NonNull final String currency) {
        switch (currency) {
            case PHILIPPINE_PESO:
                return CurrencySymbolPosition.LEFT;
            default:
                return CurrencySymbolPosition.LEFT;
        }
    }
}
