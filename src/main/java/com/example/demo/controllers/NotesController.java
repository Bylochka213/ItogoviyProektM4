package com.example.demo.controllers;

import com.example.demo.dao.WebNoteDAO;
import com.example.demo.models.WebNote;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class NotesController {
	
	private final WebNoteDAO webNoteDAO;
	
	public NotesController(WebNoteDAO webNoteDAO) {
		 this.webNoteDAO = webNoteDAO;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("notes", webNoteDAO.index());
		return "index";
	}
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id,Model model) {
		model.addAttribute("note", webNoteDAO.show(id));
		return "show";
	}
	
	@PostMapping("/{note}")
	public String create(@RequestParam("note") String note,Model model) {
		WebNote webNote = new WebNote();
		webNote.setNote(note);
		return "success Page";
	}
	@GetMapping("/new")
	public String newNote(Model model) {
		model.addAttribute("webNote",new WebNote());
		return "new";
	}
	@PostMapping("/create")
	public String create(@ModelAttribute("webNote") WebNote webNote) {
		webNoteDAO.save(webNote);
		return "redirect:/";
	}
	@GetMapping("/{id}/edit")
	public String edit(Model model,@PathVariable("id") int id) {
		model.addAttribute("webNote",webNoteDAO.show(id));
		return "edit";
	}
	@GetMapping("/{id}/update")
	public String update(@ModelAttribute("webNote") WebNote webNote, @PathVariable("id") int id) {
		webNoteDAO.update(id,webNote.getNote());
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		webNoteDAO.delete(id);
		return "redirect:/";
	}

}
