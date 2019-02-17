package com.taotaoke.portal.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotaoke.common.pojo.TaotaoResult;
import com.taotaoke.common.util.HttpClientUtil;
import com.taotaoke.common.util.JsonUtils;
import com.taotaoke.pojo.TbContent;
import com.taotaoke.portal.service.ContentService;


@Service
public class ContentServiceImpl implements ContentService {
	
	@Value("${REST_BASE_URL}")
	String REST_BASE_URL;
	
	@Value("${REST_INDEX_AD_URL}")
	String REST_INDEX_AD_URL;

	@Override
	public String getContentList() {
		//调用服务层服务
		 String result = HttpClientUtil.doGet(REST_BASE_URL+REST_INDEX_AD_URL);
		 try {
			//把字符串转换成 taotaoresult
			  TaotaoResult taotaoResult = TaotaoResult.formatToList(result, TbContent.class);
			//取内容列表
			  List<TbContent> list = (List<TbContent>) taotaoResult.getData();
			  //创建一个JSP页面要求的pojo列表
			  List<Map> listmap = new ArrayList<>();
			  for(TbContent content:list){
				  Map map = new HashMap<>();
				  map.put("src", content.getPic());
				  map.put("height", 240);
				  map.put("alt", content.getSubTitle());
				  map.put("width", 670);
				  map.put("widthB", 550);
				  map.put("href", content.getUrl());
				  map.put("heightB", 240);
				  map.put("srcB", content.getPic2());
				  listmap.add(map);
			  }
			  return JsonUtils.objectToJson(listmap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
