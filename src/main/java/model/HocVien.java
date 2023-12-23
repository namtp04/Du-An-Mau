/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class HocVien {
    private int maHV;
    private int maKH;
    private String maNH;
    private Double diemTB;
    private String hoTen;

    public HocVien() {
    }

    public HocVien(int maHV, int maKH, String maNH, Double diemTB) {
        this.maHV = maHV;
        this.maKH = maKH;
        this.maNH = maNH;
        this.diemTB = diemTB;
    }

    public HocVien(int maHV, String maNH, Double diemTB, String hoTen) {
        this.maHV = maHV;
        this.maNH = maNH;
        this.diemTB = diemTB;
        this.hoTen = hoTen;
    }

    public HocVien(int maHV, int maKH, String maNH, Double diemTB, String hoTen) {
        this.maHV = maHV;
        this.maKH = maKH;
        this.maNH = maNH;
        this.diemTB = diemTB;
        this.hoTen = hoTen;
    }
    
    
    

    public int getMaHV() {
        return maHV;
    }

    public void setMaHV(int maHV) {
        this.maHV = maHV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getMaNH() {
        return maNH;
    }

    public void setMaNH(String maNH) {
        this.maNH = maNH;
    }

    public Double getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(Double diemTB) {
        this.diemTB = diemTB;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    
    
}
