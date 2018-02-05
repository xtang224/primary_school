/**
   @version 1.30 2000-05-12
   @author Cay Horstmann
*/

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.sql.*;

import javax.swing.text.*;
import javax.swing.undo.*;
import javax.swing.event.*;
import javax.swing.*;

public class InputApplet2 extends JApplet
{  
   public void init()
   {  
      Container contentPane = getContentPane();
      contentPane.add(new InputPanel());
   }
}

/**
   A panel with calculator buttons and a result display.
   (Unchanged from chapter 9.)
*/
class InputPanel extends JPanel implements ActionListener
{  
   //private Editor display;
   private Editor display;
   private JPanel panel;
   private JPanel buttonPanel;
   //private ButtonGroup  status_group = null;
   //private JRadioButton status_One = null;
   private JButton status_One = null;
   private JButton status_Two = null;
   private JButton status_Three = null;
   private JButton status_Four = null;
   private JButton status_Five = null;
   private JButton status_Six = null;
   private JButton status_Seven = null;
   private JButton status_Eight = null;
   private JButton status_Nine = null;
   private JButton status_Ten = null;
   private JButton status_Hundred = null;
   private JButton status_Thousand = null;
   private JButton status_Wan = null;
   private JButton status_Yi = null;
   private JButton status_Wei = null;
   private JButton status_Zero = null;

   private JButton status_Ping = null;  
   private JButton status_Fang = null;  
   private JButton status_Mi = null;
   private JButton status_Fen = null;
   private JButton status_Li = null;
   private JButton status_Hao = null;
   private JButton status_Gong = null;
   private JButton status_Qing = null;

   private JButton status_Rui = null;
   private JButton status_Zhi = null;
   private JButton status_Dun = null;
   private JButton status_Zhou = null;
   private JButton status_Jiao = null;
   private JButton status_She = null;
   private JButton status_Xian = null;
   private JButton status_Duan = null;
   private JButton status_Ding = null;
   private JButton status_Duan2 = null;
   private JButton status_Dian = null;
   private JButton status_Bian = null;
   private JButton status_Du = null; 
   private JButton status_Shu = null;
   private JButton status_Liang = null;
   private JButton status_Qi = null;
   private JButton status_Small = null;
   private JButton status_Equal = null;
   private JButton status_Large = null;
   private JButton status_Slash = null;
   private JButton status_Underline = null;
   private JButton status_Angle = null;

   private JButton status_Dan = null;
   private JButton status_Jia = null;
   private JButton status_Su = null;
   private JButton status_Shi = null;
   private JButton status_Jian = null;
   private JButton status_Zong = null;
   private JButton status_Lu = null;
   private JButton status_Cheng = null;
   private JButton status_Multiply = null;

   private JButton status_Bu = null;
   private JButton status_Bian2 = null;  
   private JButton status_Xiang = null;
   private JButton status_Tong = null;
   private JButton status_Deng = null;
   private JButton status_Miao = null;
   private JButton status_Mei = null;
   private JButton status_You = null;
   private JButton status_Wu2 = null;

   private JButton status_Xiao = null;
   private JButton status_Wu3 = null;
   private JButton status_Gui = null;
   private JButton status_Da = null;
   private JButton status_Bai = null;
   private JButton status_Tu = null;

   private JButton status_Zhi2 = null;
   private JButton status_Tian = null;
   private JButton status_Ci = null;
   private JButton status_Dui = null;
   private JButton status_Ge = null; 
   private JButton status_Duo = null;
   private JButton status_Zhong = null;
   private JButton status_Li2 = null;
   private JButton status_Yue = null;
   private JButton status_Da2 = null;

   private JButton status_Ji = null;
   private JButton status_Ji2 = null;
   private JButton status_Du2 = null;   

   private JButton status_Xian2 = null;
   private JButton status_De = null;
   private JButton status_A = null;
   private JButton status_La = null;
   private JButton status_Bo = null;
   private JButton status_Zi = null;
   private JButton status_Ran = null;
   private JButton status_Zheng = null;

   private JButton status_Tiao = null;

