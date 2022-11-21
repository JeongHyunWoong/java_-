package 자바26_자판기;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class VM_커피자판기 {

	public static void main(String[] args) {
		VM v = new VM();
	}
}
class VM extends Frame implements ActionListener{
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	private Image img1;
	private Image img2;
	private Image img3;
	private Image img4;
	private Image img5;
	private Image img6;
	int P1=1,P2=1,P3=1,P4=1,P5=1,P6=1;
	int point = 0;
	int coin=0; //현재투입금액
	int totCoin=0; //총결제금액
	//선택한 커피 순번
	int selCoffee = 0;
	int coffeeCnt[] = {1,2,3,4,5,6};
	int coffeePrice[] = {2500,2000,4000,1500,3000,1000};
	String coffeeName[] = {"모카커피","아메리카노","카푸치노","율무차","핫초코","다방커피"};
	boolean makeCheck = false;
	
	Label lbTitle = new Label("자판기");
	Label lbTitle1 = new Label("선택상품");
	Label lbS1 = new Label("수량");
	Label lbS2 = new Label("수량");
	Label lbS3 = new Label("수량");
	Label lbS4 = new Label("수량");
	Label lbS5 = new Label("수량");
	Label lbS6 = new Label("수량");
	Label lbS7 = new Label("수량:");
	
	Label lbG1 = new Label("0개");
	Label lbG2 = new Label("0개");
	Label lbG3 = new Label("0개");
	Label lbG4 = new Label("0개");
	Label lbG5 = new Label("0개");
	Label lbG6 = new Label("0개");
	Label lbG7 = new Label("0개");
	
	Label lbICE7 = new Label("선택대기중...");
	
	Label lbW1 = new Label("2500원");
	Label lbW2 = new Label("2000원");
	Label lbW3 = new Label("4000원");
	Label lbW4 = new Label("1500원");
	Label lbW5 = new Label("3000원");
	Label lbW6 = new Label("1000원");
	Label lbW7 = new Label("0원",Label.RIGHT);
	
	Label lbTG = new Label("투입금액");
	
	Button btnICE1 = new Button("모카커피");
	Button btnICE2 = new Button("아메리카노");
	Button btnICE3 = new Button("카푸치노");
	Button btnICE4 = new Button("율무차");
	Button btnICE5 = new Button("핫초코");
	Button btnICE6 = new Button("다방커피");
	Button btn1 = new Button("1000원");
	Button btn2 = new Button("5000원");
	Button btn3 = new Button("10000원");
	Button btn4 = new Button("결재하기");
	
	Label lbN1 = new Label("현재잔액:");
	Label lbN2 = new Label("0원",Label.RIGHT);
	Label lbN3 = new Label("결재금액:");
	Label lbN4 = new Label("0원",Label.RIGHT);
	private Font font20;
	private Image imgSel;
	
