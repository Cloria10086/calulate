package BossZheng;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import BossZheng.Calulate;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("四则运算生成器");
		f.setSize(800,600);
		f.setLocation(200,200);
		f.setLayout(null);		
		f.setResizable(false);			
		File src = new File("./background.jpg");
		BackgroundPanel bgp=new BackgroundPanel((new ImageIcon("background.jpg")).getImage());
		bgp.setBounds(0,0,800,600);

		JLabel l= new JLabel("请选择生成标准:\n");
		l.setFont(new Font("宋体",Font.BOLD,25));
		l.setForeground(Color.white);
		l.setBounds(285,5,500,100);
		
		JButton b = new JButton("开始生成题目");
		b.setFont(new Font("宋体",Font.BOLD,20));
		b.setBounds(300, 500, 200, 30);   
		
		JCheckBox add,substract,multiply,divide,decimal,brackets;
		add = new JCheckBox("加");
		substract = new JCheckBox("减");
		multiply = new JCheckBox("乘");
		divide = new JCheckBox("除");
		decimal = new JCheckBox("小数点");
		brackets = new JCheckBox("括号");
		
		add.setBounds(100, 100, 50, 30);
		add.setFont(new Font("宋体",Font.BOLD,20));
		
		substract.setBounds(200, 100, 50, 30);
		substract.setFont(new Font("宋体",Font.BOLD,20));
		
		multiply.setBounds(300, 100, 50, 30);
		multiply.setFont(new Font("宋体",Font.BOLD,20));
		
		divide.setBounds(400, 100, 50, 30);
		divide.setFont(new Font("宋体",Font.BOLD,20));
		
		decimal.setBounds(500, 100, 100, 30);
		decimal.setFont(new Font("宋体",Font.BOLD,20));
		
		brackets.setBounds(650, 100, 100, 30);
		brackets.setFont(new Font("宋体",Font.BOLD,20));
		
		JTextArea ta = new JTextArea();
	    ta.setFont(new Font("宋体",Font.BOLD,25));
        ta.setPreferredSize(new Dimension(200,100));
        ta.setLineWrap(true);
        ta.setBounds(250,170,100,35);
        
        JTextArea tb = new JTextArea(375,175);
	    tb.setFont(new Font("宋体",Font.BOLD,25));
        tb.setPreferredSize(new Dimension(200,100));
        tb.setLineWrap(true);
        tb.setBounds(525,170,150,35);
        
        JTextArea ans = new JTextArea();
	    ans.setFont(new Font("宋体",Font.BOLD,15));
        ans.setPreferredSize(new Dimension(200,100));
        ans.setLineWrap(true);
        ans.setBounds(340,240,375,175);
        ans.setEditable(false);
     //   ans.setWrapStyleWord(true);
        
        JLabel j= new JLabel("生成的题目数:\n");
		j.setFont(new Font("宋体",Font.BOLD,20));
		j.setForeground(Color.white);
		j.setBounds(100,165,200,45);
		
		JLabel k= new JLabel("生成的最大数:\n");
		k.setFont(new Font("宋体",Font.BOLD,20));
		k.setForeground(Color.white);
		k.setBounds(375,165,200,45);
		
		JLabel text1= makeText(f,220,"备注:");
		JLabel text2= makeText(f,245,"题目数小于100的正整数");
		JLabel text3= makeText(f,270,"最大数不大于一万的正数");
		JLabel text4= makeText(f,295,"最少选择一个运算符");
		JLabel text5= makeText(f,320,"不要使用Tab切换");
		
		JComboBox cmb = new JComboBox();
		cmb.addItem("--请选择输出方式--");
		cmb.addItem("输出到文本文档");
		cmb.addItem("输出到PDF");
		cmb.setFont(new Font("宋体",Font.BOLD,15));
        cmb.setBounds(100,240,200,40);
		
        b.addActionListener(new  ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				 String number= ta.getText();
				 String maxx = tb.getText();
				 Boolean[] cal = new Boolean[7];
				 cal[1] = add.isSelected();
				 cal[2] = substract.isSelected();
				 cal[3] = multiply.isSelected();
				 cal[4] = divide.isSelected();
				 cal[5] = decimal.isSelected();
				 cal[6] = brackets.isSelected();
				 cmb.getSelectedIndex();
				 boolean isOk = true;
				 
				 if(number.equals("")||!number.matches("[0-9]+") ||(Integer.valueOf(number)>100||Integer.valueOf(number)<1)) {
					 
					 Font font  =  new  Font("黑体",0,17);
					 UIManager.put("Button.font",font);
					 UIManager.put("Label.font",font);				 
					 JOptionPane.showMessageDialog(f,"  题目数输入不合法！");
					 isOk = false;
				 }	
				 if(maxx.equals("")||!(maxx.matches("[0-9]+.[0-9]+?$")||maxx.matches("[0-9]+")) ||(Double.valueOf(maxx)<=0)) {
					 
					 Font font  =  new  Font("黑体",0,17);
					 UIManager.put("Button.font",font);
					 UIManager.put("Label.font",font);				 
					 JOptionPane.showMessageDialog(f,"  最大数输入不合法！");
					 isOk = false;
				 }
				 if(cmb.getSelectedIndex()==0) {
					 Font font  =  new  Font("黑体",0,17);
					 UIManager.put("Button.font",font);
					 UIManager.put("Label.font",font);				 
					 JOptionPane.showMessageDialog(f,"  请选择输出方式！");
					 isOk = false;
				 }
	             
				 boolean check = false;
				 for(int i=1;i<=4;++i)
					 if(cal[i])
						 check=true;
				 if(!check) {
					 Font font  =  new  Font("黑体",0,17);
					 UIManager.put("Button.font",font);
					 UIManager.put("Label.font",font);				 
					 JOptionPane.showMessageDialog(f,"  请至少选择一种运算符！");
					 isOk = false;
				 }
				 
	             try {
	            	 if(isOk)
	            	 	CreateSentence(Integer.valueOf(number),Integer.valueOf(maxx),cmb.getSelectedIndex(),cal,ans);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
			}
		});    
        JScrollPane js = new JScrollPane(ans);
        js.setHorizontalScrollBarPolicy(
        		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        		js.setVerticalScrollBarPolicy(
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        js.setVisible(true);

        js.setBounds(340,240,375,175);
        f.add(l);        f.add(b);              f.add(ta);		 
        f.add(j); 		f.add(k); f.add(tb);  f.add(cmb);
        f.add(add);f.add(substract);f.add(multiply);f.add(divide);
        f.add(decimal);f.add(brackets);  f.add(js); 
        f.add(text1);f.add(text2);f.add(text3);f.add(text4);f.add(text5);
        f.getContentPane(). add(bgp);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}//备注\n题目数小于100的正整数\n最大数为正数\n最少选择一个运算符
	public static JLabel makeText(JFrame f,int x,String s) {
		JLabel text= new JLabel(s);
		text.setFont(new Font("宋体",Font.BOLD,17));
		text.setForeground(Color.white);
		text.setBounds(100,x,200,200);
		return text;
	}
	public static void CreateSentence(Integer number,Integer maxx,Integer cmb,Boolean[] cal,JTextArea ans) throws IOException {
		//  生成number个算式，maxx最大数，cmb打印方式，cal的运算符
		ArrayList<StringBuffer> result = new ArrayList<>();
		ArrayList<Character> tmpCmb = new ArrayList<>();
		String cout="";
		for(int i=1;i<=4;++i) {
			if(cal[i]) {
				switch(i) {
				case 1:tmpCmb.add('＋');break;
				case 2:tmpCmb.add('－');break;
				case 3:tmpCmb.add('×');break;
				case 4:tmpCmb.add('÷');break;
				}
			}
		}
		Random r = new Random();
		for(int i=1;i<=number;++i) {
			StringBuffer tmp = new StringBuffer("");
			int length = r.nextInt(9)+2;
			ArrayList<Integer> num = new ArrayList<>();
			for(int j=1;j<=length;++j) 
				num.add((r.nextInt(maxx-1)+1));		
			if(cal[5])
				tmp.append(getDecimal(num.get(0)).toString());
			else
				tmp.append(num.get(0).toString());
			for(int j=1;j<length;++j) {
				tmp.append(tmpCmb.get(r.nextInt(tmpCmb.size())));
				if(cal[5])
					tmp.append(getDecimal(num.get(j)).toString());
				else
					tmp.append(num.get(j).toString());
			}
			if(cal[6]) {
				int st = r.nextInt(length/2);
				//System.out.println(st);
				int cnt = 0;
				for(int j=0;j<tmp.length();++j) {
					
					if(tmp.charAt(j)=='＋'||tmp.charAt(j)=='－'||tmp.charAt(j)=='×'||tmp.charAt(j)=='÷')
						cnt++;
					if(cnt == st) {
						if(cnt==0)
							tmp.insert(j, "(");
						else
							tmp.insert(j+1, "(");
						break;
					}
				}
				cnt = 0;
				st = r.nextInt(length-st-1);
				for(int j = tmp.length()-1;j>=0;--j) {
					if(tmp.charAt(j)=='＋'||tmp.charAt(j)=='－'||tmp.charAt(j)=='×'||tmp.charAt(j)=='÷')
						cnt++;
					if(cnt == st) {
						if(cnt==0)
							tmp.insert(j+1, ")");
						else
						    tmp.insert(j, ")");
						break;
					}
				}
			}
			tmp.append("=");
			result.add(tmp);
			System.out.println(tmp.toString());		
			cout+=tmp.toString()+'\n';		
					
		}
		ans.setText(cout);
		for(int i=0;i<result.size();++i) {
			for(int j = 0;j<result.get(i).length();++j) {
				if(result.get(i).charAt(j)=='＋')
					result.get(i).setCharAt(j,'+');
				if(result.get(i).charAt(j)=='－')
					result.get(i).setCharAt(j, '-');
				if(result.get(i).charAt(j)=='×')
					result.get(i).setCharAt(j, '*');
				if(result.get(i).charAt(j)=='÷')
					result.get(i).setCharAt(j, '/');
			}
			
				
		}
		File  file = new File("result.txt");
		BufferedWriter bf = new BufferedWriter(new FileWriter(file));
		for(StringBuffer q : result) {
			bf.write(q.toString());
			new Calulate();
			bf.write(q.toString()+Calulate. executeExpression(q.toString()));
		}
		
	}
	public static Double getDecimal(Integer x) {
		double a = x*1.0;
		double b = (new Random().nextInt(100)+1)/100.0;
		b = (int)(b*100)/100;
		return (Double)(a+b);
	}

}

class BackgroundPanel extends JPanel
{
	Image im;
	public BackgroundPanel(Image im)
	{
		this.im=im;
		this.setOpaque(true);
	}
	//Draw the back ground.
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
		
	}
}
