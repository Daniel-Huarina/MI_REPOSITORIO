<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Producto"%>
<%
    
    if(session.getAttribute("listapro") == null){
       
       ArrayList<Producto> lisaux = new ArrayList<Producto>();
       
       session.setAttribute("listapro", lisaux);
    }
       ArrayList<Producto> lista = (ArrayList<Producto>)session.getAttribute("listapro");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Productos</h1>
        <a href="MainServlet?op=nuevo">Nuevo</a>>
        <table border ="1">
            <tr>
                <th>Id</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoria</th>
                <th></th>
                <th></th>
            </tr>
            <%
              if (lista !=null){
            for (Producto item : lista){
              
            %>
            
            <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getDescripcion() %></td>
                <td><%= item.getCantidad() %></td>
                <td><%= item.getPrecio()%></td>
                <td><%= item.getCategoria() %></td>
                <td>
                    <a href="MainServlet?op=editar&id=<%= item.getId() %>">Editar</a>
                </td>
                <td>
                    <a href="MainServlet?op=eliminar&id=<%= item.getId() %>"
                       onclick="return (confirm('Esta seguro de eliminar'))">Eliminar</a></td>
            </tr>
            <%
                }
                
                }
            
            %>
            
        </table>
    </body>
</html>
