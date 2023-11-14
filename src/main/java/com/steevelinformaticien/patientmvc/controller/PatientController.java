package com.steevelinformaticien.patientmvc.controller;

import com.steevelinformaticien.patientmvc.entities.Patient;
import com.steevelinformaticien.patientmvc.repositories.PatientRepositories;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepositories patientRepositories;

    @GetMapping(path = "/index")
    public String patients(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size ,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {
         Page<Patient> patientPage = patientRepositories.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients", patientPage.getContent());
        System.out.println(patientPage.getTotalPages());
        model.addAttribute("pages",new int[patientPage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id , String keyword , int page){
        patientRepositories.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping( path = "/")
    public String home(){
        return "redirect:/index";
    }


    @GetMapping(path = "/patients")
    @ResponseBody
    public List<Patient> allPatient(){
        return patientRepositories.findAll();
    }
    @GetMapping(path = "/formPatients")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }

    @PostMapping(path = "/save")
    public String save(Model model , @Valid  Patient patient , BindingResult bindingResult , int page , String keyword){
        if(bindingResult.hasErrors())return "formPatients";
        patientRepositories.save(patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }


  @GetMapping(path = "/edit-patient")
    public String editPatient(Model model , Long id , String keyword , int page  ){
        Patient patient=patientRepositories.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }



}
