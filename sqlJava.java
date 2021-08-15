import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class sqlJava extends javax.swing.JFrame {

    public sqlJava() { //constructor....
        initComponents();
        showTableData();
    }
    private String sql =null;
    private PreparedStatement preparedSt = null;
    private ResultSet res = null;
    private Connection con = null;
    
	public Connection connectDB() {
	    try {
                    //Database connecting parameters..
                    String dataBaseURL = "jdbc:mysql://localhost:3306/employee";
                    String username = "root";
                    String password = "";
                    
                    //Creating the connection object..
		    Connection con = DriverManager.getConnection(dataBaseURL,username,password);
		    if (con != null) { //if connected to the database
		        System.out.println("Connected");
		        return con;
		    }
		    else {  //if cannot connect to the database
		    	System.out.println("Not Connected");
		    	return null;
		    }
		} catch (SQLException ex) {
		    return null;
		}
        }
               
        public void insertToDB(){ 
        try{

            con=connectDB();
            //Store the user enter data values...
            String Fname = txtEmployee_Fname.getText();
            String Minit = txtEmployee_Minit.getText();
            String Lname = txtEmployee_Lname.getText();
            String Ssn = txtEmployee_Ssn.getText();
            String Dob = txtEmployee_Bdate.getText();
            String Address = txtEmployee_Address.getText();
            String Sex = txtEmployee_Sex.getText();
            String Salary = txtEmployee_Salary.getText();
            String Super_ssn = txtEmployee_Super_ssn.getText();
            String Dno = txtEmployee_Dno.getText();
            
            //insert to SQL..
            sql = "INSERT INTO EMPLOYEE(Fname,Minit,Lname,Ssn,Bdate,Address,Sex,Salary,Super_ssn,Dno)values('"+Fname+"','"+Minit+
                    "','"+Lname+"','"+Integer.parseInt(Ssn)+"','"+Dob+"','"+Address+"','"+Sex+"','"+Integer.parseInt(Salary)+
                    "','"+Integer.parseInt(Super_ssn)+"','"+Integer.parseInt(Dno)+"')";
            
            int tuppleInserted = con.prepareStatement(sql).executeUpdate();
            if (tuppleInserted > 0) //if record added successfully..
                System.out.println("Record Added"); 
            else   //if record adding failed..
                System.out.println("Insert Failed");
            
            con.close();
            } catch(Exception e) {
                System.out.println(e);
            }
              showTableData(); //show the updated table
        }
        
   public void deleteFromDB(){
        String tempory_Ssn = txtEmployee_Ssn.getText();
        con=connectDB();
        try {
        //check the matching Ssn with user input in SQL that want to delete
        sql = "DELETE FROM EMPLOYEE WHERE Ssn = ?";        
        preparedSt = con.prepareStatement(sql);
        preparedSt.setString(1, tempory_Ssn);
        
        int tupplesDeleted = preparedSt.executeUpdate();
            if (tupplesDeleted > 0) //if record deleting successfully....
                System.out.println("DELETED SUCCESSFULLY");
            else   //if record deleting failed..
                System.out.println("ERROR OCCURED");
            
            con.close();
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
        }
        clear();
        showTableData();  //show the updated table
       
    }
   
   	public void updateDB() {
        try {
            con=connectDB();
            //updating values that user enter..             
            String Fname = txtEmployee_Fname.getText();
            String Minit = txtEmployee_Minit.getText();
            String Lname = txtEmployee_Lname.getText();
            String Ssn = txtEmployee_Ssn.getText();
            String Dob = txtEmployee_Bdate.getText();
            String Address = txtEmployee_Address.getText();
            String Sex = txtEmployee_Sex.getText();
            String Salary = txtEmployee_Salary.getText();
            String Super_ssn = txtEmployee_Super_ssn.getText();
            String Dno = txtEmployee_Dno.getText();
            //updating values in SQL
            String sql = "UPDATE EMPLOYEE SET Fname=?, Minit=?, Lname=?, Bdate=?, Address=?, Sex=?,Salary=?, Super_ssn=?, Dno=? WHERE Ssn=?";
            //setting strings to relevant parameters....
            preparedSt = con.prepareStatement(sql);
            //setting strings to relevant parameters....
            preparedSt.setString(1, Fname);
            preparedSt.setString(2, Minit);
            preparedSt.setString(3, Lname);
            preparedSt.setString(4, Dob);
            preparedSt.setString(5, Address);
            preparedSt.setString(6, Sex);
            preparedSt.setInt(7, Integer.parseInt(Salary));
            preparedSt.setInt(8, Integer.parseInt(Super_ssn));
            preparedSt.setInt(9, Integer.parseInt(Dno));
            preparedSt.setInt(10, Integer.parseInt(Ssn));
			 
            int tuppleUpdate = preparedSt.executeUpdate();
            if (tuppleUpdate > 0) //if record updating successfully..
                System.out.println("UPDATED SUCCESSFULLY!");
            else   //if record updating successfully...
                System.out.println("UPDATED FAILED");           
            con.close();
                
            } catch(Exception e) {
                    System.out.println(e);
            }
            showTableData(); //show the updated table
	}
    
        public void clear(){ //clearing the record..
                txtEmployee_Fname.setText("");
                txtEmployee_Minit.setText("");
                txtEmployee_Lname.setText("");
                txtEmployee_Bdate.setText("");
                txtEmployee_Ssn.setText("");
                txtEmployee_Address.setText("");
                txtEmployee_Sex.setText("");
                txtEmployee_Salary.setText("");
                txtEmployee_Super_ssn.setText("");
                txtEmployee_Dno.setText("");
        }
        
        public void showTableData(){  //function for showing the updated table
            try{
                //Database connecting parameters..
                String dataBaseURL = "jdbc:mysql://localhost:3306/employee";
                String username = "root";
                String password = "";
                    
                //Creating the connection object..
		Connection con = DriverManager.getConnection(dataBaseURL,username,password);
                String sql = "SELECT * FROM employee";
                preparedSt = con.prepareStatement(sql);
                res=preparedSt.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(res));
                }
                catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);

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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtEmployee_Ssn = new javax.swing.JTextField();
        txtEmployee_Fname = new javax.swing.JTextField();
        txtEmployee_Minit = new javax.swing.JTextField();
        txtEmployee_Lname = new javax.swing.JTextField();
        txtEmployee_Bdate = new javax.swing.JTextField();
        txtEmployee_Address = new javax.swing.JTextField();
        txtEmployee_Sex = new javax.swing.JTextField();
        txtEmployee_Salary = new javax.swing.JTextField();
        txtEmployee_Super_ssn = new javax.swing.JTextField();
        txtEmployee_Dno = new javax.swing.JTextField();
        button_Insert = new javax.swing.JButton();
        Button_Update = new javax.swing.JButton();
        button_Delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 40)); // NOI18N
        jLabel1.setText("Employee data Table");

        jLabel2.setFont(new java.awt.Font("Neue Montreal Medium", 0, 14)); // NOI18N
        jLabel2.setText("Ssn");

        jLabel3.setFont(new java.awt.Font("Neue Montreal Medium", 0, 14)); // NOI18N
        jLabel3.setText("Lname");

        jLabel4.setFont(new java.awt.Font("Neue Montreal Medium", 0, 14)); // NOI18N
        jLabel4.setText("Fname");

        jLabel5.setFont(new java.awt.Font("Neue Montreal Medium", 0, 14)); // NOI18N
        jLabel5.setText("Minit");

        jLabel6.setFont(new java.awt.Font("Neue Montreal Medium", 0, 14)); // NOI18N
        jLabel6.setText("Bdate");

        jLabel7.setFont(new java.awt.Font("Neue Montreal Medium", 0, 14)); // NOI18N
        jLabel7.setText("Address");

        jLabel8.setFont(new java.awt.Font("Neue Montreal Medium", 0, 14)); // NOI18N
        jLabel8.setText("Sex");

        jLabel9.setFont(new java.awt.Font("Neue Montreal Medium", 0, 14)); // NOI18N
        jLabel9.setText("Salary");

        jLabel10.setFont(new java.awt.Font("Neue Montreal Medium", 0, 14)); // NOI18N
        jLabel10.setText("Super_ssn");

        jLabel11.setFont(new java.awt.Font("Neue Montreal Medium", 0, 14)); // NOI18N
        jLabel11.setText("Dno");

        txtEmployee_Ssn.setBackground(new java.awt.Color(255, 204, 255));
        txtEmployee_Ssn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtEmployee_Fname.setBackground(new java.awt.Color(255, 204, 255));
        txtEmployee_Fname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtEmployee_Fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmployee_FnameActionPerformed(evt);
            }
        });

        txtEmployee_Minit.setBackground(new java.awt.Color(255, 204, 255));
        txtEmployee_Minit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtEmployee_Minit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmployee_MinitActionPerformed(evt);
            }
        });

        txtEmployee_Lname.setBackground(new java.awt.Color(255, 204, 255));
        txtEmployee_Lname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtEmployee_Lname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmployee_LnameActionPerformed(evt);
            }
        });

        txtEmployee_Bdate.setBackground(new java.awt.Color(255, 204, 255));
        txtEmployee_Bdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtEmployee_Address.setBackground(new java.awt.Color(255, 204, 255));
        txtEmployee_Address.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtEmployee_Sex.setBackground(new java.awt.Color(255, 204, 255));
        txtEmployee_Sex.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtEmployee_Salary.setBackground(new java.awt.Color(255, 204, 255));
        txtEmployee_Salary.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtEmployee_Super_ssn.setBackground(new java.awt.Color(255, 204, 255));
        txtEmployee_Super_ssn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtEmployee_Dno.setBackground(new java.awt.Color(255, 204, 255));
        txtEmployee_Dno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        button_Insert.setBackground(new java.awt.Color(255, 0, 0));
        button_Insert.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 15)); // NOI18N
        button_Insert.setText("INSERT");
        button_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_InsertActionPerformed(evt);
            }
        });

        Button_Update.setBackground(new java.awt.Color(255, 51, 51));
        Button_Update.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        Button_Update.setText("UPDATE");
        Button_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_UpdateActionPerformed(evt);
            }
        });

        button_Delete.setBackground(new java.awt.Color(255, 0, 51));
        button_Delete.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        button_Delete.setText("DELETE");
        button_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DeleteActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel12.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 40)); // NOI18N
        jLabel12.setText("Employee data Form");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEmployee_Dno, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                .addComponent(txtEmployee_Super_ssn)
                                .addComponent(txtEmployee_Salary)
                                .addComponent(txtEmployee_Sex)
                                .addComponent(txtEmployee_Address)))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtEmployee_Lname))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtEmployee_Ssn, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(4, 4, 4)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEmployee_Minit)
                                        .addComponent(txtEmployee_Fname)))))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(button_Insert)
                            .addGap(27, 27, 27)
                            .addComponent(Button_Update)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                            .addComponent(button_Delete)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmployee_Bdate, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(400, 400, 400)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(21, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmployee_Fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmployee_Minit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmployee_Lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmployee_Ssn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmployee_Bdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmployee_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmployee_Sex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmployee_Salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmployee_Super_ssn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmployee_Dno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_UpdateActionPerformed
      updateDB();  // TODO add your handling code here:
    }//GEN-LAST:event_Button_UpdateActionPerformed

    private void button_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DeleteActionPerformed
        deleteFromDB();        // TODO add your handling code here:
    }//GEN-LAST:event_button_DeleteActionPerformed

    private void button_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_InsertActionPerformed
    insertToDB();        // TODO add your handling code here:
    }//GEN-LAST:event_button_InsertActionPerformed

    private void txtEmployee_FnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmployee_FnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmployee_FnameActionPerformed

    private void txtEmployee_MinitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmployee_MinitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmployee_MinitActionPerformed

    private void txtEmployee_LnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmployee_LnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmployee_LnameActionPerformed

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
            java.util.logging.Logger.getLogger(sqlJava.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sqlJava.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sqlJava.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sqlJava.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sqlJava().setVisible(true);
                sqlJava e = new sqlJava();
                e.connectDB();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Update;
    private javax.swing.JButton button_Delete;
    private javax.swing.JButton button_Insert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtEmployee_Address;
    private javax.swing.JTextField txtEmployee_Bdate;
    private javax.swing.JTextField txtEmployee_Dno;
    private javax.swing.JTextField txtEmployee_Fname;
    private javax.swing.JTextField txtEmployee_Lname;
    private javax.swing.JTextField txtEmployee_Minit;
    private javax.swing.JTextField txtEmployee_Salary;
    private javax.swing.JTextField txtEmployee_Sex;
    private javax.swing.JTextField txtEmployee_Ssn;
    private javax.swing.JTextField txtEmployee_Super_ssn;
    // End of variables declaration//GEN-END:variables
}
