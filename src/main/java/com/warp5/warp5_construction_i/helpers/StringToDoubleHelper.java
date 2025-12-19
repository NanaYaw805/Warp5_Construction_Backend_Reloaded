package com.warp5.warp5_construction_i.helpers;

public class StringToDoubleHelper {

    public StringToDoubleHelper() {

    }



    public Double parseAmount(String amount) {
        if (amount == null || amount.trim().isEmpty()) {
            throw new IllegalArgumentException("Rental amount is required");
        }

        try {
            return Double.valueOf(amount.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid rental amount format");
        }
    }

}


