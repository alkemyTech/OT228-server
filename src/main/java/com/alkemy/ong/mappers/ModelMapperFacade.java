package com.alkemy.ong.mappers;

import java.util.List;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

public interface ModelMapperFacade {

	public static  <S, T> T map(S source, Class<T> destinationType) {
		return new ModelMapper().map(source, destinationType);
	}

	public static  <S, T> T map(S source, Class<T> destinationType, List<String> skipFields) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(new Condition<>() {
			@Override
			public boolean applies(MappingContext<Object, Object> context) {
				return !skipFields.stream()
						.anyMatch(f -> context.getMapping().getLastDestinationProperty().getName().equals(f));
			}
		});
		return modelMapper.map(source, destinationType);
	}

}
