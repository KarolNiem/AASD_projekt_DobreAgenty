package com.dobreagenty.misc;

public enum DistrictEnum {
    Srodmiescie,
    Mokotow,
    Wola,
    Zoliborz,
    PragaPln,
    TestDistrict,
    Average;

    @Override
    public String toString() {
        return switch (this) {
            case Srodmiescie -> "Śródmieście";
            case Mokotow -> "Mokotów";
            case Wola -> "Wola";
            case Zoliborz -> "Żoliborz";
            case PragaPln -> "Praga Północ";
            case TestDistrict -> "TestDistrict";
            case Average -> "";
        };
    }
}
