package com.smart.comm.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.smart.comm.entity.DropDataEntity;
import com.smart.comm.entity.FinanceSaleEntity;
import com.smart.comm.entity.TicketStateEntity;
import com.smart.entity.User;
import com.smart.oo.domain.res.DetnFareVO;
import com.smart.oo.domain.res.DetnItineraryVO;
import com.smart.oo.domain.res.DetnLegVO;
import com.smart.oo.domain.res.DetnPassengerVO;
import com.smart.oo.domain.res.DetnResult;
import com.smart.oo.domain.res.DetnSegmentVO;
import com.smart.oo.domain.res.DetrBy517Result;
import com.smart.oo.domain.res.DetrByTnoResult;
import com.smart.oo.domain.res.DetrDetail;
import com.smart.oo.domain.res.Lines;
import com.smart.oo.domain.res.TfareDetrResult;
import com.smart.oo.domain.res.TicketInfo;
import com.smart.oo.report.FinanceCtrByUploadVO;
import com.smart.oo.report.OrderFlightForDropVO;
import com.smart.oo.report.QueryTicketNoByUploadVO;
import com.smart.oo.vo.DetrFltVO;
import com.smart.oo.vo.DetrInfoResVO;
import com.smart.utils.DateUtils;
import com.smart.utils.StringUtils;
import com.smart.utils.UniqId;

public class ObjectToObiectUtils {

	public static TicketStateEntity toTicketStateEntity(DetrInfoResVO res,
			TicketStateEntity t) {
		TicketStateEntity newt = new TicketStateEntity();
		newt.setLastime(DateUtils.getDateys());
		newt.setId(t.getId());
		if (res != null) {
			List<DetrFltVO> flts = res.getFlt();
			String state = "";
			// fno,dep,arr,date,dtm,cabin|
			String fltinfo = "";
			if (flts != null && flts.size() > 0) {
				for (DetrFltVO flt : flts) {
					if (StringUtils.isNotEmpty(flt.getState())) {
						state += flt.getState() + ",";
					}
					if (StringUtils.isNotEmpty(flt.getFltno())) {
						fltinfo = flt.getFltno() + "," + flt.getDepCode() + ","
								+ flt.getArrCode() + "," + flt.getDepDate()
								+ "," + flt.getOrgt() + "," + flt.getCabin()
								+ "|";
					}
				}
			}
			newt.setPlans(res.getPlan() != null ? res.getPlan() : "");
			newt.setState(state);
			newt.setCertno(res.getCertno() != null ? res.getCertno() : "");
			newt.setPassengerName2(res.getPsgname());
			String msg = "";
			if (!StringUtils.isEmpty(res.getData())) {
				msg += res.getData();
			}
			if (!StringUtils.isEmpty(res.getMsg())) {
				msg += res.getMsg();
			}
			if (msg != null) {
				msg = msg.replace("\r", " ").replace("\n", " ")
						.replaceAll(" +", " ").trim();
			}
			if (msg != null && msg.length() > 900) {
				newt.setMessages(msg.substring(0, 900));
			} else {
				newt.setMessages(msg);
			}
			newt.setFltinfo(fltinfo);
			if ("0".equals(res.getCode())) {
				newt.setQueryok(1);
			} else if ("1".equals(res.getCode())) {
				newt.setQueryok(2);
			} else {
				newt.setQueryok(1);
			}
		} else {
			newt.setQueryok(1);
		}
		return newt;
	}

	public static DetrInfoResVO toDetrInfoResVO(DetrByTnoResult xtyRs,
			TicketStateEntity ticket) {
		DetrInfoResVO resV = null;
		if (xtyRs != null) {
			List<DetrDetail> detrs = xtyRs.getDetrs();
			resV = new DetrInfoResVO();
			resV.setNo(ticket.getNo());
			resV.setCertno("");
			resV.setPlan("DETR");
			resV.setPsgname(ticket.getPassengerName());
			resV.setCode(xtyRs.getCode() != null ? String.valueOf(xtyRs
					.getCode()) : "0");
			resV.setMsg(xtyRs.getMsg());
			resV.setData(xtyRs.getTxt() != null ? xtyRs.getTxt()
					.replaceAll(" +", " ").trim() : "");
			resV.setCompany("");
			resV.setFace(0d);
			resV.setOffcode("");
			resV.setOffice("");
			resV.setTax(0d);
			resV.setYq(0d);
			resV.setTotal(0d);
			if (detrs != null && detrs.size() > 0) {
				List<DetrFltVO> flts = new ArrayList<DetrFltVO>();
				for (DetrDetail l : detrs) {
					DetrFltVO f = new DetrFltVO();
					f.setArrCh("");
					f.setArrCode(l.getArrCityCode());
					f.setArrt("");
					f.setCabin("");
					f.setDepCh("");
					f.setDepCode(l.getDepCityCode());
					f.setDepDate(l.getDepdate());
					f.setFltno(l.getFlightNo());
					f.setOrgt("");
					f.setState(l.getStatus());
					flts.add(f);
				}
				resV.setFlt(flts);
			}
		}
		return resV;
	}

