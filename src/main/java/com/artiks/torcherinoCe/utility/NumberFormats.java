package com.artiks.torcherinoCe.utility;

public class NumberFormats {

    public static String formatNumber(long number) {
        if (number < 1000) {
            return number + "";
        } else if (number < 1_000_000) {
            return String.format("%.1f k", number / 1000.0);
        } else if (number < 1_000_000_000) {
            return String.format("%.1f m", number / 1_000_000.0);
        } else if (number < 1_000_000_000_000L) {
            return String.format("%.1f b", number / 1_000_000_000.0);
        } else if (number < 1_000_000_000_000_000L) {
            return String.format("%.1f t", number / 1_000_000_000_000.0);
        } else if (number < 1_000_000_000_000_000_000L) {
            return String.format("%.1f q", number / 1_000_000_000_000_000.0);
        } else {
            return String.format("%.1f qq", number / 1_000_000_000_000_000_000.0);
        }
    }
}
