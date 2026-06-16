package com.example.tdd2.chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = addedMonths(payData.getPayAmount());

        if (payData.getFistBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private int addedMonths(int payAmount) {
        int addedMonths = 0;
        if (payAmount >= 100_000) {
            int year = payAmount / 100_000;
            payAmount = payAmount - (year * 100_000);
            addedMonths += 12 * year;
        }
        addedMonths += payAmount / 10_000;
        return addedMonths;
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        final int dayOfFirstBilling = payData.getFistBillingDate().getDayOfMonth();
        if (isSameDayOfMonth(payData.getFistBillingDate(), candidateExp)) {
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
            if (dayLenOfCandiMon < payData.getFistBillingDate().getDayOfMonth()) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

    private int lastDayOfMonth(LocalDate candidateExp) {
        return YearMonth.from(candidateExp).lengthOfMonth();
    }

    private boolean isSameDayOfMonth(LocalDate fistBillingDate, LocalDate candidateExp) {
        final int dayOfFirstBilling = fistBillingDate.getDayOfMonth();
        return dayOfFirstBilling != candidateExp.getDayOfMonth();
    }


}
