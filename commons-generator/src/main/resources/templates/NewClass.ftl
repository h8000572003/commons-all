
<#if header!="">
${header}
</#if>
${protectedValue} class ${name} ${extend}  ${implement}{
    <#if constructorArgs!="">
      ${protectedValue} ${name}(${constructorArgs}){

      }
    </#if>
    <#if body!="">
${body}
    <#else>
//TODO
    </#if>
}