   private JButton status_Suan = null;
   private JButton status_Chou = null;
   private JButton status_Pan = null;
   private JButton status_Ying = null;
   private JButton status_Guo = null;
   private JButton status_Ou = null; 
   private JButton status_Zhou2 = null;
   private JButton status_Dian2 = null;
   private JButton status_Zi2 = null;
   private JButton status_Ji3 = null;
   private JButton status_Tai = null;
   private JButton status_Shi2 = null;
   private JButton status_Bi = null; 
   private JButton status_Ji4 = null;
   private JButton status_Ben = null;
   private JButton status_Ban = null;  

   private JButton status_Xing = null;
   private JButton status_Hu = null;   
   private JButton status_Chui = null;
   private JButton status_Jiao2 = null;

   private JButton status_Yu = null;
   private JButton status_PingXing = null;
   private JButton status_ChuiZhi = null;

   private JButton status_Bie = null;
   private JButton status_Rong = null;
   private JButton status_Yi2 = null;
   private JButton status_Xing2 = null;

   private JButton status_Yao = null;
   private JButton status_Ti = null;
   private JButton status_Chang = null;
   private JButton status_Zheng2 = null;

   private JButton status_Shang = null;
   private JButton status_Xia = null;
   private JButton status_Di = null;
   private JButton status_Gao = null;

   private JButton status_Gou = null;
   private JButton status_Chu = null;
 
   private JButton status_Yi3 = null;
   private JButton status_Jia2 = null;
   private JButton status_Yi4 = null;
   private JButton status_Che = null;
   private JButton status_Ye = null;

   private JButton status_Chu2 = null;

   private JButton status_Delete = null;

   private JButton buttonDone = null;
   private JButton buttonDone2 = null;

