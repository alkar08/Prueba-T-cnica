package com.amelissa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amelissa.models.entity.Tercero;
import com.amelissa.models.service.ITerceroService;

@Controller
@SessionAttributes("tercero")
public class TerceroController {
	
	@Autowired
	private ITerceroService terceroService;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String crear (Model model) {
		
		Tercero tercero = new Tercero();
		model.addAttribute("tercero", tercero);
		model.addAttribute("titulo", "Crear Tercero");
		return "form";
	}
	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar (Model model){
		
		List<Tercero> listaTerceros = new ArrayList<Tercero>();
		listaTerceros = terceroService.findAll();
		model.addAttribute("listaTerceros", listaTerceros);
		model.addAttribute("titulo", "Listado de Terceros");
		return "listar";
	}
	
	@RequestMapping(value = "/ver/{id}", method = RequestMethod.GET)
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		
		Tercero tercero = terceroService.findOne(id);
		if (tercero == null) {
			flash.addAttribute("error", "El tercero no existe en la Base de Datos");
			return "redirect:/listar";
		}
		model.addAttribute("tercero", tercero);
		model.addAttribute("titulo","Detalle tercero: "+ tercero.getNombres());
		return "ver";
	}
	
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Tercero tercero, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Terceros");
			return "form";
		}

		String mensajeFlash = (tercero.getId() !=null)? "Tercero editado con éxito!" :"Tercero creado con éxito!";
		
		terceroService.save(tercero);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash );
		return "redirect:/listar";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		
		Tercero tercero = null;
		
		if (id > 0) {
			tercero = terceroService.findOne(id);
			if (tercero == null) {
				flash.addFlashAttribute("error", "El ID del tercero no existe en la Base de Datos!");
				return "redirect:/listar";
			}
		}else {
			flash.addFlashAttribute("error", "El ID del tercero no puede ser cero!");
			return "redirect:/listar";
		}
		model.addAttribute("tercero", tercero);
		model.addAttribute("titulo", "Editar Tercero");
		return "form";
	}
	
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		if (id > 0) {
			terceroService.delete(id);
			flash.addFlashAttribute("success", "Tercero eliminado con éxito");
		}else {
			flash.addFlashAttribute("error", "El ID del tercero no puede ser cero!");
			return "redirect:/listar";
		}	
		return "redirect:/listar";
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public String buscar  (Model model) {
		Tercero tercero = new Tercero();
		model.addAttribute("tercero", tercero);
		model.addAttribute("titulo", "Buscar Tercero");
		return "buscar";
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.POST)
	public String buscarRespuesta(Tercero tercero, Model model, RedirectAttributes flash) {
		
		Tercero findTercero = terceroService.findByDocumento(tercero.getDocumento());
		if (findTercero == null) {
			flash.addFlashAttribute("error", "El documento del tercero no existe!");
			return "redirect:/listar";
		}
		
		model.addAttribute("tercero", findTercero);
		model.addAttribute("titulo", "Tercero Encontrado");
		return "buscar";
	}
	

}
