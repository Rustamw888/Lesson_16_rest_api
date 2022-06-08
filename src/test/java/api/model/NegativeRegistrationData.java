package api.model;

import api.iModel.INegativeRegistrationData;

public class NegativeRegistrationData implements INegativeRegistrationData {

    private String wrongEmail;

    public NegativeRegistrationData() {
    }

    public NegativeRegistrationData(String wrongEmail) {
        this.wrongEmail = wrongEmail;
    }

    @Override
    public String getWrongEmail() {
        return wrongEmail;
    }
}
