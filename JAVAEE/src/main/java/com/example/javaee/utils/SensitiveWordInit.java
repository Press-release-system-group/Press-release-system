package com.example.javaee.utils;

import com.example.javaee.JavaeeApplication;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 *  初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
 *
 *  */
public class SensitiveWordInit {
    private String ENCODING = "UTF-8";    //字符编码
    @SuppressWarnings("rawtypes")
    public HashMap sensitiveWordMap;
    public SensitiveWordInit(){
        super();
    }
    @SuppressWarnings("rawtypes")
    public Map initKeyWord(){
        try {
            //读取敏感词库
            Set<String> keyWordSet = readSensitiveWordFile();
            //将敏感词库加入到HashMap中
            addSensitiveWordToHashMap(keyWordSet);
            //spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sensitiveWordMap;
    }
    //将得到的敏感词库用一个DFA算法模型放到map中
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
        sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        //迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while(iterator.hasNext()){
            key = iterator.next();    //关键字
            nowMap = sensitiveWordMap;
            for(int i = 0 ; i < key.length() ; i++){
                char keyChar = key.charAt(i);       //转换成char型
                Object wordMap = nowMap.get(keyChar);       //获取

                if(wordMap != null){        //如果存在该key，直接赋值
                    nowMap = (Map) wordMap;
                }
                else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<String,String>();
                    newWorMap.put("isEnd", "0");     //不是最后一个
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if(i == key.length() - 1){
                    nowMap.put("isEnd", "1");    //最后一个
                }
            }
        }
    }

    //读取敏感词文件 加到set集合中
    @SuppressWarnings("resource")
    private Set<String> readSensitiveWordFile() throws Exception{
        Set<String> set = new HashSet<String>();
        ClassLoader classLoader = JavaeeApplication.class.getClassLoader();
        URL resource = classLoader.getResource("SensitiveWord.txt");
        File file = new File(resource.getFile());    //读取文件
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try
        {
            if(file.isFile() && file.exists())//文件流是否存在
            {
                read = new InputStreamReader(new FileInputStream(file),ENCODING);
                bufferedReader = new BufferedReader(read);
            }
            //经过各种尝试均未能成功从jar包中读取，我妥协了
//            else//从jar包中读取，由于是压缩包，要使用InputStream
//            {
//                InputStream is = ClassLoader.getSystemResourceAsStream("SensitiveWord.txt");
//                if((is != null))
//                {
//                    bufferedReader = new BufferedReader(new InputStreamReader(is));
//                }
//                else
//                {
//                    throw new Exception("敏感词库文件不存在");
//                }
//            }
            else
            {
                File f = new File("SensitiveWord.txt");
                if(f.isFile() && f.exists())//文件流是否存在
                {
                    read = new InputStreamReader(new FileInputStream(f),ENCODING);
                    bufferedReader = new BufferedReader(read);
                }
                else
                {
                    throw new Exception("敏感词库文件不存在");
                }
            }

            String txt = null;
            while((txt = bufferedReader.readLine()) != null)//读取文件，将文件内容放入到set中
            {
                System.out.println(txt);
                set.add(txt);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            if(read != null)
                read.close();
        }

        return set;
    }
}
