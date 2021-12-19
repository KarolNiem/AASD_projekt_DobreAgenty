package com.dobreagenty.payloads;

import com.dobreagenty.misc.DistrictEnum;
import com.dobreagenty.misc.OfferType;

public class Idea {
    public String name;
    public OfferType offerType;
    public DistrictEnum district;

    public Idea(String name, OfferType offerType, DistrictEnum district) {
        this.name = name;
        this.offerType = offerType;
        this.district = district;
    }
}