	public static DetrInfoResVO toDetrInfoResVO(DetnResult xtyRs,
			TicketStateEntity ticket) {

		DetrInfoResVO resV = null;
		if (xtyRs != null) {
			DetnItineraryVO itiner = xtyRs.getItinerary();
			resV = new DetrInfoResVO();
			resV.setNo(ticket.getNo());
			resV.setCertno("");
			resV.setPlan("DETN");
			if (itiner != null && itiner.getPassenger() != null
					&& itiner.getPassenger().size() > 0) {
				DetnPassengerVO p = itiner.getPassenger().iterator().next();
				resV.setPsgname(p.getName());
				resV.setCertno(p.getCardNumber());
			} else {
				resV.setPsgname(ticket.getPassengerName());
			}
			resV.setCode(xtyRs.getCode() != null ? String.valueOf(xtyRs
					.getCode()) : "0");
			resV.setMsg(xtyRs.getMsg());
			resV.setData("");
			resV.setCompany("");
			resV.setOffcode("");
			resV.setOffice("");
			resV.setFace(0d);
			resV.setTax(0d);
			resV.setYq(0d);
			resV.setTotal(0d);
			if (itiner != null) {
				List<DetnFareVO> fares = itiner.getFare();
				if (fares != null && fares.size() > 0) {
					for (DetnFareVO f : fares) {
						resV.setFace(StringUtils.isNotEmpty(f.getFare())
								&& StringUtils.isFigure(f.getFare()) ? Double
								.parseDouble(f.getFare()) : 0d);
						resV.setTax(StringUtils.isNotEmpty(f.getTax())
								&& StringUtils.isFigure(f.getTax()) ? Double
								.parseDouble(f.getTax()) : 0d);
						resV.setYq(StringUtils.isNotEmpty(f.getYq())
								&& StringUtils.isFigure(f.getYq()) ? Double
								.parseDouble(f.getYq()) : 0d);
						resV.setTotal(StringUtils.isNotEmpty(f.getTotal())
								&& StringUtils.isFigure(f.getTotal()) ? Double
								.parseDouble(f.getTotal()) : 0d);
					}
				}
				List<DetnSegmentVO> segs = itiner.getSegment();
				if (segs != null && segs.size() > 0) {
					List<DetrFltVO> flts = new ArrayList<DetrFltVO>();
					for (DetnSegmentVO l : segs) {
						List<DetnLegVO> legs = l.getLeg();
						for (DetnLegVO leg : legs) {
							DetrFltVO f = new DetrFltVO();
							f.setArrCh(leg.getDest());
							f.setArrCode("");
							f.setArrt(leg.getArrtime());
							f.setCabin(l.getClassx());
							f.setDepCh(leg.getOrigin());
							f.setDepCode("");
							f.setDepDate(l.getDepdate());
							f.setFltno(l.getAirLine() + l.getFlightNo());
							f.setOrgt(leg.getDeptime());
							f.setState(l.getSeatStatus() != null ? l
									.getSeatStatus().split(" ")[0] : "");
							flts.add(f);
						}
					}
					resV.setFlt(flts);
				}

			}
		}
		return resV;
	}

