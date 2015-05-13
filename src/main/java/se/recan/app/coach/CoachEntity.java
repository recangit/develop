package se.recan.app.coach;

import se.recan.utils.ValidateUtil;

/**
 * @date 2013-feb-07
 *
 * @author Anders Recksén (recan)
 */
public class CoachEntity {

    private int county = 0;
    private String kommun = "";
    private String postort = "";
    private int language = 0;
    private int profession = 0;
    private String company = "";
    private String message = "";

    public int getCounty() {
        return county;
    }

    public void setCounty(int county) {
        this.county = county;
    }

    public String getKommun() {
        return kommun;
    }

    public void setKommun(String kommun) {
        this.kommun = kommun;
    }

    public String getPostort() {
        return postort;
    }

    public void setPostort(String postort) {
        this.postort = postort;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getProfession() {
        return profession;
    }

    public void setProfession(int profession) {
        this.profession = profession;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
        if (!ValidateUtil.validate(company, 3)) {
            setMessage("F�retagsnamn m�ste best� av minst tre tecken");
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}