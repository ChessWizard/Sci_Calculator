package com.chess.sci_calculator;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;// Ekledigimiz 3. parti kutuphanenin tum icerigini projemize import ettik.

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;



import com.chess.sci_calculator.databinding.ActivityIntegralBinding;

public class IntegralActivity extends AppCompatActivity {

    // Integral islemlerimizin bulundugu sinifimiz icin degisken tanimlandi.
    IntegralCalculus integralCalculus;

    String upperValue;
    String lowerValue;
    String userDefiniton;

    private ActivityIntegralBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntegralBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Butonlarimizin arkaplan renklerini ayarliyoruz.

        binding.integralclearButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255,0,0)));
        binding.integralparanthesisButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.integralexpoButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.integraldivButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.integralmultiplyButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.integralsubButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.integraladdButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.integralequalButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(0,93,244)));
        binding.integralsevenButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.integraleightButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.integralnineButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.integralsixButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.integralfiveButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.integralfourButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.integralthreeButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.integraltwoButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.integraloneButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.integralzeroButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.integralXButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.integralpointButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.integralbackButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(0,63,9)));

        // Integral isaretimizin (.svg formattaki) rengini degistiriyoruz
        binding.integralImage.setColorFilter(Color.WHITE);

        // Baslangic klavyesi etkisiz hale getirildi
        binding.integralupperValue.setShowSoftInputOnFocus(false);// Klavye gosterim metodunu false yaparak etkisiz hale getirmis olduk.
        binding.integrallowerValue.setShowSoftInputOnFocus(false);
        binding.userDefinition.setShowSoftInputOnFocus(false);



        //integralCalculus = new IntegralCalculus(upperValue,lowerValue,userDefiniton);



    }

    // Ust degeri icin

    public void received_upper_value(String received_upper){

            String oldStr = binding.integralupperValue.getText().toString();
            int cursorPosition = binding.integralupperValue.getSelectionStart();// Ust deger icin "secilmeye baslama konumu" alindi.

            String leftStr = oldStr.substring(0,cursorPosition);// Girilen degerin sol kismi alindi
            String rightStr = oldStr.substring(cursorPosition);// Geri kalan kisim da ifadenin sag kismidir.

            // Son olarak alinan ifade ve ortaya girilen ifade de olduysa bunlar goruntuleniyor.

            binding.integralupperValue.setText(String.format("%s%s%s",leftStr,received_upper,rightStr));
            binding.integralupperValue.setSelection(cursorPosition+1);// Her deger girildiginde cursor'umuz saga kaysin.

    }


    // Alt degeri icin
    public void received_lower_value(String received_lower){

            String oldStr = binding.integrallowerValue.getText().toString();
            int cursorPosition = binding.integrallowerValue.getSelectionStart();// Ust deger icin "secilmeye baslama konumu" alindi.

            String leftStr = oldStr.substring(0,cursorPosition);// Girilen degerin sol kismi alindi
            String rightStr = oldStr.substring(cursorPosition);// Geri kalan kisim da ifadenin sag kismidir.

            // Son olarak alinan ifade ve ortaya girilen ifade de olduysa bunlar goruntuleniyor.

            binding.integrallowerValue.setText(String.format("%s%s%s",leftStr,received_lower,rightStr));
            binding.integrallowerValue.setSelection(cursorPosition+1);// Her deger girildiginde cursor'umuz saga kaysin.

    }

    public void received_user_definition(String userDef){

        String oldStr = binding.userDefinition.getText().toString();
        int cursorPosition = binding.userDefinition.getSelectionStart();// Ust deger icin "secilmeye baslama konumu" alindi.

        String leftStr = oldStr.substring(0,cursorPosition);// Girilen degerin sol kismi alindi
        String rightStr = oldStr.substring(cursorPosition);// Geri kalan kisim da ifadenin sag kismidir.

        // Son olarak alinan ifade ve ortaya girilen ifade de olduysa bunlar goruntuleniyor.

        binding.userDefinition.setText(String.format("%s%s%s",leftStr,userDef,rightStr));
        binding.userDefinition.setSelection(cursorPosition+1);// Her deger girildiginde cursor'umuz saga kaysin.
    }

    public void integralbackBTN(View view){
        int cursorPos = binding.userDefinition.getSelectionStart();// Cursor(imlec) posizyonunu(indisini) aldik.
        int textLen = binding.userDefinition.getText().length();// Girilen ifadenin uzunlugu aliniyor(indis sayisi + 1)

        if(cursorPos != 0 && textLen != 0){// Eger imlecimiz en solda degilse(0. indiste) ve bir karakter dizisi varsa(karakter dizisi uzunlugu 0 degilse)
            // SpannableStringBuilder -> java'daki StringBuilder kutuphanesinin android yapilari ile uyumlu hali.
            // Bir String'in "tum indislerine ulasabilmeyi" saglar bu class tipi.
            SpannableStringBuilder selection = (SpannableStringBuilder) binding.userDefinition.getText();// Girilen ifade alindi ve selection nesnesine cast edilmis bicimde atandi.
            // Indislere ulasildaiktan sonra bir karakteri bosluk ile degistirip onu silmis oluyoruz.
            selection.replace(cursorPos - 1,cursorPos,"");// İmlecin oldugu yerden bir onceki(sagindaki) ifadeden baslayip, soluna kadar giderek(1 tane karakteri kaplayacak sekilde)
            // Bu karakterin degerini de bosluk ile(silme anlaminda) degistiren ifade.
            binding.userDefinition.setText(selection);// Her silme isleminden sonra karakter dizisinin son hali ekranda goruntulensin.
            binding.userDefinition.setSelection(cursorPos - 1);// Karakteri sildikten sonra bir sonraki karaktere(0. indise dogru) silmeye devam edebilmek icin gidiyoru.
        }

        else{
            binding.userDefinition.setHint("");// Eger ekranda islem yokken basilirsa, islem giriniz yazisi silinsin.
        }
    }

    // Butonlara tiklandiginda oluscak islemleri tutan metotlar.(onClick metotlari)
    public void integralzeroBTN(View view){
        checkFocused("0");// EditText icerisini belirleyebilmesi icin butonlara updateText'ine eklenecek veriler olarak davranmasini sagliyoruz.
    }

    public void integraloneBTN(View view){
        checkFocused("1");
    }

    public void integraltwoBTN(View view){
        checkFocused("2");
    }

    public void integralthreeBTN(View view){
        checkFocused("3");
    }

    public void integralfourBTN(View view){
        checkFocused("4");
    }

    public void integralfiveBTN(View view){
        checkFocused("5");
    }

    public void integralsixBTN(View view){
        checkFocused("6");
    }

    public void integralsevenBTN(View view){
        checkFocused("7");
    }

    public void integraleightBTN(View view){
        checkFocused("8");
    }

    public void integralnineBTN(View view){
        checkFocused("9");
    }

    public void integralclearBTN(View view){
        //updateText("");// Silme tusuna basildiginda herhangi bir sey eklenmeyecek, var olan hepsi silinecek.
        // Bundan dolayi guncelleme metodunun icerisi bos birakildi.
        binding.integralupperValue.setText("");// Icerisini bosa ayarlamak en dogrusudur. Tum ifadeler silinir.
    }

    public void integralXBTN(View view){
        checkFocused("x");
    }

    public void integralEqualBTN(View view){

        // Integral islemini yapiyoruz
        upperValue = binding.integralupperValue.getText().toString();
        lowerValue = binding.integrallowerValue.getText().toString();
        userDefiniton = binding.userDefinition.getText().toString();
        userDefiniton = userDefiniton.replaceAll("×","*");// Kodlamadaki × ifadesi yerine * karakteri gecsin.

        // Kutuphaneye gonderilecek ifade tanimlaniyor
        // StringBuilder class'ile String birlestirme yapiliyor
        // Cunku Expression constructor'i bu string yapisi ile ifadeyi aliyor.

        // 3x^2 'nin integrali nedir
        // 3*x^2 seklinde yazilarak yapilabiliyor su an

        StringBuilder userDef = new StringBuilder();
        userDef.append("int(");
        userDef.append(userDefiniton);
        userDef.append(",");
        userDef.append("x,");
        userDef.append(lowerValue);
        userDef.append(",");
        userDef.append(upperValue);
        userDef.append(")");

        Log.e("definition",userDef.toString());

        // int(islem) -> int kelimesi integration islemini yapmayi saglar.
        //int(islem,bilinmeyen ifade(x),alt sinir,ust sinir)
        Expression exp = new Expression(userDef.toString());
        String result = String.valueOf(exp.calculate());// İslem sonucu yaptirildi
        binding.userDefinition.setText(result);

    }

    public void integralPointBTN(View view){
        checkFocused(".");
    }

    public void integralExpoBTN(View view) {
        checkFocused("^");
    }

    public void integralMultiplyBTN(View view){
        checkFocused("×");
    }

    // Hangi widget'in secildigini kontrol eden metot
    // Bircok kez ayni kodu yazmak yerine metot haline getirdik
    // Her farkli degere uygun olabilmesi icin de parametreli metot yaptik
    public void checkFocused(String buttonValue){

        // isFocused() metodu -> android widget'larina "o anki tiklanma olayini" kontrol eder

        boolean focusedUpper = binding.integralupperValue.isFocused();
        boolean focusedLower = binding.integrallowerValue.isFocused();
        boolean focusedDefinition = binding.userDefinition.isFocused();

        // Hangi edittext'e tiklandiysa sadece ona yazilsin ifadeler
        if(focusedUpper){

            received_upper_value(buttonValue);
        }

        else if(focusedLower){
            received_lower_value(buttonValue);
        }

        // Islem kismi secildiyse
        else if(focusedDefinition){
            received_user_definition(buttonValue);
        }

    }



}