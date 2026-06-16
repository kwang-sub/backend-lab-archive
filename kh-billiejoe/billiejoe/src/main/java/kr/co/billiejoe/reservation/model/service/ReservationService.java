package kr.co.billiejoe.reservation.model.service;

import java.util.List;

import kr.co.billiejoe.common.model.vo.Pagination;
import kr.co.billiejoe.reservation.model.vo.BookedReservation;

public interface ReservationService {
	
	Pagination getPagination(Pagination pg, int memberNo);
	
	List<BookedReservation> selectBookedList(Pagination pagination, int memberNo);

}
