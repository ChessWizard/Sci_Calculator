package com.chess.sci_calculator;
import org.apache.commons.lang3.StringUtils;

public class IntegralCalculus {

    private String upperLimit;// Belirli integraldeki tepe deger. Ondalikli degerler girilebilir.
    private String lowerLimit;// Belirli integraldeki alt deger.
    private String userDef;// x+2 gibi sinir sartlarinin onemli oldugu ifadeler girileceginden String olmali.

    // Parametreli constructor olusturuldu.
    public IntegralCalculus(String upperLimit, String lowerLimit, String userDef) {
        if(StringUtils.isNumeric(upperLimit)){
            this.upperLimit = upperLimit;
        }
        if(StringUtils.isNumeric(lowerLimit)){
            this.lowerLimit = lowerLimit;
        }
        this.userDef = userDef;
    }

    // Getter ve Setter metotlar


    public String getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(String upperLimit) {

        if(StringUtils.isNumeric(upperLimit)){
            this.upperLimit = upperLimit;
        }

    }

    public String getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(String lowerLimit) {
        if(StringUtils.isNumeric(lowerLimit)){
            this.lowerLimit = lowerLimit;
        }
    }

    public String getUserDef() {
        return userDef;
    }

    public void setUserDef(String userDef) {
        this.userDef = userDef;
    }
}
