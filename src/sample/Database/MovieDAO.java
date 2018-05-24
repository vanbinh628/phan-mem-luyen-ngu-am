package sample.Database;

import sample.Database.DBConnect;
import sample.Model.Phim;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MovieDAO {
    Connection connection;
    public MovieDAO()
    {
        connection = DBConnect.getConnection();
    }




    public Phim getMovieByID(String ID) throws SQLException{

        String sql="Select * from phim where phim.id='"+ID+"'";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Phim movie=new Phim();
        while(rs.next()){

            movie.setId(rs.getInt("id"));
            movie.setTen(rs.getString("ten"));
            movie.setTheLoai(rs.getString("theloai"));
            movie.setAnh(rs.getString("anh"));
            movie.setVideo(rs.getString("video"));
            movie.setSubEng(rs.getString("subenglish"));
            movie.setSubSpell(rs.getString("subspell"));
            movie.setLevel(rs.getInt("leve"));
        }
        return movie;
    }



    public ArrayList<Phim> getMovieList() throws SQLException{

        String sql="Select * from phim";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<Phim> list  =new ArrayList<>();
        while(rs.next()){
            Phim movie=new Phim();
            movie.setId(rs.getInt("id"));
            movie.setTen(rs.getString("ten"));
            movie.setTheLoai(rs.getString("theloai"));
            movie.setAnh(rs.getString("anh"));
            movie.setVideo(rs.getString("video"));
            movie.setSubEng(rs.getString("subenglish"));
            movie.setSubSpell(rs.getString("subspell"));
            movie.setLevel(rs.getInt("leve"));
            list.add(movie);
        }
        return list;
    }
    public ArrayList<Phim> getMovieListLeve() throws SQLException{

        String sql="Select * from phim where leve=4";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<Phim> list  =new ArrayList<>();
        while(rs.next()){
            Phim movie=new Phim();
            movie.setId(rs.getInt("id"));
            movie.setTen(rs.getString("ten"));
            movie.setTheLoai(rs.getString("theloai"));
            movie.setAnh(rs.getString("anh"));
            movie.setVideo(rs.getString("video"));
            movie.setSubEng(rs.getString("subenglish"));
            movie.setSubSpell(rs.getString("subspell"));
            movie.setLevel(rs.getInt("leve"));
            list.add(movie);
        }
        return list;
    }

    // Kiem tra email co bi trung k




    // Them tai khoan
    public boolean insertPhim(Phim u) {

            String sqlinsert="insert into englishmovie.phim (ten, theloai, anh, video, subenglish, subspell, leve) values ('"+u.getTen()+"', '"+u.getTheLoai()+"', '"+u.getAnh()+"', '"+u.getVideo()+"', '"+u.getSubEng()+"', '"+u.getSubSpell()+"', "+u.getLevel()+")";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlinsert);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Phim u)  {

            String sql = "update phim SET ten='"+u.getTen()+"',theloai='"+u.getTheLoai()+"',anh='"+u.getAnh()+"',video='"+u.getVideo()+"',subenglish='"+u.getSubEng()+"',subspell='"+u.getSubSpell()+"',leve=0 where id="+u.getId()+"";

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
