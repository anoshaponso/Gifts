package com.example.Gifts.Store_Tab;

/**
 * Created by adminslt on 11/27/2014.
 */
public class Gift_Status {
    String personId;
    String giftStatus;

    public Gift_Status(String personId, String giftStatus) {
        this.personId = personId;
        this.giftStatus = giftStatus;
    }

    public String getGiftStatus() {
        return giftStatus;
    }

    public void setGiftStatus(String giftStatus) {
        this.giftStatus = giftStatus;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

}
