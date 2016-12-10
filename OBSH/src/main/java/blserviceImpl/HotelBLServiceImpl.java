package blserviceImpl;

import java.rmi.RemoteException;
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
	
	private static int scoreCount=0;
	
	public HotelBLServiceImpl() {
		hoteldao = HotelDaoImpl.getInstance();
		hotelroomdao=HotelroomDaoImpl.getInstance();
	}

	@Override
	public ResultMessage Addhotel(HotelVo hotelvo) throws RemoteException{
		String id = String.valueOf(hotelvo.getHotelID());
		if (id.length() != 4)
			return ResultMessage.FormatWrong;
		if( hoteldao.addHotelPo(new HotelPo(hotelvo)))return ResultMessage.Success;
		return ResultMessage.IDExsit;
	}

	@Override
	public List<HotelPo> FilterByStar(int star, List<HotelPo> list) throws RemoteException{
		try{
			for (int i = 0; i < list.size();i++) {
			    if (list.get(i).getStar() != star)
				    list.remove(i--);
			}
		}catch(NullPointerException e){
			e.getStackTrace();
		}
       return list;
	}

	@Override
	public List<HotelPo> Views(String address, String commercialDistrict) throws RemoteException{
		return hoteldao.searchHotelByLocation(address + "+" + commercialDistrict);
	}

	@Override
	public String ViewHotelDetail(int hotelid) throws RemoteException{
		HotelPo hotelpo = hoteldao.getHotel(hotelid);
		String detail = hotelpo.getLocation() + "+"+hotelpo.getBriefInfo() + "+" +hotelpo.getServices();
		
		List<HotelroomPo> list=hotelroomdao.getHotelroomByHotelID(hotelid);
		Map<String,Object> map=new HashMap<String, Object>();
		
		for(int i=0;i<list.size();i++){
			if(!map.containsKey(list.get(i).getRoomType())){
				map.put(list.get(i).getRoomType(), null);
				detail=detail+"+"+list.get(i).getRoomType()+String.valueOf(list.get(i).getPrice());
			}
		}
		
		return detail;
	}

	/**
	 * 
	 * @param hotelid
	 * @return 获得该酒店最低价
	 */
	private double getHotelMinPrice(int hotelid) {
		List<HotelroomPo> list=hotelroomdao.getHotelroomByHotelID(hotelid);
		double price=0;
		try{
			price=list.get(0).getPrice();
			for(int i=1;i<list.size();i++){
				if(list.get(i).getPrice()<price)
					price=list.get(i).getPrice();
			}
		}catch(NullPointerException e){
			e.getStackTrace();
		}
		return price;
	}

	

	@Override
	public List<HotelPo> FilterByPrice(double min, double max, List<HotelPo> list) throws RemoteException{
		try{
		      for(int i=0;i<list.size();i++){
			     List<HotelroomPo> roomlist= hotelroomdao.getHotelroomByHotelID(list.get(i).getHotelID());
			     double temp=max+1;
			     for(int j=0;j<roomlist.size();j++){
			    	 double p=roomlist.get(j).getPrice();
			    	 if(p<=max&&p>=min&&p<temp)
			    		 temp=p;
			     }
			     if(!(temp<max))
			    	 list.remove(i--);
		      }			
		}catch(NullPointerException e){
			e.getStackTrace();
}
		
		return list;

	}

	@Override
	public List<HotelPo> FilterByScore(double min, double max, List<HotelPo> list) throws RemoteException{
		try{
		      for(int i=0;i<list.size();i++){
		    	  double score=list.get(i).getScore();
			    if(!(score<=max&&score>=min))
			    	list.remove(i--);
		      }			
		}catch(NullPointerException e){
			e.getStackTrace();
}
		
		return list;
	}

	@Override
	public List<HotelPo> FilterByRoomType(String type, List<HotelPo> list) throws RemoteException{
		
        return null;
	}

	@Override
	public HotelPo SearchByName(String hotel) throws RemoteException{
		return hoteldao.searchHotelByName(hotel);
	}

	@Override
	public HotelPo SearchByID(int id) throws RemoteException{
		return hoteldao.getHotel(id);
	}
	
	@Override
	public List<HotelPo> SortByStar(List<HotelPo> list) throws RemoteException{
		for(int i=0;i<list.size();i++){
			for(int j=1;j<list.size();j++){
				if(list.get(i).getStar()<list.get(j).getStar()){
					HotelPo temp=list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		return list;
	}

	@Override
	public List<HotelPo> SortByPrice(List<HotelPo> list) throws RemoteException{
		for(int i=0;i<list.size();i++){
			for(int j=1;j<list.size();j++){
				if(getHotelMinPrice(list.get(i).getHotelID())>
						getHotelMinPrice(list.get(j).getHotelID())){
					HotelPo temp=list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		return list;
	}

	@Override
	public List<HotelPo> SortByScore(List<HotelPo> list) throws RemoteException{
		for(int i=0;i<list.size();i++){
			for(int j=1;j<list.size();j++){
				if(list.get(i).getScore()<list.get(j).getScore()){
					HotelPo temp=list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		return list;
	}


	@Override
	public List<HotelroomPo> SortByTime(List<HotelroomPo> list) throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage AddAssess(int score,String comment,int hotelID) throws RemoteException {
		HotelPo hotelpo=hoteldao.getHotel(hotelID);
		double prescore=hotelpo.getScore();
		if(score!=-1){
			prescore*=scoreCount++;
			prescore+=score;
			hotelpo.setScore(prescore/scoreCount);
		}
		if(null!=comment)
			hotelpo.addSummary(comment);
		if(hoteldao.updateHotel(hotelpo)) return ResultMessage.UpdateSuccess;
		return ResultMessage.UpdateFail;
	}


}