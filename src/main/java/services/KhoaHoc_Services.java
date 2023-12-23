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
import model.KhoaHoc;

/**
 *
 * @author Admin
 */
public class KhoaHoc_Services implements EduSys_Inf<KhoaHoc> {

    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;

    @Override
    public List<KhoaHoc> getAllData() {
        sql = "select MAKH,THOILUONG,HOCPHI,NGAYKHAIGIANG,MANV,NGAYTAO from KHOAHOC";
        List<KhoaHoc> lst = new ArrayList<>();
        try {

            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                KhoaHoc kh = new KhoaHoc(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4), rs.getString(5), rs.getDate(6));
                lst.add(kh);
            }

            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public int insert(KhoaHoc n) {
        sql = "insert into KhoaHoc values(?,?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, n.getMaCD());
            ps.setObject(2, n.getHocPhi());
            ps.setObject(3, n.getThoiLuong());
            ps.setObject(4, n.getKhaiGiang());
            ps.setObject(5, n.getGhiChu());
            ps.setObject(6, n.getMaNV());
            ps.setObject(7, n.getNgayTao());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String ma) {
        sql = "delete from KHOAHOC where MAKH =?";
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
    public int update(KhoaHoc n, String ma) {
        sql = "update KHOAHOC set NGAYKHAIGIANG=?, GHICHU=? where MAKH=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, n.getKhaiGiang());
            ps.setObject(2, n.getGhiChu());
            ps.setObject(3, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public KhoaHoc getTheoMa(String ma) {
        sql = "SELECT KHOAHOC.MAKH, KHOAHOC.HOCPHI, KHOAHOC.THOILUONG, KHOAHOC.NGAYKHAIGIANG, KHOAHOC.GHICHU, NHANVIEN.HOTEN, KHOAHOC.NGAYTAO FROM KHOAHOC where MAKH=? and KHOAHOC.MANV=NHANVIEN.MANV";
        KhoaHoc kh = null;
        try {

            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {

                kh = new KhoaHoc(rs.getInt(1), rs.getInt(3), rs.getDouble(2), rs.getDate(4), rs.getString(5), rs.getDate(6), rs.getString(7));

            }

            return kh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public List<KhoaHoc> getTheoChuyenDe(String ma) {
        sql = "select KH.MAKH,KH.THOILUONG,KH.HOCPHI,KH.NGAYKHAIGIANG,NV.HOTEN,KH.NGAYTAO,KH.GHICHU,CD.TENCD from KHOAHOC AS KH,CHUYENDE AS CD,NHANVIEN as NV where KH.MACD=? AND KH.MACD=CD.MACD and KH.MANV = NV.MANV";
        List<KhoaHoc> lstKH = new ArrayList<>();
        try {

            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {

                KhoaHoc kh = new KhoaHoc(rs.getInt(1), rs.getString(8), rs.getInt(2), rs.getDouble(3), rs.getDate(4), rs.getString(5), rs.getDate(6), rs.getString(7));
                lstKH.add(kh);
            }
            return lstKH;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public int updateWithNewNV(KhoaHoc n, String ma, String manv) {
        sql = "update KHOAHOC set NGAYKHAIGIANG=?, GHICHU=?, MANV=? where MAKH=?";
        NhanVien_Services he = new NhanVien_Services();

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, n.getKhaiGiang());
            ps.setObject(2, n.getGhiChu());
            ps.setObject(3, he.getMa(manv));
            ps.setObject(4, ma);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
