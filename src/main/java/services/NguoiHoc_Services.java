/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import db.DBConnect;
import interf.EduSys_Inf;
import java.util.ArrayList;
import java.util.List;
import model.NguoiHoc;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class NguoiHoc_Services implements EduSys_Inf<NguoiHoc>{
    
    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;

    @Override
    public List<NguoiHoc> getAllData() {
        sql="SELECT * FROM NGUOIHOC";
        List<NguoiHoc> lst = new ArrayList<>(); 
        try {
            
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
              
                lst.add(new NguoiHoc(rs.getString(1),rs.getString(2),rs.getBoolean(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9)));
                
            }
            
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            
        }
    }

    @Override
    public int insert(NguoiHoc n) {
        sql="insert into NGUOIHOC values(?,?,?,?,?,?,?,?,?)";
        try {
            con=DBConnect.getConnection();
            ps=con.prepareStatement(sql);
            ps.setObject(1, n.getMaNH());
            ps.setObject(2, n.getHoTen());
            ps.setObject(3, n.isGioiTinh());
            ps.setObject(4, n.getNgaySinh());
            ps.setObject(5, n.getDienThoai());
            ps.setObject(6, n.getEmail());
            ps.setObject(7, n.getGhiChu());
            ps.setObject(8, n.getMaNV());
            ps.setObject(9, n.getNgayDangKy());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int delete(String ma) {
        sql="delete from NGUOIHOC where MANH =?";
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
    public int update(NguoiHoc n, String ma) {
        sql="update NGUOIHOC set HOTEN=?, GIOITINH=?, NGAYSINH=?, DIENTHOAI=?, EMAIL=?, GHICHU=? where MANH=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, n.getHoTen());
            ps.setObject(2, n.isGioiTinh());
            ps.setObject(3, n.getNgaySinh());
            ps.setObject(4, n.getDienThoai());
            ps.setObject(5, n.getEmail());
            ps.setObject(6, n.getGhiChu());
           
            ps.setObject(7, ma);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public NguoiHoc getTheoMa(String ma) {
        sql="select * from NGUOIHOC where MANH=?";
        NguoiHoc nv = null;
        try {
            
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
              
               nv = new NguoiHoc(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9));
                
            }
            
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            
        }
    }
    
    public List<NguoiHoc> getTheoTen(String ten) {
        sql="SELECT * FROM NGUOIHOC where HOTEN like ?";
        List<NguoiHoc> lst = new ArrayList<>(); 
        try {
            
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + ten + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
              
                lst.add(new NguoiHoc(rs.getString(1),rs.getString(2),rs.getBoolean(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9)));
                
            }
            
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            
        }
    }
    
    public boolean checkTrung(String ma){
        sql="select * from NGUOIHOC where MANH=?";
        
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
    
    public int updateWithNewNV(NguoiHoc n, String ma,String manv) {
        sql = "update NGUOIHOC set HOTEN=?, GIOITINH=?, NGAYSINH=?, DIENTHOAI=?, EMAIL=?, GHICHU=?, MANV=? where MANH=?";
        NhanVien_Services he = new NhanVien_Services();
        
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, n.getHoTen());
            ps.setObject(2, n.isGioiTinh());
            ps.setObject(3, n.getNgaySinh());
            ps.setObject(4, n.getDienThoai());
            ps.setObject(5, n.getEmail());
            ps.setObject(6, n.getGhiChu());
            ps.setObject(7, n.getMaNV());
           
            ps.setObject(8, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    
}
