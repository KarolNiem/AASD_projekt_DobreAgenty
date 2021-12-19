package com.dobreagenty.payloads;

import com.dobreagenty.misc.*;
import org.json.JSONObject;
import java.util.UUID;

public class Offer {
    public UUID id;
    public String name;
    public OfferType type;
    public District district;

    public Offer(String name, OfferTypeEnum offerTypeEnum, DistrictEnum districtEnum) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.type = new OfferType(offerTypeEnum);
        this.district = new District(districtEnum);
    }

    public Offer(JSONObject json) {
        id = UUID.fromString(json.getString("id"));
        name = json.getString("name");
        type = new OfferType(OfferTypeEnum.values()[json.getInt("type")]);
        district = new District(DistrictEnum.values()[json.getInt("district")]);
    }

    public int getNumberOfTargetPopulation() {
        return switch (type.targetGroup) {
            case Children ->   district.numberOfChildren;
            case Adults ->   district.numberOfAdults;
            case Seniors ->   district.numberOfSeniors;
        };
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id.toString());
        json.put("name", name);
        json.put("type", type.type.ordinal());
        json.put("district", district.districtEnum.ordinal());
        return json.toString();
    }
}
