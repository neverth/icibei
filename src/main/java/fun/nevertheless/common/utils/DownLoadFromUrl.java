package fun.nevertheless.common.utils;

import fun.nevertheless.dao.WordCet4Dao;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadFromUrl {

    public String downLoadFromUrl(String urlStr, String fileName, String savePath) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            // 得到输入流
            InputStream inputStream = conn.getInputStream();
            // 获取自己数组
            byte[] getData = readInputStream(inputStream);

            // 文件保存位置
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            File file = new File(saveDir + File.separator + fileName);
            if(file.exists()){
                inputStream.close();
                return "file exists";
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            fos.close();
            inputStream.close();
            // System.out.println("info:"+url+" download success");
            return "file down success" +  saveDir + File.separator + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";

    }
    public  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) {
        System.out.println(new DownLoadFromUrl().
                downLoadFromUrl("https://fanyi.baidu.com/gettts?lan=en&text=abstract&spd=3&source=web",
                        "ability.mp3", "C:\\Users\\HUSHUHUA\\Desktop\\workPlace\\icibei\\src\\main\\webapp\\resource\\mp3"));

    }
}
