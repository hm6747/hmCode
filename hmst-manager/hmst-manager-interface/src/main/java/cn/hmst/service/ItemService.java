package cn.hmst.service;

import cn.hmst.common.pojo.EasyUIDataGridResult;
import cn.hmst.common.pojo.HmResult;
import cn.hmst.pojo.TbItem;

/**
 * Created by Administrator on 2017/10/30 0030.
 */
public interface ItemService {
    TbItem getItemById(long itemId);
    EasyUIDataGridResult getItemList(int page, int rows);
    HmResult addItem(TbItem tbItem , String desc);
}
