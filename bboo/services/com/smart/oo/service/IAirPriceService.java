package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.AirPriceEntity;
import com.smart.framework.base.Page;

public interface IAirPriceService {

	String saveData(AirPriceEntity en) throws Exception;

	void del(AirPriceEntity entity) throws Exception;

	void saveDataAndDelByLine(List<AirPriceEntity> ens) throws Exception;

	void saveDataAndDelByKey(List<AirPriceEntity> ens) throws Exception;

	List<AirPriceEntity> findDatas(AirPriceEntity en) throws Exception;

	List<AirPriceEntity> findDatas(AirPriceEntity en, Page p) throws Exception;

	AirPriceEntity barrierById(String id) throws Exception;

}
