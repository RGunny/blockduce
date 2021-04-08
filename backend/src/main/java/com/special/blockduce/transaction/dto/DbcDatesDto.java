package com.special.blockduce.transaction.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class DbcDatesDto {

    private List<LocalDate> localDates = new ArrayList<>();

    public DbcDatesDto(List<LocalDate> localDates) {
        this.localDates = localDates;
    }

    public String transactionHash;

    public DbcDatesDto() {

    }
}
