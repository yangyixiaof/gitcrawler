/**
 * <p>Title: �������ݲ���</p>
 * <p>Description: ��ʾһά����Ͷ�ά����ĳ�ʼ���ͻ�������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: myArray.java</p>
 * @author �Ž�
 * @version 1.0
 */
 public class  myArray{
   //��ʼ���������
   char[] cNum = {'1','2','3','4','5','6','7','8','9','0'};
   char[] cStr = {'a','b','c','d','e','f','g','h',
                  'i','j','k','l','m','n','o','p',
                  'q','r','s','t','u','v','w','x','y','z'};
   int[] iMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
   String[] sMail = {"@","."};
/**
 *<br>����˵����У������ʼ�
 *<br>���������String sPara ��У��ĵ����ʼ��ַ�
 *<br>�������ͣ�boolean ���У��ĸ�ʽ���ϵ����ʼ���ʽ����true�����򷵻�false
 */   
   public boolean isMail(String sPara){
   	for(int i=0;i<sMail.length;i++){
   	  if(sPara.indexOf(sMail[i])==-1)
   	    return false;   	  
   	}
   	return true;
   }
/**
 *<br>����˵�����ж��Ƿ�������
 *<br>���������String sPara�� ��Ҫ�жϵ��ַ���
 *<br>�������ͣ�boolean����������������ͣ�����true�����򷵻�false
 */   
   public boolean isNumber(String sPara){
   	 int iPLength = sPara.length();
   	 for(int i=0;i<iPLength;i++){
   	  char cTemp = sPara.charAt(i);
   	  boolean bTemp = false;
   	  for(int j=0;j<cNum.length;j++){
   	    if(cTemp==cNum[j]){
   	      bTemp = true;
   	      break;
   	    }
   	  }
   	  if(!bTemp) return false; 
   	 }
    return true;
   }
/**
 *<br>����˵�����ж��Ƿ���Ӣ���ַ�
 *<br>���������String sPara��Ҫ�����ַ�
 *<br>�������ͣ�boolean����������ַ�����true����֮Ϊfalse
 */   
   public boolean isString(String sPara){
   	 int iPLength = sPara.length();
   	 for(int i=0;i<iPLength;i++){
   	  char cTemp = sPara.charAt(i);
   	  boolean bTemp = false;
   	  for(int j=0;j<cStr.length;j++){
   	    if(cTemp==cStr[j]){
   	      bTemp = true;
   	      break;
   	    }
   	  }
   	  if(!bTemp) return false; 
   	 }
    return true;
   }
/**
 *<br>����˵�����ж��Ƿ�������
 *<br>���������int iPara��Ҫ�жϵ����
 *<br>�������ͣ�boolean����������귵��true�����򷵻�false
 */   
   public boolean chickDay(int iPara){
     return iPara%100==0&&iPara%4==0;
   }
/**
 *<br>����˵����������ڸ�ʽ�Ƿ���ȷ
 *<br>���������String sPara��Ҫ���������ַ�
 *<br>�������ͣ�int��0 ���ڸ�ʽ��ȷ��-1 �»����ղ���Ҫ�� -2 �����ո�ʽ����ȷ 
 */
   public int chickData(String sPara){
   	boolean bTemp = false;
   	//���������ڳ��Ȳ���ȷ
   	if(sPara.length()!=10) return -2;
   	//��ȡ��
   	String sYear = sPara.substring(0,4);
   	//�ж����Ƿ�Ϊ����
   	if(!isNumber(sYear)) return -2;
   	//��ȡ�·�
   	String sMonth = sPara.substring(5,7);
   	//�ж��·��Ƿ�Ϊ����
   	if(!isNumber(sMonth)) return -2;
   	//��ȡ��
   	String sDay = sPara.substring(8,10);
   	//�ж����Ƿ�Ϊ����
   	if(!isNumber(sDay)) return -2;
   	//���ꡢ�¡���ת��Ϊ����
   	int iYear = Integer.parseInt(sYear);
   	int iMon = Integer.parseInt(sMonth);
   	int iDay = Integer.parseInt(sDay);
   	if(iMon>12) return -1;
   	//������´���
   	if(iMon==2&&chickDay(iYear)){
   	  if(iDay>29) return 2;
   	}else{
   	  if(iDay>iMonth[iMon-1]) return -1;
   	}
   	return 0;
   }
/**
 *<br>����˵������������������
 *<br>���������
 *<br>�������ͣ�
 */ 
   public static void main(String[] arges){
     myArray mA = new myArray();
     //У���ʼ���ַ
     boolean bMail = mA.isMail("tom@163.com");
     System.out.println("1 bMail is "+bMail);
     bMail = mA.isMail("tom@163com");
     System.out.println("2 bMail is "+bMail);
     //��ʾ�Ƿ�������
     boolean bIsNum = mA.isNumber("1234");
     System.out.println("1��bIsNum="+bIsNum);
     bIsNum = mA.isNumber("123r4");
     System.out.println("2��bIsNum="+bIsNum);
     //��ʾ�Ƿ���Ӣ���ַ�
     boolean bIsStr = mA.isString("wer");
     System.out.println("1��bIsStr="+bIsStr);
     bIsStr = mA.isString("wer3");
     System.out.println("2��bIsStr="+bIsStr);
     //��ʾ�������
     int iIsTime = mA.chickData("2003-12-98");
     System.out.println("1��iIsTime="+iIsTime);
     iIsTime = mA.chickData("2003-111-08");
     System.out.println("2��iIsTime="+iIsTime);
     iIsTime = mA.chickData("2003-10-08");
     System.out.println("3��iIsTime="+iIsTime);
     iIsTime = mA.chickData("2000-02-30");
     System.out.println("4��iIsTime="+iIsTime);
   }
 }