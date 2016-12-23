package blserviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ResultMessage.ResultMessage;
import blservice.HotelBLService;
import data.dao.HotelDao;
import data.dao.HotelroomDao;
import data.dao.impl.HotelDaoImpl;
import data.dao.impl.HotelroomDaoImpl;
import po.HotelPo;
import po.HotelroomPo;
import vo.HotelVo;

public class HotelBLServiceImpl implements HotelBLService {

	private HotelDao hoteldao;
	private HotelroomDao hotelroomdao;
	private final double DefaultPrice = 10000000;	
	private static int scoreCount=0;
	
	public HotelBLServiceImpl() {
		hoteldao = HotelDaoImpl.getInstance();
		hotelroomdao=HotelroomDaoImpl.getInstance();
	}

	/**
	 * 添加酒店方法实现
	 * 酒店ID要与酒店工作人员一致 即4位
	 */
	@Override
	public ResultMessage Addhotel(HotelVo hotelvo) {
		String id = String.valueOf(hotelvo.getHotelID());
		if (id.length() != 4)
			return ResultMessage.FormatWrong;
		if (hoteldao.addHotelPo(new HotelPo(hotelvo)))
			return ResultMessage.Success;
		return ResultMessage.IDExsit;
	}

	/**
	 * 获取在一定地址商圈下的所有酒店信息
	 */
	@Override
	public List<HotelPo> Views(String address, String commercialDistrict) {
		List<HotelPo> list=hoteldao.searchHotelByLocation(address + "+" + commercialDistrict);
		return list;
	}
	
	@Override
	public double getHotelMinPrice(int hotelid) {
		HotelPo po = hoteldao.getHotel(hotelid);
		double price = DefaultPrice;
		String Rooms[] = po.getRoomInfo().split(";");

		for (int i = 1; i < Rooms.length; i++) {
			String r[] = Rooms[i].split("[+]");
			if (Double.parseDouble(r[2]) < price && Integer.parseInt(r[1]) > 0)// 在价格符合的情况下需要房间个数足够
				price = Double.parseDouble(r[2]);
		}
		return price;
	}
	
	//根据酒店名称查找酒店
	@Override
	public HotelPo SearchByName(String hotel) {
		return hoteldao.searchHotelByName(hotel);
	}

	
	//根据酒店ID查找酒店 
	@Override
	public HotelPo SearchByID(int id) {
		HotelPo po = hoteldao.getHotel(id);
		return po;
	}
	
	// 按房间类型搜索
	@Override
	public List<HotelVo> FilterByType(String roomType, List<HotelVo> hotellist) {
		if (!roomType.equals("不限")) {
			for (int i = 0; i < hotellist.size(); i++) {
				String rooms[] = hotellist.get(i).getRoomInfo().split(";");
				boolean has = false;
				for (int j = 0; j < rooms.length; j++) {
					String temp[] = rooms[i].split("[+]");
					if (temp[0].equals(roomType) && Integer.parseInt(temp[1]) > 0) {
						has = true;
						break;
					}
				}
				if (!has)
					hotellist.remove(i--);
			}
		}
		return hotellist;
	}
	
	// 按名称搜索
	@Override
	public List<HotelVo> FilterByName(String name, List<HotelVo> list) {
		if (!name.equals("")) {
			for (int i = 0; i < list.size(); i++) {
				if (!list.get(i).getName().equals(name))
					list.remove(i--);
			}
		}
		return list;
	}
	
	//按星级搜索
	@Override
	public List<HotelVo> FilterByStar(int hotelStar, List<HotelVo> hotellist) {
		if (hotelStar != 5) {
			for (int i = 0; i < hotellist.size(); i++) {
				if (hotellist.get(i).getStar() <= hotelStar)
					hotellist.remove(i--);
			}
		}
		return hotellist;
	}
	
	private Comparator<HotelVo> comparePrice = new Comparator<HotelVo>() {

		@Override
		public int compare(HotelVo o1, HotelVo o2) {
			return o1.getMinPrice() <= o2.getMinPrice() ? 1 : -1;
		}

	};
	
	private Comparator<HotelVo> compareStar = new Comparator<HotelVo>() {

		@Override
		public int compare(HotelVo o1, HotelVo o2) {
			return o1.getStar() >= o2.getStar() ? 1 : -1;
		}

	};
	
	private Comparator<HotelVo> compareScore = new Comparator<HotelVo>() {

		@Override
		public int compare(HotelVo o1, HotelVo o2) {
			return o1.getScore() >= o2.getScore() ? 1 : -1;
		}

	};

