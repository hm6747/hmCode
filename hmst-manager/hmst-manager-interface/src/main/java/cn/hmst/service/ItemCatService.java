package cn.hmst.service;

import cn.hmst.comon.pojo.EasyUITreeNode;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);
}