	public static DetrInfoResVO toDetrInfoResVO(DetrBy517Result xtyRs,
			TicketStateEntity ticket) {
		DetrInfoResVO resV = null;
		if (xtyRs != null) {
			List<DetrDetail> detrs = xtyRs.getDetrs();
			resV = new DetrInfoResVO();
			resV.setNo(ticket.getNo());
			resV.setCertno("");
			resV.setPlan("517NA");
			resV.setPsgname(ticket.getPassengerName());
			resV.setCode(xtyRs.getCode() != null ? xtyRs.getCode() : "0");
			resV.setMsg(xtyRs.getMsg());
			resV.setData(xtyRs.getData() != null ? xtyRs.getData()
					.replaceAll(" +", " ").trim() : "");
			resV.setCompany("");
			resV.setFace(0d);
			resV.setOffcode("");
			resV.setOffice("");
			resV.setTax(0d);
			resV.setYq(0d);
			resV.setTotal(0d);
			if (detrs != null && detrs.size() > 0) {
				List<DetrFltVO> flts = new ArrayList<DetrFltVO>();
				for (DetrDetail l : detrs) {
					DetrFltVO f = new DetrFltVO();
					f.setArrCh("");
					f.setArrCode(l.getArrCityCode());
					f.setArrt("");
					f.setCabin("");
					f.setDepCh("");
					f.setDepCode(l.getDepCityCode());
					f.setDepDate(l.getDepdate());
					f.setFltno(l.getFlightNo());
					f.setOrgt("");
					f.setState(l.getStatus());
					flts.add(f);
				}
				resV.setFlt(flts);
			}
		}
		return resV;
	}

	public static DetrInfoResVO toDetrInfoResVO(TfareDetrResult xtyRs,
			TicketStateEntity ticket) {
		DetrInfoResVO resV = null;
		if (xtyRs != null) {
			List<DetrDetail> detrs = xtyRs.getDetr();
			resV = new DetrInfoResVO();
			resV.setNo(ticket.getNo());
			resV.setCertno("");
			resV.setPlan("TFARE");
			resV.setPsgname(ticket.getPassengerName());
			resV.setCode(xtyRs.getState() != null ? String.valueOf(xtyRs
					.getState()) : "0");
			resV.setMsg("");
			resV.setData(xtyRs.getTxt() != null ? xtyRs.getTxt()
					.replaceAll(" +", " ").trim() : "");
			resV.setCompany("");
			resV.setFace(0d);
			resV.setOffcode("");
			resV.setOffice("");
			resV.setTax(0d);
			resV.setYq(0d);
			resV.setTotal(0d);
			if (detrs != null && detrs.size() > 0) {
				List<DetrFltVO> flts = new ArrayList<DetrFltVO>();
				for (DetrDetail l : detrs) {
					DetrFltVO f = new DetrFltVO();
					f.setArrCh("");
					f.setArrCode(l.getArrCityCode());
					f.setArrt("");
					f.setCabin("");
					f.setDepCh("");
					f.setDepCode(l.getDepCityCode());
					f.setDepDate(l.getDepdate());
					f.setFltno(l.getFlightNo());
					f.setOrgt("");
					f.setState(l.getStatus());
					flts.add(f);
				}
				resV.setFlt(flts);
			}
		}
		return resV;
	}

	public static DetrInfoResVO toDetrInfoResVO(TicketInfo xtyRs,
			TicketStateEntity ticket) {
		DetrInfoResVO resV = null;
		if (xtyRs != null) {
			resV = new DetrInfoResVO();
			resV.setNo(StringUtils.isNotEmpty(xtyRs.getTno()) ? xtyRs.getTno()
					: ticket.getNo());
			resV.setCertno("");
			resV.setPlan("XTY");
			resV.setPsgname(StringUtils.isNotEmpty(xtyRs.getName()) ? xtyRs
					.getName() : ticket.getPassengerName());
			resV.setCode(StringUtils.isNotEmpty(xtyRs.getCode()) ? xtyRs
					.getCode() : "0");
			resV.setMsg(StringUtils.isNotEmpty(xtyRs.getMsg()) ? xtyRs.getMsg()
					: "");
			resV.setData("");
			resV.setCompany(xtyRs.getCompany() != null ? xtyRs.getCompany()
					: "");
			resV.setFace(xtyRs.getFace() != null ? xtyRs.getFace() : 0d);
			resV.setOffcode(xtyRs.getOffcode() != null ? xtyRs.getOffcode()
					: "");
			resV.setOffice(xtyRs.getOffice() != null ? xtyRs.getOffice() : "");
			resV.setTax(xtyRs.getTax() != null ? xtyRs.getTax() : 0d);
			resV.setYq(xtyRs.getYq() != null ? xtyRs.getYq() : 0d);
			resV.setTotal(xtyRs.getTotal() != null ? xtyRs.getTotal() : 0d);
			List<Lines> lines = xtyRs.getLine();
			if (lines != null && lines.size() > 0) {
				List<DetrFltVO> flts = new ArrayList<DetrFltVO>();
				for (Lines l : lines) {
					DetrFltVO f = new DetrFltVO();
					f.setArrCh(l.getArrCh());
					f.setArrCode(l.getArrc());
					f.setArrt(l.getArrt());
					f.setCabin(l.getCabin());
					f.setDepCh(l.getDepCh());
					f.setDepCode(l.getDepc());
					f.setDepDate(l.getDepd());
					f.setFltno(l.getFno());
					f.setOrgt(l.getDept());
					f.setState(l.getState());
					flts.add(f);
				}
				resV.setFlt(flts);
			}
		}
		return resV;
	}

