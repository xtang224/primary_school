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

   public static HashSet hs_low = null;
   public static HashSet hs_middle = null;
   public static HashSet hs_high = null;
   public static int total = 0;
   public static HashSet hs_low_used = null;

   public static HashSet hs_low_tf = null;
   public static HashSet hs_middle_tf = null;
   public static HashSet hs_high_tf = null;
   public static int total_tf = 0;
   public static HashSet hs_low_tf_used = null;

   public static HashSet hs_low_fb = null;
   public static HashSet hs_middle_fb = null;
   public static HashSet hs_high_fb = null;
   public static int total_fb = 0;
   public static HashSet hs_middle_fb_used = null;

   public static HashSet mhs_low = null;
   public static HashSet mhs_middle = null;
   public static HashSet mhs_high = null;
   public static int mtotal = 0;
   public static HashSet mhs_high_used = null;

   public static HashSet hs_low_fdb = null;
   public static HashSet hs_middle_fdb = null;
   public static HashSet hs_high_fdb = null;
   public static int total_fdb = 0;
   public static HashSet hs_high_fdb_used = null;

   public static HashSet hs_middle_tm = null;
   public static HashSet hs_middle_tm_used = null;
   public static int total_tm = 0;

   public static HashSet hs_high_m = null;
   public static HashSet hs_high_m_used = null;
   public static int total_m = 0;

   public static HashSet hs_high_d = null;
   public static HashSet hs_high_d_used = null;
   public static int total_d = 0;

   public static HashSet hs_high_c2 = null;
   public static HashSet hs_high_c2_used = null;
   public static int total_c2 = 0;

   //below two field members are for problem.jsp and choice.jsp instead of smart ones
   public static HashSet hs_total = null;
   public static HashSet hs_total_tf = null;

   private String userName = null;

   public static boolean init = false;

   public static boolean start19 = false;
   public static boolean done19 = false;
   public static boolean start25 = false;
   public static boolean done25 = false;
   public static boolean start237 = false;
   public static boolean done237 = false;
   public static boolean start282 = false;
   public static boolean done282 = false;
   public static boolean start378 = false;
   public static boolean done378 = false;
   public static boolean start383 = false;
   public static boolean done383 = false;
   public static boolean start462 = false;
   public static boolean done462 = false;
   public static boolean start470 = false;
   public static boolean done470 = false;  
   public static boolean start583 = false;
   public static boolean done583 = false; 
   public static boolean start611 = false;
   public static boolean done611 = false; 
   public static boolean start614 = false;
   public static boolean done614 = false; 
   public static boolean start688 = false;
   public static boolean done688 = false; 
   public static boolean start711 = false;
   public static boolean done711 = false; 
   public static boolean start722 = false;
   public static boolean done722 = false; 
   public static boolean start739 = false;
   public static boolean done739 = false;
   public static boolean start749 = false;
   public static boolean done749 = false;
   public static boolean start789 = false;
   public static boolean done789 = false;
   public static boolean start796 = false;
   public static boolean done796 = false;
   public static boolean start809 = false;
   public static boolean done809 = false;
   public static boolean start861 = false;
   public static boolean done861 = false; 
   public static boolean start874 = false;
   public static boolean done874 = false; 
   public static boolean start879 = false;
   public static boolean done879 = false; 
   public static boolean start883 = false;
   public static boolean done883 = false;
   public static boolean start888 = false;
   public static boolean done888 = false;
   public static boolean start909 = false;
   public static boolean done909 = false;

   public static boolean start330 = false;
   public static boolean done330 = false;
   public static boolean start382 = false;
   public static boolean done382 = false;
   public static boolean start445 = false;
   public static boolean done445 = false;
   public static boolean start447 = false;
   public static boolean done447 = false;
   public static boolean start473 = false;
   public static boolean done473 = false;
   public static boolean start553 = false;
   public static boolean done553 = false;
   public static boolean start558 = false;
   public static boolean done558 = false;
   public static boolean start577 = false;
   public static boolean done577 = false;
   public static boolean start582 = false;
   public static boolean done582 = false;
   public static boolean start623 = false;
   public static boolean done623 = false;
   public static boolean start625 = false;
   public static boolean done625 = false;
   public static boolean start638 = false;
   public static boolean done638 = false;
   public static boolean start645 = false;
   public static boolean done645 = false;
   public static boolean start746 = false;
   public static boolean done746 = false;
   public static boolean start748 = false;
   public static boolean done748 = false;
   public static boolean start750 = false;
   public static boolean done750 = false;
   public static boolean start752 = false;
   public static boolean done752 = false;
   public static boolean start810 = false;
   public static boolean done810 = false;   
   public static boolean start858 = false;
   public static boolean done858 = false;

   public static boolean testDebug = false;
   public static boolean linux = true;

   public static HashMap<Integer, Integer> hm_fdb = new HashMap<Integer, Integer>();
   public static HashMap<Integer, Integer> hm_fb = new HashMap<Integer, Integer>();
   public static HashMap<Integer, Integer> hm_q = new HashMap<Integer, Integer>();
   public static HashMap<Integer, Integer> hm_mq = new HashMap<Integer, Integer>();
   public static HashMap<Integer, Integer> hm_c = new HashMap<Integer, Integer>();
   public static HashMap<Integer, Integer> hm_tm = new HashMap<Integer, Integer>();
   public static HashMap<Integer, Integer> hm_m = new HashMap<Integer, Integer>();
   public static HashMap<Integer, Integer> hm_d = new HashMap<Integer, Integer>();
   public static HashMap<Integer, Integer> hm_c2 = new HashMap<Integer, Integer>();

   public static Timer aTimer = null;
   public static MyTask myTask = null;

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
               if (usedHashSet.containsAll(hs_low)){
                  id = 0;
                  break outer;
               }
               //temp = new HashSet(hs_low_used);
               //temp.removeAll(usedHashSet);
               //if (temp.size() == 0){//then we need to get id from hs_low
               //now we only want to get id from hs_low without using hs_low_used
                  temp = new HashSet(hs_low);
                  temp.removeAll(usedHashSet); 
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
               if (usedHashSet.containsAll(hs_low_tf)){
                  id = 0;
                  break outer;
               }
               //temp = new HashSet(hs_low_tf_used);
               //temp.removeAll(usedHashSet);
               //if (temp.size() == 0){
               //now we only want to get id from hs_low_tf without using hs_low_tf_used
                  temp = new HashSet(hs_low_tf);
                  temp.removeAll(usedHashSet);                                      
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
               if (usedHashSet.containsAll(mhs_high)){
                  id = 0;
                  break outer;
               }
               //temp = new HashSet(mhs_high_used);
               //temp.removeAll(usedHashSet);
               //if (temp.size() == 0){
               //now we only want to get id from mhs_high without using mhs_high_used
                  temp = new HashSet(mhs_high);
                  temp.removeAll(usedHashSet); 
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
               if (usedHashSet.containsAll(hs_middle_fb)){
                  id = 0;
                  break outer;
               }
               //temp = new HashSet(hs_middle_fb_used);
               //temp.removeAll(usedHashSet);
               //if (temp.size() == 0){
               //now we only need to used hs_middle_fb without using hs_middle_fb_used
                  temp = new HashSet(hs_middle_fb);
                  temp.removeAll(usedHashSet); 
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
               if (usedHashSet.containsAll(hs_high_fdb)){
                  id = 0;
                  break outer;
               }
               //temp = new HashSet(hs_high_fdb_used);
               //temp.removeAll(usedHashSet);
               //if (temp.size() == 0){
               //now we only want to use hs_high_fdb without using hs_high_fdb_used
                  temp = new HashSet(hs_high_fdb);
                  temp.removeAll(usedHashSet);
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
               if (usedHashSet.containsAll(hs_middle_tm)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(hs_middle_tm);
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
               if (usedHashSet.containsAll(hs_high_m)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(hs_high_m);
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
               if (usedHashSet.containsAll(hs_high_d)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(hs_high_d);
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

   public static int getRandomNumber_c2(char type, HashSet usedHashSet){
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
               if (usedHashSet.containsAll(hs_high_c2)){
                  id = 0;
                  break outer;
               }
               temp = new HashSet(hs_high_c2);
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

   public static HashSet getMidTestIds(String filePath){
      HashSet ret = new HashSet();
      InputStream inputStream = null;
      String encoding = "GBK";
      BufferedReader reader = null;
      try {
         inputStream = new FileInputStream(new File(filePath));
         reader =  new BufferedReader(new InputStreamReader(inputStream, encoding));
         String line;
         int count = 0;
         System.out.println(filePath);
         while ((line = reader.readLine()) != null) {
            count ++;
            //String[] strings = TextUtils.split(line, "-");
            String[] strings = line.split(";");
            for(int i=0; i<strings.length; i++){
               ret.add(new Integer(strings[i].trim()));
               System.out.print(strings[i]+";");
            }
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
      return ret;
   }

   public static void addTestScore(String filePath, HashMap hMap){      
      InputStream inputStream = null;
      String encoding = "GBK";
      BufferedReader reader = null;
      try {
         inputStream = new FileInputStream(new File(filePath));
         reader =  new BufferedReader(new InputStreamReader(inputStream, encoding));
         String line;
         int count = 0;
         System.out.println(filePath);
         while ((line = reader.readLine()) != null) {
            count ++;
            //String[] strings = TextUtils.split(line, "-");
            String[] strings = line.split(";");
            for(int i=0; i<strings.length; i++){
               String[] strings2 = strings[i].split(":");
               if (strings2.length>1)
                  hMap.put(new Integer(strings2[0].trim()), new Integer(strings2[1].trim()));               
               System.out.print(strings[i]+";");
            }
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

   public static void saveString(String filePath, String result){ 
      String encoding = "GBK";
      OutputStream outputStream = null; 
      BufferedWriter writer = null; 
      try {
         outputStream = new FileOutputStream(new File(filePath));
         writer =  new BufferedWriter(new OutputStreamWriter(outputStream, encoding));         
         //System.out.println(filePath);
         writer.write(result, 0, result.length());
         writer.close();
      }catch (FileNotFoundException e){
         e.printStackTrace();
      }catch (UnsupportedEncodingException e){
         e.printStackTrace();
      }catch (IOException e){
         e.printStackTrace();
      }finally {      
          System.out.println("This is the end of the writing");
      } 
   } 

   public static boolean inGroup(int[] ids, int id){
      for (int i=0; i<ids.length; i++){
         if (ids[i] == id)
            return true;
      }
      return false;
   }

   public static boolean inStrGroup(String[] ids, String id){
      for (int i=0; i<ids.length; i++){
         if (ids[i].equals(id))
            return true;
      }
      return false;
   } 
 
}

