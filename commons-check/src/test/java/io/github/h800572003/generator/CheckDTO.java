package io.github.h800572003.generator;

public class CheckDTO extends BaseDTO implements ICheckDTO, ICheckDTO2 {

	private String name;

	public void setName(String name) {
		this.name = name;

	}

	@Override
	public String getName() {
		return name;
	}

}
