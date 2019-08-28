<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
          "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:rich="http://richfaces.org/rich">
	
<f:loadBundle basename="properties.standard.library" var="std" /> 
	
<head>
  <title><ui:insert name="title">Library for all</ui:insert></title>
  <link rel="stylesheet" type="text/css" href="${facesContext.externalContext.requestContextPath}/css/style.css" />
  
</head>

<body>
  
   <h1>Welcome to LibraryForAll your online free library</h1>
      
      <h:form>
         <h:panelGrid id = "panel" columns = "2" border = "0"
            cellpadding = "13" cellspacing = "1">          
            <f:facet name = "header">
               <h:outputText id="identification" value = "Réinisialisez votre mot de passe" style="font-size:33px;"/>
            </f:facet>
            <h:outputLabel value = "#{std.nosso_pass}" />
            <h:inputSecret value="#{login.pwd}"/>
            <h:outputLabel value = "#{std.nosso_pass}" />
            <h:inputSecret value="#{login.pwd}"/>     
            <h:messages style="color: #f1c860"/> 
                   
            <f:facet name = "footer">
               <h:panelGroup style = "display:block; text-align:center">
                  <h:commandButton id = "submit" value = "Confirmer" action="#{login.reinisialisezPassword}"/>
               </h:panelGroup>
               <br/>
               
            </f:facet>
         </h:panelGrid>
         
         <!-- <h:messages style="color: red"/> -->
      </h:form>

</body>
</html>

