/*
 * Created by JFormDesigner on Thu May 19 20:35:16 CST 2022
 */

package com.juliy.view;

import com.juliy.controller.ButtonMonitor;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Objects;

/**
 * @author JuLiy
 */
public class ManagerMainView extends JFrame {

    public ManagerMainView() {
        initComponents();
        addActionListener();
        setComponentsName();
        setVisible(true);
    }

    //========== 其他 ==========

    /** 给需要监听的控件添加对应自定义监听器 */
    private void addActionListener() {
        //监听点击事件
        btn_logout.addActionListener(ButtonMonitor.btnListener);

        btn_stuSearch.addActionListener(ButtonMonitor.btnListener);
        btn_stuShowAll.addActionListener(ButtonMonitor.btnListener);
        btn_stuAdd.addActionListener(ButtonMonitor.btnListener);

        btn_tchSearch.addActionListener(ButtonMonitor.btnListener);
        btn_tchShowAll.addActionListener(ButtonMonitor.btnListener);
        btn_tchAdd.addActionListener(ButtonMonitor.btnListener);

        btn_courseSearch.addActionListener(ButtonMonitor.btnListener);
        btn_courseShowAll.addActionListener(ButtonMonitor.btnListener);
        btn_courseAdd.addActionListener(ButtonMonitor.btnListener);
    }

    /** 给需要监听的控件设置name，以便识别 */
    private void setComponentsName() {
        btn_logout.setName("mgr_logout");

        btn_stuSearch.setName("mgr_stuSearch");
        btn_stuShowAll.setName("mgr_stuShowAll");
        btn_stuAdd.setName("mgr_stuAdd");

        btn_tchSearch.setName("mgr_tchSearch");
        btn_tchShowAll.setName("mgr_tchShowAll");
        btn_tchAdd.setName("mgr_tchAdd");

        btn_courseSearch.setName("mgr_courseSearch");
        btn_courseShowAll.setName("mgr_courseShowAll");
        btn_courseAdd.setName("mgr_courseAdd");
    }

    /** 设置默认表格格式 */
    private void tableSet(JTable table, AbstractTableModel model, int[] columnWidth) {
        //设置表格内容
        table.setModel(model);
        //设置选中行颜色
        table.setSelectionBackground(new Color(0x78B2B1));
        //让表格不可更改列的位置
        table.getTableHeader().setReorderingAllowed(false);
        //让表格不可更改列的宽度
        table.getTableHeader().setResizingAllowed(false);
        //让表格不会自动设置列宽，以实现横向滚动条
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //自定义渲染器
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                //非按钮列设置为隔行不同颜色
                if (!"修改".equals(value) && !"删除".equals(value)) {
                    if (row % 2 == 0)
                        setBackground(Color.WHITE); //设置奇数行底色
                    else if (row % 2 == 1)
                        setBackground(new Color(0xDDE9F7));    //设置偶数行底色
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
                //按钮列显示按钮图标
                else {
                    JButton button = new JButton();
                    button.setText(String.valueOf(value));
                    return button;
                }
            }
        };
        //单元格内容设置居中
        tcr.setHorizontalAlignment(JLabel.CENTER);

        //自定义编辑器
        class MyButtonEditor extends DefaultCellEditor {
            private final JButton button = new JButton();

            public MyButtonEditor(String columnName, int count) {
                //父类无默认构造方法，需定义一个，但用不到
                super(new JTextField());
                // 设置点击1次激活编辑
                setClickCountToStart(1);
                //为按钮添加事件,只能添加ActionListener事件
                button.addActionListener(ButtonMonitor.btnListener);
                //依据列数设置name
                if (count == 19) {  //19表示是学生管理页面
                    if ("修改".equals(columnName)) {
                        button.setName("mgr_stuChange");
                    } else if ("删除".equals(columnName)) {
                        button.setName("mgr_stuDelete");
                    }
                } else if (count == 15) {   //15表示是教师管理页面
                    if ("修改".equals(columnName)) {
                        button.setName("mgr_tchChange");
                    } else if ("删除".equals(columnName)) {
                        button.setName("mgr_tchDelete");
                    }
                } else if (count == 10) {   //10表示是课程管理页面
                    if ("修改".equals(columnName)) {
                        button.setName("mgr_courseChange");
                    } else if ("删除".equals(columnName)) {
                        button.setName("mgr_courseDelete");
                    }
                }
            }

