package com.xuecheng.content.service.impl;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.service.CourseCategoryService;
import com.xuecheng.model.dto.CourseCategoryTreeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
@Slf4j
public class CourseCategoryServiceImpl implements CourseCategoryService {
    @Autowired
    CourseCategoryMapper courseCategoryMapper;
//    TODO 这里实现较为复杂，有点听不懂，需要再次听一次
    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
//        调用 mapper 递归查询出分类信息
        List<CourseCategoryTreeDto> list =  courseCategoryMapper.selectTreeNodes(id);
//        封装成 List<CourseCategoryTreeDto> 返回
//        转换方法：先将 list 转换为 map, key 就是结点的 id ， value 就是 CourseCategoryTreeDto 对象
       Map<String,CourseCategoryTreeDto> map = list.stream().filter(item->!id.equals(item.getId()))/*把根节点排除*/.collect(Collectors.toMap(key ->
               key.getId(), value -> value,(key1,key2)->key2));
//       定义一个 list 作为最终返回的 list
      List<CourseCategoryTreeDto> courseCategoryTreeDtos=  new ArrayList<>();
//        从头遍历 List<CourseCategoryTreeDto> ，一边遍历一边找子节点放在父节点的 childrenTreeNode
        list.stream().filter(item->!id.equals(item.getId())).forEach(item->{
            if(item.getParentid().equals(id)){
                courseCategoryTreeDtos.add(item);
            }
//            找到节点的父节点
         CourseCategoryTreeDto courseCategoryTreeDto=   map.get(item.getParentid());
            if(courseCategoryTreeDto != null){
                if(courseCategoryTreeDto.getChildrenTreeNodes() == null){
//                如果该父节点的 ChildrenTreeNodes 为空，就创建一个 ArrayList
                    courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<>());
                }
                //            找到每个节点的子节点放在父节点的 childrenTreeNodes;
                courseCategoryTreeDto.getChildrenTreeNodes().add(item);
            }
         });
        return  courseCategoryTreeDtos;
    }
}















