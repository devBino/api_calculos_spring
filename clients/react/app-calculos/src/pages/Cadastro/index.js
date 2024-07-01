import React, {useState} from "react";
import {useNavigate} from 'react-router-dom';
import DivContainer from "../DivContainer";
import api from '../../services/api';

export default function Cadastro(){
    
    const [numero1, setNumero1] = useState('');
    const [numero2, setNumero2] = useState('');
    const [sinal, setSinal] = useState('');

    const navigate = useNavigate();

    async function cadastrar(ev){
        
        ev.preventDefault();

        try{

            let calculo = {
                numero1: numero1,
                numero2: numero2,
                sinal: sinal
            }

            const response = await api.post('calculos/criar', calculo);

            if( response.data.id !== undefined && response.data.id !== null ){

                alert('Calculo Cadastrado com sucesso');

                document.getElementById('numero1').value = '';
                document.getElementById('numero2').value = '';
                document.getElementById('sinal').value = '';
                
            }

            navigate('/cadastro');

        }catch(err){
            alert('Ocorreu um erro ao tentar cadastrar o cálculo...');
        }

    }

    return (
        <DivContainer title="Cadastro de Novo Cálculo">
            
            <div class="col-md-4">

            </div>
            <div class="col-md-4">
                <form class="form-control" onSubmit={cadastrar}>

                    <div class="row p-2">
                        <div class="col-md-3 d-flex justify-content-end">
                            <label for="numero1" class="form-label">Valor 1</label>
                        </div>
                        <div class="col-md-9">
                            <input type="number" id="numero1" name="numero1" step="0.01" class="form-control" aria-label="Primeiro Valor Numérico, inteiro ou decimal"
                            value={numero1} onChange={e => setNumero1(e.target.value)}></input>
                        </div>
                    </div>

                    <div class="row p-2">
                        <div class="col-md-3 d-flex justify-content-end">
                            <label for="numero2" class="form-label">Valor 2</label>
                        </div>
                        <div class="col-md-9">
                            <input type="number" id="numero2" name="numero2" step="0.01" class="form-control" aria-label="Segundo Valor Numérico, inteiro ou decimal"
                            value={numero2} onChange={e => setNumero2(e.target.value)}></input>
                        </div>
                    </div>

                    <div class="row p-2">
                        <div class="col-md-3 d-flex justify-content-end">
                            <label for="sinal" class="form-label">Sinal</label>
                        </div>
                        <div class="col-md-9">
                            <select id="sinal" name="sinal" class="form-select" aria-label="Sinal para o cálculo matemático"
                            value={sinal} onChange={e => setSinal(e.target.value)}>
                                <option></option>
                                <option value="+">Adição</option>
                                <option value="-">Subtração</option>
                                <option value="*">Multiplicação</option>
                                <option value="/">Divisão</option>
                            </select>
                        </div>
                    </div>

                    <div class="row p-2">
                        <div class="col-md-12">
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary">Acessar</button>
                            </div>
                        </div>
                    </div>

                </form>
            </div>
            <div class="col-md-4">

            </div>
        </DivContainer>
    )
}