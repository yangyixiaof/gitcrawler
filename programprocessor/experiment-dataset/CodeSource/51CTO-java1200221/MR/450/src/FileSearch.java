import java.util.*;
import java.io.*;

public class FileSearch {
    
    public static List findFiles(String baseDirName, String targetFileName) {
        List fileList = new ArrayList(); // ���屣�淵��ֵ��List����
        File baseDir = new File(baseDirName); // ���ݲ�������File����
        if (!baseDir.exists() || !baseDir.isDirectory()) { // �����File���󲻴��ڻ��߲���һ��Ŀ¼
            return fileList; // ����List����
        }
        String tempName = null;
        File[] files = baseDir.listFiles(); // ��ȡ����Ŀ¼�µ��ļ�����
        for (int i = 0; i < files.length; i++) { // ѭ�������ļ�����
            if (!files[i].isDirectory()) { // ��������е��ļ�����һ��Ŀ¼
                tempName = files[i].getName(); // ��ȡ�����������
                if (FileSearch.findName(targetFileName, tempName)) { // �����ļ�ƥ�䷽��
                    fileList.add(files[i].getAbsoluteFile()); // ��ָ�����ļ�����ӵ�������
                }
            }
        }
        return fileList;
    }
    
    public static boolean findName(String pattern, String str) {
        int patternLength = pattern.length(); // ��ȡ�����ַ����ĳ���
        int strLength = str.length();
        int strIndex = 0;
        char eachCh;
        for (int i = 0; i < patternLength; i++) { // ѭ���ַ������ַ����е�ÿ���ַ�
            eachCh = pattern.charAt(i); // ��ȡ�ַ�����ÿ������λ�õ��ַ�
            if (eachCh == '*') { // �������ַ���һ���Ǻ�
                while (strIndex < strLength) {
                    if (findName(pattern.substring(i + 1), str
                            .substring(strIndex))) { // ����ļ���������ģ��ƥ��
                        return true;
                    }
                    strIndex++;
                }
            } else if (eachCh == '?') { // ��������ʺ�
                strIndex++;
                if (strIndex > strLength) { // ���str��û���ַ�����ƥ�䡰?����
                    return false;
                }
            } else { // ���ҪѰ�ҵ�����ͨ���ļ�
                if ((strIndex >= strLength) || (eachCh != str.charAt(strIndex))) { // ���û�в��ҵ�ƥ����ļ�
                    return false;
                }
                strIndex++;
            }
        }
        return (strIndex == strLength);
    }
}