   //private String[] strs = new String[]{"һ"; "��"; "��"; "��"; "��"; "��"; "��"; "��"; "��"; "ʮ"; "��"; "ǧ"; "��"; "��"; "��"};
   private String[] strs = new String[]{"һ", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ", "��", "ǧ", "��", "��", "λ", "��", "ƽ", "��", "��", "��", "��", "��", "��" ,"��", "��", "ֱ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "<", "=", ">", "/", "_", "��", "��", "��", "��", "ʱ", "��", "��", "·", "��", "X", "��", "��", "��", "ͬ", "��", "��", "û", "��", "��", "С", "��", "��", "��", "��", "��", "ֻ", "��", "��", "��", "��", "��", "��", "��", "Լ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "Ȼ", "��", "��", "��", "��", "��", "Ӣ", "��", "ŷ", "��", "��", "��", "��", "̨", "ʽ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","��","Ҳ","��"};

   Connection dbCon = null;
   ResultSetMetaData rsmd = null;
   PreparedStatement ptmt = null;
   ResultSet rs = null;
 
   public InputPanel()
   {  
      setLayout(new BorderLayout());
      
      // add the display
      //display = new JTextField("0");
      display = new Editor();
      //display.setEditable(true);
      add(display, BorderLayout.NORTH);     
      
      // add the buttons in a n x 1 grid
      panel = new JPanel();
      panel.setLayout(new GridLayout(8, 20));

      //status_One = new JRadioButton("һ");   
      status_One = new JButton("һ");   
      status_One.setActionCommand("һ");
      status_One.addActionListener(this);

      status_Two = new JButton("��");
      status_Two.setActionCommand("��");
      status_Two.addActionListener(this);

      status_Three = new JButton("��");
      status_Three.setActionCommand("��");
      status_Three.addActionListener(this);

      status_Four = new JButton("��");
      status_Four.setActionCommand("��");
      status_Four.addActionListener(this);

      status_Five = new JButton("��");
      status_Five.setActionCommand("��");
      status_Five.addActionListener(this);

      status_Six = new JButton("��");
      status_Six.setActionCommand("��");
      status_Six.addActionListener(this);

      status_Seven = new JButton("��");
      status_Seven.setActionCommand("��");
      status_Seven.addActionListener(this);

      status_Eight = new JButton("��");
      status_Eight.setActionCommand("��");
      status_Eight.addActionListener(this);

      status_Nine = new JButton("��");
      status_Nine.setActionCommand("��");
      status_Nine.addActionListener(this);

      status_Ten = new JButton("ʮ");
      status_Ten.setActionCommand("ʮ");
      status_Ten.addActionListener(this);

      status_Hundred = new JButton("��");
      status_Hundred.setActionCommand("��");
      status_Hundred.addActionListener(this);

      status_Thousand = new JButton("ǧ");
      status_Thousand.setActionCommand("ǧ");
      status_Thousand.addActionListener(this);

      status_Wan = new JButton("��");
      status_Wan.setActionCommand("��");
      status_Wan.addActionListener(this);

      status_Yi = new JButton("��");
      status_Yi.setActionCommand("��");
      status_Yi.addActionListener(this);

      status_Wei = new JButton("λ");
      status_Wei.setActionCommand("λ");
      status_Wei.addActionListener(this);

      status_Zero = new JButton("��");
      status_Zero.setActionCommand("��");
      status_Zero.addActionListener(this); 

      status_Ping = new JButton("ƽ");
      status_Ping.setActionCommand("ƽ");
      status_Ping.addActionListener(this);

      status_Fang = new JButton("��");
      status_Fang.setActionCommand("��");
      status_Fang.addActionListener(this);

      status_Mi = new JButton("��");
      status_Mi.setActionCommand("��");
      status_Mi.addActionListener(this);

      status_Fen = new JButton("��");
      status_Fen.setActionCommand("��");
      status_Fen.addActionListener(this);

      status_Li = new JButton("��");
      status_Li.setActionCommand("��");
      status_Li.addActionListener(this);

      status_Hao = new JButton("��");
      status_Hao.setActionCommand("��");
      status_Hao.addActionListener(this);

      status_Gong = new JButton("��");
      status_Gong.setActionCommand("��");
      status_Gong.addActionListener(this);

      status_Qing = new JButton("��");
      status_Qing.setActionCommand("��");
      status_Qing.addActionListener(this); 
   
      status_Rui = new JButton("��");
      status_Rui.setActionCommand("��");
      status_Rui.addActionListener(this);

      status_Zhi = new JButton("ֱ");
      status_Zhi.setActionCommand("ֱ");
      status_Zhi.addActionListener(this);

      status_Dun = new JButton("��");
      status_Dun.setActionCommand("��");
      status_Dun.addActionListener(this);

      status_Zhou = new JButton("��");
      status_Zhou.setActionCommand("��");
      status_Zhou.addActionListener(this);

      status_Jiao = new JButton("��");
      status_Jiao.setActionCommand("��");
      status_Jiao.addActionListener(this);

      status_She = new JButton("��");
      status_She.setActionCommand("��");
      status_She.addActionListener(this);

      status_Xian = new JButton("��");
      status_Xian.setActionCommand("��");
      status_Xian.addActionListener(this);

      status_Duan = new JButton("��");
      status_Duan.setActionCommand("��");
      status_Duan.addActionListener(this);  

      status_Ding = new JButton("��");
      status_Ding.setActionCommand("��");
      status_Ding.addActionListener(this);

      status_Duan2 = new JButton("��");
      status_Duan2.setActionCommand("��");
      status_Duan2.addActionListener(this);

      status_Dian = new JButton("��");
      status_Dian.setActionCommand("��");
      status_Dian.addActionListener(this);

      status_Bian = new JButton("��");
      status_Bian.setActionCommand("��");
      status_Bian.addActionListener(this);

      status_Du = new JButton("��");
      status_Du.setActionCommand("��");
      status_Du.addActionListener(this);

      status_Shu = new JButton("��");
      status_Shu.setActionCommand("��");
      status_Shu.addActionListener(this);

      status_Liang = new JButton("��");
      status_Liang.setActionCommand("��");
      status_Liang.addActionListener(this);

      status_Qi = new JButton("��");
      status_Qi.setActionCommand("��");
      status_Qi.addActionListener(this);     

      status_Small = new JButton("<");
      status_Small.setActionCommand("<");
      status_Small.addActionListener(this);

      status_Equal = new JButton("=");
      status_Equal.setActionCommand("=");
      status_Equal.addActionListener(this);

      status_Large = new JButton(">");
      status_Large.setActionCommand(">");
      status_Large.addActionListener(this); 

      status_Slash = new JButton("/");
      status_Slash.setActionCommand("/");
      status_Slash.addActionListener(this);
       
      status_Underline = new JButton("_");
      status_Underline.setActionCommand("_");
      status_Underline.addActionListener(this); 

      status_Angle = new JButton("��");
      status_Angle.setActionCommand("��");
      status_Angle.addActionListener(this);      

      status_Dan = new JButton("��");
      status_Dan.setActionCommand("��");
      status_Dan.addActionListener(this);

      status_Jia = new JButton("��");
      status_Jia.setActionCommand("��");
      status_Jia.addActionListener(this);

      status_Su = new JButton("��");
      status_Su.setActionCommand("��");
      status_Su.addActionListener(this);

      status_Shi = new JButton("ʱ");
      status_Shi.setActionCommand("ʱ");
      status_Shi.addActionListener(this);

      status_Jian = new JButton("��");
      status_Jian.setActionCommand("��");
      status_Jian.addActionListener(this);

      status_Zong = new JButton("��");
      status_Zong.setActionCommand("��");
      status_Zong.addActionListener(this);

      status_Lu = new JButton("·");
      status_Lu.setActionCommand("·");
      status_Lu.addActionListener(this);

      status_Cheng = new JButton("��");
      status_Cheng.setActionCommand("��");
      status_Cheng.addActionListener(this);

      status_Multiply = new JButton("X");
      status_Multiply.setActionCommand("X");
      status_Multiply.addActionListener(this);

      status_Bu = new JButton("��");
      status_Bu.setActionCommand("��");
      status_Bu.addActionListener(this);

      status_Bian2 = new JButton("��");
      status_Bian2.setActionCommand("��");
      status_Bian2.addActionListener(this);

      status_Xiang = new JButton("��");
      status_Xiang.setActionCommand("��");
      status_Xiang.addActionListener(this);

      status_Tong = new JButton("ͬ");
      status_Tong.setActionCommand("ͬ");
      status_Tong.addActionListener(this);

      status_Deng = new JButton("��");
      status_Deng.setActionCommand("��");
      status_Deng.addActionListener(this);

      status_Miao = new JButton("��");
      status_Miao.setActionCommand("��");
      status_Miao.addActionListener(this);

      status_Mei = new JButton("û");
      status_Mei.setActionCommand("û");
      status_Mei.addActionListener(this);

      status_You = new JButton("��");
      status_You.setActionCommand("��");
      status_You.addActionListener(this);
      
      status_Wu2 = new JButton("��");
      status_Wu2.setActionCommand("��");
      status_Wu2.addActionListener(this);

      status_Xiao = new JButton("С");
      status_Xiao.setActionCommand("С");
      status_Xiao.addActionListener(this);

      status_Wu3 = new JButton("��");
      status_Wu3.setActionCommand("��");
      status_Wu3.addActionListener(this);

      status_Gui = new JButton("��");
      status_Gui.setActionCommand("��");
      status_Gui.addActionListener(this);

      status_Da = new JButton("��");
      status_Da.setActionCommand("��");
      status_Da.addActionListener(this);

      status_Bai = new JButton("��");
      status_Bai.setActionCommand("��");
      status_Bai.addActionListener(this);

      status_Tu = new JButton("��");
      status_Tu.setActionCommand("��");
      status_Tu.addActionListener(this);

      status_Zhi2 = new JButton("ֻ");
      status_Zhi2.setActionCommand("ֻ");
      status_Zhi2.addActionListener(this);

      status_Tian = new JButton("��");
      status_Tian.setActionCommand("��");
      status_Tian.addActionListener(this);

      status_Ci = new JButton("��");
      status_Ci.setActionCommand("��");
      status_Ci.addActionListener(this);

      status_Dui = new JButton("��");
      status_Dui.setActionCommand("��");
      status_Dui.addActionListener(this);

      status_Ge = new JButton("��");
      status_Ge.setActionCommand("��");
      status_Ge.addActionListener(this);

      status_Duo = new JButton("��");
      status_Duo.setActionCommand("��");
      status_Duo.addActionListener(this);

      status_Zhong = new JButton("��");
      status_Zhong.setActionCommand("��");
      status_Zhong.addActionListener(this);

      status_Li2 = new JButton("��");
      status_Li2.setActionCommand("��");
      status_Li2.addActionListener(this);

      status_Yue = new JButton("Լ");
      status_Yue.setActionCommand("Լ");
      status_Yue.addActionListener(this);

      status_Da2 = new JButton("��");
      status_Da2.setActionCommand("��");
      status_Da2.addActionListener(this);

      status_Ji = new JButton("��");
      status_Ji.setActionCommand("��");
      status_Ji.addActionListener(this);

      status_Ji2 = new JButton("��");
      status_Ji2.setActionCommand("��");
      status_Ji2.addActionListener(this);

      status_Du2 = new JButton("��");
      status_Du2.setActionCommand("��");
      status_Du2.addActionListener(this);

      status_Xian2 = new JButton("��");
      status_Xian2.setActionCommand("��");
      status_Xian2.addActionListener(this);

      status_De = new JButton("��");
      status_De.setActionCommand("��");
      status_De.addActionListener(this);

      status_A = new JButton("��");
      status_A.setActionCommand("��");
      status_A.addActionListener(this);

      status_La = new JButton("��");
      status_La.setActionCommand("��");
      status_La.addActionListener(this);

      status_Bo = new JButton("��");
      status_Bo.setActionCommand("��");
      status_Bo.addActionListener(this);

      status_Zi = new JButton("��");
      status_Zi.setActionCommand("��");
      status_Zi.addActionListener(this);

      status_Ran = new JButton("Ȼ");
      status_Ran.setActionCommand("Ȼ");
      status_Ran.addActionListener(this);

      status_Zheng = new JButton("��");
      status_Zheng.setActionCommand("��");
      status_Zheng.addActionListener(this);

      status_Tiao = new JButton("��");
      status_Tiao.setActionCommand("��");
      status_Tiao.addActionListener(this);

      status_Suan = new JButton("��");
      status_Suan.setActionCommand("��");
      status_Suan.addActionListener(this);

      status_Chou = new JButton("��");
      status_Chou.setActionCommand("��");
      status_Chou.addActionListener(this);

      status_Pan = new JButton("��");
      status_Pan.setActionCommand("��");
      status_Pan.addActionListener(this);

      status_Ying = new JButton("Ӣ");
      status_Ying.setActionCommand("Ӣ");
      status_Ying.addActionListener(this);

      status_Guo = new JButton("��");
      status_Guo.setActionCommand("��");
      status_Guo.addActionListener(this);

      status_Ou = new JButton("ŷ");
      status_Ou.setActionCommand("ŷ");
      status_Ou.addActionListener(this);

      status_Zhou2 = new JButton("��");
      status_Zhou2.setActionCommand("��");
      status_Zhou2.addActionListener(this);

      status_Dian2 = new JButton("��");
      status_Dian2.setActionCommand("��");
      status_Dian2.addActionListener(this);

      status_Zi2 = new JButton("��");
      status_Zi2.setActionCommand("��");
      status_Zi2.addActionListener(this);
 
      status_Ji3 = new JButton("��");
      status_Ji3.setActionCommand("��");
      status_Ji3.addActionListener(this);

      status_Tai = new JButton("̨");
      status_Tai.setActionCommand("̨");
      status_Tai.addActionListener(this);

      status_Shi2 = new JButton("ʽ");
      status_Shi2.setActionCommand("ʽ");
      status_Shi2.addActionListener(this);

      status_Bi = new JButton("��");
      status_Bi.setActionCommand("��");
      status_Bi.addActionListener(this);

      status_Ji4 = new JButton("��");
      status_Ji4.setActionCommand("��");
      status_Ji4.addActionListener(this);

      status_Ben = new JButton("��");
      status_Ben.setActionCommand("��");
      status_Ben.addActionListener(this);

      status_Ban = new JButton("��");
      status_Ban.setActionCommand("��");
      status_Ban.addActionListener(this);

      status_Xing = new JButton("��");
      status_Xing.setActionCommand("��");
      status_Xing.addActionListener(this);

      status_Hu = new JButton("��");
      status_Hu.setActionCommand("��");
      status_Hu.addActionListener(this);

      status_Chui = new JButton("��");
      status_Chui.setActionCommand("��");
      status_Chui.addActionListener(this);

      status_Jiao2 = new JButton("��");
      status_Jiao2.setActionCommand("��");
      status_Jiao2.addActionListener(this);  

      status_Yu = new JButton("��");
      status_Yu.setActionCommand("��");
      status_Yu.addActionListener(this);

      status_PingXing = new JButton("��");
      status_PingXing.setActionCommand("��");
      status_PingXing.addActionListener(this);

      status_ChuiZhi = new JButton("��");
      status_ChuiZhi.setActionCommand("��");
      status_ChuiZhi.addActionListener(this);

      status_Bie = new JButton("��");
      status_Bie.setActionCommand("��");
      status_Bie.addActionListener(this);

      status_Rong = new JButton("��");
      status_Rong.setActionCommand("��");
      status_Rong.addActionListener(this);

      status_Yi2 = new JButton("��");
      status_Yi2.setActionCommand("��");
      status_Yi2.addActionListener(this);

      status_Xing2 = new JButton("��");
      status_Xing2.setActionCommand("��");
      status_Xing2.addActionListener(this);

      status_Yao = new JButton("��");
      status_Yao.setActionCommand("��");
      status_Yao.addActionListener(this);

      status_Ti = new JButton("��");
      status_Ti.setActionCommand("��");
      status_Ti.addActionListener(this);

      status_Chang = new JButton("��");
      status_Chang.setActionCommand("��");
      status_Chang.addActionListener(this);

      status_Zheng2 = new JButton("��");
      status_Zheng2.setActionCommand("��");
      status_Zheng2.addActionListener(this);

      status_Shang = new JButton("��");
      status_Shang.setActionCommand("��");
      status_Shang.addActionListener(this);

      status_Xia = new JButton("��");
      status_Xia.setActionCommand("��");
      status_Xia.addActionListener(this);

      status_Di = new JButton("��");
      status_Di.setActionCommand("��");
      status_Di.addActionListener(this);

      status_Gao = new JButton("��");
      status_Gao.setActionCommand("��");
      status_Gao.addActionListener(this);

      status_Gou = new JButton("��");
      status_Gou.setActionCommand("��");
      status_Gou.addActionListener(this);

      status_Chu = new JButton("��");
      status_Chu.setActionCommand("��");
      status_Chu.addActionListener(this);

      status_Yi3 = new JButton("��");
      status_Yi3.setActionCommand("��");
      status_Yi3.addActionListener(this); 

      status_Jia2 = new JButton("��");
      status_Jia2.setActionCommand("��");
      status_Jia2.addActionListener(this);

      status_Yi4 = new JButton("��");
      status_Yi4.setActionCommand("��");
      status_Yi4.addActionListener(this); 

      status_Che = new JButton("��");
      status_Che.setActionCommand("��");
      status_Che.addActionListener(this); 

      status_Ye = new JButton("Ҳ");
      status_Ye.setActionCommand("Ҳ");
      status_Ye.addActionListener(this);

      status_Chu2 = new JButton("��");
      status_Chu2.setActionCommand("��");
      status_Chu2.addActionListener(this);

      status_Delete = new JButton("<-");
      status_Delete.setActionCommand("<-");
      status_Delete.addActionListener(this);

      buttonDone = new JButton("��һ�������������");
      buttonDone.setActionCommand("��һ�������������");
      buttonDone.addActionListener(this);

      buttonDone2 = new JButton("�ڶ��������������");
      buttonDone2.setActionCommand("�ڶ��������������");
      buttonDone2.addActionListener(this);
      /*
      status_group = new ButtonGroup();
      status_group.add(status_One);
      status_group.add(status_Two);   
      */
      panel.add(status_One);
      panel.add(status_Two);
      panel.add(status_Three);
      panel.add(status_Four);
      panel.add(status_Five);
      panel.add(status_Six);
      panel.add(status_Seven);
      panel.add(status_Eight);
      panel.add(status_Nine);
      panel.add(status_Ten);
      panel.add(status_Hundred);
      panel.add(status_Thousand);
      panel.add(status_Wan);
      panel.add(status_Yi);
      panel.add(status_Wei);
      panel.add(status_Zero);

      panel.add(status_Ping);
      panel.add(status_Fang);
      panel.add(status_Mi);
      panel.add(status_Fen);    
      panel.add(status_Li);    
      panel.add(status_Hao);    
      panel.add(status_Gong);
      panel.add(status_Qing);
     
      panel.add(status_Rui);
      panel.add(status_Zhi);
      panel.add(status_Dun);
      panel.add(status_Zhou);
      panel.add(status_Jiao);
      panel.add(status_She);
      panel.add(status_Xian);
      panel.add(status_Duan);
      panel.add(status_Ding);
      panel.add(status_Duan2);
      panel.add(status_Dian);
      panel.add(status_Bian);
      panel.add(status_Du);
      panel.add(status_Shu);
      panel.add(status_Liang);
      panel.add(status_Qi);

      panel.add(status_Small);
      panel.add(status_Equal);
      panel.add(status_Large);
      panel.add(status_Slash);
      panel.add(status_Underline);
      panel.add(status_Angle);     

      panel.add(status_Dan);
      panel.add(status_Jia);
      panel.add(status_Su);
      panel.add(status_Shi);
      panel.add(status_Jian);
      panel.add(status_Zong);
      panel.add(status_Lu);
      panel.add(status_Cheng);
      panel.add(status_Multiply);

      panel.add(status_Bu);
      panel.add(status_Bian2);
      panel.add(status_Xiang);
      panel.add(status_Tong);
      panel.add(status_Deng);
      panel.add(status_Miao);
      panel.add(status_Mei);
      panel.add(status_You);
      panel.add(status_Wu2);

      panel.add(status_Xiao);
      panel.add(status_Wu3);
      panel.add(status_Gui);
      panel.add(status_Da);
      panel.add(status_Bai);
      panel.add(status_Tu);

      panel.add(status_Zhi2);
      panel.add(status_Tian);
      panel.add(status_Ci);
      panel.add(status_Dui);
      panel.add(status_Ge);
      panel.add(status_Duo);
      panel.add(status_Zhong);
      panel.add(status_Li2);
      panel.add(status_Yue);
      panel.add(status_Da2);

      panel.add(status_Ji);
      panel.add(status_Ji2);
      panel.add(status_Du2);

      panel.add(status_Xian2);
      panel.add(status_De);
      panel.add(status_A);
      panel.add(status_La);
      panel.add(status_Bo);
      panel.add(status_Zi);
      panel.add(status_Ran);
      panel.add(status_Zheng);

      panel.add(status_Tiao);
      
      panel.add(status_Suan);
      panel.add(status_Chou);
      panel.add(status_Pan);
      panel.add(status_Ying);
      panel.add(status_Guo);
      panel.add(status_Ou);
      panel.add(status_Zhou2);
      panel.add(status_Dian2);
      panel.add(status_Zi2);
      panel.add(status_Ji3);
      panel.add(status_Tai);
      panel.add(status_Shi2);
      panel.add(status_Bi);
      panel.add(status_Ji4);
      panel.add(status_Ben);
      panel.add(status_Ban);

      panel.add(status_Xing);
      panel.add(status_Hu);
      panel.add(status_Chui);
      panel.add(status_Jiao2);
 
      panel.add(status_Yu);
      panel.add(status_PingXing);
      panel.add(status_ChuiZhi);

      panel.add(status_Bie);
      panel.add(status_Rong);
      panel.add(status_Yi2);
      panel.add(status_Xing2);

      panel.add(status_Yao);
      panel.add(status_Ti); 

      panel.add(status_Chang);
      panel.add(status_Zheng2);

      panel.add(status_Shang);
      panel.add(status_Xia);
      panel.add(status_Di);
      panel.add(status_Gao);

      panel.add(status_Gou);
      panel.add(status_Chu);

      panel.add(status_Yi3);
      panel.add(status_Jia2);
      panel.add(status_Yi4);
      panel.add(status_Che);
      panel.add(status_Ye);

      panel.add(status_Chu2);

      panel.add(status_Delete);

      add(panel, BorderLayout.CENTER);

      buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(1, 2));
      buttonPanel.add(buttonDone);
      buttonPanel.add(buttonDone2);

      add(buttonPanel, BorderLayout.SOUTH);
   }

