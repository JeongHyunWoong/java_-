package �ڹ�26_���Ǳ�;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class VM_���Ǳ�ȭ�� {

	public static void main(String[] args) {
		VM2 v = new VM2();
	}
}
class VM2 extends Frame implements ActionListener {
	//��ǰ ���̺� �о�ͼ� �����ϴ� 2�����迭
	String coffee[][] = new String[6][4];
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	
	Label lbTitle = new Label("���Ǳ�-������ȭ��");
	Label lbSP1 = new Label("��ǰ1");
	Label lbSP2 = new Label("��ǰ2");
	Label lbSP3 = new Label("��ǰ3");
	Label lbSP4 = new Label("��ǰ4");
	Label lbSP5 = new Label("��ǰ5");
	Label lbSP6 = new Label("��ǰ6");
	
	Label lbN1 = new Label("�̸�:");	TextField tfN1 = new TextField();
	Label lbN2 = new Label("�̸�:");	TextField tfN2 = new TextField();
	Label lbN3 = new Label("�̸�:");	TextField tfN3 = new TextField();
	Label lbN4 = new Label("�̸�:");	TextField tfN4 = new TextField();
	Label lbN5 = new Label("�̸�:");	TextField tfN5 = new TextField();
	Label lbN6 = new Label("�̸�:");	TextField tfN6 = new TextField();
	
	Label lbS1 = new Label("����:");	TextField tfS1 = new TextField();
	Label lbS2 = new Label("����:");	TextField tfS2 = new TextField();
	Label lbS3 = new Label("����:");	TextField tfS3 = new TextField();
	Label lbS4 = new Label("����:");	TextField tfS4 = new TextField();
	Label lbS5 = new Label("����:");	TextField tfS5 = new TextField();
	Label lbS6 = new Label("����:");	TextField tfS6 = new TextField();
	
	Label lbG1 = new Label("����:");	TextField tfG1 = new TextField();
	Label lbG2 = new Label("����:");	TextField tfG2 = new TextField();
	Label lbG3 = new Label("����:");	TextField tfG3 = new TextField();
	Label lbG4 = new Label("����:");	TextField tfG4 = new TextField();
	Label lbG5 = new Label("����:");	TextField tfG5 = new TextField();
	Label lbG6 = new Label("����:");	TextField tfG6 = new TextField();
	
	Button btn1 = new Button("����");
	Button btn2 = new Button("����");
	Button btn3 = new Button("����");
	Button btn4 = new Button("����");
	Button btn5 = new Button("����");
	Button btn6 = new Button("����");
	private Font font20;
	
	VM2()
	{
		super("���Ǳ�");
		this.setSize(900,650);
		this.center();
		this.init();
		this.start();
		this.setVisible(true);
		//��� �����ؼ� ���� ����Ÿ �����ͼ� ���̱�.
		getData();
	}
	
