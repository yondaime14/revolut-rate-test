package com.carllewis14.revoluttest.data.model.rates

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


class CurrencyRatesDeserializer : JsonDeserializer<CurrencyRatesResponse> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyRatesResponse? {

        val currenciesJsonObject = json.asJsonObject

        val ratesJsonObject = currenciesJsonObject?.get("rates")?.asJsonObject
        var rates = mutableListOf<Currency>()

        if (ratesJsonObject != null) {
            for (key in ratesJsonObject.keySet()) {
                var rateJsonElement = ratesJsonObject.get(key)
                rates.add(
                    Currency(
                        code = key,
                        rate = rateJsonElement.asDouble
                    )
                )
            }
        }

        val currenciesResponse =
            CurrencyRatesResponse(
                base = currenciesJsonObject?.get("base")?.asString,
                date = currenciesJsonObject?.get("date")?.asString,
                currencies = rates
            )

        return currenciesResponse

    }

}