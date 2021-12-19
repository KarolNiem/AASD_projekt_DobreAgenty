package com.dobreagenty.payloads;

import com.dobreagenty.misc.*;
import org.json.JSONObject;

public class UsabilityEvaluation extends BaseEvaluation{
    public double result;

    public UsabilityEvaluation(Offer offer) {
        super(offer);
    }

    public UsabilityEvaluation(JSONObject json) {
        super(json);
        result = json.getDouble("result");
    }

    public void evaluate() {
        double averageCoeff = getAveragePlacesPerPerson();
        double pop = offer.getNumberOfTargetPopulation();
        double places = offer.district.getNumberOfPlaces(offer.district.districtEnum, offer.type.type);
        double coeff = places / pop;
        // Value between 0 and 1, 0.5 if average, 0.0 if twice as many as average
        result = Math.min(1, Math.max(0, 1 - 0.5 * coeff / averageCoeff));
    }

    public double getAveragePlacesPerPerson() {
        District averageDistrict = new District(DistrictEnum.Average);
        TargetGroup targetGroup = offer.type.targetGroup;
        double averagePop = switch (targetGroup) {
            case Children -> averageDistrict.numberOfChildren;
            case Adults -> averageDistrict.numberOfAdults;
            case Seniors -> averageDistrict.numberOfSeniors;
        };
        double averagePlaces = averageDistrict.getNumberOfPlaces(DistrictEnum.Average, offer.type.type);
        return averagePlaces / averagePop;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", offer.id.toString());
        json.put("name", offer.name);
        json.put("type", offer.type.type.ordinal());
        json.put("district", offer.district.districtEnum.ordinal());
        json.put("result", result);
        return json.toString();
    }
}
