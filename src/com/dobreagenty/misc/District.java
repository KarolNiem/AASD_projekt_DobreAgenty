package com.dobreagenty.misc;

public class District {
    public DistrictEnum districtEnum;
    public String name;
    public int budget;
    public int numberOfChildren;
    public int numberOfAdults;
    public int numberOfSeniors;
    public int schools;
    public int playgrounds;
    public int preschools;
    public int brineGraduationTowers;
    public int parkingLots;
    public int busStops;
    public int trashCans;
    public int libraries;

    public District(DistrictEnum districtEnum) {
        this.districtEnum = districtEnum;
        name = districtEnum.toString();
        budget = getBudget(districtEnum);
        numberOfChildren = getNumberOfChildren(districtEnum);
        numberOfAdults = getNumberOfAdults(districtEnum);
        numberOfSeniors = getNumberOfSeniors(districtEnum);
        schools = getNumberOfSchools(districtEnum);
        playgrounds = getNumberOfPlaygrounds(districtEnum);
        preschools = getNumberOfPreschools(districtEnum);
        brineGraduationTowers = getNumberOfBrineGraduationTowers(districtEnum);
        parkingLots = getNumberOfParkingLots(districtEnum);
        busStops = getNumberOfBusStops(districtEnum);
        trashCans = getNumberOfTrashCans(districtEnum);
        libraries = getNumberOfLibraries(districtEnum);
    }

    public int getNumberOfPlaces(DistrictEnum district, OfferTypeEnum type) {
        return switch (type) {
            case School -> getNumberOfSchools(district);
            case Playground -> getNumberOfPlaygrounds(district);
            case Preschool -> getNumberOfPreschools(district);
            case BrineGraduationTower -> getNumberOfBrineGraduationTowers(district);
            case ParkingLot -> getNumberOfParkingLots(district);
            case BusStop -> getNumberOfBusStops(district);
            case TrashCan -> getNumberOfTrashCans(district);
            case Library -> getNumberOfLibraries(district);
        };
    }

    public int getBudget(DistrictEnum district) {
        return switch (district) {
            case Srodmiescie -> 19000000;
            case Mokotow -> 33000000;
            case Wola -> 40000000;
            case Zoliborz -> 15000000;
            case PragaPln -> 15000000;
            case Average -> 24400000;
        };
    }

    public int getNumberOfChildren(DistrictEnum district) {
        return switch (district) {
            case Srodmiescie -> 12794;
            case Mokotow -> 31536;
            case Wola -> 20298;
            case Zoliborz -> 8177;
            case PragaPln -> 8484;
            case Average -> 16257;
        };
    }

    public int getNumberOfAdults(DistrictEnum district) {
        return switch (district) {
            case Srodmiescie -> 71966;
            case Mokotow -> 133361;
            case Wola -> 90709;
            case Zoliborz -> 32837;
            case PragaPln -> 42243;
            case Average -> 74223;
        };
    }

    public int getNumberOfSeniors(DistrictEnum district) {
        return switch (district) {
            case Srodmiescie -> 28953;
            case Mokotow -> 53368;
            case Wola -> 30400;
            case Zoliborz -> 11778;
            case PragaPln -> 12754;
            case Average -> 27450;
        };
    }

    public int getNumberOfSchools(DistrictEnum district) {
        return switch (district) {
            case Srodmiescie -> 20;
            case Mokotow -> 18;
            case Wola -> 19;
            case Zoliborz -> 19;
            case PragaPln -> 15;
            case Average -> 18;
        };
    }

    public int getNumberOfPlaygrounds(DistrictEnum district) {
        return switch (district) {
            case Srodmiescie -> 33;
            case Mokotow -> 29;
            case Wola -> 22;
            case Zoliborz -> 6;
            case PragaPln -> 8;
            case Average -> 20;
        };
    }

    public int getNumberOfPreschools(DistrictEnum district) {
        return switch (district) {
            case Srodmiescie -> 56;
            case Mokotow -> 105;
            case Wola -> 66;
            case Zoliborz -> 28;
            case PragaPln -> 19;
            case Average -> 55;
        };
    }

    public int getNumberOfBrineGraduationTowers(DistrictEnum district) {
        return switch (district) {
            case Srodmiescie, Mokotow, Zoliborz -> 0;
            case Wola, PragaPln, Average -> 1;
        };
    }

    public int getNumberOfParkingLots(DistrictEnum district) {
        return switch (district) {
            case Srodmiescie -> 19;
            case Mokotow -> 23;
            case Wola -> 20;
            case Zoliborz -> 18;
            case PragaPln -> 18;
            case Average -> 20;
        };
    }

    public int getNumberOfBusStops(DistrictEnum district) {
        return switch (district) {
            case Srodmiescie -> 138;
            case Mokotow -> 208;
            case Wola -> 249;
            case Zoliborz -> 176;
            case PragaPln -> 153;
            case Average -> 185;
        };
    }

    public int getNumberOfTrashCans(DistrictEnum district) {
        return switch (district) {
            case Srodmiescie -> 1991;
            case Mokotow -> 1383;
            case Wola -> 827;
            case Zoliborz -> 386;
            case PragaPln -> 680;
            case Average -> 1053;
        };
    }

    public int getNumberOfLibraries(DistrictEnum district) {
        return switch (district) {
            case Srodmiescie -> 12;
            case Mokotow -> 17;
            case Wola -> 9;
            case Zoliborz -> 9;
            case PragaPln -> 8;
            case Average -> 11;
        };
    }
}
