/*
 * Created by JFormDesigner on Thu May 19 20:34:51 CST 2022
 */

package com.juliy.view;

import com.juliy.controller.ButtonMonitor;
import com.juliy.controller.KeyMonitor;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;
import java.awt.*;
import java.util.Objects;

/**
 * @author JuLiy
 */
public class StudentMainView extends JFrame {

    public StudentMainView() {
        initComponents();
        reInitComponents();
        addActionListener();
        setComponentsName();
        setVisible(true);
    }

    //========== 其他 ==========

    //cardPanel布局方式，用于控制卡片翻页
    private final CardLayout cardLayout = new CardLayout();

    /** 给需要监听的控件添加对应自定义监听器 */
    private void addActionListener() {
        //监听点击事件
        btn_logout.addActionListener(ButtonMonitor.btnListener);
        btn_editAddress.addActionListener(ButtonMonitor.btnListener);
        btn_editPhone.addActionListener(ButtonMonitor.btnListener);
        btn_changePassword.addActionListener(ButtonMonitor.btnListener);
        btn_queryScore.addActionListener(ButtonMonitor.btnListener);
        btn_queryAllScore.addActionListener(ButtonMonitor.btnListener);
        btn_queryCourse.addActionListener(ButtonMonitor.btnListener);
        btn_queryAllCourse.addActionListener(ButtonMonitor.btnListener);
        btn_courseFilter.addActionListener(ButtonMonitor.btnListener);
        //监听回车
        field_address.addKeyListener(KeyMonitor.keyListener);
        field_phone.addKeyListener(KeyMonitor.keyListener);
    }

    /** 给需要监听的控件设置name，以便识别 */
    private void setComponentsName() {
        btn_logout.setName("stu_logout");
        btn_editAddress.setName("stu_editAddress");
        btn_editPhone.setName("stu_editPhone");
        btn_changePassword.setName("stu_changePassword");
        btn_queryScore.setName("stu_queryScore");
        btn_queryAllScore.setName("stu_queryAllScore");
        btn_queryCourse.setName("stu_queryCourse");
        btn_queryAllCourse.setName("stu_queryAllCourse");
        btn_courseFilter.setName("stu_courseFilter");
        field_address.setName("stu_address");
        field_phone.setName("stu_phone");
    }

    /** 对部分控件重新设置 */
    private void reInitComponents() {
        //重新设置cardPanel布局方式，以便控制卡片翻页
        cardPanel_address.setLayout(cardLayout);
        cardPanel_phone.setLayout(cardLayout);

        //设置下拉框内容
        //学年
        String[] courseSchoolYear = {"全部", "2021-2022", "2020-2021", "2019-2020",
                "2018-2019", "2017-2018", "2016-2017", "2015-2016"};
        String[] scoreSchoolYear = {"全部", "2021-2022", "2020-2021", "2019-2020",
                "2018-2019", "2017-2018", "2016-2017", "2015-2016"};
        DefaultComboBoxModel<String> courseSchoolYearModel = new DefaultComboBoxModel<>(courseSchoolYear);
        DefaultComboBoxModel<String> scoreSchoolYearModel = new DefaultComboBoxModel<>(scoreSchoolYear);
        course_comboBox_schoolYear.setModel(courseSchoolYearModel);
        score_comboBox_schoolYear.setModel(scoreSchoolYearModel);
        //学期
        String[] courseTerm = {"全部", "1", "2"};
        String[] scoreTerm = {"全部", "1", "2"};
        DefaultComboBoxModel<String> courseTermModel = new DefaultComboBoxModel<>(courseTerm);
        DefaultComboBoxModel<String> scoreTermModel = new DefaultComboBoxModel<>(scoreTerm);
        course_comboBox_term.setModel(courseTermModel);
        score_comboBox_term.setModel(scoreTermModel);
        //课程性质
        String[] courseType = {"全部", "选修", "通识"};
        String[] scoreType = {"全部", "必修", "选修", "通识"};
        DefaultComboBoxModel<String> courseTypeModel = new DefaultComboBoxModel<>(courseType);
        DefaultComboBoxModel<String> scoreTypeModel = new DefaultComboBoxModel<>(scoreType);
        course_comboBox_type.setModel(courseTypeModel);
        score_comboBox_type.setModel(scoreTypeModel);

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
        //自定义渲染器
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                //非按钮列设置为隔行不同颜色
                if (!"选择".equals(value) && !"退选".equals(value)) {
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

            public MyButtonEditor(String columnName) {
                //父类无默认构造方法，需定义一个，但用不到
                super(new JTextField());
                // 设置点击1次激活编辑
                setClickCountToStart(1);
                //为按钮添加事件,只能添加ActionListener事件
                button.addActionListener(ButtonMonitor.btnListener);
                //设置name
                if ("选择".equals(columnName)) {
                    button.setName("stu_selectCourse");
                } else if ("退选".equals(columnName)) {
                    button.setName("stu_dropCourse");
                }
            }

            //重写父类的编辑方法，即点击后会发生什么变化，需设置button内容并返回
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                //设置按钮文字，不加空格按下后文字会偏右，不知道为什么
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
            if (column.getHeaderValue().equals("选择") || column.getHeaderValue().equals("退选")) {
                column.setCellEditor(new MyButtonEditor(column.getHeaderValue().toString()));
            }
        }
    }

    //========== 选课管理页面 ==========
    //======== 选课信息 ========

    /** 设置课程信息查询表格 */
    public void setTable_courseInfo(DefaultTableModel model) {
        //表头宽度
        int[] columnWidth = {100, 30, 200, 50, 70, 30};
        //设置表格
        tableSet(table_courseInfo, model, columnWidth);
    }

    /** 重设课程信息页面下拉框选择的内容 */
    public void resetCourseCbBox() {
        course_comboBox_schoolYear.setSelectedIndex(0);
        course_comboBox_term.setSelectedIndex(0);
    }

    //======== 课程选修 ========

    /** 设置课程选修表格 */
    public void setTable_courseSelect(AbstractTableModel model) {
        //表头宽度
        int[] columnWidth = {50, 200, 50, 30, 100, 150, 70, 50};
        //设置表格
        tableSet(table_courseSelect, model, columnWidth);
        //设置表格禁止选中行，否则点击按钮会被响应为选中行
        table_courseSelect.setRowSelectionAllowed(false);
    }

