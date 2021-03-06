import java.util.ArrayList;
import java.util.Random;

public class Ortam {

    // Genel Sabitler
    private char[] nukleikAsitler       = {'A','G','T','S'};
    private String[] HUCRE_ISIMLERI     = {"Bitki Hucresi","Mantar Hucresi","Hayvan Hucresi","Bakteri","Arke"};
    private double MAX_BESIN_MIKTARI        = 20;
    private double MAX_BUYUKLUK             = 80;
    private int DNA_UZUNLUGU                = 150;
    private double BESIN_TUKETIM_MIKTARI    = 2; // Yüzdelik
    private double BUYUME_MIKTARI           = 10; // Yüzdelik
    private double MINIMUM_BESIN_MIKTARI    = 5; // Yüzdelik
    private int MAX_RASTGELE_ZAMAN_MIKTARI    = 10; // Yüzdelik

    // Genel Değişkenler
    ArrayList<Hucre> hucreList = new ArrayList<Hucre>();
    Random random = new Random();

    public static void main(String[] args){

        // Ortam Olustur
        Ortam ortam = new Ortam();

        // Hucre ekle
        ortam.rastgeleHucreOlustur(10);

        // Ekrana yazdir
        ortam.tumHucreleriGoster();

        ortam.rastgeleZamanGecir();
        
    }

    // Rastgele Zaman Geçir
    public void rastgeleZamanGecir(){
        // Rastgele Zaman Sec
        int rastgeleZaman = random.nextInt(MAX_RASTGELE_ZAMAN_MIKTARI);
        // Zaman Gecir 
        zamanGecir(rastgeleZaman);
        // Bilgilendir 
        System.out.println(rastgeleZaman + " zaman gecti !");
        tumHucreleriGoster();
    }

    // Zaman gecir
    public void zamanGecir(int zaman){

        for(int i =0 ; i < zaman ; i++){
            birZamanDilimiGecir();
        }
        
    }

    // Zaman Geçtikçe hücreler belli bir oranda rastgele büyür ve besin kaybeder
    public void birZamanDilimiGecir(){

        // Tum Hucreleri don
        for(Hucre h: this.hucreList){
            // Besin azalt
            h.setBesinMiktari(h.getBesinMiktari() - BESIN_TUKETIM_MIKTARI);
            // Buyukluk arttir
            h.setBuyukluk(h.getBuyukluk() + BUYUME_MIKTARI);

        }

        olenHucreleriBul();

    }

    // hucrenin ölüp ölmediğini kontrol et
    public void olenHucreleriBul(){
        // Tum Hucreleri tersten don
        for(int i = this.hucreList.size()-1 ; i >= 0 ; i--){
            // Hucreyi getir
            Hucre h = this.hucreList.get(i);

            // Besin miktari belli seviyeden azsa
            if (h.getBesinMiktari() < MINIMUM_BESIN_MIKTARI){
                // hucreyi sil
                this.hucreList.remove(i);
                System.out.println(h.getID() + " hucresi açlıktan öldü ...");
            }

        }
    }

    // Bu method verilen sayı kadar rastgele hücre oluşturur
    public void rastgeleHucreOlustur(int hucreSayisi){

        for(int i=0 ; i < hucreSayisi ; i++){
            // rastgele bir isim sec
            String randomHucreIsmi      = HUCRE_ISIMLERI[random.nextInt(HUCRE_ISIMLERI.length)];
            // rastgele besin miktari sec
            double randomBesinMiktari   = random.nextDouble() * MAX_BESIN_MIKTARI;
            // rastgele buyukluk sec
            double randomBuyukluk       = random.nextDouble() * MAX_BUYUKLUK;

            // Hucre olustur
            Hucre yeniHucre = new Hucre(i,randomHucreIsmi,randomBesinMiktari,randomBuyukluk,rastgeleDNAOlustur(DNA_UZUNLUGU));
            // Hucreyi Ortam'daki array list'e ekle
            hucreList.add(yeniHucre);
        }
    }
    
    // Verilen uzunlukta random bir DNA char arraylist'i oluşturma
    public ArrayList<Character> rastgeleDNAOlustur(int DNAUzunlugu){

        // Geçici bir arraylist oluştur
        ArrayList<Character> geciciDNA = new ArrayList<Character>();

        for(int i=0 ; i < DNAUzunlugu ; i++){
            // rastgele bir nukleik asit sec ve sonuna ekle
            geciciDNA.add(nukleikAsitler[random.nextInt(nukleikAsitler.length)]);

        }

        // Oluşturduğun arraylist'i geri gönder
        return geciciDNA;
    }

    // Olusturulan Hucrelerin bilgilerini ekrana yazdir
    public void tumHucreleriGoster(){

        // Tum hucreleri don
        for (Hucre h : this.hucreList){
            // Hucreyi ekrana yazdir
            h.hucreyi_ekrana_yazdir();

        }

    }


    
}
