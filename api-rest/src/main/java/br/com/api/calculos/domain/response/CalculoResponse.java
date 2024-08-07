package br.com.api.calculos.domain.response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.api.calculos.domain.vo.CalculoVO;
import jakarta.validation.ConstraintViolation;

/**
 * Responde a cenários de erro durante requisições
 * recebidas e processadas em CalculoController
 */
@Component
public class CalculoResponse extends BaseResponse {
    
    /**
     * Recebe os erros da validação de CalculoVO
     * e os retornam no response
     * @param erros
     * @return
     */
    public ResponseEntity<?> buildResponseErros(Set<ConstraintViolation<CalculoVO>> erros){
        
        Map<String, String> mapErros = new HashMap<>();

        erros.forEach(e -> {
            mapErros.put(e.getPropertyPath().toString(), e.getMessage());
        });

        return ResponseEntity.badRequest().body(Map.of("erros", mapErros));

    }

}
