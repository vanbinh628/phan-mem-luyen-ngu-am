package sample.Database;

import sample.Model.LuuCau;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class LuuCauDAO {
    Connection connection;
    public LuuCauDAO()
    {
        connection = DBConnect.getConnection();
    }
    public ArrayList<LuuCau> getListLuuCauByID(String ID) throws SQLException{

        String sql="Select * from luucau where idphim='"+ID+"'";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<LuuCau> list  =new ArrayList<>();
        while(rs.next()){
            LuuCau luucau=new LuuCau();
            luucau.setId(rs.getInt("id"));
            luucau.setIdPhim(rs.getInt("idphim"));
            luucau.setNoidungsub(rs.getString("noidungsub"));
            luucau.setNoidungspell(rs.getString("noidungspell"));
            luucau.setTgdau(rs.getInt("tgdau"));
            luucau.setTgcuoi(rs.getInt("tgcuoi"));
            luucau.setLink(rs.getString("noidungsub"));
            list.add(luucau);
        }
        return list;
    }



    public ArrayList<LuuCau> getLuuCauList() throws SQLException{

        String sql="Select * from luucau";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<LuuCau> list  =new ArrayList<>();
        while(rs.next()){
            LuuCau luucau=new LuuCau();
            luucau.setId(rs.getInt("id"));
            luucau.setIdPhim(rs.getInt("idphim"));
            luucau.setNoidungsub(rs.getString("noidungsub"));
            luucau.setNoidungspell(rs.getString("noidungspell"));
            luucau.setTgdau(rs.getInt("tgdau"));
            luucau.setTgcuoi(rs.getInt("tgcuoi"));
            luucau.setLink(rs.getString("noidungsub"));
            list.add(luucau);
        }
        return list;
    }

    // Kiem tra email co bi trung k




    // Them tai khoan
    public boolean insertLuuCau(LuuCau u) {

        String sqlinsert="insert into englishmovie.luucau (idphim, noidungsub, noidungspell, tgdau, tgcuoi, link) values("+u.getIdPhim()+", '"+u.getNoidungsub()+"','"+u.getNoidungspell()+"',"+u.getTgdau()+","+u.getTgcuoi()+",'"+u.getLink()+"')";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlinsert);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(LuuCau u)  {

        String sql = "update luucau SET idphim='"+u.getIdPhim()+"',noidungsub='"+u.getNoidungsub()+"',noidungspell='"+u.getNoidungspell()+"',tgdau="+u.getTgdau()+",tgcuoi="+u.getTgcuoi()+",link='"+u.getLink()+"'where id="+u.getId()+"";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public static void main(String[] args) throws SQLException {
//        MovieDAO dao = new MovieDAO();
//        System.out.println(dao.checkEmail("nguoiemcuanui@gmail.com"));
//    }

}