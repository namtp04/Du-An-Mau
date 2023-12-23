/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import db.DBConnect;
import interf.EduSys_Inf;
import model.NhanVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.KhoaHoc;

/**
 *
 * @author Admin
 */
public class NhanVien_Services implements EduSys_Inf<NhanVien>{
    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;
    
    public List<NhanVien> getAllData(){
        sql="select MANV,MATKHAU,HOTEN,VAITRO from NHANVIEN";
        List<NhanVien> lst = new ArrayList<>(); 
        try {
            
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
              
                lst.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
                
            }
            
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            
        }
    }
    
    @Override
    public int insert(NhanVien n) {
        sql="insert into NHANVIEN values(?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, n.getMaNV());
            ps.setObject(2, n.getMatKhau());
            ps.setObject(3, n.getHoTen());
            ps.setObject(4, n.isVaiTro());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String ma) {
        sql="delete from NHANVIEN where MANV =?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    

    @Override
    public int update(NhanVien n, String ma) {
        sql="update NHANVIEN set MATKHAU=?, HOTEN=?, VAITRO=? where MANV=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, n.getMatKhau());
            ps.setObject(2, n.getHoTen());
            ps.setObject(3, n.isVaiTro());
            ps.setObject(4, ma);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public NhanVien getTheoMa(String ma){
        sql="select MANV,MATKHAU,HOTEN,VAITRO from NHANVIEN where MANV=?";
        NhanVien nv = null;
        try {
            
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
              
               nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
                
            }
            
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            
        }
    }
    
    public String getMa(String ma){
        sql="select MANV from NHANVIEN where MANV=?";
        String nv = null;
        try {
            
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
              
                nv = rs.getString(1);
                
            }
            
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            
        }
    }
    
    
    public boolean checkTrung(String ma){
        sql="select MANV,MATKHAU,HOTEN,VAITRO from NHANVIEN where MANV=?";
        NhanVien nv = null;
        try {
            
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
              
               return false;
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return true;
            
        }
        return true;
    }
    
    
    public String getMaNVInKhoaHoc(String ma){
        sql="select KHOAHOC.MANV from KHOAHOC,NHANVIEN where KHOAHOC.MANV=? and KHOAHOC.MANV=NHANVIEN.MANV";
        String maNVInKhoaHoc = null;
        try {
            
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
               maNVInKhoaHoc = rs.getString(1);
               
                
            }
            
            return maNVInKhoaHoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            
        }
    }
    
    public String getMaNVInNguoiHoc(String ma){
        sql="select NGUOIHOC.MANV from NGUOIHOC,NHANVIEN where NGUOIHOC.MANV=? and NGUOIHOC.MANV=NHANVIEN.MANV";
        String maNVInNguoiHoc = null;
        try {
            
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
               maNVInNguoiHoc = rs.getString(1);
               
                
            }
            
            return maNVInNguoiHoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            
        }
    }
    
}
