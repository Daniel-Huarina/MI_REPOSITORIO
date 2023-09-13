/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes.controlador;

import com.emergentes.modelo.Producto;
import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DANIEL
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String op = request.getParameter("op");
         Producto obj1 = new Producto();
         int id,pos;
        
         HttpSession ses = request.getSession();
         ArrayList<Producto>lista=(ArrayList<Producto>)ses.getAttribute("listapro");
    
       switch(op){
           
           case "nuevo":
               
               request.setAttribute("miProducto", obj1);
               request.getRequestDispatcher("editar.jsp").forward(request, response);
               break;
               
           case "editar":
               
              id = Integer.parseInt(request.getParameter("id"));
              pos = buscarIndice(request,id);
              obj1 = lista.get(pos);
              request.setAttribute("miProducto", obj1);
              request.getRequestDispatcher("editar.jsp").forward(request, response);
              break;
              
           case "eliminar":
               
              id = Integer.parseInt(request.getParameter("id"));
              pos = buscarIndice(request,id);
              if(pos>=0){
                    lista.remove(pos);
              }
              request.setAttribute("listapro", lista);
              response.sendRedirect("index.jsp");
               break;
              
           default:
              
       }
     }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession ses = request.getSession();
        ArrayList<Producto> lista= (ArrayList<Producto>)ses.getAttribute("listapro");
        Producto obj1 = new Producto();
        obj1.setId(id);
        obj1.setDescripcion(request.getParameter("descripcion"));
        obj1.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        obj1.setPrecio(Integer.parseInt(request.getParameter("precio")));
        obj1.setCategoria(request.getParameter("categoria"));
        
        if(id ==0){
        int idnuevo = obtenerId(request);
        obj1.setId(idnuevo);
        lista.add(obj1);
        
        }
        
        else{
        int pos = buscarIndice(request,id);
        lista.set(pos, obj1);
        }
        request.setAttribute("listapro", lista);
        response.sendRedirect("index.jsp");
    }
    
    public int buscarIndice(HttpServletRequest request,int id){
    
    HttpSession ses = request.getSession();
        ArrayList<Producto> lista= (ArrayList<Producto>)ses.getAttribute("listapro");
    
        int pos = -1;
        
        if(lista != null){
        for(Producto ele : lista){
        ++pos;
        if(ele.getId()== id){
            
            break;
        
        }
        
        }
        
    }
  
    return pos;
    
}
public int obtenerId(HttpServletRequest request){

     HttpSession ses = request.getSession();
     ArrayList<Producto> lista= (ArrayList<Producto>)ses.getAttribute("listapro");

int idn = 0;

for (Producto ele : lista){
idn = ele.getId();
}
return idn + 1;
}
        
   
}
