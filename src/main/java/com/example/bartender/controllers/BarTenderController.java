package com.example.bartender.controllers;

import com.example.bartender.dtos.ResponseUserDTO;
import com.example.bartender.services.BarTenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * BarTenderController es la clase que contiene las rutas de acceso REST de la aplicación
 */
@Controller
@RequestMapping("/")
public class BarTenderController {

    private static Logger logger = LoggerFactory.getLogger(BarTenderController.class);

    private String message;//Mensage ya sea de error o no
    private String typeMessage;//Identifica si hubo error con (0) o (1) éxito

    private ResponseUserDTO responseUser;//Clase que me permite almacenar la información que se necesita retornar al cliente

    @Autowired
    private BarTenderService barTenderService;//Servicio que ejecuta la iteración

    /**
     * Ruta REST GET de inicio, aquí se retorna el template iu que va a utilizar el usuario
     *
     * @param model interfaz de acceso y manipulación de parámetros para template
     * @throws Exception no genera excepciones ya que está controlado
     */
    @GetMapping("")
    public String getHome(Model model){
        model.addAttribute("typeMessage", typeMessage);//Identifica si hubo error con (0) o (1) éxito
        model.addAttribute("message", message);//Mensage ya sea de error o no
        model.addAttribute("logs", responseUser != null ? responseUser.getListLogDTO(): null);//Lista de los resultados de la iteración
        //Se vacian ya que fueron enviadas
        typeMessage = "";
        message = "";
        responseUser = null;
        return "index";
    }

    /**
     * Ruta REST POST que recibe la información indicada por el usuario desde el formulario
     * recibe el params "Iterate" para no redirigir innecesariamente a otra ruta y permanecer siempre en el inicio
     * así evitamos que el usuario tenga que hacer uso de botones para ir atrás
     *
     * @param request la petición HTTP.
     * @throws Exception no genera excepciones ya que está controlado
     */
    @PostMapping(value = "", params = {"iterate"})
    public String postIterate(HttpServletRequest request){
        try {//Definimos un try por si el usuario no envía info correcta
            //Definimos las variables que vamos a usar
            int qSize = Integer.parseInt(request.getParameter("qSize"));
            int dataRange = Integer.parseInt(request.getParameter("dataRange"));

            //Realizamos la iteración desde el servicio encargado
            responseUser = barTenderService.iterate(qSize, dataRange);
            message = responseUser.getResult();
            typeMessage = "1";
        } catch(Exception e){
            typeMessage = "0";
            message = "Error al iterar, verifique que esté enviando un número de iteración correcto y el id de la pila de datos correcto";
        }
        return "redirect:/";
    }
}