	@Override
	public void comprehensiveSort(List<HotelVo> list, boolean Sort[]) {
       List<Comparator<HotelVo>> comList=new ArrayList<Comparator<HotelVo>>();
		if (Sort[0])
			comList.add(comparePrice);
		if (Sort[1])
			comList.add(compareStar);
		if (Sort[2])
			comList.add(compareScore);

		if (comList == null)
			return;
		Comparator<HotelVo> cmp = new Comparator<HotelVo>() {

			@Override
			public int compare(HotelVo o1, HotelVo o2) {
				for (Comparator<HotelVo> comparator : comList) {
					if (comparator.compare(o1, o2) > 0) {
						return 1;
					} else if (comparator.compare(o1, o2) < 0) {
						return -1;
					}
				}
				return 0;
			}

		};
		Collections.sort(list, cmp);
	}
	
	//评价酒店
	@Override
	public ResultMessage AddAssess(int score, String comment, int hotelID) {
		HotelPo hotelpo = hoteldao.getHotel(hotelID);
		double prescore = hotelpo.getScore();
		if (score != -1) {
			prescore *= scoreCount++;
			prescore += score;
			hotelpo.setScore(prescore / scoreCount);
		}
		if (null != comment)
			hotelpo.addSummary(comment);
		if (hoteldao.updateHotel(hotelpo))
			return ResultMessage.UpdateSuccess;
		return ResultMessage.UpdateFail;
	}
	
	//获得酒店房间价格
	@Override
	public double getHotelRoomPriceByType(String type, String hotelname) {
		String rooms[] = SearchByName(hotelname).getRoomInfo().split(";");

		for (int i = 0; i < rooms.length; i++) {
			String s[] = rooms[i].split("+");
			if (s[0].equals(type)) {
				return Double.parseDouble(s[2]);
			}
		}
		return 0;
	}
	
	//胡渣渣版本增加酒店房间
	@Override
	public void AddRoom(int hotelid, int num, String type, double price) {
		HotelPo hotel = hoteldao.getHotel(hotelid);
		String roomInfo = hotel.getRoomInfo();
		String Rooms[] = roomInfo.split(";");

		boolean newType = true;
		for (int i = 0; i < Rooms.length; i++) {
			String r[] = Rooms[i].split("[+]");
			if (r[0].equals(type)) {
				newType = false;
				r[1] = String.valueOf(Integer.parseInt(r[1]) + num);
				Rooms[i] = r[0] + "+" + r[1] + "+" + r[2];
				break;
			}
		}

		String res = "";
		for (int i = 0; i < Rooms.length; i++) {
			res = res + Rooms[i] + ";";
		}
		if (newType) {
			res = res + type + "+" + String.valueOf(num) + "+" + String.valueOf(price) + ";";
		}

		res = res.substring(0, res.length() - 1);
		hotel.setRoomInfo(res);
		hoteldao.updateHotel(hotel);
	}
	
	@Override
	public void ModifyPrice(int hotelID, String type, double price) {
		HotelPo hotel = hoteldao.getHotel(hotelID);
		String roomInfo = hotel.getRoomInfo();
		String Rooms[] = roomInfo.split(";");

		for (int i = 0; i < Rooms.length; i++) {
			String r[] = Rooms[i].split("[+]");
			if (r[0].equals(type)) {
				r[2] = String.valueOf(price);
				Rooms[i] = r[0] + "+" + r[1] + "+" + r[2];
				break;
			}
		}

		String res = "";
		for (int i = 0; i < Rooms.length; i++) {
			res = res + Rooms[i] + ";";
		}

		res = res.substring(0, res.length() - 1);
		hotel.setRoomInfo(res);
		hoteldao.updateHotel(hotel);
	}

	@Override
	public void ModifyRoomNumByOrder(int hotelID, int num, String type) {
		HotelPo hotel = hoteldao.getHotel(hotelID);
		String roomInfo = hotel.getRoomInfo();
		String Rooms[] = roomInfo.split(";");

		for (int i = 0; i < Rooms.length; i++) {
			String r[] = Rooms[i].split("[+]");
			if (r[0].equals(type)) {
				r[1] = String.valueOf(Integer.parseInt(r[1]) - num);
				Rooms[i] = r[0] + "+" + r[1] + "+" + r[2];
				break;
			}
		}

		String res = "";
		for (int i = 0; i < Rooms.length; i++) {
			res = res + Rooms[i] + ";";
		}

		res = res.substring(0, res.length() - 1);
		hotel.setRoomInfo(res);
		hoteldao.updateHotel(hotel);
	}
	
	@Override
	public ResultMessage ModifyHotelMessage(HotelVo vo) {
		HotelPo po = new HotelPo(vo);
		System.out.println(po.getBriefInfo());
		if( hoteldao.updateHotel(po))
			return ResultMessage.UpdateSuccess;
		return ResultMessage.UpdateFail;
	
	}


}