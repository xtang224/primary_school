package util;

import java.util.*;
import java.sql.*;
import java.io.*;

public class StringSetTransfer{   

   public int continueCorrect = 0;
   public int continueWrong = 0;
   public boolean neverHigh = true;

   public static char lastType = 'L';   
   public static boolean lastCorrect = false;
   public static int problem_low = 0;
   public static int correct_low = 0;
   public static int problem_middle = 0;
   public static int correct_middle = 0;
   public static int problem_high = 0;
   public static int correct_high = 0;

   public static HashSet hs_low = new HashSet();
   public static HashSet hs_middle = new HashSet();
   public static HashSet hs_high = new HashSet();
   public static int total = 0;
   public static HashSet hs_low_used = new HashSet();

   public static HashSet hs_low_tf = new HashSet();
   public static HashSet hs_middle_tf = new HashSet();
   public static HashSet hs_high_tf = new HashSet();
   public static int total_tf = 0;
   public static HashSet hs_low_tf_used = new HashSet();

   public static HashSet hs_low_fb = new HashSet();
   public static HashSet hs_middle_fb = new HashSet();
   public static HashSet hs_high_fb = new HashSet();
   public static int total_fb = 0;
   public static HashSet hs_middle_fb_used = new HashSet();

   public static HashSet mhs_low = new HashSet();
   public static HashSet mhs_middle = new HashSet();
   public static HashSet mhs_high = new HashSet();
   public static int mtotal = 0;
   public static HashSet mhs_high_used = new HashSet();

   public static HashSet hs_low_fdb = new HashSet();
   public static HashSet hs_middle_fdb = new HashSet();
   public static HashSet hs_high_fdb = new HashSet();
   public static int total_fdb = 0;
   public static HashSet hs_high_fdb_used = new HashSet();

   public static HashSet hs_high_ftb = new HashSet();
   public static int total_ftb = 0;
   public static HashSet hs_high_ftb_used = new HashSet();
   
   public static HashSet hs_high_fqb = new HashSet();
   public static int total_fqb = 0;
   public static HashSet hs_high_fqb_used = new HashSet();

   public static HashSet hs_middle_tm = new HashSet();
   public static HashSet hs_middle_tm_used = new HashSet();
   public static int total_tm = 0;

   public static HashSet hs_middle_tm2 = new HashSet();
   public static HashSet hs_middle_tm2_used = new HashSet();
   public static int total_tm2 = 0;

   public static HashSet hs_high_m = null;
   public static HashSet hs_high_m_used = null;
   public static int total_m = 0;

   public static HashSet hs_high_d = null;
   public static HashSet hs_high_d_used = null;
   public static int total_d = 0;

   public static HashSet hs_middle_pm = new HashSet();
   public static HashSet hs_middle_pm_used = new HashSet();
   public static int total_pm = 0;

   public static boolean tb133_start = false;
   public static boolean tb133_end = false;

   //below two field members are for problem.jsp and choice.jsp instead of smart ones
   public static HashSet hs_total = new HashSet();
   public static HashSet hs_total_tf = new HashSet();

   public static HashMap nameMap = new HashMap();

   private String userName = null;

   public static boolean init = false;

   public StringSetTransfer(String userName){
      this.userName = userName;
   }

   public String getUserName(){
      return userName;
   }

   public void setUserName(){
      this.userName = userName;
   }

   public static String setToString(HashSet hs){
      String ret = "";
      if (hs == null || hs.size() == 0){
         return ret;
      }
      Object[] obj = hs.toArray();
      for (int i=0; i<obj.length; i++){
         int value = ((Integer)obj[i]).intValue();
         ret += value + ";";
      }
      return ret;
   }

   public static HashSet stringToSet(String str){
      HashSet hs = new HashSet();
      if (str == null || str.equals("")){
         return hs;
      }
      StringTokenizer tokenizer = new StringTokenizer(str, ";", false);
      while (tokenizer.hasMoreTokens()){
          int value = Integer.parseInt(tokenizer.nextToken());
          Integer integer = new Integer(value);
          hs.add(integer);
      }
      return hs;
   }

