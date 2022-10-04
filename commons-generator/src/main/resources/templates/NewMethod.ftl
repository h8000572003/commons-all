    <#if memo!="">
//${memo}
    </#if>
    <#if header!="">
${header}
    </#if>
${protectedValue} ${returnType} ${name}(${args})${exceptions} {
   <#if body!="">
   ${body}
   <#else>
   //TODO
   </#if>
    }