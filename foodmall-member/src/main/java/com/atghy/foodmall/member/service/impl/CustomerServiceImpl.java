package com.atghy.foodmall.member.service.impl;

import com.atghy.foodmall.common.constant.CouponConstant;
import com.atghy.foodmall.member.Exception.PhoneExistException;
import com.atghy.foodmall.member.Exception.UsernameExistException;
import com.atghy.foodmall.member.feign.CouponFeignService;
import com.atghy.foodmall.member.vo.CustomerLoginVo;
import com.atghy.foodmall.member.vo.CustomerRegistVo;
import com.atghy.foodmall.member.vo.UserScoreInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atghy.foodmall.common.utils.PageUtils;
import com.atghy.foodmall.common.utils.Query;

import com.atghy.foodmall.member.dao.CustomerDao;
import com.atghy.foodmall.member.entity.CustomerEntity;
import com.atghy.foodmall.member.service.CustomerService;
import org.springframework.transaction.annotation.Transactional;


@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, CustomerEntity> implements CustomerService {

    @Autowired
    CouponFeignService couponFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustomerEntity> page = this.page(
                new Query<CustomerEntity>().getPage(params),
                new QueryWrapper<CustomerEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void regist(CustomerRegistVo vo) {
        System.out.println(vo);
        CustomerDao customerDao = this.baseMapper;
        CustomerEntity customerEntity = new CustomerEntity();
        //验证用户名和手机号唯一性 让controller感知异常
        checkPhoneUnique(vo.getPhone());
        checkUsernameUnique(vo.getUserName());
        //设置基础信息
        customerEntity.setUsername(vo.getUserName());
        customerEntity.setMobile(vo.getPhone());
        customerEntity.setNickname(vo.getUserName());
        customerEntity.setBirthday(new Date());
        customerEntity.setUpdateTime(new Date());
        Long i =Long.valueOf(UUID.randomUUID().toString().replace("-", "").hashCode());
        Long uuid = i < 0 ? -i:i;
        System.out.println(uuid);
        customerEntity.setUuid(uuid);
        customerEntity.setStatus(0);
        //密码加密存储
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(vo.getPassword());
        customerEntity.setPassword(encode);
        customerDao.insert(customerEntity);
        //设置初始等级
        UserScoreInfoVo userScoreInfoVo = new UserScoreInfoVo();
        userScoreInfoVo.setUuid(uuid);
        userScoreInfoVo.setScore(CouponConstant.VIP0_ENUM.getScore());
        userScoreInfoVo.setLevel(CouponConstant.VIP0_ENUM.getCode());
        userScoreInfoVo.setDiscount(CouponConstant.VIP0_ENUM.getDiscount());
        userScoreInfoVo.setStatus(0);
        couponFeignService.save(userScoreInfoVo);
    }

    @Override
    public CustomerEntity login(CustomerLoginVo vo) {
        String username = vo.getUserName();
        String password = vo.getPassword();
        QueryWrapper<CustomerEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username).or().eq("mobile",username);
        CustomerEntity customerEntity = this.baseMapper.selectOne(queryWrapper);
        if (customerEntity == null){
            //登录失败
            return null;
        }else{
            //1-获取数据库的password
            String passwordDb = customerEntity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //2-密码匹配
            boolean matches = passwordEncoder.matches(password, passwordDb);
            if (matches){
                return customerEntity;
            }else {
                return null;
            }
        }
    }

    private void checkUsernameUnique(String username) {
        CustomerDao customerDao = this.baseMapper;
        Integer count = customerDao.selectCount(new QueryWrapper<CustomerEntity>().eq("username", username));
        if (count>0){
            throw new UsernameExistException();
        }
    }

    private void checkPhoneUnique(String mobile) {
        CustomerDao customerDao = this.baseMapper;
        Integer count = customerDao.selectCount(new QueryWrapper<CustomerEntity>().eq("mobile", mobile));
        if (count > 0){
            throw new PhoneExistException();
        }
    }

}