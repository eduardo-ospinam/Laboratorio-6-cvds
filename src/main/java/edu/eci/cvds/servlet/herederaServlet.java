package edu.eci.cvds.servlet;

import edu.eci.cvds.servlet.model.Todo;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
	    urlPatterns = "/pruebaOspinaMedina"
	)

public class herederaServlet extends HttpServlet {
	
	//doGet () se utilizará cuando una pequeña cantidad de datos y datos insensibles, como una consulta
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer responseWriter = resp.getWriter();
	    Optional<String> optName = Optional.ofNullable(req.getParameter("id"));
		String parametroId = optName.isPresent() && !optName.get().isEmpty() ? optName.get() : "";
        if(parametroId != "" ){
            try{
                int numeroId = Integer.parseInt(parametroId);
                if(numeroId<=200 && numeroId >=1 ){
                    Todo todo = Service.getTodo(numeroId);
                    List<Todo> todoList = new ArrayList<>();
                    todoList.add(todo);
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.setContentType("text/html"); //ignifica qué tipo de respuesta desea enviar al cliente,
                    responseWriter.write(Service.todosToHTMLTable(todoList));
                    responseWriter.flush();
                }else{
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND); //SC_NOT_FOUND: código de estado (404) que indica que el recurso solicitado no está disponible.
                    responseWriter.write("No existe un item con el identificador dado.");
                    responseWriter.flush();
                }
            }catch(MalformedURLException e){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); //Código de estado (500) que indica un error dentro del servidor HTTP que le impidió cumplir con la solicitud.
                responseWriter.write("Error interno del servidor.");
                responseWriter.flush();
            }catch (Exception e){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);//400: bad request, esta respuesta siginifica que el servidor no pudo interpretar la solicitud dada
                responseWriter.write("Requerimiento inválido.");
                responseWriter.flush();
            }
            
        }else{
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseWriter.write("Requerimiento inválido.");
            responseWriter.flush();
        }   
    }
	
	// doPost () se utilizará cuando se deba enviar una cantidad relativamente grande de datos confidenciales.
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer responseWriter = resp.getWriter();
	    Optional<String> optName = Optional.ofNullable(req.getParameter("id"));
		String parametroId = optName.isPresent() && !optName.get().isEmpty() ? optName.get() : "";
        
        if(parametroId != "" ){
            try{
                int numeroId = Integer.parseInt(parametroId);
                if(numeroId<=200 && numeroId >=1 ){
                    Todo todo = Service.getTodo(numeroId);
                    List<Todo> todoList = new ArrayList<>();
                    todoList.add(todo);
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.setContentType("text/html"); //ignifica qué tipo de respuesta desea enviar al cliente,
                    responseWriter.write(Service.todosToHTMLTable(todoList));
                    responseWriter.flush();
                }else{
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND); //SC_NOT_FOUND: código de estado (404) que indica que el recurso solicitado no está disponible.
                    responseWriter.write("No existe un item con el identificador dado.");
                    responseWriter.flush();
                }
            }catch(MalformedURLException e){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); //Código de estado (500) que indica un error dentro del servidor HTTP que le impidió cumplir con la solicitud.
                responseWriter.write("Error interno del servidor.");
                responseWriter.flush();
            }catch (Exception e){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);//400: bad request, esta respuesta siginifica que el servidor no pudo interpretar la solicitud dada
                responseWriter.write("Requerimiento inválido.");
                responseWriter.flush();
            }
            
        }else{
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseWriter.write("Requerimiento inválido.");
            responseWriter.flush();
        }   
    }
}