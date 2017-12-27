package cn.hmst.impl;

import cn.hmst.comon.pojo.EasyUIDataGridResult;
import cn.hmst.comon.pojo.HmResult;
import cn.hmst.comon.util.IDUtils;
import cn.hmst.mapper.TbItemDescMapper;
import cn.hmst.mapper.TbItemMapper;
import cn.hmst.pojo.TbItem;
import cn.hmst.pojo.TbItemDesc;
import cn.hmst.pojo.TbItemExample;
import cn.hmst.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public EasyUIDataGridResult getItemList(int page, int rows) {
        PageHelper.startPage(page, rows);
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
        long total = pageInfo.getTotal();
        result.setTotal(total);
        return result;
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