   public char getNextType(char lastType, boolean lastCorrect, int continueRight, int continueWrong){
      char thisType = 'L';
      if (lastCorrect){
         switch (lastType){
            case 'L':
              thisType = 'M';
              break;
            case 'M':
              thisType = 'H';              
              break;
            case 'H':
              neverHigh = false;
              continueRight ++;
              if (continueRight == 3){
                 thisType = 'M';
                 continueRight = 0;
              }else{
                 thisType = 'H'; 
              }             
              break;
            default:
              thisType = 'L';
              break;
         }
      }else{
         switch (lastType){
            case 'L':
              continueWrong ++;
               if (continueWrong == 3){
                 thisType = 'M';
                 continueWrong = 0;
              }else{
                 thisType = 'L'; 
              }       
              break;
            case 'M':
              thisType = 'L';
              break;
            case 'H':
              neverHigh = false;
              thisType = 'M';
              break;
            default:
              thisType = 'L';
              break;
         }
      }
      return thisType;
   }

   public static int getRandomNumber(char type, HashSet usedHashSet){
      int id = 0;
      HashSet temp = null;
      Iterator iter = null;
      int index = 0;
      outer:
      while(true){
         switch (type){
            case 'L':
               if (hs_low.size()==0){
                  id = 0;
                  break outer; 
               }
               if (usedHashSet.containsAll(hs_low) && usedHashSet.containsAll(hs_low_used)){
                  id = 0;
                  break outer;
               }
               //temp = new HashSet(hs_low_used);
               //temp.removeAll(usedHashSet);
               //if (temp.size() == 0){//then we need to get id from hs_low
               //now we only want to get id from hs_low without using hs_low_used
                  temp = new HashSet(hs_low);
                  temp.removeAll(hs_low_used);
                  temp.removeAll(usedHashSet); 
                  if (temp.size() == 0){
                     id = 0;
                     break outer;
                  }
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
  System.out.println("inside StringSetTransfer, getRandomNumber(char type, HashSet usedHashSet), at char='L',  id = " + id);
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               /*               
               }else{//we can first get id from hs_low_used
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
  System.out.println("inside StringSetTransfer, getRandomNumber(char type, HashSet usedHashSet), at char='L',  id = " + id);
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }  
               */
               break;
            case 'M':
               if (usedHashSet.containsAll(hs_middle)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(hs_middle);
               temp.removeAll(usedHashSet);
               if (temp.size() == 0){
                  id = 0;
                  break outer;
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               break;
            case 'H':
               if (usedHashSet.containsAll(hs_high)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(hs_high);
               temp.removeAll(usedHashSet);
               if (temp.size() == 0){
                  id = 0;
                  break outer;
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               break;
            default:
               break;
         }

         if (usedHashSet.contains(new Integer(id))) continue;
         else break; 
      }
      return id;
   }

   public static int getRandomNumber_tf(char type, HashSet usedHashSet){
      int id = 0;
      HashSet temp = null;
      Iterator iter = null;
      int index = 0;
      outer:
      while(true){
         switch (type){
            case 'L':
               if (hs_low_tf.size()==0){
                  id = 0;
                  break outer; 
               }
               if (usedHashSet.containsAll(hs_low_tf) && usedHashSet.containsAll(hs_low_tf_used)){
                  id = 0;
                  break outer;
               }
               //temp = new HashSet(hs_low_tf_used);
               //temp.removeAll(usedHashSet);
               //if (temp.size() == 0){
               //now we only want to get id from hs_low_tf without using hs_low_tf_used
                  temp = new HashSet(hs_low_tf);
                  temp.removeAll(hs_low_tf_used);
                  temp.removeAll(usedHashSet); 
                  if (temp.size() == 0){
                     id = 0;
                     break outer;
                  }                                     
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
System.out.println("inside StringSetTransfer,getRandomNumber_tf(char type, HashSet usedHashSet),at char='L', id = " + id);
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               /*
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
System.out.println("inside StringSetTransfer,getRandomNumber_tf(char type, HashSet usedHashSet),at char='L', id = " + id);
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               */ 
               break;
            case 'M':
               if (usedHashSet.containsAll(hs_middle_tf)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(hs_middle_tf);
               temp.removeAll(usedHashSet);
               if (temp.size() == 0){
                  id = 0;
                  break outer;
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               break;
            case 'H':
               if (usedHashSet.containsAll(hs_high_tf)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(hs_high_tf);
               temp.removeAll(usedHashSet);
               if (temp.size() == 0){
                  id = 0;
                  break outer;
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               break;
            default:
               break;
         }

         if (usedHashSet.contains(new Integer(id))) continue;
         else break; 
      }
      return id;
   }

   public static int get_M_RandomNumber(char type, HashSet usedHashSet){
      int id = 0;
      HashSet temp = null;
      Iterator iter = null;
      int index = 0;
      outer:
      while(true){
         switch (type){
            case 'L':
               if (usedHashSet.containsAll(mhs_low)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(mhs_low);
               temp.removeAll(usedHashSet);
               if (temp.size() == 0){
                  id = 0;
                  break outer;
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        String info = "inside StringSetTransfer, get_M_RandomNumber(char type, HashSet usedHashSet), at char=L,  id = " + id;
                        System.out.println(info);
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               break;
            case 'M':
               if (usedHashSet.containsAll(mhs_middle)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(mhs_middle);
               temp.removeAll(usedHashSet);
               if (temp.size() == 0){
                  id = 0;
                  break outer;
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               break;
            case 'H':
               if (mhs_high.size()==0){
                  id = 0;
                  break outer; 
               } 
               if (usedHashSet.containsAll(mhs_high) && usedHashSet.containsAll(mhs_high_used)){
                  id = 0;
                  break outer;
               }
               //temp = new HashSet(mhs_high_used);
               //temp.removeAll(usedHashSet);
               //if (temp.size() == 0){
               //now we only want to get id from mhs_high without using mhs_high_used
                  temp = new HashSet(mhs_high);
                  temp.removeAll(mhs_high_used);
                  temp.removeAll(usedHashSet); 
                  if (temp.size() == 0){
                     id = 0;
                     break outer;
                  }       
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               /*
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               */
               break;
            default:
               break;
         }

         if (usedHashSet.contains(new Integer(id))) continue;
         else break; 
      }
      return id;
   }

   public static int getRandomNumber_fb(char type, HashSet usedHashSet){
      int id = 0;
      HashSet temp = null;
      Iterator iter = null;
      int index = 0;
      outer:
      while(true){
         switch (type){
            case 'L':
               if (usedHashSet.containsAll(hs_low_fb)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(hs_low_fb);
               temp.removeAll(usedHashSet);
               if (temp.size() == 0){
                  id = 0;
                  break outer;
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        System.out.println("inside StringSetTransfer, getRandomNumber_fb(char type, HashSet usedHashSet), at char='L',  id = " + id);
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               break;
            case 'M':
               if (hs_middle_fb.size()==0){
                  id = 0;
                  break outer; 
               } 
               if (usedHashSet.containsAll(hs_middle_fb) && usedHashSet.containsAll(hs_middle_fb_used)){
                  id = 0;
                  break outer;
               }
               //temp = new HashSet(hs_middle_fb_used);
               //temp.removeAll(usedHashSet);
               //if (temp.size() == 0){
               //now we only need to used hs_middle_fb without using hs_middle_fb_used
                  temp = new HashSet(hs_middle_fb);
                  temp.removeAll(hs_middle_fb_used); 
                  temp.removeAll(usedHashSet); 
                  if (temp.size() == 0){
                     id = 0;
                     break outer;
                  }
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               /*
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               */
               break;
            case 'H':
               if (hs_high_fb.size()==0){
                  id = 0;
                  break outer; 
               } 
               if (usedHashSet.containsAll(hs_high_fb)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(hs_high_fb);
               temp.removeAll(usedHashSet);
               if (temp.size() == 0){
                  id = 0;
                  break outer;
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               break;
            default:
               break;
         }

         if (usedHashSet.contains(new Integer(id))) continue;
         else break; 
      }
      return id;
   }

   public static int getRandomNumber_fdb(char type, HashSet usedHashSet){
      int id = 0;
      HashSet temp = null;
      Iterator iter = null;
      int index = 0;
      outer:
      while(true){
         switch (type){
            case 'L':
               if (usedHashSet.containsAll(hs_low_fdb)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(hs_low_fdb);
               temp.removeAll(usedHashSet);
               if (temp.size() == 0){
                  id = 0;
                  break outer;
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        System.out.println("inside StringSetTransfer, getRandomNumber_fdb(char type, HashSet usedHashSet), at char='L',  id = " + id);
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               break;
            case 'M':
               if (usedHashSet.containsAll(hs_middle_fdb)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(hs_middle_fdb);
               temp.removeAll(usedHashSet);
               if (temp.size() == 0){
                  id = 0;
                  break outer;
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               break;
            case 'H':
               if (hs_high_fdb.size()==0){
                  id = 0;
                  break outer; 
               }
               if (usedHashSet.containsAll(hs_high_fdb) && usedHashSet.containsAll(hs_high_fdb_used)){
                  id = 0;
                  break outer;
               }
               //temp = new HashSet(hs_high_fdb_used);
               //temp.removeAll(usedHashSet);
               //if (temp.size() == 0){
               //now we only want to use hs_high_fdb without using hs_high_fdb_used
                  temp = new HashSet(hs_high_fdb);
                  temp.removeAll(hs_high_fdb_used);
                  temp.removeAll(usedHashSet);
                  if (temp.size() == 0){
                     id = 0;
                     break outer;
                  }
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               /*
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               */
               break;
            default:
               break;
         }

         if (usedHashSet.contains(new Integer(id))) continue;
         else break; 
      }
      return id;
   }

   public static int getRandomNumber_fqb(char type, HashSet usedHashSet){
      int id = 0;
      HashSet temp = null;
      Iterator iter = null;
      int index = 0;
      outer:
      while(true){
         switch (type){
            case 'L':               
               break;
            case 'M':               
               break;
            case 'H':
               if (hs_high_fqb.size()==0){
                  id = 0;
                  break outer; 
               }
               if (usedHashSet.containsAll(hs_high_fqb) && usedHashSet.containsAll(hs_high_fqb_used)){
                  id = 0;
                  break outer;
               }               
               //now we only want to use hs_high_fdb without using hs_high_fdb_used
                  temp = new HashSet(hs_high_fqb);
                  temp.removeAll(hs_high_fqb_used);
                  temp.removeAll(usedHashSet);
                  if (temp.size() == 0){
                     id = 0;
                     break outer;
                  }
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               
               break;
            default:
               break;
         }

         if (usedHashSet.contains(new Integer(id))) continue;
         else break; 
      }
      return id;
   }

   public static int getRandomNumber_ftb(char type, HashSet usedHashSet){
      int id = 0;
      HashSet temp = null;
      Iterator iter = null;
      int index = 0;
      outer:
      while(true){
         switch (type){
            case 'L':               
               break;
            case 'M':               
               break;
            case 'H':
               if (hs_high_ftb.size()==0){
                  id = 0;
                  break outer; 
               }
               if (usedHashSet.containsAll(hs_high_ftb) && usedHashSet.containsAll(hs_high_ftb_used)){
                  id = 0;
                  break outer;
               }               
               //now we only want to use hs_high_ftb without using hs_high_ftb_used
                  temp = new HashSet(hs_high_ftb);
                  temp.removeAll(hs_high_ftb_used);
                  temp.removeAll(usedHashSet);
                  if (temp.size() == 0){
                     id = 0;
                     break outer;
                  }
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               
               break;
            default:
               break;
         }

         if (usedHashSet.contains(new Integer(id))) continue;
         else break; 
      }
      return id;
   }

   public static int getRandomNumber_m(char type, HashSet usedHashSet){
      int id = 0;
      HashSet temp = null;
      Iterator iter = null;
      int index = 0;
      outer:
      while(true){
         switch (type){
            case 'L':               
               break;
            case 'M':               
               break;
            case 'H':
               if (hs_high_m.size()==0){
                  id = 0;
                  break outer; 
               }
               if (usedHashSet.containsAll(hs_high_m) && usedHashSet.containsAll(hs_high_m_used)){
                  id = 0;
                  break outer;
               }               
               //now we only want to use hs_high_m without using hs_high_m_used
                  temp = new HashSet(hs_high_m);
                  temp.removeAll(hs_high_m_used);
                  temp.removeAll(usedHashSet);
                  if (temp.size() == 0){
                     id = 0;
                     break outer;
                  }
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               
               break;
            default:
               break;
         }

         if (usedHashSet.contains(new Integer(id))) continue;
         else break; 
      }
      return id;
   }

   public static int getRandomNumber_d(char type, HashSet usedHashSet){
      int id = 0;
      HashSet temp = null;
      Iterator iter = null;
      int index = 0;
      outer:
      while(true){
         switch (type){
            case 'L':               
               break;
            case 'M':               
               break;
            case 'H':
               if (hs_high_d.size()==0){
                  id = 0;
                  break outer; 
               }    
               if (usedHashSet.containsAll(hs_high_d) && usedHashSet.containsAll(hs_high_d_used)){
                  id = 0;
                  break outer;
               }               
               //now we only want to use hs_high_d without using hs_high_d_used
                  temp = new HashSet(hs_high_d);
                  temp.removeAll(hs_high_d_used);
                  temp.removeAll(usedHashSet);
                  if (temp.size() == 0){
                     id = 0;
                     break outer;
                  }
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }               
               break;
            default:
               break;
         }

         if (usedHashSet.contains(new Integer(id))) continue;
         else break; 
      }
      return id;
   }

   public static int getRandomNumber_tm(char type, HashSet usedHashSet){
      int id = 0;
      HashSet temp = null;
      Iterator iter = null;
      int index = 0;
      outer:
      while(true){
         switch (type){
            case 'L':               
               break;
            case 'M':
               if (hs_middle_tm.size()==0){
                  id = 0;
                  break outer; 
               }  
               if (usedHashSet.containsAll(hs_middle_tm)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(hs_middle_tm);
               temp.removeAll(hs_middle_tm_used);
               temp.removeAll(usedHashSet);
               if (temp.size() == 0){
                  id = 0;
                  break outer;
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               break;
            case 'H':
               break;
            default:
               break;
         }

         if (usedHashSet.contains(new Integer(id))) continue;
         else break; 
      }
      return id;
   }

   public static int getRandomNumber_pm(char type, HashSet usedHashSet){
      int id = 0;
      HashSet temp = null;
      Iterator iter = null;
      int index = 0;
      outer:
      while(true){
         switch (type){
            case 'L':               
               break;
            case 'M':
               if (hs_middle_pm.size()==0){
                  id = 0;
                  break outer; 
               }
               if (usedHashSet.containsAll(hs_middle_pm)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(hs_middle_pm);
               temp.removeAll(hs_middle_pm_used);
               temp.removeAll(usedHashSet);
               if (temp.size() == 0){
                  id = 0;
                  break outer;
               }else{
                  id = (int)(Math.random() * temp.size());                  
                  iter = temp.iterator();
                  index = 0;
                  while (iter.hasNext()) {
                     if (index == id){
                        id = ((Integer)iter.next()).intValue();
                        break outer;
                     }
                     iter.next();
                     index++;                      
                  }
               }
               break;
            case 'H':
               break;
            default:
               break;
         }

         if (usedHashSet.contains(new Integer(id))) continue;
         else break; 
      }
      return id;
   }

   public static int getRandomNumber(HashSet usedHashSet, HashSet totalHashSet){
 System.out.println("in StringSetTransfer,int getRandomNumber(two Hashsets), totalHashSet :: " + setToString(totalHashSet));
      int id = 0;
      HashSet totalTemp = new HashSet(totalHashSet);
      totalTemp.removeAll(usedHashSet);
      
      if (totalTemp.size() == 0){
         return 0;
      }else{
         id = (int)(Math.random() * totalTemp.size());
System.out.println("in StringSetTransfer, getRandomNumber(two Hashsets),id = (int)(Math.random() * totalTemp.size())" + id);
         Iterator iter = totalTemp.iterator();
         int index = 0;
         while (iter.hasNext()){
            if (index == id){
               id = ((Integer)iter.next()).intValue();
               break;
            }
            iter.next();
            index++;
         }      
      }
      System.out.println("in StringSetTransfer, getRandomNumber(two Hashsets),at returen, id = " + id);
      return id;
   }

   public static double[] rate(boolean _lastCorrect, char _lastType){
      lastCorrect = _lastCorrect;
      lastType = _lastType;
      double score = 0;
      problem_low = 0;
      correct_low = 0;
      problem_middle = 0;
      correct_middle = 0;
      problem_high = 0;
      correct_high = 0;
      if (lastCorrect){
         switch (lastType){
            case 'L':
              problem_low ++;
              correct_low ++;
              score = 1;
              break;
            case 'M':
              problem_middle ++;
              correct_middle ++;
              score = 1.5;             
              break;
            case 'H':
              problem_high ++;
              correct_high ++;
              score = 2;
              break;
            default:
              score = 1;
              break;
         }
      }else{
         switch (lastType){
            case 'L':
              problem_low ++;  
              score = 0;           
              break;
            case 'M': 
              problem_middle ++;                                 
              //score = -0.5;
              score = 0; //we want to remove the measure of punishment
              break;
            case 'H':
              problem_high ++;                         
              //score = -1;
              score = 0; //we want to remove the measure of punishment
              break;
            default:              
              break;
         }
      }
      double[] ret = new double[7];
      ret[0]=score; 
      ret[1]=problem_low; ret[2]=correct_low;
      ret[3]=problem_middle; ret[4]=correct_middle;
      ret[5]=problem_high; ret[6]=correct_high;
      return ret;
   }

   public static boolean match(String answers, String input){
      Object[] obs;
      if (answers.contains("#"))
         obs = answers.split("#");
      else
         obs = answers.split("%");
      for (int i=0; i<obs.length; i++){
         String answer = (String)obs[i];
         if (answer.equalsIgnoreCase(input))
            return true;
      }
      return false;
   }

   public static String getString(String filePath){      
      InputStream inputStream = null;
      String encoding = "GBK";
      BufferedReader reader = null;
      String result= "";
      try {
         inputStream = new FileInputStream(new File(filePath));
         reader =  new BufferedReader(new InputStreamReader(inputStream, encoding));
         String line;
         int count = 0;
         System.out.println(filePath);
         while ((line = reader.readLine()) != null) {
            count ++;
            result += line;
         }
          reader.close();
      }catch (FileNotFoundException e){
         e.printStackTrace();
      }catch (UnsupportedEncodingException e){
         e.printStackTrace();
      }catch (IOException e){
         e.printStackTrace();
      }finally {      
          System.out.println("This is the end of the reading");
      } 
      return result;    
   }

   public static HashSet getSetFromFile(String filePath) throws Exception{
      String result = getString(filePath);     
      HashSet hSet = new HashSet();
      if (result.trim().equals(""))
         return hSet;
      String[] results = result.split(";");
      for (int i=0; i<results.length; i++){
         hSet.add(new Integer(results[i]));
      }
      return hSet;
   }

 
}

