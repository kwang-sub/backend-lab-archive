package kr.co.billiejoe.reservation.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.billiejoe.common.model.vo.Pagination;
import kr.co.billiejoe.reservation.model.dao.ReservationDAO;
import kr.co.billiejoe.reservation.model.vo.BookedReservation;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	public ReservationDAO dao;
	
	@Override
	public Pagination getPagination(Pagination pg, int memberNo) {
		int selectPg = dao.getListCount(memberNo);
		return new Pagination(pg.getCurrentPage(), selectPg);
	}

	@Override
	public List<BookedReservation> selectBookedList(Pagination pagination, int memberNo) {
		List<BookedReservation> resList = dao.selectBookedList(pagination, memberNo);
		return resList;
	}

}
