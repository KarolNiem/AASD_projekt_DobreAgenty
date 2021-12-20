package com.dobreagenty.payloads;

import com.dobreagenty.misc.DistrictEnum;
import com.dobreagenty.misc.OfferType;

public class Idea {
    public String name;
    public OfferTypeEnum offerType;
    public DistrictEnum district;

    public Idea(String name, OfferTypeEnum offerType, DistrictEnum district) {
        this.name = name;
        this.offerType = offerType;
        this.district = district;
    }

    public Idea(JSONObject json) {
        name = json.getString("name");
        offerType = OfferTypeEnum.values()[json.getInt("type")];
        district = DistrictEnum.values()[json.getInt("district")];
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", offerType);
        json.put("district", district);
        return json.toString();
    }
}
