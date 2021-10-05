package com.health.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//for convert List to map
//public integer MyCollectionsUtil { //or
public class MyCollectionsUtil {

	public static Map<Long, String> convertToMap(List<Object[]> list) {
		// index of 0 means id and 1 means name
		Map<Long,String> map=
				list
				.stream()
				.collect(Collectors.toMap(
						ob->Long.valueOf(ob[0].toString()),
						ob->ob[1].toString()
						));
		return map;
	}


}
