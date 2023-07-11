package com.deloitte.ads.mariosy;

public enum MariosType {
    MARIOS_T1,
    MARIOS_T2,
    MARIOS_T3,
    MARIOS_T4,
    MARIOS_T5;

    public static boolean checkIfTypeExists (MariosType value) {
        for (MariosType type : values()) {
            if (type.equals(value)) {
                return true;
            }
        }
        return false;
    }

}
