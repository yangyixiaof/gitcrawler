package com.zzk;

/**
 * @author 张振坤
 * 产生65~90之间随机整数的类
 */
public class RandomBuildLetter {
    public RandomBuildLetter() {
        super();
    }
    
    public int[] getLetter(int letterCounts) {
        int[] letter = new int[letterCounts]; // 根据参数创建整型数组
        for (int i = 0; i < letterCounts; i++) {
            int a = (int) getRandomLetter(); // 调用方法产生65~90之间的随机整数，即大写字母A-Z的ASCII值
            letter[i] = a; // 将产生的数赋值给数组
        }
        return letter; // 返回数组对象
    }
    
    public int getRandomLetter() {
        int letter = (int) (Math.random() * 26) + 65; // 产生65~90之间的随机整数，即大写字母的ASCII值
        return letter;
    }
}
