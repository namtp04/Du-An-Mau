/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import com.sun.source.tree.WildcardTree;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChuyenDe;
import model.KhoaHoc;
import model.NhanVien;
import services.ChuyenDe_Services;
import services.KhoaHoc_Services;
import services.NhanVien_Services;

/**
 *
 * @author Admin
 */
public class KhoaHoc_JDiaLog extends javax.swing.JDialog {
    
    DefaultTableModel tblModel = new DefaultTableModel();
    int index = -1;
    List<KhoaHoc> lstKH = new ArrayList<>();
    KhoaHoc_Services ql = new KhoaHoc_Services();
    NhanVien_Services heh = new NhanVien_Services();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public KhoaHoc_JDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }
    
    private void init() {
        setLocationRelativeTo(null);
        loadComboBox();
        loadTable(lstKH);
        txtNgayTao.setText(sdf.format(new Date()));
        updateStatus();
    }
    
    private void loadComboBox() {
        
        DefaultComboBoxModel cboModel = new DefaultComboBoxModel();
        cboModel = (DefaultComboBoxModel) cboChuyenDe.getModel();
        cboModel.removeAllElements();
        ChuyenDe_Services cdser = new ChuyenDe_Services();
        List<ChuyenDe> lstCD = new ArrayList<>();
        lstCD = cdser.getAllData();
        for (ChuyenDe cd : lstCD) {
            cboModel.addElement(cd);
        }
        
    }
    
    private void loadTable(List<KhoaHoc> lst) {
        tblModel = (DefaultTableModel) tblKhoaHoc.getModel();
        tblModel.setRowCount(0);
        ChuyenDe cd = (ChuyenDe) cboChuyenDe.getSelectedItem();
        lst = ql.getTheoChuyenDe(cd.getMaCD());
        
        for (KhoaHoc kh : lst) {
            
            tblModel.addRow(new Object[]{
                kh.getMaKH(),
                kh.getThoiLuong(),
                kh.getHocPhi(),
                sdf.format(kh.getKhaiGiang()),
                kh.getNguoiTao(),
                sdf.format(kh.getNgayTao())
            });
        }
    }
    
    private void showDetail(int index, List<KhoaHoc> lst) {
        ChuyenDe cd = (ChuyenDe) cboChuyenDe.getSelectedItem();
        lst = ql.getTheoChuyenDe(cd.getMaCD());
        KhoaHoc kh = lst.get(index);
        txtChuyenDe.setText(kh.getTenCD());
        txtThoiLuong.setText(kh.getThoiLuong() + "");
        txtHocPhi.setText(kh.getHocPhi() + "");
        txtChuyenDe.setText(kh.getTenCD());
        txtGhiChu.setText(kh.getGhiChu());
        txtKhaiGiang.setText(sdf.format(kh.getKhaiGiang()));
        txtNgayTao.setText(sdf.format(kh.getNgayTao()));
        txtNguoiTao.setText(kh.getNguoiTao());
        
    }
    
    private void clearForm() {
        txtGhiChu.setText("");
        txtNguoiTao.setText("");
        txtKhaiGiang.setText("");
        index = -1;
    }
    
    private void updateStatus() {
        ChuyenDe cd = (ChuyenDe) cboChuyenDe.getSelectedItem();
        lstKH = ql.getTheoChuyenDe(cd.getMaCD());
        boolean edit = this.index >= 0;
        boolean first = this.index == 0;
        boolean last = this.index == tblKhoaHoc.getRowCount() - 1;
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnLast.setEnabled(edit && !last);
        btnNext.setEnabled(edit && !last);
        if (lstKH.size() == 0) {
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
            btnThem.setEnabled(true);
            clearForm();
            txtNgayTao.setText(sdf.format(new Date()));
        }
    }
    
    private boolean checkForm() {
        if (txtChuyenDe.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chuyên đề");
            return false;
        }
        if (txtKhaiGiang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày khai giảng");
            return false;
        }
        if (txtHocPhi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập học phí");
            return false;
        }
        if (txtNgayTao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày tạo");
            return false;
        }
        if (txtThoiLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thời lượng");
            return false;
        }
        return true;
    }
    
    private KhoaHoc readFormToUpDate() {
        try {
            KhoaHoc kh = new KhoaHoc();
            try {
                kh.setKhaiGiang(sdf.parse(txtKhaiGiang.getText()));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Ngày chưa đúng dịnh dạng\nVui lòng nhập ngày theo định dạng dd/MM/yyyy");
                return null;
            }
            kh.setGhiChu(txtGhiChu.getText());
            return kh;
        } catch (Exception e) {
            return null;
        }
        
    }
    
    private KhoaHoc readFormToInsert() {
        try {
            KhoaHoc kh = new KhoaHoc();
            ChuyenDe cd = (ChuyenDe) cboChuyenDe.getSelectedItem();
            try {
                kh.setKhaiGiang(sdf.parse(txtKhaiGiang.getText()));
                kh.setNgayTao(sdf.parse(txtNgayTao.getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ngày chưa đúng dịnh dạng\nVui lòng nhập ngày theo định dạng dd/MM/yyyy");
                return null;
            }
            kh.setMaCD(cd.getMaCD());
            kh.setGhiChu(txtGhiChu.getText());
            kh.setThoiLuong(Integer.parseInt(txtThoiLuong.getText()));
            kh.setHocPhi(Double.parseDouble(txtHocPhi.getText()));
            String ma = JOptionPane.showInputDialog(this, "Vui lòng bổ sung mã người tạo(nhân viên) để tạo mới");
            if (ma != null) {
                kh.setMaNV(ma);
            }
            if (ma == null) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên rỗng không thể tạo mới");
                return null;
            }
            if (heh.getMa(ma) == null) {
                JOptionPane.showMessageDialog(this, "Mã người tạo không tồn tại trong hệ thống");
                return null;
            }
            
            return kh;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtKhaiGiang = new javax.swing.JTextField();
        txtThoiLuong = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtChuyenDe = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtHocPhi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtNguoiTao = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        cboChuyenDe = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhoaHoc = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EduSys - Quản lý khóa học");

        jLabel4.setText("Khai giảng");

        txtKhaiGiang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhaiGiangActionPerformed(evt);
            }
        });

        txtThoiLuong.setEnabled(false);

        txtNgayTao.setEnabled(false);

        jLabel5.setText("Thời lượng (giờ)");

        jLabel6.setText("Ngày tạo");

        jLabel8.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        jLabel9.setText("Chuyên đề");

        txtChuyenDe.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtChuyenDe.setForeground(new java.awt.Color(255, 0, 0));
        txtChuyenDe.setEnabled(false);
        txtChuyenDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtChuyenDeMouseClicked(evt);
            }
        });
        txtChuyenDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChuyenDeActionPerformed(evt);
            }
        });

        jLabel10.setText("Học phí");

        txtHocPhi.setEnabled(false);

        jLabel11.setText("Người tạo");

        txtNguoiTao.setEnabled(false);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(txtChuyenDe, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(txtHocPhi)
                            .addComponent(txtNguoiTao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(txtKhaiGiang, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                            .addComponent(txtThoiLuong)
                            .addComponent(txtNgayTao))))
                .addContainerGap())
        );

        panel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtChuyenDe, txtHocPhi, txtKhaiGiang, txtNgayTao, txtNguoiTao, txtThoiLuong});

        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKhaiGiang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtChuyenDe, txtHocPhi, txtKhaiGiang, txtNgayTao, txtNguoiTao, txtThoiLuong});

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CHUYÊN ĐỀ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(255, 0, 0))); // NOI18N

        cboChuyenDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChuyenDeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboChuyenDe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(null);

        tblKhoaHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MA KH", "THỜI LƯỢNG", "HỌC PHÍ", "KHAI GIẢNG", "TẠO BỞI", "NGÀY TẠO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhoaHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhoaHocMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhoaHoc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKhaiGiangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhaiGiangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKhaiGiangActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        updateStatus();
        if (checkForm()) {
            int hoi = JOptionPane.showConfirmDialog(this, "Xác nhận thêm mới chuyên đề?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
            if (hoi == JOptionPane.YES_OPTION) {
                KhoaHoc kh = this.readFormToInsert();
                if (kh == null) {
                    return;
                }
                
                if (ql.insert(kh) != 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    lstKH.clear();
                    
                    loadTable(lstKH);
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");
                    return;
                }
                
            }
            return;
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        updateStatus();
        if (checkForm()) {
            int hoi = JOptionPane.showConfirmDialog(this, "Xác nhận cập nhật?\n\nLưu ý: Dữ liệu liên quan cũng sẽ được cập nhật", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
            if (hoi == JOptionPane.YES_OPTION) {
                
                KhoaHoc kh = this.readFormToUpDate();
                
                if(kh==null){
                    return;
                }
                
                int chck = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật người tạo(nhân viên)?","Xác nhận",JOptionPane.YES_NO_OPTION);
                if (chck == JOptionPane.YES_OPTION) {
                    
                    String mah = JOptionPane.showInputDialog(this, "Vui lòng bổ sung mã người tạo(nhân viên) để cập nhật");
                    
                    if (heh.getMa(mah) == null) {
                        JOptionPane.showMessageDialog(this, "Mã người tạo không tồn tại trong hệ thống");
                        return;
                    }
                    if (mah != null) {
                        kh.setMaNV(mah);
                    }
                    String ma = tblKhoaHoc.getValueAt(index, 0).toString();
                        if (ql.updateWithNewNV(kh, ma,mah) != 0) {
                            JOptionPane.showMessageDialog(this, "Update thành công");
                            lstKH.clear();
                            loadTable(lstKH);
                            clearForm();
                            
                        } else {
                            JOptionPane.showMessageDialog(this, "Update thất bại");
                            clearForm();
                            return;
                        }
                    
                } else {
                    
                
                    String ma = tblKhoaHoc.getValueAt(index, 0).toString();
                    if (ql.update(kh, ma) != 0) {
                        JOptionPane.showMessageDialog(this, "Update thành công");
                        lstKH.clear();
                        clearForm();
                        loadTable(lstKH);
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Update thất bại");
                        clearForm();
                        return;
                    }
                }
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        updateStatus();
        if (lstKH.size() == 0) {
            
            JOptionPane.showMessageDialog(this, "Danh sách trống!");
            return;
        }
        if (txtKhaiGiang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã chuyên đề muốn xóa");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Xác nhận xóa?\n\n<html><font color='red'><b>Lưu ý: </b>Dữ liệu đã xóa sẽ không thể khôi phục.</font></html>", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
        if (hoi == JOptionPane.YES_OPTION) {
            
            String ma = tblKhoaHoc.getValueAt(index, 0).toString();
            if (ql.delete(ma) != 0) {
                lstKH.clear();
                JOptionPane.showMessageDialog(this, "<html><font color='green'>Xóa thành công.</font></html>");
                clearForm();
                loadTable(lstKH);
                index = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
                return;
            }
            
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clearForm();
        updateStatus();
        txtNgayTao.setText(sdf.format(new Date()));
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        try {
            
            if (index == 0) {
                return;
            }
            if (lstKH.size() == 0) {
                JOptionPane.showMessageDialog(this, "Danh sách trống!");
                return;
            } else {
                index = 0;
                showDetail(index, lstKH);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nút về đầu!");
            return;
        }
        updateStatus();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        try {
            if (index == -1) {
                return;
            }
            if (lstKH.size() == 0) {
                JOptionPane.showMessageDialog(this, "Danh sách trống!");
                return;
            } else {
                if (index > 0) {
                    index--;
                    showDetail(index, lstKH);
                    
                } else {
                    
                    return;
                    
                }
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nút lùi!");
            return;
        }
        updateStatus();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        try {
            if (index == -1) {
                return;
            }
            if (lstKH.size() == 0) {
                JOptionPane.showMessageDialog(this, "Danh sách trống!");
                return;
            }
            if (index < lstKH.size() - 1) {
                index++;
                
            } else {
                return;
            }
            
            showDetail(index, lstKH);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nút tiến!");
            return;
        }
        updateStatus();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        try {
            
            if (index == lstKH.size() - 1 && lstKH.size() != 0) {
                
                return;
            }
            
            if (lstKH.size() == 0) {
                JOptionPane.showMessageDialog(this, "Danh sách trống!");
                return;
            } else {
                index = lstKH.size() - 1;
                showDetail(index, lstKH);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nút về cuối!");
            return;
        }
        updateStatus();
    }//GEN-LAST:event_btnLastActionPerformed

    private void tblKhoaHocMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoaHocMousePressed
        if (evt.getClickCount() == 2) {
            index = tblKhoaHoc.getSelectedRow();
            showDetail(index, lstKH);
            
            updateStatus();
        }
    }//GEN-LAST:event_tblKhoaHocMousePressed

    private void txtChuyenDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChuyenDeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChuyenDeActionPerformed

    private void cboChuyenDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChuyenDeActionPerformed
        ChuyenDe cd = (ChuyenDe) cboChuyenDe.getSelectedItem();
        txtThoiLuong.setText(cd.getThoiLuong() + "");
        txtHocPhi.setText(cd.getHocPhi() + "");
        txtChuyenDe.setText(cd.getTenCD());
        txtGhiChu.setText(cd.getTenCD());
        
        loadTable(lstKH);
        
        updateStatus();
    }//GEN-LAST:event_cboChuyenDeActionPerformed

    private void txtChuyenDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtChuyenDeMouseClicked

    }//GEN-LAST:event_txtChuyenDeMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KhoaHoc_JDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhoaHoc_JDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhoaHoc_JDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhoaHoc_JDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KhoaHoc_JDiaLog dialog = new KhoaHoc_JDiaLog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboChuyenDe;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panel1;
    private javax.swing.JTable tblKhoaHoc;
    private javax.swing.JTextField txtChuyenDe;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtKhaiGiang;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables
}
