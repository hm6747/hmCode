package cn.hmst.common.util;

import cn.hmst.common.pojo.HmResult;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author xpw
 * 文件上传工具类
 */
public class FileUploadUtil {

    //当上传文件超过限制时设定的临时文件位置，注意是绝对路径
    private String tempPath = null;

    //文件上传目标目录，注意是绝对路径
    private String dstPath = null;

    //新文件名称，不设置时默认为原文件名
    private String newFileName = null;
    //获取的上传请求
    private HttpServletRequest fileuploadReq = null;

    //设置最多只允许在内存中存储的数据,单位:字节，这个参数不要设置太大
    private int sizeThreshold = 4096;

    //设置允许用户上传文件大小,单位:字节
    //共10M
    private long sizeMax = 10485760;

    //图片文件序号
    private int picSeqNo = 1;

    private boolean isSmallPic = false;

    public FileUploadUtil(){
    }

    public FileUploadUtil(String tempPath, String destinationPath){
        this.tempPath  = tempPath;
        this.dstPath = destinationPath;
    }

    public FileUploadUtil(String tempPath, String destinationPath, HttpServletRequest fileuploadRequest){
        this.tempPath   = tempPath;
        this.dstPath = destinationPath;
        this.fileuploadReq = fileuploadRequest;
    }
    public HmResult fileupload(String deviceId, String token, HttpServletRequest request, String [] checkFileTypes, long maxFileSize, String path) throws Exception {
        HmResult baseResponse = new HmResult();
        String time =DateUtils.getNowData();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/";
        String uploadPath = request.getSession().getServletContext().getRealPath("upload/"+path+"/"+time+"/");
        File saveFile = new File(uploadPath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        try{
            baseResponse.setStatus(-1);
            //文件上传的请求
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
            String fileUploadpath = "";
            for(int i = 0;true;i++){
                // 获取上传文件
                MultipartFile file = mRequest.getFile("file"+i);
                if(file == null){
                    break;
                }
                File fileOriginal = new File(uploadPath+"/"+file.getOriginalFilename());
                String fileName = file.getOriginalFilename();
                if(fileOriginal.isFile() && fileOriginal.exists()){
                    baseResponse.setMsg(fileName+"文件已存在,请更改文件名再上传");
                    return baseResponse;
                }
                long fileSize = file.getSize()/1024/1024;
                String filetype= fileName.substring(fileName.lastIndexOf("."));
                if(fileSize >= maxFileSize){
                    baseResponse.setMsg(fileName+"文件大小超过"+maxFileSize+"MB，无法上传");
                    return baseResponse;
                }
                boolean fileTypeResult = false;
                for(int j =0;j<checkFileTypes.length;j++){
                    if(filetype.toUpperCase().equals(checkFileTypes[j].toUpperCase())){
                        fileTypeResult = true;
                    }
                }
                if(!fileTypeResult){
                    baseResponse.setMsg(fileName+"错误的文件类型");
                    return baseResponse;
                }
            }
            for (int i = 0;true; i++) {
                // 获取上传文件
                MultipartFile file = mRequest.getFile("file"+i);
                if(file == null){
                    break;
                }
                String fileName = file.getOriginalFilename();
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadPath, fileName));
                if(i==0){
                    fileUploadpath = fileUploadpath+ basePath+"upload/"+path+"/"+time+"/"+fileName;
                    continue;
                }
                fileUploadpath = fileUploadpath+";"+ basePath+"upload/"+path+"/"+time+"/"+fileName;
            }
            baseResponse.setMsg("上传成功");
            baseResponse.setData(fileUploadpath);
        }catch(Exception e)
        {
        }
        return baseResponse;
    };

    /**
     * 设置临时存贮目录
     */
    public void setTmpPath(String tmppath)
    {
        this.tempPath = tmppath;
    }
    /**
     * 设置目标目录
     */
    public void setDstPath(String dstpath) {
        this.dstPath = dstpath;
    }
    /**
     * 设置最大上传文件字节数，不设置时默认10M
     */
    public void setFileMaxSize(long maxsize) {
        this.sizeMax = maxsize;
    }
    /**
     * 设置Http 请求参数，通过这个能数来获取文件信息
     */
    public void setHttpReq(HttpServletRequest httpreq) {
        this.fileuploadReq = httpreq;
    }
    /**
     * 设置Http 请求参数，通过这个能数来获取文件信息
     */
    public void setNewFileName(String filename) {
        this.newFileName = filename;
    }

    /**
     * 设置此上传文件是否是缩略图文件，这个参数主要用于缩略图命名
     */
    public void setIsSmalPic(boolean isSmallPic) {
        this.isSmallPic = isSmallPic;
    }

    /**
     * 设置Http 请求参数，通过这个能数来获取文件信息
     */
    public void setPicSeqNo(int seqNo) {
        this.picSeqNo = seqNo;
    }

}