	/**
	 * @param v
	 * @param user
	 * @param fileno
	 * @return
	 */
	public static List<DropDataEntity> toDropDataEntity(OrderFlightForDropVO v,
			User user, String fileno) {
		List<DropDataEntity> list = new ArrayList<DropDataEntity>();
		if (StringUtils.isEmpty(v.getDepCode())) {
			return null;
		}
		if (StringUtils.isEmpty(v.getArrCode())) {
			return null;
		}
		if (StringUtils.isEmpty(v.getFligthNum())) {
			return null;
		}
		if (StringUtils.isEmpty(v.getCabinCode())) {
			return null;
		}
		if (StringUtils.isEmpty(v.getDepDate())) {
			return null;
		}
		if (StringUtils.isEmpty(v.getTicketDate())) {
			return null;
		}
		DropDataEntity d = new DropDataEntity();
		d.setDepCode(v.getDepCode().toUpperCase().trim());
		d.setArrCode(v.getArrCode().toUpperCase().trim());
		d.setAvtime("");
		d.setAvtxt("");
		if (StringUtils.isNotEmpty(v.getPsgType())) {
			if (v.getPsgType().contains("成人")) {
				d.setPsgType("ADU");
			} else {
				d.setPsgType("CHD");
			}
		} else {
			d.setPsgType("ADU");
		}
		d.setCabin(v.getCabinCode().trim().toUpperCase());
		d.setFlightNo(v.getFligthNum().trim().toUpperCase());
		d.setCarrier(v.getFligthNum().trim().toUpperCase().replace("*", "")
				.substring(0, 2));
		d.setCreateTime(DateUtils.getDateys());
		String dDate = DateUtils.formatDate(DateUtils.parseDate(v.getDepDate()
				.replace("/", "-"), "yyyy-MM-dd"));
		if (StringUtils.isEmpty(dDate)) {
			return null;
		}
		d.setDDate(dDate);
		if (v.getDepDate().split(" ").length > 1
				&& v.getDepDate().contains(":")) {
			d.setDTime(v.getDepDate().split(" ")[1]);
		} else {
			d.setDTime(StringUtils.isEmpty(v.getDepTime()) ? "24:00" : v
					.getDepTime());
		}
		String ticketime = DateUtils.formatDate(DateUtils.parseDate(
				v.getTicketDate(), "yyyy-MM-dd"));
		if (StringUtils.isEmpty(ticketime)) {
			return null;
		}
		d.setTicketime(ticketime);
		d.setFileno(fileno);
		d.setFlightType("S");
		d.setId(UniqId.getInstance().getStrId());
		d.setMno(user != null ? user.getMert().getMno() : "");
		d.setOrderId("");
		d.setOrderNo(v.getOrderNo() != null ? v.getOrderNo().trim() : "");
		d.setPnrCode(v.getPnr() != null ? v.getPnr().trim() : "");
		d.setRemark(v.getRemark());
		d.setSource("RIMP");
		d.setIndexKey(d.getDepCode() + d.getArrCode() + d.getCarrier()
				+ d.getDDate());
		list.add(d);
		return list;
	}

