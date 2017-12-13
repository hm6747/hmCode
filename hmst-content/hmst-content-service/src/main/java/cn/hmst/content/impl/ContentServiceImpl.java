package cn.hmst.content.impl;

import cn.hmst.common.jedis.JedisClient;
import cn.hmst.common.pojo.HmResult;
import cn.hmst.common.util.JsonUtils;
import cn.hmst.content.service.ContentService;
import cn.hmst.mapper.TbContentMapper;
import cn.hmst.pojo.TbContent;
import cn.hmst.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/7 0007.
 */
public class ContentServiceImpl implements ContentService{
    @Autowired
    private  TbContentMapper tbContentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${CONTENT_LIST}}")
    private  String CONTENT_LIST;
    @Override
    public HmResult addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        tbContentMapper.insert(content);
        return HmResult.ok();
    }

    @Override
    public List<TbContent> getContentListByCid(long cid) {
        try {
            String json = jedisClient.hget(CONTENT_LIST,cid+"");
            if(StringUtils.isNotBlank(json)){
                List<TbContent> tbContents =JsonUtils.jsonToList(json,TbContent.class);
                return  tbContents;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> tbContents =tbContentMapper.selectByExampleWithBLOBs(example);
        try {
            jedisClient.hset(CONTENT_LIST,cid+"", JsonUtils.objectToJson(tbContents));
        }catch (Exception e){
            e.printStackTrace();
        }
        return tbContents;
    }
}
