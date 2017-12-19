package cn.hmst.restcontroller;

import cn.hmst.common.util.FastDFSClient;
import cn.hmst.common.util.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/1 0001.
 */

@Controller
public class FileController {
    @Value("${FILE_SERVER_URL}")
    private String FILE_SERVER_URL;
    @RequestMapping(value = "pic/upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile){
        FastDFSClient fastDFSClient = null;
        try {
            fastDFSClient = new FastDFSClient("classpath:config/client.conf");
            String fileName = uploadFile.getOriginalFilename();
            String extName = fileName.substring(fileName.lastIndexOf(".")+1);
            String url = fastDFSClient.uploadFile(uploadFile.getBytes(),extName);
            url = FILE_SERVER_URL+url;
            Map result = new HashMap();
            result.put("error",0);
            result.put("url",url);
            String json = JsonUtils.objectToJson(result);
            return  json;
        } catch (Exception e) {
            e.printStackTrace();
            Map result = new HashMap();
            result.put("error",1);
            result.put("url","文件上传失败");
            String json = JsonUtils.objectToJson(result);
            return  json;
        }

    }
}
