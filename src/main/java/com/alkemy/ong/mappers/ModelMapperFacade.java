package com.alkemy.ong.mappers;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

public interface ModelMapperFacade {

	public static  <S, T> T map(S source, Class<T> destinationType) {
		return new ModelMapper().map(source, destinationType);
	}

	public static <S, D> D patchObject(S source, D destination) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		mapper.map(source, destination);
		return destination;
	}

}
