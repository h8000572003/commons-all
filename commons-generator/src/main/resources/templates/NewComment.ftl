<#if multiLine>

        /**
       <#list comments as item>
          *${item}
       </#list>
        */

       <#else>
         <#list comments as item>
                  //${item}
         </#list>
</#if>