	public static List<TicketStateEntity> toTicketStateEntity(
			QueryTicketNoByUploadVO v, User user, String fileno) {
		TicketStateEntity obj = null;
		List<TicketStateEntity> list = null;
		if (v != null && StringUtils.isNotEmpty(v.getTicketNo())) {
			list = new ArrayList<TicketStateEntity>();
			String[] tickets = null;
			String[] passegners = null;
			v.setTicketNo(v.getTicketNo().trim());
			v.setPassengerName(v.getPassengerName() != null ? v
					.getPassengerName().trim() : "");
			if (v.getTicketNo().contains(",")) {
				tickets = v.getTicketNo().split(",");
			} else if (v.getTicketNo().contains("|")) {
				tickets = v.getTicketNo().split("\\|");
			} else if (v.getTicketNo().contains(";")) {
				tickets = v.getTicketNo().split(";");
			} else if (v.getTicketNo().contains("/")) {
				tickets = v.getTicketNo().split("/");
			} else {
				tickets = v.getTicketNo().split(" ");
			}
			if (v.getPassengerName().contains(",")) {
				passegners = v.getPassengerName().split(",");
			} else if (v.getPassengerName().contains("|")) {
				passegners = v.getPassengerName().split("\\|");
			} else if (v.getPassengerName().contains(";")) {
				passegners = v.getPassengerName().split(";");
			} else if (v.getPassengerName().contains("/")) {
				passegners = v.getPassengerName().split("/");
			} else {
				passegners = v.getPassengerName().split(" ");
			}
			for (int i = 0; i < tickets.length; i++) {
				String t = tickets[i];
				String p = passegners.length > i ? passegners[i] : "";
				String tno = t != null ? OOUtils.getfigure(t) : "";
				if (tno != null
						&& tno.length() == OOComms.TICKETNO_MODEL.length()) {
					obj = new TicketStateEntity();
					obj.setId(UniqId.getInstance().getStrId());
					obj.setMno(OOUtils.getUserMerchant(user));
					obj.setNo(tno);
					obj.setCertno("");
					obj.setOid("");
					obj.setPassengerName(p.trim());
					obj.setPassengerName2("");
					if (StringUtils.isNotEmpty(v.getRemark())) {
						if (v.getRemark().length() > 250) {
							v.setRemark(v.getRemark().substring(0, 250));
						}
					}
					obj.setRemark(v.getRemark() != null ? v.getRemark().trim()
							: "");
					if (StringUtils.isNotEmpty(v.getDepartureDate())) {
						v.setDepartureDate(v.getDepartureDate()
								.replace("/", "-").replace("\\", "-"));
					}
					obj.setTakeOff(v.getDepartureDate() != null ? v
							.getDepartureDate() : "");
					if (StringUtils.isNotEmpty(v.getTicketDate())) {
						v.setTicketDate(v.getTicketDate().replace("/", "-")
								.replace("\\", "-"));
					}
					obj.setOutDate(v.getTicketDate() != null ? v
							.getTicketDate() : "");
					obj.setFileno(fileno);
					obj.setState("");
					obj.setMessages("");
					obj.setLastime("");
					obj.setFltinfo("");
					obj.setQueryok(0);
					obj.setCtm(DateUtils.getDateys());
					list.add(obj);
				} else {
					System.out.println("票号格式异常");
				}
			}
		} else {
			System.out.println("空票号");
		}
		return list;
	}

