package com.artiks.torcherinoCe.utility;

public class EnergyFormat {

    public static String formatNumber(long number) {
        if (number < 1000) {
            return number + " RF";
        } else if (number < 1_000_000) {
            return String.format("%.1f kRF", number / 1000.0);
        } else if (number < 1_000_000_000) {
            return String.format("%.1f mRF", number / 1_000_000.0);
        } else if (number < 1_000_000_000_000L) {
            return String.format("%.1f bRF", number / 1_000_000_000.0);
        } else if (number < 1_000_000_000_000_000L) {
            return String.format("%.1f tRF", number / 1_000_000_000_000.0);
        } else if (number < 1_000_000_000_000_000_000L) {
            return String.format("%.1f qRF", number / 1_000_000_000_000_000.0);
        } else {
            return String.format("%.1f qqRF", number / 1_000_000_000_000_000_000.0);
        }
    }
}
