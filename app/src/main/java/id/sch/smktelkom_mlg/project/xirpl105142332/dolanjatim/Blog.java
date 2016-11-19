package id.sch.smktelkom_mlg.project.xirpl105142332.dolanjatim;

/**
 * Created by rizaalvin on 19/11/2016.
 */

public class Blog {
    private String deskripsi;
    private String judul;
    private String logo;

    public Blog() {

    }

    public Blog(String deskripsi, String judul, String logo) {
        this.deskripsi = deskripsi;
        this.judul = judul;
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }


}