	void init() {
		Font font30 = new Font("SansSerif", Font.BOLD, 30);
		
		this.setLayout(null);
		this.add(lbTitle); lbTitle.setFont(font30);
		lbTitle.setBounds(50,30,400,50);
		
		this.add(lbSP1); lbSP1.setFont(font30);
		lbSP1.setBounds(20,120,100,40);		
		this.add(lbSP2); lbSP2.setFont(font30);	
		lbSP2.setBounds(20,200,100,40);			
		this.add(lbSP3); lbSP3.setFont(font30);	
		lbSP3.setBounds(20,280,100,40);		
		this.add(lbSP4); lbSP4.setFont(font30);
		lbSP4.setBounds(20,360,100,40);		
		this.add(lbSP5); lbSP5.setFont(font30);	
		lbSP5.setBounds(20,440,100,40);			
		this.add(lbSP6); lbSP6.setFont(font30);	
		lbSP6.setBounds(20,520,100,40);	
		
		this.add(lbN1); lbN1.setFont(font30);	this.add(tfN1); tfN1.setFont(font30);
		lbN1.setBounds(130,120,90,40);			tfN1.setBounds(230,120,90,40);
		this.add(lbN2); lbN2.setFont(font30);	this.add(tfN2); tfN2.setFont(font30);
		lbN2.setBounds(130,200,90,40);			tfN2.setBounds(230,200,90,40);
		this.add(lbN3); lbN3.setFont(font30);	this.add(tfN3); tfN3.setFont(font30);
		lbN3.setBounds(130,280,90,40);			tfN3.setBounds(230,280,90,40);
		this.add(lbN4); lbN4.setFont(font30);	this.add(tfN4); tfN4.setFont(font30);
		lbN4.setBounds(130,360,90,40);			tfN4.setBounds(230,360,90,40);
		this.add(lbN5); lbN5.setFont(font30);	this.add(tfN5); tfN5.setFont(font30);
		lbN5.setBounds(130,440,90,40);			tfN5.setBounds(230,440,90,40);
		this.add(lbN6); lbN6.setFont(font30);	this.add(tfN6); tfN6.setFont(font30);
		lbN6.setBounds(130,520,90,40);			tfN6.setBounds(230,520,90,40);
		
		this.add(lbS1); lbS1.setFont(font30);	this.add(tfS1); tfS1.setFont(font30);
		lbS1.setBounds(330,120,90,40);			tfS1.setBounds(430,120,90,40);
		this.add(lbS2); lbS2.setFont(font30);	this.add(tfS2); tfS2.setFont(font30);
		lbS2.setBounds(330,200,90,40);			tfS2.setBounds(430,200,90,40);
		this.add(lbS3); lbS3.setFont(font30);	this.add(tfS3); tfS3.setFont(font30);
		lbS3.setBounds(330,280,90,40);			tfS3.setBounds(430,280,90,40);
		this.add(lbS4); lbS4.setFont(font30);	this.add(tfS4); tfS4.setFont(font30);
		lbS4.setBounds(330,360,90,40);			tfS4.setBounds(430,360,90,40);
		this.add(lbS5); lbS5.setFont(font30);	this.add(tfS5); tfS5.setFont(font30);
		lbS5.setBounds(330,440,90,40);			tfS5.setBounds(430,440,90,40);
		this.add(lbS6); lbS6.setFont(font30);	this.add(tfS6); tfS6.setFont(font30);
		lbS6.setBounds(330,520,90,40);			tfS6.setBounds(430,520,90,40);
		
		this.add(lbG1); lbG1.setFont(font30);	this.add(tfG1); tfG1.setFont(font30);
		lbG1.setBounds(530,120,90,40);			tfG1.setBounds(630,120,90,40);
		this.add(lbG2); lbG2.setFont(font30);	this.add(tfG2); tfG2.setFont(font30);
		lbG2.setBounds(530,200,90,40);			tfG2.setBounds(630,200,90,40);
		this.add(lbG3); lbG3.setFont(font30);	this.add(tfG3); tfG3.setFont(font30);
		lbG3.setBounds(530,280,90,40);			tfG3.setBounds(630,280,90,40);
		this.add(lbG4); lbG4.setFont(font30);	this.add(tfG4); tfG4.setFont(font30);
		lbG4.setBounds(530,360,90,40);			tfG4.setBounds(630,360,90,40);
		this.add(lbG5); lbG5.setFont(font30);	this.add(tfG5); tfG5.setFont(font30);
		lbG5.setBounds(530,440,90,40);			tfG5.setBounds(630,440,90,40);
		this.add(lbG6); lbG6.setFont(font30);	this.add(tfG6); tfG6.setFont(font30);
		lbG6.setBounds(530,520,90,40);			tfG6.setBounds(630,520,90,40);
		
		this.add(btn1); btn1.setFont(font30);
		btn1.setBounds(750,120,90,40);
		this.add(btn2); btn2.setFont(font30);
		btn2.setBounds(750,200,90,40);
		this.add(btn3); btn3.setFont(font30);
		btn3.setBounds(750,280,90,40);
		this.add(btn4); btn4.setFont(font30);
		btn4.setBounds(750,360,90,40);
		this.add(btn5); btn5.setFont(font30);
		btn5.setBounds(750,440,90,40);
		this.add(btn6); btn6.setFont(font30);
		btn6.setBounds(750,520,90,40);
		
		
	}
	
