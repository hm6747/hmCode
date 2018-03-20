package cn.hmst.impl;

import cn.hmst.common.pojo.EasyUITreeNode;
import cn.hmst.dto.TbItemCatDto;
import cn.hmst.mapper.TbItemCatMapper;
import cn.hmst.pojo.TbItemCat;
import cn.hmst.pojo.TbItemCatExample;
import cn.hmst.service.ItemCatService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
        for (TbItemCat tbItemCat : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent() ? "closed" : "open");
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public List<TbItemCatDto> getItemCatTree() {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        List<TbItemCat> getAllList = itemCatMapper.selectByExample(example);
        List<TbItemCatDto> getAllDtoList = Lists.newArrayList();
        adpet(getAllList, getAllDtoList);
        criteria.andParentIdEqualTo(0l);
        List<TbItemCat> rootList = itemCatMapper.selectByExample(example);
        List<TbItemCatDto> rootDtoList = Lists.newArrayList();
        adpet(rootList, rootDtoList);
        TreeMap<Long, List<TbItemCatDto>> treeMap = new TreeMap();
        List<TbItemCatDto> treeNodes = this.dtoListToTree(getAllDtoList, rootDtoList, treeMap);
        return treeNodes;
    }

    public List<TbItemCatDto> dtoListToTree(List<TbItemCatDto> dtoList, List<TbItemCatDto> rootDtoList, TreeMap<Long, List<TbItemCatDto>> treeMap) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Lists.newArrayList();
        }
        for (TbItemCatDto dto : dtoList) {
            List<TbItemCatDto> list = Lists.newArrayList();
            if (treeMap.containsKey(dto.getParentId())) {
                list = treeMap.get(dto.getParentId());
            }
            list.add(dto);
            treeMap.put(dto.getParentId(), list);
        }
        // 递归生成树
        transformItemCatTree(rootDtoList, treeMap);
        return rootDtoList;
    }

    public void transformItemCatTree(List<TbItemCatDto> levelList, TreeMap<Long, List<TbItemCatDto>> treeMap) {
        for (int i = 0; i < levelList.size(); i++) {
            TbItemCatDto levelDto = levelList.get(i);
            List<TbItemCatDto> nextLevelList = treeMap.get(levelDto.getId());
            if(CollectionUtils.isNotEmpty(nextLevelList)){
                levelDto.setItemCatDtoList(nextLevelList);
                transformItemCatTree(nextLevelList,treeMap);
            }
        }
    }

    public void adpet(List<TbItemCat> originList, List<TbItemCatDto> targetList) {
        for (TbItemCat tbItemCat :
                originList) {
            TbItemCatDto dto = TbItemCatDto.adapt(tbItemCat);
            targetList.add(dto);
        }
    }
}
