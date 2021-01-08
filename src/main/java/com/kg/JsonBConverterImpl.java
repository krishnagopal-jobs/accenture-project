package com.kg;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyOrderStrategy;


public class JsonBConverterImpl implements JsonConverter<SampleObject,Object> {


	private Jsonb jsonb;
	
	public JsonBConverterImpl()  {
		JsonbConfig config = new JsonbConfig()
			    .withPropertyOrderStrategy(PropertyOrderStrategy.LEXICOGRAPHICAL);
		jsonb = JsonbBuilder.create(config);
	}

	@Override
	public SampleObject unmarshall(String stringJson) {
		return jsonb.fromJson(stringJson, SampleObject.class);
	}

	@Override
	public String marshall(Object sampleObject) {
		return jsonb.toJson(sampleObject);
	}
	

}
