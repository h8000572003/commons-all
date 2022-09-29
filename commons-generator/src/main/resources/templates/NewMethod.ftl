    <#if memo!="">
    //${memo}
    </#if>
    <#if header!="">
    ${header}
    </#if>
    ${protectedValue} ${returnType} ${name}(${args}) {
       <#if body!="">
          ${body}
       <#else>
        //TODO
       </#if>
    }