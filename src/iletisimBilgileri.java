
public class iletisimBilgileri {
    private int id;
    private String isimsoyisim;
    private String yas;
    private String telefon;
    private String kangrubu;
    private String adres;

    public iletisimBilgileri(int id, String isimsoyisim, String yas, String telefon, String kangrubu,String adres) {
        this.id = id;
        this.isimsoyisim = isimsoyisim;
        this.yas = yas;
        this.telefon = telefon;
        this.kangrubu = kangrubu;
        this.adres = adres;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsimsoyisim() {
        return isimsoyisim;
    }

    public void setIsimsoyisim(String isimsoyisim) {
        this.isimsoyisim = isimsoyisim;
    }

    public String getYas() {
        return yas;
    }

    public void setYas(String yas) {
        this.yas = yas;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getKangrubu() {
        return kangrubu;
    }

    public void setKangrubu(String kangrubu) {
        this.kangrubu = kangrubu;
    }
    
}
