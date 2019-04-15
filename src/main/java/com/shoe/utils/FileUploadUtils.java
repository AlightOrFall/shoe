package com.shoe.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class FileUploadUtils {
	private static final Log LOG = LogFactory.getLog(FileUploadUtils.class);
	private static final String DEFALUT_SUFFIX = "temp";
	private static final String FILE_SEPARATOR = "/";
	/**
	 * 上传文件的最大文件字节数(单位k)
	 */
	private static final long DEFAULT_MAX_SIZE=20480;
	/**读取大小*/
	private static final int BYTE_SIZE = 1;
	/**保存大小*/
	private static final int SAVE_SIZE = 1024;
	
	/**
	 * 上传文件,为保证文件的上传成功，用时间戳作为文件名<br/>
	 * 如果文件名不存在，则返回null
	 * @param request http request 请求
	 * @param fileParamter 上传的文件请求参数
	 * @param 上传文件允许的最大字节数(单位k)
	 * @param 上传文件允许的后缀名
	 * @return 返回相对于web工程的绝对路径+文件名
	 * @throws IOException 
	 */
	public static String uploadFile(HttpServletRequest request,String fileParamter,long maxSize,String[] includesuffixs,String savePath) throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile(fileParamter);		
		String filename = multipartFile.getOriginalFilename();
		if(StringUtils.isEmpty(filename)) return null;		
		String[] split = filename.split("\\.");
		String suffix = split.length == 1?DEFALUT_SUFFIX:split[split.length-1];
		return uploadFile(request,fileParamter,savePath,suffix,maxSize,includesuffixs);
	}
	
	/**
	 * 上传文件,为保证文件的上传成功，用时间戳作为文件名
	 * @author fyuan
	 * @param request http request 请求
	 * @param fileParamter 上传的文件请求参数
	 * @return 返回相对于web工程的绝对路径+文件名
	 * @throws IOException 
	 */
	public static String uploadFile(HttpServletRequest request,String fileParamter,String savePath) throws IOException {
		return uploadFile(request,fileParamter,DEFAULT_MAX_SIZE,null,savePath);
	}
	
	private static String getUploadPath(String uploadPath) {
		 String webRoot = getWebRoot().endsWith(FILE_SEPARATOR)?getWebRoot():getWebRoot()+"/";
		 uploadPath = uploadPath.startsWith(FILE_SEPARATOR)?uploadPath.substring(1):uploadPath;
		 String path = webRoot+uploadPath;
		String month = FileUploadUtils.getMonth();
		path = path.endsWith(FILE_SEPARATOR)?path+month:path+FILE_SEPARATOR+month+"/";
		initDir(path);
		return path;
	}

	/**
	 * 上传文件,为保证文件的上传成功，用时间戳作为文件名（作为web端上传微课用）
	 * @param request http request 请求
	 * @param fileParamter 上传的文件请求参数
	 * @param uploadPath   相对对web工程的上传路径
	 * @param suffix		文件后缀名
	 * @return 返回相对于web工程的绝对路径+文件名
	 * @throws IOException 
	 */
	public static String uploadFile2(HttpServletRequest request,String fileParamter,String uploadPath,long maxSize,String[] includesuffixs,boolean hasDatePath) throws IOException{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile(fileParamter);		
		String filename = multipartFile.getOriginalFilename();
		if(StringUtils.isEmpty(filename)) return null;		
		String[] split = filename.split("\\.");
		String suffix = split.length == 1?DEFALUT_SUFFIX:split[split.length-1];
		
		uploadPath = getUploadPath(uploadPath,hasDatePath);
		String filePrefix = new Long(System.currentTimeMillis()).toString();
		String fileName = uploadPath.endsWith(FILE_SEPARATOR)?uploadPath+filePrefix:uploadPath+FILE_SEPARATOR+filePrefix;
		fileName = fileName+"."+suffix;
		assertCanUpload(multipartFile,suffix,maxSize,includesuffixs);
		File destFile = new File(fileName.toString());
		try {
			multipartFile.transferTo(destFile);
		} catch (Exception e) {
			LOG.error("upload image error", e);
			return null;
		}
		String returnFileName = fileName.substring(getWebRoot().length());
		return returnFileName.startsWith(FILE_SEPARATOR)?returnFileName:"/"+returnFileName;
	}
	
	private static String getUploadPath(String uploadPath,boolean hasDatePath) {
		String webRoot = getWebRoot().endsWith(FILE_SEPARATOR)?getWebRoot():getWebRoot()+"/";
		uploadPath = uploadPath.startsWith(FILE_SEPARATOR)?uploadPath.substring(1):uploadPath;
		String path = webRoot+uploadPath;
		String month = FileUploadUtils.getMonth();
		if(hasDatePath){
			path = path.endsWith(FILE_SEPARATOR)?path+month:path+FILE_SEPARATOR+month+"/";
		}else{
			path = path.endsWith(FILE_SEPARATOR)?path:path+FILE_SEPARATOR;
		}
		
		initDir(path);
		return path;
	}
	
	/**
	 * 如果目录不存在那么创建目录
	 * @param uploadPath
	 */
	public static void initDir(String uploadPath) {
		File directory = new File(uploadPath);
		if (!directory.isDirectory()) {
			directory.mkdirs();
		}
	}
	/**
	 * 上传文件,为保证文件的上传成功，用时间戳作为文件名
	 * @param request http request 请求
	 * @param fileParamter 上传的文件请求参数
	 * @param uploadPath   相对对web工程的上传路径
	 * @param suffix		文件后缀名
	 * @return 返回相对于web工程的绝对路径+文件名
	 * @throws IOException 
	 */
	public static String uploadFile(HttpServletRequest request,String fileParamter,String uploadPath,String suffix,long maxSize,String[] includesuffixs) throws IOException{
		uploadPath = getUploadPath(uploadPath);
		String filePrefix = new Long(System.currentTimeMillis()).toString();
		String fileName = uploadPath.endsWith(FILE_SEPARATOR)?uploadPath+filePrefix:uploadPath+FILE_SEPARATOR+filePrefix;
		fileName = fileName+"."+suffix;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile(fileParamter);
		assertCanUpload(multipartFile,suffix,maxSize,includesuffixs);
		File destFile = new File(fileName.toString());
		try {
			multipartFile.transferTo(destFile);
		} catch (Exception e) {
			LOG.error("upload image error", e);
			return null;
		}
		String returnFileName = fileName.substring(getWebRoot().length());
		return returnFileName.startsWith(FILE_SEPARATOR)?returnFileName:"/"+returnFileName;
	}
	
	/**
	 * 上传文件保存到绝对路径
	 * @param request
	 * @param fileParamter
	 * @param uploadPath
	 * @return
	 * @throws IOException
	 */
	public static String uploadAbsImage(HttpServletRequest request,String fileParamter,String uploadPath) throws IOException{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile(fileParamter);		
		String filename = multipartFile.getOriginalFilename();
		if(StringUtils.isEmpty(filename)) return null;		
		String[] split = filename.split("\\.");
		String suffix = split.length == 1?DEFALUT_SUFFIX:split[split.length-1];
		String filePrefix = new Long(System.currentTimeMillis()).toString();
		String fileName = uploadPath.endsWith(FILE_SEPARATOR)?uploadPath+filePrefix:uploadPath+FILE_SEPARATOR+filePrefix;
		fileName = fileName+"."+suffix;
		assertCanUpload(multipartFile,suffix,DEFAULT_MAX_SIZE,null);
		File destFile = new File(fileName.toString());
		try {
			multipartFile.transferTo(destFile);
		} catch (Exception e) {
			LOG.error("upload image error", e);
			return null;
		}
		return fileName;
	}
	
	private static void assertCanUpload(MultipartFile multipartFile,String suffix,long maxSize,String[] includesuffixs) throws IOException {
		if(multipartFile == null||multipartFile.getSize() == 0) {
			throw new IOException("上传文件不存在");
		}
		if(multipartFile.getSize()>maxSize*1024){
			throw new IOException("上传文件不能超过  "+maxSize+" K");
		}
		if(includesuffixs ==null||includesuffixs.length == 0) return;
		boolean eixstSuffix = false;
		for(String includesuffix : includesuffixs) {
			if(includesuffix.toLowerCase().equals(suffix.toLowerCase())) {
				eixstSuffix = true;
				break;
			}
		}
		if(!eixstSuffix) {
			throw new IOException("上传的文件格式不正确");
		}
	}
	
	/**
	 * 获得输入流
	 * @param request
	 * @param fileParamter
	 * @return
	 * @throws IOException
	 */
	public static InputStream getInputStream(HttpServletRequest request,String fileParamter) throws IOException{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile(fileParamter);
		if(multipartFile != null)
			return multipartFile.getInputStream();
		return null;
	}
	
	public static final String getWebRoot() {
		return PropertiesUtils.getSocketAttribute("root_path");
	}
	
	/**
	 * 获得当前年 月份
	 * @return
	 */
	public static final String getMonth(){
		return DateUtils.format(new Date(), "yyyyMM");
	}
	
	/**
	 * 获得当前月份日期
	 * @return
	 */
	public static final String getDay(){
		return DateUtils.format(new Date(), "yyyyMMdd");
	}
	
	/**
	 * 读取图片流，转为BASE64Decoder
	 */
	private static String getImgToBase64(InputStream in) {
		byte[] buff = new byte[BYTE_SIZE]; // 每次读的缓存
		byte[] save = new byte[SAVE_SIZE]; // 保存前缓存
		byte[] imgByte = null;
		String imgStr = null;
		BufferedInputStream bf = null;
		List<byte[]> byteList = null;
		try {
			byteList = new ArrayList<byte[]>();
			// 对字节数组Base64编码
			BASE64Encoder encoder = new BASE64Encoder();
			bf = new BufferedInputStream(in);
			int i = 0;
			while (bf.read(buff) != -1) {
				// 一个字节一个字节读
				save[i] = buff[0];
				if (i == SAVE_SIZE - 1) { // 达到保存长度时开始保存
					// 返回 Base64编码过的字节数组字符串
					byteList.add(save);
					save = new byte[SAVE_SIZE];
					i = 0;
				} else {
					i++;
				}
			}
			// 最后这段如果没达到保存长度，需要把前面的保存下来
			if (i > 0) {
				// 返回Base64编码过的字节数组字符串
				byteList.add(save);
			}
			int length = byteList.size();
			imgByte = new byte[SAVE_SIZE * (length - 1) + i];
			for (int j = 0; j < byteList.size() - 1; j++) {
				byte[] temp = byteList.get(j);
				System.arraycopy(temp, 0, imgByte, j * SAVE_SIZE, SAVE_SIZE);
			}
			if (i > 0) {
				System.arraycopy(save, 0, imgByte, (length - 1) * SAVE_SIZE, i);
			}
			imgStr = encoder.encode(imgByte);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bf.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return imgStr;
	}
	/**
	  * 将一个base64转换成图片保存在  path  文件夹下   名为imgName.jpg
	  * @param base64String
	  * @param path  是一个文件夹路径
	  * @param imgName 图片名字(没有后缀)
	  * @param suffix 图片后缀 可以为jpg、png、gif
	  * @throws Exception
	  */
	public static String savePictoServer(String base64String,String path,String imgName,String suffix)throws Exception{
		 	BASE64Decoder decoder = new sun.misc.BASE64Decoder(); 
	        byte[] bytes1 = decoder.decodeBuffer(base64String);                  
	        ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);    
	        BufferedImage bi1 =ImageIO.read(bais); 
	        String realPath = path+Constant.SHOE_USER_PICTURE+getMonth();
	        File dir=new File(realPath);
	        if(!dir.exists()){
	         dir.mkdirs();
	        }
	        String fileName=realPath+"/"+imgName+"."+suffix;
	        File w2 = new File(fileName);    
	        ImageIO.write(bi1, suffix, w2);
	        return fileName;
	 }
	 	/**
	 	 * 根据图片URL上传图片到云微课服务器
	 	 * @param url
	 	 * @return 返回图片路径
	 	 * @throws Exception
	 	 */
	    public static  String uploadBase64(String url,String suffix) throws Exception {
			String savePath = getWebRoot();
			URL realUrl = new URL(url);
	        URLConnection connection = realUrl.openConnection();
	        InputStream in=connection.getInputStream();
	        String imgStr=getImgToBase64(in);
	        String imgPath=savePictoServer(imgStr,savePath, String.valueOf(System.currentTimeMillis()),suffix);
			if(in!=null){
				in.close();
			}
			connection=null;
			realUrl=null;
	        return imgPath;
		}

}