    /** 获取选修表格选中课程的编号和名称 */
    public String[] getSelectCourseName() {
        String[] courseInfo = new String[2];
        int row = table_courseSelect.getSelectedRow();
        courseInfo[0] = table_courseSelect.getModel().getValueAt(row, 0).toString();
        courseInfo[1] = table_courseSelect.getModel().getValueAt(row, 1).toString();
        return courseInfo;
    }

    //======== 课程退选 ========

    /** 设置课程选修表格 */
    public void setTable_courseDrop(AbstractTableModel model) {
        //表头宽度
        int[] columnWidth = {50, 200, 50, 30, 100, 150, 70, 50};
        //设置表格
        tableSet(table_courseDrop, model, columnWidth);
        //设置表格禁止选中行，否则点击按钮会被响应为选中行
        table_courseDrop.setRowSelectionAllowed(false);
    }

    /** 获取退选表格选中课程的编号和名称 */
    public String[] getDropCourseName() {
        String[] courseInfo = new String[2];
        int row = table_courseDrop.getSelectedRow();
        courseInfo[0] = table_courseDrop.getModel().getValueAt(row, 0).toString();
        courseInfo[1] = table_courseDrop.getModel().getValueAt(row, 1).toString();
        return courseInfo;
    }

    //========== 成绩查询页面 ==========

    //======== 普通课程成绩 ========

    /** 设置成绩查询表格 */
    public void setTable_score(DefaultTableModel model) {
        //表头宽度
        int[] columnWidth = {100, 30, 200, 50, 70, 70, 70, 30};
        //设置表格
        tableSet(table_score, model, columnWidth);
    }

    /** 重设成绩页面下拉框选择的内容 */
    public void resetScoreCbBox() {
        score_comboBox_schoolYear.setSelectedIndex(0);
        score_comboBox_term.setSelectedIndex(0);
        score_comboBox_type.setSelectedIndex(0);
    }

    //======== 学业完成情况 ========

    public void setTable_credit(DefaultTableModel model) {
        //表头宽度
        int[] columnWidth = {300, 300};
        //设置表格
        tableSet(table_credit, model, columnWidth);
    }

    //========== 个人信息页面 ==========

    /** 切换地址编辑按钮状态 */
    public void changeBtn_editAddress() {
        //判断当前按钮状态并进行切换
        if (btn_editAddress.getText().equals("编辑")) {
            btn_editAddress.setText("确认");
            btn_editAddress.setName("stu_confirmAddress");
        } else {
            btn_editAddress.setText("编辑");
            btn_editAddress.setName("stu_editAddress");
        }
    }

    /** 切换电话编辑按钮状态 */
    public void changeBtn_editPhone() {
        //判断当前按钮状态并进行切换
        if (btn_editPhone.getText().equals("编辑")) {
            btn_editPhone.setText("确认");
            btn_editPhone.setName("stu_confirmPhone");
        } else {
            btn_editPhone.setText("编辑");
            btn_editPhone.setName("stu_editPhone");
        }
    }

    /** 切换地址窗口状态 */
    public void changeAddressCard() {
        cardLayout.next(cardPanel_address);
        field_address.setText("");
        //判断当前显示的是否为地址输入框，若是，则获取焦点，否则失去焦点
        //若设置输入框焦点不取消，则底下会有条杠，不美观；若直接不设置焦点则不合使用逻辑
        Component c = cardPanel_address.getComponent(1);
        if (c.isVisible()) {
            c.requestFocus();
        } else
            field_address.requestFocus(false);
    }

    /** 切换电话窗口状态 */
    public void changePhoneCard() {
        cardLayout.next(cardPanel_phone);
        field_phone.setText("");
        Component c = cardPanel_phone.getComponent(1);
        if (c.isVisible()) {
            c.requestFocus();
        } else
            field_address.requestFocus(false);
    }

    //========== 各种get、set方法 ==========

    public void setLabel_name(String name) {
        label_name.setText(name);
    }

    public void setLabel_number(String number) {
        label_number.setText(number);
    }

    public void setLabel_sex(String sex) {
        label_sex.setText(sex);
    }

    public void setLabel_age(String age) {
        label_age.setText(age);
    }

    public void setLabel_nationality(String nationality) {
        label_nationality.setText(nationality);
    }

    public void setLabel_id(String id) {
        label_id.setText(id);
    }

    public void setLabel_address(String address) {
        label_address.setText(address);
    }

    public void setLabel_phone(String phone) {
        label_phone.setText(phone);
    }

    public void setLabel_hometown(String hometown) {
        label_hometown.setText(hometown);
    }

    public void setLabel_politicalStatus(String politicalStatus) {
        label_politicalStatus.setText(politicalStatus);
    }

    public void setLabel_startTime(String startTime) {
        label_startTime.setText(startTime);
    }

    public void setLabel_college(String college) {
        label_college.setText(college);
    }

    public void setLabel_preMajor(String preMajor) {
        label_preMajor.setText(preMajor);
    }

    public void setLabel_major(String major) {
        label_major.setText(major);
    }

    public void setLabel_class(String stuClass) {
        label_class.setText(stuClass);
    }

    public void setLabel_isSports(String isSports) {
        label_isSports.setText(isSports);
    }

    public void setLabel_welcome(String welcome) {
        label_welcome.setText(welcome);
    }

    public void setLabel_time(String time) {
        label_time.setText(time);
    }

    public void setLabel_credit(String credit) {
        label_credit.setText(credit);
    }

    public String getLabel_address() {
        return label_address.getText();
    }

    public String getLabel_phone() {
        return label_phone.getText();
    }

    public String getField_address() {
        return field_address.getText();
    }

    public String getField_phone() {
        return field_phone.getText();
    }

    public String getScore_CbBox_schoolYear() {
        return Objects.requireNonNull(score_comboBox_schoolYear.getSelectedItem()).toString();
    }

    public String getScore_CbBox_term() {
        return Objects.requireNonNull(score_comboBox_term.getSelectedItem()).toString();
    }

