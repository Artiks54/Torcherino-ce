package com.ariks.torcherino.util;

public class EnergyFormat {

    public static String formatNumber(long number) {
        if (number < 1000) {
            return String.valueOf(number);
        } else if (number < 1_000_000) {
            return String.format("%.1f K", number / 1000.0);
        } else if (number < 1_000_000_000) {
            return String.format("%.1f M", number / 1_000_000.0);
        } else if (number < 1_000_000_000_000L) {
            return String.format("%.1f B", number / 1_000_000_000.0);
        } else if (number < 1_000_000_000_000_000L) {
            return String.format("%.1f T", number / 1_000_000_000_000.0);
        } else if (number < 1_000_000_000_000_000_000L) {
            return String.format("%.1f Q", number / 1_000_000_000_000_000.0);
        } else {
            return String.format("%.1f QQ", number / 1_000_000_000_000_000_000.0);
        }
    }
}
