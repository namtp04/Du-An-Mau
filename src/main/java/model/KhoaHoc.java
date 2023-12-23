/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class KhoaHoc {
    private int maKH;
    private String tenCD;
    private int thoiLuong;
    private Double hocPhi;
    private Date khaiGiang;
    private String nguoiTao;
    private Date ngayTao;
    private String ghiChu;
    private String maCD;
    private String maNV;
    
    public KhoaHoc() {
    }

    public KhoaHoc(int thoiLuong, Double hocPhi, Date khaiGiang, Date ngayTao, String ghiChu, String maCD, String maNV) {
        this.thoiLuong = thoiLuong;
        this.hocPhi = hocPhi;
        this.khaiGiang = khaiGiang;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
        this.maCD = maCD;
        this.maNV = maNV;
    }

    
    
    public KhoaHoc(int maKH, int thoiLuong, Double hocPhi, Date khaiGiang, String maNV, Date ngayTao) {
        this.maKH = maKH;
        this.thoiLuong = thoiLuong;
        this.hocPhi = hocPhi;
        this.khaiGiang = khaiGiang;
        this.maNV = maNV;
        this.ngayTao = ngayTao;
    }

    public KhoaHoc(String tenCD, int thoiLuong, Double hocPhi, Date khaiGiang, String nguoiTao, Date ngayTao, String ghiChu) {
        this.tenCD = tenCD;
        this.thoiLuong = thoiLuong;
        this.hocPhi = hocPhi;
        this.khaiGiang = khaiGiang;
        this.nguoiTao = nguoiTao;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
    }

    public KhoaHoc(int maKH, String tenCD, int thoiLuong, Double hocPhi, Date khaiGiang, String nguoiTao, Date ngayTao, String ghiChu) {
        this.maKH = maKH;
        this.tenCD = tenCD;
        this.thoiLuong = thoiLuong;
        this.hocPhi = hocPhi;
        this.khaiGiang = khaiGiang;
        this.nguoiTao = nguoiTao;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
    }

    public KhoaHoc(int maKH, int thoiLuong, Double hocPhi, Date khaiGiang, String nguoiTao, Date ngayTao, String ghiChu) {
        this.maKH = maKH;
        this.thoiLuong = thoiLuong;
        this.hocPhi = hocPhi;
        this.khaiGiang = khaiGiang;
        this.nguoiTao = nguoiTao;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
    }
    
    

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenCD() {
        return tenCD;
    }

    public void setTenCD(String tenCD) {
        this.tenCD = tenCD;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public Double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(Double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public Date getKhaiGiang() {
        return khaiGiang;
    }

    public void setKhaiGiang(Date khaiGiang) {
        this.khaiGiang = khaiGiang;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaCD() {
        return maCD;
    }

    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String toString(){
        return this.ghiChu + " (" + this.khaiGiang + ")";
    }
    
    
    
    
    

   
    
    
    
}
