package cn.hmst.content.service;

import cn.hmst.comon.pojo.EasyUITreeNode;
import cn.hmst.comon.pojo.HmResult;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
public interface ContentCategoryService {
    HmResult addConentCategory(long parentId, String name);
    public List<EasyUITreeNode> getContentCatList(long parentId);
}
