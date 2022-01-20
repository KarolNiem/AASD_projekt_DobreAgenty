package com.dobreagenty.misc;

public enum OfferTypeEnum {
    School,
    Playground,
    Preschool,
    BrineGraduationTower, // tężnia solankowa :)
    ParkingLot,
    BusStop,
    TrashCan,
    Library,
    TestObject,
    TestObject2,
    TestObject3;

    @Override
    public String toString() {
        return switch (this) {
            case School -> "Szkoła";
            case Playground -> "Plac zabaw";
            case Preschool -> "Przedszkole";
            case BrineGraduationTower -> "Tężnia solankowa";
            case ParkingLot -> "Parking";
            case BusStop -> "Przystanek autobusowy";
            case TrashCan -> "Śmietnik";
            case TestObject -> "Obiekt testowy";
            case TestObject2 -> "Obiekt testowy 2";
            case TestObject3 -> "Obiekt testowy 3";
            case Library -> "Biblioteka";
        };
    }
}
