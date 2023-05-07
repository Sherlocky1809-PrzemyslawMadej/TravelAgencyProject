package com.example.demo.pojo;

public enum Currency {

    USD("Dolar amerykański"),
    GBP("Brytyjski funt szterling"),
    XOF("CFA Frank BCEAO"),
    XAF("CFA Frank BEAC"),
    AUD("Dolar Australijski"),
    EUR("Euro"),
    CHF("Frank szwajcarski"),
    HRK("Kuna"),
    TRY("Lira turecka"),
    MXN("Peso Meksykańskie"),
    IDR("Rupia indonezyjska"),
    INR("Rupia indyjska"),
    PLN("Złoty");

    private final String currencyName;

    Currency(String currencyName) {
        this.currencyName = currencyName;
    }
}
