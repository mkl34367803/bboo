package com.smart.oo.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.BaseRefundEntity;
import com.smart.comm.entity.RefundEntity;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.from.RefundReportVo;
import com.smart.oo.from.RefundVo;
import com.smart.oo.service.IRefundService;

@Service("BBOORefundServiceImpl")
public class RefundServiceImpl implements IRefundService {
	
	@Autowired
	private FactoryDaoImpl factoryDao;

	@Override
	public List<RefundEntity> queryRefunds(RefundEntity entity)
			throws Exception {
		return this.factoryDao.getRefundDao().queryRefunds(entity);
	}

	@Override
	public void saveRefound(RefundEntity entity) throws Exception {
		this.factoryDao.getRefundDao().saveRefound(entity);
	}

	@Override
	public void updateRefound(RefundEntity entity) throws Exception {
		this.factoryDao.getRefundDao().updateRefound(entity);
	}

	@Override
	public void deleteRefound(String id) throws Exception {
		this.factoryDao.getRefundDao().deleteRefound(id);
	}

	@Override
	public List<RefundEntity> queryByFkrid(String fkrid) throws Exception {
		List<Object[]> list = this.factoryDao.getRefundDao().queryByFkrid(fkrid);
		List<RefundEntity> refundList = null;
		if (null != list && list.size() > 0) {
			refundList = parseRefundList(list);
			
		}
		return refundList;
	}

	@Override
	public List<RefundEntity> queryByBaseRefund(BaseRefundEntity br) throws Exception {
		List<Object[]> list = this.factoryDao.getRefundDao().queryByBaseRefund(br);
		List<RefundEntity> refundList = null;
		if (null != list && list.size() > 0) {
			refundList = parseRefundList(list);
			
		}
		return refundList;
	}
	
	private List<RefundEntity> parseRefundList(List<Object[]> list) {
		List<RefundEntity> refundList =new ArrayList<RefundEntity>();
		RefundEntity entity = null;
		for (Object[] objs : list) {
			int i = 0;
			entity = new RefundEntity();
			entity.setId(parseStr(objs[i++]));
			entity.setActRefund(parseDouble(objs[i++]));
			entity.setActRefund2(parseDouble(objs[i++]));
			entity.setBaseFace(parseDouble(objs[i++]));
			entity.setBaseFace2(parseDouble(objs[i++]));
			entity.setCreateTime(parseStr(objs[i++]));
			entity.setFace(parseDouble(objs[i++]));
			entity.setFace2(parseDouble(objs[i++]));
			entity.setFee(parseDouble(objs[i++]));
			entity.setFee2(parseDouble(objs[i++]));
			entity.setFkrid(parseStr(objs[i++]));
			entity.setFltid(parseStr(objs[i++]));
			if (objs[i] != null) {
				entity.setIslock(parseInt(objs[i++]));
			} else {
				i++;
			}
			if (objs[i] != null) {
				entity.setIssubmit(parseInt(objs[i++]));
			} else {
				i++;
			}
			if (objs[i] != null) {
				entity.setIssubmit2(parseInt(objs[i++]));
			} else {
				i++;
			}
			entity.setIsvoid(parseInt(objs[i++]));
			entity.setIsvoid2(parseInt(objs[i++]));
			entity.setPsgid(parseStr(objs[i++]));
			entity.setRate(parseDouble(objs[i++]));
			entity.setRate2(parseDouble(objs[i++]));
			entity.setRefund(parseDouble(objs[i++]));
			entity.setRefund2(parseDouble(objs[i++]));
			entity.setRtype(parseInt(objs[i++]));
			entity.setRtype2(parseInt(objs[i++]));
			if (objs[i] != null) {
				entity.setTax(parseDouble(objs[i++]));
			} else {
				i++;
			}
			if (objs[i] != null) {
				entity.setTax2(parseDouble(objs[i++]));
			} else {
				i++;
			}
			if (objs[i] != null) {
				entity.setVerifyAmount(parseDouble(objs[i++]));
			} else {
				i++;
			}
			if (objs[i] != null) {
				entity.setVerifyAmount2(parseDouble(objs[i++]));
			} else {
				i++;
			}
			entity.setYq(parseDouble(objs[i++]));
			entity.setYq2(parseDouble(objs[i++]));
			refundList.add(entity);
		}
		return refundList;
	}
	
