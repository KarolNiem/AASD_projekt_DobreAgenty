package com.dobreagenty.misc;

public class OfferType {
    public OfferTypeEnum type;
    public TargetGroup targetGroup;
    public int price;

    public OfferType(OfferTypeEnum type) {
        this.type = type;
        price = GetPrice(type);
        targetGroup = GetTargetGroup(type);
    }

    public int GetPrice(OfferTypeEnum type) {
        return switch (type) {
            case School -> 6000000;
            case Playground -> 300000;
            case Preschool -> 1000000;
            case BrineGraduationTower -> 900000;
            case ParkingLot -> 1200000;
            case BusStop -> 250000;
            case TrashCan -> 10000;
            case Library -> 5000000;
        };
    }

    public TargetGroup GetTargetGroup(OfferTypeEnum type) {
        return switch (type) {
            case School, Preschool, Playground -> TargetGroup.Children;
            case BrineGraduationTower, Library -> TargetGroup.Seniors;
            case ParkingLot, BusStop, TrashCan -> TargetGroup.Adults;
        };
    }
}
