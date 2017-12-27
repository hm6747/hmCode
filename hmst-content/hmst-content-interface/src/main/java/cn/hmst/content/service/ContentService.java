package cn.hmst.content.service;

import cn.hmst.comon.pojo.HmResult;
import cn.hmst.pojo.TbContent;

import java.util.List;

/**
 * Created by Administrator on 2017/12/7 0007.
 */
public interface ContentService {
    HmResult addContent(TbContent tbContent);
    List<TbContent> getContentListByCid(long cid);
}
