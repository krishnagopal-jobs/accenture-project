package com.kg;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {

//		final String [] b = new String [] {
//		"{uuid: 2, name: 'Darling'}",
//		"{uuid: 3, name: 'Cry-Baby'}",
//		"{uuid: 4, name: 'Snitch'}",
//		"{uuid: 5, name: 'Pawn'}"
//		};

		final String[] b = new String[] { "{\"uuid\": 2, \"name\": \"Darling\"}",
				"{\"uuid\": 3, \"name\": \"Cry-Baby\"}", "{\"uuid\": 4, \"name\": \"Snitch\"}",
				"{\"uuid\": 5, \"name\": \"Pawn\"}" };

//		final String [] a = new String[] {
//		"{uuid: 1, role: 'admin'}",
//		"{uuid: 2, role: 'contributor'}",
//		"{uuid: 3, role: 'owner'}",
//		"{uuid: 4, role: 'contributor'}"
//		};

		final String[] a = new String[] { "{\"uuid\": 1, \"role\": \"admin\"}",
				"{\"uuid\": 2, \"role\": \"contributor\"}", "{\"uuid\": 3, \"role\": \"owner\"}",
				"{\"uuid\": 4, \"role\": \"contributor\"}" };

		JsonConverter<SampleObject, Object> converter = new JsonBConverterImpl();

		Map<Integer, SampleObject> aMap = Arrays.asList(a).stream().map(converter::unmarshall).sorted().collect(
				Collectors.toMap(SampleObject::getUuid, Function.identity(), Main::mergeSamples, TreeMap::new));

		Map<Integer, SampleObject> bMap = Arrays.asList(b).stream().map(converter::unmarshall).collect(
				Collectors.toMap(SampleObject::getUuid, Function.identity(), Main::mergeSamples, TreeMap::new));

		aMap.values().stream().forEach(sampleObject -> {
			SampleObject obj = bMap.get(sampleObject.getUuid());
			Optional.ofNullable(obj).map(SampleObject::getName).ifPresent(sampleObject::setName);
		});

		String result = converter.marshall(aMap.values());

		System.out.println(result);

		// OUTPUT
		// [
		// {uuid:1, name: null, role: 'admin'},
		// {uuid:2, name: 'Darling', role: 'contributor'},
		// {uuid:3, name: 'Cry-Baby', role: 'owner'},
		// {uuid:4, name: 'Snitch', role: 'contributor'},
		// {uuid:5, name: 'Pawn', role: null}
		// ]

	}

	private static SampleObject mergeSamples(SampleObject o1, SampleObject o2) {
		SampleObject result = new SampleObject();
		result.setUuid(o1.getUuid());
		result.setName(o1.getName() + "|" + o2.getName());
		result.setRole(o1.getRole() + "|" + o2.getRole());
		return result;
	}

}
