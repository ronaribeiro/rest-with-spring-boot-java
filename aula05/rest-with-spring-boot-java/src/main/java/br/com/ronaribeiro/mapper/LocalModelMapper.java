package br.com.ronaribeiro.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

//import com.github.dozermapper.core.DozerBeanMapperBuilder;
//import com.github.dozermapper.core.Mapper;

public class LocalModelMapper {
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return modelMapper.map(origin, destination);		
		
	}
	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
		
		List<D> destinatioObjects = new ArrayList<D>();
		
		for (O o: origin) {
			destinatioObjects.add(modelMapper.map(o,destination));			
		}
		
		return destinatioObjects;
	}

}
