package com.example.exchangedatafetcher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rate {
    String no;
    String effectiveDate;
    Double bid;
    Double ask;
    Double difference;

    public Rate(String no, String effectiveDate, Double bid, Double ask) {
        this.no = no;
        this.effectiveDate = effectiveDate;
        this.bid = bid;
        this.ask = ask;
    }

    public Rate(String no, String effectiveDate, double difference) {
        this.no = no;
        this.effectiveDate = effectiveDate;
        this.difference = difference;
    }

    public String toStringDiff() {
        return "Rate{" +
                "no='" + no + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", difference=" + difference +
                '}';
    }
}
