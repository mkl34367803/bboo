package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.AirPriceEntity;
import com.smart.framework.base.Page;

public interface IAirPriceDao {

	String saveData(AirPriceEntity en) throws Exception;

	void del(AirPriceEntity entity) throws Exception;

	List<AirPriceEntity> findDatas(AirPriceEntity en) throws Exception;

	List<AirPriceEntity> findDatas(AirPriceEntity en, Page p) throws Exception;

	AirPriceEntity barrierById(String id) throws Exception;

}
