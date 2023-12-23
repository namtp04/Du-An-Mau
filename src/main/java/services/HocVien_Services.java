/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import db.DBConnect;
import interf.EduSys_Inf;
import java.util.List;
import model.HocVien;
import model.NguoiHoc;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class HocVien_Services implements EduSys_Inf<HocVien> {

    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;

    public HocVien_Services() {
    }

    @Override
    public List<HocVien> getAllData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(HocVien n) {
        sql="insert into HOCVIEN values(?,?,?)";
        try {
            con=DBConnect.getConnection();
            ps=con.prepareStatement(sql);
            ps.setObject(1, n.getMaKH());
            ps.setObject(2  , n.getMaNH());
            ps.setObject(3, n.getDiemTB());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String ma) {
        sql = "delete from HOCVIEN where MAHV=?";
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
    public int update(HocVien n, String ma) {
        sql = "update HOCVIEN set DIEM=? where MAHV=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, n.getDiemTB());
            ps.setObject(2, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    
    public int update1(Double n, String ma) {
        sql = "update HOCVIEN set DIEM=? where MAHV=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, n);
            ps.setObject(2, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public HocVien getTheoMa(String ma) {
        sql = "select * from HOCVIEN where MAHV=?";
        HocVien hv = null;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs=ps.executeQuery();
            while(rs.next()){
                hv = new HocVien(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4));
            }
            return hv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NguoiHoc> getNHNotInKH(String ten, int ma) {
        sql = "select * from NGUOIHOC where HOTEN like ? and MANH not in (select MANH from HOCVIEN where MAKH = ?)";
        List<NguoiHoc> lst = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + ten + "%");
            ps.setObject(2, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                lst.add(new NguoiHoc(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9)));
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<HocVien> getHVTheoKH(int makh) {
        sql = "select HOCVIEN.MAHV, HOCVIEN.MAKH,HOCVIEN.MANH,HOCVIEN.DIEM, NGUOIHOC.HOTEN from HOCVIEN,NGUOIHOC where HOCVIEN.MAKH=? and NGUOIHOC.MANH=HOCVIEN.MANH";
        List<HocVien> lst = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, makh);
            rs = ps.executeQuery();
            while (rs.next()) {
                lst.add(new HocVien(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getString(5)));
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }

}