            //重写父类的编辑方法，即点击后会发生什么变化，需设置button内容并返回
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                //设置按钮文字，不加空格的话按下后文字会偏右，不知道为什么
                button.setText(value + "    ");
                return button;
            }
        }

        //设置表格格式
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            //设置每列宽度
            column.setPreferredWidth(columnWidth[i]);
            //添加渲染器
            column.setCellRenderer(tcr);
            //按钮列添加编辑器
            if (column.getHeaderValue().equals("修改") || column.getHeaderValue().equals("删除")) {
                column.setCellEditor(new MyButtonEditor(column.getHeaderValue().toString(), columnModel.getColumnCount()));
            }
        }
    }

    //========== 学生管理页面 ==========

    /** 设置学生信息表格 */
    public void setTable_stuInfo(AbstractTableModel model) {
        //表头宽度
        int[] columnWidth = {130, 80, 50, 50, 200, 120, 120, 120, 120, 120, 150, 80, 180, 300, 150, 90, 90, 70, 70};
        //设置表格3
        tableSet(table_stuInfo, model, columnWidth);
        //设置表格禁止选中行，否则点击按钮会被响应为选中行
        table_stuInfo.setRowSelectionAllowed(false);
    }

    /** 初始化学生管理页面下拉框内容 */
    public void setStuCbBox(DefaultComboBoxModel<String> collegeModel, DefaultComboBoxModel<String> majorModel, DefaultComboBoxModel<String> statusModel) {
        cbBox_stuCollege.setModel(collegeModel);
        cbBox_stuMajor.setModel(majorModel);
        cbBox_stuStatus.setModel(statusModel);
    }

    /** 获取学生信息表格选中学生的学号 */
    public String getSelectedStuNumber() {
        int row = table_stuInfo.getSelectedRow();
        return table_stuInfo.getModel().getValueAt(row, 0).toString();
    }

    //========== 教师管理页面 ==========

    /** 设置教师信息表格 */
    public void setTable_tchInfo(AbstractTableModel model) {
        //表头宽度
        int[] columnWidth = {130, 80, 50, 50, 200, 120, 120, 120, 150, 80, 180, 300, 150, 70, 70};
        //设置表格3
        tableSet(table_tchInfo, model, columnWidth);
        //设置表格禁止选中行，否则点击按钮会被响应为选中行
        table_tchInfo.setRowSelectionAllowed(false);
    }

    /** 初始化教师管理页面下拉框内容 */
    public void setTchCbBox(DefaultComboBoxModel<String> collegeModel, DefaultComboBoxModel<String> jobModel, DefaultComboBoxModel<String> statusModel) {
        cbBox_tchCollege.setModel(collegeModel);
        cbBox_tchJob.setModel(jobModel);
        cbBox_tchStatus.setModel(statusModel);
    }

    /** 获取教师信息表格选中教师的工号 */
    public String getSelectedTchNumber() {
        int row = table_tchInfo.getSelectedRow();
        return table_tchInfo.getModel().getValueAt(row, 0).toString();
    }

    //========== 课程管理页面 ==========

    /** 设置课程信息表格 */
    public void setTable_courseInfo(AbstractTableModel model) {
        //表头宽度
        int[] columnWidth = {70, 150, 100, 150, 150, 80, 80, 80, 70, 70};
        //设置表格3
        tableSet(table_courseInfo, model, columnWidth);
        //设置表格禁止选中行，否则点击按钮会被响应为选中行
        table_courseInfo.setRowSelectionAllowed(false);
    }

    /** 初始化课程管理页面下拉框内容 */
    public void setCourseCbBox(DefaultComboBoxModel<String> collegeModel, DefaultComboBoxModel<String> typeModel) {
        cbBox_courseCollege.setModel(collegeModel);
        cbBox_courseType.setModel(typeModel);
    }

    /** 获取课程信息表格选中课程的编号 */
    public String getSelectedCourseNumber() {
        int row = table_courseInfo.getSelectedRow();
        return table_courseInfo.getModel().getValueAt(row, 0).toString();
    }

    //========== 各种get、set方法 ==========

    public void setLabel_time(String time) {
        label_time.setText(time);
    }

    public String getField_stuNumber() {
        return field_stuNumber.getText();
    }

    public String getField_stuName() {
        return field_stuName.getText();
    }

    public String getCbBox_stuCollege() {
        return Objects.requireNonNull(cbBox_stuCollege.getSelectedItem()).toString();
    }

    public String getCbBox_stuMajor() {
        return Objects.requireNonNull(cbBox_stuMajor.getSelectedItem()).toString();
    }

    public String getCbBox_stuStatus() {
        return Objects.requireNonNull(cbBox_stuStatus.getSelectedItem()).toString();
    }

    public String getField_tchNumber() {
        return field_tchNumber.getText();
    }

    public String getField_tchName() {
        return field_tchName.getText();
    }

    public String getCbBox_tchCollege() {
        return Objects.requireNonNull(cbBox_tchCollege.getSelectedItem()).toString();
    }

    public String getCbBox_tchJob() {
        return Objects.requireNonNull(cbBox_tchJob.getSelectedItem()).toString();
    }

    public String getCbBox_tchStatus() {
        return Objects.requireNonNull(cbBox_tchStatus.getSelectedItem()).toString();
    }

    public String getCbBox_courseCollege() {
        return Objects.requireNonNull(cbBox_courseCollege.getSelectedItem()).toString();
    }

    public String getCbBox_courseType() {
        return Objects.requireNonNull(cbBox_courseType.getSelectedItem()).toString();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        panel_title = new JPanel();
        label_title = new JLabel();
        panel3 = new JPanel();
        label_welcome = new JLabel();
        label_time = new JLabel();
        btn_logout = new JButton();
        tabbedPane_main = new JTabbedPane();
        panel_stuManagePage = new JPanel();
        label2 = new JLabel();
        field_stuNumber = new JTextField();
        label3 = new JLabel();
        field_stuName = new JTextField();
        label4 = new JLabel();
        cbBox_stuCollege = new JComboBox();
        label5 = new JLabel();
        cbBox_stuMajor = new JComboBox();
        label6 = new JLabel();
        cbBox_stuStatus = new JComboBox();
        btn_stuSearch = new JButton();
        btn_stuShowAll = new JButton();
        scrollPane1 = new JScrollPane();
        table_stuInfo = new JTable();
        btn_stuAdd = new JButton();
        panel_tchManagePage = new JPanel();
        label7 = new JLabel();
        field_tchNumber = new JTextField();
        label12 = new JLabel();
        field_tchName = new JTextField();
        label13 = new JLabel();
        cbBox_tchCollege = new JComboBox();
        label16 = new JLabel();
        cbBox_tchJob = new JComboBox();
        label17 = new JLabel();
        cbBox_tchStatus = new JComboBox();
        btn_tchSearch = new JButton();
        btn_tchShowAll = new JButton();
        scrollPane2 = new JScrollPane();
        table_tchInfo = new JTable();
        btn_tchAdd = new JButton();
        panel_courseManagePage = new JPanel();
        label21 = new JLabel();
        cbBox_courseCollege = new JComboBox();
        label22 = new JLabel();
        cbBox_courseType = new JComboBox();
        btn_courseSearch = new JButton();
        btn_courseShowAll = new JButton();
        scrollPane3 = new JScrollPane();
        table_courseInfo = new JTable();
        btn_courseAdd = new JButton();
        panel_systemInfoPage = new JPanel();
        label1 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();

        //======== this ========
        setTitle("\u7ba1\u7406\u5458\u754c\u9762");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 600));
        setResizable(false);
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setLayout(new GridBagLayout());
            ((GridBagLayout) panel1.getLayout()).columnWidths = new int[]{900, 0};
            ((GridBagLayout) panel1.getLayout()).rowHeights = new int[]{91, 25, 440, 0};
            ((GridBagLayout) panel1.getLayout()).columnWeights = new double[]{1.0, 1.0E-4};
            ((GridBagLayout) panel1.getLayout()).rowWeights = new double[]{1.0, 1.0, 1.0, 1.0E-4};

            //======== panel_title ========
            {
                panel_title.setBackground(new Color(216, 216, 216));

                //---- label_title ----
                label_title.setText("\u5b66\u751f\u4fe1\u606f\u7ba1\u7406\u7cfb\u7edf");
                label_title.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 48));
                label_title.setForeground(Color.black);

                GroupLayout panel_titleLayout = new GroupLayout(panel_title);
                panel_title.setLayout(panel_titleLayout);
                panel_titleLayout.setHorizontalGroup(
                        panel_titleLayout.createParallelGroup()
                                .addGroup(panel_titleLayout.createSequentialGroup()
                                                  .addGap(251, 251, 251)
                                                  .addComponent(label_title, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
                                                  .addContainerGap(254, Short.MAX_VALUE))
                );
                panel_titleLayout.setVerticalGroup(
                        panel_titleLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, panel_titleLayout.createSequentialGroup()
                                        .addComponent(label_title, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                        .addContainerGap())
                );
            }
            panel1.add(panel_title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                                                           GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                           new Insets(0, 0, 0, 0), 0, 0));

            //======== panel3 ========
            {
                panel3.setPreferredSize(new Dimension(896, 25));
                panel3.setBackground(new Color(204, 204, 204));
                panel3.setMinimumSize(new Dimension(824, 25));

                //---- label_welcome ----
                label_welcome.setText("\u7cfb\u7edf\u7ba1\u7406\u5458\uff0c\u60a8\u597d\uff0c\u6b22\u8fce\u4f7f\u7528\u672c\u7cfb\u7edf");
                label_welcome.setPreferredSize(new Dimension(271, 25));

                //---- label_time ----
                label_time.setText("\u5f53\u524d\u65f6\u95f4\uff1ayyyy-MM-dd-EEEE");
                label_time.setPreferredSize(new Dimension(255, 25));

                //---- btn_logout ----
                btn_logout.setBackground(new Color(204, 204, 204));
                btn_logout.setText("\u6ce8\u9500");

                GroupLayout panel3Layout = new GroupLayout(panel3);
                panel3.setLayout(panel3Layout);
                panel3Layout.setHorizontalGroup(
                        panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                                  .addComponent(label_welcome, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE)
                                                  .addGap(98, 98, 98)
                                                  .addComponent(label_time, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(btn_logout, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                  .addContainerGap(17, Short.MAX_VALUE))
                );
                panel3Layout.setVerticalGroup(
                        panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(panel3Layout.createSequentialGroup()
                                                  .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(btn_logout, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(label_welcome, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(label_time, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                                  .addContainerGap())
                );
            }
            panel1.add(panel3, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                                                      GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                      new Insets(0, 0, 0, 0), 0, 0));

            //======== tabbedPane_main ========
            {
                tabbedPane_main.setFont(new Font("\u9ed1\u4f53", Font.BOLD, 16));
                tabbedPane_main.setForeground(new Color(0, 51, 153));

                //======== panel_stuManagePage ========
                {
                    panel_stuManagePage.setLayout(new GridBagLayout());
                    ((GridBagLayout) panel_stuManagePage.getLayout()).columnWidths = new int[]{5, 30, 100, 5, 30, 0, 5, 30, 0, 5, 0, 0, 5, 60, 90, 15, 70, 15, 80, 0, 0};
                    ((GridBagLayout) panel_stuManagePage.getLayout()).rowHeights = new int[]{5, 25, 5, 347, 5, 25, 5, 0};
                    ((GridBagLayout) panel_stuManagePage.getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0E-4};
                    ((GridBagLayout) panel_stuManagePage.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0E-4};

                    //---- label2 ----
                    label2.setText("\u5b66\u53f7");
                    label2.setPreferredSize(new Dimension(30, 25));
                    label2.setHorizontalAlignment(SwingConstants.CENTER);
                    label2.setMaximumSize(new Dimension(30, 25));
                    label2.setMinimumSize(new Dimension(30, 25));
                    panel_stuManagePage.add(label2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                                                                           GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                           new Insets(0, 0, 0, 0), 0, 0));

                    //---- field_stuNumber ----
                    field_stuNumber.setMinimumSize(new Dimension(110, 25));
                    field_stuNumber.setPreferredSize(new Dimension(110, 25));
                    field_stuNumber.setMaximumSize(new Dimension(110, 25));
                    field_stuNumber.setToolTipText("\u652f\u6301\u6a21\u7cca\u641c\u7d22");
                    panel_stuManagePage.add(field_stuNumber, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                                                                                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                    new Insets(0, 0, 0, 0), 0, 0));

                    //---- label3 ----
                    label3.setText("\u59d3\u540d");
                    label3.setPreferredSize(new Dimension(30, 25));
                    label3.setHorizontalAlignment(SwingConstants.CENTER);
                    label3.setMinimumSize(new Dimension(30, 25));
                    panel_stuManagePage.add(label3, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
                                                                           GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                           new Insets(0, 0, 0, 0), 0, 0));

                    //---- field_stuName ----
                    field_stuName.setMinimumSize(new Dimension(70, 25));
                    field_stuName.setPreferredSize(new Dimension(70, 25));
                    field_stuName.setToolTipText("\u652f\u6301\u6a21\u7cca\u641c\u7d22");
                    panel_stuManagePage.add(field_stuName, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,
                                                                                  GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                  new Insets(0, 0, 0, 0), 0, 0));

                    //---- label4 ----
                    label4.setText("\u5b66\u9662");
                    label4.setPreferredSize(new Dimension(30, 25));
                    label4.setHorizontalAlignment(SwingConstants.CENTER);
                    label4.setMinimumSize(new Dimension(30, 25));
                    panel_stuManagePage.add(label4, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0,
                                                                           GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                           new Insets(0, 0, 0, 0), 0, 0));

                    //---- cbBox_stuCollege ----
                    cbBox_stuCollege.setPreferredSize(new Dimension(110, 25));
                    cbBox_stuCollege.setMinimumSize(new Dimension(110, 25));
                    panel_stuManagePage.add(cbBox_stuCollege, new GridBagConstraints(8, 1, 1, 1, 0.0, 0.0,
                                                                                     GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                     new Insets(0, 0, 0, 0), 0, 0));

                    //---- label5 ----
                    label5.setText("\u4e13\u4e1a");
                    label5.setPreferredSize(new Dimension(30, 25));
                    label5.setHorizontalAlignment(SwingConstants.CENTER);
                    label5.setMinimumSize(new Dimension(30, 25));
                    panel_stuManagePage.add(label5, new GridBagConstraints(10, 1, 1, 1, 0.0, 0.0,
                                                                           GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                           new Insets(0, 0, 0, 0), 0, 0));

                    //---- cbBox_stuMajor ----
                    cbBox_stuMajor.setPreferredSize(new Dimension(110, 25));
                    cbBox_stuMajor.setMinimumSize(new Dimension(110, 25));
                    panel_stuManagePage.add(cbBox_stuMajor, new GridBagConstraints(11, 1, 1, 1, 0.0, 0.0,
                                                                                   GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                   new Insets(0, 0, 0, 0), 0, 0));

                    //---- label6 ----
                    label6.setText("\u653f\u6cbb\u9762\u8c8c");
                    label6.setPreferredSize(new Dimension(60, 25));
                    label6.setHorizontalAlignment(SwingConstants.CENTER);
                    label6.setMinimumSize(new Dimension(60, 25));
                    panel_stuManagePage.add(label6, new GridBagConstraints(13, 1, 1, 1, 0.0, 0.0,
                                                                           GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                           new Insets(0, 0, 0, 0), 0, 0));

                    //---- cbBox_stuStatus ----
                    cbBox_stuStatus.setPreferredSize(new Dimension(90, 25));
                    cbBox_stuStatus.setMinimumSize(new Dimension(90, 25));
                    panel_stuManagePage.add(cbBox_stuStatus, new GridBagConstraints(14, 1, 1, 1, 0.0, 0.0,
                                                                                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                    new Insets(0, 0, 0, 0), 0, 0));

                    //---- btn_stuSearch ----
                    btn_stuSearch.setText("\u641c\u7d22");
                    btn_stuSearch.setPreferredSize(new Dimension(70, 25));
                    btn_stuSearch.setMinimumSize(new Dimension(70, 25));
                    panel_stuManagePage.add(btn_stuSearch, new GridBagConstraints(16, 1, 1, 1, 0.0, 0.0,
                                                                                  GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                  new Insets(0, 0, 0, 0), 0, 0));

                    //---- btn_stuShowAll ----
                    btn_stuShowAll.setText("\u663e\u793a\u6240\u6709");
                    btn_stuShowAll.setPreferredSize(new Dimension(80, 25));
                    btn_stuShowAll.setMinimumSize(new Dimension(80, 25));
                    panel_stuManagePage.add(btn_stuShowAll, new GridBagConstraints(18, 1, 1, 1, 0.0, 0.0,
                                                                                   GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                   new Insets(0, 0, 0, 0), 0, 0));

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(table_stuInfo);
                    }
                    panel_stuManagePage.add(scrollPane1, new GridBagConstraints(0, 3, 20, 1, 0.0, 0.0,
                                                                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                new Insets(0, 0, 0, 0), 0, 0));

                    //---- btn_stuAdd ----
                    btn_stuAdd.setText("\u6dfb\u52a0\u65b0\u5b66\u751f");
                    btn_stuAdd.setPreferredSize(new Dimension(70, 25));
                    btn_stuAdd.setMinimumSize(new Dimension(70, 25));
                    btn_stuAdd.setMaximumSize(new Dimension(70, 25));
                    panel_stuManagePage.add(btn_stuAdd, new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0,
                                                                               GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                               new Insets(0, 0, 0, 0), 0, 0));
                }
                tabbedPane_main.addTab("\u5b66\u751f\u7ba1\u7406", panel_stuManagePage);

                //======== panel_tchManagePage ========
                {
                    panel_tchManagePage.setLayout(new GridBagLayout());
                    ((GridBagLayout) panel_tchManagePage.getLayout()).columnWidths = new int[]{5, 30, 100, 5, 30, 0, 5, 30, 0, 5, 0, 0, 5, 60, 90, 15, 70, 15, 80, 0, 0};
                    ((GridBagLayout) panel_tchManagePage.getLayout()).rowHeights = new int[]{5, 25, 5, 347, 5, 25, 5, 0};
                    ((GridBagLayout) panel_tchManagePage.getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0E-4};
                    ((GridBagLayout) panel_tchManagePage.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0E-4};

                    //---- label7 ----
                    label7.setText("\u5de5\u53f7");
                    label7.setPreferredSize(new Dimension(30, 25));
                    label7.setHorizontalAlignment(SwingConstants.CENTER);
                    label7.setMaximumSize(new Dimension(30, 25));
                    label7.setMinimumSize(new Dimension(30, 25));
                    panel_tchManagePage.add(label7, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                                                                           GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                           new Insets(0, 0, 0, 0), 0, 0));

                    //---- field_tchNumber ----
                    field_tchNumber.setMinimumSize(new Dimension(110, 25));
                    field_tchNumber.setPreferredSize(new Dimension(110, 25));
                    field_tchNumber.setMaximumSize(new Dimension(110, 25));
                    field_tchNumber.setToolTipText("\u652f\u6301\u6a21\u7cca\u641c\u7d22");
                    panel_tchManagePage.add(field_tchNumber, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                                                                                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                    new Insets(0, 0, 0, 0), 0, 0));

                    //---- label12 ----
                    label12.setText("\u59d3\u540d");
                    label12.setPreferredSize(new Dimension(30, 25));
                    label12.setHorizontalAlignment(SwingConstants.CENTER);
                    label12.setMinimumSize(new Dimension(30, 25));
                    panel_tchManagePage.add(label12, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
                                                                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                            new Insets(0, 0, 0, 0), 0, 0));

                    //---- field_tchName ----
                    field_tchName.setMinimumSize(new Dimension(70, 25));
                    field_tchName.setPreferredSize(new Dimension(70, 25));
                    field_tchName.setToolTipText("\u652f\u6301\u6a21\u7cca\u641c\u7d22");
                    panel_tchManagePage.add(field_tchName, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,
                                                                                  GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                  new Insets(0, 0, 0, 0), 0, 0));

                    //---- label13 ----
                    label13.setText("\u5b66\u9662");
                    label13.setPreferredSize(new Dimension(30, 25));
                    label13.setHorizontalAlignment(SwingConstants.CENTER);
                    label13.setMinimumSize(new Dimension(30, 25));
                    panel_tchManagePage.add(label13, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0,
                                                                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                            new Insets(0, 0, 0, 0), 0, 0));

                    //---- cbBox_tchCollege ----
                    cbBox_tchCollege.setPreferredSize(new Dimension(110, 25));
                    cbBox_tchCollege.setMinimumSize(new Dimension(110, 25));
                    panel_tchManagePage.add(cbBox_tchCollege, new GridBagConstraints(8, 1, 1, 1, 0.0, 0.0,
                                                                                     GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                     new Insets(0, 0, 0, 0), 0, 0));

                    //---- label16 ----
                    label16.setText("\u804c\u79f0");
                    label16.setPreferredSize(new Dimension(30, 25));
                    label16.setHorizontalAlignment(SwingConstants.CENTER);
                    label16.setMinimumSize(new Dimension(30, 25));
                    panel_tchManagePage.add(label16, new GridBagConstraints(10, 1, 1, 1, 0.0, 0.0,
                                                                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                            new Insets(0, 0, 0, 0), 0, 0));

                    //---- cbBox_tchJob ----
                    cbBox_tchJob.setPreferredSize(new Dimension(110, 25));
                    cbBox_tchJob.setMinimumSize(new Dimension(110, 25));
                    panel_tchManagePage.add(cbBox_tchJob, new GridBagConstraints(11, 1, 1, 1, 0.0, 0.0,
                                                                                 GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                 new Insets(0, 0, 0, 0), 0, 0));

                    //---- label17 ----
                    label17.setText("\u653f\u6cbb\u9762\u8c8c");
                    label17.setPreferredSize(new Dimension(60, 25));
                    label17.setHorizontalAlignment(SwingConstants.CENTER);
                    label17.setMinimumSize(new Dimension(60, 25));
                    panel_tchManagePage.add(label17, new GridBagConstraints(13, 1, 1, 1, 0.0, 0.0,
                                                                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                            new Insets(0, 0, 0, 0), 0, 0));

                    //---- cbBox_tchStatus ----
                    cbBox_tchStatus.setPreferredSize(new Dimension(90, 25));
                    cbBox_tchStatus.setMinimumSize(new Dimension(90, 25));
                    panel_tchManagePage.add(cbBox_tchStatus, new GridBagConstraints(14, 1, 1, 1, 0.0, 0.0,
                                                                                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                    new Insets(0, 0, 0, 0), 0, 0));

                    //---- btn_tchSearch ----
                    btn_tchSearch.setText("\u641c\u7d22");
                    btn_tchSearch.setPreferredSize(new Dimension(70, 25));
                    btn_tchSearch.setMinimumSize(new Dimension(70, 25));
                    panel_tchManagePage.add(btn_tchSearch, new GridBagConstraints(16, 1, 1, 1, 0.0, 0.0,
                                                                                  GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                  new Insets(0, 0, 0, 0), 0, 0));

                    //---- btn_tchShowAll ----
                    btn_tchShowAll.setText("\u663e\u793a\u6240\u6709");
                    btn_tchShowAll.setPreferredSize(new Dimension(80, 25));
                    btn_tchShowAll.setMinimumSize(new Dimension(80, 25));
                    panel_tchManagePage.add(btn_tchShowAll, new GridBagConstraints(18, 1, 1, 1, 0.0, 0.0,
                                                                                   GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                   new Insets(0, 0, 0, 0), 0, 0));

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setViewportView(table_tchInfo);
                    }
                    panel_tchManagePage.add(scrollPane2, new GridBagConstraints(0, 3, 20, 1, 0.0, 0.0,
                                                                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                new Insets(0, 0, 0, 0), 0, 0));

                    //---- btn_tchAdd ----
                    btn_tchAdd.setText("\u6dfb\u52a0\u65b0\u6559\u5e08");
                    btn_tchAdd.setPreferredSize(new Dimension(70, 25));
                    btn_tchAdd.setMinimumSize(new Dimension(70, 25));
                    btn_tchAdd.setMaximumSize(new Dimension(70, 25));
                    panel_tchManagePage.add(btn_tchAdd, new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0,
                                                                               GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                               new Insets(0, 0, 0, 0), 0, 0));
                }
                tabbedPane_main.addTab("\u6559\u5e08\u7ba1\u7406", panel_tchManagePage);

                //======== panel_courseManagePage ========
                {
                    panel_courseManagePage.setLayout(new GridBagLayout());
                    ((GridBagLayout) panel_courseManagePage.getLayout()).columnWidths = new int[]{5, 60, 100, 5, 60, 0, 15, 70, 15, 80, 0, 0};
                    ((GridBagLayout) panel_courseManagePage.getLayout()).rowHeights = new int[]{5, 25, 5, 347, 5, 25, 5, 0};
                    ((GridBagLayout) panel_courseManagePage.getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0E-4};
                    ((GridBagLayout) panel_courseManagePage.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0E-4};

                    //---- label21 ----
                    label21.setText("\u5f00\u8bfe\u9662\u7cfb");
                    label21.setPreferredSize(new Dimension(60, 25));
                    label21.setHorizontalAlignment(SwingConstants.CENTER);
                    label21.setMinimumSize(new Dimension(60, 25));
                    label21.setMaximumSize(new Dimension(60, 25));
                    panel_courseManagePage.add(label21, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                                                                               GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                               new Insets(0, 0, 0, 0), 0, 0));

                    //---- cbBox_courseCollege ----
                    cbBox_courseCollege.setPreferredSize(new Dimension(110, 25));
                    cbBox_courseCollege.setMinimumSize(new Dimension(110, 25));
                    cbBox_courseCollege.setMaximumSize(new Dimension(110, 25));
                    panel_courseManagePage.add(cbBox_courseCollege, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                                                                                           GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                           new Insets(0, 0, 0, 0), 0, 0));

                    //---- label22 ----
                    label22.setText("\u8bfe\u7a0b\u7c7b\u578b");
                    label22.setPreferredSize(new Dimension(60, 25));
                    label22.setHorizontalAlignment(SwingConstants.CENTER);
                    label22.setMinimumSize(new Dimension(60, 25));
                    label22.setMaximumSize(new Dimension(60, 25));
                    panel_courseManagePage.add(label22, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
                                                                               GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                               new Insets(0, 0, 0, 0), 0, 0));

                    //---- cbBox_courseType ----
                    cbBox_courseType.setPreferredSize(new Dimension(90, 25));
                    cbBox_courseType.setMinimumSize(new Dimension(90, 25));
                    cbBox_courseType.setMaximumSize(new Dimension(90, 25));
                    panel_courseManagePage.add(cbBox_courseType, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0,
                                                                                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                        new Insets(0, 0, 0, 0), 0, 0));

                    //---- btn_courseSearch ----
                    btn_courseSearch.setText("\u641c\u7d22");
                    btn_courseSearch.setPreferredSize(new Dimension(70, 25));
                    btn_courseSearch.setMinimumSize(new Dimension(70, 25));
                    panel_courseManagePage.add(btn_courseSearch, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0,
                                                                                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                        new Insets(0, 0, 0, 0), 0, 0));

                    //---- btn_courseShowAll ----
                    btn_courseShowAll.setText("\u663e\u793a\u6240\u6709");
                    btn_courseShowAll.setPreferredSize(new Dimension(80, 25));
                    btn_courseShowAll.setMinimumSize(new Dimension(80, 25));
                    panel_courseManagePage.add(btn_courseShowAll, new GridBagConstraints(9, 1, 1, 1, 0.0, 0.0,
                                                                                         GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                         new Insets(0, 0, 0, 0), 0, 0));

                    //======== scrollPane3 ========
                    {
                        scrollPane3.setViewportView(table_courseInfo);
                    }
                    panel_courseManagePage.add(scrollPane3, new GridBagConstraints(0, 3, 11, 1, 0.0, 0.0,
                                                                                   GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                   new Insets(0, 0, 0, 0), 0, 0));

                    //---- btn_courseAdd ----
                    btn_courseAdd.setText("\u6dfb\u52a0\u65b0\u8bfe\u7a0b");
                    btn_courseAdd.setPreferredSize(new Dimension(70, 25));
                    btn_courseAdd.setMinimumSize(new Dimension(70, 25));
                    btn_courseAdd.setMaximumSize(new Dimension(70, 25));
                    panel_courseManagePage.add(btn_courseAdd, new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0,
                                                                                     GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                     new Insets(0, 0, 0, 0), 0, 0));
                }
                tabbedPane_main.addTab("\u8bfe\u7a0b\u7ba1\u7406", panel_courseManagePage);

                //======== panel_systemInfoPage ========
                {
                    panel_systemInfoPage.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

                    //---- label1 ----
                    label1.setText("\u672c\u7cfb\u7edf\u7531 \u5357\u4eac\u4fe1\u606f\u5de5\u7a0b\u5927\u5b66 \u8ba1\u7b97\u673a\u4e0e\u8f6f\u4ef6\u5b66\u9662 \u8f6f\u4ef6\u5de5\u7a0b\u4e13\u4e1a 20\u7ea7 Java\u8bfe\u7a0b \u7b2c11\u7ec4\u5f00\u53d1");
                    label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));

                    //---- label8 ----
                    label8.setText("\u7ec4\u957f\uff1a");
                    label8.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                    //---- label9 ----
                    label9.setText("\u7ec4\u5458\uff1a");
                    label9.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                    //---- label10 ----
                    label10.setText("\u5510\u601d\u54f2  202083290247");
                    label10.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                    //---- label11 ----
                    label11.setText("\u97a0\u7acb\u9633  202083290549");
                    label11.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                    //---- label14 ----
                    label14.setText("\u5468\u5ba3\u5b8f  202083290423");
                    label14.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                    //---- label15 ----
                    label15.setText("\u674e\u6668    202083290232");
                    label15.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                    GroupLayout panel_systemInfoPageLayout = new GroupLayout(panel_systemInfoPage);
                    panel_systemInfoPage.setLayout(panel_systemInfoPageLayout);
                    panel_systemInfoPageLayout.setHorizontalGroup(
                            panel_systemInfoPageLayout.createParallelGroup()
                                    .addGroup(panel_systemInfoPageLayout.createSequentialGroup()
                                                      .addGroup(panel_systemInfoPageLayout.createParallelGroup()
                                                                        .addGroup(panel_systemInfoPageLayout.createSequentialGroup()
                                                                                          .addContainerGap()
                                                                                          .addComponent(label1))
                                                                        .addGroup(panel_systemInfoPageLayout.createSequentialGroup()
                                                                                          .addGap(49, 49, 49)
                                                                                          .addComponent(label8)
                                                                                          .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                          .addComponent(label10))
                                                                        .addGroup(panel_systemInfoPageLayout.createSequentialGroup()
                                                                                          .addGap(49, 49, 49)
                                                                                          .addComponent(label9)
                                                                                          .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                                          .addComponent(label11))
                                                                        .addGroup(panel_systemInfoPageLayout.createSequentialGroup()
                                                                                          .addGap(103, 103, 103)
                                                                                          .addGroup(panel_systemInfoPageLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                                            .addComponent(label15)
                                                                                                            .addComponent(label14))))
                                                      .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    panel_systemInfoPageLayout.setVerticalGroup(
                            panel_systemInfoPageLayout.createParallelGroup()
                                    .addGroup(panel_systemInfoPageLayout.createSequentialGroup()
                                                      .addContainerGap()
                                                      .addComponent(label1)
                                                      .addGap(18, 18, 18)
                                                      .addGroup(panel_systemInfoPageLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(label8)
                                                                        .addComponent(label10))
                                                      .addGap(18, 18, 18)
                                                      .addGroup(panel_systemInfoPageLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(label9)
                                                                        .addComponent(label11))
                                                      .addGap(18, 18, 18)
                                                      .addComponent(label14)
                                                      .addGap(18, 18, 18)
                                                      .addComponent(label15)
                                                      .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }
                tabbedPane_main.addTab("\u7cfb\u7edf\u76f8\u5173", panel_systemInfoPage);
            }
            panel1.add(tabbedPane_main, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                                                               GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                               new Insets(0, 0, 0, 0), 0, 0));
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 896, GroupLayout.PREFERRED_SIZE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 558, GroupLayout.PREFERRED_SIZE))
        );
        setSize(900, 600);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JPanel panel_title;
    private JLabel label_title;
    private JPanel panel3;
    private JLabel label_welcome;
    private JLabel label_time;
    private JButton btn_logout;
    private JTabbedPane tabbedPane_main;
    private JPanel panel_stuManagePage;
    private JLabel label2;
    private JTextField field_stuNumber;
    private JLabel label3;
    private JTextField field_stuName;
    private JLabel label4;
    private JComboBox cbBox_stuCollege;
    private JLabel label5;
    private JComboBox cbBox_stuMajor;
    private JLabel label6;
    private JComboBox cbBox_stuStatus;
    private JButton btn_stuSearch;
    private JButton btn_stuShowAll;
    private JScrollPane scrollPane1;
    private JTable table_stuInfo;
    private JButton btn_stuAdd;
    private JPanel panel_tchManagePage;
    private JLabel label7;
    private JTextField field_tchNumber;
    private JLabel label12;
    private JTextField field_tchName;
    private JLabel label13;
    private JComboBox cbBox_tchCollege;
    private JLabel label16;
    private JComboBox cbBox_tchJob;
    private JLabel label17;
    private JComboBox cbBox_tchStatus;
    private JButton btn_tchSearch;
    private JButton btn_tchShowAll;
    private JScrollPane scrollPane2;
    private JTable table_tchInfo;
    private JButton btn_tchAdd;
    private JPanel panel_courseManagePage;
    private JLabel label21;
    private JComboBox cbBox_courseCollege;
    private JLabel label22;
    private JComboBox cbBox_courseType;
    private JButton btn_courseSearch;
    private JButton btn_courseShowAll;
    private JScrollPane scrollPane3;
    private JTable table_courseInfo;
    private JButton btn_courseAdd;
    private JPanel panel_systemInfoPage;
    private JLabel label1;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label14;
    private JLabel label15;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
