package com.corneloaie.android.top10devs;

/**
 * Created by Cornel-PC on 04/03/2018.
 */

public class Developer {
    private int userID;
    private String displayName;
    private String location;
    private int goldBadge;
    private int silverBadge;
    private int bronzeBadge;
    private String profileImage;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getGoldBadge() {
        return goldBadge;
    }

    public void setGoldBadge(int goldBadge) {
        this.goldBadge = goldBadge;
    }

    public int getSilverBadge() {
        return silverBadge;
    }

    public void setSilverBadge(int silverBadge) {
        this.silverBadge = silverBadge;
    }

    public int getBronzeBadge() {
        return bronzeBadge;
    }

    public void setBronzeBadge(int bronzeBadge) {
        this.bronzeBadge = bronzeBadge;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
