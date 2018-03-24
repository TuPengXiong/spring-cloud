/**

 * @date 2016年5月4日下午3:20:55
*/

package com.tx.image.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.TimeZone;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * 
 * @ClassName: Ftp 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author tupx 
 * @date 2018年1月11日 下午4:01:11 
 *
 */
public class Ftp {
	private FTPClient ftpClient;  
    private String strIp;  
    private int intPort;  
    private String user;  
    private String password;  
    private Logger logger=Logger.getLogger(Ftp.class);
  
    /* * 
     * Ftp构造函数 
     */  
    public Ftp(String strIp, int intPort, String user, String Password) {  
        this.strIp = strIp;  
        this.intPort = intPort;  
        this.user = user;  
        this.password = Password;  
        this.ftpClient = new FTPClient();  
    }  
    /** 
     * @return 判断是否登入成功 
     * */  
    public boolean ftpLogin() {  
        boolean isLogin = false;  
        FTPClientConfig ftpClientConfig = new FTPClientConfig();  
        ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());  
        this.ftpClient.setControlEncoding("GBK");  
        this.ftpClient.configure(ftpClientConfig);  
        try {  
            if (this.intPort > 0) {  
                this.ftpClient.connect(this.strIp, this.intPort);  
            } else {  
                this.ftpClient.connect(this.strIp);  
            }  
            // FTP服务器连接回答  
            int reply = this.ftpClient.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
                this.ftpClient.disconnect();  
                logger.error("登录FTP服务失败！");  
                return isLogin;  
            }  
            this.ftpClient.login(this.user, this.password);  
            // 设置传输协议  
            this.ftpClient.enterLocalPassiveMode();  
            this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
            isLogin = true;  
        } catch (Exception e) {  
            logger.error(this.user + "登录FTP服务失败！" + e.getMessage());  
        }  
        this.ftpClient.setBufferSize(1024 * 2);  
        this.ftpClient.setDataTimeout(30 * 1000);  
        return isLogin;  
    }  
  
    /** 
     * @退出关闭服务器链接 
     * */  
    public void ftpLogOut() {  
        if (null != this.ftpClient && this.ftpClient.isConnected()) {  
            try {  
                boolean reuslt = this.ftpClient.logout();// 退出FTP服务器  
                if (reuslt) {  
                	logger.info("成功退出服务器");  
                }  
            } catch (IOException e) {  
                logger.error("退出FTP服务器异常！" + e.getMessage());  
            } finally {  
                try {  
                    this.ftpClient.disconnect();// 关闭FTP服务器的连接  
                } catch (IOException e) {  
                    logger.error("关闭FTP服务器的连接异常！"+e.getMessage(),e);  
                }  
            }  
        }  
    }  
  
    /*** 
     * 上传Ftp文件 
     * @param localFile 当地文件 
     * @param romotUpLoadePath上传服务器路径 - 应该以/结束 
     * */  
    public boolean uploadFile(File localFile, String romotUpLoadePath) {  
        BufferedInputStream inStream = null;  
        boolean success = false;  
        try {  
            this.ftpClient.changeWorkingDirectory(romotUpLoadePath);// 改变工作路径  
            inStream = new BufferedInputStream(new FileInputStream(localFile));  
            success = this.ftpClient.storeFile(localFile.getName(), inStream);  
            if (success == true) {  
                return success;  
            }  
        } catch (FileNotFoundException e) {  
            logger.error(localFile + "未找到  "+e.getMessage(),e);  
        } catch (IOException e) {  
        	logger.error(e.getMessage(),e);  
        } finally {  
            if (inStream != null) {  
                try {  
                    inStream.close();  
                } catch (IOException e) {  
                	logger.error(e.getMessage(),e);  
                }  
            }  
        }  
        return success;  
    }  
  
    /*** 
     * 下载文件 
     * @param remoteFileName   待下载文件名称 
     * @param localDires 下载到当地那个路径下 
     * @param remoteDownLoadPath remoteFileName所在的路径 
     * */  
  
    public boolean downloadFile(String remoteFileName, String localDires,  
            String remoteDownLoadPath) {  
        String strFilePath = localDires+remoteFileName;  
        BufferedOutputStream outStream = null;  
        boolean success = false;  
        try {  
        	 File fileDir=new File(localDires);
 	        if(!fileDir.exists()){
 	        	fileDir.mkdirs();
 	        }
 	        File file=new File(strFilePath);
 	        if(!file.exists()){
 	        	file.createNewFile();
 	        }
        	outStream = new BufferedOutputStream(new FileOutputStream(  
                    strFilePath));  
            this.ftpClient.changeWorkingDirectory(remoteDownLoadPath);  
            ftpClient.setBufferSize(1024); 
            //设置文件类型（二进制） 
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
            success = ftpClient.retrieveFile(remoteFileName, outStream); 
            if (success == true) {  
                return success;  
            }  
        } catch (Exception e) {  
            logger.error(remoteFileName + "下载失败   "+e.getMessage(),e);  
        } finally {  
            if (null != outStream) {  
                try {  
                    outStream.flush();  
                    outStream.close();  
                } catch (IOException e) {  
                	logger.error(e.getMessage(),e);
                }  
            }  
        }  
        if (success == false) {  
        	logger.error(remoteFileName + "下载失败!!!");  
        }  
        return success;  
    }  
  
    /*** 
     * @上传文件夹 
     * @param localDirectory 
     *            当地文件夹 
     * @param remoteDirectoryPath 
     *            Ftp 服务器路径 以目录"/"结束 
     * */  
    public boolean uploadDirectory(String localDirectory,  
            String remoteDirectoryPath) {  
        File src = new File(localDirectory);  
        try {  
            remoteDirectoryPath = remoteDirectoryPath + src.getName() + "/";  
            this.ftpClient.makeDirectory(remoteDirectoryPath);  
        } catch (IOException e) {  
            logger.error(remoteDirectoryPath + "目录创建失败"+e.getMessage(),e);  
        }  
        File[] allFile = src.listFiles();  
        for (int currentFile = 0; currentFile < allFile.length; currentFile++) {  
            if (!allFile[currentFile].isDirectory()) {  
                String srcName = allFile[currentFile].getPath().toString();  
                uploadFile(new File(srcName), remoteDirectoryPath);  
            }  
        }  
        for (int currentFile = 0; currentFile < allFile.length; currentFile++) {  
            if (allFile[currentFile].isDirectory()) {  
                // 递归  
                uploadDirectory(allFile[currentFile].getPath().toString(),  
                        remoteDirectoryPath);  
            }  
        }  
        return true;  
    }  
  
    /*** 
     * @下载文件夹 
     * @param localDirectoryPath本地地址 
     * @param remoteDirectory 远程文件夹 
     * */  
    public boolean downLoadDirectory(String localDirectoryPath,String remoteDirectory) {  
        try {  
            String fileName = new File(remoteDirectory).getName();  
            localDirectoryPath = localDirectoryPath + fileName + "//";  
            new File(localDirectoryPath).mkdirs();  
            FTPFile[] allFile = this.ftpClient.listFiles(remoteDirectory);  
            for (int currentFile = 0; currentFile < allFile.length; currentFile++) {  
                if (!allFile[currentFile].isDirectory()) {  
                    downloadFile(allFile[currentFile].getName(),localDirectoryPath, remoteDirectory);  
                }  
            }  
            for (int currentFile = 0; currentFile < allFile.length; currentFile++) {  
                if (allFile[currentFile].isDirectory()) {  
                    String strremoteDirectoryPath = remoteDirectory + "/"+ allFile[currentFile].getName();  
                    downLoadDirectory(localDirectoryPath,strremoteDirectoryPath);  
                }  
            }  
        } catch (IOException e) {  
        	logger.error("下载文件夹失败"+e.getMessage(),e);  
            return false;  
        }  
        return true;  
    }  
    
    /**
     * 读取FTP文件内容
     * @param path
     * @param fileName
     * @return
     */
    public String downLoadFile(String path,String fileName){
    	StringBuilder builder = null;
    	try {
    		InputStream ins = null;
    		ftpClient.changeWorkingDirectory(path);
        	FTPFile[] fs = this.ftpClient.listFiles(path);//目录下的文件list
            for(FTPFile ff:fs){
                byte[] bytes = ff.getName().getBytes("utf-8");
                String fn = new String(bytes , "utf-8");
                if(fn.equals(fileName)){ //判断是否是要下载的文件fileName
                	 ins = ftpClient.retrieveFileStream(fileName);
                	 BufferedReader reader=new BufferedReader(new InputStreamReader(ins,Charset.defaultCharset()));
                	 String line;
                	 builder=new StringBuilder(150);
                	 while((line=reader.readLine())!=null){
                		 builder.append(line);
                	 }
                	 reader.close();
                	 if(ins!=null){
                		 ins.close();
                	 }
                	 ftpClient.getReply();
                }else{
                	continue;
                }
            }
		} catch (Exception e) {
			logger.error("读取文件内容失败"+e.getMessage(),e);  
		}
        return builder.toString();
    }
    // FtpClient的Set 和 Get 函数  
    public FTPClient getFtpClient() {  
        return ftpClient;  
    }  
    public void setFtpClient(FTPClient ftpClient) {  
        this.ftpClient = ftpClient;  
    }  
}

