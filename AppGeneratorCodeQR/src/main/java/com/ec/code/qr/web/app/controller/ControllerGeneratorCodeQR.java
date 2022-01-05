package com.ec.code.qr.web.app.controller;

import java.util.concurrent.ExecutionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ec.code.qr.web.app.Pages;
import com.ec.code.qr.web.app.model.CodeQR;
import com.ec.code.qr.web.app.service.ServiceGeneratorCodeQR;

@Controller
@RequestMapping("/codeqr")
public class ControllerGeneratorCodeQR {

	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	ServiceGeneratorCodeQR serviceQR;
	
	@GetMapping("/generateQR")
	public String viewQR(Model model) throws InterruptedException, ExecutionException {
		log.info("INICIAR MODULO GENERAR QR");
		
		addAttribute(model, new CodeQR());
		
		return Pages.CODE_QR_FRM;
	}
	
	@PostMapping("/generateQR")
	public String generatorCodeQR(@Validated @ModelAttribute("codeqr") CodeQR codeqr, BindingResult result, Model model) throws InterruptedException, ExecutionException {
		log.info("GENERAR NUEVO CODIGO QR : " + codeqr.getCode() + " " + codeqr.getDescription());
		
		if (result.hasErrors()) {
			addAttribute(model, codeqr);
			model.addAttribute("errorSave", "Error al guardar, complete los datos");
		} else {
			try {
				//serviceQR.createQR(codeqr, null);
				//CodeQR qrID = serviceQR.generatorQR(codeqr);
				serviceQR.generatorQRCode(codeqr.getCode(), 500, 500);
				//addAttribute(model, codeqr);
				log.info("*** CODE QR CON EXITO  ***");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				log.error("XXXXX ERROR AL GUARDAR CODE QR XXXXX");
				addAttribute(model, codeqr);
			}
		}
		
		return Pages.CODE_QR;
	}
	
	private void addAttribute(Model model, CodeQR codeQR)  throws InterruptedException, ExecutionException {
		model.addAttribute("codeqr", codeQR);
		
	}
}
