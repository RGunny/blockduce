package com.special.blockduce.transaction.repository;

import com.special.blockduce.transaction.domain.DBC;
import com.special.blockduce.transaction.dto.DbcResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CalendarRepository extends JpaRepository<DBC, Long> {

    /**
     * 1번 그림
     * <p>
     * request : userId, year, month
     * ex) api/election/content/{userid}/{year}/{month}
     * userId : 35
     * date : 2021/04
     * 받고 싶은 데이터 : month의 날짜별 true false
     * -> 해당 달에 해당 유저가 투표한 날에 대해
     * -> userId : sender_id
     *
     *  member랑 패치조인(toOne) 해서  해당 userId가
     */
    @Query("select d from DBC d join fetch d.member m" +
            " where m.id in :senderId" +
            " and year(d.localDateTime) in :year and month(d.localDateTime) in :month")
    List<DBC> findDayBySenderIdAndDate(Long senderId, int year, int month);

    @Query("select d from DBC d join fetch d.member m where m.id = :senderId")
    List<DBC> findDbcFjMemberBySenderId(Long senderId);

    @Query("select d from DBC d where year(d.localDateTime) = :year and month(d.localDateTime) = :month")
    List<DBC> findDateTimes(int year, int month);

    /**
     * 2번 그림
     *
     *  보내주는 데이터 : userId, year, month, day
     *  ex) api/election/detail/{userid}/{year}/{month}
     *  userId : 35
     *  date : 2021/04/02
     *  받고 싶은 데이터 : day 에 있었던 trasaction 데이터
     *  block_number, block_hash, candidate_img, candidate_name, agency, timeStamp ,DBC 양 ,total DBC 양
     *      -> receiver_id : cadndidate table - candidate_id
     *      -> sender_id : member table - member_id
     *      -> DBC 양 : dbc table - value
     *      -> total DBC 양 : dbc table - 해달 날의 value 합
     */
    /**
     *     Long blockNumber;
     *     String blockHash;
     *     String candidateImg;
     *     String candidateName;
     *     String agency;
     *     LocalDateTime localDateTime;
     *     Long value; // 투표한 dbc 양
     *     Long totalValue; // 해당 날의 투표한 dbc 총량
     */
//    @Query("select d "+
//            " from DBC d join d.member m join d.candidate c " +
//            " where m.id = :senderId" +
//            " and month(d.localDateTime) = :month and day(d.localDateTime) = :day")
//    List<DBC> findDbcFjMemberCandidateBySenderId(Long senderId, int month, int day);

    @Query("select new com.special.blockduce.transaction.dto.DbcResponseDto" +
            " (d.blockNumber, d.blockHash, c.img, c.name, c.agency, d.localDateTime, d.value, d.transactionHash)" +
            " from DBC d join d.member m join d.candidate c " +
            " where m.id = :senderId" +
            " and month(d.localDateTime) = :month and day(d.localDateTime) = :day")
    List<DbcResponseDto> findDbcDtos(Long senderId, int month, int day);

}
