package  com.bean; 
/**
 * <p>Title: JSP���õ�Bean</p>
 * <p>Description: �û���֤У��</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: myBean.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class myBean  {
//�û���
 private String NAME="";
 public void setNAME(String newFiled) {
    NAME=newFiled;
 }
 public String getNAME() {
    return NAME;
 }
 //����
 private String PWD="";
 public void setPWD(String newFiled) {
   	PWD=newFiled;
 }
 public String getPWD() {
    return PWD;
 }
 //��ɫID
 private int ID=-1;
 public void setID(int newFiled) {
   	ID=newFiled;
 }
 public int getID() {
    return ID;
 }
/**
 *<br>����˵��������û��Ƿ�Ϸ�
 *<br>���������
 *<br>�������ͣ�
 */ 
 public int check(){
   String[] name = {"tom","river","wind","riverwind"};
   String[] pwd = {"123","1234","12345","123456"};
   int[][] id = {{20010},
                 {20010,10001},
                 {20010,20001},
                 {20010,20001,10001}};
   for(int i=0;i<name.length;i++){

     if(name[i].equals(NAME)){
       if(pwd[i].equals(PWD)){
         int[] iTemp = id[i];
         for(int j=0;j<iTemp.length;j++){
           if(iTemp[j]==ID) return 1;
         }
         return 0;
       }
       return -1;
     }
   }
   return -2;
 }
}//end