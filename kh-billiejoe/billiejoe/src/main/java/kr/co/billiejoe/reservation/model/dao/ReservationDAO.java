package kr.co.billiejoe.reservation.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.billiejoe.common.model.vo.Pagination;
import kr.co.billiejoe.reservation.model.vo.BookedReservation;

@Repository
public class ReservationDAO {
	@Autowired
	private SqlSessionTemplate session;

	public int getListCount(int memberNo) {
		return session.selectOne("reservationMapper.selectBookedListCount", memberNo);
	}
	
	public List<BookedReservation> selectBookedList(Pagination pagination, int memberNo) {
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		return session.selectList("reservationMapper.selectBookedList", memberNo, rowBounds);
	}
}