	public static String toFinanceSaleEntity(FinanceCtrByUploadVO vo,
			User user, String fileno, FinanceSaleEntity obj) {
		// FinanceSaleEntity obj = null;
		// List<FinanceSaleEntity> list = null;
		if (vo != null && StringUtils.isNotEmpty(vo.getOrderNO())) {
			// list = new ArrayList<FinanceSaleEntity>();

			// obj = new FinanceSaleEntity();
			obj.setId(UniqId.getInstance().getStrId());
			obj.setImpTime(DateUtils.getDateys());
			if (StringUtils.isNotEmpty(user.toString()))
				obj.setMno(OOUtils.getUserMerchant(user));
			if (StringUtils.isNotEmpty(fileno))
				obj.setFileno(fileno);
			// 销售平台
			if (StringUtils.isNotEmpty(vo.getXiaoShouPojo())) {
				obj.setXiaoShouPojo(vo.getXiaoShouPojo());
			} else {
				return "销售平台为空";
			}
			// 订单号
			if (StringUtils.isNotEmpty(vo.getOrderNO())) {
				obj.setOrderNO(vo.getOrderNO());
			} else {
				return "订单号为空";
			}
			// 订单生成时间
			if (StringUtils.isNotEmpty(vo.getOrderCreateDate())) {
				obj.setOrderCreateDate(vo.getOrderCreateDate());
			} else {
				return "订单生成时间为空";
			}
			// PNR
			if (StringUtils.isNotEmpty(vo.getPNR())) {
				obj.setPNR(vo.getPNR());
			} else {
				obj.setPNR("");
				// return "PNR为空";
			}
			// 乘机人姓名
			if (StringUtils.isNotEmpty(vo.getChengJiRenName())) {
				obj.setChengJiRenName(vo.getChengJiRenName());
			} else {
				return "乘机人姓名为空";
			}
			// 票号
			if (StringUtils.isNotEmpty(vo.getFareNO())) {
				obj.setFareNO(vo.getFareNO());
			} else {
				return "票号为空";
			}
			// 出票时间
			if (StringUtils.isNotEmpty(vo.getChuPiaoDate())) {
				obj.setChuPiaoDate(vo.getChuPiaoDate());
			} else {
				return "出票时间为空";
			}
			// 起飞时间
			if (StringUtils.isNotEmpty(vo.getFightDate())) {
				obj.setFightDate(vo.getFightDate());
			} else {
				return "起飞时间为空";
			}
			// 航程类型
			if (StringUtils.isNotEmpty(vo.getTravlTyle())) {
				obj.setTravlTyle(vo.getTravlTyle());
			} else {
				return "航程类型为空";
			}
			// 乘机人类型 no.10
			if (StringUtils.isNotEmpty(vo.getChengJiRenType())) {
				obj.setChengJiRenType(vo.getChengJiRenType());
			} else {
				return "乘机人类型为空";
			}
			// 出发到达机场
			if (StringUtils.isNotEmpty(vo.getStartCodeToAriveCode())) {
				if (StringUtilsc.isContainChinese(vo.getStartCodeToAriveCode())) {
					return "出发到达机场不能为中文";
				}
				obj.setStartCodeToAriveCode(vo.getStartCodeToAriveCode());
				String[] ssa = vo.getStartCodeToAriveCode().split(",");
				String[] sa = ssa[0].split("-");
				if (sa.length < 2) {
					obj.setStartCode(vo.getStartCodeToAriveCode().substring(0,
							3));
					obj.setArriveCode(vo.getStartCodeToAriveCode().substring(3,
							6));
				} else {
					obj.setStartCode(sa[0]);
					obj.setArriveCode(sa[1]);
				}
			} else {
				return "出发到达机场为空";
			}
			// 承运人
			if (StringUtils.isNotEmpty(vo.getChengYunRen())) {
				obj.setChengYunRen(vo.getChengYunRen());
			} else {
				return "承运人为空";
			}
			// 航班号
			if (StringUtils.isNotEmpty(vo.getFightNO())) {
				obj.setFightNO(vo.getFightNO());
			} else {
				return "航班号为空";
			}
			// 舱位
			if (StringUtils.isNotEmpty(vo.getCabin())) {
				obj.setCabin(vo.getCabin());
			} else {
				return "舱位为空";
			}
			// 收款日期
			if (StringUtils.isNotEmpty(vo.getShouKuaiDate())) {
				obj.setShouKuaiDate(vo.getShouKuaiDate());
			} else {
				return "收款日期为空";
			}
			// 收款账户
			if (StringUtils.isNotEmpty(vo.getShouKuanAccount())) {
				obj.setShouKuanAccount(vo.getShouKuanAccount());
			} else {
				return "收款账户为空";
			}
			// 出票员
			if (StringUtils.isNotEmpty(vo.getChuaPiaoRen())) {
				obj.setChuaPiaoRen(vo.getChuaPiaoRen());
			} else {
				return "出票员为空";
			}
			// 备注1
			if (StringUtils.isNotEmpty(vo.getBeiZhuFirst())) {
				obj.setBeiZhuFirst(vo.getBeiZhuFirst());
			} else {
				obj.setBeiZhuFirst("");
			}
			// 报价类型
			if (StringUtils.isNotEmpty(vo.getBaoJiaTyle())) {
				obj.setBaoJiaTyle(vo.getBaoJiaTyle());
			} else {
				obj.setBaoJiaTyle("");
			}
			// 订单状态
			if (StringUtils.isNotEmpty(vo.getOrderStatus())) {
				obj.setOrderStatus(vo.getOrderStatus());
			} else {
				obj.setOrderStatus("");
			}
			// 自动出票
			if (StringUtils.isNotEmpty(vo.getChuPiaoSelf())) {
				obj.setChuPiaoSelf(vo.getChuPiaoSelf());
			} else {
				obj.setChuPiaoSelf("");
			}
			// 采购平台
			if (StringUtils.isNotEmpty(vo.getCaiGaoProjo())) {
				obj.setCaiGaoProjo(vo.getCaiGaoProjo());
			} else {
				return "采购平台为空";
			}
			// 支付账户
			if (StringUtils.isNotEmpty(vo.getPayAccount())) {
				obj.setProjoName(vo.getPayAccount());
			} else {
				return "支付账户为空";
			}
			// 备注2
			if (StringUtils.isNotEmpty(vo.getBeiZhuSecond())) {
				obj.setBeiZhuSecond(vo.getBeiZhuSecond());
			} else {
				obj.setBeiZhuSecond("");
			}
			// CSO备注
			if (StringUtils.isNotEmpty(vo.getCSOBeiZhu())) {
				obj.setCSOBeiZhu(vo.getCSOBeiZhu());
			} else {
				obj.setCSOBeiZhu("");
			}
			// 备注3
			if (StringUtils.isNotEmpty(vo.getBeiZhuLast())) {
				obj.setBeiZhuLast(vo.getBeiZhuLast());
			} else {
				obj.setBeiZhuLast("");
			}
			// 交易方式
			if (StringUtils.isNotEmpty(vo.getJiaoYiType())) {
				obj.setJiaoYiType(vo.getJiaoYiType());
			} else {
				obj.setJiaoYiType("");
			}
			// 成人票面价
			if (StringUtils.isNotEmpty(vo.getChengRenPice())) {
				if (StringUtils.isNumeric(vo.getChengRenPice()))// 是数字
					obj.setChengRenPice(Double.valueOf(vo.getChengRenPice()));
				else
					obj.setChengRenPice(doContendsSpilcallyStr(vo
							.getChengRenPice()));
			} else {
				return "成人票面价为空";
			}
			// 成人基建
			if (StringUtils.isNotEmpty(vo.getChengRenJiJian())) {
				if (StringUtils.isNumeric(vo.getChengRenJiJian()))// 是数字
					obj.setChengRenJiJian(Double.valueOf(vo.getChengRenJiJian()));
				else
					obj.setChengRenJiJian(doContendsSpilcallyStr(vo
							.getChengRenJiJian()));
			} else {
				return "成人基建为空";
			}
			// 成人燃油税
			if (StringUtils.isNotEmpty(vo.getChengRenRanYouShui())) {
				if (StringUtils.isNumeric(vo.getChengRenRanYouShui()))// 是数字
					obj.setChengRenRanYouShui(Double.valueOf(vo
							.getChengRenRanYouShui()));
				else
					obj.setChengRenRanYouShui(doContendsSpilcallyStr(vo
							.getChengRenRanYouShui()));
			} else {
				obj.setChengRenRanYouShui(0.0);
			}
			// 快递
			if (StringUtils.isNotEmpty(vo.getKuaiDi())) {
				if (StringUtils.isNumeric(vo.getKuaiDi()))// 是数字
					obj.setKuaiDi(Double.valueOf(vo.getKuaiDi()));
				else
					obj.setKuaiDi(doContendsSpilcallyStr(vo.getKuaiDi()));
			} else {
				obj.setKuaiDi(0.0);
			}
			// 快递成本
			if (StringUtils.isNotEmpty(vo.getKuaiDiChengBen())) {
				if (StringUtils.isNumeric(vo.getKuaiDi()))// 是数字
					obj.setKuaiDiChengBen(Double.valueOf(vo.getKuaiDiChengBen()));
				else
					obj.setKuaiDiChengBen(doContendsSpilcallyStr(vo
							.getKuaiDiChengBen()));
			} else {
				obj.setKuaiDiChengBen(0.0);
			}
			// 佣金
			if (StringUtils.isNotEmpty(vo.getYongJin())) {
				if (StringUtils.isNumeric(vo.getYongJin()))// 是数字
					obj.setYongJin(Double.valueOf(vo.getYongJin()));
				else
					obj.setYongJin(doContendsSpilcallyStr(vo.getYongJin()));
			} else {
				obj.setYongJin(0.0);
			}
			// 机票实收款
			if (StringUtils.isNotEmpty(vo.getSurePrice())) {
				if (StringUtils.isNumeric(vo.getSurePrice()))// 是数字
					obj.setSurePrice(Double.valueOf(vo.getSurePrice()));
				else
					obj.setSurePrice(doContendsSpilcallyStr(vo.getSurePrice()));
			} else {
				return "机票实收款为空";
			}
			// 实际支付成本
			if (StringUtils.isNotEmpty(vo.getMoney())) {
				if (StringUtils.isNumeric(vo.getMoney()))// 是数字
					obj.setMoney(Double.valueOf(vo.getMoney()));
				else
					obj.setMoney(doContendsSpilcallyStr(vo.getMoney()));
			} else {
				return "实际支付成本为空";
			}
			// 实际采购票面价
			if (StringUtils.isNotEmpty(vo.getPrice())) {
				if (StringUtils.isNumeric(vo.getPrice()))// 是数字
					obj.setPrice(Double.valueOf(vo.getPrice()));
				else
					obj.setPrice(doContendsSpilcallyStr(vo.getPrice()));
			} else {
				return "实际采购票面价为空";
			}
			// cso数据
			if (StringUtils.isNotEmpty(vo.getCsoShuJu())) {
				if (StringUtils.isNumeric(vo.getCsoShuJu()))// 是数字
					obj.setCsoShuJu(Double.valueOf(vo.getCsoShuJu()));
				else
					obj.setCsoShuJu(doContendsSpilcallyStr(vo.getCsoShuJu()));
			} else {
				obj.setCsoShuJu(0.0);
			}
			// 后返
			if (StringUtils.isNotEmpty(vo.getHouFan())) {
				if (StringUtils.isNumeric(vo.getHouFan())) { // 是数字
					obj.setHouFan(Double.valueOf(vo.getHouFan()));
				} else {
					obj.setHouFan(doContendsSpilcallyStr(vo.getHouFan()));
				}
			} else {
				obj.setHouFan(0.0);
			}
			// 广告费
			if (StringUtils.isNotEmpty(vo.getAdRate())) {
				if (StringUtils.isNumeric(vo.getAdRate()))// 是数字
					obj.setAdRate(Double.valueOf(vo.getAdRate()));
				else
					obj.setAdRate(doContendsSpilcallyStr(vo.getAdRate()));
			} else {
				obj.setAdRate(0.0);
			}
			// 实际成本 实际成本=采购-后返+佣金+广告费
			if (StringUtils.isNotEmpty(vo.getShiJiChengBen())) {
				if (StringUtils.isNumeric(vo.getShiJiChengBen()))// 是数字
					obj.setShiJiChengBen(Double.valueOf(vo.getShiJiChengBen()));
				else
					obj.setShiJiChengBen(doContendsSpilcallyStr(vo
							.getShiJiChengBen()));
			} else {
				double sjcg = obj.getMoney() - obj.getHouFan()
						+ obj.getYongJin() + obj.getAdRate();
				obj.setShiJiChengBen(sjcg);
				// return "实际成本为空";
			}
			// 利润 利润=机票实收+快递费-快递成本-实际成本
			if (StringUtils.isNotEmpty(vo.getLiRun())) {
				if (StringUtils.isNumeric(vo.getLiRun()))// 是数字
					obj.setLiRun(Double.valueOf(vo.getLiRun()));
				else
					obj.setLiRun(doContendsSpilcallyStr(vo.getLiRun()));
			} else {
				double lr = obj.getSurePrice() + obj.getKuaiDi()
						- obj.getKuaiDiChengBen() - obj.getShiJiChengBen();
				obj.setLiRun(lr);
				// return "利润为空";
			}
			return "success";
		}
		return null;
	}

	/**
	 * 对于字符串含有, | / 以及价格中夹杂数字的处理
	 * 
	 * @param args
	 */
	public static Double doContendsSpilcallyStr(String str) {
		if (str.contains(",")) {
			return Double.valueOf(str.replace(",", ""));
		} else if (str.contains("|")) {
			String str2_1 = str.substring(0, str.indexOf("|"));
			String str2_2 = str.substring(str.indexOf("|") + 1, str.length());
			return Double.valueOf(str2_1) + Double.valueOf(str2_2);
		} else if (str.contains("/")) {
			return Double.valueOf(str.replace("/", ""));
		} else {
			Pattern pattern = null;
			if (str.contains("-")) {
				pattern = Pattern.compile("-\\d+(\\.\\d+)?");
				;
			} else {
				pattern = Pattern.compile("\\d+(\\.\\d+)?");
				;
			}
			Matcher matcher = pattern.matcher(str);
			while (matcher.find()) {
				return Double.valueOf(matcher.group(0));
			}
		}
		return 0.00;
	}

}
