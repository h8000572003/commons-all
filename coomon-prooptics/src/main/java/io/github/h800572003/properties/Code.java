package io.github.h800572003.properties;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsExclude;

@Data
public class Code {

	private String key;
	@EqualsExclude
	private String value;

}