	void start()
	{
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		btn6.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});	
	}
	void center() {
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int) (dimen.getWidth() / 2 - dimen1.getWidth() / 2);
		ypos = (int) (dimen.getHeight() / 2 - dimen1.getHeight() / 2);
		this.setLocation(xpos,ypos);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String N1 = tfN1.getText();
		String N2 = tfN2.getText();
		String N3 = tfN3.getText();
		String N4 = tfN4.getText();
		String N5 = tfN5.getText();
		String N6 = tfN6.getText();
		
		String S1 = tfS1.getText();
		String S2 = tfS2.getText();
		String S3 = tfS3.getText();
		String S4 = tfS4.getText();
		String S5 = tfS5.getText();
		String S6 = tfS6.getText();
		
		String G1 = tfG1.getText();
		String G2 = tfG2.getText();
		String G3 = tfG3.getText();
		String G4 = tfG4.getText();
		String G5 = tfG5.getText();
		String G6 = tfG6.getText();
		
		if(e.getSource() == btn1)
		{
			if(spaceCheck(N1, S1, G1))
			{
				return;
			}
			else
			{
				update(1,N1, S1, G1);
			}
					
		}
		else if(e.getSource() == btn2)
		{
			if(spaceCheck(N2, S2, G2))
			{
				return;
			}
			else
			{
				update(2,N2, S2, G2);
			}
			
		}
		else if(e.getSource() == btn3)
		{
			if(spaceCheck(N3, S3, G3))
			{
				return;
			}
			else
			{
				update(3,N3, S3, G3);
			}
		}
		else if(e.getSource() == btn4)
		{
			if(spaceCheck(N4, S4, G4))
			{
				return;
			}
			else
			{
				update(4,N4, S4, G4);
			}
		}
		else if(e.getSource() == btn5)
		{
			if(spaceCheck(N5, S5, G5))
			{
				return;
			}
			else
			{
				update(5,N5, S5, G5);
			}
		}
		else if(e.getSource() == btn6)
		{
			if(spaceCheck(N6, S6, G6))
			{
				return;
			}
			else
			{
				update(6,N6, S6, G6);
			}
		}
	}
	boolean spaceCheck(String name, String cnt, String price)	
	{
		if(name.equals("")) {msg("Ŀ�Ǹ��� �����ּ���."); return true;}
		if(cnt.equals("")) {msg("������ �����ּ���.");   return true;}
		if(price.equals("")) {msg("������ �����ּ���."); return true;}
		
		//��������Դٴ°� ������ �ƴ϶� false~
		return false;
	}
	boolean update(int idx, String name, String cnt, String price)
	{
		Connection dc=null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException ee) {		
		}
		String url = "jdbc:mysql://127.0.0.1:3306/dw202?"
				+ "useUnicode=true&characterEncoding=utf8";
		String id = "root";
		String pass = "qwer";
		try {
			dc = DriverManager.getConnection(url, id, pass);
		} catch (SQLException ee) {
		}
		
		String query = "update coffee set name = ?, cnt = ?, price = ? where idx = ?";
	
		try {
			PreparedStatement pstmt = dc.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, cnt);
			pstmt.setString(3, price);
			pstmt.setInt(4, idx);			
			pstmt.executeUpdate();
			pstmt.close();
			msg("���������Ϸ�!");
		} catch (SQLException ee) {
			System.err.println("�������� ����!!:"+ee.getMessage());
			return false;
		}
		return true;
	}
	void msg(String msg)
	{
		final Dialog dlg = new Dialog(this, "�˸� �޼���â", true);
		dlg.setLayout(null);
		Label lbMsg = new Label(msg);
		
		dlg.add(lbMsg);	lbMsg.setFont(font20);
		lbMsg.setBounds(100, 100, 450, 30);
		
		dlg.setSize(500, 250);
		dlg.setLocation(700, 400);
		
		dlg.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dlg.setVisible(false);
			}
		});
		dlg.setVisible(true);
	}
	void getData() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException ee) {
			System.out.println("����̺갡 ����ȵ�.");
			System.exit(0);
		}
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/dw202?"
				+ "useUnicode=true&characterEncoding=utf8";				
		String id = "root";
		String pass = "qwer";
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		String query = "select * from coffee";
		try {
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			String result="";
			int count=0;
			
			while (rs.next()) {
				coffee[count][0] = rs.getInt("idx")+"";
				coffee[count][1] = rs.getString("name");
				coffee[count][2] = rs.getString("cnt");
				coffee[count][3] = rs.getString("price");				
				count++;
			}				
				tfN1.setText(coffee[0][1]);
				tfS1.setText(coffee[0][2]);
				tfG1.setText(coffee[0][3]);
				
				tfN2.setText(coffee[1][1]);
				tfS2.setText(coffee[1][2]);
				tfG2.setText(coffee[1][3]);
				
				tfN3.setText(coffee[2][1]);
				tfS3.setText(coffee[2][2]);
				tfG3.setText(coffee[2][3]);
					
				tfN4.setText(coffee[3][1]);
				tfS4.setText(coffee[3][2]);
				tfG4.setText(coffee[3][3]);
					
				tfN5.setText(coffee[4][1]);
				tfS5.setText(coffee[4][2]);
				tfG5.setText(coffee[4][3]);
					
				tfN6.setText(coffee[5][1]);
				tfS6.setText(coffee[5][2]);
				tfG6.setText(coffee[5][3]);
				
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException ee) {
			System.err.println("error = " + ee.toString());
		}
	}
}