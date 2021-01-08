package com.kg;

public interface JsonConverter<T, U> {
	
	T unmarshall(String stringJson);

	String marshall(U t);

}
