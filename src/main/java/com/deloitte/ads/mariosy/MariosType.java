package com.deloitte.ads.mariosy;

public enum MariosType {
    T1,
    T2,
    T3,
    T4,
    T5;

    public static boolean checkIfTypeExists (MariosType value) {
        for (MariosType type : values()) {
            if (type.equals(value)) {
                return true;
            }
        }
        return false;
    }

}
