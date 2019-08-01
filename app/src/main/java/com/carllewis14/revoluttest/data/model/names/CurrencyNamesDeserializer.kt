package com.carllewis14.revoluttest.data.model.names

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


class CurrencyNamesDeserializer : JsonDeserializer<CurrencyNamesResponse> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyNamesResponse? {

        val namesJsonObject = json.asJsonObject

        var namesMap = HashMap<String, String>()

        if (namesJsonObject != null) {
            for (key in namesJsonObject.keySet()) {
                var rateJsonElement = namesJsonObject.get(key)
                namesMap.put(key, rateJsonElement.asString)
            }
        }

        val response = CurrencyNamesResponse(namesMap)

        return response

    }

}