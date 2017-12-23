package cn.hmst.impl;

import cn.hmst.comon.util.LevelUtil;
import cn.hmst.dao.SysDeptMapper;
import cn.hmst.dto.SysDeptDto;
import cn.hmst.pojo.SysDept;
import cn.hmst.service.SysTreesService;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hm on 2017/12/23.
 */
@Service
public class SysTreesServiceImpl implements SysTreesService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysDeptDto> deptTree() {
        List<SysDept> deptList = sysDeptMapper.getAllDept();
        List<SysDeptDto> dtoList = Lists.newArrayList();
        for (SysDept dept :
                deptList) {
            SysDeptDto dto = SysDeptDto.adapt(dept);
            dtoList.add(dto);
        }
        List<SysDeptDto> dtoTreeList =  deptListToTree(dtoList);
        return dtoTreeList;
    }

    public  List<SysDeptDto> deptListToTree(List<SysDeptDto> dtoList){
        if(CollectionUtils.isEmpty(dtoList)){
            return  Lists.newArrayList();
        }
        Multimap<String,SysDeptDto> dtoMap = ArrayListMultimap.create();
        List<SysDeptDto> rootList = Lists.newArrayList();
        for (SysDeptDto dto:
             dtoList) {
            dtoMap.put(dto.getLevel(),dto);
            if(LevelUtil.ROOT.equals(dto.getLevel())){
                rootList.add(dto);
            }
        }
        Collections.sort(rootList, new Comparator<SysDeptDto>() {
            @Override
            public int compare(SysDeptDto o1, SysDeptDto o2) {
                return o1.getSeq()-o2.getSeq();
            }
        });
        // 递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, dtoMap);
        return rootList;
    }

    // level:0, 0, all 0->0.1,0.2
    // level:0.1
    // level:0.2
    public void transformDeptTree(List<SysDeptDto> deptLevelList, String level, Multimap<String, SysDeptDto> levelDeptMap) {
        for (int i = 0; i < deptLevelList.size(); i++) {
            // 遍历该层的每个元素
            SysDeptDto deptLevelDto = deptLevelList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
            // 处理下一层
            List<SysDeptDto> tempDeptList = (List<SysDeptDto>) levelDeptMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempDeptList)) {
                // 排序
                Collections.sort(tempDeptList, deptSeqComparator);
                // 设置下一层部门
                deptLevelDto.setDeptDtoList(tempDeptList);
                // 进入到下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }
    public Comparator<SysDeptDto> deptSeqComparator = new Comparator<SysDeptDto>() {
        public int compare(SysDeptDto o1, SysDeptDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

/*    public Comparator<AclModuleLevelDto> aclModuleSeqComparator = new Comparator<AclModuleLevelDto>() {
        public int compare(AclModuleLevelDto o1, AclModuleLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    public Comparator<AclDto> aclSeqComparator = new Comparator<AclDto>() {
        public int compare(AclDto o1, AclDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };*/
}
