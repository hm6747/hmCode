package cn.hmst.service;

import cn.hmst.common.pojo.EasyUITreeNode;
import cn.hmst.dto.TbItemCatDto;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);
    List<TbItemCatDto> getItemCatTree();
}
