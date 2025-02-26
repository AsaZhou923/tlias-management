package com.example.service.impl;

import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.*;
import com.example.service.EmpLogService;
import com.example.service.EmpService;
import com.example.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    /**
     * 分页查询
     * @return
     * sql语句结尾不能有分号
     * pagehelper仅能对紧跟在其后的第一条查询语句进行分页处理
     */
    @Override
    public PageResult<Emp> Page(EmpQueryParam empQueryParam){
        //设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //查询数据
        List<Emp> empList = empMapper.list(empQueryParam);
        //封装数据 将empList强转为Page类型
        Page<Emp> p = (Page<Emp>) empList;
        //返回结果
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    @Transactional
    @Override
    public void save(Emp emp) {
        try {
            //1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2.保存员工工作经历
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                //遍历集合，为empId赋值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工：" + emp);
            empLogService.insertLog(empLog);
        }


    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        //批量删除员工基本信息
        empMapper.deleteByIds(ids);

        //批量删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //更新员工工作经历
        //删除原来的
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //插入新的
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //遍历集合，为empId赋值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }

    }

    @Override
    public List<Emp> queryAll() {

        return empMapper.queryAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        //查询用户信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        //判断是否存在这个员工
        if(e != null){
            log.info("登录成功{}",e);
            //生成JWT令牌
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);

            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
        }

        //不存在返回null
        return null;
    }
}