   public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand(); 
      String tmpStr = "";
      for (int i=0; i<strs.length; i++){
         if (command.equals(strs[i])) {
            tmpStr = display.getText() + command;
     	    display.setText(tmpStr);
            System.out.println("result = " + display.getText());
         }
      }
      if (command.equals("<-")){
         tmpStr = display.getText();
         if (tmpStr.length() > 0){
            tmpStr = tmpStr.substring(0,tmpStr.length()-1);
            display.setText(tmpStr);
            System.out.println("result = " + display.getText());
         }
      }
      if (command.equals("��һ�������������")){
         tmpStr = display.getText();
         try{
            connect();                        
            ptmt = dbCon.prepareStatement("update hintMatch set term2=? where term1=?");
            ptmt.clearParameters();
            ptmt.setString(1, tmpStr); 
            ptmt.setString(2, "input");            
            ptmt.executeUpdate();
         }catch (SQLException ex){
            ex.printStackTrace();
         }catch (ClassNotFoundException ex){
            ex.printStackTrace();
         }finally{
            try{
               if (rs != null) rs.close();
               if (ptmt != null) ptmt.close();
               if (dbCon != null) dbCon.close();
            }catch (SQLException e2){
               e2.printStackTrace();
            }
         }
      }
      if (command.equals("�ڶ��������������")){
         tmpStr = display.getText();
         try{
            connect();                        
            ptmt = dbCon.prepareStatement("update hintMatch set term2=? where term1=?");
            ptmt.clearParameters();
            ptmt.setString(1, tmpStr); 
            ptmt.setString(2, "input2");            
            ptmt.executeUpdate();
         }catch (SQLException ex){
            ex.printStackTrace();
         }catch (ClassNotFoundException ex){
            ex.printStackTrace();
         }finally{
            try{
               if (rs != null) rs.close();
               if (ptmt != null) ptmt.close();
               if (dbCon != null) dbCon.close();
            }catch (SQLException e2){
               e2.printStackTrace();
            }
         }
      }
   }

   private boolean connect() throws ClassNotFoundException, SQLException
    {
       Class.forName("org.hsqldb.jdbcDriver");
       dbCon = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
       return true;     
    }   
}

   class Editor extends JTextArea implements MouseListener, CaretListener {
       private boolean currentWork = false;

       Editor(){
          super();
          //addMouseListener(this);
          //addCaretListener(this);
       }

       public void setCurrentWork(boolean currentWork){
          this.currentWork = currentWork;
       }

       public void mouseClicked(MouseEvent e){          
          
          Component component = e.getComponent();
          if (component instanceof JTextArea) {
             JTextArea area = (JTextArea)component;
             
          }
          /*
          String text = getText();     
          String text2 = text;     
          int pos = getCaretPosition();
          text2 = text2.substring(0,pos);
          int posLastReturn = text2.lastIndexOf("\n"); 
          String colText = "col = " + (pos-posLastReturn);         

          int count = 1;
          pos = text2.indexOf("\n");
          while(pos != -1){
             count ++;
             text2 = text2.substring(pos+1);
             pos = text2.indexOf("\n");
          }
          //System.out.println("Line number = " + count + "::" + colText);
          status.setText("Line number = " + count + "::" + colText);
          */
       }

       public void caretUpdate(CaretEvent e){ 
          String text = getText();       
          /*
          String text2 = text;
          int pos = getCaretPosition();
          text2 = text2.substring(0,pos);
          int posLastReturn = text2.lastIndexOf("\n"); 
          String colText = "col = " + (pos-posLastReturn);         

          int count = 1;
          pos = text2.indexOf("\n");
          while(pos != -1){
             count ++;
             text2 = text2.substring(pos+1);
             pos = text2.indexOf("\n");
          }
          //System.out.println("Line number = " + count + "::" + colText);
          status.setText("Line number = " + count + "::" + colText);
          */
       }

       public void mousePressed(MouseEvent e){

       }

       public void mouseEntered(MouseEvent e){}
       public void mouseExited(MouseEvent e){}       
       public void mouseReleased(MouseEvent e){}  
       
    }




