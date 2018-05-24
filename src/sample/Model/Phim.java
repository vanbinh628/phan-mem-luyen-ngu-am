package sample.Model;

public class Phim {
    int id;
    String ten;
    String theLoai;
    String anh;
    String video;
    String subEng;
    String subSpell;
    int level;

    public void setId(int id) {
        this.id = id;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setSubEng(String subEng) {
        this.subEng = subEng;
    }

    public void setSubSpell(String subSpell) {
        this.subSpell = subSpell;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public String getAnh() {
        return anh;
    }

    public String getVideo() {
        return video;
    }

    public String getSubEng() {
        return subEng;
    }

    public String getSubSpell() {
        return subSpell;
    }

    public int getLevel() {
        return level;
    }

    public Phim(String ten, String theLoai, String anh, String video, String subEng, String subSpell, int level) {
        this.id = 0;
        this.ten = ten;
        this.theLoai = theLoai;
        this.anh = anh;
        this.video = video;
        this.subEng = subEng;
        this.subSpell = subSpell;
        this.level = level;
    }
    public Phim()
    {
        this.id = 0;
        this.ten = "";
        this.theLoai = "";
        this.anh = "";
        this.video = "";
        this.subEng = "";
        this.subSpell = "";
        this.level = 0;
    }
}
