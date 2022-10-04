package io.github.h800572003.generator.strategy.basedtogenerator;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class BaseGenDTO {
    private String typeClass;
    private String type;
    private String name;
    private String memo;
    private boolean isNum=false;
    private boolean isAssign=false;
    private String value;
    private String columnName;
}
