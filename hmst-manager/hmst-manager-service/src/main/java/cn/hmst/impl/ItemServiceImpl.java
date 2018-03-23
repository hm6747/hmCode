package cn.hmst.impl;

import cn.hmst.common.pojo.HmResult;
import cn.hmst.common.util.IDUtils;
import cn.hmst.mapper.TbItemDescMapper;
import cn.hmst.mapper.TbItemMapper;
import cn.hmst.pojo.TbItem;
import cn.hmst.pojo.TbItemDesc;
import cn.hmst.pojo.TbItemExample;
import cn.hmst.query.PageQuery;
import cn.hmst.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30 0030.
 */

@Service
public class ItemServiceImpl  implements ItemService{
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Override
    public TbItem getItemById(long itemId) {
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        System.out.println(1);
        List<TbItem> tbItems = itemMapper.selectByExample(example);
        System.out.println(2);
        if (tbItems != null && tbItems.size() > 0) {

            return  tbItems.get(0);
        }
        return null;
    }

    @Override
    public PageInfo<TbItem> getItemList(PageQuery pageQuery,String keyWord) {
        PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(keyWord)){
            criteria.andTitleLike("%"+keyWord+"%");
        }
        List<TbItem> list = itemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
        return pageInfo;
    }

    @Override
    public HmResult addItem(TbItem item, String desc) {
        long itemId = IDUtils.genItemId();
        item.setId(itemId);
        //1-正常 2-下架 3-删除
        item.setStatus((byte)1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        itemMapper.insert(item);
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return HmResult.ok();
    }
}