	VM()
	{
		super("자판기");
		this.setSize(1000,900);
		this.center();
		this.init();
		this.start();
		this.setVisible(true);
	}
	void init() {
	//디비연동해서 값처리
	//상품명, 상품수량, 상품가격을 가져옴
	getData();
	//화면에 보여지는 구간 셋팅
		btnICE1.setLabel(coffeeName[0]);
		lbG1.setText(coffeeCnt[0]+"개");
		lbW1.setText(coffeePrice[0]+"원");
				
		btnICE2.setLabel(coffeeName[1]);
		lbG1.setText(coffeeCnt[1]+"개");
		lbW1.setText(coffeePrice[1]+"원");
				
		btnICE3.setLabel(coffeeName[2]);
		lbG1.setText(coffeeCnt[2]+"개");
		lbW1.setText(coffeePrice[2]+"원");
				
		btnICE4.setLabel(coffeeName[3]);
		lbG1.setText(coffeeCnt[3]+"개");
		lbW1.setText(coffeePrice[3]+"원");
				
		btnICE5.setLabel(coffeeName[4]);
		lbG1.setText(coffeeCnt[4]+"개");
		lbW1.setText(coffeePrice[4]+"원");
				
		btnICE6.setLabel(coffeeName[5]);
		lbG1.setText(coffeeCnt[5]+"개");
		lbW1.setText(coffeePrice[5]+"원");
		
		Font font20 = new Font("SansSerif", Font.BOLD, 20);
		Font font50 = new Font("SansSerif", Font.BOLD, 50);
		
		this.setLayout(null);
		this.add(lbTitle); lbTitle.setFont(font50);
		lbTitle.setBounds(200,30,200,50);
		this.add(lbTitle1); lbTitle1.setFont(font50);
		lbTitle1.setBounds(700,120,250,50);
		
		this.add(lbS1); lbS1.setFont(font20);
		lbS1.setBounds(50,220,70,40);
		this.add(lbS2); lbS2.setFont(font20);
		lbS2.setBounds(280,220,70,40);
		this.add(lbS3); lbS3.setFont(font20);
		lbS3.setBounds(510,220,70,40);
		this.add(lbS4); lbS4.setFont(font20);
		lbS4.setBounds(50,470,70,40);
		this.add(lbS5); lbS5.setFont(font20);
		lbS5.setBounds(280,470,70,40);
		this.add(lbS6); lbS6.setFont(font20);
		lbS6.setBounds(510,470,70,40);
		this.add(lbS7); lbS7.setFont(font20);
		lbS7.setBounds(720,350,80,50);
		
		this.add(lbG1); lbG1.setFont(font20);
		lbG1.setBounds(120,220,70,40);
		this.add(lbG2); lbG2.setFont(font20);
		lbG2.setBounds(350,220,70,40);
		this.add(lbG3); lbG3.setFont(font20);
		lbG3.setBounds(580,220,70,40);
		this.add(lbG4); lbG4.setFont(font20);
		lbG4.setBounds(120,470,70,40);
		this.add(lbG5); lbG5.setFont(font20);
		lbG5.setBounds(350,470,70,40);
		this.add(lbG6); lbG6.setFont(font20);
		lbG6.setBounds(580,470,70,40);
		this.add(lbG7); lbG7.setFont(font20);
		lbG7.setBounds(800,350,70,50);
		
		this.add(lbICE7); lbICE7.setFont(font20);
		lbICE7.setBounds(720,390,150,50);

		this.add(lbW1); lbW1.setFont(font20);
		lbW1.setBounds(50,310,110,40);
		this.add(lbW2); lbW2.setFont(font20);
		lbW2.setBounds(280,310,110,40);
		this.add(lbW3); lbW3.setFont(font20);
		lbW3.setBounds(510,310,110,40);
		this.add(lbW4); lbW4.setFont(font20);
		lbW4.setBounds(50,560,110,40);
		this.add(lbW5); lbW5.setFont(font20);
		lbW5.setBounds(280,560,110,40);
		this.add(lbW6); lbW6.setFont(font20);
		lbW6.setBounds(510,560,110,40);
		this.add(lbW7); lbW7.setFont(font20);
		lbW7.setBounds(720,430,100,80);
		
		this.add(lbTG); lbTG.setFont(font20);
		lbTG.setBounds(30,600,150,50);
		
		this.add(btnICE1); btnICE1.setFont(font20);
		btnICE1.setBounds(50,260,120,40);
		this.add(btnICE2); btnICE2.setFont(font20);
		btnICE2.setBounds(280,260,120,40);
		this.add(btnICE3); btnICE3.setFont(font20);
		btnICE3.setBounds(510,260,120,40);
		this.add(btnICE4); btnICE4.setFont(font20);
		btnICE4.setBounds(50,510,120,40);
		this.add(btnICE5); btnICE5.setFont(font20);
		btnICE5.setBounds(280,510,120,40);
		this.add(btnICE6); btnICE6.setFont(font20);
		btnICE6.setBounds(510,510,120,40);
		this.add(btn1); btn1.setFont(font50);
		btn1.setBounds(30,650,200,80);
		this.add(btn2); btn2.setFont(font50);
		btn2.setBounds(250,650,200,80);
		this.add(btn3); btn3.setFont(font50);
		btn3.setBounds(470,650,200,80);
		this.add(btn4); btn4.setFont(font50);
		btn4.setBounds(700,650,230,80);
		
		this.add(lbN1); lbN1.setFont(font50);
		lbN1.setBounds(30,770,230,80);
		this.add(lbN2); lbN2.setFont(font50);
		lbN2.setBounds(270,770,190,80);
		this.add(lbN3); lbN3.setFont(font50);
		lbN3.setBounds(490,770,230,80);
		this.add(lbN4); lbN4.setFont(font50);
		lbN4.setBounds(730,770,190,80);
		
		img1 = Toolkit.getDefaultToolkit().getImage("sj1.png");
		img2 = Toolkit.getDefaultToolkit().getImage("sj2.png");
		img3 = Toolkit.getDefaultToolkit().getImage("sj3.png");
		img4 = Toolkit.getDefaultToolkit().getImage("sj4.png");
		img5 = Toolkit.getDefaultToolkit().getImage("sj5.png");
		img6 = Toolkit.getDefaultToolkit().getImage("sj6.png");
		imgSel = Toolkit.getDefaultToolkit().getImage("img/default.png");
	}
	public void paint(Graphics g) {
		g.drawImage(img1, 50,100,120,120, this);
		g.drawImage(img2, 280,100,120,120, this);
		g.drawImage(img3, 510,100,120,120, this);
		g.drawImage(img4, 50,350,120,120, this);
		g.drawImage(img5, 280,350,120,120, this);
		g.drawImage(img6, 510,350,120,120, this);
		g.drawImage(imgSel, 730,200,120,120, this);
	}
	void start()
	{
		btnICE1.addActionListener(this);
		btnICE2.addActionListener(this);
		btnICE3.addActionListener(this);
		btnICE4.addActionListener(this);
		btnICE5.addActionListener(this);
		btnICE6.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		
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
		if(e.getSource()==btnICE1) {
			selCoffee=0;//1번커피선택
			totCoin += coffeePrice[0];
			lbN4.setText(totCoin+"원");	
			lbW7.setText(totCoin+"원");
			lbICE7.setText(coffeeName[0]);
			lbG1.setText(P1+"개"); P1++; point++;
			imgSel = Toolkit.getDefaultToolkit().getImage("sj1.png");
			this.repaint();
		}
		if(e.getSource()==btnICE2) {
			selCoffee=1;//2번커피선택
			totCoin += coffeePrice[1];
			lbN4.setText(totCoin+"원");
			lbW7.setText(totCoin+"원");
			lbICE7.setText(coffeeName[1]);
			lbG2.setText(P2+"개"); P2++; point++;
			imgSel = Toolkit.getDefaultToolkit().getImage("sj2.png");
			this.repaint();
		}
		if(e.getSource()==btnICE3) {
			selCoffee=2;//3번커피선택
			totCoin += coffeePrice[2];
			lbN4.setText(totCoin+"원");
			lbW7.setText(totCoin+"원");
			lbICE7.setText(coffeeName[2]);
			lbG3.setText(P3+"개"); P3++; point++;
			imgSel = Toolkit.getDefaultToolkit().getImage("sj3.png");
			this.repaint();
		}
		if(e.getSource()==btnICE4) {
			selCoffee=3;//4번커피선택
			totCoin += coffeePrice[3];
			lbN4.setText(totCoin+"원");
			lbW7.setText(totCoin+"원");
			lbICE7.setText(coffeeName[3]);
			lbG4.setText(P4+"개"); P4++; point++;
			imgSel = Toolkit.getDefaultToolkit().getImage("sj4.png");
			this.repaint();
		}
		if(e.getSource()==btnICE5) {
			selCoffee=4;//5번커피선택
			totCoin += coffeePrice[4];
			lbN4.setText(totCoin+"원");
			lbW7.setText(totCoin+"원");
			lbICE7.setText(coffeeName[4]);
			lbG5.setText(P5+"개"); P5++; point++;
			imgSel = Toolkit.getDefaultToolkit().getImage("sj5.png");
			this.repaint();
		}
		if(e.getSource()==btnICE6) {
			selCoffee=5;//6번커피선택
			totCoin += coffeePrice[5];
			lbN4.setText(totCoin+"원");
			lbW7.setText(totCoin+"원");
			lbICE7.setText(coffeeName[5]);
			lbG6.setText(P6+"개"); P6++; point++;
			imgSel = Toolkit.getDefaultToolkit().getImage("sj6.png");
			this.repaint();
		}
		lbG7.setText(point+"개");
		
		if(e.getSource() == btn1)
		{
			
			coin+=1000;
			lbN2.setText(coin+"원");
		}
		else if(e.getSource() == btn2)
		{
			coin+=5000;
			lbN2.setText(coin+"원");
		}
		else if(e.getSource() == btn3)
		{
			coin+=10000;
			lbN2.setText(coin+"원");
		}
		
		if(e.getSource()==btn4) {
			lbG7.setText("0개");
			lbW7.setText("0원");
			coin-=totCoin;
			lbN2.setText(coin+"원");
			lbN4.setText("0원");
		}
		
		//수량체크 메서드
		countCheck(selCoffee);
		
		if(makeCheck==true)
		{			
			imgSel = Toolkit.getDefaultToolkit().getImage("img/ing.gif");
			this.repaint();
		}
		else
		{
			makeCheck=true;
		}	
	}
	void countCheck(int selCoffee)
	{
		if(coffeeCnt[selCoffee]==0)
		{
			msg("수량이없어서 구매가불가능합니다.");			
			makeCheck=false;
			return;
		}		
		if(selCoffee==0)
		{
			coffeeCnt[0]--;
			lbG1.setText(coffeeCnt[0]+"개");
		}
		else if(selCoffee==1)
		{
			coffeeCnt[1]--;
			lbG2.setText(coffeeCnt[1]+"개");
		}
		else if(selCoffee==2)
		{
			coffeeCnt[2]--;
			lbG3.setText(coffeeCnt[2]+"개");
		}
		else if(selCoffee==3)
		{
			coffeeCnt[3]--;
			lbG4.setText(coffeeCnt[3]+"개");
		}
		else if(selCoffee==4)
		{
			coffeeCnt[4]--;
			lbG5.setText(coffeeCnt[4]+"개");
		}
		else if(selCoffee==5)
		{
			coffeeCnt[5]--;
			lbG6.setText(coffeeCnt[5]+"개");
		}
	}
	void msg(String msg) {
		final Dialog dlg = new Dialog(this, "알림 메세지창", true);
		dlg.setLayout(null);
		Label lbMsg = new Label(msg);
		
		dlg.add(lbMsg);	lbMsg.setFont(font20);
		lbMsg.setBounds(100, 100, 450, 30);
		
		dlg.setSize(450, 250);
		dlg.setLocation(700, 400);
		
		dlg.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dlg.setVisible(false);
			}
		});
		dlg.setVisible(true);
	}
	void getData()
	{
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException ee) {
			System.out.println("드라이브가 연결안됨.");
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
				
				coffeeName[count] = rs.getString("name");
				coffeeCnt[count] = rs.getInt("cnt");
				coffeePrice[count] = rs.getInt("price");				
				count++;
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException ee) {
			System.err.println("error = " + ee.toString());
		}
	}
}