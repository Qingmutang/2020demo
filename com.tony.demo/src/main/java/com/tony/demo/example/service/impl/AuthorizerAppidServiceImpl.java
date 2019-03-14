package com.tony.demo.example.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.tony.demo.example.bean.AuthorizerAppid;
import com.tony.demo.example.bean.AuthorizerAppidExample;
import com.tony.demo.example.constant.ExampleRequestValidate;
import com.tony.demo.example.mapper.AuthorizerAppidMapper;
import com.tony.demo.example.service.IAuthorizerAppidService;
import com.tony.demo.example.vo.req.AuthorizerAppidReq;
import com.tony.demo.example.vo.resp.AuthorizerAppidListResp;
import com.tony.demo.example.vo.resp.AuthorizerAppidResp;
import com.tony.demo.misc.base.BaseModel;
import com.tony.demo.misc.base.BaseQueryResult;
import com.tony.demo.misc.base.Response;
import com.tony.demo.misc.utils.CollectionNullPropertyUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthorizerAppidServiceImpl implements IAuthorizerAppidService {

	@Autowired
	AuthorizerAppidMapper authorizerAppidMapper;

	@Override
	public Response<AuthorizerAppidResp> getAuthorizerAppidInfo(AuthorizerAppidReq req) {
		log.info("获取信息 start-> req:{}", JSONObject.toJSON(req));
		AuthorizerAppidResp resp = new AuthorizerAppidResp();

		try {

			AuthorizerAppidExample example = new AuthorizerAppidExample();
			AuthorizerAppidExample.Criteria criteria = example.createCriteria();

			if (StringUtils.isNotBlank(req.getComponentAppid())) {
				criteria.andComponentAppidEqualTo(req.getComponentAppid());
			}

			if (StringUtils.isNotBlank(req.getAuthorizerAppid())) {
				criteria.andAuthorizerAppidLike("%" + req.getAuthorizerAppid() + "%");
			}

			if (StringUtils.isNotBlank(req.getGroupId())) {
				criteria.andGroupIdEqualTo(req.getGroupId());
			}

			if (StringUtils.isNotBlank(req.getUserName())) {
				criteria.andUserNameEqualTo(req.getUserName());
			}

			if (!CollectionUtils.isEmpty(req.getStatusList())) {
				criteria.andStatusIn(req.getStatusList());
			}

			List<AuthorizerAppid> exampleOut = authorizerAppidMapper.selectByExample(example);
			if (!CollectionUtils.isEmpty(exampleOut)) {
				AuthorizerAppid authorizerAppid = exampleOut.get(0);
				// BeanUtils.copyProperties(authorizerAppid, resp,
				// CollectionNullPropertyUtil.getNullPropertyNames(authorizerAppid));
				BeanUtils.copyProperties(authorizerAppid, resp);
			}
		} catch (Exception e) {
			log.error("获取信息 error-> e:{}", JSONObject.toJSON(e));
		}

		log.info("获取信息 end-> resp:{}", JSONObject.toJSON(resp));
		return new Response<AuthorizerAppidResp>(resp);
	}

	@Override
	public Response<AuthorizerAppidListResp> queryAuthorizerAppidInfoByPageInfo(AuthorizerAppidReq req) {
		log.info("获取信息 start-> req:{}", JSONObject.toJSON(req));

		AuthorizerAppidListResp resp = new AuthorizerAppidListResp();
		List<AuthorizerAppidResp> respList = new ArrayList<AuthorizerAppidResp>();
		resp.setAuthorizerAppidList(respList);

		try {
			AuthorizerAppidExample example = new AuthorizerAppidExample();
			AuthorizerAppidExample.Criteria criteria = example.createCriteria();

			if (StringUtils.isNotBlank(req.getComponentAppid())) {
				criteria.andComponentAppidEqualTo(req.getComponentAppid());
			}

			if (StringUtils.isNotBlank(req.getAuthorizerAppid())) {
				/** 模糊查询 */
				criteria.andAuthorizerAppidLike("%" + req.getAuthorizerAppid() + "%");
			}

			if (StringUtils.isNotBlank(req.getGroupId())) {
				criteria.andGroupIdEqualTo(req.getGroupId());
			}

			if (StringUtils.isNotBlank(req.getUserName())) {
				criteria.andUserNameEqualTo(req.getUserName());
			}

			if (!CollectionUtils.isEmpty(req.getStatusList())) {
				/** 集合匹配 */
				criteria.andStatusIn(req.getStatusList());
			}

			BaseQueryResult<AuthorizerAppid> result = BaseModel.selectByPage(authorizerAppidMapper, example,
					req.getPageInfo(), true);

			if (result != null) {
				/** 添加分页信息 */
				resp.setPageInfo(result.getPage());

				/** 填充集合信息 */
				if (!CollectionUtils.isEmpty(result.getList())) {
					List<AuthorizerAppid> resultList = result.getList();
					for (AuthorizerAppid authorizerAppid : resultList) {
						AuthorizerAppidResp respListItem = new AuthorizerAppidResp();
						BeanUtils.copyProperties(authorizerAppid, respListItem);
						respList.add(respListItem);
					}
				}
			}
		} catch (Exception e) {
			log.error("获取信息 error-> e:{}", JSONObject.toJSON(e));
		}

		log.info("获取信息 end-> resp:{}", JSONObject.toJSON(resp));
		return new Response<AuthorizerAppidListResp>(resp);
	}

	@Override
	public Response<AuthorizerAppidResp> insertAuthorizerAppidInfo(AuthorizerAppidReq req) {
		log.info("插入信息 start-> req:{}", JSONObject.toJSON(req));
		Response<AuthorizerAppidResp> resp = new Response<AuthorizerAppidResp>();

		if (!req.validateReq().equals(ExampleRequestValidate.SUCCESS)) {
			ExampleRequestValidate validateReq = req.validateReq();
			resp.setResultCode(validateReq.getCode() + "");
			resp.setMsg(validateReq.getMessage());
			return resp;
		}

		AuthorizerAppidResp respItem = new AuthorizerAppidResp();
		resp.setResult(respItem);
		try {
            boolean flag =true;
			AuthorizerAppidExample example = new AuthorizerAppidExample();
			AuthorizerAppidExample.Criteria criteria = example.createCriteria();
			criteria.andGroupIdEqualTo(req.getGroupId());
			criteria.andAuthorizerAppidEqualTo(req.getAuthorizerAppid());
			criteria.andDeletedEqualTo((byte)0);

			List<AuthorizerAppid> exampleList = authorizerAppidMapper.selectByExample(example);
			if(CollectionUtils.isEmpty(exampleList)) {
				//insert
				AuthorizerAppid insert = new AuthorizerAppid();
				//LocalDateTime now = LocalDateTime.now(); 
				Long now = System.currentTimeMillis();
				insert.setCreatedTime(now);
				insert.setUpdatedTime(now);
				
				BeanUtils.copyProperties(req, insert);
				flag = authorizerAppidMapper.insertSelective(insert)>0;
				BeanUtils.copyProperties(insert, respItem);
				
			}else {
				//update
				AuthorizerAppid update = exampleList.get(0);
				//redis 如果有就需要处理
				
				BeanUtils.copyProperties(req, update, CollectionNullPropertyUtil.getNullPropertyNames(req)); 
				update.setUpdatedTime(System.currentTimeMillis()); 
				flag = authorizerAppidMapper.updateByPrimaryKey(update)>0;
				BeanUtils.copyProperties(update, respItem);
			}
			
			if(!flag) {
				resp.setResultCode("-1");
				resp.setMsg("插入失败");
				return resp;
			}

		} catch (Exception e) {
			log.error("插入信息 error-> e:{}", JSONObject.toJSON(e));
		}

		log.info("插入信息 end-> resp:{}", JSONObject.toJSON(resp));

		return resp;
	}

}
