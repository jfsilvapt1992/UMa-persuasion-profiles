package com.uma.mestrado.persuasive_profiles.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewDicasAlimentacaoController
{

  Logger logger = LoggerFactory.getLogger(ViewDicasAlimentacaoController.class);

  // @Autowired
  // private BackendAPIServices backendApiService;a

  @GetMapping(
  {
    "dicas-alimentacao",
    "dicas-alimentacao.html"
  })
  private String getDicasAlimentacao(Model aModel)
  {
    return "dicas-alimentacao";
  }
}
