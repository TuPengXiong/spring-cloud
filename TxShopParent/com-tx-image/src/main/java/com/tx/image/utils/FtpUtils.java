package com.tx.image.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("ftpUtils")
public final class FtpUtils {
	private Logger logger=Logger.getLogger(FtpUtils.class);
	
	@Value("${ftp.host}")
	private String host;
	@Value("${ftp.port}")
	private int port;
	@Value("${ftp.username}")
	private String username;
	@Value("${ftp.password}")
	private String password;
	/**
	 * 文件上传方法
	 * @param path
	 * @param file
	 * @throws IOException 
	 */
	public  void upload(String path, byte[] file) throws IOException {
		FTPClient ftpClient = new FTPClient();
		InputStream inputStream = getStringStream(file);
		try {
			ftpClient.connect(host, port);
			ftpClient.login(username, password);
			ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			//服务器随机分配端口
			ftpClient.enterLocalPassiveMode();
			//本地随机分配端口
			//ftpClient.enterLocalActiveMode();
			int replyCode = ftpClient.getReplyCode();
			if (FTPReply.isPositiveCompletion(replyCode)) {
				String directory = StringUtils.substringBeforeLast(path, "/");
				String filename = StringUtils.substringAfterLast(path, "/");
				if (!ftpClient.changeWorkingDirectory(directory)) {
					String[] paths = StringUtils.split(directory, "/");
					String p = "/";
					ftpClient.changeWorkingDirectory(p);
					for (String s : paths) {
						p += s + "/";
						if (!ftpClient.changeWorkingDirectory(p)) {
							ftpClient.makeDirectory(s);
							ftpClient.changeWorkingDirectory(p);
						}
					}
				}
				boolean store = ftpClient.storeFile(filename, inputStream);
				if(!store){
					throw new IOException("store error");
				}
			}
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			ftpClient.logout();
			IOUtils.closeQuietly(inputStream);
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 上传文件
	 * @param file 文件对象
	 * @param path 上传路径，如："/data/images/test.png或data/images/test.png"
	 */
	public void upload(byte[] file,String path) {
		FTPClient ftpClient = new FTPClient();
		InputStream inputStream = getStringStream(file);;
		try {
			ftpClient.connect(host, port);
			ftpClient.login(username, password);
			ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			String directory = path.substring(0,path.lastIndexOf("/"));
			String filename = path.substring(path.lastIndexOf("/")+1,path.length());
			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				if (!ftpClient.changeWorkingDirectory(directory)) {
					String[] paths = directory.split("/");
					String p = "/";
					ftpClient.changeWorkingDirectory(p);
					for (String s : paths) {
						p += s + "/";
						if (!ftpClient.changeWorkingDirectory(p)) {
							ftpClient.makeDirectory(s);
							ftpClient.changeWorkingDirectory(p);
						}
					}
				}
				ftpClient.storeFile(filename, inputStream);
				ftpClient.logout();
			}
		} catch (IOException e) {
			logger.info("上传文件出错！",e);
		} finally {
			IOUtils.closeQuietly(inputStream);
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					logger.info("上传文件出错！",e);
				}
			}
		}
	}
	/*** 
     * 下载文件 
     * @param remoteFileName   待下载文件名称 
     * @param localDires 下载到当地那个路径下 
     * @param remoteDownLoadPath remoteFileName所在的路径 
     * */  
  
    public boolean downloadFile(String remoteFileName, String localDires,  
            String remoteDownLoadPath) {  
    	Ftp ftp=new Ftp(host,port,username,password);  
        ftp.ftpLogin();  
        Boolean flag= ftp.downloadFile(remoteFileName, localDires, remoteDownLoadPath);
        ftp.ftpLogOut();    
    	return flag;
    }  
    public String downLoad(String remoteDownLoadPath,String remoteFileName) {  
    	Ftp ftp=new Ftp(host,port,username,password);  
        ftp.ftpLogin();  
        String str= ftp.downLoadFile(remoteDownLoadPath, remoteFileName);
        ftp.ftpLogOut();    
    	return str;
    }  
	
	/**
	 * 将json字符串上传至FTP服务器
	 * @param json
	 * @param path
	 */
	public void uploadJsonFile(String json,String path) {
		FTPClient ftpClient = new FTPClient();
		InputStream inputStream = null;
		try {
			inputStream = getStringStream(json);
			ftpClient.connect(host, port);
			ftpClient.login(username, password);
			ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			String directory = path.substring(0,path.lastIndexOf("/"));
			String filename = path.substring(path.lastIndexOf("/")+1,path.length());
			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				if (!ftpClient.changeWorkingDirectory(directory)) {
					String[] paths = directory.split("/");
					String p = "/";
					ftpClient.changeWorkingDirectory(p);
					for (String s : paths) {
						p += s + "/";
						if (!ftpClient.changeWorkingDirectory(p)) {
							ftpClient.makeDirectory(s);
							ftpClient.changeWorkingDirectory(p);
						}
					}
				}
				ftpClient.storeFile(filename, inputStream);
				ftpClient.logout();
			}
		} catch (IOException e) {
			logger.info("上传文件出错！",e);
		} finally {
			IOUtils.closeQuietly(inputStream);
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					logger.info("上传文件出错！",e);
				}
			}
		}
	}
	/**
	 * 上传文件
	 * @param fileName
	 * @param filePath
	 * @param input
	 * @return
	 */
	public  boolean uploadFile(String fileName,String filePath,InputStream input) {
		boolean success = false;
		FTPClient ftpClient = new FTPClient();
		try {
			int reply;
			ftpClient.connect(host, port);
			ftpClient.login(username, password);
			reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return success;
			}
			ftpClient.changeWorkingDirectory(filePath);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.setControlEncoding("UTF-8");
			ftpClient.storeFile(fileName, input);			
			
			input.close();
			ftpClient.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}
    
	/**
	 * 输入字符串转换为输入流
	 * @param sInputString
	 * @return
	 */
	public InputStream getStringStream(String sInputString){
		if (sInputString != null && !sInputString.trim().equals("")){
			try{
				ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
				return tInputStringStream;
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 输入字符串转换为输入流
	 * @param sInputString
	 * @return
	 */
	public InputStream getStringStream(byte[] file){
		return  new ByteArrayInputStream(file);
	}
}
