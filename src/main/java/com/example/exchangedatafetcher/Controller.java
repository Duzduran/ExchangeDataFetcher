package com.example.exchangedatafetcher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;


@RestController
public class Controller {

    @GetMapping("/averageExchangeRates/{currency}/{date}")
    public Double averageExchangeRates(@RequestParam("currency")  String currency,
                                       @RequestParam("date") String date) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://api.nbp.pl/api/exchangerates/rates/a/" + currency + "/" + date))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Helper::parseMid)
                .join();
    }



    @GetMapping("/maxMinAverage")
    public List<Double> maxMinAverage(@RequestParam("currency")  String currency,
                                      @RequestParam("no") String no) {
        Helper.catchException(currency, no);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://api.nbp.pl/api/exchangerates/rates/a/" + currency + "/last/" + no + "/"))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Helper::maxMinParse)
                .join();
    }

    @GetMapping("/diffBuySell")
    public HashSet<Rate> diffBuySell(@RequestParam("currency")  String currency,
                                     @RequestParam("no") String no) {
        Helper.catchException(currency, no);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://api.nbp.pl/api/exchangerates/rates/c/" + currency + "/last/" + no + "/"))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Helper::buySellDiffParse)
                .join();
    }


}
