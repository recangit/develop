package se.recan.app.kaffe;

import se.recan.utils.ValidateUtil;

/**
 * 2013-feb-18
 *
 * @author Anders Recksén (recan)
 */
public class KaffeEntity {

    private static final int KAFFE       = 1;
    private static final int THE         = 2;
    private static final int SVART       = 4;
    private static final int MJOLK       = 8;
    private static final int INDISKT     = 16;
    private static final int KINESISKT   = 32;
    private static final int SOCKER      = 64;
    private int summa           = 0;
    private int pris           = 0;
    private int betalning       = 0;
    private boolean leverans    = false;
    private String message      = "";
    private int filter = 0;

    public boolean isKaffe() { return (filter & KAFFE) == KAFFE; }

    public void setKaffe(boolean filter) {
        this.filter = filter ? this.filter | KAFFE : this.filter & ~KAFFE;
        this.pris += 5;
    }

    public boolean isThe() { return (filter & THE) == THE; }

    public void setThe(boolean filter) {
        this.filter = filter ? this.filter | THE : this.filter & ~THE;
        this.pris += 4;
    }

    public boolean isSvart() { return (filter & SVART) == SVART; }

    public void setSvart(boolean filter) {
        this.filter = filter ? this.filter | SVART : this.filter & ~SVART;
    }

    public boolean isMjolk() { return (filter & MJOLK) == MJOLK; }

    public void setMjolk(boolean filter) {
        this.filter = filter ? this.filter | MJOLK : this.filter & ~MJOLK;
        this.pris += 1;
    }

    public boolean isIndiskt() { return (filter & INDISKT) == INDISKT; }

    public void setIndiskt(boolean filter) {
        this.filter = filter ? this.filter | INDISKT : this.filter & ~INDISKT;
    }

    public boolean isKinesiskt() { return (filter & KINESISKT) == KINESISKT; }

    public void setKinesiskt(boolean filter) {
        this.filter = filter ? this.filter | KINESISKT : this.filter & ~KINESISKT;
        this.pris += 2;
    }

    public boolean isSocker() { return (filter & SOCKER) == SOCKER; }

    public void setSocker(boolean filter) {
        this.filter = filter ? this.filter | SOCKER : this.filter & ~SOCKER;
    }

    public int getSumma() {
        return summa += betalning;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    public int getBetalning() {
        return betalning;
    }

    public void setBetalning_1(int betalning) {
        this.betalning = betalning;
    }

    public void setBetalning_5(int betalning) {
        this.betalning = betalning;
    }

    public void setBetalning_10(int betalning) {
        this.betalning = betalning;
    }

    public boolean isLeverans() {
        return leverans;
    }

    public void setLeverans(boolean leverans) {
        this.leverans = leverans;
    }

    public String getMessage() {
        return message + "<br /> Betalt " + summa + " Pris " + pris;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFilter(int filter) {
        this.filter = filter;
    }

    public boolean isValidCombination() {
        boolean valid = false;
        
//        if(ValidateUtil.validate(filter) && summa >= pris) { valid = true; }
        
        return valid;
    }
}