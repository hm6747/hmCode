package cn.hmst.impl;

import cn.hmst.comon.util.LevelUtil;
import cn.hmst.dao.SysAclModuleMapper;
import cn.hmst.dao.SysDeptMapper;
import cn.hmst.dto.AclDto;
import cn.hmst.dto.AclModuleLevelDto;
import cn.hmst.dto.SysDeptDto;
import cn.hmst.pojo.SysAcl;
import cn.hmst.pojo.SysAclModule;
import cn.hmst.pojo.SysDept;
import cn.hmst.service.SysCoreService;
import cn.hmst.service.SysTreesService;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hm on 2017/12/23.
 */
@Service
public class SysTreesServiceImpl implements SysTreesService {
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Resource
    private SysAclModuleMapper sysAclModuleMapper;
    public List<AclModuleLevelDto> roleTree(int roleId) {
        // 1、当前用户已分配的权限点
        List<SysAcl> userAclList = SysCoreServiceImpl.getCurrentUserAclList();
        // 2、当前角色分配的权限点
        List<SysAcl> roleAclList = SysCoreService.getRoleAclList(roleId);
        // 3、当前系统所有权限点
        List<AclDto> aclDtoList = Lists.newArrayList();

        Set<Integer> userAclIdSet = userAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());
        Set<Integer> roleAclIdSet = roleAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());

        List<SysAcl> allAclList = sysAclMapper.getAll();
        for (SysAcl acl : allAclList) {
            AclDto dto = AclDto.adapt(acl);
            if (userAclIdSet.contains(acl.getId())) {
                dto.setHasAcl(true);
            }
            if (roleAclIdSet.contains(acl.getId())) {
                dto.setChecked(true);
            }
            aclDtoList.add(dto);
        }
        return aclListToTree(aclDtoList);
    }

    public List<AclModuleLevelDto> aclListToTree(List<AclDto> aclDtoList) {
        if (CollectionUtils.isEmpty(aclDtoList)) {
            return Lists.newArrayList();
        }
        List<AclModuleLevelDto> aclModuleLevelList = aclModuleTree();

        Multimap<Integer, AclDto> moduleIdAclMap = ArrayListMultimap.create();
        for(AclDto acl : aclDtoList) {
            if (acl.getStatus() == 1) {
                moduleIdAclMap.put(acl.getAclModuleId(), acl);
            }
        }
        bindAclsWithOrder(aclModuleLevelList, moduleIdAclMap);
        return aclModuleLevelList;
    }

    public void bindAclsWithOrder(List<AclModuleLevelDto> aclModuleLevelList, Multimap<Integer, AclDto> moduleIdAclMap) {
        if (CollectionUtils.isEmpty(aclModuleLevelList)) {
            return;
        }
        for (AclModuleLevelDto dto : aclModuleLevelList) {
            List<AclDto> aclDtoList = (List<AclDto>)moduleIdAclMap.get(dto.getId());
            if (CollectionUtils.isNotEmpty(aclDtoList)) {
                Collections.sort(aclDtoList, aclSeqComparator);
                dto.setAclList(aclDtoList);
            }
            bindAclsWithOrder(dto.getAclModuleList(), moduleIdAclMap);
        }
    }

    @Override
    public List<AclModuleLevelDto> aclModuleTree() {
        List<SysAclModule> aclModuleList = sysAclModuleMapper.getAllAclModule();
        List<AclModuleLevelDto> dtoList = Lists.newArrayList();
        for (SysAclModule aclModule : aclModuleList) {
            dtoList.add(AclModuleLevelDto.adapt(aclModule));
        }
        return aclModuleListToTree(dtoList);
    }
    public List<AclModuleLevelDto> aclModuleListToTree(List<AclModuleLevelDto> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Lists.newArrayList();
        }
        // level -> [aclmodule1, aclmodule2, ...] Map<String, List<Object>>
        Multimap<String, AclModuleLevelDto> levelAclModuleMap = ArrayListMultimap.create();
        List<AclModuleLevelDto> rootList = Lists.newArrayList();

        for (AclModuleLevelDto dto : dtoList) {
            levelAclModuleMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
        Collections.sort(rootList, aclModuleSeqComparator);
        transformAclModuleTree(rootList, LevelUtil.ROOT, levelAclModuleMap);
        return rootList;
    }
    public void transformAclModuleTree(List<AclModuleLevelDto> dtoList, String level, Multimap<String, AclModuleLevelDto> levelAclModuleMap) {
        for (int i = 0; i < dtoList.size(); i++) {
            AclModuleLevelDto dto = dtoList.get(i);
            String nextLevel = LevelUtil.calculateLevel(level, dto.getId());
            List<AclModuleLevelDto> tempList = (List<AclModuleLevelDto>) levelAclModuleMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempList)) {
                Collections.sort(tempList, aclModuleSeqComparator);
                dto.setAclModuleList(tempList);
                transformAclModuleTree(tempList, nextLevel, levelAclModuleMap);
            }
        }
    }

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


  public Comparator<AclModuleLevelDto> aclModuleSeqComparator = new Comparator<AclModuleLevelDto>() {
        public int compare(AclModuleLevelDto o1, AclModuleLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

   public Comparator<AclDto> aclSeqComparator = new Comparator<AclDto>() {
        public int compare(AclDto o1, AclDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };



}
