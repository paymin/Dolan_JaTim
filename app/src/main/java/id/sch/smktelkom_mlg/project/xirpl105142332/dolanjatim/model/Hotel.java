package id.sch.smktelkom_mlg.project.xirpl105142332.dolanjatim.model;

import java.io.Serializable;

/**
 * Created by paymin on 01/11/2016.
 */

public class Hotel implements Serializable {
    public String judul;
    public String deskripsi;
    public String detail;
    public String foto;
    public String lokasi;

    public Hotel(String judul, String deskripsi, String detail, String lokasi, String foto) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.detail = detail;
        this.lokasi = lokasi;
        this.foto = foto;
    }

}
