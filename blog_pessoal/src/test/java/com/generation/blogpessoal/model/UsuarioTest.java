package com.generation.blogpessoal.model;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTest {

    public Usuario usuario;
    public Usuario usuarioErro = new Usuario();

    @Autowired
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    Validator validator = factory.getValidator();

    @BeforeEach
    public void start() throws ParseException {


        LocalDate data = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Usuario usuario = new Usuario(0, "João Silva", "joao@email.com.br", "13465278", data);
        
        if(usuarioRepository.findByLogin(usuario.getLogin()) != null)
			usuarioRepository.save(usuario);
        
        usuario = new Usuario(0, "Manuel da Silva", "manuel@email.com.br", "13465278", data);

        if(usuarioRepository.findByLogin(usuario.getLogin()) != null)
            usuarioRepository.save(usuario);

        usuario = new Usuario(0, "Fred da Silva", "frederico@email.com.br", "13465278", data);

        if(usuarioRepository.findByLogin(usuario.getLogin()) != null)
            usuarioRepository.save(usuario);

       	usuario = new Usuario(0, "Paulo Antunes", "paulo@email.com.br", "13465278", data);

        if(usuarioRepository.findByLogin(usuario.getLogin()) != null)
            usuarioRepository.save(usuario);
  }

    @Test
    @DisplayName("✔ Valida Atributos Não Nulos")
    void testValidaAtributos() {

        Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
        System.out.println(violacao.toString());
        assertTrue(violacao.isEmpty());

    }

    @Test
    @DisplayName("❌ Valida Atributos Nulos")
    void testValidaAtributosNulos() {
        
        usuarioErro.setLogin("paulo@email.com.br");
        Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioErro);
        System.out.println(violacao.toString());
        assertFalse(violacao.isEmpty());
   
    }

}
