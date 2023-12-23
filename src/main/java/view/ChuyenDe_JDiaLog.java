/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.ChuyenDe;
import services.ChuyenDe_Services;

/**
 *
 * @author Admin
 */
public class ChuyenDe_JDiaLog extends javax.swing.JDialog {

    DefaultTableModel tblModel = new DefaultTableModel();
    int index = -1;
    ChuyenDe_Services ql = new ChuyenDe_Services();
    List<ChuyenDe> lstCD = new ArrayList<>();

    /**
     * Creates new form ChuyenDe_JDiaLog
     */
    public ChuyenDe_JDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();

    }

    private void init() {
        setLocationRelativeTo(null);
        lstCD = ql.getAllData();
        this.loadTable(lstCD);
        updateStatus();
    }

    private void loadTable(List<ChuyenDe> lst) {
        tblModel.setRowCount(0);
        tblModel = (DefaultTableModel) tblChuyenDe.getModel();
        for (ChuyenDe cd : lst) {
            tblModel.addRow(new Object[]{
                cd.getMaCD(),
                cd.getTenCD(),
                cd.getHocPhi(),
                cd.getThoiLuong(),
                cd.getHinh()
            });
        }
    }

    private void showDetail(int index) {
        ChuyenDe cd = lstCD.get(index);
        txtMaCD.setText(cd.getMaCD());
        txtTenCD.setText(cd.getTenCD());
        txtThoiLuong.setText(cd.getThoiLuong() + "");
        txtHocPhi.setText(cd.getHocPhi() + "");
        txtMoTa.setText(cd.getMoTa());
        urlAnh = cd.getHinh();
        updateHinh(urlAnh);

        tblChuyenDe.setRowSelectionInterval(index, index);
    }

    private void clearForm() {
        txtMaCD.setText("");
        txtTenCD.setText("");
        txtThoiLuong.setText("");
        txtHocPhi.setText("");
        txtMoTa.setText("");
        tblChuyenDe.clearSelection();
        index = -1;
    }

    private ChuyenDe readForm() {
        ChuyenDe cd = new ChuyenDe();
        cd.setMaCD(txtMaCD.getText());
        cd.setTenCD(txtTenCD.getText());
        cd.setThoiLuong(Integer.parseInt(txtThoiLuong.getText()));
        cd.setHocPhi(Double.parseDouble(txtHocPhi.getText()));
        cd.setMoTa(txtMoTa.getText());
        cd.setHinh(urlAnh);
        return cd;
    }

    private void updateStatus() {
        boolean edit = this.index >= 0;
        boolean first = this.index == 0;
        boolean last = this.index == tblChuyenDe.getRowCount() - 1;
        txtMaCD.setEditable(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnLast.setEnabled(edit && !last);
        btnNext.setEnabled(edit && !last);
    }

    void updateHinh(String url) {
        ImageIcon img = new ImageIcon("src\\main\\java\\icon\\" + url);
        Image im = img.getImage();
        ImageIcon icon = new ImageIcon(im.getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), im.SCALE_SMOOTH));
        lblHinh.setText("");
        lblHinh.setIcon(icon);
    }

    private boolean checkForm() {
        if (txtMaCD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã chuyên đề");
            return false;
        }
        if (txtTenCD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên chuyên đề");
            return false;
        }
        if (txtThoiLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thời lượng");
            return false;
        }
        if (txtHocPhi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập học phí");
            return false;
        }
        if (txtMoTa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "VUi lòng nhập mô tả");
            return false;
        }
        if (lblHinh.getIcon() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hình");
            return false;
        }
        try {
            Double hocPhi = Double.parseDouble(txtHocPhi.getText());
            if (hocPhi < 0 || hocPhi > Double.MAX_VALUE) {
                JOptionPane.showMessageDialog(this, "Học phí phải lớn hơn 0 và không quá phạm vi");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Học phí là 1 số thực");
            return false;
        }
        
        try {
            int thoiLuong = Integer.parseInt(txtThoiLuong.getText());
            if (thoiLuong < 0 || thoiLuong > Integer.MAX_VALUE) {
                JOptionPane.showMessageDialog(this, "Thời lượng phải lớn hơn 0 và không quá phạm vi");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thời lượng là 1 số nguyên dương");
            return false;
        }
        
        
        

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaCD = new javax.swing.JTextField();
        txtTenCD = new javax.swing.JTextField();
        txtThoiLuong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtHocPhi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChuyenDe = new javax.swing.JTable();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EduSys - Quản lý chuyên đề");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("QUẢN LÝ CHUYÊN ĐỀ");

        jLabel2.setText("Hình logo");

        lblHinh.setText(" ");
        lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        jLabel4.setText("Mã chuyên đề");

        txtMaCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaCDActionPerformed(evt);
            }
        });

        jLabel5.setText("Tên chuyên đề");

        jLabel6.setText("Thời lượng (giờ)");

        jLabel7.setText("Học phí");

        jLabel8.setText("Mô tả chuyên đề");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

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

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel2)
                                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(87, 87, 87))
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(243, 243, 243)
                            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTenCD, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(txtMaCD, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        panel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnMoi, btnSua, btnThem, btnXoa});

        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(txtMaCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        panel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnMoi, btnSua, btnThem, btnXoa});

        jScrollPane1.setBorder(null);

        tblChuyenDe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MA CD", "TÊN CD", "HỌC PHÍ", "THỜI LƯỢNG", "HÌNH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChuyenDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblChuyenDeMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblChuyenDe);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(btnFirst)
                .addGap(18, 18, 18)
                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnFirst, btnLast, btnNext, btnPrev});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnFirst, btnLast, btnNext, btnPrev});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaCDActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        updateStatus();
        if (lstCD.size() == 0) {

            JOptionPane.showMessageDialog(this, "Danh sách trống!");
            return;
        }
        if (txtMaCD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã chuyên đề muốn xóa");
            return;
        }
        int hoi = JOptionPane.showConfirmDialog(this, "Xác nhận xóa?\n\n<html><font color='red'><b>Lưu ý: </b>Dữ liệu đã xóa sẽ không thể khôi phục.</font></html>", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
        if (hoi == JOptionPane.YES_OPTION) {

            if (ql.getMaCDInKhoaHoc(txtMaCD.getText()) != null) {
                JOptionPane.showMessageDialog(this, "Xóa thất bại\nCó thể chuyên đề này đang thuộc 1 khóa học\n\n<html><font color='red'><b>Lưu ý: </b>Hãy chắc chắn rằng chuyên đề này không thuộc 1 khóa học nào</font></html>");
                return;
            }

            if (ql.getTheoMa(txtMaCD.getText()) != null) {
                if (ql.delete(txtMaCD.getText()) != 0) {
                    lstCD.clear();
                    lstCD = ql.getAllData();
                    JOptionPane.showMessageDialog(this, "<html><font color='green'>Xóa thành công.</font></html>");
                    clearForm();
                    loadTable(lstCD);
                    index = -1;
                    return;

                }
                if (ql.delete(txtMaCD.getText()) == 0) {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mã chuyên đề không tồn tại");
                return;
            }

            String ma = tblChuyenDe.getValueAt(index, 0).toString();
            if (ql.delete(ma) != 0) {
                lstCD.clear();
                lstCD = ql.getAllData();
                JOptionPane.showMessageDialog(this, "<html><font color='green'>Xóa thành công.</font></html>");
                clearForm();
                loadTable(lstCD);
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
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblChuyenDeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChuyenDeMousePressed
        if (evt.getClickCount() == 2) {
            index = tblChuyenDe.getSelectedRow();
            showDetail(index);
            
            updateStatus();
        }
    }//GEN-LAST:event_tblChuyenDeMousePressed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        try {

            if (index == 0) {

                return;
            }

            if (lstCD.size() == 0) {

                JOptionPane.showMessageDialog(this, "Danh sách trống!");
                return;
            } else {
                index = 0;
                showDetail(index);

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
            if (lstCD.size() == 0) {
                JOptionPane.showMessageDialog(this, "Danh sách trống!");
                return;
            } else {
                if (index > 0) {
                    index--;
                    showDetail(index);

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
            if (lstCD.size() == 0) {
                JOptionPane.showMessageDialog(this, "Danh sách trống!");
                return;
            }
            if (index < lstCD.size() - 1) {
                index++;

            } else {
                return;
            }

            showDetail(index);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nút tiến!");
            return;
        }
        updateStatus();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        try {

            if (index == lstCD.size() - 1 && lstCD.size() != 0) {

                return;
            }

            if (lstCD.size() == 0) {
                JOptionPane.showMessageDialog(this, "Danh sách trống!");
                return;
            } else {
                index = lstCD.size() - 1;
                showDetail(index);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nút về cuối!");
            return;
        }
        updateStatus();
    }//GEN-LAST:event_btnLastActionPerformed

    private String anhChuyenDe = "";
    private String urlAnh = null;

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        ImageIcon img = new ImageIcon();
        anhChuyenDe = choseFile(img, lblHinh);
    }//GEN-LAST:event_lblHinhMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        updateStatus();
        if (checkForm()) {
            int hoi = JOptionPane.showConfirmDialog(this, "Xác nhận thêm mới chuyên đề?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
            if (hoi == JOptionPane.YES_OPTION) {
                ChuyenDe cd = this.readForm();
                if (ql.checkTrung(cd.getMaCD())) {
                    if (ql.insert(cd) != 0) {
                        JOptionPane.showMessageDialog(this, "<html><font color='green'>Thêm thành công.</font></html>");
                        lstCD.clear();
                        lstCD = ql.getAllData();
                        loadTable(lstCD);
                    } else {
                        JOptionPane.showMessageDialog(this, "<html><font color='green'>Thêm thất bại.</font></html>");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Trùng mã chuyên đề");
                    return;
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        updateStatus();
        if (checkForm()) {
            int hoi = JOptionPane.showConfirmDialog(this, "Xác nhận cập nhật?\n\nLưu ý: Dữ liệu liên quan cũng sẽ được cập nhật", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
            if (hoi == JOptionPane.YES_OPTION) {
                ChuyenDe cd = this.readForm();
                String ma = tblChuyenDe.getValueAt(index, 0).toString();
                if (ql.update(cd, ma) != 0) {
                    JOptionPane.showMessageDialog(this, "<html><font color='green'>Cập nhật thành công.</font></html>");
                    lstCD.clear();
                    lstCD = ql.getAllData();
                    loadTable(lstCD);

                } else {
                    JOptionPane.showMessageDialog(this, "<html><font color='red'>Cập nhật thất bại.</font></html>");
                }
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private String choseFile(ImageIcon img, JLabel loadAnh) {
        try {
            // Tạo một đối tượng JFileChooser
            JFileChooser jfc = new JFileChooser("src\\main\\java\\icon");
            // Tạo một bộ lọc tệp chỉ cho phép chọn các tệp hình ảnh JPEG
            FileFilter imageFilter = new FileNameExtensionFilter(
                    "JPEG Image", ImageIO.getReaderFileSuffixes());
            jfc.addChoosableFileFilter(imageFilter);

            // Hiển thị hộp thoại chọn tệp
            jfc.showOpenDialog(null);

            // Lấy tệp đã chọn từ hộp thoại
            File file = jfc.getSelectedFile();

            // Lấy đường dẫn của tệp đã chọn
            String fileName = file.getAbsolutePath();

            // Tạo một đối tượng ImageIcon từ tệp đã chọn, và điều chỉnh kích thước hình ảnh
            img = new ImageIcon(new ImageIcon(fileName).getImage().getScaledInstance(lblHinh.getWidth(), lblHinh.getHeight(), Image.SCALE_SMOOTH));

            //Lấy tên ảnh
            urlAnh = jfc.getSelectedFile().getName();

            loadAnh.setText("");

            // Đặt hình ảnh đã chọn vào nhãn lblHinh để hiển thị trên giao diện
            loadAnh.setIcon(img);

            return fileName;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Bạn đã hủy chọn ảnh");

            // Trả về thông báo lỗi
            return e.getMessage();
        }
    }

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
            java.util.logging.Logger.getLogger(ChuyenDe_JDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChuyenDe_JDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChuyenDe_JDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChuyenDe_JDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChuyenDe_JDiaLog dialog = new ChuyenDe_JDiaLog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JPanel panel1;
    private javax.swing.JTable tblChuyenDe;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtMaCD;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenCD;
    private javax.swing.JTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables
}
