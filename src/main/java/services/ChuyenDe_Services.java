/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import db.DBConnect;
import interf.EduSys_Inf;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ChuyenDe;

/**
 *
 * @author Admin
 */
public class ChuyenDe_Services implements EduSys_Inf<ChuyenDe>{
    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;
    
    public List<ChuyenDe> getAllData(){
        sql="select MACD,TENCD,HOCPHI,THOILUONG,HINH,MOTA from CHUYENDE";
        List<ChuyenDe> lst = new ArrayList<>(); 
        try {
            
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
              
                lst.add(new ChuyenDe(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),rs.getString(5),rs.getString(6)));
                
            }
            
            return lst;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            
        }
        
    }
    
    @Override
    public int insert(ChuyenDe n) {
        sql="insert into CHUYENDE values(?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, n.getMaCD());
            ps.setObject(2, n.getTenCD());
            ps.setObject(3, n.getHocPhi());
            ps.setObject(4, n.getThoiLuong());
            ps.setObject(5, n.getHinh());
            ps.setObject(6, n.getMoTa());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String ma) {
        sql="delete from CHUYENDE where MACD =?";
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
    public int update(ChuyenDe n, String ma) {
        sql="update CHUYENDE set MACD=?,TENCD=?,HOCPHI=?,THOILUONG=?,HINH=?,MOTA=? where MACD=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, n.getMaCD());
            ps.setObject(2, n.getTenCD());
            ps.setObject(3, n.getHocPhi());
            ps.setObject(4, n.getThoiLuong());
            ps.setObject(5, n.getHinh());
            ps.setObject(6, n.getMoTa());
            ps.setObject(7, ma);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public ChuyenDe getTheoMa(String ma){
        sql="select MACD,TENCD,HOCPHI,THOILUONG,HINH,MOTA from CHUYENDE where MACD=?";
        ChuyenDe cd = null;
        try {
            
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
              
               cd = new ChuyenDe(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),rs.getString(5),rs.getString(6));
                
            }
            
            return cd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            
        }
    }
    
    public boolean checkTrung(String ma){
        sql="select MACD,TENCD,HOCPHI,THOILUONG,HINH,MOTA from CHUYENDE where MACD=?";
        ChuyenDe cd = null;
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
    
    public String getMaCDInKhoaHoc(String ma){
        sql="select KHOAHOC.MACD from KHOAHOC,CHUYENDE where KHOAHOC.MACD=? and KHOAHOC.MACD=CHUYENDE.MACD";
        String maCDInKhoaHoc = null;
        try {
            
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
               maCDInKhoaHoc = rs.getString(1);
               
                
            }
            
            return maCDInKhoaHoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            
        }
    }
    
    
}
