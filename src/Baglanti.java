
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.timestamp.TSRequest;

public class Baglanti {
    
    
    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
    public Baglanti(){
        try {
            String url = "jdbc:mysql://"+Database.host+":"+Database.port+"/"+Database.db_ismi;
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection(url, Database.kullaniciAdi, Database.sifre);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public ArrayList<iletisimBilgileri> bilgileriGetir(){
        try {
            String query = "SELECT * FROM iletisimbilgileri";
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            ArrayList<iletisimBilgileri> iletisimArray = new ArrayList<iletisimBilgileri>();
            while(rs.next()){
                int id = rs.getInt("id");
                String isimsoyisim = rs.getString("isimsoyisim");
                String yas = rs.getString("yas");
                String telefon = rs.getString("telefon");
                String kangrubu = rs.getString("kangrubu");
                String adres = rs.getString("adres");
                
                
                iletisimArray.add(new iletisimBilgileri(id, isimsoyisim, yas, telefon, kangrubu,adres));

            }
            return iletisimArray;
        } catch (SQLException ex) {Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);    
        }
        return null;
        
    }
    public boolean girisKontrol(String ka,String sifree){
        try {
            statement = con.createStatement();
            String query = "SELECT * FROM admins";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                String kullaniciAdiString = rs.getString("kullaniciadi");
                String sifreString = rs.getString("sifre");
                
                if(kullaniciAdiString.equals(ka) && sifreString.equals(sifree)){
                    return true;
                }  
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
        
    }
    public void bilgiEkle(String isimSoyisim, String yas,String telefon,String kanGrubu,String adres){
        try {
            String query = "INSERT INTO iletisimbilgileri (isimsoyisim,yas,telefon,kangrubu,adres) VALUES ('" + isimSoyisim + "','" +yas + "','" + telefon + "','" +kanGrubu + "','" + adres + "')";
            
            statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void bilgiSil(int id){
        try {
            String query = "DELETE FROM iletisimbilgileri WHERE id =" + id;
            statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void bilgiGuncelle(String isimsoyisim,String yas,String telefon, String kangrubu,String adres,int id){
        try {
            String query = "UPDATE iletisimbilgileri SET isimsoyisim=?,yas =?,telefon=?,kangrubu=?,adres=? WHERE id=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, isimsoyisim);
            preparedStatement.setString(2, yas);
            preparedStatement.setString(3, telefon);
            preparedStatement.setString(4, kangrubu);
            preparedStatement.setString(5, adres);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
