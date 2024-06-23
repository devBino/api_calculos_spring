package br.com.api.calculos.response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.api.calculos.vo.GenericParamIDVO;
import br.com.api.calculos.vo.PaginateParansVO;
import jakarta.validation.ConstraintViolation;

/**
 * Componente base para respostas durante processamento
 * das requisições recebidas nas controllers
 */
@Component
public class BaseResponse {
    
    public ResponseEntity<?> buildResponseErrosParamId(Set<ConstraintViolation<GenericParamIDVO>> erros){
        
        Map<String, String> mapErros = new HashMap<>();

        erros.forEach(e -> {
            mapErros.put(e.getPropertyPath().toString(), e.getMessage());
        });

        return ResponseEntity.badRequest().body(Map.of("erros", mapErros));

    }

    public ResponseEntity<?> buildResponseErrosPaginacao(Set<ConstraintViolation<PaginateParansVO>> erros){
        
        Map<String, String> mapErros = new HashMap<>();

        erros.forEach(e -> {
            mapErros.put(e.getPropertyPath().toString(), e.getMessage());
        });

        return ResponseEntity.badRequest().body(Map.of("erros", mapErros));

    }

    public ResponseEntity<?> buildResponseErros(Map<String, String> erros){
        
        Map<String, String> mapErros = new HashMap<>();

        for(String e : erros.keySet()){
            mapErros.put(e, erros.get(e));
        }
        
        return ResponseEntity.badRequest().body(Map.of("erros", mapErros));

    }

}
