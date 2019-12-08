package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MusicBg {
    public static String musicbg = "profile\\musicbg.txt";
    
    public static  String readbg() {   	
        File file = new File(musicbg);
        if(file.isFile() && file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                 
                StringBuffer sb = new StringBuffer();
                String text = null;
                while((text = bufferedReader.readLine()) != null){
                    sb.append(text);
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static void createbg() {
    	String flag="1";
    	File file = new File(musicbg);
        if(file.exists()==false){     	
            try {
            	FileOutputStream fileOutputStream = null;
            	file.createNewFile();
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(flag.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public static void writebg(String content){ 
       FileOutputStream fileOutputStream = null;
       File file = new File(musicbg);
       try {
           if(file.exists()==false){
               createbg();
               content = readbg();
           }
           else {
        	   file.delete();
        	   file.createNewFile();
               fileOutputStream = new FileOutputStream(file);
               fileOutputStream.write(content.getBytes());
               fileOutputStream.flush();
               fileOutputStream.close();
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
