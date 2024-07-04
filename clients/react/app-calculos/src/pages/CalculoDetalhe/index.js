import React, {useState, useEffect} from 'react';
import DivContainter from '../DivContainer';
import api from '../../services/api';
import apiError from '../../services/apiError';

export default function CalculoDetalhe(){
    
    const [dadosCalculo, setDadosCalculo] = useState({});
    const [historicos, setHistoricos] = useState([]);
    const [numero1, setNumero1] = useState('');
    const [numero2, setNumero2] = useState('');
    const [sinal, setSinal] = useState('');

    useEffect(()=>{

        let calculo_id = localStorage.getItem('calculo_id');

        if( calculo_id === undefined || calculo_id === null ){
            window.location.href = '/calculos';
            return;
        }

        const getCalculo = async function(){
            await api.get(`calculos/detalhar/${calculo_id}`)
                .then(response => {
                    setDadosCalculo(response.data);
                })
                .catch(err => {
                    apiError(err);
                });
        }

        const historicos = async function(){
            await api.get(`calculo-historico/${calculo_id}`)
                .then(response => {
                    setHistoricos(response.data);
                })
                .catch(err => {
                    apiError(err);
                });
        }

        
        getCalculo();
        historicos();
        
    }, []);

    async function deletarCalculo(ev){
        
        ev.preventDefault();
        
        let calculo_id = localStorage.getItem('calculo_id');

        if( !window.confirm(`Deseja Realmente Deletar o Cálculo ${calculo_id}`) ){
            return;
        }
        
        await api.delete(`calculos/deletar/${calculo_id}`)
            .then(response => {
                alert('Calculo deletado com sucesso');
                window.location.href = '/calculos';
            })
            .catch(err => {
                apiError(err);
            });

    }

    async function atualizarCalculo(ev){
            
        ev.preventDefault();

        let calculo_id = localStorage.getItem('calculo_id');

        await api.put('calculos/atualizar', {
                id:calculo_id,
                numero1:numero1,
                numero2:numero2,
                sinal:sinal
            })
            .then(response => {
                
                let status = response.status;
                
                if( status === 200 && response.data.id !== undefined ){
                    alert('Calculo Atualizado com Sucesso')
                    window.location.href = '/calculos';
                    return;
                }

                alert('Não foi possível concluir atualização do cálculo')

            })
            .catch(err => {
                apiError(err);
            });

    }

    return (
        <DivContainter title='Detalhes do Calculo'>
            
            <div class="row">

                <div class="col-md-3">

                    <form class="formulario">

                        <h3>Calculo Id: {dadosCalculo.id}</h3>

                        <div class="row p-2">

                            <div class="col-md-3 d-flex justify-content-end">
                                <label for="numero1" class="form-label">Valor 1</label>
                            </div>
                            <div class="col-md-9">
                                <input type="number" id="numero1" name="numero1" step="0.01" class="form-control" aria-label="Primeiro Valor Numérico, inteiro ou decimal"
                                onChange={e => setNumero1(e.target.value)}></input>
                            </div>
                        </div>

                        <div class="row p-2">
                            <div class="col-md-3 d-flex justify-content-end">
                                <label for="numero2" class="form-label">Valor 2</label>
                            </div>
                            <div class="col-md-9">
                                <input type="number" id="numero2" name="numero2" step="0.01" class="form-control" aria-label="Primeiro Valor Numérico, inteiro ou decimal"
                                onChange={e => setNumero2(e.target.value)}></input>
                            </div>
                        </div>

                        <div class="row p-2">
                            <div class="col-md-3 d-flex justify-content-end">
                                <label for="sinal" class="form-label">Sinal</label>
                            </div>
                            <div class="col-md-9">
                                <select id="sinal" name="sinal" class="form-select" aria-label="Sinal para o cálculo matemático"
                                onChange={e => setSinal(e.target.value)}>
                                    <option></option>
                                    <option value="+">Adição</option>
                                    <option value="-">Subtração</option>
                                    <option value="*">Multiplicação</option>
                                    <option value="/">Divisão</option>
                                </select>
                            </div>
                        </div>

                        <div class="row p-2">
                            <div class="col-md-6">
                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-primary" onClick={deletarCalculo}>Deletar</button>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-primary" onClick={atualizarCalculo}>Salvar</button>
                                </div>
                            </div>
                        </div>

                    </form>

                </div>
                <div class="col-md-1"></div>
                <div class="col-md-8">
                    
                        <div class="card">
                            
                            <h3 class="card-header">Históricos do Cálculo</h3>

                            <div class="card-body">
                                
                                {                                    
                                    historicos.map(h => (
                                        <p class="card-text">{h.descricao}</p>
                                    ))
                                }

                            </div>

                        </div>
                </div>

            </div>

        </DivContainter>
    );
}