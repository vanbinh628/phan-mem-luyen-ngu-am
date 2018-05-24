package sample.Model;

public class LuuCau {
    int id;
    int idPhim;
    String noidungsub;
    String noidungspell;
    int tgdau;
    int tgcuoi;
    String link;
    public LuuCau()
    {}
    public LuuCau(int idPhim, String noidungsub, String noidungspell, int tgdau, int tgcuoi, String link) {
        this.id=0;
        this.idPhim = idPhim;
        this.noidungsub = noidungsub;
        this.noidungspell = noidungspell;
        this.tgdau = tgdau;
        this.tgcuoi = tgcuoi;
        this.link = link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPhim(int idPhim) {
        this.idPhim = idPhim;
    }

    public void setNoidungsub(String noidungsub) {
        this.noidungsub = noidungsub;
    }

    public void setNoidungspell(String noidungspell) {
        this.noidungspell = noidungspell;
    }

    public void setTgdau(int tgdau) {
        this.tgdau = tgdau;
    }

    public void setTgcuoi(int tgcuoi) {
        this.tgcuoi = tgcuoi;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public int getIdPhim() {
        return idPhim;
    }

    public String getNoidungsub() {
        return noidungsub;
    }

    public String getNoidungspell() {
        return noidungspell;
    }

    public int getTgdau() {
        return tgdau;
    }

    public int getTgcuoi() {
        return tgcuoi;
    }

    public String getLink() {
        return link;
    }
}
