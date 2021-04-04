package com.special.blockduce.candidate.service;

import com.special.blockduce.transaction.dto.DbcDatesDto;
import com.special.blockduce.transaction.dto.DbcResponseDto;
import com.special.blockduce.transaction.repository.CalendarRepository;
import com.special.blockduce.transaction.domain.DBC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public DbcDatesDto getElections(Long senderId, int year, int month) {
        List<DBC> result = calendarRepository.findDayBySenderIdAndDate(senderId, year, month);
//
        DbcDatesDto datesDto = new DbcDatesDto();
        for (DBC dbc : result) {
            datesDto.getLocalDates().add(dbc.getLocalDateTime().toLocalDate());
        }

        return datesDto;
    }


    public List<DbcResponseDto> getElection(Long senderId, int month, int day) {

        List<DbcResponseDto> result = calendarRepository.findDbcDtos(senderId, month, day);

        int total = 0;
        for (DbcResponseDto dto : result) {
            total += dto.getValue();
        }

        for (DbcResponseDto dto : result) {
            dto.setTotalValue(total);
            dto.setLocalDate(dto.getLocalDateTime().toLocalDate());
            System.out.println("dto.getLocalDate() = " + dto.getLocalDate());
        }

        return result;
    }

}
