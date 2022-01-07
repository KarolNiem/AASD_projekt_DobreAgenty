package com.dobreagenty.misc;

public enum OfferTypeEnum {
    School,
    Playground,
    Preschool,
    BrineGraduationTower, // tężnia solankowa :)
    ParkingLot,
    BusStop,
    TrashCan,
    Library;

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
            case Library -> "Biblioteka";
        };
    }
}
