package com.chess.sci_calculator;

public class IntegralCalculus {

    private float maxVal;// Belirli integraldeki tepe deger. Ondalikli degerler girilebilir.
    private float minVal;// Belirli integraldeki alt deger.
    private String userDef;// x+2 gibi sinir sartlarinin onemli oldugu ifadeler girileceginden String olmali.

    // Parametreli constructor olusturuldu.

    public IntegralCalculus(float maxVal,float minVal,String userDef){
        this.maxVal = maxVal;
        this.maxVal = minVal;
        this.userDef = userDef;
    }

    // Getter ve Setter metotlar

    public float getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(float maxVal) {
        this.maxVal = maxVal;
    }

    public float getMinVal() {
        return minVal;
    }

    public void setMinVal(float minVal) {
        this.minVal = minVal;
    }

    public String getUserDef() {
        return userDef;
    }

    public void setUserDef(String userDef) {
        this.userDef = userDef;
    }
}