    public String getScore_CbBox_type() {
        return Objects.requireNonNull(score_comboBox_type.getSelectedItem()).toString();
    }

    public String getCourse_CbBox_schoolYear() {
        return Objects.requireNonNull(course_comboBox_schoolYear.getSelectedItem()).toString();
    }

    public String getCourse_CbBox_term() {
        return Objects.requireNonNull(course_comboBox_term.getSelectedItem()).toString();
    }

    public String getCourse_CbBox_type() {
        return Objects.requireNonNull(course_comboBox_type.getSelectedItem()).toString();
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
        btn_changePassword = new JButton();
        tabbedPane_main = new JTabbedPane();
        tabbedPane_course = new JTabbedPane();
        panel_courseInfoPage = new JPanel();
        label30 = new JLabel();
        course_comboBox_schoolYear = new JComboBox();
        label31 = new JLabel();
        course_comboBox_term = new JComboBox();
        label32 = new JLabel();
        btn_queryCourse = new JButton();
        label33 = new JLabel();
        btn_queryAllCourse = new JButton();
        scrollPane3 = new JScrollPane();
        table_courseInfo = new JTable();
        panel_courseSelect = new JPanel();
        label35 = new JLabel();
        course_comboBox_type = new JComboBox();
        label36 = new JLabel();
        btn_courseFilter = new JButton();
        scrollPane4 = new JScrollPane();
        table_courseSelect = new JTable();
        panel_courseDrop = new JPanel();
        scrollPane5 = new JScrollPane();
        table_courseDrop = new JTable();
        tabbedPane_grade = new JTabbedPane();
        panel_commonScorePage = new JPanel();
        label16 = new JLabel();
        score_comboBox_schoolYear = new JComboBox();
        label17 = new JLabel();
        score_comboBox_term = new JComboBox();
        label18 = new JLabel();
        score_comboBox_type = new JComboBox();
        label19 = new JLabel();
        btn_queryScore = new JButton();
        label28 = new JLabel();
        btn_queryAllScore = new JButton();
        label34 = new JLabel();
        scrollPane1 = new JScrollPane();
        table_score = new JTable();
        panel_creditPage = new JPanel();
        label29 = new JLabel();
        label_credit = new JLabel();
        scrollPane2 = new JScrollPane();
        table_credit = new JTable();
        panel_studentInfoPage = new JPanel();
        panel_studentInfo = new JPanel();
        panel_stuInfoLeft = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label_name = new JLabel();
        label_number = new JLabel();
        label_sex = new JLabel();
        label_age = new JLabel();
        label_nationality = new JLabel();
        label_id = new JLabel();
        cardPanel_address = new JPanel();
        label_address = new JLabel();
        field_address = new JTextField();
        btn_editAddress = new JButton();
        btn_editPhone = new JButton();
        label12 = new JLabel();
        label13 = new JLabel();
        cardPanel_phone = new JPanel();
        label_phone = new JLabel();
        field_phone = new JTextField();
        panel_stuInfoRight = new JPanel();
        label20 = new JLabel();
        label_hometown = new JLabel();
        label21 = new JLabel();
        label22 = new JLabel();
        label23 = new JLabel();
        label24 = new JLabel();
        label25 = new JLabel();
        label26 = new JLabel();
        label27 = new JLabel();
        label_politicalStatus = new JLabel();
        label_startTime = new JLabel();
        label_college = new JLabel();
        label_preMajor = new JLabel();
        label_major = new JLabel();
        label_class = new JLabel();
        label_isSports = new JLabel();
        panel_systemInfoPage = new JPanel();
        label1 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();

        //======== this ========
        setTitle("\u5b66\u751f\u7aef");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
                                                  .addGap(237, 237, 237)
                                                  .addComponent(label_title, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
                                                  .addContainerGap(268, Short.MAX_VALUE))
                );
                panel_titleLayout.setVerticalGroup(
                        panel_titleLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, panel_titleLayout.createSequentialGroup()
                                        .addComponent(label_title, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
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
                label_welcome.setText("\u4e13\u4e1a \u73ed\u7ea7 \u59d3\u540d \u540c\u5b66\uff0c\u60a8\u597d\uff0c\u6b22\u8fce\u4f7f\u7528\u672c\u7cfb\u7edf");
                label_welcome.setPreferredSize(new Dimension(271, 25));

                //---- label_time ----
                label_time.setText("\u5f53\u524d\u65f6\u95f4\uff1ayyyy-MM-dd-EEEE");
                label_time.setPreferredSize(new Dimension(255, 25));

                //---- btn_logout ----
                btn_logout.setBackground(new Color(204, 204, 204));
                btn_logout.setText("\u6ce8\u9500");

                //---- btn_changePassword ----
                btn_changePassword.setBackground(new Color(204, 204, 204));
                btn_changePassword.setText("\u4fee\u6539\u5bc6\u7801");

                GroupLayout panel3Layout = new GroupLayout(panel3);
                panel3.setLayout(panel3Layout);
                panel3Layout.setHorizontalGroup(
                        panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                                  .addComponent(label_welcome, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(label_time, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(btn_changePassword, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(btn_logout, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                  .addContainerGap(17, Short.MAX_VALUE))
                );
                panel3Layout.setVerticalGroup(
                        panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(panel3Layout.createSequentialGroup()
                                                  .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(btn_logout, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(btn_changePassword, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(label_time, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(label_welcome, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
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

                //======== tabbedPane_course ========
                {
                    tabbedPane_course.setTabPlacement(SwingConstants.LEFT);
                    tabbedPane_course.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));

                    //======== panel_courseInfoPage ========
                    {
                        panel_courseInfoPage.setLayout(new GridBagLayout());
                        ((GridBagLayout) panel_courseInfoPage.getLayout()).columnWidths = new int[]{40, 130, 40, 115, 0, 100, 0, 151, 0, 180, 0};
                        ((GridBagLayout) panel_courseInfoPage.getLayout()).rowHeights = new int[]{32, 360, 0};
                        ((GridBagLayout) panel_courseInfoPage.getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0E-4};
                        ((GridBagLayout) panel_courseInfoPage.getLayout()).rowWeights = new double[]{0.0, 1.0, 1.0E-4};

                        //---- label30 ----
                        label30.setText("\u5b66\u5e74");
                        label30.setHorizontalAlignment(SwingConstants.CENTER);
                        panel_courseInfoPage.add(label30, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                                                                                 GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                 new Insets(0, 0, 2, 0), 0, 0));
                        panel_courseInfoPage.add(course_comboBox_schoolYear, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                                                                                                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                                    new Insets(0, 0, 2, 0), 0, 0));

                        //---- label31 ----
                        label31.setText("\u5b66\u671f");
                        label31.setHorizontalAlignment(SwingConstants.CENTER);
                        panel_courseInfoPage.add(label31, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                                                                                 GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                 new Insets(0, 0, 2, 0), 0, 0));
                        panel_courseInfoPage.add(course_comboBox_term, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                                                                                              GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                              new Insets(0, 0, 2, 0), 0, 0));

                        //---- label32 ----
                        label32.setText("   ");
                        panel_courseInfoPage.add(label32, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
                                                                                 GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                 new Insets(0, 0, 2, 0), 0, 0));

                        //---- btn_queryCourse ----
                        btn_queryCourse.setText("\u67e5\u8be2");
                        btn_queryCourse.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
                        panel_courseInfoPage.add(btn_queryCourse, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0,
                                                                                         GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                         new Insets(0, 0, 2, 0), 0, 0));

                        //---- label33 ----
                        label33.setText("   ");
                        panel_courseInfoPage.add(label33, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0,
                                                                                 GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                 new Insets(0, 0, 2, 0), 0, 0));

                        //---- btn_queryAllCourse ----
                        btn_queryAllCourse.setText("\u67e5\u8be2\u6240\u6709\u9009\u8bfe\u4fe1\u606f");
                        btn_queryAllCourse.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
                        panel_courseInfoPage.add(btn_queryAllCourse, new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0,
                                                                                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                            new Insets(0, 0, 2, 0), 0, 0));

                        //======== scrollPane3 ========
                        {
                            scrollPane3.setViewportView(table_courseInfo);
                        }
                        panel_courseInfoPage.add(scrollPane3, new GridBagConstraints(0, 1, 10, 1, 0.0, 0.0,
                                                                                     GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                     new Insets(0, 0, 0, 0), 0, 0));
                    }
                    tabbedPane_course.addTab("\u9009\u8bfe\u4fe1\u606f", panel_courseInfoPage);

                    //======== panel_courseSelect ========
                    {
                        panel_courseSelect.setLayout(new GridBagLayout());
                        ((GridBagLayout) panel_courseSelect.getLayout()).columnWidths = new int[]{67, 126, 0, 0, 524, 0};
                        ((GridBagLayout) panel_courseSelect.getLayout()).rowHeights = new int[]{32, 360, 0};
                        ((GridBagLayout) panel_courseSelect.getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                        ((GridBagLayout) panel_courseSelect.getLayout()).rowWeights = new double[]{0.0, 1.0, 1.0E-4};

                        //---- label35 ----
                        label35.setText("\u8bfe\u7a0b\u6027\u8d28");
                        panel_courseSelect.add(label35, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                                                                               GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                               new Insets(0, 0, 2, 0), 0, 0));
                        panel_courseSelect.add(course_comboBox_type, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                                                                                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                            new Insets(0, 0, 2, 0), 0, 0));

                        //---- label36 ----
                        label36.setText("    ");
                        panel_courseSelect.add(label36, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                                                                               GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                               new Insets(0, 0, 2, 0), 0, 0));

                        //---- btn_courseFilter ----
                        btn_courseFilter.setText("\u7b5b\u9009");
                        btn_courseFilter.setHorizontalTextPosition(SwingConstants.CENTER);
                        panel_courseSelect.add(btn_courseFilter, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                                                                                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                        new Insets(0, 0, 2, 0), 0, 0));

