package cn.hmst.impl;

import cn.hmst.common.pojo.EasyUITreeNode;
import cn.hmst.mapper.TbItemCatMapper;
import cn.hmst.pojo.TbItemCat;
import cn.hmst.pojo.TbItemCatExample;
import cn.hmst.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
        for (TbItemCat tbItemCat:list
             ) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }

}
