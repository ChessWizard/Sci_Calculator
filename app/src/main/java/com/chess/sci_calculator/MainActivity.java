package com.chess.sci_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import org.mariuszgromada.math.mxparser.*;// Ekledigimiz 3. parti kutuphanenin tum icerigini projemize import ettik.

import com.chess.sci_calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // İslem butonlarimizin tasarimi icin TableLayout kullaniyoruz. Responsive sekilde butonlarin gorunmesini saglayacak.
        // 5 satirdan olusuyor, her bir satirina dorder tane islem butonu koyuyoruz.
        // Her bir islem botununun ismi ve yapisini string.xml dosyasina yaziyoruz ki hardcoded-text olmasin.
        // EditText'imize hint(ipucu) ozelligi ile kullanicinin deger girmesini soyledik.
        // Java kodlarinin bir denklemi degerlendirebilmesi icin mxParser adinda bir kutuphane indiriyoruz.
        // Bu kutuphaneyi eklemek icin uyumlu jdk surumumuz ile(JDK 11) sol ustteki Project->app->libs kismina ekliyoruz projemizin.
        // Bu .jar dosyasini Create as library diyerek kutuphane seklinde getiriyoruz projemiz icerisinde.
        // android:textAllCaps="false" ifadesi -> xml tasarimi uzerindeki yazilari lowercase(kucuk harfli) yapar.

        // Butonlarimizin arkaplan renklerini ayarliyoruz.

        binding.clearButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(255,0,0)));
        binding.paranthesisButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.expoButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.divButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.multiplyButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.subButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.addButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.equalButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(0,93,244)));
        binding.sevenButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.eightButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.nineButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.sixButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.fiveButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.fourButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.threeButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.twoButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.oneButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.zeroButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(151,239,0)));
        binding.percentageButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.pointButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(137,206,0)));
        binding.backButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(0,63,9)));


        // Uygulamadaki EditText' tiklandiginda cikan "varsayilan klavyeden kurtulmak"

        binding.valueText.setShowSoftInputOnFocus(false);// Klavye gosterim metodunu false yaparak etkisiz hale getirmis olduk.

        // Arkaplan rengi uzerinde gorunebilmesi icin hint ifademizin rengini ayarliyoruz.

        binding.valueText.setHintTextColor(ColorStateList.valueOf(Color.rgb(255,0,0)));

        // EditText altindaki cizgiyi kaldiriyoruz

        binding.valueText.setBackgroundResource(0);// Arkaplani 0(false) yapinca, EditText'in alt cizgisi dahil tum arkaplani kapatildi.

        // Ekranin landscape(yan ekran) oldugu zamanda bu tasarimlar gerceklestirilmeli
        // Cunku nesnelerin id'lerine ulastigimiz icin portrait modda bu nesneler yok
        // Bundan dolayi program cokuyordu!
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            binding.secondPower.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(192,230,209)));
            binding.index.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(192,230,209)));
            binding.factorial.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(192,230,209)));
            binding.adjustableindex.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(192,230,209)));
            binding.eulerNumber.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(192,230,209)));
            binding.logarithmln.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(192,230,209)));
            binding.classicalLogarithm.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(192,230,209)));
            binding.piNumber.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(192,230,209)));
            binding.reverseLogarithmln.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(192,230,209)));
            binding.reverseLogarithm.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(192,230,209)));
            binding.reverseNumber.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(192,230,209)));
            binding.sinusFunction.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(192,230,209)));
            binding.cosFunction.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(192,230,209)));
            binding.tanFunction.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(192,230,209)));
        }


    }



    // EditText ifadesine eklenecek islemleri tutacak metot
    private void updateText(String strToAdd){
        String oldStr = binding.valueText.getText().toString();// Onceki kalan String ifade alindi.
        int cursorPosition = binding.valueText.getSelectionStart();// Imlecimiz ile tikladigimiz yerin konumunu aliyoruz ki tikladigimiz yerden itibaren isleme devam edebilelim.
        // Simdi girilen ifadeyi iki parcaya bolerek arasina yazacagimiz ifadeyi ekleyecegiz
        String leftStr = oldStr.substring(0,cursorPosition);// Bolecegimiz ifadeyi(eski ifade,ekranda bulunan sayilar) 0. indisten imlecimizin bulundugu indis'e kadar parcaliyoruz.
        String rightStr = oldStr.substring(cursorPosition);// Baslangici imlecimizin oldugu yerden, ifade nereye kadar giderseye kadar bolduk. Bu da sag tarafi olsturdu.
        // Son olarak girdigimiz yeni ifadeyi(sayiyi,sayilari) bu alinan parcalarin arasina ekliyoruz
        // NOT: Araya bir yere yazilmasa bile(koselere) calisir
        // Sola eklense -> leftStr icin substring(0,0), rightStr icin substring(girilen karakterin indis sayisi kadar)
        // Saga eklense -> leftStr icin substring(0,girilen karakterin indis sayisi kadar) rightStr icin substring(0)
        binding.valueText.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr));
        binding.valueText.setSelection(cursorPosition+1);// Bir karakter dizisi soldan saga dizilir -> 0 1 2 3 4 ...
        // Sagdan sola dogru karakterler girerken tekrar basladigimiz(sag taraftan) karakterler girilmeye devam etsin istersek
        // Geriye(saga) donmemiz gerekir. bunun icin de imlecimizin konumunu her defasinda geldigi yere 1 ekleyerek olayi gerceklestiririz.
        // Imlec sola dogru 0 olur karakter girdikce ama biz 1 arttirinca hep 1 olur ve saga gider
        // NOT: Orta kisimlara sayi veya islem girildiginde ise girilen kisma gore saga gider imlec.
    }

    // Butonlara tiklandiginda oluscak islemleri tutan metotlar.(onClick metotlari)
    public void zeroBTN(View view){
        updateText("0");// EditText icerisini belirleyebilmesi icin butonlara updateText'ine eklenecek veriler olarak davranmasini sagliyoruz.
    }

    public void oneBTN(View view){
        updateText("1");
    }

    public void twoBTN(View view){
        updateText("2");
    }

    public void threeBTN(View view){
        updateText("3");
    }

    public void fourBTN(View view){
        updateText("4");
    }

    public void fiveBTN(View view){
        updateText("5");
    }

    public void sixBTN(View view){
        updateText("6");
    }

    public void sevenBTN(View view){
        updateText("7");
    }

    public void eightBTN(View view){
        updateText("8");
    }

    public void nineBTN(View view){
        updateText("9");
    }

    public void clearBTN(View view){
        //updateText("");// Silme tusuna basildiginda herhangi bir sey eklenmeyecek, var olan hepsi silinecek.
        // Bundan dolayi guncelleme metodunun icerisi bos birakildi.
        binding.valueText.setText("");// Icerisini bosa ayarlamak en dogrusudur. Tum ifadeler silinir.
        binding.valueText.setHint("");// İpucu ifadesi de ilk islem girildikten sonra tamamen slindiginde artik bos kalsin.
    }

    public void paranthesisBTN(View view){
        int cursorPos = binding.valueText.getSelectionStart();// İmlecin konumunu aliyoruz.
        int openPar = 0;//
        int closedPar = 0;//
        int textLen = binding.valueText.getText().length();// Girilen karakter dizisinin uzunlugu alindi.

        for(int i = 0 ; i<cursorPos ; i++){// 0. indisten(en soldan) imlecimizin oldugu yere kadar indisler gezilsin.
            if(binding.valueText.getText().toString().substring(i,i+1).equals("(")){// Eger 1 karakter araliginda gidildiginde(substring(i,i+1) sayesinde) ( konulduysa.

                openPar += 1;// Acik parantez -> ( sayisi 1 artsin, (yani 1 tane eklenmesi icin degeri olsun).
            }

            else if(binding.valueText.getText().toString().substring(i,i+1).equals(")")){// Eger 1 karakter araliginda gidildiginde(substring(i,i+1) sayesinde) ) konulduysa.
                closedPar += 1; // Kapali parantez -> ) sayisi 1 artsin.
            }
        }

        // Acik parantezi ekleme sarti
        if(openPar == closedPar || binding.valueText.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");
            binding.valueText.setSelection(cursorPos+1);// Bir onceki indise geri donsun parantezi koyduktan sonra.
        }

        // Kapali parantezi ekleme sarti
        else if(closedPar < openPar && !(binding.valueText.getText().toString().substring(textLen-1,textLen).equals("("))){
            updateText(")");
            binding.valueText.setSelection(cursorPos+1);// Bir onceki indise geri donsun parantezi koyduktan sonra.
        }

    }

    public void expoBTN(View view){
        updateText("^");
    }

    public void divBTN(View view){
        updateText("÷");
    }

    public void multiplyBTN(View view){
        updateText("×");
    }

    public void subBTN(View view){
        updateText("-");
    }

    public void addBTN(View view){
        updateText("+");
    }

    public void percentageBTN(View view){
        updateText("%");
    }

    public void equalBTN(View view){
        String userExp = binding.valueText.getText().toString();// Kullanicinin girdigi islemler alinir.
        // Girdigimiz karakterlerin kullanacagimiz kutuphane uzerinde islem yapmaya saglayan olanlari ile degistirilmelerini sagliyoruz arka planda.
        // NOT: Bu ek kutuphane parantezler dahil tum karakterlerimizi algilar!
        // Sadece asagidaki karakterler icin bir degisim yapmamiz gerekti.
        // Tum bu karakterleri taniyarak calculate() metodu sayesinde bunlari isler ve sonucu bize verir!

        // Tum String icerisindeki asgida olan tum karakterler belirtilen karsiliklari ile degistirilsin.
        userExp = userExp.replaceAll("÷","/");// Kodlamadaki ÷ ifadesi yerine / karakteri gecsin
        userExp = userExp.replaceAll("×","*");// Kodlamadaki × ifadesi yerine * karakteri gecsin.
        userExp = userExp.replaceAll("√","^(1/2)");// Kok alma ifadesi, kutuphanenin anlayacagi uslu sekilde degistirildi.
        userExp = userExp.replaceAll("log","log10");// mxparser ile uyumlu log10 ifadesidir. Bundan dolayi uyumlu olan ile arkaplanda degistirildi.
        userExp = userExp.replaceAll("π","pi");// π sayisini ek kutuphanenin algilayacagi ifade ile iliskilendirdik ve girilen degerimize de bu yeni degisimi atadik.
        // Ek kutuphanemiz icerisindeki bir siniftan nesne olusturduk.

        Expression expression = new Expression(userExp);// Kullanicinin tanimi(islemi) constructor parametresi olarak alinir.

        String result = String.valueOf(expression.calculate());// Ek kutuphane sayesinde girilen sonuc ne olursa olsun hesaplansin ve String'e Parse edilsin.
        binding.valueText.setText(result);// Son olarak bu hesaplanan sonuc goruntulensin.
        binding.valueText.setSelection(result.length());// Imlecimizin konumunu da en saga ayarliyoruz son olarak.
    }

    public void pointBTN(View view){
        updateText(".");
    }


    public void backspaceBTN(View view){
        int cursorPos = binding.valueText.getSelectionStart();// Cursor(imlec) posizyonunu(indisini) aldik.
        int textLen = binding.valueText.getText().length();// Girilen ifadenin uzunlugu aliniyor(indis sayisi + 1)

        if(cursorPos != 0 && textLen != 0){// Eger imlecimiz en solda degilse(0. indiste) ve bir karakter dizisi varsa(karakter dizisi uzunlugu 0 degilse)
            // SpannableStringBuilder -> java'daki StringBuilder kutuphanesinin android yapilari ile uyumlu hali.
            // Bir String'in "tum indislerine ulasabilmeyi" saglar bu class tipi.
            SpannableStringBuilder selection = (SpannableStringBuilder) binding.valueText.getText();// Girilen ifade alindi ve selection nesnesine cast edilmis bicimde atandi.
            // Indislere ulasildaiktan sonra bir karakteri bosluk ile degistirip onu silmis oluyoruz.
            selection.replace(cursorPos - 1,cursorPos,"");// İmlecin oldugu yerden bir onceki(sagindaki) ifadeden baslayip, soluna kadar giderek(1 tane karakteri kaplayacak sekilde)
            // Bu karakterin degerini de bosluk ile(silme anlaminda) degistiren ifade.
            binding.valueText.setText(selection);// Her silme isleminden sonra karakter dizisinin son hali ekranda goruntulensin.
            binding.valueText.setSelection(cursorPos - 1);// Karakteri sildikten sonra bir sonraki karaktere(0. indise dogru) silmeye devam edebilmek icin gidiyoru.
        }

        else{
            binding.valueText.setHint("");// Eger ekranda islem yokken basilirsa, islem giriniz yazisi silinsin.
        }
    }

    // Ekran yan cevrildiginde cikan ek islemler

    public void second_power(View view){
        updateText("^(2)");
    }

    public void index(View view){
        updateText("√");
    }

    public void factorial(View view){
        updateText("!");
    }

    public void adjustable_index(View view){
        updateText("^(1÷");
        // Bu ifade yazildiktan sonra Cursor'umuz en saga gitsin ki kokun derecesi girilebilsin.
        int textLength = binding.valueText.getText().toString().length();
        binding.valueText.setSelection(textLength);// Cursor konumu setSelection(int index) metodu ile ayarlanir
        // Girilen ifade uzunlugunda giderse, en saga gitmis olur Cursor.
    }

    public void euler_number(View view){
        updateText("e");
    }

    public void logarithm_ln(View view){
        updateText("ln(");
        int textLength = binding.valueText.getText().toString().length();
        binding.valueText.setSelection(textLength);// Cursor konumu setSelection(int index) metodu ile ayarlanir
        // Girilen ifade uzunlugunda giderse, en saga gitmis olur Cursor.
    }

    public void classical_logarithm(View view){
        updateText("log(");// 10'luk tabanda logaritma islemi gonderiliyor.
        int textLength = binding.valueText.getText().toString().length();
        binding.valueText.setSelection(textLength);// Cursor konumu setSelection(int index) metodu ile ayarlanir
        // Girilen ifade uzunlugunda giderse, en saga gitmis olur Cursor.
    }

    public void reverse_logarithmln(View view){
        updateText("e^(");
        int textLength = binding.valueText.getText().toString().length();
        binding.valueText.setSelection(textLength);// Cursor konumu setSelection(int index) metodu ile ayarlanir
        // Girilen ifade uzunlugunda giderse, en saga gitmis olur Cursor.
    }

    public void reverse_logarithm(View view){
        updateText("10^(");
        int textLength = binding.valueText.getText().toString().length();
        binding.valueText.setSelection(textLength);// Cursor konumu setSelection(int index) metodu ile ayarlanir
        // Girilen ifade uzunlugunda giderse, en saga gitmis olur Cursor.
    }

    public void pi_number(View view){
        updateText("π");
    }

    public void reverse_number(View view){
        updateText("^(-1)");
    }

    public void sinus_fun(View view){
        updateText("sin(");
        int textLength = binding.valueText.getText().toString().length();
        binding.valueText.setSelection(textLength);// Cursor konumu setSelection(int index) metodu ile ayarlanir
        // Girilen ifade uzunlugunda giderse, en saga gitmis olur Cursor.
    }

    public void cosinus_fun(View view){
        updateText("cos(");
        int textLength = binding.valueText.getText().toString().length();
        binding.valueText.setSelection(textLength);// Cursor konumu setSelection(int index) metodu ile ayarlanir
        // Girilen ifade uzunlugunda giderse, en saga gitmis olur Cursor.
    }

    public void tangent_fun(View view){
        updateText("tan(");
        int textLength = binding.valueText.getText().toString().length();
        binding.valueText.setSelection(textLength);// Cursor konumu setSelection(int index) metodu ile ayarlanir
        // Girilen ifade uzunlugunda giderse, en saga gitmis olur Cursor.
    }


    // Ekrani "tam ekran" halinde goruntulemek icin yontem.
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}