                        //======== scrollPane4 ========
                        {
                            scrollPane4.setViewportView(table_courseSelect);
                        }
                        panel_courseSelect.add(scrollPane4, new GridBagConstraints(0, 1, 5, 1, 0.0, 0.0,
                                                                                   GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                   new Insets(0, 0, 0, 0), 0, 0));
                    }
                    tabbedPane_course.addTab("\u8bfe\u7a0b\u9009\u4fee", panel_courseSelect);

                    //======== panel_courseDrop ========
                    {
                        panel_courseDrop.setLayout(new GridBagLayout());
                        ((GridBagLayout) panel_courseDrop.getLayout()).columnWidths = new int[]{0, 0};
                        ((GridBagLayout) panel_courseDrop.getLayout()).rowHeights = new int[]{0, 0};
                        ((GridBagLayout) panel_courseDrop.getLayout()).columnWeights = new double[]{1.0, 1.0E-4};
                        ((GridBagLayout) panel_courseDrop.getLayout()).rowWeights = new double[]{1.0, 1.0E-4};

                        //======== scrollPane5 ========
                        {
                            scrollPane5.setViewportView(table_courseDrop);
                        }
                        panel_courseDrop.add(scrollPane5, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                                                                                 GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                 new Insets(0, 0, 0, 0), 0, 0));
                    }
                    tabbedPane_course.addTab("\u8bfe\u7a0b\u9000\u9009", panel_courseDrop);
                }
                tabbedPane_main.addTab("\u9009\u8bfe\u7ba1\u7406", tabbedPane_course);

                //======== tabbedPane_grade ========
                {
                    tabbedPane_grade.setTabPlacement(SwingConstants.LEFT);
                    tabbedPane_grade.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));

                    //======== panel_commonScorePage ========
                    {
                        panel_commonScorePage.setLayout(new GridBagLayout());
                        ((GridBagLayout) panel_commonScorePage.getLayout()).columnWidths = new int[]{40, 130, 40, 115, 70, 130, 0, 99, 0, 123, 0, 0};
                        ((GridBagLayout) panel_commonScorePage.getLayout()).rowHeights = new int[]{32, 360, 0};
                        ((GridBagLayout) panel_commonScorePage.getLayout()).columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0E-4};
                        ((GridBagLayout) panel_commonScorePage.getLayout()).rowWeights = new double[]{0.0, 0.0, 1.0E-4};

                        //---- label16 ----
                        label16.setText("\u5b66\u5e74");
                        label16.setHorizontalAlignment(SwingConstants.CENTER);
                        panel_commonScorePage.add(label16, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                                                                                  GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                  new Insets(0, 0, 2, 0), 0, 0));
                        panel_commonScorePage.add(score_comboBox_schoolYear, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                                                                                                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                                    new Insets(0, 0, 2, 0), 0, 0));

                        //---- label17 ----
                        label17.setText("\u5b66\u671f");
                        label17.setHorizontalAlignment(SwingConstants.CENTER);
                        panel_commonScorePage.add(label17, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                                                                                  GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                  new Insets(0, 0, 2, 0), 0, 0));
                        panel_commonScorePage.add(score_comboBox_term, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                                                                                              GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                              new Insets(0, 0, 2, 0), 0, 0));

                        //---- label18 ----
                        label18.setText("\u8bfe\u7a0b\u6027\u8d28");
                        label18.setHorizontalAlignment(SwingConstants.CENTER);
                        panel_commonScorePage.add(label18, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
                                                                                  GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                  new Insets(0, 0, 2, 0), 0, 0));
                        panel_commonScorePage.add(score_comboBox_type, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0,
                                                                                              GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                              new Insets(0, 0, 2, 0), 0, 0));

                        //---- label19 ----
                        label19.setText("   ");
                        panel_commonScorePage.add(label19, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0,
                                                                                  GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                  new Insets(0, 0, 2, 0), 0, 0));

                        //---- btn_queryScore ----
                        btn_queryScore.setText("\u6210\u7ee9\u67e5\u8be2");
                        btn_queryScore.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
                        panel_commonScorePage.add(btn_queryScore, new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0,
                                                                                         GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                         new Insets(0, 0, 2, 0), 0, 0));

                        //---- label28 ----
                        label28.setText("   ");
                        panel_commonScorePage.add(label28, new GridBagConstraints(8, 0, 1, 1, 0.0, 0.0,
                                                                                  GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                  new Insets(0, 0, 2, 0), 0, 0));

                        //---- btn_queryAllScore ----
                        btn_queryAllScore.setText("\u67e5\u8be2\u6240\u6709\u6210\u7ee9");
                        btn_queryAllScore.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
                        panel_commonScorePage.add(btn_queryAllScore, new GridBagConstraints(9, 0, 1, 1, 0.0, 0.0,
                                                                                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                            new Insets(0, 0, 2, 0), 0, 0));

                        //---- label34 ----
                        label34.setText("    ");
                        panel_commonScorePage.add(label34, new GridBagConstraints(10, 0, 1, 1, 0.0, 0.0,
                                                                                  GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                  new Insets(0, 0, 2, 0), 0, 0));

                        //======== scrollPane1 ========
                        {
                            scrollPane1.setViewportView(table_score);
                        }
                        panel_commonScorePage.add(scrollPane1, new GridBagConstraints(0, 1, 11, 1, 0.0, 0.0,
                                                                                      GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                      new Insets(0, 0, 0, 0), 0, 0));
                    }
                    tabbedPane_grade.addTab("\u666e\u901a\u8bfe\u7a0b\u6210\u7ee9", panel_commonScorePage);

                    //======== panel_creditPage ========
                    {
                        panel_creditPage.setLayout(new GridBagLayout());
                        ((GridBagLayout) panel_creditPage.getLayout()).columnWidths = new int[]{83, 55, 40, 0};
                        ((GridBagLayout) panel_creditPage.getLayout()).rowHeights = new int[]{32, 360, 0};
                        ((GridBagLayout) panel_creditPage.getLayout()).columnWeights = new double[]{0.0, 0.0, 1.0, 1.0E-4};
                        ((GridBagLayout) panel_creditPage.getLayout()).rowWeights = new double[]{0.0, 1.0, 1.0E-4};

                        //---- label29 ----
                        label29.setText("\u5df2\u83b7\u5b66\u5206\uff1a");
                        label29.setHorizontalAlignment(SwingConstants.CENTER);
                        label29.setHorizontalTextPosition(SwingConstants.CENTER);
                        panel_creditPage.add(label29, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                                                                             GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                             new Insets(0, 0, 2, 0), 0, 0));

                        //---- label_credit ----
                        label_credit.setText("credit");
                        label_credit.setHorizontalAlignment(SwingConstants.CENTER);
                        label_credit.setHorizontalTextPosition(SwingConstants.CENTER);
                        panel_creditPage.add(label_credit, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                                                                                  GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                  new Insets(0, 0, 2, 0), 0, 0));

                        //======== scrollPane2 ========
                        {
                            scrollPane2.setViewportView(table_credit);
                        }
                        panel_creditPage.add(scrollPane2, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0,
                                                                                 GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                                                                 new Insets(0, 0, 0, 0), 0, 0));
                    }
                    tabbedPane_grade.addTab("\u5b66\u4e1a\u5b8c\u6210\u60c5\u51b5", panel_creditPage);
                }
                tabbedPane_main.addTab("\u6210\u7ee9\u67e5\u8be2", tabbedPane_grade);

                //======== panel_studentInfoPage ========
                {

                    //======== panel_studentInfo ========
                    {
                        panel_studentInfo.setBorder(new TitledBorder(null, "\u57fa\u672c\u4fe1\u606f", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                                                                     new Font("\u5b8b\u4f53", Font.BOLD, 18), Color.black));

                        //======== panel_stuInfoLeft ========
                        {

                            //---- label2 ----
                            label2.setText("\u59d3\u540d");
                            label2.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label2.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label3 ----
                            label3.setText("\u5b66\u53f7");
                            label3.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label3.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label4 ----
                            label4.setText("\u6027\u522b");
                            label4.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label4.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label5 ----
                            label5.setText("\u5e74\u9f84");
                            label5.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label5.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label6 ----
                            label6.setText("\u6c11\u65cf");
                            label6.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label6.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label7 ----
                            label7.setText("\u8eab\u4efd\u8bc1");
                            label7.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label7.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label_name ----
                            label_name.setText("name");
                            label_name.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                            label_name.setHorizontalAlignment(SwingConstants.LEFT);

                            //---- label_number ----
                            label_number.setText("number");
                            label_number.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                            label_number.setHorizontalAlignment(SwingConstants.LEFT);

                            //---- label_sex ----
                            label_sex.setText("sex");
                            label_sex.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                            label_sex.setHorizontalAlignment(SwingConstants.LEFT);

                            //---- label_age ----
                            label_age.setText("age");
                            label_age.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                            label_age.setHorizontalAlignment(SwingConstants.LEFT);

                            //---- label_nationality ----
                            label_nationality.setText("nationality");
                            label_nationality.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                            label_nationality.setHorizontalAlignment(SwingConstants.LEFT);

                            //---- label_id ----
                            label_id.setText("id");
                            label_id.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                            label_id.setHorizontalAlignment(SwingConstants.LEFT);

                            //======== cardPanel_address ========
                            {
                                cardPanel_address.setLayout(new CardLayout());

                                //---- label_address ----
                                label_address.setText("address");
                                label_address.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                                label_address.setHorizontalAlignment(SwingConstants.LEFT);
                                label_address.setPreferredSize(new Dimension(59, 25));
                                label_address.setMinimumSize(new Dimension(59, 25));
                                cardPanel_address.add(label_address, "card1");

                                //---- field_address ----
                                field_address.setPreferredSize(new Dimension(7, 25));
                                field_address.setMinimumSize(new Dimension(7, 25));
                                field_address.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1 Light", Font.PLAIN, 16));
                                field_address.setToolTipText("\u4e0d\u80fd\u8d85\u8fc750\u4e2a\u5b57");
                                field_address.setBorder(null);
                                cardPanel_address.add(field_address, "card2");
                            }

                            //---- btn_editAddress ----
                            btn_editAddress.setText("\u7f16\u8f91");
                            btn_editAddress.setToolTipText("\u4fee\u6539\u5bb6\u5ead\u4f4f\u5740");

                            //---- btn_editPhone ----
                            btn_editPhone.setText("\u7f16\u8f91");
                            btn_editPhone.setToolTipText("\u4fee\u6539\u7535\u8bdd\u53f7\u7801");

                            //---- label12 ----
                            label12.setText("\u5bb6\u5ead\u4f4f\u5740");
                            label12.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label12.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label13 ----
                            label13.setText("\u7535\u8bdd\u53f7\u7801");
                            label13.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label13.setHorizontalAlignment(SwingConstants.CENTER);

                            //======== cardPanel_phone ========
                            {
                                cardPanel_phone.setLayout(new CardLayout());

                                //---- label_phone ----
                                label_phone.setText("phone");
                                label_phone.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                                label_phone.setHorizontalAlignment(SwingConstants.LEFT);
                                label_phone.setPreferredSize(new Dimension(59, 25));
                                label_phone.setMinimumSize(new Dimension(59, 25));
                                cardPanel_phone.add(label_phone, "card1");

                                //---- field_phone ----
                                field_phone.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1 Light", Font.PLAIN, 16));
                                field_phone.setBorder(null);
                                cardPanel_phone.add(field_phone, "card2");
                            }

                            GroupLayout panel_stuInfoLeftLayout = new GroupLayout(panel_stuInfoLeft);
                            panel_stuInfoLeft.setLayout(panel_stuInfoLeftLayout);
                            panel_stuInfoLeftLayout.setHorizontalGroup(
                                    panel_stuInfoLeftLayout.createParallelGroup()
                                            .addGroup(panel_stuInfoLeftLayout.createSequentialGroup()
                                                              .addContainerGap()
                                                              .addGroup(panel_stuInfoLeftLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                .addGroup(panel_stuInfoLeftLayout.createSequentialGroup()
                                                                                                  .addComponent(label6, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                  .addComponent(label_nationality, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(panel_stuInfoLeftLayout.createSequentialGroup()
                                                                                                  .addComponent(label5, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                  .addComponent(label_age, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(panel_stuInfoLeftLayout.createSequentialGroup()
                                                                                                  .addComponent(label4, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                  .addComponent(label_sex, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(panel_stuInfoLeftLayout.createSequentialGroup()
                                                                                                  .addGroup(panel_stuInfoLeftLayout.createParallelGroup()
                                                                                                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                                                                                                    .addComponent(label3, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                                                                                  .addGap(54, 54, 54)
                                                                                                  .addGroup(panel_stuInfoLeftLayout.createParallelGroup()
                                                                                                                    .addComponent(label_name, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                                    .addGroup(panel_stuInfoLeftLayout.createSequentialGroup()
                                                                                                                                      .addGap(0, 0, Short.MAX_VALUE)
                                                                                                                                      .addComponent(label_number, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING, panel_stuInfoLeftLayout.createSequentialGroup()
                                                                                        .addGroup(panel_stuInfoLeftLayout.createParallelGroup()
                                                                                                          .addComponent(label7, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                                                                                          .addComponent(label12, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                                                                                          .addComponent(label13, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addGroup(panel_stuInfoLeftLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                                          .addComponent(label_id, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                          .addComponent(cardPanel_address, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                          .addComponent(cardPanel_phone, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))))
                                                              .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                              .addGroup(panel_stuInfoLeftLayout.createParallelGroup()
                                                                                .addComponent(btn_editAddress, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                                                                .addComponent(btn_editPhone, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                                              .addContainerGap(34, Short.MAX_VALUE))
                            );
                            panel_stuInfoLeftLayout.linkSize(SwingConstants.HORIZONTAL, label12, label13, label2, label3, label4, label5, label6, label7);
                            panel_stuInfoLeftLayout.linkSize(SwingConstants.HORIZONTAL, btn_editAddress, btn_editPhone);
                            panel_stuInfoLeftLayout.setVerticalGroup(
                                    panel_stuInfoLeftLayout.createParallelGroup()
                                            .addGroup(panel_stuInfoLeftLayout.createSequentialGroup()
                                                              .addContainerGap()
                                                              .addGroup(panel_stuInfoLeftLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(label2)
                                                                                .addComponent(label_name))
                                                              .addGap(18, 18, 18)
                                                              .addGroup(panel_stuInfoLeftLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(label3)
                                                                                .addComponent(label_number))
                                                              .addGap(16, 16, 16)
                                                              .addGroup(panel_stuInfoLeftLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(label4)
                                                                                .addComponent(label_sex))
                                                              .addGap(18, 18, 18)
                                                              .addGroup(panel_stuInfoLeftLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(label5)
                                                                                .addComponent(label_age))
                                                              .addGap(18, 18, 18)
                                                              .addGroup(panel_stuInfoLeftLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(label6)
                                                                                .addComponent(label_nationality))
                                                              .addGap(18, 18, 18)
                                                              .addGroup(panel_stuInfoLeftLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(label_id)
                                                                                .addComponent(label7))
                                                              .addGap(18, 18, 18)
                                                              .addGroup(panel_stuInfoLeftLayout.createParallelGroup()
                                                                                .addGroup(panel_stuInfoLeftLayout.createSequentialGroup()
                                                                                                  .addGroup(panel_stuInfoLeftLayout.createParallelGroup()
                                                                                                                    .addComponent(btn_editAddress, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                                                                    .addComponent(cardPanel_address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                  .addGroup(panel_stuInfoLeftLayout.createParallelGroup()
                                                                                                                    .addComponent(cardPanel_phone, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                                                                    .addComponent(btn_editPhone, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(panel_stuInfoLeftLayout.createSequentialGroup()
                                                                                                  .addComponent(label12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                  .addGap(18, 18, 18)
                                                                                                  .addComponent(label13)))
                                                              .addContainerGap(12, Short.MAX_VALUE))
                            );
                            panel_stuInfoLeftLayout.linkSize(SwingConstants.VERTICAL, label12, label13, label2, label3, label4, label5, label6, label7);
                        }

                        //======== panel_stuInfoRight ========
                        {

                            //---- label20 ----
                            label20.setText("\u7c4d\u8d2f");
                            label20.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label20.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label_hometown ----
                            label_hometown.setText("hometown");
                            label_hometown.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                            label_hometown.setHorizontalAlignment(SwingConstants.LEFT);

                            //---- label21 ----
                            label21.setText("\u653f\u6cbb\u9762\u8c8c");
                            label21.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label21.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label22 ----
                            label22.setText("\u5165\u5b66\u5e74\u4efd");
                            label22.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label22.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label23 ----
                            label23.setText("\u5b66\u9662");
                            label23.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label23.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label24 ----
                            label24.setText("\u5206\u6d41\u524d\u4e13\u4e1a");
                            label24.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label24.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label25 ----
                            label25.setText("\u4e13\u4e1a");
                            label25.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label25.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label26 ----
                            label26.setText("\u73ed\u7ea7");
                            label26.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label26.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label27 ----
                            label27.setText("\u662f\u5426\u4f53\u80b2\u751f");
                            label27.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.ITALIC, 16));
                            label27.setHorizontalAlignment(SwingConstants.CENTER);

                            //---- label_politicalStatus ----
                            label_politicalStatus.setText("politicalStatus");
                            label_politicalStatus.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                            label_politicalStatus.setHorizontalAlignment(SwingConstants.LEFT);

                            //---- label_startTime ----
                            label_startTime.setText("startTime");
                            label_startTime.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                            label_startTime.setHorizontalAlignment(SwingConstants.LEFT);

                            //---- label_college ----
                            label_college.setText("college");
                            label_college.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                            label_college.setHorizontalAlignment(SwingConstants.LEFT);

                            //---- label_preMajor ----
                            label_preMajor.setText("preMajor");
                            label_preMajor.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                            label_preMajor.setHorizontalAlignment(SwingConstants.LEFT);

                            //---- label_major ----
                            label_major.setText("major");
                            label_major.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                            label_major.setHorizontalAlignment(SwingConstants.LEFT);

                            //---- label_class ----
                            label_class.setText("class");
                            label_class.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                            label_class.setHorizontalAlignment(SwingConstants.LEFT);

                            //---- label_isSports ----
                            label_isSports.setText("isSports");
                            label_isSports.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 16));
                            label_isSports.setHorizontalAlignment(SwingConstants.LEFT);

                            GroupLayout panel_stuInfoRightLayout = new GroupLayout(panel_stuInfoRight);
                            panel_stuInfoRight.setLayout(panel_stuInfoRightLayout);
                            panel_stuInfoRightLayout.setHorizontalGroup(
                                    panel_stuInfoRightLayout.createParallelGroup()
                                            .addGroup(panel_stuInfoRightLayout.createSequentialGroup()
                                                              .addContainerGap()
                                                              .addGroup(panel_stuInfoRightLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                .addGroup(panel_stuInfoRightLayout.createSequentialGroup()
                                                                                                  .addComponent(label27, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                  .addComponent(label_isSports, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(panel_stuInfoRightLayout.createSequentialGroup()
                                                                                                  .addComponent(label26, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                  .addComponent(label_class, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(panel_stuInfoRightLayout.createSequentialGroup()
                                                                                                  .addComponent(label25, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                  .addComponent(label_major, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(panel_stuInfoRightLayout.createSequentialGroup()
                                                                                                  .addComponent(label24, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                  .addComponent(label_preMajor, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(panel_stuInfoRightLayout.createSequentialGroup()
                                                                                                  .addComponent(label23, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                  .addComponent(label_college, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(panel_stuInfoRightLayout.createSequentialGroup()
                                                                                                  .addComponent(label22, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                  .addComponent(label_startTime, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING, panel_stuInfoRightLayout.createSequentialGroup()
                                                                                        .addComponent(label21, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addComponent(label_politicalStatus, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING, panel_stuInfoRightLayout.createSequentialGroup()
                                                                                        .addComponent(label20, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                        .addGap(55, 55, 55)
                                                                                        .addComponent(label_hometown, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)))
                                                              .addContainerGap(94, Short.MAX_VALUE))
                            );
                            panel_stuInfoRightLayout.setVerticalGroup(
                                    panel_stuInfoRightLayout.createParallelGroup()
                                            .addGroup(panel_stuInfoRightLayout.createSequentialGroup()
                                                              .addContainerGap()
                                                              .addGroup(panel_stuInfoRightLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(label20)
                                                                                .addComponent(label_hometown))
                                                              .addGap(18, 18, 18)
                                                              .addGroup(panel_stuInfoRightLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(label21)
                                                                                .addComponent(label_politicalStatus))
                                                              .addGap(18, 18, 18)
                                                              .addGroup(panel_stuInfoRightLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(label22)
                                                                                .addComponent(label_startTime))
                                                              .addGap(18, 18, 18)
                                                              .addGroup(panel_stuInfoRightLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(label23)
                                                                                .addComponent(label_college))
                                                              .addGap(18, 18, 18)
                                                              .addGroup(panel_stuInfoRightLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(label24)
                                                                                .addComponent(label_preMajor))
                                                              .addGap(18, 18, 18)
                                                              .addGroup(panel_stuInfoRightLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(label25)
                                                                                .addComponent(label_major))
                                                              .addGap(18, 18, 18)
                                                              .addGroup(panel_stuInfoRightLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(label26)
                                                                                .addComponent(label_class))
                                                              .addGap(18, 18, 18)
                                                              .addGroup(panel_stuInfoRightLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(label27)
                                                                                .addComponent(label_isSports))
                                                              .addContainerGap(10, Short.MAX_VALUE))
                            );
                        }

                        GroupLayout panel_studentInfoLayout = new GroupLayout(panel_studentInfo);
                        panel_studentInfo.setLayout(panel_studentInfoLayout);
                        panel_studentInfoLayout.setHorizontalGroup(
                                panel_studentInfoLayout.createParallelGroup()
                                        .addGroup(panel_studentInfoLayout.createSequentialGroup()
                                                          .addGap(12, 12, 12)
                                                          .addComponent(panel_stuInfoLeft, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                          .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                          .addComponent(panel_stuInfoRight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                          .addContainerGap(16, Short.MAX_VALUE))
                        );
                        panel_studentInfoLayout.linkSize(SwingConstants.HORIZONTAL, panel_stuInfoLeft, panel_stuInfoRight);
                        panel_studentInfoLayout.setVerticalGroup(
                                panel_studentInfoLayout.createParallelGroup()
                                        .addGroup(panel_studentInfoLayout.createSequentialGroup()
                                                          .addContainerGap(17, Short.MAX_VALUE)
                                                          .addGroup(panel_studentInfoLayout.createParallelGroup()
                                                                            .addComponent(panel_stuInfoLeft, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(panel_stuInfoRight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                          .addContainerGap(30, Short.MAX_VALUE))
                        );
                        panel_studentInfoLayout.linkSize(SwingConstants.VERTICAL, panel_stuInfoLeft, panel_stuInfoRight);
                    }

                    GroupLayout panel_studentInfoPageLayout = new GroupLayout(panel_studentInfoPage);
                    panel_studentInfoPage.setLayout(panel_studentInfoPageLayout);
                    panel_studentInfoPageLayout.setHorizontalGroup(
                            panel_studentInfoPageLayout.createParallelGroup()
                                    .addGroup(panel_studentInfoPageLayout.createSequentialGroup()
                                                      .addComponent(panel_studentInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                      .addGap(0, 1, Short.MAX_VALUE))
                    );
                    panel_studentInfoPageLayout.setVerticalGroup(
                            panel_studentInfoPageLayout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel_studentInfoPageLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(panel_studentInfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }
                tabbedPane_main.addTab("\u4e2a\u4eba\u4fe1\u606f", panel_studentInfoPage);

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
                                                      .addContainerGap(260, Short.MAX_VALUE))
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
                                                      .addContainerGap(212, Short.MAX_VALUE))
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
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                          .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 896, GroupLayout.PREFERRED_SIZE)
                                          .addGap(0, 0, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
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
    private JButton btn_changePassword;
    private JTabbedPane tabbedPane_main;
    private JTabbedPane tabbedPane_course;
    private JPanel panel_courseInfoPage;
    private JLabel label30;
    private JComboBox course_comboBox_schoolYear;
    private JLabel label31;
    private JComboBox course_comboBox_term;
    private JLabel label32;
    private JButton btn_queryCourse;
    private JLabel label33;
    private JButton btn_queryAllCourse;
    private JScrollPane scrollPane3;
    private JTable table_courseInfo;
    private JPanel panel_courseSelect;
    private JLabel label35;
    private JComboBox course_comboBox_type;
    private JLabel label36;
    private JButton btn_courseFilter;
    private JScrollPane scrollPane4;
    private JTable table_courseSelect;
    private JPanel panel_courseDrop;
    private JScrollPane scrollPane5;
    private JTable table_courseDrop;
    private JTabbedPane tabbedPane_grade;
    private JPanel panel_commonScorePage;
    private JLabel label16;
    private JComboBox score_comboBox_schoolYear;
    private JLabel label17;
    private JComboBox score_comboBox_term;
    private JLabel label18;
    private JComboBox score_comboBox_type;
    private JLabel label19;
    private JButton btn_queryScore;
    private JLabel label28;
    private JButton btn_queryAllScore;
    private JLabel label34;
    private JScrollPane scrollPane1;
    private JTable table_score;
    private JPanel panel_creditPage;
    private JLabel label29;
    private JLabel label_credit;
    private JScrollPane scrollPane2;
    private JTable table_credit;
    private JPanel panel_studentInfoPage;
    private JPanel panel_studentInfo;
    private JPanel panel_stuInfoLeft;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label_name;
    private JLabel label_number;
    private JLabel label_sex;
    private JLabel label_age;
    private JLabel label_nationality;
    private JLabel label_id;
    private JPanel cardPanel_address;
    private JLabel label_address;
    private JTextField field_address;
    private JButton btn_editAddress;
    private JButton btn_editPhone;
    private JLabel label12;
    private JLabel label13;
    private JPanel cardPanel_phone;
    private JLabel label_phone;
    private JTextField field_phone;
    private JPanel panel_stuInfoRight;
    private JLabel label20;
    private JLabel label_hometown;
    private JLabel label21;
    private JLabel label22;
    private JLabel label23;
    private JLabel label24;
    private JLabel label25;
    private JLabel label26;
    private JLabel label27;
    private JLabel label_politicalStatus;
    private JLabel label_startTime;
    private JLabel label_college;
    private JLabel label_preMajor;
    private JLabel label_major;
    private JLabel label_class;
    private JLabel label_isSports;
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
