package cn.hmst.content.impl;

import cn.hmst.common.pojo.HmResult;
import cn.hmst.content.service.ContentService;
import cn.hmst.mapper.TbContentMapper;
import cn.hmst.pojo.TbContent;
import cn.hmst.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/7 0007.
 */
public class ContentServiceImpl implements ContentService{
    @Autowired
    private  TbContentMapper tbContentMapper;
    @Override
    public HmResult addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        tbContentMapper.insert(content);
        return HmResult.ok();
    }

    @Override
    public List<TbContent> getContentListByCid(long cid) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> tbContents =tbContentMapper.selectByExampleWithBLOBs(example);
        return tbContents;
    }
}
