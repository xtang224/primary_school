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

   //private String[] strs = new String[]{"一"; "二"; "三"; "四"; "五"; "六"; "七"; "八"; "九"; "十"; "百"; "千"; "万"; "亿"; "零"};
   private String[] strs = new String[]{"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "百", "千", "万", "亿", "位", "零", "平", "方", "米", "分", "厘", "豪", "公" ,"顷", "锐", "直", "钝", "周", "角", "射", "线", "段", "顶", "端", "点", "边", "度", "数", "量", "器", "<", "=", ">", "/", "_", "°", "单", "价", "速", "时", "间", "总", "路", "程", "X", "不", "变", "相", "同", "等", "秒", "没", "有", "无", "小", "乌", "龟", "大", "白", "兔", "只", "天", "次", "对", "个", "多", "种", "粒", "约", "达", "计", "级", "读", "限", "的", "阿", "拉", "伯", "自", "然", "整", "条", "算", "筹", "盘", "英", "国", "欧", "洲", "电", "子", "机", "台", "式", "笔", "记", "本", "板", "行", "互", "垂", "交", "于", "∥", "⊥","别","容","易","形","腰","梯","长","正","上","下","底","高","够","除","以","甲","乙","车","也","÷"};

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

      //status_One = new JRadioButton("一");   
      status_One = new JButton("一");   
      status_One.setActionCommand("一");
      status_One.addActionListener(this);

      status_Two = new JButton("二");
      status_Two.setActionCommand("二");
      status_Two.addActionListener(this);

      status_Three = new JButton("三");
      status_Three.setActionCommand("三");
      status_Three.addActionListener(this);

      status_Four = new JButton("四");
      status_Four.setActionCommand("四");
      status_Four.addActionListener(this);

      status_Five = new JButton("五");
      status_Five.setActionCommand("五");
      status_Five.addActionListener(this);

      status_Six = new JButton("六");
      status_Six.setActionCommand("六");
      status_Six.addActionListener(this);

      status_Seven = new JButton("七");
      status_Seven.setActionCommand("七");
      status_Seven.addActionListener(this);

      status_Eight = new JButton("八");
      status_Eight.setActionCommand("八");
      status_Eight.addActionListener(this);

      status_Nine = new JButton("九");
      status_Nine.setActionCommand("九");
      status_Nine.addActionListener(this);

      status_Ten = new JButton("十");
      status_Ten.setActionCommand("十");
      status_Ten.addActionListener(this);

      status_Hundred = new JButton("百");
      status_Hundred.setActionCommand("百");
      status_Hundred.addActionListener(this);

      status_Thousand = new JButton("千");
      status_Thousand.setActionCommand("千");
      status_Thousand.addActionListener(this);

      status_Wan = new JButton("万");
      status_Wan.setActionCommand("万");
      status_Wan.addActionListener(this);

      status_Yi = new JButton("亿");
      status_Yi.setActionCommand("亿");
      status_Yi.addActionListener(this);

      status_Wei = new JButton("位");
      status_Wei.setActionCommand("位");
      status_Wei.addActionListener(this);

      status_Zero = new JButton("零");
      status_Zero.setActionCommand("零");
      status_Zero.addActionListener(this); 

      status_Ping = new JButton("平");
      status_Ping.setActionCommand("平");
      status_Ping.addActionListener(this);

      status_Fang = new JButton("方");
      status_Fang.setActionCommand("方");
      status_Fang.addActionListener(this);

      status_Mi = new JButton("米");
      status_Mi.setActionCommand("米");
      status_Mi.addActionListener(this);

      status_Fen = new JButton("分");
      status_Fen.setActionCommand("分");
      status_Fen.addActionListener(this);

      status_Li = new JButton("厘");
      status_Li.setActionCommand("厘");
      status_Li.addActionListener(this);

      status_Hao = new JButton("豪");
      status_Hao.setActionCommand("豪");
      status_Hao.addActionListener(this);

      status_Gong = new JButton("公");
      status_Gong.setActionCommand("公");
      status_Gong.addActionListener(this);

      status_Qing = new JButton("顷");
      status_Qing.setActionCommand("顷");
      status_Qing.addActionListener(this); 
   
      status_Rui = new JButton("锐");
      status_Rui.setActionCommand("锐");
      status_Rui.addActionListener(this);

      status_Zhi = new JButton("直");
      status_Zhi.setActionCommand("直");
      status_Zhi.addActionListener(this);

      status_Dun = new JButton("钝");
      status_Dun.setActionCommand("钝");
      status_Dun.addActionListener(this);

      status_Zhou = new JButton("周");
      status_Zhou.setActionCommand("周");
      status_Zhou.addActionListener(this);

      status_Jiao = new JButton("角");
      status_Jiao.setActionCommand("角");
      status_Jiao.addActionListener(this);

      status_She = new JButton("射");
      status_She.setActionCommand("射");
      status_She.addActionListener(this);

      status_Xian = new JButton("线");
      status_Xian.setActionCommand("线");
      status_Xian.addActionListener(this);

      status_Duan = new JButton("段");
      status_Duan.setActionCommand("段");
      status_Duan.addActionListener(this);  

      status_Ding = new JButton("顶");
      status_Ding.setActionCommand("顶");
      status_Ding.addActionListener(this);

      status_Duan2 = new JButton("端");
      status_Duan2.setActionCommand("端");
      status_Duan2.addActionListener(this);

      status_Dian = new JButton("点");
      status_Dian.setActionCommand("点");
      status_Dian.addActionListener(this);

      status_Bian = new JButton("边");
      status_Bian.setActionCommand("边");
      status_Bian.addActionListener(this);

      status_Du = new JButton("度");
      status_Du.setActionCommand("度");
      status_Du.addActionListener(this);

      status_Shu = new JButton("数");
      status_Shu.setActionCommand("数");
      status_Shu.addActionListener(this);

      status_Liang = new JButton("量");
      status_Liang.setActionCommand("量");
      status_Liang.addActionListener(this);

      status_Qi = new JButton("器");
      status_Qi.setActionCommand("器");
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

      status_Angle = new JButton("°");
      status_Angle.setActionCommand("°");
      status_Angle.addActionListener(this);      

      status_Dan = new JButton("单");
      status_Dan.setActionCommand("单");
      status_Dan.addActionListener(this);

      status_Jia = new JButton("价");
      status_Jia.setActionCommand("价");
      status_Jia.addActionListener(this);

      status_Su = new JButton("速");
      status_Su.setActionCommand("速");
      status_Su.addActionListener(this);

      status_Shi = new JButton("时");
      status_Shi.setActionCommand("时");
      status_Shi.addActionListener(this);

      status_Jian = new JButton("间");
      status_Jian.setActionCommand("间");
      status_Jian.addActionListener(this);

      status_Zong = new JButton("总");
      status_Zong.setActionCommand("总");
      status_Zong.addActionListener(this);

      status_Lu = new JButton("路");
      status_Lu.setActionCommand("路");
      status_Lu.addActionListener(this);

      status_Cheng = new JButton("程");
      status_Cheng.setActionCommand("程");
      status_Cheng.addActionListener(this);

      status_Multiply = new JButton("X");
      status_Multiply.setActionCommand("X");
      status_Multiply.addActionListener(this);

      status_Bu = new JButton("不");
      status_Bu.setActionCommand("不");
      status_Bu.addActionListener(this);

      status_Bian2 = new JButton("变");
      status_Bian2.setActionCommand("变");
      status_Bian2.addActionListener(this);

      status_Xiang = new JButton("相");
      status_Xiang.setActionCommand("相");
      status_Xiang.addActionListener(this);

      status_Tong = new JButton("同");
      status_Tong.setActionCommand("同");
      status_Tong.addActionListener(this);

      status_Deng = new JButton("等");
      status_Deng.setActionCommand("等");
      status_Deng.addActionListener(this);

      status_Miao = new JButton("秒");
      status_Miao.setActionCommand("秒");
      status_Miao.addActionListener(this);

      status_Mei = new JButton("没");
      status_Mei.setActionCommand("没");
      status_Mei.addActionListener(this);

      status_You = new JButton("有");
      status_You.setActionCommand("有");
      status_You.addActionListener(this);
      
      status_Wu2 = new JButton("无");
      status_Wu2.setActionCommand("无");
      status_Wu2.addActionListener(this);

      status_Xiao = new JButton("小");
      status_Xiao.setActionCommand("小");
      status_Xiao.addActionListener(this);

      status_Wu3 = new JButton("乌");
      status_Wu3.setActionCommand("乌");
      status_Wu3.addActionListener(this);

      status_Gui = new JButton("龟");
      status_Gui.setActionCommand("龟");
      status_Gui.addActionListener(this);

      status_Da = new JButton("大");
      status_Da.setActionCommand("大");
      status_Da.addActionListener(this);

      status_Bai = new JButton("白");
      status_Bai.setActionCommand("白");
      status_Bai.addActionListener(this);

      status_Tu = new JButton("兔");
      status_Tu.setActionCommand("兔");
      status_Tu.addActionListener(this);

      status_Zhi2 = new JButton("只");
      status_Zhi2.setActionCommand("只");
      status_Zhi2.addActionListener(this);

      status_Tian = new JButton("天");
      status_Tian.setActionCommand("天");
      status_Tian.addActionListener(this);

      status_Ci = new JButton("次");
      status_Ci.setActionCommand("次");
      status_Ci.addActionListener(this);

      status_Dui = new JButton("对");
      status_Dui.setActionCommand("对");
      status_Dui.addActionListener(this);

      status_Ge = new JButton("个");
      status_Ge.setActionCommand("个");
      status_Ge.addActionListener(this);

      status_Duo = new JButton("多");
      status_Duo.setActionCommand("多");
      status_Duo.addActionListener(this);

      status_Zhong = new JButton("种");
      status_Zhong.setActionCommand("种");
      status_Zhong.addActionListener(this);

      status_Li2 = new JButton("粒");
      status_Li2.setActionCommand("粒");
      status_Li2.addActionListener(this);

      status_Yue = new JButton("约");
      status_Yue.setActionCommand("约");
      status_Yue.addActionListener(this);

      status_Da2 = new JButton("达");
      status_Da2.setActionCommand("达");
      status_Da2.addActionListener(this);

      status_Ji = new JButton("计");
      status_Ji.setActionCommand("计");
      status_Ji.addActionListener(this);

      status_Ji2 = new JButton("级");
      status_Ji2.setActionCommand("级");
      status_Ji2.addActionListener(this);

      status_Du2 = new JButton("读");
      status_Du2.setActionCommand("读");
      status_Du2.addActionListener(this);

      status_Xian2 = new JButton("限");
      status_Xian2.setActionCommand("限");
      status_Xian2.addActionListener(this);

      status_De = new JButton("的");
      status_De.setActionCommand("的");
      status_De.addActionListener(this);

      status_A = new JButton("阿");
      status_A.setActionCommand("阿");
      status_A.addActionListener(this);

      status_La = new JButton("拉");
      status_La.setActionCommand("拉");
      status_La.addActionListener(this);

      status_Bo = new JButton("伯");
      status_Bo.setActionCommand("伯");
      status_Bo.addActionListener(this);

      status_Zi = new JButton("自");
      status_Zi.setActionCommand("自");
      status_Zi.addActionListener(this);

      status_Ran = new JButton("然");
      status_Ran.setActionCommand("然");
      status_Ran.addActionListener(this);

      status_Zheng = new JButton("整");
      status_Zheng.setActionCommand("整");
      status_Zheng.addActionListener(this);

      status_Tiao = new JButton("条");
      status_Tiao.setActionCommand("条");
      status_Tiao.addActionListener(this);

      status_Suan = new JButton("算");
      status_Suan.setActionCommand("算");
      status_Suan.addActionListener(this);

      status_Chou = new JButton("筹");
      status_Chou.setActionCommand("筹");
      status_Chou.addActionListener(this);

      status_Pan = new JButton("盘");
      status_Pan.setActionCommand("盘");
      status_Pan.addActionListener(this);

      status_Ying = new JButton("英");
      status_Ying.setActionCommand("英");
      status_Ying.addActionListener(this);

      status_Guo = new JButton("国");
      status_Guo.setActionCommand("国");
      status_Guo.addActionListener(this);

      status_Ou = new JButton("欧");
      status_Ou.setActionCommand("欧");
      status_Ou.addActionListener(this);

      status_Zhou2 = new JButton("洲");
      status_Zhou2.setActionCommand("洲");
      status_Zhou2.addActionListener(this);

      status_Dian2 = new JButton("电");
      status_Dian2.setActionCommand("电");
      status_Dian2.addActionListener(this);

      status_Zi2 = new JButton("子");
      status_Zi2.setActionCommand("子");
      status_Zi2.addActionListener(this);
 
      status_Ji3 = new JButton("机");
      status_Ji3.setActionCommand("机");
      status_Ji3.addActionListener(this);

      status_Tai = new JButton("台");
      status_Tai.setActionCommand("台");
      status_Tai.addActionListener(this);

      status_Shi2 = new JButton("式");
      status_Shi2.setActionCommand("式");
      status_Shi2.addActionListener(this);

      status_Bi = new JButton("笔");
      status_Bi.setActionCommand("笔");
      status_Bi.addActionListener(this);

      status_Ji4 = new JButton("记");
      status_Ji4.setActionCommand("记");
      status_Ji4.addActionListener(this);

      status_Ben = new JButton("本");
      status_Ben.setActionCommand("本");
      status_Ben.addActionListener(this);

      status_Ban = new JButton("板");
      status_Ban.setActionCommand("板");
      status_Ban.addActionListener(this);

      status_Xing = new JButton("行");
      status_Xing.setActionCommand("行");
      status_Xing.addActionListener(this);

      status_Hu = new JButton("互");
      status_Hu.setActionCommand("互");
      status_Hu.addActionListener(this);

      status_Chui = new JButton("垂");
      status_Chui.setActionCommand("垂");
      status_Chui.addActionListener(this);

      status_Jiao2 = new JButton("交");
      status_Jiao2.setActionCommand("交");
      status_Jiao2.addActionListener(this);  

      status_Yu = new JButton("于");
      status_Yu.setActionCommand("于");
      status_Yu.addActionListener(this);

      status_PingXing = new JButton("∥");
      status_PingXing.setActionCommand("∥");
      status_PingXing.addActionListener(this);

      status_ChuiZhi = new JButton("⊥");
      status_ChuiZhi.setActionCommand("⊥");
      status_ChuiZhi.addActionListener(this);

      status_Bie = new JButton("别");
      status_Bie.setActionCommand("别");
      status_Bie.addActionListener(this);

      status_Rong = new JButton("容");
      status_Rong.setActionCommand("容");
      status_Rong.addActionListener(this);

      status_Yi2 = new JButton("易");
      status_Yi2.setActionCommand("易");
      status_Yi2.addActionListener(this);

      status_Xing2 = new JButton("形");
      status_Xing2.setActionCommand("形");
      status_Xing2.addActionListener(this);

      status_Yao = new JButton("腰");
      status_Yao.setActionCommand("腰");
      status_Yao.addActionListener(this);

      status_Ti = new JButton("梯");
      status_Ti.setActionCommand("梯");
      status_Ti.addActionListener(this);

      status_Chang = new JButton("长");
      status_Chang.setActionCommand("长");
      status_Chang.addActionListener(this);

      status_Zheng2 = new JButton("正");
      status_Zheng2.setActionCommand("正");
      status_Zheng2.addActionListener(this);

      status_Shang = new JButton("上");
      status_Shang.setActionCommand("上");
      status_Shang.addActionListener(this);

      status_Xia = new JButton("下");
      status_Xia.setActionCommand("下");
      status_Xia.addActionListener(this);

      status_Di = new JButton("底");
      status_Di.setActionCommand("底");
      status_Di.addActionListener(this);

      status_Gao = new JButton("高");
      status_Gao.setActionCommand("高");
      status_Gao.addActionListener(this);

      status_Gou = new JButton("够");
      status_Gou.setActionCommand("够");
      status_Gou.addActionListener(this);

      status_Chu = new JButton("除");
      status_Chu.setActionCommand("除");
      status_Chu.addActionListener(this);

      status_Yi3 = new JButton("以");
      status_Yi3.setActionCommand("以");
      status_Yi3.addActionListener(this); 

      status_Jia2 = new JButton("甲");
      status_Jia2.setActionCommand("甲");
      status_Jia2.addActionListener(this);

      status_Yi4 = new JButton("乙");
      status_Yi4.setActionCommand("乙");
      status_Yi4.addActionListener(this); 

      status_Che = new JButton("车");
      status_Che.setActionCommand("车");
      status_Che.addActionListener(this); 

      status_Ye = new JButton("也");
      status_Ye.setActionCommand("也");
      status_Ye.addActionListener(this);

      status_Chu2 = new JButton("÷");
      status_Chu2.setActionCommand("÷");
      status_Chu2.addActionListener(this);

      status_Delete = new JButton("<-");
      status_Delete.setActionCommand("<-");
      status_Delete.addActionListener(this);

      buttonDone = new JButton("第一个括号输入结束");
      buttonDone.setActionCommand("第一个括号输入结束");
      buttonDone.addActionListener(this);

      buttonDone2 = new JButton("第二个括号输入结束");
      buttonDone2.setActionCommand("第二个括号输入结束");
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
      if (command.equals("第一个括号输入结束")){
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
      if (command.equals("第二个括号输入结束")){
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