	private String parseStr(Object obj) {
		return obj == null ? "" : obj.toString();
	}
	private double parseDouble(Object obj) {
		return obj == null ? 0.0 : Double.parseDouble(obj.toString());
	}
	private int parseInt(Object obj) {
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}
	private long parseLong(Object obj) {
		return obj == null ? 0 : Long.parseLong(obj.toString());
	}

	@Override
	public List<RefundReportVo> downloadRefund(RefundVo vo) throws Exception {
		List<Object[]> list = this.factoryDao.getRefundDao().downloadRefund(vo);
		List<RefundReportVo> refundList = null;
		if (null != list && list.size() > 0) {
			refundList = new ArrayList<RefundReportVo>();
			RefundReportVo refundVo = null;
			for (Object[] objects : list) {
				refundVo = parseRefundVo(objects);
				refundList.add(refundVo);
			}
		}
		return refundList;
	}
	
	private RefundReportVo parseRefundVo(Object[] objs) throws Exception {
		RefundReportVo vo = new RefundReportVo();
		int i = 0;
		vo.setPurchasePlaceCh(parseStr(objs[i++]));
		vo.setDistributorCh(parseStr(objs[i++]));
		vo.setShopNameCh(parseStr(objs[i++]));
		vo.setOrderNo(parseStr(objs[i++]));
		vo.setOrderCreateDate(parseStr(objs[i++]));
		vo.setFlightType(parseStr(objs[i++]));
		vo.setPnrCode(parseStr(objs[i++]));
		
		vo.setName(parseStr(objs[i++]));
		vo.setAgeType(parseStr(objs[i++]));
		vo.setEticketNum(parseStr(objs[i++]));
		vo.setAddTime(parseStr(objs[i++]));
		
		vo.setCarrier(parseStr(objs[i++]));
		vo.setFlightNum(parseStr(objs[i++]));
		vo.setShareNum(parseStr(objs[i++]));
		vo.setCabin(parseStr(objs[i++]));
		vo.setActCabin(parseStr(objs[i++]));
		vo.setActFltno(parseStr(objs[i++]));
		vo.setDepAircode(parseStr(objs[i++]));
		vo.setArrAircode(parseStr(objs[i++]));
		vo.setDepartureDate(parseStr(objs[i++]));
		vo.setDepartureTime(parseStr(objs[i++]));
		
		vo.setOperator(parseStr(objs[i++]));
		vo.setIsdelay(parseStr(objs[i++]));
		vo.setPcount(parseLong(objs[i++]));
		vo.setRefundTime(parseStr(objs[i++]));
		vo.setOrderStatus(parseStr(objs[i++]));
		
		vo.setFace(parseDouble(objs[i++]));
		vo.setBaseFace(parseDouble(objs[i++]));
		vo.setYq(parseDouble(objs[i++]));
		vo.setTax(parseDouble(objs[i++]));
		vo.setRate(parseDouble(objs[i++]));
		vo.setFee(parseDouble(objs[i++]));
		vo.setRefund(parseDouble(objs[i++]));
		vo.setActRefund(parseDouble(objs[i++]));
		
		vo.setFace2(parseDouble(objs[i++]));
		vo.setBaseFace2(parseDouble(objs[i++]));
		vo.setYq2(parseDouble(objs[i++]));
		vo.setTax2(parseDouble(objs[i++]));
		vo.setRate2(parseDouble(objs[i++]));
		vo.setFee2(parseDouble(objs[i++]));
		vo.setRefund2(parseDouble(objs[i++]));
		vo.setActRefund2(parseDouble(objs[i++]));
		
		vo.setLr(vo.getFee2()-vo.getFee());
		return vo;
	}

}
