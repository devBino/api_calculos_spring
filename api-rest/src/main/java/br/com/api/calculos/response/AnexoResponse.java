package br.com.api.calculos.response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.api.calculos.vo.AnexoVO;
import jakarta.validation.ConstraintViolation;

/**
 * Responde a cenários de erro durante requisições
 * recebidas e processadas em AnexoController
 */
@Component
public class AnexoResponse extends BaseResponse {
    
    /**
     * Recebe os erros da validação de AnexoBO
     * e os retornam no response
     * @param erros
     * @return
     */
    public ResponseEntity<?> buildResponseErros(Set<ConstraintViolation<AnexoVO>> erros){
        
        Map<String, String> mapErros = new HashMap<>();

        erros.forEach(e -> {
            mapErros.put(e.getPropertyPath().toString(), e.getMessage());
        });

        return ResponseEntity.badRequest().body(Map.of("erros", mapErros));

    }

}
